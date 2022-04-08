package com.project3.revtech.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.pojo.ProductPojo;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //----- Product Custom JPA Queries ----------//
    ProductEntity findByProductId(int productId);
	
}
