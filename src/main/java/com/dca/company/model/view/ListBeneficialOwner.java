package com.dca.company.model.view;

import com.dca.company.model.entity.BeneficialOwner;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 10/02/16.
 */
@Data
public class ListBeneficialOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<BeneficialOwner> items = new ArrayList<>();

    private final int count;

    public ListBeneficialOwner(List<BeneficialOwner> items, int count) {
        this.items.addAll(items);
        this.count = count;
    }

}
