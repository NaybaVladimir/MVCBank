package com.example.mvcbank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionModel implements Comparable<TransactionModel>, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "accountBankModelId")
    private AccountBankModel fromBankAccountModel;

    private TypeOfOperation typeOfOperation;

    private long sum;

    private long accountStateBeforeTheTransaction;

    private String purposeOfPayment;


    @Override
    public int compareTo(TransactionModel o) {
        return (int) (o.getId() - this.getId());
    }

    @Override
    public TransactionModel clone() throws CloneNotSupportedException {
        return (TransactionModel) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionModel that = (TransactionModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
