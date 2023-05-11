

//contentType : 서버단으로 넘기는 데이터의 타입 지정
//default: "application/x-www-form-urlencoded; charset=UTF-8",
//많이 사용하는 타입 : application/json; charset=UTF-8


//default 방식 
function test1(){
	
		$.ajax({
		url: '/test/test1', //요청경로
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'name': 'java', 'age': 20, 'addr': '울산시'}, 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}

//json 방식
function test2(){
	
	data = {'name': 'java', 'age': 20, 'addr': '울산시'}

		$.ajax({
		url: '/test/test2', //요청경로
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(data), 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


// json방식 map으로 받기
function test3(){
	data = {'name': 'java', 'age': 20, 'addr': '울산시'}

		$.ajax({
		url: '/test/test3', //요청경로
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(data), 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


//자바스크립트의 객체 배열을 넘기고 받기
function test4(){
	
	dataArr = [];
	
	for(let i = 0; i < 3; i++){
		data = {'name': 'java' + i, 'age': 20  + i, 'addr': '울산시'  + i};
		dataArr[i] = data;
	}

		$.ajax({
		url: '/test/test4', //요청경로
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(dataArr), 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}

function test5(){
	dataArr = [];
	for(let i = 0; i < 3; i++){
		data = {'name': 'java' + i, 'age': 20  + i, 'addr': '울산시'  + i};
		dataArr[i] = data;
	}
		$.ajax({
		url: '/test/test5', //요청경로
		type: 'post',
		//contentType: 'application/json; charset=UTF-8',
		data: {'dataArr': dataArr}, 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


function test6(){
	
	dataArr = [];
	
	for(let i = 0; i < 3; i++){
		data = {'name': 'java' + i, 'age': 20  + i, 'addr': '울산시'  + i};
		dataArr[i] = data;

	}
		$.ajax({
		url: '/test/test6', //요청경로
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(dataArr), 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


function test7(){
	
	studentArr = [];
	
	for(let i = 0; i < 3; i++){
		student = {'name': 'java' + i, 'age': 20  + i, 'score': 50  + i};
		studentArr[i] = student;
	}
	
	class_info = {
		'teacher_name' : '홍길동'
		, 'class_name' : '자바반'
		, 'stu_info' : studentArr
	};
	
		$.ajax({
		url: '/test/test7', //요청경로
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(class_info), 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


function test8(){
	
	studentArr = [];
	
	for(let i = 0; i < 3; i++){
		student = {'name': 'java' + i, 'age': 20  + i, 'score': 50  + i};
		studentArr[i] = student;
	}
	
	classInfo = {
		'tName' : '홍길동'
		, 'cName' : '자바반'
		, 'stuInfo' : studentArr
	};
	
		$.ajax({
		url: '/test/test8', //요청경로
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(classInfo), 
		success: function(result) {
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}

