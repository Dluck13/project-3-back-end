package com.project3.revtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.revtech.entity.BundleEntity;

@Repository
public interface BundleRepository extends JpaRepository<BundleEntity, Integer> {
	BundleEntity findByBundleId(int bundleId);

}
