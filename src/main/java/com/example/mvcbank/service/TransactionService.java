package com.example.mvcbank.service;

import com.example.mvcbank.model.AccountBankModel;
import com.example.mvcbank.model.TransactionModel;
import com.example.mvcbank.model.TypeOfOperation;
import com.example.mvcbank.repository.AccountBankRepo;
import com.example.mvcbank.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final AccountBankRepo accountBankRepo;

    /**
     * инициализация ответа для представления данными
     * @param map ответ для представления
     * @param id ID текущего счета
     * @param dateFrom - дата для фильтра по дате
     * @param dateTo - дата для фильтра по дате
     */
    public void initMainTransaction(Map<String, Object> map, String id, String dateFrom, String dateTo) {
        val client = accountBankRepo.findAccountBankModelById(Long.parseLong(id));
        var allTransaction = transactionRepo.findAllByFromBankAccountModel(client);
        var allAccountBank = accountBankRepo.findAll();

        val accountStateBeforeTheTransaction = accountBankRepo.findAccountBankModelById(Long.parseLong(id)).getAmountOfMoney();
        val idAccountBank = accountBankRepo.findAccountBankModelById(Long.parseLong(id)).getClientBankModel().getId();

        if (!"notDate".equals(dateFrom)) {
            val from = format(dateFrom);
            allTransaction = allTransaction.stream()
                    .filter(t -> t.getDate().compareTo(from) >= 0)
                    .collect(Collectors.toList());
        }

        if (!"notDate".equals(dateTo)) {
            val to = format(dateTo);
            allTransaction = allTransaction.stream()
                    .filter(t -> t.getDate().compareTo(to) <= 0)
                    .collect(Collectors.toList());
        }

        allAccountBank.remove(accountBankRepo.findAccountBankModelById(Long.parseLong(id)));
        map.put("allTransaction", allTransaction);
        map.put("accountStateBeforeTheTransaction", accountStateBeforeTheTransaction);
        map.put("idAccountBank", idAccountBank);
        map.put("newTransaction", new TransactionModel());
        map.put("allAccountBank", allAccountBank);

    }

    /**
     * Производит необходимые манипуляции с новой транзакцией и отдает на сохранение в БД
     * @param transaction - транзакция полученая с представления
     */
    public void addNewTransaction(TransactionModel transaction) {

        val sum = transaction.getFromBankAccountModel().getAmountOfMoney();

        switch (transaction.getTypeOfOperation()) {
            case Refill:
                if (sum + transaction.getSum() >= 0&&transaction.getSum()>=1) {
                    transaction.getFromBankAccountModel().setAmountOfMoney(sum + transaction.getSum());
                    transaction.setPurposeOfPayment("Refill");
                    saveTransaction(transaction);
                }
                break;
            case WritingOffMoney:
                if (sum - transaction.getSum() >= 0&&transaction.getSum()>=1) {
                    transaction.getFromBankAccountModel().setAmountOfMoney(sum - transaction.getSum());
                    transaction.setPurposeOfPayment("WritingOffMoney");
                    saveTransaction(transaction);
                }
                break;
            case TransfersBetweenAccounts:
                AccountBankModel accountBankModel=accountBankRepo.findAccountBankModelById(Long.parseLong(transaction.getPurposeOfPayment()));
                if (sum - transaction.getSum() >= 0&&transaction.getSum()>=1) {

                    transaction.getFromBankAccountModel().setAmountOfMoney(sum - transaction.getSum());

                    TransactionModel recipientTransaction=new TransactionModel();
                    recipientTransaction.setDate(new Date());
                    recipientTransaction.setFromBankAccountModel(accountBankModel);
                    recipientTransaction.setTypeOfOperation(TypeOfOperation.TransfersBetweenAccounts);
                    recipientTransaction.setSum(transaction.getSum());
                    recipientTransaction.setAccountStateBeforeTheTransaction(accountBankModel.getAmountOfMoney());
                    recipientTransaction.setPurposeOfPayment("Refill");

                    accountBankModel.setAmountOfMoney(accountBankModel.getAmountOfMoney()+transaction.getSum());

                    saveTransaction(transaction);
                    saveTransaction(recipientTransaction);
                }

                break;
        }

    }

    /**
     * Парсит строку с датой в в объект Date
     * @param dateString - строка с датой
     * @return - отдает результат работы
     */
    public Date format(String dateString) {
        val format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            log.error("Формат переданной строки не подходит для необходимого преобразования в дату{}", dateString);
        }
        return date;
    }

    /**
     * Сохранияет транзакцию в БД
     * @param transactionModel - транзакция для сохранения
     */
    @Transactional
    public void saveTransaction(TransactionModel transactionModel) {
        transactionRepo.save(transactionModel);
        log.info("Транзакция сохранена {}", transactionModel);
    }
}
