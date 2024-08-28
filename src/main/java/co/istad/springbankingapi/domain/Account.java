package co.istad.springbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String aliasName;
    @Column(length = 9, nullable = false,unique = true)
    private String accNo;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private BigDecimal transferLimit;

    @Column(nullable = false)
    private Boolean isHidden;
    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private AccountType accountTypes;

    @OneToOne
    private Cards cards;

    @OneToOne(mappedBy = "accounts")
    private UserAccount userAccounts;

}
