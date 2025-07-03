<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table width=1500 border="1">
    <tr>
        <th>Adherent</th>
        <th>Date debut</th>
        <th>Date fin</th>
    </tr>
    <c:forEach items="${adherentpenalite}" var="adherentpenalite">
        <tr>
            <td>${adherentpenalite.adherant.nom}</td>
            <td>${adherentpenalite.dateDebut}</td>
            <td>${adherentpenalite.dateFin}</td>
        </tr>
    </c:forEach>
</table>