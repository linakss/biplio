package com.example.clientv2.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CityEntity {
    private Long id;
    private String title;
    private List<PublisherEntity> publisher;

    @Override
    public String toString() {
        return title;
    }
}
