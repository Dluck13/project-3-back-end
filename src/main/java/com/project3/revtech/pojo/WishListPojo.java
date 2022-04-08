package com.project3.revtech.pojo;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListPojo {
	
	private int wishListId;
	private UserPojo userPojo;
	private int wishListTotal;
	private List<WishListItemPojo> wishListItems;
	
}