package com.example.clientv2.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    private Long id;
    @NotNull
    private String lastname;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String password;
    @NotNull
    private String login;

    @Override
    public String toString() {
        return surname + name + lastname;
    }
}
