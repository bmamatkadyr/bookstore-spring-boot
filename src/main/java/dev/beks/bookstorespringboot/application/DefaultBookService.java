package dev.beks.bookstorespringboot.application;

import dev.beks.bookstorespringboot.adapter.in.web.PagedResult;
import dev.beks.bookstorespringboot.adapter.in.web.dto.BookQuery;
import dev.beks.bookstorespringboot.adapter.in.web.dto.BookRequest;
import dev.beks.bookstorespringboot.adapter.out.jdbc.BookRepo;
import dev.beks.bookstorespringboot.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepo bookRepo;

    @Override
    @Transactional
    public Book save(BookRequest bookRequest) {

        Book newBook = bookRequest.setValuesTo(new Book());

        return bookRepo.save(newBook);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedResult<Book> getAll(BookQuery bookQuery) {

        Page<Book> bookPage = bookRepo.getAllBy(bookQuery);

        return PagedResult.of(bookPage);
    }

    @Override
    public Book findById(Long id) {
        return bookRepo.findByIdOrElseThrow(id);
    }

    @Override
    @Transactional
    public Book update(Long id, BookRequest bookRequest) {

        Book book = bookRepo.findByIdOrElseThrow(id);

        bookRequest.setValuesTo(book);

        return bookRepo.save(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = bookRepo.findByIdOrElseThrow(id);
        bookRepo.delete(book);
    }
}