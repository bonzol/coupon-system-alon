package com.couponsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.ClientService.ClientType;

@Service
@Transactional
@Scope("prototype")
public class LoginManager {

	@Autowired
	AdminService adminService;
	@Autowired
	CompanyService companyService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ApplicationContext ctx;

	public LoginManager() {
	}

	public int login(String email, String password, ClientType clientType) throws CouponSystemException {

		switch (clientType) {
		case ADMINISTRATOR: {
			if (adminService.login(email, password)) {
				System.out.println("----------------- Admin Logged -----------------");
				return 0;
			} else {
				throw new CouponSystemException("Login Failed");
			}
		}
		case COMPANY: {
			if (companyService.login(email, password)) {
				int id = this.companyService.getCompanyByEmail(email).getId();
				System.out.println("----------------- Company Id:" + id + " Logged -----------------");
				return id;
			} else {
				throw new CouponSystemException("Login Failed, Password Is Incorrect");
			}
		}
		case CUSTOMER: {
			if (customerService.login(email, password)) {
				int id = this.customerService.getCustomerByEmail(email).getId();
				System.out.println("----------------- Customer Id:" + id + " Logged -----------------");
				return id;
			} else {
				throw new CouponSystemException("Login Failed, Password Is Incorrect");
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected Value In Client Type: " + clientType);
		}
	}

}
