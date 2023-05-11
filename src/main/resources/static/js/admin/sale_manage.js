//html이 뜨자마자 ajax실행
getChartDataAjax();

//select box의 값 변경시 실행
function getStatistics(){
	const year = document.querySelector('#year').value;
	location.href = `/admin/saleStatusPerMonth?year=${year}`;
	
}

//차트를 그릴 데이터 가져오는 함수
function getChartDataAjax(){
	const year = document.querySelector('#year').value;
	$.ajax({
		url: '/admin/getChartDataAjax', //요청경로
		type: 'post',
		//async : true,
		//contentType : 'application/json; charset=UTF-8',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data: {'year': year}, 
		success: function(result) {
			drawChart(result);
		},
		error: function() {
			alert('실패');
		}
	});	
	
	
}

//차트 그리기
 function drawChart(result){
         
  //console.log(result);
  const a = result['cntList'];
  const b = result['salesList'];
  console.log(a);
  console.log(b);
  
  const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
 
      datasets: [
      {
         type:'bar', 
            label: '월별 판매금액',
           data: a,
           borderWidth: 3,
           yAxisID: 'y',
         },
            {
         type:'line', 
            label: '월별 판매건수',
           data: b,
           borderWidth: 3,
           yAxisID: 'y1',
         }
      ]
    },
    options: {
    responsive: true,
    interaction: {
      mode: 'index',
      intersect: false,
    },
    stacked: false,
    plugins: {
      title: {
        display: true,
        text: '월별 매출현황'
      }
    },
   
   
      scales: {
        y: {
          beginAtZero: true,
          type:'linear',
          display: true, 
          position:'left' 
         
        },
          y1: {
          beginAtZero: true,
          type:'linear',
          display: true, 
          position:'right' 
         
        }
        
      }
    }
  });

}





/*
setYearSelect();
	

function setYear(){
	const setYear = document.querySelector('#setYear').value;
	const subMenuCode = document.querySelector('#subMenuCode').value;
	
	location.href='/admin/saleStatusPerMonth?setYear=' + setYear 
	+ '&menuCode=MENU_003&subMenuCode=' + subMenuCode;
}

function setYearSelect(){
	const setYear = document.querySelector('#setY').value;
	
	if(setYear != null && setYear != ''){
		$('#setYear').val(setYear).prop("selected",true);
	}
	else{
		$('#setYear').val('2023').prop("selected",true);
	}

} 
*/
