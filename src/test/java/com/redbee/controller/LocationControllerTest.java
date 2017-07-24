package com.redbee.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.redbee.model.Board;
import com.redbee.model.Location;
import com.redbee.repository.BoardRepository;
import com.redbee.repository.LocationRepository;
import com.redbee.repository.WeatherRepository;

public class LocationControllerTest {
	@InjectMocks
	private LocationController locationController;
	
	@Mock
	private LocationRepository locationRepository;
	@Mock
	private BoardRepository boardRepository;
	@Mock
	private WeatherRepository weatherRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testLocationsList() throws BoardNotFoundException {
		List<Location> locations = new ArrayList<>();
		Board board1 = new Board(1l, "martin");
		Location location1 = new Location(1l, "Buenos Aires");
		location1.setBoard(board1);
		locations.add(location1);
		
		when(locationRepository.findByBoardName("martin")).thenReturn(locations);
		when(boardRepository.findByName("martin")).thenReturn(board1);
		
		List<Location> locationsList = locationController.list("martin");
		
		verify(locationRepository).findByBoardName("martin");
		assertThat(locationsList.get(0).getId(), is(1l));
		assertThat(locationsList.get(0).getName(), is("Buenos Aires"));
	}
	
	@Test
	public void testLocationCreate() throws BoardNotFoundException, UnsupportedEncodingException, JSONException {
//		Board board1 = new Board(1l, "martin");
//		Location location = new Location(2l, "Goya");
//		location.setBoard(board1);
//		
//		when(boardRepository.findByName("martin")).thenReturn(board1);
//		when(locationRepository.saveAndFlush(location)).thenReturn(location);
//		
//		Location createdLocation = locationController.create("martin", location);
//		
//		verify(locationRepository).saveAndFlush(location);
//		assertThat(createdLocation.getId(), is(2l));
//		assertThat(createdLocation.getName(), is("Goya"));
	}
	
	@Test
	public void testLocationDelete() throws BoardNotFoundException {
		Board board1 = new Board(1l, "martin");
		Location location = new Location(1l, "Goya");
		
		when(boardRepository.findByName("martin")).thenReturn(board1);
		when(locationRepository.findOne(1l)).thenReturn(location);
		
		Location deletedLocation = locationController.delete("martin", 1l);
		
		verify(locationRepository).delete(location);
		assertThat(deletedLocation.getId(), is(1l));
		assertThat(deletedLocation.getName(), is("Goya"));
	}
	
}

