package com.project3.revtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project3.revtech.exception.ApplicationException;

import com.project3.revtech.pojo.WishListItemPojo;

import com.project3.revtech.service.WishListItemServiceImpl;

@RestController
@RequestMapping("api/wishList-items")
@CrossOrigin
public class WishListItemController {

	@Autowired
	WishListItemServiceImpl wishListItemService;

	@PostMapping("add/items")
	ResponseEntity<WishListItemPojo> addItem(@RequestBody WishListItemPojo wishListItem) throws ApplicationException {
		return ResponseEntity.ok().header("Content-type", "application/json")
				.body(wishListItemService.addItem(wishListItem));
	}

	@DeleteMapping("{bid}/delete")
	ResponseEntity<Boolean> removeItem(@PathVariable("bid") int wishListItemId) throws ApplicationException {
		return ResponseEntity.ok().header("Content-type", "application/json")
				.body(wishListItemService.removeItem(wishListItemId));
	}

}
