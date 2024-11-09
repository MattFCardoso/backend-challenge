package com.challange.picpay.domain.user;

import com.challange.picpay.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO userDTO){
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.document = userDTO.document();
        this.userType = userDTO.userType();
        this.password = userDTO.password();
        this.email = userDTO.email();
        this.balance = userDTO.balance();

    }
}
