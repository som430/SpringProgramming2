<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.mycompany.web.dto.*"%>

<%
List<Ch05Board> boardList = (List<Ch05Board>)request.getAttribute("boardList");
%>

<table class="table table-sm">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">내용</th>
      <th scope="col">글쓴이</th>
      <th scope="col">날짜</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
	<%for(Ch05Board board : boardList){%>
	    <tr>
	      <th scope="row"><%=board.getBno()%></th>
	      <td><%=board.getBtitle()%></td>
	      <td><%=board.getBcontent()%></td>
	      <td><%=board.getWriter()%></td>
	      <td><%=board.getDate()%></td>
	      <td><%=board.getHitcount()%></td>
	    </tr>
	<%}%>
  </tbody>
</table>