package pl.ething.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ething.dto.EthingThingAndFeatures;
import pl.ething.model.EthingFeature;
import pl.ething.model.EthingThing;
import pl.ething.model.EthingThingtype;
import pl.ething.model.EthingUser;
import pl.ething.repository.EthingFeatureRepository;
import pl.ething.repository.EthingThingRepository;
import pl.ething.repository.EthingThingTypeRepository;
import pl.ething.repository.EthingUserRepository;

/**
 *
 * @author prographer
 */
@org.springframework.stereotype.Controller
public class ThingController {

    @Autowired
    EthingUserRepository ethingUserRepository;
    @Autowired
    EthingThingTypeRepository ethingThingTypeRepository;
    @Autowired
    EthingThingRepository ethingThingRepository;
    @Autowired
    EthingFeatureRepository ethingFeatureRepository;

    @RequestMapping(value = "/deleteThing", method = RequestMethod.POST)
    public @ResponseBody
    String deleteThing(HttpServletRequest request, @RequestBody String idhash, Principal principal) {
        try {
            if (principal != null) {
                EthingThing thing = ethingThingRepository.findEthingThingByIdhash(idhash);
                EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
                System.out.print(thing.getUserid().getId() + "  " + user.getId());
                if (thing.getUserid().getId().equals(user.getId())) {
                    //System.out.print("aaa");
                    Set<EthingFeature> features = thing.getEthingFeatureSet();
                    for (Iterator<EthingFeature> iterator = features.iterator(); iterator.hasNext();) {
                        EthingFeature next = iterator.next();
                        ethingFeatureRepository.delete(next);
                    }
                    //thing.setUserid(null);
                    //thing.setEthingFeatureSet(null);
                    //thing.setEthingThingimageSet(null);
                    ethingThingRepository.delete(thing.getId());
                } else {
                    return "error";
                }
                return "message";
            } else {
                return "error";
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/editThing", method = RequestMethod.POST)
    public @ResponseBody
    String editThing(HttpServletRequest request, @RequestBody EthingThingAndFeatures ethingThingAndFeatures, Principal principal) {

        try {
            EthingThing newThing = ethingThingAndFeatures.getEthingThing();
            //not impl.
            EthingThing oldThing = ethingThingRepository.findEthingThingByIdhash(newThing.getIdhash());
            Set<EthingFeature> oldFeature = oldThing.getEthingFeatureSet();
            for (Iterator<EthingFeature> iterator = oldFeature.iterator(); iterator.hasNext();) {
                EthingFeature next = iterator.next();
                ethingFeatureRepository.delete(next);
            }
            newThing.setId(oldThing.getId());
            newThing.setEthingThingimageSet(null);//not impl.
            newThing.setIdhash(ethingThingAndFeatures.getEthingThing().getIdhash());
            EthingThingtype thingType = ethingThingTypeRepository.findEthingThingtypeByName(newThing.getThingtype().getName());
            newThing.setThingtype(thingType);//not impl.
            EthingUser thingUser = ethingUserRepository.findEthingUserByLoginAndActivation(principal.getName(), "1");
            newThing.setUserid(thingUser);
            EthingThing thing = ethingThingRepository.save(newThing);
            List<EthingFeature> ethingFeatures = ethingThingAndFeatures.getEthingFeatures();
            for (Iterator<EthingFeature> iterator = ethingFeatures.iterator(); iterator.hasNext();) {
                EthingFeature next = iterator.next();
                next.setThingid(thing);
            }
            ethingFeatureRepository.save(ethingFeatures);
            //Set<EthingFeature> setEthingFeatures = new HashSet<EthingFeature>(ethingFeatures);
            //thing.setEthingFeatureSet(setEthingFeatures);
            return "message";
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/addThing", method = RequestMethod.PUT)
    public @ResponseBody
    String addThing(HttpServletRequest request, @RequestBody EthingThingAndFeatures ethingThingAndFeatures, Principal principal) {

        try {
            EthingThing newThing = ethingThingAndFeatures.getEthingThing();
            //not impl.
            newThing.setEthingThingimageSet(null);//not impl.
            int idhash = (newThing.getName() + newThing.getStatus() + newThing.getUrl()).hashCode();
            idhash = Math.abs(idhash);
            newThing.setIdhash(idhash + "");
            EthingThingtype thingType = ethingThingTypeRepository.findEthingThingtypeByName(newThing.getThingtype().getName());
            newThing.setThingtype(thingType);
            EthingUser thingUser = ethingUserRepository.findEthingUserByLoginAndActivation(principal.getName(), "1");
            newThing.setUserid(thingUser);
            EthingThing thing = ethingThingRepository.save(newThing);
            List<EthingFeature> ethingFeatures = ethingThingAndFeatures.getEthingFeatures();
            for (Iterator<EthingFeature> iterator = ethingFeatures.iterator(); iterator.hasNext();) {
                EthingFeature next = iterator.next();
                next.setThingid(thing);
            }
            ethingFeatureRepository.save(ethingFeatures);
            //Set<EthingFeature> setEthingFeatures = new HashSet<EthingFeature>(ethingFeatures);
            //thing.setEthingFeatureSet(setEthingFeatures);
            return "message";
        } catch (Exception e) {
            return "error";
        }
    }
}
