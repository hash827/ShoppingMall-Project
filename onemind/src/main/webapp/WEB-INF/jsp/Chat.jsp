<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
.container h5 {
	padding: 5px 5px 5px 5px;
}

.chating {
	background-color: #F6F6F6;
	height: 500px;
	overflow-x: hidden;
	overflow-y: scroll;
}

.chating .me {
	padding-right: 10px;
	color: #000;
	text-align: right;
}

.chating .others {
	padding-left: 10px;
	color: #000;
	text-align: left;
}
.chating .mydateInfo {
	padding-right: 10px;
	color: #000;
	text-align: right;
	font-size: 5px;
}
.chating .otherdateInfo {
	padding-left: 10px;
	color: #000;
	text-align: left;
	font-size: 5px;
}
#chatting {
	width: 660px;
	height: 25px;
}
/* #cmpl {
	display: none;
} */

button {
	background-color: #007bff;
	font-size: 14px;
	color: #FFFFFF;
	border: 1px solid #FFFFFF;
	padding: 3px;
	margin: 3px;
}

#wrap {
	width: 1200px;
	height: 700px;
	margin: 0 auto;
}

#aside {
	width: 250px;
	height: 700px;
	float: left;
}

#contents {
	margin-top: 50px;
	margin-bottom: 100px;
	width: 950px;
	height: 700px;
	float: left;
}

#chatList th, #chatList td {
	width: 230px;
	text-align: center;
}
</style>

<script type="text/javascript">
	var ws;

	window.onload = function() {
		wsOpen();
		getChat();
	}

	function wsOpen() {
		//웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
		ws = new WebSocket("ws://" + location.host + "/chating/"
				+ $("#roomNumber").val());
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 동작
		}

		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if (msg != null && msg.trim() != '') {
				var d = JSON.parse(msg);
				if (d.type == "getId") {
					var si = d.sessionId != null ? d.sessionId : "";
					if (si != '') {
						$("#sessionId").val(si);
					}
				} else if (d.type == "message") {
					if (d.sessionId == $("#sessionId").val()) {
						$("#chating").append(
								"<p class='me'>나 :" +d.msg+"<p class='mydateInfo'>"+d.chatTime+"</p></p>");
					} else {
						//$("#chating").append("<p class='others'>" + d.userName + " :" + d.msg + "</p>");
						$("#chating").append(
								"<p class='others'>" + d.username + " :"+d.msg+"<p class='otherdateInfo'>"+d.chatTime+"</p></p>");
					}

				} else {
					console.warn("unknown type!")
				}
			}
			$("#chating").scrollTop($("#chating")[0].scrollHeight);
		}

		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
	}
	function getTime() {
		var d = new Date();
		var s = leadingZeros(d.getFullYear(),4)+"-"+
		leadingZeros(d.getMonth()+1,2)+"-"+
		leadingZeros(d.getDate(),2)+"\t"+
		
		leadingZeros(d.getHours(),2)+":"+
		leadingZeros(d.getMinutes(),2)+":"+
		leadingZeros(d.getSeconds(),2);
		
		return s;
	}
	function getTime2(d) {
		var s = d.toString();
		var st = s.split("T");
		var st2 = st[1].split(":");
		var hour = parseInt(st2[0])+9;
		var str = st[0]+"\t"+hour+":"+st2[1]+":"+st2[2].substr(0,2);
		
		return str;
	}
	function leadingZeros(n, digits) {
		var zero='';
		n = n.toString();
		if (n.length < digits) {
			for(i=0; i<digits-n.length; i++)
				zero += '0';
		}
		return zero+n;
	}
	function send() {
		var option = {
			type : "message",
			roomNumber : $("#roomNumber").val(),
			sessionId : $("#sessionId").val(),
			username : $("#username").val(),
			msg : $("#chatting").val(),
			chatTime : getTime()
		}
		ws.send(JSON.stringify(option))
		$.ajax({
			url : '/sendChat',
			dataType : 'json',
			type : 'POST',
			data : {
				chatting : $('#chatting').val(),
				chatTime : getTime()
			}
		});
		$('#chatting').val("");
	}

	function complete() {
		$.ajax({
			url : '/completeChat',
			dataType : 'json',
			type : 'POST',
			data : {
				roomNumber : $("#roomNumber").val()
			}
		});
		alert('거래가 완료되었습니다.');
		$('#complete').remove();
	}

	function getChat() {
		var shUserName = $("#shUsername").val();
		var username = $("#username").val();
		if (shUserName == username) {
			$("#cmpl").show();
		}
		commonAjax('/getChat', "", 'post', function(result) {
			createChat(result);
		});
	}

	function createChat(res) {
		if (res != null) {
			var tag;
			res.forEach(function(d) {
						var cc = d.chatContent;
						var fromId = d.fromId;
						var chatTime = getTime2(d.chatTime);
						if (fromId == $("#username").val()) {
							$("#chating").append(
									"<p class='me'>나 :" + cc +"<p class='mydateInfo'>"+ chatTime + "</p></p>");
						} else {
							$("#chating").append(
									"<p class='others'>" + fromId + " :" + cc+ "<p class='otherdateInfo'>"+chatTime+ "</p></p>");
						}
					});
			$("#chating").scrollTop($("#chating")[0].scrollHeight);
		}
	}

	function commonAjax(url, parameter, type, calbak, contentType) {
		$.ajax({
			url : url,
			data : parameter,
			type : type,
			contentType : contentType != null ? contentType
					: 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(res) {
				calbak(res);
			},
			error : function(err) {
				console.log('error');
				calbak(err);
			}
		});
	}
</script>
<div id="wrap">
	<div id="aside">
		<%@ include file="IncludeSideSecondhand.jsp"%>
	</div>
	<div id="contents">
		<div align="center">
			<div id="container" class="container">
				<h5>채팅방 이름: ${roomName}</h5>
				<c:choose>
					<c:when test="${shState eq 'COMPLETED'}">
						<%out.println("<script>alert('거래가 완료된 상품입니다.')</script>"); %>
					</c:when>
				</c:choose>
				<input type="hidden" id="sessionId" value=""> <input
					type="hidden" id="roomNumber" value="${roomNumber}"> <input
					type="hidden" id="username" value="${username}"><input
					type="hidden" id="shUsername" value="${shUsername}">

				<div id="chating" class="chating">
					<table id="chatList" class="chatList"></table>
				</div>
				<div id="yourMsg">
					<table class="inputTable">
						<tr>
							<th>메시지</th>
							<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
							<th><button onclick="send()" id="sendBtn">보내기</button></th>
							<c:if test="${shUsername eq username}">
							<th id="cmpl"><c:choose>
									<c:when test="${shState eq 'NOW'}">
										<button onclick="complete()" id="complete">거래 완료</button>
									</c:when>
									<c:when test="${shState eq 'RESERVE'}">
										<button onclick="complete()" id="complete">거래 완료</button>
									</c:when>
								</c:choose></th>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>