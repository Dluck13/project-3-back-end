package com.project3.revtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.revtech.entity.ImageEntity;

@Repository
public interface ImageControllerRepository extends JpaRepository<ImageEntity, Integer> {

}
