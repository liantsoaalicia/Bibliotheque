<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Prets en cours</h2>
<table width=1500 border="1">
    <tr>
        <th>Livre</th>
        <th>Date de pret</th>
        <th>Date a laquelle il faut retourner le livre</th>
    </tr>

    <c:forEach items="${prets}" var="pret">
        <tr>
            <td>${pret.exemplaire.livre.titre}</td>
            <td>${pret.dateEmprunt}</td>
            <td>
                <form:form method="post" action="${pageContext.request.contextPath}/pret/prolonger/${pret.id}">
                    <label>Date a laquelle tu fais le prolongement</label>
                    <input type="date" name="dateJourProlongement">
                    <button type="submit">Prolonger</button>
                </form:form>
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