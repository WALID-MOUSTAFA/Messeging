<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>${title != null? title : 'document'}</title>
	<link href="/static/css/foundation.css" rel="stylesheet"/>
	<link href="/static/css/login.css" rel="stylesheet"/>
	
    </head>
    <body>
	
	<div class="grid-container">
	    
	    <div class="login-wrapper"> 
		<form:form method="post" class="log-in-form" modelAttribute="loginForm">
		    <p class="h4 text-center">Log in with you email account</p>

		    <div class="form-errors">
			<form:errors path="username"  cssClass="validate-error" element="div"/> 
			<form:errors path="password"  cssClass="validate-error" element="div"/> 
		    </div>

		    <label>Username
			<input autofocus required name="username" type="text" placeholder="username">
		    </label>
		    <label>Password
			<input required name="password" type="password" placeholder="Password">
		    </label>
		    <!-- <input id="show-password" type="checkbox"><label for="show-password">Show password</label> -->
		    <p><input type="submit" class="button expanded" value="Log in"></input></p>
		    <!-- <p class="text-center"><a href="#">Forgot your password?</a></p> -->
		</form:form>
	    </div>
	    
	</div>


	
	
	<script src="/static/js/jquery.min.js">  </script>
 	<script src="/static/js/foundation.js"></script>
	<script src="/static/js/login.js"></script>
    </body>
</html>

