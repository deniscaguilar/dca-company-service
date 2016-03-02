package com.dca.company.controller;

import com.dca.company.config.CompanyApplication;
import com.dca.company.model.entity.Company;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CompanyApplication.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class CompanyControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void shouldFindAllCompaniesAndValidateSize() throws Exception {
        this.mvc.perform(get("/company/").accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("items", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    public void shouldFindCompanyByIdAndValidateJson() throws Exception {
        Long id = 1l;
        this.mvc.perform(get(String.format("/company/%s", id)).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Company One")))
                .andExpect(jsonPath("address", equalTo("6831 Hollister Ave")))
                .andExpect(jsonPath("city", equalTo("California")))
                .andExpect(jsonPath("country", equalTo("United States")));
    }

    @Test
    public void shouldFindCompanyByIdUnknowAndThrowResourceNotFoundException() throws Exception {
        Long id = 99l;
        this.mvc.perform(get(String.format("/company/%s", id)).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldDeleteCompany() throws Exception {
        Long id = 4l;
        this.mvc.perform(delete(String.format("/company/%s", id))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8")).andExpect(status().isOk());
    }

    @Test
    public void shouldTryDeleteCompanyAssociatedWithEmployeeAndThrowDataAccessException() throws Exception {
        Long id = 3l;
        this.mvc.perform(delete(String.format("/company/%s", id))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8")).andExpect(status().isBadGateway())
                .andDo(print());
    }

    @Test
    public void shouldInsertNewCompanyValid() throws Exception {
        Company c = new Company();
        c.setName("Company Test");
        c.setAddress("Address Test");
        c.setCity("City Test");
        c.setCountry("Country Test");
        c.setEmail("emailtest@emailtest.com");
        c.setPhone("111111");

        this.mvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c))
                .accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldTryInsertNewCompanyWithEmailInvalidAndThrowInvalidRequestException() throws Exception {
        Company c = new Company();
        c.setName("Company Test Invalid");
        c.setAddress("Address Test");
        c.setCity("City Test");
        c.setCountry("Country Test");
        c.setEmail("Email Test");
        c.setPhone("111111");

        this.mvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c))
                .accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[*].message", contains("Please enter a valid email address.")))
                .andDo(print());
    }

    @Test
    public void shouldTryInsertNewCompanyWithNameAndAddressNullAndThrowInvalidRequestException() throws Exception {
        Company c = new Company();
        c.setCity("City Test");
        c.setCountry("Country Test");
        c.setEmail("emailtest@emailtest.com");
        c.setPhone("111111");

        this.mvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c))
                .accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder("Please enter the address.","Please enter name of company.")))
                .andDo(print());
    }

    @Test
    public void shouldUpdateCompanyRegistered() throws Exception {
        Long id = 3l;
        MvcResult r = this.mvc.perform(get(String.format("/company/%s", id)).accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Company Three")))
                .andReturn();

        String json = r.getResponse().getContentAsString();
        Company c = convertJsonToObject(json, Company.class);
        c.setName(c.getName() + " - modificado");

        this.mvc.perform(put("/company").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Company Three - modificado")));
    }

    @Test
    public void shouldTryUpdateCompanyWithIdNullAndThrowBadRequest() throws Exception {
        Company c = new Company();
        c.setName("Company Test Invalid");
        c.setAddress("Address Test");
        c.setCity("City Test");
        c.setCountry("Country Test");
        c.setEmail("emailtest@emailtest.com");
        c.setPhone("111111");

        this.mvc.perform(put("/company").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(c))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(setContentType("charset=utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", equalTo("Problems for update the company.")));
    }

}
