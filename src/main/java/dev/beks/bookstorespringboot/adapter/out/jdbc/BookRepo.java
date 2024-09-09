package dev.beks.bookstorespringboot.adapter.out.jdbc;

import dev.beks.bookstorespringboot.adapter.in.web.dto.BookQuery;
import dev.beks.bookstorespringboot.application.BookNotFoundException;
import dev.beks.bookstorespringboot.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    default Book findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Query("""
            SELECT * 
            FROM books b
            WHERE to_tsvector(b.title || ' ' || b.author || ' ' || b.description) @@ to_tsquery(:search)
            ORDER BY b.id DESC
            LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
            """)
    List<Book> getAllBy(String search, Pageable pageable);

    @Query("""
            SELECT COUNT(*)
            FROM books b
            WHERE to_tsvector(b.title || ' ' || b.author || ' ' || b.description) @@ to_tsquery(:search)
            """)
    long countAllBy(String search);


    default Page<Book> getAllBy(BookQuery bookQuery) {

        List<Book> books = getAllBy(
                bookQuery.search(), bookQuery.getPageable()
        );

        long total = countAllBy(bookQuery.search());

        return PageableExecutionUtils.getPage(
                books, bookQuery.getPageable(), () -> total
        );
    }
}