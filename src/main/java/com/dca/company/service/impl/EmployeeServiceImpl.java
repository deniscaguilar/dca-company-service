package com.dca.company.service.impl;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.Employee;
import com.dca.company.model.repository.EmployeeRepository;
import com.dca.company.model.view.ListEmployee;
import com.dca.company.service.EmployeeService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by denis on 10/02/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        if (log.isDebugEnabled()) {
            log.debug("Save employee: {}", employee);
        }
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        Employee employee = employeeRepository.findOne(id);
        return Optional.ofNullable(employee).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ListEmployee findByPage(Pageable page) {
        long count = employeeRepository.count();
        return new ListEmployee(Lists.newArrayList(employeeRepository.findAll()), count);
    }

    @Override
    public void delete(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Delete employee: {}", id);
        }
        employeeRepository.delete(id);
    }
}
