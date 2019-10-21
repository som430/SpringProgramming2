package com.mycompany.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.web.dto.Ch10Board;

@Component
public class Ch10BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Ch10Board> selcetList(int startRowNo, int endRowNo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNo", startRowNo);
		map.put("endRowNo", endRowNo);
		List<Ch10Board> boardList = sqlSessionTemplate.selectList("board.selectList", map);
		return boardList;
	}	
	
	public int selectTotalRowNo() {
		int totalRowNum = sqlSessionTemplate.selectOne("board.selectTotalRowNum");
		return totalRowNum;
	}
	
	public int insert(Ch10Board board) {
		int rows = sqlSessionTemplate.insert("board.insert", board); //return값 : 반영된 행의 수 
		return rows;
	}
}
