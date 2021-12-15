<%@ page import="java.util.Date" %>
<%@ page import="com.example.mvcbank.model.TypeOfOperation" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Форма для добавления новой транзакции:</h2>
<form name="newTransaction" action="/transaction/${idAccountBank}" method="post">
    <ul>
        <li>
            <input type="radio" name="typeOfOperation" value="<%=TypeOfOperation.Refill%>"> Пополнение
            <input type="radio" name="typeOfOperation" value="<%=TypeOfOperation.WritingOffMoney%>"> Списание
            <input type="radio" name="typeOfOperation" value="<%=TypeOfOperation.TransfersBetweenAccounts%>"> Перевод между счетами
        </li>
        <li>
            <label>Дата(Не редактируется):</label> <input type="text" name="date"
                                                          value="<%=new SimpleDateFormat("dd/MM/yyyy").format(new Date())%>"
                                                          readonly/>
        </li>
        <li>
            <label>Id Банковского счета(Не редактируется):</label> <input type="text" name="fromBankAccountModel"
                                                                          value="${idAccountBank}" readonly/>
        </li>
        <li>
            <label>Сумма:</label> <input type="number" name="sum"/>
        </li>
    </ul>
    <button type="submit">Update</button>
</form>

<h2>Список транзакций по счету c id ${idAccountBank}:</h2>
<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>date</th>
        <th>fromBankAccountModel</th>
        <th>typeOfOperation</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="transaction" items="${allTransaction}">
        <tr>
                            <td>${transaction.id}</td>
                            <td>${transaction.date}</td>
                            <td>${transaction.fromBankAccountModel.id}</td>
                            <td>${transaction.typeOfOperation}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>