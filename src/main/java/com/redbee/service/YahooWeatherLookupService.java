package com.redbee.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.redbee.model.Weather;

@Service
public class YahooWeatherLookupService {

	private final RestTemplate restTemplate;
	
	public YahooWeatherLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public Weather findWeather(Long woeid) throws InterruptedException, JSONException {
		System.out.println("1");
		String weatherQuery = "https://query.yahooapis.com/v1/public/yql?q=select item.condition from weather.forecast where woeid = " + woeid + "&format=json";
		System.out.println("2 " + weatherQuery);
    	String weatherStr = restTemplate.getForObject(weatherQuery, String.class);
    	System.out.println("3 " + weatherStr);
    	JSONObject weatherJson = new JSONObject(weatherStr);
    	System.out.println("4 " + weatherJson.toString());
    	JSONObject condition = weatherJson.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition");
    	System.out.println("5 " + condition.toString());
    	String date = condition.getString("date");
    	System.out.println("6 " + date);
    	Integer temp = condition.getInt("temp");
    	System.out.println("7 " + temp);
    	String text = condition.getString("text");
    	System.out.println("8 " + text);
    	Weather weather = new Weather(woeid, date, temp, text);
    	System.out.println("9 ");
    	
    	return weather;
	}
	
}
