<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bibliotheque.models.Livre" %>

<% 
    Livre livre = (Livre) request.getAttribute("livre");
    boolean isEdit = livre != null;
%>

<form:form method="post" action="${pageContext.request.contextPath}/livre/save" modelAttribute="livre">
    <form:input path="id" type="hidden" />

    <label>Titre :</label>
    <form:input path="titre" placeholder="Titre du livre" /><br/>

    <label>Edition :</label>
    <form:input path="edition" placeholder="Edition" /><br/>

    <label>Auteur :</label>
    <form:select path="auteur.id">
        <form:option value="">Selectionner un auteur</form:option>
        <c:forEach items="${auteurs}" var="auteur">
            <form:option value="${auteur.id}">${auteur.nom}</form:option>
        </c:forEach>
    </form:select><br/>

    <label>Tag :</label>
    <form:input path="tag" placeholder="Tag" /><br/>

    <label>Genre litteraire :</label>
    <form:select path="genreLitteraire.id">
        <form:option value="">Selectionner un genre litteraire</form:option>
        <c:forEach items="${genresLitteraires}" var="genre">
            <form:option value="${genre.id}">${genre.libelle}</form:option>
        </c:forEach>
    </form:select><br/>

    <label>Age Requis :</label>
    <form:input path="ageRequis" type="number" placeholder="Âge requis" /><br/>

    <label>Maison d'edition :</label>
    <form:select path="maisonEdition.id">
        <form:option value="">Selectionner une maison d'edition</form:option>
        <c:forEach items="${maisonsEdition}" var="maison">
            <form:option value="${maison.id}">${maison.nom}</form:option>
        </c:forEach>
    </form:select><br/>

    <button type="submit">Enregistrer</button>
</form:form>