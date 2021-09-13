package com.couponsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.couponsystem.exception.CouponSystemException;
import com.couponsystem.services.ClientService.ClientType;
import com.couponsystem.services.LoginFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CouponSystemApplication {

	public static void main(String[] args) throws CouponSystemException {
		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);

		Test test = ctx.getBean(Test.class);
		test.addAll(ctx);
	}

	@Bean("admin")
	public FilterRegistrationBean<LoginFilter> filterRegistrationBeanAdmin(LoginFilter loginFilter) {
		loginFilter.setUserType(ClientType.ADMINISTRATOR);
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/admin/*");
		return filterRegistrationBean;
	}

	@Bean("company")
	public FilterRegistrationBean<LoginFilter> filterRegistrationBeanCompany(LoginFilter loginFilter) {
		loginFilter.setUserType(ClientType.COMPANY);
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/company/*");
		return filterRegistrationBean;
	}

	@Bean("customer")
	public FilterRegistrationBean<LoginFilter> filterRegistrationBeanCustomer(LoginFilter loginFilter) {
		loginFilter.setUserType(ClientType.CUSTOMER);
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/customer/*");
		return filterRegistrationBean;
	}
}
