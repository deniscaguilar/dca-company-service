package com.dca.company.model.repository;

import com.dca.company.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by denis on 10/02/16.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
