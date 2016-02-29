package com.dca.company.model;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.Company;
import com.dca.company.model.repository.CompanyRepository;
import com.dca.company.service.CompanyService;
import com.dca.company.service.impl.CompanyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by denis on 11/02/16.
 */
public class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private Company company;

    @Mock
    List<Company> listCompany;

    private Long id = 1l;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository);
        when(companyRepository.findOne(id)).thenReturn(company);
        when(company.getId()).thenReturn(id);
        when(companyRepository.findAll()).thenReturn(listCompany);
    }

    @Test
    public void shouldSaveCompanyWhenDoesNotExist(){
        when(company.getId()).thenReturn(id);
        companyService.save(company);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowResourceNotFoundExceptionWhenCompanyDoesNotExist(){
        when(company.getId()).thenReturn(id);
        companyService.findById(2l);
    }

}
