package dev.beks.bookstorespringboot.adapter.in.web;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public record PagedResult<T>(
        int page,
        int size,
        long totalPages,
        long totalElements,
        List<T> data
) {

    public static <S, U> PagedResult<U> of(Page<S> page, Function<S, U> convert) {
        return new PagedResult<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getContent().stream().map(convert).toList());
    }

    public static <S> PagedResult<S> of(Page<S> page) {
        return new PagedResult<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getContent()
        );
    }
}