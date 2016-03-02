package com.dca.company.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "Beneficial")
@Data
@ToString
@EqualsAndHashCode
public class BeneficialOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 150, message = "{beneficialOwner.name.size}")
    @NotNull(message = "{beneficialOwner.name.notnull}")
    @Column(nullable = false, length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Transient
    private Long companyId;

}
