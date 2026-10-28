package kh.edu.istad.restdemo.dto;

import java.math.BigDecimal;

public record CreateProductRequest(

        String title,

        BigDecimal price,

        Integer qty

) {
}
