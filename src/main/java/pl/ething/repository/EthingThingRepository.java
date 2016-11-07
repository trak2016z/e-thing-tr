/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ething.model.EthingThing;
import pl.ething.model.EthingUser;

/**
 *
 * @author Koksik
 */
@RepositoryRestResource(collectionResourceRel = "thing", path = "thing")
public interface EthingThingRepository extends PagingAndSortingRepository<EthingThing, Long> {
    
}
