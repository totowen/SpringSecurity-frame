/**
 * 
 */
package com.demo.bolian.security.rbac.repository.spec;


import com.demo.bolian.security.rbac.domain.Admin;
import com.demo.bolian.security.rbac.dto.AdminCondition;
import com.demo.bolian.security.rbac.repository.support.ImoocSpecification;
import com.demo.bolian.security.rbac.repository.support.QueryWraper;

/**
 *
 */
public class AdminSpec extends ImoocSpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
