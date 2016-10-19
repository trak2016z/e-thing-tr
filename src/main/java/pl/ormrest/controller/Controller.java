package pl.ormrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.ormrest.model.Person;
import pl.ormrest.repository.PersonRepository;


@RestController
public class Controller {

	@Autowired
	PersonRepository personRepository; 
    @RequestMapping( method=RequestMethod.GET, value="/getPerson")
    public Person getPerson()  {
    	
    	return  new Person("aaa", "aaa");
    }
}