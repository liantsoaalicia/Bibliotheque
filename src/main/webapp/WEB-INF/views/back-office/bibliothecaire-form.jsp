<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Connection Bibliothecaire</h2>

<form:form method="post" action="${pageContext.request.contextPath}/bibliothecaire/login" modelAttribute="bibliothecaire">
    <label>Email :</label>
    <form:input path="email" /><br/>

    <label>Mot de passe :</label>
    <form:password path="mdp" /><br/>

    <button type="submit">Se connecter</button>
</form:form>

