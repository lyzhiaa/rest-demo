package kh.edu.istad.restdemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer qty;

    private Boolean isActive;
}
