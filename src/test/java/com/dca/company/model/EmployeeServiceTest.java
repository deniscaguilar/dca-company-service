package com.dca.company.model;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.Employee;
import com.dca.company.model.repository.EmployeeRepository;
import com.dca.company.service.CompanyService;
import com.dca.company.service.EmployeeService;
import com.dca.company.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Created by denis on 11/02/16.
 */
public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private CompanyService companyService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Employee employee;

    private Long id = 1l;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository, companyService);
        when(employeeRepository.findOne(id)).thenReturn(employee);
        when(employee.getId()).thenReturn(id);
    }

    @Test
    public void shouldSaveEmployeeWhenDoesNotExist(){
        when(employee.getId()).thenReturn(id);
        employeeService.save(employee);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowResourceNotFoundExceptionWhenEmployeeDoesNotExist(){
        when(employee.getId()).thenReturn(id);
        employeeService.findById(2l);
    }


}
