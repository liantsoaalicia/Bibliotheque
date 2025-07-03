<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" action="${pageContext.request.contextPath}/pret/save" modelAttribute="pret">

    <label>Date d'emprunt :</label>
    <form:input path="dateEmprunt" type="date" /><br/>

    <label>Adherant :</label>
    <form:select path="adherant.id">
        <form:option value="">Selectionner un adherant</form:option>
        <c:forEach items="${adherants}" var="adherant">
            <form:option value="${adherant.id}">${adherant.nom}</form:option>
        </c:forEach>
    </form:select><br/>

    <label>Exemplaire :</label>
    <form:select path="exemplaire.id">
        <form:option value="">Selectionner un exemplaire</form:option>
        <c:forEach items="${exemplaires}" var="exemplaire">
            <form:option value="${exemplaire.id}">${exemplaire.numExemplaire}</form:option>
        </c:forEach>
    </form:select><br/>

    <label>Type de pret :</label>
    <form:select path="typePret.id">
        <c:forEach items="${typePrets}" var="type">
            <form:option value="${type.id}">${type.libelle}</form:option>
        </c:forEach>
    </form:select><br/>

    <button type="submit">Enregistrer</button>
</form:form>

<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>