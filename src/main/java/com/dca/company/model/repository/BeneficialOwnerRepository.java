package com.dca.company.model.repository;

import com.dca.company.model.entity.Company;
import com.dca.company.model.entity.BeneficialOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficialOwnerRepository extends JpaRepository<BeneficialOwner, Long> {

    List<BeneficialOwner> findByCompany(Company company);

}
