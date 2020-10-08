<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
</head>

<body>

	<section id="content">
		 <form role="form" method="post" autocomplete="off">
			  <div class="input_area">
			   <label for="userId">아이디</label>
			   <input type="email" id="userId" name="userId" required="required" />      
			  </div>
			  
			  <div class="input_area">
			   <label for="userPass">패스워드</label>
			   <input type="password" id="userPass" name="userPass" required="required" />      
			  </div>
			       
			  <button type="submit" id="signin_btn" name="signin_btn">로그인</button>
			  
			  <c:if test="${msg == false}">
			   <p style="color:#f00;">로그인에 실패했습니다.</p>
			  </c:if>
		  
		 </form>   
	</section>



</body>
</html>
