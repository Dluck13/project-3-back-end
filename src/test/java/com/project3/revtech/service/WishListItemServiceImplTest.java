package com.project3.revtech.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project3.revtech.dao.ProductRepository;
import com.project3.revtech.dao.WishItemRepository;
import com.project3.revtech.dao.WishListRepository;
import com.project3.revtech.entity.DiscountEntity;
import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.entity.UserEntity;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.entity.WishListItemEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.joinedpojo.ProductAndDiscountPojo;
import com.project3.revtech.pojo.ProductPojo;
import com.project3.revtech.pojo.UserPojo;
import com.project3.revtech.pojo.WishListItemPojo;
import com.project3.revtech.pojo.WishListPojo;

@ContextConfiguration(classes = { WishListItemServiceImpl.class })
@ExtendWith(SpringExtension.class)
public class WishListItemServiceImplTest {

	@MockBean
	private WishItemRepository wishItemRepository;
	
	@MockBean
	private WishListRepository wishListRepository;

	@MockBean
	private ProductRepository productRepository;
	
	@Autowired
	private WishListItemServiceImpl wishListItemServiceImpl;

	@Test
	void testRemoveItem() throws ApplicationException {
		doNothing().when(this.wishItemRepository).deleteById((Integer) any());
		assertTrue(this.wishListItemServiceImpl.removeItem(123));
		verify(this.wishItemRepository).deleteById((Integer) any());
	}

}
