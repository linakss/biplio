package com.example.clientv2.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GenreEntity {
    private Long id;
    private String title;
    private List<BookEntity> books;

    @Override
    public String toString() {
        return title;
    }
}
