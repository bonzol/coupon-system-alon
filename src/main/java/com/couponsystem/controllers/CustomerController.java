package com.couponsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.entities.Customer;
import com.couponsystem.entities.Review;
import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.CustomerService;
import com.couponsystem.services.JwtUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private CustomerService customerS;

	@Autowired
	JwtUtil jwtU;

	@Autowired
	public CustomerController(CustomerService customerS) {
		super();
		this.customerS = customerS;
	}

	@GetMapping(path = "/getAllCustomerCoupon")
	public List<Coupon> getAllCoupons(@RequestHeader String token) {
		int id = getId(token);
		try {
			return customerS.getCustomerCoupons(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCoupons")
	public List<Coupon> getAllCustomerCoupons(@RequestHeader String token) {
		int id = getId(token);
		try {
			return customerS.getAllCoupons(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCustomerCouponCategory")
	public List<Coupon> getAllCouponsByCategory(@RequestParam Category category, @RequestHeader String token) {
		int id = getId(token);
		try {
			return customerS.getCustomerCouponsByCategory(category, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCustomerCouponMaxPrice")
	public List<Coupon> getAllCouponsMaxPrice(@RequestParam double maxPrice, @RequestHeader String token) {
		int id = getId(token);
		try {
			return customerS.getCustomerCouponsBymaxPrice(maxPrice, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getCust")
	public Customer getCustomer(@RequestHeader String token) {
		int id = getId(token);
		try {
			return customerS.getCustomerDetails(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(path = "/purchaseCoupon")
	public void PurchaseCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		int id = getId(token);
		try {
			customerS.purchaseCoupon(coupon, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
		}
	}

	@PostMapping(path = "/addreview")
	public void addReview(@RequestBody Review review, @RequestParam int couponId, @RequestHeader String token) {
		int customerId = this.getId(token);
		try {
			customerS.addReview(customerId, review, couponId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
		}
	}

	public int getId(String token) {
		return jwtU.extractId(token);
	}

}
