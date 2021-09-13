package com.couponsystem.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer amount;
	private Double price;
	private String image;
	private Double score;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "customers_vs_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@JsonIgnore
	private List<Customer> customers;

	@OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews;

	public enum Category {

		FOOD, ELECTRICITY, RESTAURANT, VACTION;

	}

	public void addReview(Review review) {
		if (this.reviews == null) {
			this.reviews = new ArrayList<Review>();
		}
		this.reviews.add(review);
	}

	public Coupon(Category category, String title, String description, LocalDate startDate, LocalDate endDate,
			Integer amount, Double price, String image) {
		super();
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon(int id, Category category, String title, String description, LocalDate startDate, LocalDate endDate,
			Integer amount, Double price, String image) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", category=" + category + ", title=" + title + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", price=" + price
				+ ", image=" + image + "]";
	}

}
