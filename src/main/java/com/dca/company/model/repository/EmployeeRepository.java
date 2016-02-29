package com.dca.company.model.repository;

import com.dca.company.model.entity.Company;
import com.dca.company.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by denis on 10/02/16.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByCompany(Company company);

}
