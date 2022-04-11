package com.project3.revtech.pojo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountPojo {

    private int discountId;
    private int productId;
    private String discountDescription;
    private BigDecimal discountPercentage;




    @Override
    public String toString() {
        return "DiscountPojo{" +
                "discountId=" + discountId +
                ", productId=" + productId +
                ", discountDescription='" + discountDescription + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
