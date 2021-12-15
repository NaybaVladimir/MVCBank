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
public class ClientBankModel implements Comparable<ClientBankModel>, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private byte age;
    @OneToMany(mappedBy = "clientBankModel",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<AccountBankModel> list;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBankModel that = (ClientBankModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(ClientBankModel o) {
        return (int) (o.getId() - this.getId());
    }

    @Override
    public ClientBankModel clone() throws CloneNotSupportedException {
        return (ClientBankModel) super.clone();
    }

    @Override
    public String toString() {
        return "ClientBankModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", list=" + list +
                '}';
    }
}
