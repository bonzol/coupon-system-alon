package com.couponsystem.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.AdminService;

@CrossOrigin
@RestController("*")
@RequestMapping("/api/admin")
public class AdminController {

	private AdminService adminS;

	@Autowired
	public AdminController(AdminService adminS) {
		super();
		this.adminS = adminS;
	}

	@GetMapping(path = "/getAllComp")
	public ArrayList<Company> getAllCompanies(@RequestHeader String token) {
		try {
			return (ArrayList<Company>) adminS.getAllCompanies();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCoup")
	public ArrayList<Coupon> getAllCoupons(@RequestHeader String token) {
		try {
			return (ArrayList<Coupon>) adminS.getAllCoupons();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCust")
	public ArrayList<Customer> getAllCustomers(@RequestHeader String token) {
		try {
			return (ArrayList<Customer>) adminS.getAllCustomers();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getOneComp")
	public Company getOneCompany(@RequestParam int id, @RequestHeader String token) {
		try {
			return adminS.getOneCompany(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getOneCust")
	public Company getOneCustomer(@RequestParam int id, @RequestHeader String token) {
		try {
			return adminS.getOneCompany(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(path = "/addComp")
	public void addCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
			adminS.addCompany(company);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(path = "/addCust")
	public void addCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			adminS.addCustomer(customer);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping(path = "/updateComp")
	public void updateCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
			adminS.updateCompany(company);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping(path = "/updateCust")
	public void updateCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			adminS.updateCustomer(customer);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping(path = "/deleteCopm")
	public void deleteCompany(@RequestParam int companyId, @RequestHeader String token) {
		try {
			adminS.deleteCompany(companyId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping(path = "/deleteCust")
	public void deleteCustomer(@RequestParam int customerId, @RequestHeader String token) {
		try {
			adminS.deleteCustomer(customerId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
