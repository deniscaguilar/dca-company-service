package com.dca.company.controller;

import com.dca.company.config.CompanyApplication;
import com.dca.company.model.entity.Company;
import com.dca.company.model.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by denis on 12/02/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CompanyApplication.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class EmployeeControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void shouldFindEmployeeByIdAndValidateJson() throws Exception {
        Long id = 1l;
        this.mvc.perform(get(String.format("/employee/%s", id)).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Employee One - Company One")))
                .andExpect(jsonPath("company.name", equalTo("Company One")));
    }

    @Test
    public void shouldFindEmployeeByIdUnknowAndThrowResourceNotFoundException() throws Exception {
        Long id = 99l;
        this.mvc.perform(get(String.format("/employee/%s", id)).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        Long id = 7l;
        this.mvc.perform(delete(String.format("/employee/%s", id))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8")).andExpect(status().isOk());
    }

    @Test
    public void shouldInsertNewEmployeeValid() throws Exception {
        Company c = new Company();
        c.setId(1l);

        Employee e = new Employee();
        e.setName("Employee Test");
        e.setCompany(c);

        this.mvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(e))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldTryInsertNewEmployeeWithNameNullAndThrowInvalidRequestException() throws Exception {
        Company c = new Company();
        c.setId(1l);

        Employee e = new Employee();
        e.setCompany(c);

        this.mvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(e))
                .accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[*].message", contains("Please enter name of employee.")))
                .andDo(print());
    }

    @Test
    public void shouldUpdateEmployeeRegistered() throws Exception {
        Long id = 6l;
        MvcResult r = this.mvc.perform(get(String.format("/employee/%s", id)).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Employee One - Company Three")))
                .andReturn();

        String json = r.getResponse().getContentAsString();
        Employee e = convertJsonToObject(json, Employee.class);
        e.setName(e.getName() + " - modificado");

        this.mvc.perform(put("/employee").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(e))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Employee One - Company Three - modificado")));
    }

    @Test
    public void shouldTryUpdateEmployeeWithIdNullAndThrowBadRequest() throws Exception {
        Company c = new Company();
        c.setId(1l);

        Employee e = new Employee();
        e.setName("Employee Test");
        e.setCompany(c);

        this.mvc.perform(put("/employee").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(e))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", equalTo("Problems for update the employee.")));
    }

}
