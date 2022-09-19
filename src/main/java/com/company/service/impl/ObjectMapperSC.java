package com.company.service.impl;

import com.company.data.entity.*;
import com.company.service.dto.BookDto;
import com.company.service.dto.OrderDto;
import com.company.service.dto.OrderItemDTO;
import com.company.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ObjectMapperSC {
    public Book toBook(BookDto bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setCoverBook(bookDTO.getCoverBook());
        book.setDateReleaseBook(LocalDate.parse(bookDTO.getDateReleaseBook()));
        book.setIsbn(bookDTO.getIsbn());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
        book.setNameAuthor(bookDTO.getNameAuthor());
        book.setDeleted(bookDTO.getDeleted());
        return book;
    }

    public BookDto toBookDTO(Book book) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setCoverBook(book.getCoverBook());
        bookDTO.setDateReleaseBook(book.getDateReleaseBook().toString());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setNameAuthor(book.getNameAuthor());
        bookDTO.setDeleted(book.getDeleted());
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

    public Order toOrder(OrderDto orderDTO) {
        Order order = new Order();
        order.setUser(orderDTO.getUser());
        order.setId(orderDTO.getId());
        order.setItems(orderDTO.getItems());
        order.setTimestamp(orderDTO.getTimestamp());
        order.setStatus(orderDTO.getStatus());
        order.setTotalCost(orderDTO.getTotalCost());
        return order;
    }

    public OrderDto toOrdersDTO (Order order){
        OrderDto orderDTO = new OrderDto();
        orderDTO.setUser(order.getUser());
        orderDTO.setId(order.getId());
        orderDTO.setItems(order.getItems());
        orderDTO.setTimestamp(order.getTimestamp());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalCost(order.getTotalCost());
        return orderDTO;
    }

    public OrderItem toOrderItem(OrderItemDTO orderItemDTO){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setOrder(toOrder(orderItemDTO.getOrderDto()));
        orderItem.setBook(toBook(orderItemDTO.getBookDto()));
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        return orderItem;
    }

    public OrderItemDTO toOrdersItemsDTO (OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setOrderDto(toOrdersDTO(orderItem.getOrder()));
        orderItemDTO.setBookDto(toBookDTO(orderItem.getBook()));
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        return orderItemDTO;
    }
}
