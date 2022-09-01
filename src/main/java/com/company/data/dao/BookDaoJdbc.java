package com.company.data.dao;

import com.company.data.dto.BookDaoDto;
import org.springframework.stereotype.Component;

@Component
public interface BookDaoJdbc extends AbstractDaoJdbc<BookDaoDto> {
    BookDaoDto create(BookDaoDto book);

    Long countAll();
}
