<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>index</title>
	<link href="/static/css/foundation.css" rel="stylesheet"/>
	<link href="/static/css/socks.css" rel="stylesheet"/>
	
    </head>
    <body>
	
	
	<div class="grid-container main-container">
	    
	    <div class="chat">
		
		<div class="card">
		    <security:authorize access="isAuthenticated()">
			authenticated as <security:authentication property="principal" /> 
		    </security:authorize>
		    
		    <div class="card-section">

			<div class="main-grid grid-x">
			    
			    <div class="messages-area cell small-8 columns">
				<!-- <div class="message"> -->
				<!--     <p class="member-from inline">walid</p> <span>:</span> <p class="message-body inline">hello</p> -->
				<!-- </div> -->
				
				
			    </div>  <!-- messages-area --> 

			    <div class="new-message">
				<form class="new-message-form" action="" autocomplete="off">
				    <div class="input-group">
					<input autofocus class="input-group-field" name="message" type="text"  placeholder="type message!"/>
					<div class="input-group-button">
					    <button class="button">send</button>
					</div>
					
				    </div>
				</form>
			    </div> <!-- new message -->
			    
			    
			    <div class="members-area cell small-4 columns">

				<div class="members-title background-black background-primary">
				    <p class="h4 text-center text-white">Members List</p>
				</div>

				<!-- <div class="member grid-x">
				     <p class="member-name small-6">{{user.username}}</p>
				     <p class="member-status small-6"></p>
				     </div> -->
				
			    </div>  <!-- members-area -->

			</div> <!-- main-grid -->
			
			
		    </div> <!-- card-section -->


		</div> <!-- card -->
		
	    </div> <!-- chat --> 
	    
	</div> <!-- grid-container -->
	

	<div class="debug"></div>
	

	<script src="/static/js/jquery.min.js">  </script>
 	<script src="/static/js/foundation.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script src="/static/js/stomp.min.js"></script>
	 <script src="/static/js/socks.js"></script>
    </body>
</html>

