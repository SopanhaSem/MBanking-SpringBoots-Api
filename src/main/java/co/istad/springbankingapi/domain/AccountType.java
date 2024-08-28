package co.istad.springbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String alias;
    @Column(nullable = false,unique = true,length = 25)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "accountTypes")
    private List<Account> accounts;
}
