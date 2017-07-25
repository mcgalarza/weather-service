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
		String weatherQuery = "https://query.yahooapis.com/v1/public/yql?q=select item.condition from weather.forecast where woeid = " + woeid + "&format=json";
    	String weatherStr = restTemplate.getForObject(weatherQuery, String.class);
    	JSONObject weatherJson = new JSONObject(weatherStr);
    	JSONObject condition = weatherJson.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition");
    	String date = condition.getString("date");
    	Integer temp = condition.getInt("temp");
    	String text = condition.getString("text");
    	Weather weather = new Weather(woeid, date, temp, text);
    	
    	return weather;
	}
	
	public Long findWoeid(String city) throws JSONException {
		String woeidQuery = "https://query.yahooapis.com/v1/public/yql?q=select woeid from geo.places(1) where text='" + city + "'&format=json";
        String woeidStr = restTemplate.getForObject(woeidQuery, String.class);
        JSONObject woeidJson = new JSONObject(woeidStr);
        Long woeid = woeidJson.getJSONObject("query").getJSONObject("results").getJSONObject("place").getLong("woeid");
        
        return woeid;
	}
	
}
