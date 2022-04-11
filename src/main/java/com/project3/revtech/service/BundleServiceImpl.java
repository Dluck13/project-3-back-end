package com.project3.revtech.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project3.revtech.dao.BundleRepository;
import com.project3.revtech.dao.ProductRepository;
import com.project3.revtech.entity.BundleEntity;
import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.BundlePojo;
import com.project3.revtech.pojo.ProductPojo;

@Service
@Transactional
public class BundleServiceImpl implements BundleService {
	
	@Autowired
	BundleRepository bundleRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	EmailDiscountServiceImpl emailDiscountService;

	@Override
	public List<BundlePojo> getAllBundles() throws ApplicationException {
		List<BundleEntity> allBundlesEntity = this.bundleRepository.findAll();
		List<BundlePojo>  allBundlesPojo = new ArrayList<BundlePojo>();
		for(BundleEntity entity: allBundlesEntity) {
			BundlePojo bundlePojo = new BundlePojo();
			BeanUtils.copyProperties(entity, bundlePojo);
			
			BeanUtils.copyProperties(entity.getProductOneEntity(), bundlePojo.getProductOnePojo());
			BeanUtils.copyProperties(entity.getProductTwoEntity(), bundlePojo.getProductTwoPojo());
			allBundlesPojo.add(bundlePojo);
		}

		return allBundlesPojo;
	}

	@Override
	public BundlePojo createBundle(BundlePojo bundlePojo) throws ApplicationException {
		
		
		BundleEntity bundleEntity = new BundleEntity();
		
		BeanUtils.copyProperties(bundlePojo, bundleEntity);
		
		ProductEntity productOneEntity = productRepository.findByProductId(bundlePojo.getProductOnePojo().getProductId());
		ProductEntity productTwoEntity = productRepository.findByProductId(bundlePojo.getProductTwoPojo().getProductId());
		
		bundleEntity.setProductOneEntity(productOneEntity);
		bundleEntity.setProductTwoEntity(productTwoEntity);
		
		bundleRepository.saveAndFlush(bundleEntity);
		
		bundlePojo.setBundleId(bundleEntity.getBundleId());
		BeanUtils.copyProperties(productOneEntity,bundlePojo.getProductOnePojo());
		BeanUtils.copyProperties(productTwoEntity,bundlePojo.getProductTwoPojo());
		
		//Email discount intercept
		emailDiscountService.sendByBundle(bundlePojo);
		return bundlePojo;
	}
}
