/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.tuberculosis.data;

import com.tuberculosis.entity.User;
import com.tuberculosis.util.DateUtil;
import com.tuberculosis.util.LongDescComparator;
import org.springside.modules.test.data.RandomData;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserData {

	public static User randomNewUser() {
		User user = new User();
		user.setLoginName(RandomData.randomName("user"));
		user.setName(RandomData.randomName("User"));
		user.setPlainPassword(RandomData.randomName("password"));

		return user;
	}

	public static void main(String[] args) {
//		int a = 10;
//		int b = 100;
//
//		System.out.println((double)a/b);
//
//		Date d = null;
//		try {
//			d = DateUtil.parse2("2015-12-11");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		System.out.println(DateUtil.monthOffset(d, 1));
//
//		Date d1 = new Date();
//		Timestamp t = new Timestamp(d1.getTime());
//
//		if(t.after(d1)) System.out.println("t");
//		if(d1.after(t)) System.out.println("d1");
//
//		if(t.before(d1)) System.out.println("t");
//		if(d1.before(t)) System.out.println("d1");
//
//
//		String mx = "M923X";
//		String mx1 = "M3X";
//		System.out.println(mx.substring(0, mx.lastIndexOf("X")));
//		System.out.println(mx1.substring(0, mx1.lastIndexOf("X")));

//		List<Long> ll = new ArrayList();
//		for(long i = 1; i< 15; i++) {
//			ll.add(i);
//		}
//
//		Collections.sort(ll, new LongDescComparator());
//
//		for(Long l:ll) {
//			System.out.println(l);
//		}


		double d = Math.round((double)2/7 * 1000) /10.0;

		System.out.println(d);

		System.out.println("aaa");
		System.out.println((double)2/7 * 1000);
		System.out.println(3/10);
	}
}
