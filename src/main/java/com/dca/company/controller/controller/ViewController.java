package com.dca.company.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class ViewController {

    @RequestMapping(method = RequestMethod.GET)
    public String listCompany() {
        return "company/list";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String openFormCompany(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("id", id);
        }
        return "company/form";
    }

    @RequestMapping(params = "detail", method = RequestMethod.GET)
    public String detailCompany(@RequestParam Long id, Model model) {
        if (id != null) {
            model.addAttribute("id", id);
        }
        return "company/detail";
    }

    @RequestMapping(params = "beneficialOwner", method = RequestMethod.GET)
    public String viewBeneficialOwner(@RequestParam Long id, Model model) {
        if (id != null) {
            model.addAttribute("id", id);
        }
        return "beneficialOwner/list";
    }

    @RequestMapping(params = "formBeneficialOwner", method = RequestMethod.GET)
    public String openFormBeneficialOwner(@RequestParam Long companyId, @RequestParam(required = false) Long id, Model model) {
        model.addAttribute("companyId", companyId);
        if (id != null) {
            model.addAttribute("id", id);
        }
        return "beneficialOwner/form";
    }

}
