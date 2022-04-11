package com.project3.revtech.joinedpojo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndDiscountPojo {

    private int productId;
    private String productSku;
    private String productName;
    private BigDecimal productCost;
    private String productCategory;
    private String productDescription;
    private int productQty;
    private String imageUrl;
    private boolean productRemoved;
    private int discountId;
    private String discountDescription;
    private BigDecimal discountPercentage;

}
