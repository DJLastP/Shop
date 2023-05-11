getSaleStatusByCategoryAjax();

//차트 그릴 Ajax
function getSaleStatusByCategoryAjax(){
	
	$.ajax({
		url: '/admin/saleStatusByCategoryAjax2', //요청경로
		type: 'post',
		//async : true,
		//contentType : 'application/json; charset=UTF-8',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data: {}, 
		success: function(result) {
			console.log(result)
			drawChart(result);
			drawTable(result);
		},
		error: function() {
			alert('실패');
		}
	});	
}

function drawTable(result){
	const tableDiv = document.querySelector('.tableDiv');
	
	//그려질 테이블 태그 작성
	let str = ``;
	str += `<table class="table text-center">`;
	str += `<thead>`;
	str += `<tr>`;
	str += `<td>No</td>`;
	str += `<td>카테고리</td>`;
	str += `<td>누적 판매 개수</td>`;
	str += `</tr>`;
	str += `</thead>`;
	str += `<tbody>`;
	for(let i = 0; i < result.length; i++){
		str += `<tr>`;
		str += `<td>${result.length - i}</td>`;
		str += `<td>${result[i]['CATE_NAME']}</td>`;
		str += `<td>${result[i]['SUM_BUY_CNT']}</td>`;
		str += `</tr>`;
	}
	str += `</tbody>`;
	str += `</table>`;
	
	//테이블이 들어갈 div 태그에 위에서 만든 코드 삽입
	tableDiv.insertAdjacentHTML('afterbegin', str);
}

 function drawChart(result){
	
	//data에 들어있는 모든 데이터중 cate_name 데이터만 추출
	const labels = [];
	const datas = [];
	
	result.forEach(function(item, index){
		labels.push(item['CATE_NAME']);
		datas.push(item['SUM_BUY_CNT']);
	});
	
	const ctx = document.getElementById('myChart');
	new Chart(ctx, {
		  type: 'pie',
		  data: {
			  labels: labels,
			  datasets: [
				  {
					  label: labels,
					  data: datas ,
					  //backgroundColor: Object.values(Utils.CHART_COLORS),
				  }
			  ]
		},
		  options: {
			  responsive: true,
			  plugins: {
				  legend: {
					  position: 'top',
				  },
				  title: {
					  display: true,
					  text: '카테고리 판매 추이'
				  }
			  } 
		  },
	  });
}
