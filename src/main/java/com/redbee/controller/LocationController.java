package com.redbee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.model.Location;
import com.redbee.repository.BoardRepository;
import com.redbee.repository.LocationRepository;

@RestController
@RequestMapping("boards/{board}")
public class LocationController {
	
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private LocationRepository locationRepository;
	
	@RequestMapping(value = "locations", method = RequestMethod.GET)
	public List<Location> list(@PathVariable String board) throws BoardNotFoundException {
		this.validateBoard(board);
		return locationRepository.findByBoardName(board);
	}
	
	@RequestMapping(value = "locations", method = RequestMethod.POST)
	public Location create(@PathVariable String board, @RequestBody Location location) throws BoardNotFoundException {
		this.validateBoard(board);
		return locationRepository.saveAndFlush(location);
	}
	
	@RequestMapping(value = "locations/{id}", method = RequestMethod.DELETE)
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
