
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Jour ferie detecte</h2>
<p>La date de retour choisie (<b>${dateRetour}</b>) est un jour ferie</p>
<p>Veuillez choisir l'action a effectuer :</p>

<fmt:formatDate value="${dateRetour}" pattern="yyyy-MM-dd" var="dateRetourStr"/>
<form:form method="post" action="${pageContext.request.contextPath}/pret/return/${pret.id}">
    <input type="hidden" name="dateRetour" value="${dateRetourStr}" />
    <label>
        <input type="radio" name="choixJourFerie" value="veille" required />
        Retourner la veille
    </label><br/>
    <label>
        <input type="radio" name="choixJourFerie" value="lendemain" required />
        Retourner le lendemain
    </label><br/>
    <button type="submit">Valider le choix</button>
</form:form>

<a href="${pageContext.request.contextPath}/pret/list">Annuler</a>
