package com.redbee.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.redbee.model.Board;
import com.redbee.repository.BoardRepository;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BoardControllerTest {
	
	@InjectMocks
	private BoardController boardController;
	
	@Mock
	private BoardRepository boardRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testBoardsList() {
		List<Board> boards = new ArrayList<>();
		Board board = new Board(1l, "board1");
		boards.add(board);
		
		when(boardRepository.findAll()).thenReturn(boards);
		
		List<Board> boardsList = boardController.list();
		
		verify(boardRepository).findAll();
		assertThat(boardsList.get(0).getId(), is(1l));
		assertThat(boardsList.get(0).getName(), is("board1"));
	}
	
	@Test
	public void testBoardsCreate() {
		Board board = new Board(2l, "board2");
		
		when(boardRepository.saveAndFlush(board)).thenReturn(board);
		
		Board createdBoard = boardController.create(board);
		
		verify(boardRepository).saveAndFlush(board);
		assertThat(createdBoard.getId(), is(2l));
		assertThat(createdBoard.getName(), is("board2"));
	}
	
}