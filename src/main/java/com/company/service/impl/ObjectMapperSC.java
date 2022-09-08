package com.company.service.impl;

import com.company.data.entity.*;
import com.company.service.dto.BookDto;
import com.company.service.dto.OrdersDto;
import com.company.service.dto.OrdersItemsDTO;
import com.company.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperSC {
    public Books toBook(BookDto bookDTO) {
        Books books = new Books();
        books.setId(bookDTO.getId());
        books.setCoverBook(bookDTO.getCoverBook());
        books.setDateReleaseBook(bookDTO.getDateReleaseBook());
        books.setIsbn(bookDTO.getIsbn());
        books.setPrice(bookDTO.getPrice());
        books.setTitle(bookDTO.getTitle());
        books.setNameAuthor(bookDTO.getNameAuthor());
        books.setDeleted(bookDTO.getDeleted());
        return books;
    }

    public BookDto toBookDTO(Books books) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(books.getId());
        bookDTO.setCoverBook(books.getCoverBook());
        bookDTO.setDateReleaseBook(books.getDateReleaseBook());
        bookDTO.setIsbn(books.getIsbn());
        bookDTO.setPrice(books.getPrice());
        bookDTO.setTitle(books.getTitle());
        bookDTO.setNameAuthor(books.getNameAuthor());
        bookDTO.setDeleted(books.getDeleted());
        return bookDTO;
    }

    public User toUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setLast_name(userDTO.getLast_name());
        user.setRole(userDTO.getRole());
        user.setIs_active(userDTO.getIs_active());
        return user;
    }

    public UserDto toUserDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setRole(user.getRole());
        userDTO.setIs_active(user.getIs_active());
        return userDTO;
    }

    public Orders toOrder(OrdersDto ordersDTO) {
        Orders orders = new Orders();
        orders.setUser(ordersDTO.getUser());
        orders.setId(ordersDTO.getId());
        orders.setItems(ordersDTO.getItems());
        orders.setTimestamp(ordersDTO.getTimestamp());
        orders.setStatus(ordersDTO.getStatus());
        orders.setTotalCost(ordersDTO.getTotalCost());
        return orders;
    }

    public OrdersDto toOrdersDTO (Orders orders){
        OrdersDto ordersDTO = new OrdersDto();
        ordersDTO.setUser(orders.getUser());
        ordersDTO.setId(orders.getId());
        ordersDTO.setItems(orders.getItems());
        ordersDTO.setTimestamp(orders.getTimestamp());
        ordersDTO.setStatus(orders.getStatus());
        ordersDTO.setTotalCost(orders.getTotalCost());
        return ordersDTO;
    }

    public OrdersItems toOrdersItems (OrdersItemsDTO ordersItemsDTO){
        OrdersItems ordersItems = new OrdersItems();
        ordersItems.setId(ordersItemsDTO.getId());
        ordersItems.setOrders(toOrder(ordersItemsDTO.getOrdersDto()));
        ordersItems.setBook(toBook(ordersItemsDTO.getBookDto()));
        ordersItems.setQuantity(ordersItemsDTO.getQuantity());
        ordersItems.setPrice(ordersItemsDTO.getPrice());
        return ordersItems;
    }

    public OrdersItemsDTO toOrdersItemsDTO (OrdersItems ordersItems){
        OrdersItemsDTO ordersItemsDTO = new OrdersItemsDTO();
        ordersItemsDTO.setId(ordersItems.getId());
        ordersItemsDTO.setOrdersDto(toOrdersDTO(ordersItems.getOrders()));
        ordersItemsDTO.setBookDto(toBookDTO(ordersItems.getBook()));
        ordersItemsDTO.setQuantity(ordersItems.getQuantity());
        ordersItemsDTO.setPrice(ordersItems.getPrice());
        return ordersItemsDTO;
    }
}
