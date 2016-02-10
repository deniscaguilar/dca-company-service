package com.dca.company.model.repository;

import com.dca.company.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by denis on 10/02/16.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
