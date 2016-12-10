/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ething.model.EthingThingtype;

/**
 *
 * @author Koksik
 */

@RepositoryRestResource(collectionResourceRel = "thingtype", path = "thingtype", exported = false)
public interface EthingThingTypeRepository extends PagingAndSortingRepository<EthingThingtype, Long> {
    
     List<EthingThingtype> findAll();
     EthingThingtype findEthingThingtypeByName(String name);
}