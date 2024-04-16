package ru.skypro.homework.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum URL {

    USER("/images/user/"),
    AD("/images/ad/");

    private final String url;
}