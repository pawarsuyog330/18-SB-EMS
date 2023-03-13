<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>

<%@taglib uri="http://www.springframework.org/security/tags"  prefix="s" %>

<a  href="addEmployee"> add more employee </a> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<form action="logoutMe"  method="post">
   <input type="submit"  value="LOGOUT">
</form>

<br>
<br>


<table border=1>
  <tr>
    <th>empno</th>
    <th>ename</th>
    <th>sal</th>
    <th>deptno</th>
    <th>actions</th>
  </tr>    

  <c:if test="${!empty empModelList}">
  
     <c:forEach  items="${empModelList}"  var="emp">
        <tr>
          <td> <c:out value="${emp.empno}"/> </td>
          <td> <c:out value="${emp.ename}"/> </td>
          <td> <c:out value="${emp.sal}"/> </td>
          <td> <c:out value="${emp.deptno}"/> </td>
          <td> 
             <a href="editEmployee?id=${emp.empno}"> <img src="images/image_edit.png"  width="40" height="40"> </a>
              &nbsp; &nbsp; &nbsp; &nbsp;
              
            <s:authorize  access="hasRole('ROLE_MANAGER')">            
             <a href="deleteEmployee?id=${emp.empno}"> <img src="images/image_delete.png" width="40" height="40"> </a>
            </s:authorize>
                        
          </td>             
        </tr>
     </c:forEach>
  </c:if>

</table>