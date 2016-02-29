package com.dca.company.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class ViewController {

    @RequestMapping(method = RequestMethod.GET)
    public String listCompany() {
        return "company/list";
    }
//
//    /**
//     * Direciona para o formulário de cadastro de endereços.
//     *
//     * @param id
//     * @param model
//     */
//    @RequestMapping(params = "formulario", method = RequestMethod.GET)
//    public String abrirFormularioEndereco(@RequestParam(required = false) Long id, Model model) {
//        if (id != null) {
//            model.addAttribute("id", id);
//        }
//        return "enderecos/formulario";
//    }
//
//    /**
//     * Direciona para a página de visualização de endereço.
//     *
//     * @param id
//     * @param model
//     */
//    @RequestMapping(params = "detalhe", method = RequestMethod.GET)
//    public String detalharEndereco(@RequestParam Long id, Model model) {
//        if (id != null) {
//            model.addAttribute("id", id);
//        }
//        return "enderecos/detalhe";
//    }
//
//    /**
//     * Direciona para a página de consulta de cep.
//     *
//     */
//    @RequestMapping(value = "consultarCep", method = RequestMethod.GET)
//    public String consultarCep() {
//        return "ceps/consultar";
//    }
//
//    /**
//     * Direciona para a página de testes de caracteres não repetidos.
//     *
//     */
//    @RequestMapping(value = "stream", method = RequestMethod.GET)
//    public String abrirFormularioStream() {
//        return "stream/formulario";
//    }

}
