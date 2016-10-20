package pl.ething.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.ething.model.Person;
import pl.ething.model.Status;


@RepositoryRestResource(collectionResourceRel = "status", path = "status")
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {


}
