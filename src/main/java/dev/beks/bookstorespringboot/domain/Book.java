package dev.beks.bookstorespringboot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("books")
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private String description;
    private double price;

}