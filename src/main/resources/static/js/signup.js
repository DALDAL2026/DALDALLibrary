// 1. 이메일 인증번호 전송 요청
function sendCode() {
    const emailInput = document.querySelector("input[name='dalMe']");
    const email = emailInput ? emailInput.value : "";

    if (!email) {
        alert("이메일을 입력해주세요.");
        emailInput.focus();
        return;
    }

    // 컨트롤러 주소(/guest/sendAuthCode)에 맞추고 폼 데이터(x-www-form-urlencoded)로 전송
    fetch('/guest/sendAuthCode', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: 'dalMe=' + encodeURIComponent(email)
    })
    .then(response => response.text()) // Controller가 문자열("success"/"fail")을 반환하므로 text()로 받음
    .then(result => {
        if (result === "success") {
            alert("인증번호가 전송되었습니다. 이메일을 확인해주세요.");
            document.getElementById("authRow").style.display = ""; // 숨겨진 인증번호 입력창 표시
        } else {
            alert("인증번호 전송에 실패했습니다. 이메일을 다시 확인해주세요.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("통신 중 오류가 발생했습니다.");
    });
}

// 2. 인증번호 확인 요청
function verifyCode() {
    const emailInput = document.querySelector("input[name='dalMe']");
    const email = emailInput ? emailInput.value : "";
    const code = document.getElementById("authCode").value;

    if (!code) {
        alert("인증번호를 입력해주세요.");
        return;
    }

    // 컨트롤러 주소(/guest/verifyAuthCode)에 맞추고 폼 데이터로 전송
    fetch('/guest/verifyAuthCode', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: 'dalMe=' + encodeURIComponent(email) + '&code=' + encodeURIComponent(code)
    })
    .then(response => response.json()) // Controller가 boolean(true/false)을 반환하므로 json()으로 받음
    .then(isValid => {
        if (isValid === true) {
            alert("이메일 인증이 완료되었습니다!");
            document.getElementById("isEmailVerified").value = "Y"; // hidden 값 변경
            emailInput.readOnly = true; // 이메일 수정 불가 처리
            document.getElementById("authCode").readOnly = true; // 인증번호 입력창 수정 불가 처리
        } else {
            alert("인증번호가 틀렸거나 만료되었습니다.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("통신 중 오류가 발생했습니다.");
    });
}

// 3. 최종 회원가입 버튼 클릭 시 검증 함수
function check() {
    const isEmailVerified = document.getElementById("isEmailVerified").value;

    if (isEmailVerified !== "Y") {
        alert("이메일 인증을 완료해주세요.");
        return false; 
    }

    const pwd = document.querySelector("input[name='dalMpwd']").value;
    const pwd2 = document.querySelector("input[name='dalMpwd2']").value;

    if (pwd !== pwd2) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }

    return true; 
}