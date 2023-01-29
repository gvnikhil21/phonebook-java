<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>List of all your contacts</h3>
<hr>

<div class="row">
	<c:forEach items="${requestScope.contacts}" var="c">
		<div class="card col-md-3">
			<img class="card-img-top"
				src="${c.avatar==''?'./assets/images/default-profile.jpg':c.avatar}"
				alt="${c.firstname} ${c.lastname}" style="max-height: 250px">
			<div class="card-body text-center">
				<h5 class="card-title">${c.firstname} ${c.lastname}</h5>
				<p class="card-text">${c.email} ${c.phone}</p>
				<a href="./view-contact-details?id=${c.id}"
					class="btn btn-link text-decoration-none">View Details</a>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="./footer.jspf"%>