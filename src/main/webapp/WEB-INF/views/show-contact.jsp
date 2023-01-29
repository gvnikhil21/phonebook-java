<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Contact Details for id ${param.id}</h3>
<hr>

<c:choose>
	<c:when test="${requestScope.contact==null}">
		<h5 class="text-danger">Sorry! We didn't find any contact with id ${param.id}</h5>
	</c:when>

	<c:otherwise>
		<div class="row">
			<div class="col-md-5">
				<img class="img-thumbnail" src="${contact.avatar==''?'./assets/images/default-profile.jpg':contact.avatar}">
			</div>
			<div class="col-md-7">
				<table class="table">
					<tr>
						<td>Firstname</td>
						<td>${contact.firstname}</td>
					</tr>
					<tr>
						<td>Lastname</td>
						<td>${contact.lastname}</td>
					</tr>
					<tr>
						<td>Email Address</td>
						<td>${contact.email}</td>
					</tr>
					<tr>
						<td>Phone Number</td>
						<td>${contact.phone}</td>
					</tr>
					<tr>
						<td>Address</td>
						<td>${contact.address}</td>
					</tr>
					<tr>
						<td>City</td>
						<td>${contact.city}</td>
					</tr>
					<tr>
						<td>PIN</td>
						<td>${contact.pincode}</td>
					</tr>
					<tr>
						<td>State</td>
						<td>${contact.state}</td>
					</tr>
					<tr>
						<td>Country</td>
						<td>${contact.country}</td>
					</tr>
				</table>
				
				<a href="./edit-and-update-contact?id=${contact.id}" class="btn btn-primary">Edit Details</a>
				<a href="./delete-contact?id=${contact.id}" class="btn btn-danger">Delete Contact</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>


<%@ include file="./footer.jspf"%>