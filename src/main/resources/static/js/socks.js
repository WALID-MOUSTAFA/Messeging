var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);


/*****************************************************************************/

function appendMessage(user, message, MessagesElement)
{
	
	var messageEl = "<div class='message'>";

	messageEl += "<p class='member-from inline'>" + user+
		"</p> <span>:</span> <p class='message-body inline'>"
		+message+ "</p>";

	messageEl += "</div>";

	$(MessagesElement).append(messageEl);	
} //appendMEssage


function seakEndMessagesArea() { $(".messages-area").scrollTop($(".messages-area")[0].scrollHeight); }


function fetchUsers(url, callback) {
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


}); //end stompClient.connect


$(document).ready(function(){

	fetchUsers("/users", function(err, data) {
		if(err != null) {
			console.log(err);
			//TODO: proper error handling;
			return;
		}else {
			
			
			for(var i=0; i < data.length; ++i) {
				var member= '<div class="member grid-x"> \
			                     <p class="member-name small-6">' + data[i].username +  '</p> \
				             <p class="member-status small-6"></p> \
				             </div>';
				$(".members-area").append(member);
			}
		}

	}); //end fetchUser


	$("form.new-message-form").on("submit", function(event){
		event.preventDefault();
		var data = $(this).serializeArray()[0];

		stompClient.send("/chat/message",{}, JSON.stringify({
			body: data.value
		}));
		
		$(this).trigger("reset");
		
	});

}); //end $(document).ready.
