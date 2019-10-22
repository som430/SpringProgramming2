package com.mycompany.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.web.dto.Ch10Board;
import com.mycompany.web.dto.Ch10Member;
import com.mycompany.web.service.Ch10Service;
import com.mycompany.web.service.LoginResult;

@Controller
@RequestMapping("/ch10")
public class Ch10Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch10Controller.class);
	
	//@Autowired
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@RequestMapping("/content")
	public String content() {
		return "ch10/content";
	}
	
	/*
	@RequestMapping("/connTest")
	public void connTest(HttpServletResponse response){
		boolean result = false;
		
		try {
			//Connection Pool에서 연결된 Connection 대여
			Connection conn = dataSource.getConnection();
			if(conn != null) result = true;
			
			//Connection Pool로 Connection을 반납
			conn.close();	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		try {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", result);
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@RequestMapping("/getMember")
	public String getMember(String mid, Model model) {
		//mid가 PK이기때문에 하나의 행만 가져옴
		Ch10Member member = sqlSessionTemplate.selectOne("member.selectMemberByMid",mid);//(xml파일의 mapper namespace .select id값, 매개변수)
		model.addAttribute("member", member);
		return "ch10/getMember";
	}*/
	
	@Autowired
	private Ch10Service service;
	
	@RequestMapping("/boardList")
	public String boardList(Model model, @RequestParam(defaultValue="1")int pageNo, HttpSession session) {
		session.setAttribute("pageNo", pageNo);
		
		//페이지당 행 수 
		int rowsPerPage = 10;
		//이전, 다음을 클릭했을 때 나오는 페이지 수 
		int pagesPerGroup = 5;
		//전제 게시물 수 
		int totalRowNum = service.getTotalRowNo();
		//전체 페이지 수 
		int totalPageNum = totalRowNum/rowsPerPage;
		if(totalRowNum % rowsPerPage != 0) totalPageNum++;
		//전제 그룹 수 
		int totalGroupNum = totalPageNum / pagesPerGroup;
		if(totalPageNum % pagesPerGroup != 0) totalGroupNum++;
		
		//현재 페이지의 그룹번호 
		int groupNo = (pageNo-1)/pagesPerGroup +1;
		//현재 그룹의 시작 페이지 번호 
		int startPageNo = (groupNo -1)*pagesPerGroup +1;
		//현재 그룹의 마지막 페이지 번호 
		int endPageNo = startPageNo + pagesPerGroup -1;
		if(groupNo == totalGroupNum) endPageNo = totalPageNum;
		
		//해당 페이지의 시작 행 번호 
		int startRowNo = (pageNo-1)*rowsPerPage + 1;
		//해당 페이지의 끝 행 번호 
		int endRowNo = pageNo*rowsPerPage;
		if(pageNo == totalPageNum) endRowNo = totalRowNum;
		
		//현재 페이지의 게시물 가져오기 
		List<Ch10Board> boardList = service.getBoardList(startRowNo, endRowNo);
		
		//JSP로 페이지 정보 넘기기
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("totalGroupNum", totalGroupNum);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("boardList", boardList);
		return "ch10/boardList";
	}
	
	@RequestMapping("/writeBoardForm")
	public String writeBoardForm(HttpSession session) {
		String mid = (String)session.getAttribute("mid");
		if(mid==null) return "redirect:/ch10/loginForm";
		return "ch10/writeBoardForm";
	}
	
	@RequestMapping("/writeBoard")
	public String writeBoard(Ch10Board board, HttpSession session) {
		board.setBwriter((String)session.getAttribute("mid"));
		service.writeBoard(board);
		return "redirect:/ch10/boardList";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(String error, Model model) {
		if(error != null) {
			if(error.equals("fail_mid")) {
				model.addAttribute("midError", "*아이디가 존재하지 않습니다.");
			}else if(error.equals("fail_mpassword")) {
				model.addAttribute("mpasswordError", "*비밀번호가 일치하지 않습니다.");
			}
		}
		return "ch10/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		LoginResult result = service.login(mid, mpassword);
		if(result == LoginResult.FAIL_MID) {
			return "redirect:/ch10/loginForm?error=fail_mid";	
		}else if(result == LoginResult.FAIL_MPASSWORD) {
			return "redirect:/ch10/loginForm?error=fail_mpassword";	
		}
		session.setAttribute("mid", mid);
		return "redirect:/ch10/boardList";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("mid");
		return "redirect:/ch10/boardList";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "ch10/joinForm";
	}
	
	@PostMapping("/join")
	public String join(Ch10Member member) {
		service.join(member);
		return "redirect:/ch10/loginForm";
	}
	
	@RequestMapping("/checkMid")
	public void checkMid(String mid, HttpServletResponse response) throws Exception {
		boolean result = service.checkMid(mid);
		response.setContentType("application/json charset=UTF-8");
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		pw.print(jsonObject.toString());
		pw.flush();
		pw.close();	
	}
	
	@RequestMapping("/boardDetail")
	public String boardDetail(int bno, Model model) {
		service.increaseHitcount(bno);
		Ch10Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch10/boardDetail";
	}
	
	@GetMapping("/updateBoard")
	public String updateBoardForm(int bno, Model model) {
		Ch10Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch10/updateBoardForm";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Ch10Board board, HttpSession session) {
		service.updateBoard(board);
		int pageNo = (Integer)session.getAttribute("pageNo");
		return "redirect:/ch10/boardList?pageNo="+pageNo;
	}
	
	@RequestMapping("/deleteBoard")
	public String deleteBoard(int bno, HttpSession session) {
		service.deleteBoard(bno);
		int pageNo = (Integer)session.getAttribute("pageNo");
		return "redirect:/ch10/boardList?pageNo="+pageNo;
	}

}
