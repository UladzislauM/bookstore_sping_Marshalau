package com.company.dao;

import com.company.dao.controller.BookControllers.*;
import com.company.dao.controller.UserControllers.*;
import com.company.dao.dao.BookDao;
import com.company.dao.dao.UserDao;
import com.company.dao.dao.daoImpl.BookDaoImpl;
import com.company.dao.dao.daoImpl.UserDaoImpl;
import com.company.dao.entity.Book;
import com.company.dao.entity.User;
import com.company.dao.service.serviceImpl.BookServiceImpl;
import com.company.dao.service.serviceImpl.UserServiceImpl;
import com.company.dao.util.DataSourceElephant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class ContextConfiguration {
    @Bean
    public Error error(){
        return new Error();
    }

    @Bean
    public UserCommand get_user_by_id(){
        return new UserCommand(userService(), user());
    }

    @Bean
    public UserCreate user_create(){
        return new UserCreate(userService(), user());
    }

    @Bean
    public UserDelete user_delete(){
        return new UserDelete(userService());
    }

    @Bean
    public UsersCommand users(){
        return new UsersCommand(userService());
    }

    @Bean
    public UserUpdate user_update(){
        return new UserUpdate(userService(), user());
    }

    @Bean
    public BookCommand get_book_by_id() {
        return new BookCommand(bookService(), book());
    }

    @Bean
    public BookCreate book_create() {
        return new BookCreate(bookService(), book());
    }

    @Bean
    public BookDelete book_delete() {
        return new BookDelete(bookService());
    }

    @Bean
    public BooksCommand books() {
        return new BooksCommand(bookService());
    }

    @Bean
    public BookUpdate book_update() {
        return new BookUpdate(bookService(), book());
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public Book book() {
        return new Book();
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    public BookServiceImpl bookService() {
        return new BookServiceImpl(bookDao());
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl(dataSourceElephant());
    }

    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl(dataSourceElephant());
    }

    @Bean
    public DataSourceElephant dataSourceElephant() {
        return DataSourceElephant.INSTANCE;
    }
}

