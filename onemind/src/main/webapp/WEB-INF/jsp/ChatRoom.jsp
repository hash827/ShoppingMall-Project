<%@ include file="IncludeTop.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
.roomContainer {
	height: 300px;
	overflow-x: hidden;
	overflow-y: scroll;
}

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

#roomList th, #roomList td {
	width: 230px;
	text-align: center;
}
</style>

<script type="text/javascript">
	var ws;
	window.onload = function() {
		$(document).ready(function() {
			//선택된 state 확인 후 라디오 버튼에 checked 넣기
			var nowState = $('#nowChatRoomState').val();

			$('input:radio[name="chatRoomState"]').each(function() {
				if ($(this).val() === nowState) {
					$(this).attr('checked', true);
				}
			})
		});
		getRoom();
		createRoom();
	}

	function getRoom() {
		commonAjax('/getRoom', "", 'post', function(result) {
			createChatingRoom(result);
		});
	}

	function createRoom() {
		$("#createRoom").click(function() {
			var msg = {
				roomName : $('#roomName').val()
			};

			commonAjax('/createRoom', msg, 'post', function(result) {
				createChatingRoom(result);
			});

			$("#roomName").val("");
		});
	}

	function goRoom(number, name) {
		location.href = "/moveChating?roomName=" + name + "&" + "roomNumber="
				+ number;
	}

	function deleteRoom(number, name, un, shun) {
		location.href = "/deleteRoom?roomName=" + name + "&" + "roomNumber="
				+ number + "&" + "username=" + un + "&" + "shUsername=" + shun;
	}

	function createChatingRoom(res) {
		if (res != null) {
			 var state =  $('#nowChatRoomState').val();
	         var tag = "<tr><th class='num'>순서</th><th class='room'>방 이름</th><th class='product'>상품명</th><th class='who'>참가자</th><th class='go'></th><th class='go'></th></tr>";
	         res
	               .forEach(function(d, idx) {
	                  var rn = d.roomName.trim();
	                  var roomNumber = d.roomNumber;
	                  var s = d.secondhandId.trim();
	                  const words = rn.split(' ');
	                  var product = words[0];
	               
	                  var un = d.username.trim();
	                  var sUn = d.shUsername.trim();
	                  tag += "<tr>" + "<td class='num'>"
	                        + (idx + 1)
	                        + "</td>"
	                        + "<td class='room'>"
	                        + rn
	                        + "</td>"
	                        + "<td class='product'>" + product + "</td>";
	                        if (state == 'SEND') {
	                        	tag+= "<td class='who'>" + sUn + "</td>";
	                        }
	                        else {
	                        	tag += "<td class='who'>" + un + "</td>";
	                        }
	                        tag+= "<td class='go'><button type='button' onclick='goRoom(\""
	                        + roomNumber
	                        + "\", \""
	                        + rn
	                        + "\")'>참여</button></td>"
	                        + "<td class='go'><button type='button' onclick='deleteRoom(\""
	                        + roomNumber + "\", \"" + rn + "\", \"" + un
	                        + "\", \"" + sUn + "\")'>나가기</button></td>"
	                        + "</tr>";
	               });
	         $("#roomList").empty().append(tag);
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
			<form id="fChatRoomState"
				action="<c:url value="/shop/secondhandViewChattingList.do" />">
				<table>
					<tr>
						<td><label><input type="radio" name='chatRoomState'
								value='SEND' />보낸 채팅</label></td>
						<!--  checked='checked' -->
						<td>&nbsp;</td>
						<td><label><input type="radio" name='chatRoomState'
								value='RECEIVE' />받은 채팅</label></td>
						<td>&nbsp;</td>
						<td><input type="submit" value="정렬"></td>

					</tr>
				</table>
			</form>
			<input type="hidden" id="nowChatRoomState" value="${chatRoomState}" />
			<div id="roomContainer" class="roomContainer">
				<table id="roomList" class="table"></table>
			</div>
		</div>
	</div>
</div>