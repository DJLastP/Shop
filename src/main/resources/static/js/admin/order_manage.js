init();


//날짜순 검색 버튼
function searchByDate(){
	
	const searchForm = document.querySelector('#searchForm');
	const orderBy = document.querySelector('#orderBy');
	orderBy.value = 'date';
	searchForm.submit();
	
}

function searchByStatus(){
	
	const searchForm = document.querySelector('#searchForm');
	const orderBy = document.querySelector('#orderBy');
	orderBy.value = 'status';
	searchForm.submit();
	
}
//페이지버튼
function prevPage(page){
			const searchForm = document.querySelector('#searchForm');
			const nowPage = document.querySelector('#nowPage');
			nowPage.value = page - 1;
			searchForm.submit(); 
}

function nextPage(page){
		const searchForm = document.querySelector('#searchForm');
		const nowPage = document.querySelector('#nowPage');
		nowPage.value = page + 1;
		searchForm.submit(); 
}

function nowPage(page){
		const searchForm = document.querySelector('#searchForm');
		const nowPage = document.querySelector('#nowPage');
		nowPage.value = page;
		searchForm.submit(); 
}



//검색후 페이지이동시 체크박스 날짜 세팅
function init (){
	var fromDate = $('#fromDate').val();
	var toDate = $('#toDate').val();
	var searchBy = $('#searchBy');
	var searchByVal = $('#searchBy').val();
	var searchValue = $('#searchValue').val();
	var status = document.querySelectorAll('.status');
	var setCheckBox = document.querySelectorAll('.setCheckBox');
	
	//검색어 세팅
	document.querySelector('#setSearchValue').value = searchValue;
	//셀렉트박스 선택
	if(searchByVal != null && !searchByVal == ''){
		$("#setSearchBy").val(searchByVal).prop("selected", true);
	}

	//체크박스 선택
	for(const checkBox of setCheckBox){
		for(const b of status){
			if(checkBox.value == b.value)
				checkBox.checked = true;
		}
	}
	//날짜선택
		
	$('#setToDate').val(toDate);
	$('#setFromDate').val(fromDate);
	
}

	//검색체크박스컨트롤
function checkBoxControl(){
	const searchCheckBox = document.querySelector('#searchCheckBox');
	const searchCheckboxes = document.querySelectorAll('.setCheckBox');
	
	if(searchCheckBox.checked){
		for(const boxes of searchCheckboxes){
			boxes.checked = true;
		}
	}
	if(!searchCheckBox.checked){
		for(const boxes of searchCheckboxes){
			boxes.checked = false;
		}
	}
}
function checkBoxesControl(){
	
	const searchCheckBox = document.querySelector('#searchCheckBox');
	const searchCheckboxes = document.querySelectorAll('.setCheckBox');
		for(const boxes of searchCheckboxes){
		if(!boxes.checked)
			searchCheckBox.checked = false;
		}
	
}
	
//목록 체크박스 컨트롤
function statusCheckBoxControl(index){
	
	const checkBox = document.querySelector('#statusCheckbox'+index);
	const checkBoxes = document.querySelectorAll('.statusCheckBoxes'+index);
	
	if(checkBox.checked){
		for(const box of checkBoxes){
			box.checked = true;
		}
	}	
	if(!checkBox.checked){
		for(const box of checkBoxes){
			box.checked = false;
		}
	}	
}


function statusCheckBoxesControl(index){
	const checkBox = document.querySelector('#statusCheckbox'+index);
	const checkBoxes = document.querySelectorAll('.statusCheckBoxes'+index);
	
	for(const box of checkBoxes){
		if(!box.checked){
			checkBox.checked = false;
		}
	}
}

//상태변경버튼클릭시 선택된체크박스가없을때
function updateStatus(index){

	const checkBoxes = document.querySelectorAll('.statusCheckBoxes'+index);
	const updateStatusForm = document.querySelector('#updateStatusForm'+index);
	let checkBoxResult = 0;
	
	for(const box of checkBoxes){
		if(box.checked){
			checkBoxResult++;
		}
	}
	
	if(checkBoxResult == 0){
			alert('선택된 주문이없습니다.');
	}
	else{
			updateStatusForm.submit();
	}
}


//아이템상세정보 모달창 열기
function openModal(buyCode, memId) {
  var modal = new bootstrap.Modal(document.getElementById('itemDetail'), {
    keyboard: true
  });
  	//ajax start
	$.ajax({
		url: '/admin/buyDetailList', //요청경로
		type: 'post',
		//contentType : 'application/json; charset=UTF-8',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		async : true,
		data: {'buyCode': buyCode, 'memId': memId}, 
		success: function(result) {
		  
  		const buyDetailTable = document.querySelector('.buyDetailTable > tbody');
  		const buyDetailBody = document.querySelector('#buyDetailBody');
		const totalPrice = `<div class="col">총 가격 : ${result[0]['BUY_PRICE']}</div>`;
		const buyDate = `<div class="col">구매 일자 : ${result[0]['BUY_DATE']}</div>`;
		
		buyDetailTable.replaceChildren();		
		buyDetailBody.replaceChildren();		
		let str = "";
		for(const data of result){
			
			str +=`<tr>`;
			str +=`<td>${data['ITEM_NAME']}</td>`
			str +=`<td>${data['ITEM_PRICE']}</td>`
			str +=`<td>${data['BUY_CNT']}</td>`
			str +=`<td>${data['DETAIL_BUY_PRICE']}</td>`
			str +=`</tr>`;
			
		}
		
		buyDetailTable.insertAdjacentHTML('afterbegin', str);
		buyDetailBody.insertAdjacentHTML('afterbegin', totalPrice);
		buyDetailBody.insertAdjacentHTML('beforeend', buyDate);
		

		
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end

  
  modal.show();
}


