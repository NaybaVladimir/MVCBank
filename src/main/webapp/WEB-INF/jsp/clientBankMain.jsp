<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Клиенты банка</title>
</head>
<body>
<h2>Клиенты банка:</h2>
<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>address</th>
        <th>age</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="client" items="${allClient}">
        <tr>
            <td>${client.id}</td>
            <td><a href="/bankAccount/${client.id}">${client.name}</a></td>
            <td>${client.address}</td>
            <td>${client.age}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>


<h2>Форма для добавления новго клиента:</h2>
<form name="newClient" action="/${id}" method="post">
    <ul>
        <li>
            <label>Name</label> <input type="text" name="name"/>
        </li>
        <li>
            <label>Address</label> <input type="text" name="address"/>
        </li>
        <li>
            <label>Age</label> <input type="number" name="age"/>
        </li>
    </ul>
    <button type="submit">Update</button>
</form>

</body>
</html>
