/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ething.model.EthingParameter;
import pl.ething.model.EthingThing;

/**
 *
 * @author Koksik
 */
@RepositoryRestResource(collectionResourceRel = "parameter", path = "parameter")
public interface EthingParameterRepository extends PagingAndSortingRepository<EthingParameter, Long> {
    
}
