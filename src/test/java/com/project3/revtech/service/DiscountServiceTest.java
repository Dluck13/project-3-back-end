package com.project3.revtech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project3.revtech.dao.DiscountRepository;
import com.project3.revtech.entity.CartItemEntity;
import com.project3.revtech.entity.DiscountEntity;
import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.DiscountPojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DiscountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DiscountServiceTest {
    @MockBean
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountServiceImpl discountService;

    @Test
    void testGetAllDiscounts() {
        when(this.discountRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.discountService.getAllDiscounts().isEmpty());
        verify(this.discountRepository).findAll();
    }

    @Test
    void testAddDiscount() throws ApplicationException {
        DiscountEntity discount = new DiscountEntity();
        discount.setDiscountDescription("3");
        discount.setDiscountId(3);
        discount.setDiscountPercentage(null);
        discount.setProductEntity(new ProductEntity());
        discount.setProductId(123);

        ProductEntity product = new ProductEntity();
        product.setCartItems(new ArrayList<>());
        product.setDiscountEntity(discount);
        product.setImageUrl("https://media.istockphoto.com/photos/newly-released-iphone-13-pro-mockup-set-with-back-and-front-angles-picture-id1356372494?k=20&m=1356372494&s=612x612&w=0&h=4IyK75PK9dd4zY-CPAF_scPK-HwsoYS2mmWJZzBwp2A=");
        product.setImages(new ArrayList<>());
        product.setProductCategory("Phone");
        product.setProductCost(BigDecimal.valueOf(42L));
        product.setProductDescription("Hi Product Description");
        product.setProductId(123);
        product.setProductName("Iphone XR");
        product.setProductQty(1);
        product.setProductRemoved(true);
        product.setProductSku("Pro123ductSku");

        DiscountEntity discount1 = new DiscountEntity();
        discount1.setDiscountDescription("3");
        discount1.setDiscountId(3);
        discount1.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount1.setProductEntity(product);
        discount1.setProductId(123);

        ProductEntity product1 = new ProductEntity();
        ArrayList<CartItemEntity> cartItemList = new ArrayList<>();
        product1.setCartItems(cartItemList);
        product1.setDiscountEntity(discount1);
        product1.setImageUrl("https://media.istockphoto.com/photos/newly-released-iphone-13-pro-mockup-set-with-back-and-front-angles-picture-id1356372494?k=20&m=1356372494&s=612x612&w=0&h=4IyK75PK9dd4zY-CPAF_scPK-HwsoYS2mmWJZzBwp2A=");
        product1.setImages(new ArrayList<>());
        product1.setProductCategory("Phone");
        product1.setProductCost(BigDecimal.valueOf(42L));
        product1.setProductDescription("Hi Product Description");
        product1.setProductId(123);
        product1.setProductName("Iphone XR");
        product1.setProductQty(1);
        product1.setProductRemoved(true);
        product1.setProductSku("Pro123ductSku");

        DiscountEntity discount2 = new DiscountEntity();
        discount2.setDiscountDescription("3");
        discount2.setDiscountId(3);
        discount2.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount2.setProductEntity(product1);
        discount2.setProductId(123);
        when(this.discountRepository.saveAndFlush((DiscountEntity) any())).thenReturn(discount2);
        DiscountPojo discountPojo = new DiscountPojo(3, 123, "3", BigDecimal.valueOf(42L));

        DiscountPojo actualAddDiscountResult = this.discountService.addDiscount(discountPojo);
        assertSame(discountPojo, actualAddDiscountResult);
        assertEquals("42", actualAddDiscountResult.getDiscountPercentage().toString());
        verify(this.discountRepository).saveAndFlush((DiscountEntity) any());
        assertEquals(cartItemList, this.discountService.getAllDiscounts());
    }

    @Test
    void testRemoveDiscount() throws ApplicationException {
        doNothing().when(this.discountRepository).deleteById((Integer) any());
        assertTrue(this.discountService.removeDiscount(123));
        verify(this.discountRepository).deleteById((Integer) any());
        assertTrue(this.discountService.getAllDiscounts().isEmpty());
    }

    @Test
    void testUpdateDiscount() throws ApplicationException {
        DiscountEntity discount = new DiscountEntity();
        discount.setDiscountDescription("3");
        discount.setDiscountId(3);
        discount.setDiscountPercentage(null);
        discount.setProductEntity(new ProductEntity());
        discount.setProductId(123);

        ProductEntity product = new ProductEntity();
        product.setCartItems(new ArrayList<>());
        product.setDiscountEntity(discount);
        product.setImageUrl("https://media.istockphoto.com/photos/newly-released-iphone-13-pro-mockup-set-with-back-and-front-angles-picture-id1356372494?k=20&m=1356372494&s=612x612&w=0&h=4IyK75PK9dd4zY-CPAF_scPK-HwsoYS2mmWJZzBwp2A=");
        product.setImages(new ArrayList<>());
        product.setProductCategory("Phone");
        product.setProductCost(BigDecimal.valueOf(42L));
        product.setProductDescription("Hi Product Description");
        product.setProductId(123);
        product.setProductName("Iphone XR");
        product.setProductQty(1);
        product.setProductRemoved(true);
        product.setProductSku("Pro123ductSku");

        DiscountEntity discount1 = new DiscountEntity();
        discount1.setDiscountDescription("3");
        discount1.setDiscountId(3);
        discount1.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount1.setProductEntity(product);
        discount1.setProductId(123);

        ProductEntity product1 = new ProductEntity();
        ArrayList<CartItemEntity> cartItemList = new ArrayList<>();
        product1.setCartItems(cartItemList);
        product1.setDiscountEntity(discount1);
        product1.setImageUrl("https://media.istockphoto.com/photos/newly-released-iphone-13-pro-mockup-set-with-back-and-front-angles-picture-id1356372494?k=20&m=1356372494&s=612x612&w=0&h=4IyK75PK9dd4zY-CPAF_scPK-HwsoYS2mmWJZzBwp2A=");
        product1.setImages(new ArrayList<>());
        product1.setProductCategory("Phone");
        product1.setProductCost(BigDecimal.valueOf(42L));
        product1.setProductDescription("Hi Product Description");
        product1.setProductId(123);
        product1.setProductName("Iphone XR");
        product1.setProductQty(1);
        product1.setProductRemoved(true);
        product1.setProductSku("Pro123ductSku");

        DiscountEntity discount2 = new DiscountEntity();
        discount2.setDiscountDescription("3");
        discount2.setDiscountId(3);
        discount2.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount2.setProductEntity(product1);
        discount2.setProductId(123);
        when(this.discountRepository.save((DiscountEntity) any())).thenReturn(discount2);
        DiscountPojo discountPojo = new DiscountPojo(3, 123, "3", BigDecimal.valueOf(42L));

        DiscountPojo actualUpdateDiscountResult = this.discountService.updateDiscount(discountPojo);
        assertSame(discountPojo, actualUpdateDiscountResult);
        assertEquals("42", actualUpdateDiscountResult.getDiscountPercentage().toString());
        verify(this.discountRepository).save((DiscountEntity) any());
        assertEquals(cartItemList, this.discountService.getAllDiscounts());
    }
}

