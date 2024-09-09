package dev.beks.bookstorespringboot.adapter.in.web.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record BookQuery(
        String search,
        int pageNumber,
        int pageSize
) {
    public BookQuery {
        if (search == null) search = "";

        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 0");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
    }

    public Pageable getPageable() {
        return PageRequest.of(pageNumber, pageSize);
    }
}