<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Reservations a valider</h2>

<table width=1500 border=1> 
    <tr>
        <th>Date debut</th>
        <th>Livre</th>
        <th>Exemplaire</th>
        <th>Adherent</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${reservations}" var="reservation">
        <tr>
            <td>${reservation.dateDebut}</td>
            <td>${reservation.exemplaire.livre.titre}</td>
            <td>${reservation.exemplaire.numExemplaire}</td>
            <td>${reservation.adherant.nom}</td>
            <td>
                <a href="${pageContext.request.contextPath}/reservation/valider/${reservation.id}" style="color:green;font-weight:bold;">Valider</a> |
                <a href="${pageContext.request.contextPath}/reservation/refuser/${reservation.id}" style="color:red;font-weight:bold;">Refuser</a>
            </td>
        </tr>
    </c:forEach>

</table>

<c:if test="${not empty message}">
    <div class="alert alert-success" role="alert">
        ${message}
    </div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>