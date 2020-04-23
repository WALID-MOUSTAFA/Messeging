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



$(document).ready(function(){
	
	stompClient.connect({}, function(msg) {
		stompClient.subscribe("/topic/chat", function(msg){
			var messageBody= JSON.parse(msg.body).body;
			var messageUsername= JSON.parse(msg.body).username;
			appendMessage(messageUsername, messageBody, "div.messages-area")
		});
		
	}); //stompClient.connect

	$("form.new-message-form").on("submit", function(event){
		event.preventDefault();
		var data = $(this).serializeArray()[0];

		stompClient.send("/chat/message",{}, JSON.stringify({
			body: data.value
		}));
		
		$(this).trigger("reset");
		
	});

});



// $(document).ready(function(){

// 	getOldMessages("/socks/oldmessages", function (data, err){
// 		if(err) {
// 			console.log(err);
// 			alert(err);
// 		}else{
// 			data.map(function(message, index){
// 				//console.log(message);
// 				appendMessage(message.username, message.message_body, "div.messages-area");
// 			});
// 		}
// 	});
	
// 	client.onmessage = function(e){

// 		var parsedMsg= getMessageBodyAsJSON(e.data);

// 		/******************************************/
// 		/*     handle online statys              */
// 		/****************************************/
		
// 		if(parsedMsg.online){
// 			var members = $("div.members-area > .member");
// 			members.each(function(){
// 				var member_username= $(this).children(".member-name")[0].innerText;
// 				var status= $(this).children(".member-status");
				
// 				if(parsedMsg.online.includes(member_username)){
// 					if(status.children(".available").length == 0){
// 						status.append("<p data-username='" +member_username+ "'  class='available'>available</p>")
// 					}
// 				}else{
// 					$("p.available").each(function(){
// 						if($(this).data("username") == member_username){
// 							$(this).remove();
// 						}
// 					});
// 				}
// 			});
// 		}

// 		/******************************************/
// 		/*     handle chat messages              */
// 		/****************************************/

// 		if(parsedMsg.message){
// 			appendMessage(parsedMsg.username, parsedMsg.message, "div.messages-area");
// 		}
		
		
// 	} //end onmessage

// 	client.onerror = function(err){
// 		console.log(err);
// 		document.body.innerHTML= "<h1> 500, unable to reach socket ";
// 	}

	
// }); //end $(document).ready()

