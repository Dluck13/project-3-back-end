package com.project3.revtech.service;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project3.revtech.dao.UserRepository;
import com.project3.revtech.dao.WishListRepository;
import com.project3.revtech.entity.UserEntity;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.UserPojo;
import com.project3.revtech.pojo.WishListPojo;

@ContextConfiguration(classes = { WishListServiceImpl.class })
@ExtendWith(SpringExtension.class)
public class WishListServiceImplTest {


	@MockBean
	private WishListRepository wishListRepository;

	@Autowired
	private WishListServiceImpl wishListServiceImpl;
	
	@MockBean
	private UserRepository userRepository;

	@Test
	void testAddWishList() throws ApplicationException {

		UserEntity user = new UserEntity();
		user.setAddress("42 Main St");
		user.setContact("Contact");
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setPassword("iloveyou");
		user.setRoles(new HashSet<>());
		user.setUserId(1);
		user.setUsername("janedoe");

		WishListEntity wishList = new WishListEntity();
		wishList.setWishListId(123);
		wishList.setUserEntity(user);
		wishList.setWishListItems(new ArrayList<>());

		UserPojo userPojo = new UserPojo();
		userPojo.setAddress("42 Main St");
		userPojo.setContact("Contact");
		userPojo.setEmail("jane.doe@example.org");
		userPojo.setFirstName("Jane");
		userPojo.setLastName("Doe");
		userPojo.setPassword("iloveyou");
		userPojo.setUsername("janedoe");
		userPojo.setUser_id(1);
		
		WishListPojo wishListPojo = new WishListPojo();
		wishListPojo.setWishListId(123);
		wishListPojo.setUserPojo(userPojo);
		wishListPojo.setWishListItems(new ArrayList<>());
		
		when(this.wishListRepository.saveAndFlush((WishListEntity) any())).thenReturn(wishList);
		when(this.userRepository.findById(1)).thenReturn(Optional.of(user));
		
		WishListPojo actualAddWishListResult = this.wishListServiceImpl.addWishList(wishListPojo);
		assertSame(wishListPojo, actualAddWishListResult);
		assertEquals(123, actualAddWishListResult.getWishListId());
		verify(this.wishListRepository).saveAndFlush((WishListEntity) any());

	}

	@Test
	void testGetWishListByUserId() throws ApplicationException {

		UserEntity user = new UserEntity();
		user.setAddress("42 Main St");
		user.setContact("Contact");
		user.setEmail("jane.doe@example.org");
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setPassword("iloveyou");
		user.setRoles(new HashSet<>());
		user.setUserId(1);
		user.setUsername("janedoe");

		WishListEntity wishList = new WishListEntity();
		wishList.setWishListId(123);
		wishList.setUserEntity(user);
		wishList.setWishListItems(new ArrayList<>());
		
		UserPojo userPojo = new UserPojo();
		userPojo.setAddress("42 Main St");
		userPojo.setContact("Contact");
		userPojo.setEmail("jane.doe@example.org");
		userPojo.setFirstName("Jane");
		userPojo.setLastName("Doe");
		userPojo.setPassword("iloveyou");
		userPojo.setUsername("janedoe");
		userPojo.setUser_id(1);
		
		WishListPojo wishListPojo = new WishListPojo();
		wishListPojo.setWishListId(123);
		wishListPojo.setUserPojo(userPojo);
		wishListPojo.setWishListItems(new ArrayList<>());

		when(this.wishListRepository.getWishListByUserId(anyInt())).thenReturn(wishList);
		WishListPojo actualWishListByUserId = this.wishListServiceImpl.getListByUserId(1);
		assertEquals(123, actualWishListByUserId.getWishListId());
		verify(this.wishListRepository).getWishListByUserId(anyInt());
	}

}
