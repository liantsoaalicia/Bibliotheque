<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table width=1500 border="1">
    <tr>
        <th>Date</th>
        <th>Libelle</th>
    </tr>
    <c:forEach items="${jourferies}" var="jourferie">
        <tr>
            <td>${jourferie.dateFerie}</td>
            <td>${jourferie.libelle}</td>
        </tr>
    </c:forEach>
</table>