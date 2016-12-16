/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ething.model.EthingThing;
import pl.ething.model.EthingUser;

/**
 *
 * @author Koksik
 */
@RepositoryRestResource(collectionResourceRel = "thing", path = "thing", exported = false)
public interface EthingThingRepository extends PagingAndSortingRepository<EthingThing, Long> {

    EthingThing findEthingThingByIdhash(String idhash);
    List<EthingThing> findEthingThingByAccess(String access);
    List<EthingThing> findEthingThingByUseridAndAccess(EthingUser ethingUser, String access);
}
