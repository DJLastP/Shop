totalPrice(document.querySelector('#itemPrice').value);

//총가격
function totalPrice(itemPrice){
	const itemStock = $('#buyCnt').val();
	const result = $('#totalPrice');
	
	result.text(itemStock * itemPrice + '원');
	document.querySelector('#buyPrice').value = itemStock * itemPrice;
}




//장바구니 클릭시 장바구니 이동 확인
function addCart(loginInfo, itemCode){
	
	if(loginInfo == 'anonymousUser'){
		const result = confirm('로그인 먼저 하셈\n로그인 하쉴?');
			if(result){
				const loginModal =  new bootstrap.Modal('#loginModal');
				loginModal.show();
			}
			return;
		}
		else{
			addCartAjax(itemCode);
		}
	}


//장바구니 추가
function addCartAjax(itemCode){
	
	const cartCnt = document.querySelector('#buyCnt').value;
	
	$.ajax({
		url: '/cart/addCartAjax', //요청경로
		type: 'post',
		data: {'cartCnt': cartCnt, 'itemCode' : itemCode}, //필요한 데이터
		success: function(result) {
			if(confirm('장바구니로 이동할까요?')){
				location.href = "/cart/itemCart";
			}
			else
			{
				location.href = "/";
			}
		},
		error: function() {
			alert('실패');
		}
	});
	
	
}

