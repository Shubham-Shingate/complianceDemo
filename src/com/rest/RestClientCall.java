package com.rest;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import com.dgsl.service.BACAMain;
import com.dgsl.service.Data;
import com.dgsl.service.KVPTable;
import com.dgsl.service.PageList;
import com.dgsl.service1.BACAMain1;
import com.dgsl.service1.Data1;
import com.dgsl.service1.KVPTable1;
import com.dgsl.service1.PageList1;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClientCall {
	int counter = 0;
	String DateInvoice;
	String AmountInvoice;
	String DateInsurance;
	String AmountInsurance;

	/* Get Content Analyzer ID */
	public String getanalyzerID(String filename) throws IOException {
		File inFile = new File("C:/Users/nikita.sawant/Desktop/Project1/" + filename);
		String encoding = Base64.getEncoder().encodeToString(("manisha.naik@datamatics.com:Password@123").getBytes());
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("multipart/form-data");

		RequestBody body = new MultipartBody.Builder().addFormDataPart("responseType", "\"utf8\",\"json\",\"pdf\"")
				.addFormDataPart("jsonOptions", "\"ocr\",\"dc\",\"kvp\"")
				.addFormDataPart("file", inFile.getName(), RequestBody.create(mediaType,
						new File("C:\\Users\\nikita.sawant\\Desktop\\Project1\\" + inFile.getName())))
				.build();

		Request request = new Request.Builder()
				.url("https://cademo5.bpm.ibmcloud.com/backendbacainst1/ca/rest/content/v1/contentAnalyzer").post(body)
				.addHeader("content-type", "multipart/form-data")
				.addHeader("apiKey", "4851ab56-34a9-4d15-bc7d-a71bee3757d3")
				.addHeader("Authorization", "Basic " + encoding).build();

		Response response = client.newCall(request).execute();
		String result = response.body().string();
		System.out.println(result);

		String analyzerId = result.substring(result.lastIndexOf("analyzerId") + 13,
				result.lastIndexOf("analyzerId") + 49);

		System.out.println("==================" + analyzerId);
		return analyzerId;
	}

	/* Get Json From Content Analyzer ID */
	public HashMap<String, String> getJsonDataInvoice(String analyzerId) throws IOException {
		OkHttpClient client1 = new OkHttpClient();

		String urls = "https://cademo5.bpm.ibmcloud.com/backendbacainst1/ca/rest/content/v1/contentAnalyzer/"
				+ analyzerId + "/json?attributes=KVPTable%2CBlockList";
		System.out.println(urls);
		System.out.println(urls);
		Request request1 = new Request.Builder().url(urls).get()
				.addHeader("apikey", "4851ab56-34a9-4d15-bc7d-a71bee3757d3")
				.addHeader("authorization", "Basic bWFuaXNoYS5uYWlrQGRhdGFtYXRpY3MuY29tOlBhc3N3b3JkQDEyMw==")
				.addHeader("cache-control", "no-cache").build();

		Response response1 = client1.newCall(request1).execute();

		String jsonData = response1.body().string();
		System.out.println(jsonData);
		jsonData = jsonData.replace("\"ExtraInformation\":{},", "");
		jsonData = jsonData.replace("\"MandatoryInformation\":{},", "");

		System.out.println(jsonData);

		ObjectMapper ob = new ObjectMapper();
		System.out.println("test one more time");
		ob.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
		ob.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		BACAMain mom = ob.readValue(jsonData, BACAMain.class);
		System.out.println(mom.getStatus().getMessage() + "======");

		if ("Content Not Found".equals(mom.getStatus().getMessage()) && counter < 10) {
			counter++;

			try {

				// sleep 5 seconds
				Thread.sleep(5000);

				System.out.println("Testing..." + new Date() + " Counter::: " + counter);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getJsonDataInvoice(analyzerId);
		}

		PageList[] pageList = mom.getData().getPageList();
		for (KVPTable kv : pageList[0].getKVPTable()) {
			if (kv.getKey().equals("Dated")) {
				DateInvoice = kv.getValue();
				System.out.println("Dated ::" + DateInvoice);
			} else if (kv.getKey().equals("Amount")) {
				AmountInvoice = kv.getValue();
				System.out.println("Amount" + AmountInvoice);
			}
		}

		HashMap<String, String> m = new HashMap<>();
		m.put("Date", DateInvoice);
		m.put("Amount", AmountInvoice);
		return m;

	}

	/* Get Json From Content Analyzer ID */
	public HashMap<String, String> getJsonDataInnsurance(String analyzerId) throws IOException, InterruptedException {
		OkHttpClient client1 = new OkHttpClient();

		String urls = "https://cademo5.bpm.ibmcloud.com/backendbacainst1/ca/rest/content/v1/contentAnalyzer/"
				+ analyzerId + "/json?attributes=KVPTable%2CBlockList";
		System.out.println(urls);
		System.out.println(urls);
		Request request1 = new Request.Builder().url(urls).get()
				.addHeader("apikey", "4851ab56-34a9-4d15-bc7d-a71bee3757d3")
				.addHeader("authorization", "Basic bWFuaXNoYS5uYWlrQGRhdGFtYXRpY3MuY29tOlBhc3N3b3JkQDEyMw==")
				.addHeader("cache-control", "no-cache").build();

		Response response1 = client1.newCall(request1).execute();

		String jsonData = response1.body().string();
		System.out.println(jsonData);
		ObjectMapper ob = new ObjectMapper();
		System.out.println("test one more time");
		ob.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
		ob.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		BACAMain1 mom = ob.readValue(jsonData, BACAMain1.class);
		System.out.println(mom.getStatus1().getMessage() + "======");

		if ("Content Not Found".equals(mom.getStatus1().getMessage()) && counter < 10) {
			counter++;
			// sleep 5 seconds
			Thread.sleep(5000);
			System.out.println("Testing..." + new Date() + " Counter::: " + counter);
			getJsonDataInnsurance(analyzerId);
		}

		PageList1[] pageList = mom.getData1().getPageList();
		for (KVPTable1 kv : pageList[0].getKVPTable1()) {
			if (kv.getKey().equals("Date")) {
				DateInsurance = kv.getValue();
				System.out.println("Message Output Reference::" + DateInsurance);
			} else if (kv.getKey().equals("Date")) {
				AmountInsurance = kv.getValue();
				System.out.println("Amount" + AmountInsurance);
			}
		}

		HashMap<String, String> m = new HashMap<>();
		m.put("Date", DateInsurance);
		m.put("Amount", AmountInsurance);
		return m;

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String filename = "commInvoice9.pdf";
		RestClientCall rc = new RestClientCall();
		String analyzerID = rc.getanalyzerID(filename);
		int counter = 0;
		if (counter < 10) {
			counter++;
			try {

				// sleep 5 seconds
				Thread.sleep(120000);

				System.out.println("Testing..." + new Date());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		rc.getJsonDataInnsurance(analyzerID);

	}
}
