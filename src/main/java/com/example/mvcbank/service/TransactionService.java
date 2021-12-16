package com.example.mvcbank.service;

import com.example.mvcbank.model.TransactionModel;
import com.example.mvcbank.repository.AccountBankRepo;
import com.example.mvcbank.repository.TransactionRepo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private AccountBankRepo accountBankRepo;


    public Map<String, Object> initMainTransaction(Map<String, Object> map, String id, String dateFrom, String dateTo) {

        val client = accountBankRepo.findAccountBankModelById(Long.parseLong(id));
        var listTransaction = transactionRepo.findAllByFromBankAccountModel(client);

        if (!"notDate".equals(dateFrom)) {
            Date from = format(dateFrom);
            listTransaction = listTransaction.stream()
                    .filter(t -> t.getDate().compareTo(from) >= 0)
                    .collect(Collectors.toList());
        }

        if (!"notDate".equals(dateTo)) {
            Date to = format(dateTo);
            listTransaction = listTransaction.stream()
                    .filter(t -> t.getDate().compareTo(to) <= 0)
                    .collect(Collectors.toList());
        }


        map.put("allTransaction", listTransaction);
        map.put("accountStateBeforeTheTransaction", accountBankRepo.findAccountBankModelById(Long.parseLong(id)).getAmountOfMoney());
        map.put("idAccountBank",accountBankRepo.findAccountBankModelById(Long.parseLong(id)).getClientBankModel().getId());
        map.put("newTransaction", new TransactionModel());
        map.put("tranactions",  accountBankRepo.findAll());
        return map;
    }


    public void addNewTransaction(TransactionModel transaction) {

        long sum = transaction.getFromBankAccountModel().getAmountOfMoney();

        switch (transaction.getTypeOfOperation()) {
            case Refill:
                transaction.getFromBankAccountModel().setAmountOfMoney(sum + transaction.getSum());
                saveTransaction(transaction);
                break;
            case WritingOffMoney:
                if (sum - transaction.getSum() >= 0)
                    transaction.getFromBankAccountModel().setAmountOfMoney(sum - transaction.getSum());
                saveTransaction(transaction);
                break;
            case TransfersBetweenAccounts:
                if (sum - transaction.getSum() >= 0)
                    transaction.getFromBankAccountModel().setAmountOfMoney(sum - transaction.getSum());
                saveTransaction(transaction);
                break;
        }

    }

    public Date format(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Transactional
    public void saveTransaction(TransactionModel transactionModel) {
        transactionRepo.save(transactionModel);
    }
}
