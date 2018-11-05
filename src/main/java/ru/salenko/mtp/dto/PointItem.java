package ru.salenko.mtp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointItem {
    private String code;
    private String name;
    private String cityCode;
}
