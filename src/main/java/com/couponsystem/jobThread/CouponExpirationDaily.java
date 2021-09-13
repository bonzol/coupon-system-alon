package com.couponsystem.jobThread;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.couponsystem.entities.Coupon;
import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.AdminService;

@Component
public class CouponExpirationDaily implements Runnable {

	@Autowired
	protected AdminService adminService;
	private static final int time = 86400000;
	private boolean quit = true;

	public CouponExpirationDaily(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@Override
	public void run() {
		while (quit) {
			System.out.println("Job Thread Awake");
			List<Coupon> coupons;
			try {
				coupons = this.adminService.getAllCoupons();
				for (Coupon coupon : coupons) {
					if (LocalDate.now().isAfter(coupon.getEndDate())) {
						this.adminService.deleteCoupon(coupon.getId());
					}
				}
			} catch (CouponSystemException e1) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e1.getMessage());

			}

			System.out.println("Job Thread Sleep");
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

				break;
			}
			System.out.println("Job Thread Awake");
		}
	}

}
