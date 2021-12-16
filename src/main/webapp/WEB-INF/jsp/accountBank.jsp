<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Клиенты банка</title>
</head>
<body>
<form action="/">
    <button type="submit">К списку клиентов</button>
</form>
<h2>Список счетов клиента:</h2>
<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>clientBankModel</th>
        <th>amountOfMoney</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="accountBank" items="${allAccountBank}">
        <tr>
            <td>${accountBank.id}</td>
            <td>${accountBank.clientBankModel.name}</td>
            <td>${accountBank.amountOfMoney}</td>
            <td>
                <form action="/transaction/${accountBank.id}">
                    <button type="submit">История | Осуществление транзакции</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<form name="newAccountBank" action="/bankAccount/${id}" method="post">

            <input type="hidden" name="clientBankModel" value="${id}" />
            <input type="hidden" name="amountOfMoney" value="0"/>
    <button type="submit">Создать новый счет</button>
</form>

</body>
</html>