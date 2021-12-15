package com.example.mvcbank.model;

public enum TypeOfOperation {
    Refill("Пополнение"),
    WritingOffMoney("Списание"),
    TransfersBetweenAccounts("Перевод");

     private final String operation;

    TypeOfOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
