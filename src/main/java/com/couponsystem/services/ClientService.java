package com.couponsystem.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.repositories.CompanyRepository;
import com.couponsystem.repositories.CouponRepository;
import com.couponsystem.repositories.CustomerRepository;
import com.couponsystem.repositories.ReviewRepository;

@Service
@Transactional
@Scope("prototype")
public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyR;
	@Autowired
	protected CustomerRepository customerR;
	@Autowired
	protected CouponRepository couponR;
	@Autowired
	protected ReviewRepository reviewR;

	public enum ClientType {

		ADMINISTRATOR, COMPANY, CUSTOMER;

	}

	public ClientService() {
		super();
	}

	public abstract boolean login(String email, String password) throws CouponSystemException;

}
