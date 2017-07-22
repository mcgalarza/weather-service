package com.redbee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.model.Board;
import com.redbee.repository.BoardRepository;

@RestController
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping(value = "boards", method = RequestMethod.GET)
	public List<Board> list() {
		return boardRepository.findAll();
	}
	
	@RequestMapping(value = "boards", method = RequestMethod.POST)
	public Board create(@RequestBody Board board) {
		return boardRepository.saveAndFlush(board);
	}
}
