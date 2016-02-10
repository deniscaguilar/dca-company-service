package com.dca.company.service;

import com.dca.company.model.entity.Employee;
import com.dca.company.model.view.ListEmployee;
import org.springframework.data.domain.Pageable;

/**
 * Created by denis on 10/02/16.
 */
public interface EmployeeService {

    Employee save(Employee employee);

    Employee findById(Long id);

    ListEmployee findByPage(Pageable page);

    void delete(Long id);

}
