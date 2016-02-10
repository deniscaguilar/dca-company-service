package com.dca.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by denis on 10/02/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found.")
public class ResourceNotFoundException extends RuntimeException {
}
