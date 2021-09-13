package com.couponsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.couponsystem.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Query(value = "SELECT * FROM review c WHERE c.coupon_id = :couponId", nativeQuery = true)
	List<Review> findByCouponId(int couponId);

}
