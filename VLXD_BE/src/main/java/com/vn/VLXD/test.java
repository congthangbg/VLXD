package com.vn.VLXD;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vn.VLXD.entities.Account;
import com.vn.VLXD.services.UserLogonService;

public class test {
	
	public static void printArr(int no ,int[] a) {
		System.out.printf("%d :",no);
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%d ",a[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> a = new ArrayList<>();
		a.add("nam");
		a.add("Thang");
		a.add("cuong");
		a.add("Tac");
		System.out.println("a"+a);
//		 Boolean b =  CollectionUtils.isEmpty(a);
		 String g = StringUtils.collectionToCommaDelimitedString(a);
		 System.out.println(g);
//		 String d = StringUtils.capitalize("nam");
		 String[] d = StringUtils.commaDelimitedListToStringArray(g);
		 System.out.println(d[1]);
		 
			List<String> asa = new ArrayList<>();
			asa.add("2,198,160");
			asa.add("2,200,160");
			
			
			int[] arr = {5,3,6,1,7,2,3};
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length - i -1; j++) {
					if(arr[j] > arr[j+1]) {
						int t = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = t;
					}
				}
				printArr(i,arr);
			}
			
	}

}
