<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table width=1500 border=1> 
    <tr>
        <th>Id</th>
        <th>Titre</th>
        <th>Edition</th>
        <th>Auteur</th>
        <th>Tag</th>
        <th>Age Requis</th>
        <th>Maison d'edition</th>
        <th>Genre litteraire</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${livres}" var="livre">
        <tr>
            <td>${livre.id}</td>
            <td>${livre.titre}</td>
            <td>${livre.edition}</td>
            <td>${livre.auteur.nom}</td>
            <td>${livre.tag}</td>
            <td>${livre.ageRequis}</td>
            <td>${livre.maisonEdition.nom}</td>
            <td>${livre.genreLitteraire.libelle}</td>
            <td>
                <a href="${pageContext.request.contextPath}/livre/edit/${livre.id}">Modifier</a> |
                <a href="${pageContext.request.contextPath}/livre/delete/${livre.id}">Supprimer</a>
            </td>
        </tr>
    </c:forEach>

</table>