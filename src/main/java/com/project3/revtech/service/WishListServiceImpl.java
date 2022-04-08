package com.project3.revtech.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project3.revtech.dao.UserRepository;
import com.project3.revtech.dao.WishListRepository;
import com.project3.revtech.entity.UserEntity;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.entity.WishListItemEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.ProductPojo;
import com.project3.revtech.pojo.UserPojo;
import com.project3.revtech.pojo.WishListItemPojo;
import com.project3.revtech.pojo.WishListPojo;


@Service
@Transactional

public class WishListServiceImpl implements WishListService {	

	@Autowired
	WishListRepository wishListRepository;
	
	@Autowired
	UserRepository userRepository;
  
	@Override
	public WishListPojo addWishList(WishListPojo wishList) throws ApplicationException {
		
		List<WishListEntity> allWishList = wishListRepository.findAll();  // fetches all wishlist and check if a wishlist exist in the for loop
		for(WishListEntity awishList : allWishList) {					  // if it does then it will just return the exisiting wishlist
			if(awishList.getUserEntity().getUserId() == wishList.getUserPojo().getUser_id()) {
				BeanUtils.copyProperties(awishList, wishList);
				BeanUtils.copyProperties(awishList.getUserEntity(), wishList.getUserPojo());
				return wishList;
			}
		}
		
		UserEntity user = userRepository.findById(wishList.getUserPojo().getUser_id()).get();
		WishListEntity wishListEntity = new WishListEntity(user);
		WishListEntity returnWishList = wishListRepository.saveAndFlush(wishListEntity);
		
		wishList.setWishListId(returnWishList.getWishListId());
		BeanUtils.copyProperties(returnWishList.getUserEntity(), wishList.getUserPojo());
	
		return wishList;
	}

	@Override
	public WishListPojo getListByUserId(int userId) throws ApplicationException {
		
		WishListEntity wishListEntity = wishListRepository.getWishListByUserId(userId);  //getting wishlist by userid
		
		WishListPojo wishList = new WishListPojo();
		UserPojo user = new UserPojo();
		
		wishList.setWishListId(wishListEntity.getWishListId());
		wishList.setWishListTotal(wishListEntity.getWishListTotal());
		BeanUtils.copyProperties(wishListEntity.getUserEntity(), user);
		user.setUser_id(userId);
		wishList.setUserPojo(user);

		List<WishListItemPojo> wishItemPojo = new ArrayList<WishListItemPojo>();
		
		for(WishListItemEntity wishItem : wishListEntity.getWishListItems()) {
			WishListItemPojo items = new WishListItemPojo();
			BeanUtils.copyProperties(wishItem, items);
			
			ProductPojo product = new ProductPojo();
			BeanUtils.copyProperties(wishItem.getProductEntity(), product);
			items.setProductPojo(product);
			wishItemPojo.add(items);
		}
		
		wishList.setWishListItems(wishItemPojo);
		return wishList;
	}

	@Override
	public boolean removeWishList(WishListPojo wishListPojo) throws ApplicationException {
		wishListRepository.deleteById(wishListPojo.getWishListId());
		return true;
	}

}

