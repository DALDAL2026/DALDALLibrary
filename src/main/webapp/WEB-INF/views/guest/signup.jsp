<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script language="javascript">
function goPopup(){
	var pop = window.open("/guest/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(dalMzipno,dalMaddr1,dalMaddr2){
	document.memberSignup.dalMzipno.value = dalMzipno;
	document.memberSignup.dalMaddr1.value = dalMaddr1
	document.memberSignup.dalMaddr2.value = dalMaddr2
}

</script>
</head>
<body>
	<div class="headTitle">
		DALDAL 도서관 회원가입
	</div>
	
	<div class="signupTable">
		<div class="signupNotice"></div>
		<form name="memberSignup" method="post" action="/member/signup">
			<!-- 이메일 인증 완료 여부를 담을 hidden 태그 (보안 핵심) -->
			<input type="hidden" name="isEmailVerified" id="isEmailVerified" value="N">
			<table border="1" width="900">
				<tr>
					<th>이메일 <span class="checkPoint">*</span></th>
					<td><input type="text" name="dalMe" placeholder="이메일을 정확하게 입력해주세요"></td>
					<td><input type="button" onclick="sendCode()" value="이메일 인증" class="confirmButton"></td> <!-- 이메일 인증 API -->
				</tr>
				<tr id="authRow" style="display:none;">
					<th>인증번호 입력</th>
					<td><input type="text" id="authCode" placeholder="6자리 입력"></td>
					<td><input type="button" value="인증 확인" class="confirmButton" onclick="verifyCode()"></td>
				</tr>

				<tr>
					<th>비밀번호 <span class="checkPoint">*</span></th>
					<td colspan="3"><input type="password" name="dalMpwd" placeholder="비밀번호는 영문대소문자와 숫자 특수문자(!,@,#,$,%,^,&,*)로 8~12자리 사이로 작성해주세요"></td>
				</tr>
				<tr>
					<th>비밀번호 확인 <span class="checkPoint">*</span></th>
					<td colspan="3"><input type="password" name="dalMpwd2" placeholder="비밀번호 확인을 위해 정확히 입력해주세요"></td>
				</tr>
				<tr>
					<th>이름 <span class="checkPoint">*</span></th>
					<td colspan="3"><input type="text" name="dalMname" placeholder="이름을 입력해주세요"></td>
				</tr>
				<tr>
					<th>닉네임 <span class="checkPoint">*</span></th>
					<td><input type="text" name="dalMnick" placeholder="닉네임은 영문대소문자, 한글로만 입력해주세요."></td>
					<td><input type="button" onclick="#" value="중복확인" class="confirmButton"></td>
				</tr>
				<tr>
					<th>성별 <span class="checkPoint">*</span></th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<th>연락처 <span class="checkPoint">*</span></th>
					<td colspan="3"><input type="text" name="dalMtel" placeholder="예시) 010-xxxx-xxxx"></td>
				</tr>
				<tr>
					<th rowspan="2">주소 <span class="checkPoint">*</span></th>
					<td colspan="2"><input type="button" value="주소 검색" class="adressButton" onclick="goPopup()"></td> <!-- 주소 검색 API -->
				</tr>
				<tr>
					<td colspan="2">
						<input type="text" name="dalMzipno" readonly placeholder="우편번호"> <br>
						<input type="text" name="dalMaddr1" readonly placeholder="상세주소"> <br>
						<input type="text" name="dalMaddr2" readonly placeholder="상세주소">
					</td>
				</tr>
				<tr>
					<th>자기소개</th>
					<td colspan="3"><textarea placeholder="자기소개를 입력해주세요." name="dalMprof" class="MyContent"></textarea></td>
				</tr>
			</table>
			<input type="submit" value="회원가입" class="submitButton" onclick="return check();">
			<input type="reset" value="초기화" class="resetButton">
		</form>
	</div>
	<script src="/js/signup.js"></script>
</body>
</html>