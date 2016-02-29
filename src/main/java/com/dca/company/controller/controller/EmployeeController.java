package com.dca.company.controller.controller;

import com.dca.company.model.entity.Employee;
import com.dca.company.model.view.ListEmployee;
import com.dca.company.service.EmployeeService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(value = "/company/{idCompany}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ListEmployee> findByCompany(@PathVariable Long idCompany) {
        ListEmployee listEmployee = employeeService.findByCompany(idCompany);
        return ResponseEntity.ok(listEmployee);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Employee> save(@RequestBody @Valid Employee employeeRequest, BindingResult result){
        validate(result);

        Employee employee = employeeService.save(employeeRequest);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Employee> update(@RequestBody @Valid Employee employeeRequest, BindingResult result) {
        Preconditions.checkArgument(employeeRequest != null && employeeRequest.getId() != null, "Problems for update the employee.");

        validate(result);

        Employee employee = employeeService.save(employeeRequest);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
