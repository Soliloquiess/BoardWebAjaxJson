<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
	<title>Login</title>
</head>
<body>
<br>
<br>
	<div class="container">
		<div class="jumbotron">
	  		<h1 class="display-4">삐따기 세상</h1>
	  		<p class="lead">새로운 생각, 새로운 사람과 함께 합니다.</p>
		</div>
		<h2>Login</h2>
	  	<form>
		    <div class="form-group">
				<label class="sr-only" for="userEmail">Email</label>
				<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="Email" value="user1mymail.com">
			</div>
			<div class="form-group">
				<label class="sr-only" for="userPassword">Password</label>
				<input type="password" class="form-control" id="userPassword" name="userPassword"  placeholder="Password" value="pws1">
			</div>
		</form>
		<div>
			<button id="btnLogin" class="btn btn-primary">로그인</button>
			<a href="<%=contextPath%>/jsp/register.jsp" class="btn btn-success">회원가입</a>
		</div>	
	    
	</div>

<script>
$(document).ready(function(){
	$("#btnLogin").click(function(){

		if( validate() ){
			login();
		}
	});
});

function validate(){
	var isUserEmailValid = false;
	var isUserPasswordValid = false;

	var userEmailValue = $("#userEmail").val();
	var userEmailLength = userEmailValue.length;
	
	if( userEmailLength > 0 ){
		isUserEmailValid = true;
	}
	
	var userPasswordValue = $("#userPassword").val();
	var userPasswordLength = userPasswordValue.length;
	
	if( userPasswordLength > 0 ){
		isUserPasswordValid = true;
	}

	if(	isUserEmailValid && isUserPasswordValid ){
		return true;
	}else{
		return false;
	}
}



// pure javascript ajax
// no alertify
var httpRequest;

function login(){
	httpRequest= new XMLHttpRequest();
	httpRequest.onreadystatechange = loginResult;
	
	var userEmail = document.getElementById("userEmail").value;
	var userPassword = document.getElementById("userPassword").value;
	
	httpRequest.open('POST', '<%=contextPath%>/login', true);
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.send("userEmail=" + userEmail + "&userPassword=" + userPassword);
}

function loginResult(){
	if (httpRequest.readyState === XMLHttpRequest.DONE) {
		if (httpRequest.status === 200) {
			
			// Before Learn JSON
			// var result = httpRequest.responseText;
			
			// After Learn JSON
			var data = JSON.parse(httpRequest.responseText);
			if( data.result == "success" ){
				window.location.href="<%=contextPath%>/board/boardMain";
			}else if( data.result == "fail" ){
				alert("아이디 또는 비밀번호가 올바르지 않습니다.");
			}
		}else{
	        alert("서버에 문제가 발생했습니다.");
		}
	}
}
</script>
</body>
</html>