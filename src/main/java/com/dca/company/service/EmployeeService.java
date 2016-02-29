package com.dca.company.service;

import com.dca.company.model.entity.Employee;
import com.dca.company.model.view.ListEmployee;

/**
 * Created by denis on 10/02/16.
 */
public interface EmployeeService {

    Employee save(Employee employee);

    Employee findById(Long id);

    ListEmployee findByCompany(Long idCompany);

    void delete(Long id);

}
