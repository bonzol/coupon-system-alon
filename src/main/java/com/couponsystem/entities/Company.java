package com.couponsystem.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "company")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String password;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Coupon> coupons;

	public void addCoupon(Coupon coupon) {
		if (this.coupons == null) {
			this.coupons = new ArrayList<Coupon>();
		}
		coupon.setCompany(this);
		this.coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	public List<Coupon> getCoupons() {
		return this.coupons;
	}
}
