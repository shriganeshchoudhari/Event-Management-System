<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="boostrap.jsp" %>

<title>Create Participant</title>
</head>
<body>
	<div class="container">
		<form method="post"
			action="${pageContext.request.contextPath}/docreate">
			<div class="form-group">
				
				<!-- table class="table">
	<tr>
		<td><label for="name">Name: </label></td><td><input name="name" type="text"/></td>
	</tr>
	<tr>
		<td><label for="email">Email: </label></td><td><input name="email" type="text"/></td>
	</tr>
	<tr>
		<td><label for="phone">Phone: </label></td><td><input name="phone" pattern="[0-9]{10}" type="text"/></td>
	</tr>
	<tr>
		<td><label for="zipcode">Zipcode: </label></td><td><input name="zipcode" type="text"/></td>
	</tr>
	</table> -->
				<div class="form-group row">
					<label for="example-text-input" class="col-xs-2 col-form-label">Name</label>
					<div class="col-xs-10">
						<input class="form-control" name="name" type="text">
					</div>
				</div>
				<div class="form-group row">
					<label for="example-email-input" class="col-xs-2 col-form-label">Email</label>
					<div class="col-xs-10">
						<input class="form-control"  name="email" type="email">
					</div>
				</div>
				<div class="form-group row">
					<label for="example-tel-input" class="col-xs-2 col-form-label">Phone</label>
					<div class="col-xs-10">
						<input class="form-control" name="phone" type="tel"
							placeholder="1-(555)-555-5555">
					</div>
				</div>
				<div class="form-group row">
					<label for="example-number-input" class="col-xs-2 col-form-label">Zipcode</label>
					<div class="col-xs-10">
						<input class="form-control" name="zipcode" sstype="number">
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-primary">Create
						participant</button>
					</td>
				</div>
				</div>
		</form>
	</div>
</body>
</html>