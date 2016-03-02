package com.dca.company.model.view;

import com.dca.company.model.entity.Company;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Company> items = new ArrayList<>();

    private final Long count;

    public ListCompany(List<Company> items, Long count) {
        this.items.addAll(items);
        this.count = count;
    }

}
