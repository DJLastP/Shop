//side_menu_toggle();

//사이드메뉴 엑티브
function side_menu_toggle(){
	const aTag = document.querySelectorAll('#sideMenu > a');
	for(const b of aTag){
		b.classList.remove('active');
	}
	aTag[1].classList.add('active');
}


//전체펼치기,닫기
function toggle_all(btn){
	const status =  btn.dataset.toggleStatus;
	//속성값이 변경되어야 하는 모든 태그가 있는 div 태그 선택
	const all_div = document.querySelectorAll('.accordion-item');
	
	if(status == 'close'){
		btn.dataset.toggleStatus = 'open';
		btn.value ='전체 접기';
		btn.ariaExpanded = 'true';
		for(const item of all_div){
			item.querySelector('button').classList.remove('collapsed');
			item.querySelector('button').ariaExpanded = 'true';
			item.querySelector('div[class*="accordion-collapse"]').classList.add('show');
		}
	}
	if(status == 'open'){
		btn.dataset.toggleStatus = 'close';
		btn.value ='전체 펼치기';
		btn.ariaExpanded = 'false';
		for(const item of all_div){
			item.querySelector('button').classList.add('collapsed');
			item.querySelector('button').ariaExpanded = 'false';
			item.querySelector('div[class*="accordion-collapse"]').classList.remove('show');
		}
	}
}

//날짜별 검색 검색
function searchBuyList(month){
	let firstDate = document.querySelector('#firstDate').value;
	let lastDate = document.querySelector('#lastDate').value;
	if(month == 1){
		firstDate = setOneMonthAgo();
		lastDate = toDay();
	}
	else if (month == 3){
		firstDate = setThreeMonthAgo();
		lastDate = toDay();
	}
		$('#firstDate').val(firstDate);
		$('#lastDate').val(lastDate);
		$('#searchForm').submit();
}

//오늘날짜
function toDay(){
	const today = new Date();
	const formattedDate = `${today.getFullYear()}-${('0' + (today.getMonth() + 1)).slice(-2)}-${('0' + today.getDate()).slice(-2)}`;
	return formattedDate;
}

//오늘기준 3개월전 날짜
function setThreeMonthAgo(){
	const today = new Date();
	const threeMonthsAgo = new Date(today.getFullYear(), today.getMonth() - 3, today.getDate());
	const formattedDate = `${threeMonthsAgo.getFullYear()}-${('0' + (threeMonthsAgo.getMonth() + 1)).slice(-2)}-${('0' + threeMonthsAgo.getDate()).slice(-2)}`;
	return formattedDate;
}

//오늘기준 1개월전 날짜
function setOneMonthAgo(){
	const today = new Date();
	const oneMonthsAgo = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	const formattedDate = `${oneMonthsAgo.getFullYear()}-${('0' + (oneMonthsAgo.getMonth() + 1)).slice(-2)}-${('0' + oneMonthsAgo.getDate()).slice(-2)}`;
	return formattedDate;
}