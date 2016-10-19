package pl.ormrest.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.ormrest.model.Contact;
import pl.ormrest.model.Message;
import pl.ormrest.model.Person;


@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {


}
