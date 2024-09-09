package dev.beks.bookstorespringboot.adapter.in.web.dto;

import dev.beks.bookstorespringboot.domain.Book;

public record BookRequest(
        String title,
        String author,
        String description,
        double price
) {

    public Book setValuesTo(Book book) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);
        return book;
    }
}
