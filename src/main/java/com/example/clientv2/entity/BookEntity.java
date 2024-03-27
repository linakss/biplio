package com.example.clientv2.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class BookEntity {
    private Long id;
    private String title;
    private AuthorEntity author;
    private PublisherEntity publisher;
    private GenreEntity genre;
    private String year;

    @Override
    public String toString() {
        return title;
    }
}
