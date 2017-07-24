package com.redbee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.redbee.model.Weather;
import com.redbee.repository.WeatherRepository;
import com.redbee.service.YahooWeatherLookupService;

@Component
public class AppRunner implements CommandLineRunner {
	private final YahooWeatherLookupService yahooWeatherLookupService;
	private ExecutorService executor;
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	public AppRunner(YahooWeatherLookupService yahooWeatherLookupService) {
		this.yahooWeatherLookupService = yahooWeatherLookupService;
		this.executor = Executors.newFixedThreadPool(10);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}
	
	@Scheduled(fixedRate=3600*1000)
	public void doSomething() {
		List<Weather> weathers = new ArrayList<>();
		weathers = weatherRepository.findAll();
		
		for (Weather weather : weathers) {
			executor.execute(new LookupTask(weather));
		}
	}
	
	private class LookupTask implements Runnable {

        private Weather weather;

        public LookupTask(Weather weather) {
            this.weather = weather;
        }

        public void run() {
        	Weather w = new Weather();
			try {
				w = yahooWeatherLookupService.findWeather(weather.getWoeid());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weatherRepository.saveAndFlush(w);
        }

    }
}