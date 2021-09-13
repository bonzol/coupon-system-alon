package com.couponsystem.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.entities.Customer;
import com.couponsystem.entities.Review;
import com.couponsystem.entities.UserDetails;
import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.AdminService;
import com.couponsystem.services.ClientService.ClientType;
import com.couponsystem.services.CustomerService;
import com.couponsystem.services.JwtUtil;
import com.couponsystem.services.LoginManager;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private LoginManager loginM;

	@Autowired
	private AdminService adminS;

	@Autowired
	private CustomerService customerS;

	@PostMapping
	public String login(@RequestParam String userEmail, @RequestParam ClientType userType,
			@RequestParam String userPassword) {
		int userId;
		try {
			userId = loginM.login(userEmail, userPassword, userType);
			switch (userType) {
			case ADMINISTRATOR:
				String userName = "Admin";
				UserDetails user = new UserDetails(userId, userEmail, userPassword, userType, userName);
				String token = jwtUtil.generateToken(user);
				System.out.println(token);
				return token;
			case COMPANY:
				String userName2 = this.adminS.getOneCompany(userId).getName();
				UserDetails user2 = new UserDetails(userId, userEmail, userPassword, userType, userName2);
				String token2 = jwtUtil.generateToken(user2);
				System.out.println(token2);
				return token2;
			case CUSTOMER:
				String userName3 = this.adminS.getOneCustomer(userId).getFirstName();
				UserDetails user3 = new UserDetails(userId, userEmail, userPassword, userType, userName3);
				String token3 = jwtUtil.generateToken(user3);
				System.out.println(token3);
				return token3;
			default:
				return null;
			}
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}

	}

	@GetMapping(path = "/getAllCoup")
	public ArrayList<Coupon> getAllCoupons() {
		try {
			return (ArrayList<Coupon>) adminS.getAllCoupons();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllFilterCoup")
	public ArrayList<Coupon> getAllFilterCoupons(@RequestParam Category category, @RequestParam double maxPrice) {
		try {
			return (ArrayList<Coupon>) adminS.getFilterCoupons(category, maxPrice);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllFilterCoupPrice")
	public ArrayList<Coupon> getAllFilterCouponsPrice(@RequestParam double maxPrice) {
		try {
			return (ArrayList<Coupon>) adminS.getCouponsBymaxPrice(maxPrice);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getAllFilterCoupCate")
	public ArrayList<Coupon> getAllFilterCouponsCategory(@RequestParam Category category) {
		try {
			return (ArrayList<Coupon>) adminS.getCouponsByCategory(category);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/getOneCoup")
	public Optional<Coupon> getOneCoupon(@RequestParam int id) {
		return adminS.getOneCoupon(id);
	}

	@GetMapping(path = "/getAllReviews")
	public ArrayList<Review> getAllReviews(@RequestParam int couponId) {
		try {
			return (ArrayList<Review>) customerS.getCouponReviews(couponId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(path = "/addCust")
	public void addCustomer(@RequestBody Customer customer) {
		try {
			adminS.addCustomer(customer);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
