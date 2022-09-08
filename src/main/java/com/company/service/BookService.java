package com.company.service;

import com.company.service.dto.BookDto;
import com.company.data.entity.Books;
import org.springframework.stereotype.Component;

@Component
public interface BookService extends AbstractService<Books, BookDto> {
    Long countAll();
}
