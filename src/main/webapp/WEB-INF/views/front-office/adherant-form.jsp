<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Connection Adherent</h2>

<form:form method="post" action="${pageContext.request.contextPath}/adherant/login" modelAttribute="adherant">
    <label>Email :</label>
    <form:input path="email" /><br/>

    <label>Mot de passe :</label>
    <form:password path="mdp" /><br/>

    <button type="submit">Se connecter</button>
</form:form>

