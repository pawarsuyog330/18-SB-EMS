<%@taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<c:if test="${message ne null}">
  <c:out value="${message}"/>
</c:if>
<hr>
1. <a  href="addEmployee"> add employee </a> <br> <br>
2. <a  href="listEmployees"> list employees </a>

<br> <br>
<form action="logoutMe" method="post">
  <input type="submit"  value="LOGOUT"/>
</form>


