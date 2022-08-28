package com.company.data.dao;

import com.company.data.dataDTO.BookDaoDTO;
import org.springframework.stereotype.Component;

@Component
public interface BookDaoJdbc extends AbstractDaoJdbc<BookDaoDTO> {
    BookDaoDTO create(BookDaoDTO book);

    Long countAll();
}
