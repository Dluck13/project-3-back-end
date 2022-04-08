package com.project3.revtech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.entity.UserEntity;
import com.project3.revtech.entity.WishListEntity;
import com.project3.revtech.joinedpojo.SentEmailsPojo;
import com.project3.revtech.pojo.DiscountPojo;

@ContextConfiguration(classes = {EmailDiscountServiceImpl.class,
		EmailService.class,
		JavaMailSenderImpl.class,
		SimpleMailMessage.class,
		SentEmailsPojo.class})
@ExtendWith(SpringExtension.class )
@ComponentScan
public class EmailDiscountTest {
	
	
	@Autowired
	EmailDiscountServiceImpl emailDiscountTest;
	@MockBean
	EmailService eService;

	@RegisterExtension
	static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);
	
	@Test
	void testEmailSendByDiscount() //throws 
	{
		DiscountPojo discount = new DiscountPojo(1,1,"high",new BigDecimal(.5));		
		
		WishListEntity first = new WishListEntity();

		ProductEntity discountedProduct = new ProductEntity();
		discountedProduct.setProductName("Hello");
		discountedProduct.setProductId(1);
		UserEntity emailReciever = new UserEntity();
		//need test email
		emailReciever.setEmail("demoreceiveracct1@gmail.com");
		first.setUserEntity(emailReciever);
		
		List<WishListEntity> wishlist = new ArrayList<WishListEntity>();
		wishlist.add(first);
		List<SentEmailsPojo> sentEmails = new ArrayList<SentEmailsPojo>();
		SentEmailsPojo sentEmail = new SentEmailsPojo(1,1,1,"demoreceiveracct1@gmail.com",new BigDecimal(.5),"Hello");
		sentEmails.add(sentEmail);
		
	//	when(wlpuRepository).thenReturn(wishlist);
		
		String messageText ="Name" + "Just went on Sale \n"
				+ "GET " + discount.getDiscountPercentage().multiply(new BigDecimal(100)) + "% OFF!!!!!";	
		
		doNothing().when(eService).sendMessage("demoreceiveracct1@gmail.com", "Discount", messageText);;
		List<SentEmailsPojo> actualResult =  emailDiscountTest.sendByDiscount(discount);
		
		System.out.println(actualResult);
		System.out.println(sentEmails);
		assertEquals(actualResult.toString(),sentEmails.toString());
	}
	
	
	
}
