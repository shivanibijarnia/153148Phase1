package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo {

	private Map<String, Customer> data;

	public WalletRepoImpl() {
		data = new HashMap<String, Customer>();
	}

	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public boolean save(Customer customer) {
		String mobile = customer.getMobileNo();
		System.out.println("Mobile number:" + mobile);

		if (data.get(mobile) == null) {
			data.put(mobile, customer);
			return true;
		} else {
			throw new InvalidInputException("This mobile number already exists");
		}

	}

	public Customer findOne(String mobileNo) {
		Customer customer = data.get(mobileNo);

		if (customer == null) {
			throw new InvalidInputException("Invalid mobile no ");
		} else {
			return customer;
		}

	}
}
