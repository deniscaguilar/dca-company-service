package com.dca.company.model.view;

import com.dca.company.model.entity.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 10/02/16.
 */
@Data
public class ListEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Employee> itens = new ArrayList<>();

    private final Long count;

    public ListEmployee(List<Employee> itens, Long count) {
        this.itens.addAll(itens);
        this.count = count;
    }

}
