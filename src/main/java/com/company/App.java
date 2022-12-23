package com.company;

import com.company.web.filter.NotAuthorizationFilter;
import com.company.web.filter.UserPermissionsFilter;
import com.company.web.interceptors.RegistrationInterceptor;
import com.company.web.interceptors.ToLoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class App implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public FilterRegistrationBean<NotAuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<NotAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new NotAuthorizationFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<UserPermissionsFilter> UserPermissionsFilter() {
        FilterRegistrationBean<UserPermissionsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserPermissionsFilter());
        registrationBean.addUrlPatterns("/users/create", "/users/delete", "/users/find_all");
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(registrationInterceptor())
                .addPathPatterns("/users/registration");
        registry.addInterceptor(toLoginInterceptor())
                .addPathPatterns("/cart/book_to_cart");
    }

    @Bean
    public RegistrationInterceptor registrationInterceptor() {
        return new RegistrationInterceptor();
    }

    @Bean
    public ToLoginInterceptor toLoginInterceptor() {
        return new ToLoginInterceptor();
    }
}
