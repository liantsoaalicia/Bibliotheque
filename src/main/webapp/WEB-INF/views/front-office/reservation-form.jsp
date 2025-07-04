<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Reserver un exemplaire</h2>

<form:form method="post" action="${pageContext.request.contextPath}/reservation/save" modelAttribute="reservation">
    <form:errors path="*" cssClass="text-danger"/>
    <label>Date de reservation :</label>
    <form:input path="dateDebut" type="date" /><br/>

    <label>Les exemplaires disponibles :</label>
    <form:select path="exemplaire.id">
        <form:option value="">Selectionner un exemplaire</form:option>
        <c:forEach items="${exemplaires}" var="exemplaire">
            <form:option value="${exemplaire.id}">${exemplaire.numExemplaire}</form:option>
        </c:forEach>
    </form:select><br/>

    <button type="submit">Reserver</button>
</form:form>

<c:if test="${not empty message}">
    <div class="alert alert-success" role="alert">
        ${message}
    </div>
</c:if>