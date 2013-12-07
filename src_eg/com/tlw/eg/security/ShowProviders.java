package com.tlw.eg.security;

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;

/**
@since 2010-5-14
@version 2010-5-14
@author 唐力伟 (tlw_ray@163.com)
 */
public class ShowProviders {
	public static void main(String[] args) {
		Provider[] providers=Security.getProviders();
		for(int i=0;i<providers.length;i++){
			Provider provider=providers[i];
			System.out.println("------------"+provider.getName()+"-----------------");
			Iterator it=provider.keySet().iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
		}
	}

}
