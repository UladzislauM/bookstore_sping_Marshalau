package com.company.service.serviceImpl;

import com.company.DTO.BookDTO;
import com.company.DTO.OrdersDTO;
import com.company.DTO.UserDTO;
import com.company.data.dataDTO.OrdersItemsDaoDTO;
import com.company.entity.Book;
import com.company.entity.Orders;
import com.company.entity.OrdersItems;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperSC {
    public Book toBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setCoverBook(bookDTO.getCoverBook());
        book.setDateReleaseBook(bookDTO.getDateReleaseBook());
        book.setIsbn(bookDTO.getIsbn());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
        book.setNameAuthor(bookDTO.getNameAuthor());
        book.setDeleted(bookDTO.getDeleted());
        return book;
    }

    public BookDTO toBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setCoverBook(book.getCoverBook());
        bookDTO.setDateReleaseBook(book.getDateReleaseBook());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setNameAuthor(book.getNameAuthor());
        bookDTO.setDeleted(book.getDeleted());
        return bookDTO;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setLast_name(userDTO.getLast_name());
        user.setRole(userDTO.getRole());
        user.setDeleted(userDTO.getDeleted());
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setRole(user.getRole());
        userDTO.setDeleted(user.getDeleted());
        return userDTO;
    }

    public Orders toOrder(OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setUser(ordersDTO.getUser());
        orders.setId(ordersDTO.getId());
        orders.setItems(ordersDTO.getItems());
        orders.setTimestamp(ordersDTO.getTimestamp());
        orders.setStatus(ordersDTO.getStatus());
        orders.setTotalCost(ordersDTO.getTotalCost());
        return orders;
    }

    public OrdersDTO toOrdersDTO (Orders orders){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setUser(orders.getUser());
        ordersDTO.setId(orders.getId());
        ordersDTO.setItems(orders.getItems());
        ordersDTO.setTimestamp(orders.getTimestamp());
        ordersDTO.setStatus(orders.getStatus());
        ordersDTO.setTotalCost(orders.getTotalCost());
        return ordersDTO;
    }

    public OrdersItems toOrderItems(OrdersItemsDaoDTO ordersItemsDTO) {
        OrdersItems ordersItems = new OrdersItems();
        ordersItems.setId(ordersItemsDTO.getId());
        ordersItems.setPrice(ordersItemsDTO.getPrice());
        ordersItems.setQuantity(ordersItemsDTO.getQuantity());
        return ordersItems;
    }
}
