package com.example.mvcbank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountBankModel implements Comparable<AccountBankModel>, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clientBankModelId")
    private ClientBankModel clientBankModel;

    private long amountOfMoney;

    @OneToMany(mappedBy = "fromBankAccountModel",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TransactionModel> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBankModel that = (AccountBankModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(AccountBankModel o) {
        return (int) (o.getId() - this.getId());
    }

    @Override
    public AccountBankModel clone() throws CloneNotSupportedException {
        return (AccountBankModel) super.clone();
    }

//    @Override
//    public String toString() {
//        return "AccountBankModel{" +
//                "Id=" + id +
//                ", clientBankModel=" + clientBankModel +
//                ", amountOfMoney=" + amountOfMoney +
//                ", transactions=" + transactions +
//                '}';
//    }
}
