<%@ include file="./header.jspf"%>

<h3>Existing users please login here</h3>

<form method="POST">
	<div class="form-group row">
		<label for="email" class="col-md-4">Email Address</label>
		<div class="col-md-8">
			<input type="email" class="form-control" name="email" id="email" value=${param.email}>
			<div class="text-danger">${errors.email}</div>
		</div>
	</div>
	<div class="form-group row">
		<label for="password" class="col-md-4">Password</label>
		<div class="col-md-8">
			<input type="password" class="form-control" name="password"
				id="password">
			<div class="text-danger">${errors.password}</div>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-md-4"></label>
		<div class="col-md-8">
			<button class="btn btn-primary">Login</button>
			<input type="reset" class="btn btn-link text-decoration-none"
				value="Reset">
		</div>
	</div>
</form>


<%@ include file="./footer.jspf"%>