/**
 * 
 */
package com.demo.bolian.security.rbac.repository;

import com.demo.bolian.security.rbac.domain.Admin;
import org.springframework.stereotype.Repository;


/**
 *
 */
@Repository
public interface AdminRepository extends ImoocRepository<Admin> {

	Admin findByUsername(String username);

}