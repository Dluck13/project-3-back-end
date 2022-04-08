package com.project3.revtech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project3.revtech.dao.DiscountRepository;
import com.project3.revtech.dao.ProductRepository;
import com.project3.revtech.dao.WishItemRepository;
import com.project3.revtech.dao.WishListRepository;
import com.project3.revtech.entity.DiscountEntity;
import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.entity.WishListItemEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.DiscountPojo;
import com.project3.revtech.pojo.ProductPojo;
import com.project3.revtech.pojo.WishListItemPojo;

@Service
@Transactional
public class WishListItemServiceImpl implements WishListItemService {
	
	@Autowired
	WishItemRepository wishItemRepository;
	
	@Autowired
	WishListRepository wishListRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public WishListItemPojo addItem(WishListItemPojo item) throws ApplicationException {
		
		WishListEntity wishList = wishListRepository.findByWishListId(item.getWishListPojo().getWishListId());
		ProductEntity productEntity = productRepository.findByProductId(item.getProductPojo().getProductId());
		
		WishListItemEntity wishItem = new WishListItemEntity();
		wishItem.setWishListEntity(wishList);
		wishItem.setProductEntity(productEntity);
		wishItem.getWishListEntity().setWishListTotal(wishItem.getWishListEntity().getWishListTotal()+1);
		
		WishListItemEntity returningItem = wishItemRepository.saveAndFlush(wishItem);
		item.setWishItemId(returningItem.getWishItemId());

		BeanUtils.copyProperties(returningItem.getProductEntity(), item.getProductPojo());
		BeanUtils.copyProperties(returningItem.getWishListEntity(), item.getWishListPojo());
		
		return item;
	}

	@Override
	public boolean removeItem(int itemId) throws ApplicationException {
		wishItemRepository.deleteById(itemId);
		return true;
	}
	
}
