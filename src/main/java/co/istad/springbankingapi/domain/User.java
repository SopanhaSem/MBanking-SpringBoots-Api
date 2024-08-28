package co.istad.springbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column(nullable = false,unique = true)
    private String uuid;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, unique = true, length = 6)
    private String pin;
    @Column(nullable = false, length = 500)
    private String password;
    @Column(nullable = false, unique = true, length = 50)
    private String nationalCardId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false, length = 10)
    private String gender;
    @Column(nullable = false,unique = true,length = 50)
    private String phoneNumber;
    private String profileImage;
    @Column(unique = true, length = 50)
    private String studentCardId;

    private String position;
    private String companyName;
    private String employeeType;
    private String cityOrProvince;
    private String khanOrDistrict;
    private String  songKatOrCommune;
    private String street;
    private String village;
    private String monthlyIncomeRange;
    private String mainSrcIncome;

    private LocalDate createAt;
    private Boolean isVerified;
    private Boolean isBlocked;

    //security
    private Boolean isDeleted;
    private Boolean isAccountNonUnlocked;
    private Boolean isAccountNonExpired;
    private Boolean isCredentialExpired;


    @OneToOne
    private Account accounts;

    @OneToMany(mappedBy = "users")
    private List<UserAccount> userAccounts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;


}
