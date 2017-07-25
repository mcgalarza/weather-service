package com.redbee.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.redbee.model.Location;
import com.redbee.model.Weather;
import com.redbee.repository.BoardRepository;
import com.redbee.repository.LocationRepository;
import com.redbee.repository.WeatherRepository;
import com.redbee.service.YahooWeatherLookupService;

@RestController
@RequestMapping("boards/{board}")
public class LocationController {
	
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	YahooWeatherLookupService yahooWeatherLookupService;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Location> list(@PathVariable String board) throws BoardNotFoundException {
		this.validateBoard(board);
		return locationRepository.findByBoardName(board);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Location create(@PathVariable String board, @RequestBody Location location) throws BoardNotFoundException, UnsupportedEncodingException, JSONException, InterruptedException {
		this.validateBoard(board);
		location.setBoard(boardRepository.findByName(board));
		Long woeid = yahooWeatherLookupService.findWoeid(location.getName());
        if (weatherRepository.exists(woeid)) {
        	location.setWeather(weatherRepository.findOne(woeid));
        } else {
        	Weather weather = yahooWeatherLookupService.findWeather(woeid);
        	location.setWeather(weather);
        	weatherRepository.saveAndFlush(weather);
        }
		return locationRepository.saveAndFlush(location);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Location delete(@PathVariable String board, @PathVariable Long id) throws BoardNotFoundException {
		this.validateBoard(board);
		Location existingLocation = locationRepository.findOne(id);
		locationRepository.delete(existingLocation);
		return existingLocation;
	}
	
	private void validateBoard(String board) throws BoardNotFoundException {
		if (this.boardRepository.findByName(board) == null)
			throw new BoardNotFoundException(board);
	}

}
