package com.facebook.android;

import item.Item;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.facebook.android.YelpApi2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Yelp {

	   String consumerKey = "d5UvXF0Ej0c67qtGQJkMgg";
	   String consumerSecret = "yL2AyVWdp8tNnbdI0WzTMJGe6Mg";
	   String token = "6wYcO_Ptv2AGaNFob0eOXjs5w71jbMzP";
	   String tokenSecret = "cDG1Mfz4eDyaLU6rDiB3lHwoD4I";
	   static OAuthService service;
	   static Token accessToken;
	   ArrayList<Item> items;
	   
	   public Yelp() {
		   this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
		   this.accessToken = new Token(token, tokenSecret);
		}
	   
	   public String search(String category_name, double latitude, double longitude) {
		    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		    request.addQuerystringParameter("ll", latitude + "," + longitude);
		    request.addQuerystringParameter("category_filter" , category_name);
		    this.service.signRequest(this.accessToken, request);
		    Response response = request.send();
		    return response.getBody();
		  }
	   
	   public JSONArray getList(String category_name, double latitude, double longitude){
		    items = new ArrayList<Item>();
		    
		    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		    request.addQuerystringParameter("category_filter", category_name);
		    request.addQuerystringParameter("ll", latitude + "," + longitude);
		    request.addQuerystringParameter("radius_filter","2000");
		    request.addQuerystringParameter("limit","10");
		    this.service.signRequest(this.accessToken, request);
		    Response response = request.send();
		   
		    try {
		    	JSONObject obj = new JSONObject(response.getBody());
				JSONArray entries = (JSONArray) obj.get("businesses");
				return entries;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	   }

}
