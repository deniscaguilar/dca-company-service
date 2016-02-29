package com.dca.company.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by denis on 10/02/16.
 */
@Entity
@Data
@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 100, message = "{company.name.size}")
    @NotNull(message = "{company.name.notnull}")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(min = 10, max = 200, message = "{company.address.size}")
    @NotNull(message = "{company.address.notnull}")
    @Column(nullable = false, length = 200)
    private String address;

    @Size(min = 5, max = 80, message = "{company.city.size}")
    @NotNull(message = "{company.city.notnull}")
    @Column(nullable = false, length = 80)
    private String city;

    @Size(min = 5, max = 50, message = "{company.country.size}")
    @NotNull(message = "{company.country.notnull}")
    @Column(nullable = false, length = 50)
    private String country;

    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "{company.email.invalido}")
    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

}
