package com.dca.company.model;

import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.model.entity.BeneficialOwner;
import com.dca.company.model.repository.BeneficialOwnerRepository;
import com.dca.company.service.BeneficialOwnerService;
import com.dca.company.service.CompanyService;
import com.dca.company.service.impl.BeneficialOwnerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Created by denis on 11/02/16.
 */
public class BeneficialOwnerServiceTest {

    private BeneficialOwnerService beneficialOwnerService;

    @Mock
    private CompanyService companyService;

    @Mock
    private BeneficialOwnerRepository beneficialOwnerRepository;

    @Mock
    private BeneficialOwner beneficialOwner;

    private final Long id = 1l;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        beneficialOwnerService = new BeneficialOwnerServiceImpl(beneficialOwnerRepository, companyService);
        when(beneficialOwnerRepository.findOne(id)).thenReturn(beneficialOwner);
        when(beneficialOwner.getId()).thenReturn(id);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowResourceNotFoundExceptionWhenBeneficialOwnerDoesNotExist(){
        when(beneficialOwner.getId()).thenReturn(id);
        beneficialOwnerService.findById(2l);
    }


}
