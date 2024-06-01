var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function select(str) {
	return document.querySelector(str);
}

function connect() {
	var socket = new SockJS('https://chatapp-zoa1.onrender.com/stomp-endpoint');
	var userName = select("#userName").value;
	console.log("----------------------------------------------",userName);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/sendToAll', function(greeting) {
//			showGreeting(JSON.parse(greeting.body));
			console.log("Subscribed greeting to :", userName);
		});
		stompClient.subscribe(`/user/${userName}/msg`, function(message) {
			console.log("Subscribed username to :", userName);
			console.log("This msg is sent by: ", message['body']);
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/app/sendToAll", {}, JSON.stringify({
		'name' : $("#name").val()
	}));
}

function sendToUser() {
	stompClient.send("/app/message", {}, JSON.stringify({
		'endpoint' : 'endpoint',
		'to' : select("#toUserName").value,
		'message' : $("#name").val()
	}));
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message.message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		console.log("Clicked send");
		sendName();
	});
	$("#sendToUser").click(function() {
		sendToUser();
	});
});
