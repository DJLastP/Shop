checkedPrice();
//side_menu_toggle();


//사이드메뉴 엑티브
function side_menu_toggle(){
	const aTag = document.querySelectorAll('#sideMenu > a');
	for(const b of aTag){
		b.classList.remove('active');
	}
	aTag[0].classList.add('active');
}
//선택구매
function buys(){
	const checked_checkboxes = document.querySelectorAll('.chk:checked');
		
	if(checked_checkboxes.length == 0){
		alert('구매할 상품을 선택하세요');
		return ;
	}
	
	
	//넘길 데이터
	const detail_info_arr = [];
	
	for(let i = 0; i < checked_checkboxes.length; i++){

		buy_detail_info = {
			'item_code' : checked_checkboxes[i].dataset.itemCode ,
			'buy_cnt' : checked_checkboxes[i].dataset.buyCnt,
			'buy_detail_price' : checked_checkboxes[i].dataset.detailBuyPrice
		};
		detail_info_arr[i] = buy_detail_info;
	}
		
	//총 구매 금액
		let final_price = document.querySelector('#checkedPrice').textContent;
		//숫자만 추출하는 정규식
		const regex = /[^0-9]/g;
		final_price = final_price.replace(regex, '');
		
		data = {
			'final_price' : final_price,
			'detail_info_arr' : detail_info_arr
		};
		
		$.ajax({
		url: '/buy/buysAjax', //요청경로
		type: 'post',
		async : true,
		contentType : 'application/json; charset=UTF-8',
		//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data: JSON.stringify(data), 

		success: function(result) {
			checkedDelete();
		},
		error: function() {
			alert('실패');
		}
	});
}

//수량변경 시 총 가격 변경
function totalPrice(itemPrice, index ,e){
	const cntTag = e.target;
	const result = itemPrice * cntTag.value;
	
	const a = document.querySelectorAll('#totalPriceTd > span');
	a[index].innerHTML = result;
	
	checkedPrice();
}

//전체체크박스선택시 전부 체크
function checkBox(){
	const checkBoxAll = document.querySelector('#cartCheckBoxAll');
	const checkBoxes = document.querySelectorAll('.cartCheckBox');
	
	if(checkBoxAll.checked){
		for(const e of checkBoxes){
			e.checked = true;
		}
	}
	else{
			for(const e of checkBoxes){
			e.checked = false;
		}
	}
	checkedPrice();
}
//체스박스 해제시 전체 체크박스 해제 
function checkBoxes(){
	const checkBoxAll = document.querySelector('#cartCheckBoxAll');
	const checkBoxes = document.querySelectorAll('.cartCheckBox');
	for(const e of checkBoxes){
		if(!e.checked){
			checkBoxAll.checked = false;
		}
		
	}
	checkedPrice();
}

//선택 삭제
function checkedDelete(){
	const checkBox = document.querySelectorAll('.cartCheckBox');
	const cartCode = document.querySelectorAll('.cartCodeInput');
	for(var i = 0; i < checkBox.length; i++){
		if(checkBox[i].checked){
			deleteCartItem(cartCode[i].value, i);
		}
	}
}


//장바구니상품 삭제 Ajax
function deleteCartItem(cartCode,index){
		//const a = e.target.parentElement.parentElement;
		const cartListTd = document.querySelectorAll('#cartListTd');
	
		$.ajax({
		url: '/cart/deleteCartItemAjax', //요청경로
		type: 'post',
		data: {'cartCode': cartCode}, //필요한 데이터
		success: function(result) {
			cartListTd[index].remove();
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
	checkedPrice();
}


//장바구니 상품 수량 수정
function updateCartCnt(cartCode, index){
		const a = document.querySelectorAll('#cartCnt');
		const cartCnt = a[index].value;
		
		$.ajax({
		url: '/cart/updateCartCntAjax', //요청경로
		type: 'post',
		data: {'cartCode': cartCode ,'cartCnt': cartCnt}, //필요한 데이터
		success: function(result) {

			
		},
		error: function() {
			alert('실패');
		}
	});
	checkedPrice();
}

//체크되어있는 상품가격의 합계
function checkedPrice(){
	const a = document.querySelectorAll('.cartCheckBox');
	const b = document.querySelectorAll('.itemPrice');
	const c = document.querySelectorAll('.cartCnt');
	let result = 0;
	for(let i = 0; i < a.length; i ++){
		if(a[i].checked){
			result += (parseInt(b[i].innerHTML) * parseInt(c[i].value));
		}
	}
	$('#checkedPrice').text('총 금액 : ' + result.toLocaleString() + '원');
}

//선택 구매
function buycheckedAjax(){
	const checkBox = document.querySelectorAll('.cartCheckBox');
	let buyPrice = 0;
	const a = document.querySelectorAll('.sumPrice');
	const cateCodeInput = document.querySelectorAll('.cartCodeInput')
	let cartCode = []; 
	
	for(let i = 0; i < checkBox.length; i++){
		if(checkBox[i].checked){
			buyPrice += parseInt(a[i].innerText);
			cartCode.push(cateCodeInput[i].value);
		}
	}

	//구매정보 Ajax
	$.ajax({
		url: '/cart/buySelectedItemAjax', //요청경로
		type: 'post',
		async: false,
		data: {'buyPrice': buyPrice, 'cartCodes': cartCode}, //필요한 데이터
		success: function(result) {
			checkedDelete();
		},
		error: function() {
			alert('실패');
		}
	});
}





