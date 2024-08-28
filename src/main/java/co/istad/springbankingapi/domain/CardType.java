package co.istad.springbankingapi.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.aspectj.weaver.ast.Literal;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_types")
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String alias;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "cardTypes")
    private List<Cards> cards;

}
