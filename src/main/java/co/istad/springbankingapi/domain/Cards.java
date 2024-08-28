package co.istad.springbankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 100,unique = true)
    private String number;
    @Column(nullable = false,length = 100)
    private String holder;
    @Column(nullable = false,length = 3)
    private String cvv;
    @Column(nullable = false)
    private LocalDate issued_at;
    @Column(nullable = false)
    private LocalDate expired_at;
    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToOne(mappedBy = "cards")
    private Account accounts;


    @ManyToOne
    private CardType cardTypes;


}
