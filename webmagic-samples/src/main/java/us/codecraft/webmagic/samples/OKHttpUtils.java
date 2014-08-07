package us.codecraft.webmagic.samples;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OKHttpUtils {
	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");

	static OkHttpClient client = new OkHttpClient();
	
	public static String get(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		String res = response.code()==200?response.body().string():"";
		return res;
	}
	
	
	public static String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		String res = response.code()==200?response.body().string():"";
		return res;
	}

	public static String  bowlingJson(String player1, String urls) {
//		return "{'user_id':'useridxxx','ablum_id':'aidxxxxxxxx'"+ 
//				"}";
		
//		   return "{\"user_id\":\"useridxxx\","
//	        + "\"ablum_id\":\"ablum_idablum_id\""
//	        + "}";
		   
//		   return "{\"user_id\":useridxxx,"
//	        + "\"ablum_id\":ablum_idablum_id"
//	        + "}";
		   
		   return "{\"category_id\":\"53a438406cc4cd870516b240\","
		   + "\"urls\":\""+urls+"\","
		   + "\"name\":\""+player1+"\""
		   + "}";
	}

}
