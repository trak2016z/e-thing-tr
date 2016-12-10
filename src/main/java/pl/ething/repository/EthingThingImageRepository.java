/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ething.model.EthingThing;
import pl.ething.model.EthingThingimage;

/**
 *
 * @author Koksik
 */
@RepositoryRestResource(collectionResourceRel = "thingimage", path = "thingimage", exported = false)
public interface EthingThingImageRepository extends PagingAndSortingRepository<EthingThingimage, Long> {
    
}
