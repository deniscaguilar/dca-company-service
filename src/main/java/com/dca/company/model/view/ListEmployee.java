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

    private final List<Employee> items = new ArrayList<>();

    private final int count;

    public ListEmployee(List<Employee> items, int count) {
        this.items.addAll(items);
        this.count = count;
    }

}
