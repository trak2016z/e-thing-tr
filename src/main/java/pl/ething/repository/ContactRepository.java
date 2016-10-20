package pl.ething.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.ething.model.Contact;
import pl.ething.model.Message;
import pl.ething.model.Person;


@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {


}
