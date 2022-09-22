<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="owners">
	<h2>${pet.name} Clinical History</h2>
	<p>
	 Name: <c:out value="${pet.name}"/><br>
     Birth Date: <petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/><br>
     Type: <c:out value="${pet.type.name}"/><br>
     Owner: <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
	</p>
	<table id="diagnosesTable" class="table table-striped">
		<thead>
			<tr>
				<th>Date</th>
				<th>Vet</th>				
				<th>Disease</th>
				<th>Vet comments</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${diagnoses}" var="diagnose">
				<tr>
					<td>${diagnose.visit.date}</td>
					<td>${diagnose.vet.firstName} ${diagnose.vet.lastName}</td>
					<td>${diagnose.disease.name}</td>
					<td>${diagnose.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>
