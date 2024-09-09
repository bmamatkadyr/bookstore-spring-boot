package dev.beks.bookstorespringboot.application;

import dev.beks.bookstorespringboot.adapter.in.web.PagedResult;
import dev.beks.bookstorespringboot.adapter.in.web.dto.BookQuery;
import dev.beks.bookstorespringboot.adapter.in.web.dto.BookRequest;
import dev.beks.bookstorespringboot.domain.Book;

public interface BookService {

    Book save(BookRequest bookRequest);

    PagedResult<Book> getAll(BookQuery bookQuery);

    Book findById(Long id);

    Book update(Long id, BookRequest bookRequest);

    void deleteById(Long id);
}
