/**
 * 
 */
package com.bolian.security.rbac.repository.spec;


import com.bolian.security.rbac.dto.AdminCondition;
import com.bolian.security.rbac.repository.support.ImoocSpecification;
import com.bolian.security.rbac.repository.support.QueryWraper;
import com.bolian.security.rbac.domain.Admin;

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
