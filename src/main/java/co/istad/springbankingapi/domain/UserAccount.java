package co.istad.springbankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(unique = true)
    @OneToOne(cascade = CascadeType.PERSIST)
    private Account accounts;

    @ManyToOne
    private User users;


    private Boolean isDeleted;
    private Boolean isBlocked;

    private LocalDateTime createAt;

}
