package com.project3.revtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.revtech.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    CartEntity findByCartId(int cartId);
    CartEntity findByCartIdAndCartRemovedFalseAndCartPaidFalse(int cartId);
    CartEntity findByUserIdAndCartRemovedFalseAndCartPaidFalse(int userId);
    CartEntity findByUserId(int userId);
    CartEntity findByCartTotalEquals(int cartTotal);

}
