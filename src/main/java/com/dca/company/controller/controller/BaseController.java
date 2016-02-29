package com.dca.company.controller.controller;

import com.dca.company.exception.InvalidRequestException;
import org.springframework.validation.BindingResult;

public class BaseController {

    protected void validate(BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidRequestException("Invalid Request", result);
        }
    }

}
