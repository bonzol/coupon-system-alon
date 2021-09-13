package com.couponsystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.exception.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class CompanyService extends ClientService {

	public CompanyService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (this.companyR.getByEmail(email) != null) {
			if (this.companyR.getByEmail(email).getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new CouponSystemException("Email Doesn't exist");
		}
	}

	public void addCoupon(Coupon coupon, int id) throws CouponSystemException {
		if (this.couponR.existsByTitle(coupon.getTitle())) {
			throw new CouponSystemException("Can't add Coupon With The Same Title");
		}
		coupon.setCompany(this.getCompanyDetails(id));
		this.couponR.save(coupon);
	}

	public void updateCoupon(Coupon coupon, int id) throws CouponSystemException {
		if (this.couponR.existsById(coupon.getId())
				&& this.couponR.getById(coupon.getId()).getCompany().getId() == id) {
			Coupon temp = this.couponR.getById(coupon.getId());
			temp.setAmount(coupon.getAmount());
			temp.setCategory(coupon.getCategory());
			temp.setDescription(coupon.getDescription());
			temp.setEndDate(coupon.getEndDate());
			temp.setImage(coupon.getImage());
			temp.setPrice(coupon.getPrice());
			temp.setStartDate(coupon.getStartDate());
			temp.setTitle(coupon.getTitle());
		} else {
			throw new CouponSystemException("Can't Update Coupon of diffrent Company");
		}
	}

	public void deleteCoupon(Integer couponId, int id) throws CouponSystemException {
		if (this.couponR.existsById(couponId) && this.couponR.getById(couponId).getCompany().getId() == id) {
			couponR.deleteById(couponId);
		} else {
			throw new CouponSystemException("Can't Delete, Sorry...");
		}
	}

	public List<Coupon> getCompanyCoupons(int id) throws CouponSystemException {
		if (this.couponR.findByCompanyId(id) != null) {
			return this.couponR.findByCompanyId(id);
		} else {
			throw new CouponSystemException("No Coupons, Sorry..");
		}
	}

	public List<Coupon> getCompanyCouponsByCategory(Category category, int id) throws CouponSystemException {
		List<Coupon> coupons = new ArrayList<Coupon>();
		List<Coupon> temp = this.getCompanyCoupons(id);
		for (Coupon coupon : temp) {
			if (coupon.getCategory().equals(category)) {
				coupons.add(coupon);
			}
		}
		return coupons;
	}

	public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice, int id) throws CouponSystemException {
		List<Coupon> coupons = new ArrayList<Coupon>();
		List<Coupon> temp = this.getCompanyCoupons(id);
		for (Coupon coupon : temp) {
			if (coupon.getPrice() <= maxPrice) {
				coupons.add(coupon);
			}
		}
		return coupons;
	}

	public Company getCompanyDetails(int id) throws CouponSystemException {
		if (this.companyR.findById(id) != null) {
			return this.companyR.findById(id);
		} else {
			throw new CouponSystemException("Company Doesn't Exist");
		}
	}

	public Company getCompanyByEmail(String email) throws CouponSystemException {
		if (this.companyR.getByEmail(email) != null) {
			return this.companyR.getByEmail(email);
		} else {
			throw new CouponSystemException("Company Doesn't Exist");
		}
	}

}
