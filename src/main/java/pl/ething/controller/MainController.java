package pl.ething.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ething.model.EthingFeature;
import pl.ething.model.EthingThing;
import pl.ething.model.EthingThingtype;
import pl.ething.model.EthingUser;
import pl.ething.repository.EthingThingRepository;
import pl.ething.repository.EthingThingTypeRepository;
import pl.ething.repository.EthingUserRepository;

/**
 *
 * @author prographer
 */
@org.springframework.stereotype.Controller
public class MainController {

    static final String PROFIL_HTML = "/profil/";
    static final String REGISTER_HTML = "/register";
    static final String LOGIN_HTML = "/login";
    static final String REMEMBERME_HTML = "/rememberme";
    static final String THINGADD_HTML = "/thingadd";
    static final String USERTHINGS_HTML = "/things/";
    static final String THING_HTML = "/thing/";
    static final String LOGOUT_HTML = "/logout";
    static final String USERSETTINGS_HTML = "/profilsettings";
    static final String THINGEDIT_HTML = "/thingedit/";
    static final String THINGDELETE_HTML = "/deleteThing/";
    @Autowired
    EthingUserRepository ethingUserRepository;
    @Autowired
    EthingThingRepository ethingThingRepository;
    @Autowired
    EthingThingTypeRepository ethingThingTypeRepository;

    @RequestMapping("/")
    public String homePage(HttpServletRequest request, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
            model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
            model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
        }
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        return "home";
    }

    @RequestMapping(value = "/things/{name}", method = RequestMethod.GET)
    public String userThingsPage(HttpServletRequest request, @PathVariable("name") String userName, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));

        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            Set<EthingThing> userThings = user.getEthingThingSet();
            List<EthingThing> listUserThings = new ArrayList<EthingThing>(userThings);
            for (Iterator<EthingThing> iterator = listUserThings.iterator(); iterator.hasNext();) {
                EthingThing next = iterator.next();
                next.setIdhash(mainPage + THING_HTML + next.getIdhash());
            }
            model.addAttribute("userThings", listUserThings);
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
            model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
            model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
        }
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);

        return "userthings";
    }

    @RequestMapping(value = "/search/{text}", method = RequestMethod.GET)
    public String mainSearchPage(HttpServletRequest request, @PathVariable("text") String text, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);

        return "mainsearch";
    }

    @RequestMapping(value = "/profil/{name}", method = RequestMethod.GET)
    public String profilPage(HttpServletRequest request, @PathVariable("name") String userName, Model model, Principal principal) {
        EthingUser profilUser = ethingUserRepository.findEthingUserByNameAndActivation(userName, "1");
        if (profilUser == null) {
            return "error";
        } else {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));

            if (principal == null) {
                model.addAttribute("loginPage", mainPage + LOGIN_HTML);
                model.addAttribute("loginPageText", "Log-in");
                model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
            } else {
                EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
                model.addAttribute("loginPageText", user.getName());
                model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
                model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
                model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
                model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
            }
            model.addAttribute("mainPage", mainPage);
            model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
            model.addAttribute("registerPage", mainPage + REGISTER_HTML);
            model.addAttribute("user", profilUser);
            return "profil";
        }
    }

    @RequestMapping(value = "/profilsettings", method = RequestMethod.GET)
    public String profilEditPage(HttpServletRequest request, Model model, Principal principal) {

        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));

        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            user.setEthingThingSet(null);
            user.setId(Long.MIN_VALUE);
            user.setPassword("");
            user.setRole("");
            model.addAttribute("user", user);
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
            model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
            model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
        }
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);

        return "profilsettings";

    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("rememberMePage", mainPage + REMEMBERME_HTML);
        return "login";
    }

    @RequestMapping("/rememberme")
    public String remembermePage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("loginPage", mainPage + LOGIN_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("rememberMePage", mainPage + REMEMBERME_HTML);
        return "rememberme";
    }

    @RequestMapping("/register")
    public String registerPage(HttpServletRequest request, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));

        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
            model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
            model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
        }
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("mainPage", mainPage);
        return "register";
    }

    @RequestMapping("/thingadd")
    public String thingaddPage(HttpServletRequest request, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
            model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
            model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
        }
        model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("mainPage", mainPage);
        List<EthingThingtype> thingType = ethingThingTypeRepository.findAll();
        model.addAttribute("thingtype", thingType);
        return "thingadd";
    }

    @RequestMapping("/thingedit/{thinghashId}")
    public String thingeditPage(HttpServletRequest request, @PathVariable("thinghashId") String thingHashId, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));

        model.addAttribute("mainPage", mainPage);
        List<EthingThingtype> thingType = ethingThingTypeRepository.findAll();
        model.addAttribute("thingtype", thingType);
        EthingThing thing = ethingThingRepository.findEthingThingByIdhash(thingHashId);
        model.addAttribute("thing", thing);
        Set<EthingFeature> features = thing.getEthingFeatureSet();
        model.addAttribute("features", features);
        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
            model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
            model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
        }
        model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("mainPage", mainPage);
        return "thingedit";
    }

    @RequestMapping(value = "/thing/{thinghashId}", method = RequestMethod.GET)
    public String thingPage(HttpServletRequest request, @PathVariable("thinghashId") String thingHashId, Model model, Principal principal) {
        if (!"".equals(thingHashId)) {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));

            model.addAttribute("mainPage", mainPage);
            EthingThing thing = ethingThingRepository.findEthingThingByIdhash(thingHashId);
            model.addAttribute("thingaddPage", mainPage + THINGADD_HTML);
            model.addAttribute("thing", thing);
            Set<EthingFeature> features = thing.getEthingFeatureSet();
            model.addAttribute("features", features);
            if (principal == null) {
                model.addAttribute("loginPage", mainPage + LOGIN_HTML);
                model.addAttribute("loginPageText", "Log-in");
                model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML);

            } else {
                EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
                model.addAttribute("loginPageText", user.getName());
                model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
                model.addAttribute("userthingPage", mainPage + USERTHINGS_HTML + user.getName());
                model.addAttribute("logoutPage", mainPage + LOGOUT_HTML);
                model.addAttribute("usersettingsPage", mainPage + USERSETTINGS_HTML);
                model.addAttribute("thingaeditPage", mainPage + THINGEDIT_HTML + thing.getIdhash());
                model.addAttribute("thingdeletePage", mainPage + THINGDELETE_HTML + thing.getIdhash());

            }
            model.addAttribute("registerPage", mainPage + REGISTER_HTML);
            return "thing";
        } else {
            return "error";
        }

    }

    @RequestMapping(value = "/activation/{activationId}", method = RequestMethod.GET)
    public String activationUser(HttpServletRequest request, @PathVariable("activationId") String hashId, Model model) {
        if (!"".equals(hashId)) {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));
            model.addAttribute("mainPage", mainPage);
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("registerPage", mainPage + REGISTER_HTML);
            EthingUser user = ethingUserRepository.findEthingUserByActivation(hashId);
            if (user != null) {
                user.setActivation("1");
                ethingUserRepository.save(user);
                return "activation";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

}
