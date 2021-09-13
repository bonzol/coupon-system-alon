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
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.CompanyService;
import com.couponsystem.services.JwtUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

	private CompanyService companyS;

	@Autowired
	JwtUtil jwtU;

	@Autowired
	public CompanyController(CompanyService companyS) {
		super();
		this.companyS = companyS;
	}

	@PostMapping(path = "/addCoupon")
	public void addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		int id = getId(token);
		try {
			companyS.addCoupon(coupon, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
		}
	}

	@PutMapping(path = "/updateCoupon")
	public void updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		int id = getId(token);
		try {
			companyS.updateCoupon(coupon, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping(path = "/deleteCoupon")
	public void deleteCoupon(@RequestParam int couponId, @RequestHeader String token) {
		int id = getId(token);
		try {
			companyS.deleteCoupon(couponId, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCompanyCoupon")
	public ArrayList<Coupon> getAllCoupons(@RequestHeader String token) {
		int id = getId(token);
		try {
			return (ArrayList<Coupon>) companyS.getCompanyCoupons(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCompanyCouponCategory")
	public ArrayList<Coupon> getAllCouponsCategory(@RequestParam Category category, @RequestHeader String token) {
		int id = getId(token);
		try {
			return (ArrayList<Coupon>) companyS.getCompanyCouponsByCategory(category, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllCompanyCouponMaxPrice")
	public ArrayList<Coupon> getAllCouponsMaxPrice(@RequestParam double maxPrice, @RequestHeader String token) {
		int id = getId(token);
		try {
			return (ArrayList<Coupon>) companyS.getCompanyCouponsByMaxPrice(maxPrice, id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getComp")
	public Company getCompany(@RequestHeader String token) {
		int id = getId(token);
		try {
			return companyS.getCompanyDetails(id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public int getId(String token) {
		return jwtU.extractId(token);
	}

}
