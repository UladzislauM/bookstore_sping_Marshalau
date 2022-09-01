package com.company.data.repository.impl;

import com.company.data.dto.BookDaoDto;
import com.company.data.dto.OrdersDaoDto;
import com.company.data.dto.OrdersItemsDaoDto;
import com.company.data.dto.UserDaoDto;
import com.company.service.entity.Book;
import com.company.service.entity.Orders;
import com.company.service.entity.OrdersItems;
import com.company.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperDao {
    public Book toBook(BookDaoDto bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setCoverBook(bookDTO.getCoverBookDaoDTO());
        book.setDateReleaseBook(bookDTO.getDateReleaseBook());
        book.setIsbn(bookDTO.getIsbn());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
        book.setNameAuthor(bookDTO.getNameAuthor());
        book.setDeleted(bookDTO.getDeleted());
        return book;
    }

    public BookDaoDto toBookDaoDTO(Book book) {
        BookDaoDto bookDTO = new BookDaoDto();
        bookDTO.setId(book.getId());
        bookDTO.setCoverBookDaoDTO(book.getCoverBook());
        bookDTO.setDateReleaseBook(book.getDateReleaseBook());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setNameAuthor(book.getNameAuthor());
        bookDTO.setDeleted(book.getDeleted());
        return bookDTO;
    }

    public User toUser(UserDaoDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setLast_name(userDTO.getLast_name());
        user.setRole(userDTO.getRoleDaoDTO());
        user.setDeleted(userDTO.getDeleted());
        return user;
    }

    public UserDaoDto toUserDaoDTO(User user) {
        UserDaoDto userDaoDTO = new UserDaoDto();
        userDaoDTO.setId(user.getId());
        userDaoDTO.setEmail(user.getEmail());
        userDaoDTO.setPassword(user.getPassword());
        userDaoDTO.setName(user.getName());
        userDaoDTO.setLast_name(user.getLast_name());
        userDaoDTO.setRoleDaoDTO(user.getRole());
        userDaoDTO.setDeleted(user.getDeleted());
        return userDaoDTO;
    }

    public Orders toOrder(OrdersDaoDto ordersDTO) {
        Orders orders = new Orders();
        orders.setId(ordersDTO.getId());
        orders.setTimestamp(ordersDTO.getTimestamp());
        orders.setStatus(ordersDTO.getStatus());
        orders.setTotalCost(ordersDTO.getTotalCost());
        return orders;
    }

    public OrdersItems toOrderItems(OrdersItemsDaoDto ordersItemsDTO) {
        OrdersItems ordersItems = new OrdersItems();
        ordersItems.setId(ordersItemsDTO.getId());
        ordersItems.setPrice(ordersItemsDTO.getPrice());
        ordersItems.setQuantity(ordersItemsDTO.getQuantity());
        return ordersItems;
    }
}
