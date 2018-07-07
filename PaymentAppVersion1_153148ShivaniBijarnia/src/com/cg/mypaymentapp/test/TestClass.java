package com.cg.mypaymentapp.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

WalletService services;
	
	@Before
	public void initData(){
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			services= new WalletServiceImpl(data);
			
	}
	@Test
	public void testCreateAccountPassed() {		
		Customer customer=services.createAccount("Shivani", "9929577597", new BigDecimal(0));
		assertNotNull(customer);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccountFailed() {
		Customer customer=services.createAccount(null, null, null);
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccountFailed1() {
		Customer customer=services.createAccount("Shivani", "9929577597", null);
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccountFailed2() {		
		Customer customer=services.createAccount("Shivani", null, new BigDecimal(0));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccountFailed3() {		
		Customer customer=services.createAccount(null, "9929577597", new BigDecimal(0));
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccountFailed4() {		
		Customer customer=services.createAccount("Shivani", "992957759", new BigDecimal(0));
		
	}

	@Test(expected=InsufficientBalanceException.class)
	public void testWithdraw() {		
		//Customer customer=services.createAccount("Shivani", "9929577597", new BigDecimal(500));
		services.withdrawAmount("9922950519", new BigDecimal(8000));
		
	}
	
	@Test
	public void testWithdraw1() {		
		//Customer customer=services.createAccount("Shivani", "9929577597", new BigDecimal(500));
		services.withdrawAmount("9922950519", new BigDecimal(200));					
	}
	
	@Test
	public void testShowBalance() {		
		//Customer customer=services.createAccount("Shivani", "9929577597", new BigDecimal(500));
		services.showBalance("9922950519");					
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDeposit() {		
		services.depositAmount("9929577597", new BigDecimal(21000));
		
	}
	
	@Test
	public void testDeposit1() {		
		Customer customer=services.depositAmount("9900112212", new BigDecimal(1000));
		assertEquals(new BigDecimal(10000),customer.getWallet().getBalance());
		
	}
	
	@Test
	public void testWithdraw2() {		
		Customer customer=services.withdrawAmount("9900112212", new BigDecimal(1000));
		assertEquals(new BigDecimal(8000),customer.getWallet().getBalance());
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer() {
		services.fundTransfer("9929574436", "9768766349", new BigDecimal(1000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer1() {
		services.fundTransfer("9922950519", "9768766349", new BigDecimal(1000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer2() {
		services.fundTransfer("9922950519", "9922950519", new BigDecimal(1000));
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer3() {
		services.fundTransfer("9922950519", "9900112212", new BigDecimal(10000));
	}
	
	@Test
	public void testFundTransfer4() {
		services.fundTransfer("9922950519", "9900112212", new BigDecimal(1000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer5() {
		services.fundTransfer(null, "9900112212", new BigDecimal(1000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer6() {
		services.fundTransfer("9900112212", null,  new BigDecimal(1000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer7() {
		services.fundTransfer("9900112212", "9922950519",  null);
	}
}
