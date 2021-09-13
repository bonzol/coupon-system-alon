package com.couponsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.couponsystem.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	boolean existsByTitle(String title);

	@Query(value = "SELECT * FROM coupon c WHERE c.company_id = :companyId", nativeQuery = true)
	List<Coupon> findByCompanyId(int companyId);

}
