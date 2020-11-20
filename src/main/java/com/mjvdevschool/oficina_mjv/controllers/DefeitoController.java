package com.mjvdevschool.oficina_mjv.controllers;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.services.DefeitoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/defeito")
public class DefeitoController {

    private final DefeitoService defeitoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefeitoController.class);

    public DefeitoController(DefeitoService defeitoService) {
        this.defeitoService = defeitoService;
    }

    /** Metodo que inicia a pagina de cadastro de defeitos.
     * @return
     */
    @GetMapping("/cadastro")
    public String cadastro() {
        return "defeito/cadastro";
    }

    /** Metodo que cadastra um defeito, validando o formulario de cadastro e retorna mensagem de erro ou sucesso para o cliente da aplicacao
     * @return
     */
    @PostMapping("/cadastrar")
    public String cadastrar(Defeito defeito, RedirectAttributes atrAttributes) {
        LOGGER.info("DefeitoController: Inicio metodo cadastrar");

        List<String> mensagensErroFormulario = new ArrayList<>();

        if(StringUtils.isEmpty(defeito.getNome())) {
            mensagensErroFormulario.add("Nome não informado");
        }

        if(!mensagensErroFormulario.isEmpty()) {
            atrAttributes.addFlashAttribute("mensagensErroFormulario", mensagensErroFormulario);
            LOGGER.error("DefeitoController: Erro no formulario nos campos: " + mensagensErroFormulario.toString());
            return "redirect:/defeito/cadastro";
        }

        try {
            defeitoService.Salvar(defeito);
            atrAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso");
            LOGGER.info("DefeitoController: Fim metodo cadastrar");
            return "redirect:/defeito/cadastro";
        } catch (BusinessException e) {
            atrAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            LOGGER.error("DefeitoController: Houve um erro ao cadastrar defeito: " + e.getMessage(), e);
            return "redirect:/defeito/cadastro";
        } catch (Exception e) {
            atrAttributes.addFlashAttribute("mensagemErro", "Houve um erro ao realizar o cadastro. Tente novamente mais tarde");
            LOGGER.error("DefeitoController: Houve um erro ao cadastrar defeito: " + e.getMessage(), e);
            return "redirect:/defeito/cadastro";
        }
    }

}
