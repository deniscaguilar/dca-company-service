package com.dca.company.controller.controller;

import com.dca.company.model.entity.Company;
import com.dca.company.model.view.ListCompany;
import com.dca.company.model.view.Message;
import com.dca.company.service.CompanyService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/company")
public class CompanyController extends BaseController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Company> findById(@PathVariable Long id){
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company);
    }

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ListCompany> findAll() {
        ListCompany listCompany = companyService.findAll();
        return ResponseEntity.ok(listCompany);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Company> save(@RequestBody @Valid Company companyRequest, BindingResult result){
        validate(result);

        Company company = companyService.save(companyRequest);
        return ResponseEntity.ok(company);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Company> update(@RequestBody @Valid Company companyRequest, BindingResult result) {
        Preconditions.checkArgument(companyRequest != null && companyRequest.getId() != null, "Problems for update the company.");

        validate(result);

        Company company = companyService.save(companyRequest);
        return ResponseEntity.ok(company);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.ok(new Message("ok"));
    }

}
