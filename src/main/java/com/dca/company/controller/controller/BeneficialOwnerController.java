package com.dca.company.controller.controller;

import com.dca.company.model.entity.BeneficialOwner;
import com.dca.company.model.view.ListBeneficialOwner;
import com.dca.company.model.view.Message;
import com.dca.company.service.BeneficialOwnerService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/beneficialOwner")
public class BeneficialOwnerController extends BaseController {

    private BeneficialOwnerService beneficialOwnerService;

    @Autowired
    public BeneficialOwnerController(BeneficialOwnerService beneficialOwnerService) {
        this.beneficialOwnerService = beneficialOwnerService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BeneficialOwner> findById(@PathVariable Long id){
        BeneficialOwner beneficialOwner = beneficialOwnerService.findById(id);
        return ResponseEntity.ok(beneficialOwner);
    }

    @RequestMapping(value = "/company/{idCompany}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ListBeneficialOwner> findByCompany(@PathVariable Long idCompany) {
        ListBeneficialOwner listBeneficialOwner = beneficialOwnerService.findByCompany(idCompany);
        return ResponseEntity.ok(listBeneficialOwner);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BeneficialOwner> save(@RequestBody @Valid BeneficialOwner beneficialOwnerRequest, BindingResult result){
        validate(result);

        BeneficialOwner beneficialOwner = beneficialOwnerService.save(beneficialOwnerRequest);
        return ResponseEntity.ok(beneficialOwner);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BeneficialOwner> update(@RequestBody @Valid BeneficialOwner beneficialOwnerRequest, BindingResult result) {
        Preconditions.checkArgument(beneficialOwnerRequest != null && beneficialOwnerRequest.getId() != null, "Problems for update the beneficial owner.");

        validate(result);

        BeneficialOwner beneficialOwner = beneficialOwnerService.save(beneficialOwnerRequest);
        return ResponseEntity.ok(beneficialOwner);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        beneficialOwnerService.delete(id);
        return ResponseEntity.ok(new Message("ok"));
    }

}
