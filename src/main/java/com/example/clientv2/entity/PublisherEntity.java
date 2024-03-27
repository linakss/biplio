package com.example.clientv2.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PublisherEntity {
    private Long id;
    private String title;
    private CityEntity city;
    private List<BookEntity> books;

    @Override
    public String toString() {
        return title + ", г." + city;
    }
}
