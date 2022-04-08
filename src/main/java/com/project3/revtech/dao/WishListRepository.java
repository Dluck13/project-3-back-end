package com.project3.revtech.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.entity.WishListItemEntity;
import com.project3.revtech.pojo.WishListPojo;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, Integer> {

	
	
	WishListEntity findByWishListId(int wishListId);
	
	@Query("SELECT wl FROM WishListEntity wl WHERE wl.userEntity.userId=:userWlId")
	WishListEntity getWishListByUserId(@Param("userWlId") int userId);


	//WishListEntity findByUserId(int userId);

//	WishListEntity findByUserIdAndWishListRemovedFalse(int userId);


	
}
