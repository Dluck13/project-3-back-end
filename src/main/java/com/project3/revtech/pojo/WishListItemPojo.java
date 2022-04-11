package com.project3.revtech.pojo;

import com.project3.revtech.joinedpojo.ProductAndDiscountPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListItemPojo {
	
	private int wishItemId;
	private WishListPojo wishListPojo;
	private ProductAndDiscountPojo productAndDiscountPojo;
	
}
