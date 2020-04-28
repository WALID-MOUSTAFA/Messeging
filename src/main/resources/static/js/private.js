var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);


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



stompClient.connect({}, function(msg) {	

	
	stompClient.subscribe("/user/topic/chat.private", function(msg){
		var messageBody= JSON.parse(msg.body).body;
		var messageUsername= JSON.parse(msg.body).username;
		appendMessage(messageUsername, messageBody, "div.messages-area")
		seakEndMessagesArea();
	}); //end subscribe("/topic/chat").


}); //end stompClient.connect


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


$(document).ready(function(){
	
	$("form.new-message-form").on("submit", function(event){
		
		event.preventDefault();
		var data = $(this).serializeArray()[0];
		var target_username= $(this).data("target-username");
		
		stompClient.send("/chat/message.private/" + target_username, {}, JSON.stringify({
			body: data.value
		}));
		
		$(this).trigger("reset");
		
	});

	fetchMessages("/private-messages/"+$("div.info").data("target"), function(err, data) {
		for(var i= 0; i < data.length; ++i) {
			appendMessage(data[i].sender.username, data[i].message.body, "div.messages-area");
		}
		seakEndMessagesArea();
	});
	

	
}); //end $(document).ready.
