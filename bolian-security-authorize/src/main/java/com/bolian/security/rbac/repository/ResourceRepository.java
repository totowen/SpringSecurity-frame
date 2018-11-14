/**
 * 
 */
package com.bolian.security.rbac.repository;

import com.bolian.security.rbac.domain.Resource;
import org.springframework.stereotype.Repository;


/**
 *
 */
@Repository
public interface ResourceRepository extends ImoocRepository<Resource> {

	Resource findByName(String name);

}
