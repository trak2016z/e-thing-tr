/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ething.model.EthingUser;

/**
 *
 * @author Koksik
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user", exported = false)
public interface EthingUserRepository extends PagingAndSortingRepository<EthingUser, Long> {
    
    EthingUser findEthingUserByName(String name);
    EthingUser findEthingUserByActivation(String activation);
    EthingUser findEthingUserByNameAndActivation(String name,String activation);
    EthingUser findEthingUserByEmailAndActivation(String email,String activation);
}
