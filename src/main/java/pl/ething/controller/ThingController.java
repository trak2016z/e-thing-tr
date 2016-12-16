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
    
 
    @RequestMapping(value = "/deleteThing/{thinghashId}", method = RequestMethod.GET)
    public @ResponseBody
    String deleteThing(HttpServletRequest request, @RequestBody String idhash, Principal principal) {
        try {     
            EthingThing thing = ethingThingRepository.findEthingThingByIdhash(idhash);
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            if(thing.getUserid().getId()==user.getId())
            {
                Set<EthingFeature> features = thing.getEthingFeatureSet();
                for (Iterator<EthingFeature> iterator = features.iterator(); iterator.hasNext();) {
                    EthingFeature next = iterator.next();
                    ethingFeatureRepository.delete(next);
                }
                ethingThingRepository.delete(thing);
            }
            else 
                return "error";
            return "message";
        } catch (Exception e) {
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
            return "error";
        }
    }
}
