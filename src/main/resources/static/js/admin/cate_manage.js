
function regCategory() {
	
	//카테고리명이 빈 값인지 확인
	const cateName = $('#cateName');
	if(cateName.val() == ''){
		alert('카테고리명은 필수입니다.');
		return ;
	}
	
	//카테고리명 중복 확인
		if(checkCateName(cateName.val())){
			alert('카테고리명이 중복이예요. 다시입력하세요');
			cateName.val('');
		return ;
	}
	
	//ajax start
	$.ajax({
		url: '/admin/setCateListAjax', //요청경로
		type: 'post',
		data: {'cateName': cateName.val()}, //필요한 데이터
		success: function(result) {
			alert('카테고리 등록 완료');
			
			//카테고리 목록 데이터 다시 조회
			getCateListAjax();
			cateName.val('');
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


//카테고리 등록시 이름 중복 확인
function checkCateName(cateName){
		let isDuplicate = false;
		
		$.ajax({
		url: '/admin/checkCateNameAjax', //요청경로
		type: 'post',
		async: false, 								//동기방식으로 실행
		data: {'cateName': cateName}, //필요한 데이터
		success: function(result) {
			if(result == 1){
				isDuplicate = true;
			}
			else{
			}
		},
		error: function() {
			alert('실패');
		}
	});
	
	return isDuplicate;
}

//카테고리 등록 후 실행되는 목록 조회
function getCateListAjax(){
		$.ajax({
		url: '/admin/getCateListAjax', //요청경로
		type: 'post',
		async: false, 			//동기방식으로 실행
		data: {}, 				//필요한 데이터
		success: function(result) {
			const tbodyTag = document.querySelector('#cateListTable > tbody');
			tbodyTag.replaceChildren();
			
			//새롭게 조회된 카테고리 목록 데이터로 테이블 채워줌
			let str = '';
			for(let i = 0; i < result.length; i++){
				str += '<tr>';
				str += `<td>${i + 1}</td>`;
				str += `<td>${result[i].cateName}</td>`;
				str += `<td>`;
				str += `<div class="row">`;
				str += `<div class="form-check col-6">`;
				if(result[i].isUse == 'Y'){
					str += `<input type="radio" value="Y" name="${'isUse' + i}" checked 
							onchange="updateIsUse('${result[i].cateCode}', this.value);">사용중`;
					str += `</div>`;
					str += `<div class="form-check col-6">`;
					str += `<input type="radio" value="N" name="${'isUse' + i}"
							onchange="updateIsUse('${result[i].cateCode}', this.value);">미사용`;
					str += `</div>`;
				}
				else{
					str += `<input type="radio" value="Y" name="${'isUse' + i}"
							onchange="updateIsUse('${result[i].cateCode}', this.value);">사용중`;
					str += `</div>`;
					str += `<div class="form-check col-6">`;
					str += `<input type="radio" value="N" name="${'isUse' + i}"
							onchange="updateIsUse('${result[i].cateCode}', this.value);" checked>미사용`;
					str += `</div>`;
				}
				str += `</div>`;
				str += `</td>`;
				str += `<td>${result[i].orderNum}</td>`;
				str += `<td><input type="button" class="btn btn-primary" value="삭제" onclick="location.href='deleteCate?cateCode=${result[i].cateCode}'"></td>`;
				str += '</tr>';
			}
			tbodyTag.insertAdjacentHTML('afterbegin', str);
		},
		error: function() {
			alert('실패');
		}
	});
}

function updateIsUse(cateCode, isUse){
	$.ajax({
		url: '/admin/updateIsUseAjax', //요청경로
		type: 'post',
		data: {'cateCode': cateCode, 'isUse': isUse}, //필요한 데이터
		success: function(result) {
				alert('사용여부 변경성공')
				
		},
		error: function() {
			alert('실패');
		}
	});
	
}