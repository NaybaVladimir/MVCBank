<%@ page import="java.util.Date" %>
<%@ page import="com.example.mvcbank.model.TypeOfOperation" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.mvcbank.model.AccountBankModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="/">
    <button type="submit">К списку клиентов</button>
</form>
<form action="/bankAccount/${idAccountBank}">
    <button type="submit">К списку счетов</button>
</form>
<h2>Форма для добавления новой транзакции:</h2>
<form name="newTransaction" action="/transaction/${id}" method="post">
    <ul>
        <li>
            <input type="radio" name="typeOfOperation" value="<%=TypeOfOperation.Refill%>"> Пополнение
            <input type="radio" name="typeOfOperation" value="<%=TypeOfOperation.WritingOffMoney%>"> Списание
            <input type="radio" name="typeOfOperation" value="<%=TypeOfOperation.TransfersBetweenAccounts%>"> Перевод
            между счетами
        </li>
        <li>
            <label>Дата транзакции - <%=new SimpleDateFormat("dd/MM/yyyy").format(new Date())%>
            </label> <input type="hidden" name="date"
                            value="<%=new SimpleDateFormat("dd/MM/yyyy").format(new Date())%>"
                            readonly/>
        </li>
        <li>
            <label>Id Банковского счета - ${id} </label> <input type="hidden" name="fromBankAccountModel"
                                                                value="${id}" readonly/>
        </li>
        <li>
            <label>сумма на счете до транзакции - ${accountStateBeforeTheTransaction} </label> <input type="hidden" name="accountStateBeforeTheTransaction"
                                                                value="${accountStateBeforeTheTransaction}" readonly/>
        </li>
        <li>
            <label>Сумма:</label> <input type="number" name="sum"/>
        </li>
    </ul>
    <button type="submit">Update</button>
</form>


<h2>Список транзакций по счету c id - ${id}:</h2>

<form action="/transaction/${id}">

    <label>От:</label> <input type="Date" name="from"/>

    <label>До:</label> <input type="Date" name="to"/>

    <button type="submit">Отфильтровать</button>
</form>

<table border="1">
    <thead>
    <tr>
        <th>УИН Транзакции</th>
        <th>Дата</th>
        <th>УИН Счета</th>
        <th>Тип Транзакции</th>
        <th>Сумма Транзакции</th>
        <th>Состояние счета до транзакции</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="transaction" items="${allTransaction}">
        <tr>
            <td>${transaction.id}</td>
            <td>${transaction.date}</td>
            <td>${transaction.fromBankAccountModel.id}</td>
            <td>${transaction.typeOfOperation}</td>
            <td>${transaction.sum}</td>
            <td>${transaction.accountStateBeforeTheTransaction}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>