var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);
var privateMessageArea= $("div.private-popup");

/*****************************************************************************/

function appendMessage(user, message, messagesElement)
{
	
	var messageTemplate = "<div class='message'> <p class='member-from inline'> {{ user }} \
		               </p> <span>:</span> <p class='message-body inline'>  \
		               {{message}}  \
                               </p> </div>";

	var data= { user: user, message: message };
	$(messagesElement).append(Mustache.render(messageTemplate, data ));	
} //appendMEssage


function seakEndMessagesArea() { $(".messages-area").scrollTop($(".messages-area")[0].scrollHeight); }


function fetchMessages(url, callback) {
	$.ajax({
		url: url,
		method: "get",
		
		success: function(data) {
			callback(null, data);
		},
		
		error: function(request) {
			callback(request, null);
		}
	});
}


stompClient.connect({}, function(msg) {
	
	stompClient.subscribe("/topic/chat.online-users", function(msg) {
		var users= JSON.parse(msg.body).users;
		if(users){
			var members = $("div.members-area > .member");
			members.each(function(){
				var member_username= $(this).children(".member-name")[0].innerText;
				var status= $(this).children(".member-status");
				
				if(users.includes(member_username)){
					if(status.children(".available").length == 0){
						status.append("<p data-username='" +member_username+ "'  class='available'>available</p>")
					}
				}else{
					$("p.available").each(function(){
						if($(this).data("username") == member_username){
							$(this).remove();
						}
					});
				}
			});
		}

	}); //end subscribe("/topic/chat.online-users").

	
	stompClient.subscribe("/topic/chat", function(msg){
		var messageBody= JSON.parse(msg.body).body;
		var messageUsername= JSON.parse(msg.body).username;
		appendMessage(messageUsername, messageBody, "div.messages-area")
		seakEndMessagesArea();
	}); //end subscribe("/topic/chat").

	
	stompClient.subscribe("/user/topic/chat.private", function(msg){
		// var messageBody= JSON.parse(msg.body).body;
		var messageSender= JSON.parse(msg.body).username;
		
		privateMessageArea.append(Mustache.render("<h4> {{username}} sent you a message, click <a href='/private/{{username}}'> open </a> to open </h4> ", {username: messageSender}));
		
	}); //end subscribe("/topic/chat").



}); //end stompClient.connect


$(document).ready(function(){
	
	$("form.new-message-form").on("submit", function(event){
		event.preventDefault();
		var data = $(this).serializeArray()[0];

		stompClient.send("/chat/message",{}, JSON.stringify({
			body: data.value
		}));
		
		$(this).trigger("reset");
		
	});


	fetchMessages("/public-messages", function(err, data) {

		var reversedData= data.reverse();
		for(var i= 0; i < reversedData.length; ++i) {
			appendMessage(reversedData[i].sender.username, reversedData[i].message.body, "div.messages-area");
		}
		seakEndMessagesArea();
	});
	

}); //end $(document).ready.
