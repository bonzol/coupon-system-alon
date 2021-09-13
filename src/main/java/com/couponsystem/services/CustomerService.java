package com.couponsystem.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.entities.Customer;
import com.couponsystem.entities.Review;
import com.couponsystem.exception.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {

	public CustomerService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (this.customerR.getByEmail(email) != null) {
			if (this.customerR.getByEmail(email).getPassword().equals(password)) {
				return true;
			} else {
				throw new CouponSystemException("Password is Incorrect");
			}
		} else {
			throw new CouponSystemException("Email Doesn't exist");
		}
	}

	public List<Coupon> getCustomerCoupons(int id) throws CouponSystemException {
		if (this.customerR.getById(id).getCoupons() != null) {
			return this.customerR.getById(id).getCoupons();
		} else {
			throw new CouponSystemException("No Coupons, Sorry");
		}
	}

	public List<Coupon> getAllCoupons(int id) throws CouponSystemException {
		List<Coupon> coupons = this.couponR.findAll();
		if (this.customerR.getById(id).getCoupons() != null) {
			List<Coupon> temp = this.customerR.getById(id).getCoupons();
			coupons.removeAll(temp);
			return coupons;
		} else {
			throw new CouponSystemException("No Coupons");
		}
	}

	public void purchaseCoupon(Coupon coupon, int id) throws CouponSystemException {
		List<Coupon> temp = this.getCustomerCoupons(id);
		Coupon tempCoupon = this.couponR.getById(coupon.getId());
		if (temp.contains(tempCoupon)) {
			throw new CouponSystemException("Can't Purchase The Same Coupon Twice");
		}
		if (tempCoupon.getAmount() <= 0) {
			throw new CouponSystemException("Can't Purchase Coupon Because The Amount Of The Coupon Is 0");
		}
		if (LocalDate.now().isAfter(tempCoupon.getEndDate())) {
			throw new CouponSystemException("Can't Purchase The Coupon Because The Coupon Is Out of Date");
		}
		this.customerR.getById(id).addCoupon(tempCoupon);
		tempCoupon.setAmount(tempCoupon.getAmount() - 1);
	}

	public void purchaseCoupon2(int couponId, int customerId) throws CouponSystemException {
		List<Coupon> temp = this.getCustomerCoupons(customerId);
		Coupon tempCoupon = this.couponR.getById(couponId);
		if (temp.contains(tempCoupon)) {
			throw new CouponSystemException("Can't Purchase The Same Coupon Twice");

		}
		if (tempCoupon.getAmount() <= 0) {
			System.out.println("Can't Purchase Coupon Because The Amount Of The Coupon Is 0");
			return;
		}
		if (LocalDate.now().isAfter(tempCoupon.getEndDate())) {
			System.out.println("Can't Purchase The Coupon Because The Coupon Is Out of Date");
			return;
		}
		this.customerR.getById(customerId).addCoupon(tempCoupon);
		tempCoupon.setAmount(tempCoupon.getAmount() - 1);
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category, int id) throws CouponSystemException {
		List<Coupon> coupons = this.getCustomerCoupons(id);
		List<Coupon> temp = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if ((coupon.getCategory().equals(category))) {
				temp.add(coupon);
			}
		}
		return temp;
	}

	public List<Coupon> getCustomerCouponsBymaxPrice(double maxPrice, int id) throws CouponSystemException {
		List<Coupon> coupons = this.getCustomerCoupons(id);
		List<Coupon> temp = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				temp.add(coupon);
			}
		}
		return temp;
	}

	public Customer getCustomerDetails(int id) throws CouponSystemException {
		if (this.customerR.findById(id) != null) {
			return this.customerR.findById(id);
		} else {
			throw new CouponSystemException("Customer Doesn't exist");
		}
	}

	public Customer getCustomerByEmail(String email) throws CouponSystemException {
		if (this.customerR.getByEmail(email) != null) {
			return this.customerR.getByEmail(email);
		} else {
			throw new CouponSystemException("Customer Doesn't exist");
		}
	}

	public int addReview(int customerId, Review review, int couponId) throws CouponSystemException {
		review.setCustomer(this.getCustomerDetails(customerId));
		review.setCoupon(this.couponR.getById(couponId));
		String name = this.getCustomerDetails(customerId).getFirstName() + " "
				+ this.getCustomerDetails(customerId).getLastName();
		review.setName(name);
		this.reviewR.save(review);
		this.couponR.getById(couponId).setScore(this.getAvgScore(couponId));
		return review.getId();
	}

	public List<Review> getCouponReviews(int couponId) throws CouponSystemException {
		if (this.reviewR.findByCouponId(couponId) != null) {
			return this.reviewR.findByCouponId(couponId);
		} else {
			throw new CouponSystemException("Customer Doesn't exist");
		}
	}

	public Double getAvgScore(int couponId) {
		List<Review> temp = this.reviewR.findByCouponId(couponId);
		double avg;
		if (temp != null) {
			double sum = 0;
			for (Review review : temp) {
				sum += review.getScore();
			}
			avg = (sum / temp.size());
			DecimalFormat df = new DecimalFormat("#.#");
			return Double.parseDouble(df.format(avg));
		} else {
			return null;
		}
	}
}
