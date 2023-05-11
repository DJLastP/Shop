//이미지 팝업 모달
const imgModal = new bootstrap.Modal('#imgModal');

//이미지명 클릭시 이미지 모달 띄움
function openImgModal(img, title){
	//모달창에 보여질 이미지
	const modalTag = document.querySelector('#imgModal');
	modalTag.querySelector('img').src = '/upload/' + img;
	
	//모달창 타이틀
	modalTag.querySelector('h1').textContent = title;
	//모달 오픈
	imgModal.show();
}

//상품 수정
function updateItemForm(itemCode){
	
	$.ajax({
		url: '/admin/updateItemFormAjax', //요청경로
		type: 'post',
		data: {'itemCode': itemCode}, //필요한 데이터
		success: function(result) {
			
			const updateitemCate = document.querySelector('#updateItemCate');
			updateitemCate.replaceChildren();
			for(const e of result['cateList']){
				updateitemCate.innerHTML += "<option value=" + e.cateName + ">" + e.cateName + "</option>"; 
			}
			
			//아이템코드
			$('#updateItemCode').val(result['item'].itemCode);
			//상품명 
			$('#updateItemName').val(result['item'].itemName);
			//판매가격
			$('#updateItemPrice').val(result['item'].itemPrice);
			//상품소개
			$('#updateItemIntro').text(result['item'].itemIntro);
			//상품 재고
			$('#updateItemStock').val(parseInt(result['item'].itemStock));
			//카테고리 선택
			$('#updateItemCate').val(result['item'].cateName).prop("selected",true);
			//상품상태
			$('#updateItemStatus').val(result['item'].itemStatus).prop("selected",true);
			
			let subImg = [];
			let mainImg = '';
			let attachedMain = '';
			let attachedSub = [];
			result['item'].imgList.forEach(function(e, index) {
  				if(e.isMain === 'Y') {
  				  mainImg = e.originFileName;
  				  attachedMain = e.attachedFileName;
				  } else {
				    subImg[index] = e.originFileName;
				    attachedSub[index] = e.attachedFileName;
				  }
			});
			$('#mainImgName').remove();
			$('.subImgName').remove();
			$('#mainImgTable').after('<tr id="mainImgName"><td colspan="2"><a href="javascript:void(0);" onclick="openImgModal(\''+ attachedMain + '\', \'' + mainImg + '\');">' + mainImg + '</td></tr>');
			subImg.forEach(function(e, index){
				$('#subImgTable').after('<tr class="subImgName"><td colspan="2"><a href="javascript:void(0);" onclick="openImgModal(\''+ attachedSub[index] + '\', \'' + e +  '\');">' + e + '</td></tr>');
				
			})
		},
		error: function() {
			alert('실패');
		}
	});
}

//카테고리 체크박스 부분선택
function searchCate(){
	
	const checkBoxDiv = document.querySelectorAll('#checkBoxDiv > input');
	const checkAll = document.querySelector('#checkAll');
	
	for(var i = 0; i < checkBoxDiv.length; i++){
		if(checkBoxDiv[i].checked){
			checkAll.checked = false;
		}
	}
}

//카테고리 체크박스 전체선택
function checkCate(){
	const checkBoxDiv = document.querySelectorAll('#checkBoxDiv > input');
	const checkAll = document.querySelector('#checkAll');
	if(checkAll.checked){
		for(var e of checkBoxDiv){
				e.checked = true;
		}
	}
}

function itemStatus(){
	const itemStatus = document.querySelector('#searchItemStatus');
	const itemStatusValue = itemStatus.options[itemStatus.selectedIndex].value;
	
	$.ajax({
		url: '/admin/searchItemStatusAjax', //요청경로
		type: 'post',
		data: {'itemStatus': itemStatusValue}, //필요한 데이터
		success: function(result) {
			const tbodyTag = document.querySelector('#itemListTable > tbody');
			tbodyTag.replaceChildren();
			
			let str = '';
			
			for(let i = 0; i < result.length; i++){
				str += '<tr>';
				str += `<td>${result.length - i}</td>`;
				str += `<td>${result[i].cateName}</td>`;
				str += `<td  onclick="updateItemForm('${result[i].itemCode}');">${result[i].itemName}</td>`;
				str += `<td>${result[i].itemStatus}</td>`;
				str += `<td>${result[i].itemStock}</td>`;
				str += '</tr>';
		}
			tbodyTag.insertAdjacentHTML('afterbegin', str);
			
		},
		error: function() {
			alert('실패');
		}
	});
}

function itemSearch(){
	const itemName = document.querySelector('#searchItemName').value;
	$.ajax({
		url: '/admin/searchItemListAjax', //요청경로
		type: 'post',
		data: {'itemName': itemName}, //필요한 데이터
		success: function(result) {
			const tbodyTag = document.querySelector('#itemListTable > tbody');
			tbodyTag.replaceChildren();
			
			let str = '';
			
			for(let i = 0; i < result.length; i++){
				str += '<tr>';
				str += `<td>${result.length - i}</td>`;
				str += `<td>${result[i].cateName}</td>`;
				str += `<td  onclick="updateItemForm('${result[i].itemCode}');">${result[i].itemName}</td>`;
				str += `<td>${result[i].itemStatus}</td>`;
				str += `<td>${result[i].itemStock}</td>`;
				str += '</tr>';
		}
			tbodyTag.insertAdjacentHTML('afterbegin', str);
			
		},
		error: function() {
			alert('실패');
		}
	});
}
