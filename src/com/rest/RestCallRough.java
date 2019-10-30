package com.rest;


import java.io.IOException;

import com.dgsl.service1.BACAMain1;
import com.dgsl.service1.Data1;
import com.dgsl.service1.KVPTable1;
import com.dgsl.service1.PageList1;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RestCallRough {

	public static void main(String[] args) throws IOException  {
		
		 
		OkHttpClient client1 = new OkHttpClient();
		String ContentAnalyzerID="af093f70-6b2d-11e9-b7df-2b522d96e673";
		
		String urls = "https://cademo5.bpm.ibmcloud.com/backendbacainst1/ca/rest/content/v1/contentAnalyzer/"+ContentAnalyzerID+"/json?attributes=KVPTable%2CBlockList";
		System.out.println(urls);
		Request request1 = new Request.Builder()
		  .url(urls)
		  .get()
		  .addHeader("apikey", "4851ab56-34a9-4d15-bc7d-a71bee3757d3")
		  .addHeader("authorization", "Basic bWFuaXNoYS5uYWlrQGRhdGFtYXRpY3MuY29tOlBhc3N3b3JkQDEyMw==")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response1 = client1.newCall(request1).execute();
		
		String jsonData = response1.body().string();
		System.out.println(jsonData);
		jsonData = jsonData.replace("\"ExtraInformation\":{},", "");
		System.out.println(jsonData);

		ObjectMapper ob = new ObjectMapper();
		System.out.println("test one more time");
		ob.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
		ob.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	   BACAMain1 mom = ob.readValue(jsonData, BACAMain1.class);
	   System.out.println(mom.getStatus1().getMessage()+"======");
	   
	   PageList1[] pageList=  mom.getData1().getPageList();
	   for(KVPTable1 kv : pageList[0].getKVPTable1()) {
		   if(kv.getKey().equals("Priority")) {
			   String fullName = kv.getValue();
			   System.out.println("Priority ::"+fullName);
		   }
	   }
		
		
	
}
}
