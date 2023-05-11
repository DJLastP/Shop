//초기 작업 실행
init();


//-- 함수 선언--//


//로그인 함수
function login(){
	const memId = document.querySelector('#loginModal #memId').value;
	const memPw = document.querySelector('#loginModal #memPw').value;
	$.ajax({
		url: '/member/login', //요청경로
		type: 'post',
		data: {'memId': memId, 'memPw': memPw}, //필요한 데이터
		success: function(result) {
			
			if(result == 'sucess'){
				
				location.href = '/';
			}
			else{
				const error_div =  document.querySelector('#errorDiv');
				if(error_div != null) {
					error_div.remove(); 
				}
					
				let str = '';
				str += '<div id="errorDiv" style="font-size: 0.7rem; color: red;">';
	      		str += '로그인 정보를 확인하세요';
	      		str += '	</div>';
				
				const login_error_div =  document.querySelector('#loginErrorDiv'); 
				login_error_div.insertAdjacentHTML('beforeend', str);
				document.querySelectorAll(
					'#loginModal input:not([type="button"])').forEach(function(t, index){
					t.value = '';
				});
			}
		},
		error: function() {
			alert('실패');
		}
	});
}

//회원가입
function join(){
	const isValid = joinValidate(); 
	if(!isValid){
		return ;
	}
	//회원가입 진행
	document.querySelector('#joinForm').submit();
}

  // 오류메세지를 전부 삭제
   function deleteErrorDiv(){
   const errorDivs =  document.querySelectorAll('div[class="my-invalid"]');
   		for(const errorDiv of errorDivs){
      			errorDiv.remove();      
   		}
}

//회원 가입 진행 시 데이터 유효성 검사
function joinValidate() {
     deleteErrorDiv();
     let str_memId = '';
     let str_memPw = '';
     let str_memPwConfirm = '';
     let result_memId = true;
     let result_memPw = true;
     let result_memPwConfirm = true;
	  // ID 유효성 검사
	  const memId = document.querySelector('#joinModal #memId').value;
	     if (memId == '') {
	          str_memId = '아이디는 필수입력입니다.';
	          result_memId = false;
	  } else if (memId.length < 3) {
	          str_memId = '아이디는 3글자 이상이여야 합니다.';
	        result_memId = false;
	  }
	     // 비밀번호 유효성 검사
	     const memPw1 = document.querySelector('#joinModal #memPw1').value;
	     const memPw2 = document.querySelector('#joinModal #memPw2').value;
	     const regExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}$/;
	  if (memPw1 == '') {
	      str_memPw = '비밀번호는 필수입력입니다.';
	      result_memPw = false;
	  } else if (memPw1.match(regExp) == null) {
	      str_memPw = '문자와 숫자가 조합된 4~12글자를 입력하세요.';
	      result_memPw = false;
	  }
	
	  // 비밀번호 재입력 확인
	  if (memPw2 == '') {
	      str_memPwConfirm = '비밀번호는 필수입력입니다.';
	      result_memPwConfirm = false;
	  } else if (memPw1 !== memPw2) {
	      str_memPwConfirm = '비밀번호가 일치하지 않습니다.';
	      result_memPwConfirm = false;
	  }
	
	  // 아이디 유효성 검사 실패 시 오류 메세지 출력
	  if (!result_memId) {
	      const errorHTML = `<div class="my-invalid">${str_memId}</div>`;
	      const memIdInput = document.querySelector('#joinModal #memId');
	      memIdInput.insertAdjacentHTML('afterend', errorHTML);
	  }
	
	  // 비밀번호 유효성 검사 실패 시 오류 메세지 출력
	  if (!result_memPw) {
	      const errorHTML = `<div class="my-invalid">${str_memPw}</div>`;
	      const memPwInput = document.querySelector('#joinModal #memPw1');
	      memPwInput.insertAdjacentHTML('afterend', errorHTML);
	  }
	
	  // 비밀번호 재입력 확인 실패 시 오류 메세지 출력
	  if (!result_memPwConfirm) {
	      const errorHTML = `<div class="my-invalid">${str_memPwConfirm}</div>`;
	      const memPwConfirmInput = document.querySelector('#joinModal #memPw2');
	      memPwConfirmInput.insertAdjacentHTML('afterend', errorHTML);
	  }
	  return result_memId && result_memPw && result_memPwConfirm;
}

function init(){
	//로그인 모달창이 닫힐 때 마다 실행되는 이벤트
	const loginModal = document.querySelector('#loginModal');
	loginModal.addEventListener('hidden.bs.modal', function(e){
		document.querySelector('#loginForm').reset();
		
	const error_div =  document.querySelector('#errorDiv');
	if(error_div != null) {
		error_div.remove(); 
	}		
	});
	
	//조인 모달창이 닫힐 때 마다 실행되는 이벤트
	const joinModal = document.querySelector('#joinModal');
	joinModal.addEventListener('hidden.bs.modal', function(e){
	document.querySelector('#joinForm').reset();
	});
	
}

//아이디 중복 체크
function checkId(){
	const checkId = document.querySelector('#joinModal #memId');
	if(checkId.value == ''){
		alert('ID를 입력하세요');
		return;
	}
	$.ajax({
		url: '/member/checkId', //요청경로
		type: 'post',
		async: false, 								//동기방식으로 실행
		data: {'memId': checkId.value}, //필요한 데이터
		success: function(result) {
			if(result == 1){
				alert('이미 사용중인 ID입니다.');
				checkId.value = "";
			}
			else{
				alert('사용가능한 ID입니다.');
				document.querySelector('#joinBtn').disabled = false;
			}
		},
		error: function() {
			alert('실패');
		}
	});
}
//회원가입버튼 비활성화
function setDisabled(){
	document.querySelector('#joinBtn').disabled = true;
}
//주소검색
function getAddr(){
	new daum.Postcode({
            oncomplete: function(data) {
                let addr = ''; // 주소 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
               document.querySelector('#joinModal #memAddr').value = addr;
        }
    }).open();
}


//비밀번호 찾기 Ajax
function findPw(btn){
	const memId = document.querySelector('#memIdforFindPw').value;
	const memName = document.querySelector('#memNameforFindPw').value;
	
	const spinner = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
	
	btn.disabled = true;
	btn.innerText = 'Loading...';
	btn.insertAdjacentHTML('afterbegin', spinner);
	
	
	$.ajax({
		url: '/member/findPwAjax', //요청경로
		type: 'post',
		data: {'memId': memId, 'memName': memName}, //필요한 데이터
		success: function(result) {
			btn.disabled = false;
			btn.innerText = '비밀번호 찾기';
			btn.replacechild();
			location.href='/';
		},
		error: function() {
			alert('실패');
		}
	});
}



