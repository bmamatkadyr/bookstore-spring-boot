package dev.beks.bookstorespringboot.adapter.in.web;

import dev.beks.bookstorespringboot.adapter.in.web.dto.BookQuery;
import dev.beks.bookstorespringboot.adapter.in.web.dto.BookRequest;
import dev.beks.bookstorespringboot.adapter.in.web.dto.SimpleMessage;
import dev.beks.bookstorespringboot.application.BookService;
import dev.beks.bookstorespringboot.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // save
    @PostMapping
    public Book save(@RequestBody BookRequest bookRequest) {
        return bookService.save(bookRequest);
    }

    // get all
    @GetMapping
    public PagedResult<Book> getAll(BookQuery bookQuery) {
        return bookService.getAll(bookQuery);
    }

    // find by id
    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    // update
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,
                       @RequestBody BookRequest bookRequest) {
        return bookService.update(id, bookRequest);
    }

    // delete by id
    @DeleteMapping("/{id}")
    public SimpleMessage deleteById(@PathVariable Long id) {
        // delete book by id
        bookService.deleteById(id);

        // return message
        return new SimpleMessage("Book with id " + id + " deleted successfully");
    }
}