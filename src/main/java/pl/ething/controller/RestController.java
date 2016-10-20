package pl.ething.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.ething.model.Person;
import pl.ething.repository.PersonRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/getPerson")
    public Person getPerson() {

        return new Person("aaa", "aaa");
    }
}
