package com.dca.company.service.impl;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.Company;
import com.dca.company.model.entity.Employee;
import com.dca.company.model.repository.EmployeeRepository;
import com.dca.company.model.view.ListEmployee;
import com.dca.company.service.CompanyService;
import com.dca.company.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by denis on 10/02/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CompanyService companyService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    @Override
    public Employee save(Employee employee) {
        log.info("Saving employee: {}", employee);

        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        log.info("Finding employee by id {}", id);

        Employee employee = employeeRepository.findOne(id);
        return Optional.ofNullable(employee).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ListEmployee findByCompany(Long idCompany) {
        log.info("Finding employee by company {}", idCompany);

        Company company = companyService.findById(idCompany);
        Optional.ofNullable(company).orElseThrow(ResourceNotFoundException::new);

        List<Employee> employees = employeeRepository.findByCompany(company);
        return new ListEmployee(employees, employees.size());
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting employee {}", id);

        employeeRepository.delete(id);
    }
}
