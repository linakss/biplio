package com.example.clientv2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.intellij.lang.annotations.Pattern;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuthorEntity {
    private Long id;
    private String lastname;
    private String name;
    private String surname;
    private List<BookEntity> books;

    @Override
    public String toString() {
        return surname + " " + name + " " + lastname;
    }
}



