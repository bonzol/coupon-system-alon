package com.couponsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.entities.Customer;
import com.couponsystem.exception.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class AdminService extends ClientService {

	public AdminService() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	public void addCompany(Company company) throws CouponSystemException {

		if (companyR.existsByEmail(company.getEmail()) || companyR.existsByName(company.getName())) {
			System.out.println("Can't Add Company With The Same Name Or Email");
		} else {
			companyR.save(company);
		}

	}

	public void updateCompany(Company company) throws CouponSystemException {
//		Company temp = this.companyR.getByName(company.getName());
		Company temp = this.companyR.getById(company.getId());
		if (temp != null) {
			temp.setName(company.getName());
			temp.setEmail(company.getEmail());
			temp.setPassword(company.getPassword());
			temp.setCoupons(company.getCoupons());
		} else {
			System.out.println("Can't Update, Company Doesn't Exist");
		}

	}

	public void deleteCompany(int companyId) throws CouponSystemException {
		Company temp = this.companyR.getById(companyId);
		if (temp != null) {
			this.companyR.delete(temp);
		} else {
			System.out.println("Can't Delete, Company Doesn't Exist");
		}

	}

	public List<Company> getAllCompanies() throws CouponSystemException {
		return this.companyR.findAll();
	}

	public Company getOneCompany(int companyId) throws CouponSystemException {
		return this.companyR.findById(companyId);
	}

	public void addCustomer(Customer customer) throws CouponSystemException {
		if (!(this.customerR.existsByEmail((customer.getEmail())))) {
			this.customerR.save(customer);
		} else {
			System.out.println("Can't Add New Customer, Customer With The The Same Email Exist");
		}
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {
		Customer temp = this.customerR.getById(customer.getId());
		if (temp != null) {
			temp.setFirstName(customer.getFirstName());
			temp.setLastName(customer.getLastName());
			temp.setEmail(customer.getEmail());
			temp.setPassword(customer.getPassword());
			temp.setCoupons(customer.getCoupons());
		} else {
			System.out.println("Can't Update, Customer Doesn't Exist");
		}
	}

	public void deleteCustomer(int customerId) throws CouponSystemException {
		Customer temp = this.customerR.findById(customerId);
		if (temp != null) {
			this.customerR.delete(temp);
		} else {
			System.out.println("Can't Delete, Customer Doesn't Exist");
		}
	}

	public List<Customer> getAllCustomers() throws CouponSystemException {
		return this.customerR.findAll();
	}

	public Customer getOneCustomer(int customerId) throws CouponSystemException {
		Customer temp = this.customerR.findById(customerId);
		return temp;
	}

	public List<Coupon> getAllCoupons() throws CouponSystemException {
		return this.couponR.findAll();
	}

	public Optional<Coupon> getOneCoupon(int id) {
		return this.couponR.findById(id);
	}

	public void deleteCoupon(int id) {
		this.couponR.delete(this.couponR.getById(id));
	}

	public List<Coupon> getCouponsByCategory(Category category) throws CouponSystemException {
		List<Coupon> coupons = this.getAllCoupons();
		List<Coupon> temp = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if ((coupon.getCategory().equals(category))) {
				temp.add(coupon);
			}
		}
		return temp;
	}

	public List<Coupon> getCouponsBymaxPrice(double maxPrice) throws CouponSystemException {
		List<Coupon> coupons = this.getAllCoupons();
		List<Coupon> temp = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				temp.add(coupon);
			}
		}
		return temp;
	}

	public List<Coupon> getFilterCoupons(Category category, double maxPrice) throws CouponSystemException {

		List<Coupon> coupons = this.getAllCoupons();
		List<Coupon> tempCategory = this.getCouponsByCategory(category);
		List<Coupon> tempPrice = this.getCouponsBymaxPrice(maxPrice);

		coupons.retainAll(tempCategory);
		coupons.retainAll(tempPrice);
		return coupons;
	}

}
