package com.mycompany.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.web.dao.Ch10BoardDao;
import com.mycompany.web.dto.Ch10Board;

@Service
public class Ch10Service {

	@Autowired
	private Ch10BoardDao boardDao;
	
	public List<Ch10Board> getBoardList(int startRowNo, int endRowNo) {
		List<Ch10Board> boardList = boardDao.selcetList(startRowNo, endRowNo);
		return boardList;
	}
	
	public void writeBoard(Ch10Board board) {
		boardDao.insert(board);
	}
	
	public int getTotalRowNo() {
		int totalRowNum = boardDao.selectTotalRowNo();
		return totalRowNum;
	}
}
