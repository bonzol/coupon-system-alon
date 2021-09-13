package com.couponsystem;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Coupon.Category;
import com.couponsystem.entities.Customer;
import com.couponsystem.entities.Review;
import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.jobThread.CouponExpirationDaily;
import com.couponsystem.services.AdminService;
import com.couponsystem.services.CompanyService;
import com.couponsystem.services.CustomerService;

@Component
public class Test {

	public void addAll(ApplicationContext ctx) throws CouponSystemException {

		CouponExpirationDaily r = new CouponExpirationDaily(ctx.getBean(AdminService.class));
		Thread t1 = new Thread(r);
		try {
			AdminService adminS = ctx.getBean(AdminService.class);

			adminS.addCompany(new Company(0, "SuperAvi", "avi@gmail.com", "milkiispricy"));
			adminS.addCompany(new Company(0, "NoaMarket", "ng@gmail.com", "ngisthequeen"));
			adminS.addCompany(new Company(0, "Grape", "grape@gmail.com", "wehateapple"));
			adminS.addCompany(new Company(0, "NoneSung", "ns@gmail.com", "dontbuy"));
			adminS.addCompany(new Company(0, "DifficultTour", "dftour@gmail.com", "flywithus"));
			adminS.addCompany(new Company(0, "FlySweet", "fsweet@gmail.com", "goodflight"));
			adminS.addCompany(new Company(0, "TheCuCumber", "tomato@gmail.com", "meatisnotgood"));
			adminS.addCompany(new Company(0, "The Indian", "hotfood@gmail.com", "saltysweet"));
			adminS.addCustomer(new Customer(0, "Ehud", "Barak", "idf@gmail.com", "iloveehud"));
			adminS.addCustomer(new Customer(0, "Shimi", "Peretz", "isr@gmail.com", "iloveisr"));
			adminS.addCustomer(new Customer(0, "Miri", "Regev", "mr@gmail.com", "ilovebibi"));
			adminS.addCustomer(new Customer(0, "Bibi", "Net", "pm@gmail.com", "ihatebenet"));
			adminS.addCustomer(new Customer(0, "Golda", "Meir", "gm@gmail.com", "iwasfirst"));
			adminS.addCustomer(new Customer(0, "Yair", "Lapid", "yl@gmail.com", "iminthecenter"));
			adminS.addCustomer(new Customer(0, "Menahem", "Begin", "mb@gmail.com", "icanttakeit"));

			CompanyService companyS = ctx.getBean(CompanyService.class);

			Coupon coupon1 = new Coupon(Category.FOOD, "Pizza Margherita",
					"Only at Super Avi, You can Get a 30% Discount On the Best Pizza Margherita",
					LocalDate.of(2021, 4, 12), LocalDate.of(2022, 5, 16), 100, (double) 20, "pizza.jpg");
			Coupon coupon2 = new Coupon(Category.FOOD, "White Bread",
					"Only at Super Avi, You can Get a 70% Discount On the Best Pizza Margherita",
					LocalDate.of(2019, 5, 7), LocalDate.of(2022, 2, 17), 50, (double) 10, "bread.jpg");
			Coupon coupon3 = new Coupon(Category.FOOD, "Ice Cream",
					"NoaMarket presents: The Best Ice Cream In the World, 25% Discount!", LocalDate.of(2020, 2, 14),
					LocalDate.of(2022, 4, 22), 120, (double) 40, "icecream.jpg");
			Coupon coupon4 = new Coupon(Category.FOOD, "Udon Noodles",
					"NoaMarket is Proud To Present: Udon Noodels that Will Blow Your Mind, All The Way To The Far East - 30% Discount",
					LocalDate.of(2020, 2, 14), LocalDate.of(2021, 11, 12), 10, (double) 8, "udon.jpg");
			Coupon coupon5 = new Coupon(Category.FOOD, "Coffee Beans",
					"NoaMarket Will Drive You Crazy With Their New, Improved, Beautiful, Aromatic, HandMaid (With Love) Coffee Beans!",
					LocalDate.of(2020, 5, 23), LocalDate.of(2022, 10, 17), 55, (double) 30, "coffee.jpg");
			Coupon coupon6 = new Coupon(Category.ELECTRICITY, "Mango Phone",
					"Grape Shop Revels Their Best Seller, The Mango Phone! It Will Make Other Phones Jealous",
					LocalDate.of(2019, 2, 14), LocalDate.of(2022, 12, 4), 36, (double) 88, "mango.jpg");
			Coupon coupon7 = new Coupon(Category.ELECTRICITY, "Lemon 5001",
					"Grape Shop - The Future Is Now - The Lemon Computer! 5001!", LocalDate.of(2020, 7, 18),
					LocalDate.of(2022, 8, 21), 23, (double) 137, "comp.jpg");
			Coupon coupon8 = new Coupon(Category.ELECTRICITY, "B&W TV",
					"NoneSung Returns To Better Day, a Black & White TV, Because Progress Is Bad",
					LocalDate.of(2025, 7, 23), LocalDate.of(2022, 3, 12), 23, (double) 137, "btv.jpg");
			Coupon coupon9 = new Coupon(Category.ELECTRICITY, "Colored TV",
					"NoneSung Will Take You To Places You Have Never Been With Colored TV", LocalDate.of(2021, 7, 12),
					LocalDate.of(2023, 8, 7), 203, (double) 200, "ctv.jpg");
			Coupon coupon10 = new Coupon(Category.VACTION, "Volcano Tour",
					"DifficultTour Will Make You Regret You Bought This Tour, It's Gonna Be Hot Hot Hot",
					LocalDate.of(2021, 2, 10), LocalDate.of(2025, 2, 12), 23, (double) 1227, "volcano.jpg");
			Coupon coupon11 = new Coupon(Category.VACTION, "Sunken Ship",
					"DifficultTour Will Make Hold Your Breath (Wink) With The Best Seller Tour On A Sunken Ship!",
					LocalDate.of(2020, 3, 18), LocalDate.of(2021, 3, 2), 20, (double) 800, "ship.jpg");
			Coupon coupon12 = new Coupon(Category.VACTION, "Azkaban",
					"FlySweet Will Take You To A Relaxed Destination - Azkaban Prison ! ", LocalDate.of(2020, 7, 11),
					LocalDate.of(2022, 5, 17), 13, (double) 567, "azkaban.jpg");
			Coupon coupon13 = new Coupon(Category.VACTION, "Jurassic Park",
					"FlySweet Will Take You The Past\\Future, Where Dinosaurs Roll The Land !",
					LocalDate.of(2021, 1, 8), LocalDate.of(2023, 1, 19), 203, (double) 1555, "jurassic.jpg");
			Coupon coupon14 = new Coupon(Category.RESTAURANT, "Pickle Breakfest",
					"TheCuCumber Will Give You The Best Cucumber Breakfest In The World", LocalDate.of(2020, 5, 6),
					LocalDate.of(2024, 2, 21), 178, (double) 26, "cucum.jpg");
			Coupon coupon15 = new Coupon(Category.RESTAURANT, "Cornishon Dinner",
					"TheCuCumber Presents - A Full Dinner From Tomatos Only (And Maybe One or Two Olives)",
					LocalDate.of(2020, 8, 8), LocalDate.of(2022, 4, 26), 23, (double) 137, "cucum2.jpg");
			Coupon coupon16 = new Coupon(Category.RESTAURANT, "Thali", "The Indian Thali Just Like Your Mother Cooks",
					LocalDate.of(2020, 11, 9), LocalDate.of(2023, 2, 5), 45, (double) 20, "thali.jpg");
			Coupon coupon17 = new Coupon(Category.RESTAURANT, "Aloo Gobi",
					"The Indian - A Anciant Tale About That Time When Potato met With A Cauliflower And Made Love - On Your Plate",
					LocalDate.of(2020, 7, 18), LocalDate.of(2022, 9, 11), 23, (double) 187, "alo.jpg");

			companyS.addCoupon(coupon4, 2);
			companyS.addCoupon(coupon5, 2);
			companyS.addCoupon(coupon7, 3);
			companyS.addCoupon(coupon10, 5);
			companyS.addCoupon(coupon16, 8);
			companyS.addCoupon(coupon9, 4);
			companyS.addCoupon(coupon15, 7);
			companyS.addCoupon(coupon6, 3);
			companyS.addCoupon(coupon11, 5);
			companyS.addCoupon(coupon8, 4);
			companyS.addCoupon(coupon14, 7);
			companyS.addCoupon(coupon13, 6);
			companyS.addCoupon(coupon3, 2);
			companyS.addCoupon(coupon17, 8);
			companyS.addCoupon(coupon1, 1);
			companyS.addCoupon(coupon2, 1);
			companyS.addCoupon(coupon12, 6);

			CustomerService customerS = ctx.getBean(CustomerService.class);

			customerS.purchaseCoupon2(2, 1);
			customerS.purchaseCoupon2(1, 1);
			customerS.purchaseCoupon2(16, 1);
			customerS.purchaseCoupon2(11, 1);
			customerS.purchaseCoupon2(16, 2);
			customerS.purchaseCoupon2(4, 2);
			customerS.purchaseCoupon2(14, 2);
			customerS.purchaseCoupon2(7, 2);
			customerS.purchaseCoupon2(2, 3);
			customerS.purchaseCoupon2(4, 3);
			customerS.purchaseCoupon2(15, 3);
			customerS.purchaseCoupon2(10, 3);
			customerS.purchaseCoupon2(5, 4);
			customerS.purchaseCoupon2(15, 4);
			customerS.purchaseCoupon2(9, 4);
			customerS.purchaseCoupon2(17, 4);
			customerS.purchaseCoupon2(6, 5);
			customerS.purchaseCoupon2(3, 6);
			customerS.purchaseCoupon2(6, 6);
			customerS.purchaseCoupon2(8, 6);
			customerS.purchaseCoupon2(14, 7);
			customerS.purchaseCoupon2(12, 7);
			customerS.purchaseCoupon2(7, 7);

			Review rev1 = new Review(0, "best coupon", "buy fast before it endssss", 4, LocalDate.of(2021, 4, 25));
			Review rev2 = new Review(0, "worst coupon", "dont buy, its badddd", 2, LocalDate.of(2021, 6, 17));
			Review rev3 = new Review(0, "dont buy", "i wish i could return it", 1, LocalDate.of(2021, 8, 10));
			Review rev4 = new Review(0, "buy the pizza",
					"its so tasty, i will eat it forever and ever and ever (and ever)", 5, LocalDate.of(2020, 11, 12));
			Review rev5 = new Review(0, "my opinion", "this is my opinion and i approve it", 3,
					LocalDate.of(2021, 3, 6));
			Review rev6 = new Review(0, "mmmmmmmm", "its gooddddddddddd", 4, LocalDate.of(2021, 4, 19));
			Review rev7 = new Review(0, "best coffee", "great aroma", 5, LocalDate.of(2021, 5, 1));
			Review rev8 = new Review(0, "very nice", "i recommend it", 4, LocalDate.of(2021, 2, 7));
			Review rev9 = new Review(0, "naah", "not very good", 2, LocalDate.of(2020, 8, 12));
			Review rev10 = new Review(0, "10 points!!", "out of 100 :(", 1, LocalDate.of(2020, 5, 20));
			Review rev11 = new Review(0, "best in town", "if you live in Antartica......", 1,
					LocalDate.of(2021, 1, 24));

			customerS.addReview(1, rev1, 8);
			customerS.addReview(1, rev2, 4);
			customerS.addReview(1, rev10, 10);
			customerS.addReview(1, rev9, 9);
			customerS.addReview(1, rev4, 1);
			customerS.addReview(1, rev9, 5);
			customerS.addReview(1, rev7, 2);
			customerS.addReview(2, rev5, 11);
			customerS.addReview(2, rev10, 5);
			customerS.addReview(2, rev6, 2);
			customerS.addReview(2, rev11, 4);
			customerS.addReview(3, rev3, 1);
			customerS.addReview(3, rev5, 3);
			customerS.addReview(3, rev10, 11);
			customerS.addReview(4, rev5, 6);
			customerS.addReview(4, rev5, 12);
			customerS.addReview(4, rev5, 16);
			customerS.addReview(4, rev1, 7);
			customerS.addReview(5, rev2, 3);
			customerS.addReview(5, rev1, 13);
			customerS.addReview(5, rev1, 14);
			customerS.addReview(5, rev1, 1);
			customerS.addReview(6, rev2, 2);
			customerS.addReview(6, rev6, 6);
			customerS.addReview(6, rev6, 7);
			customerS.addReview(6, rev6, 3);
			customerS.addReview(6, rev8, 4);
			customerS.addReview(7, rev9, 15);
			customerS.addReview(7, rev9, 16);
			customerS.addReview(7, rev9, 4);
			customerS.addReview(7, rev2, 7);

			t1.start();
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			t1.interrupt();
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
