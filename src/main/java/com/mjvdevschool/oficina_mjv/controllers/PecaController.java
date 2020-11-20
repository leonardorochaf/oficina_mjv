package com.mjvdevschool.oficina_mjv.controllers;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.services.DefeitoService;
import com.mjvdevschool.oficina_mjv.services.PecaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/peca")
public class PecaController {

    private final PecaService pecaService;

    private final DefeitoService defeitoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PecaController.class);

    public PecaController(PecaService pecaService, DefeitoService defeitoService) {
        this.pecaService = pecaService;
        this.defeitoService = defeitoService;
    }

    /** Metodo que inicia a pagina de cadastro de pecas.
     * @return
     */
    @GetMapping("/cadastro")
    public String cadastroPeca(ModelMap model) {
        model.addAttribute("defeitos", defeitoService.buscarTodos());
        return "peca/cadastro";
    }

    /** Metodo que cadastra uma {@link Peca} e os {@link Defeito} relacionados com essa peca, validando o formulario de cadastro e retorna mensagem de erro ou sucesso para o cliente da aplicacao
     * @return
     */
    @PostMapping("/cadastrar")
    public String cadastar(Peca peca, @RequestParam(required = false) List<String> defeitosIds, RedirectAttributes atrAttributes) {
        LOGGER.info("PecaController: Inicio metodo cadastrar");

        List<String> mensagensErroFormulario = new ArrayList<>();

        if(StringUtils.isEmpty(peca.getNome())) {
            mensagensErroFormulario.add("Nome não informado");
        }

        if(defeitosIds == null) {
            mensagensErroFormulario.add("É necessário selecionar ao menos um checkbox de defeito");
        }

        if(!mensagensErroFormulario.isEmpty()) {
            atrAttributes.addFlashAttribute("mensagensErroFormulario", mensagensErroFormulario);
            LOGGER.error("PecaController: Erro no formulario nos campos: " + mensagensErroFormulario.toString());
            return "redirect:/peca/cadastro";
        }

        try {
            pecaService.salvar(peca, defeitosIds);
            atrAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso");
            LOGGER.info("PecaController: Fim metodo cadastrar");
            return "redirect:/peca/cadastro";
        } catch (BusinessException e) {
            atrAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            LOGGER.error("PecaController: Houve um erro ao cadastrar peca: " + e.getMessage(), e);
            return "redirect:/peca/cadastro";
        } catch (Exception e) {
            atrAttributes.addFlashAttribute("mensagemErro", "Houve um erro ao realizar o cadastro. Tente novamente mais tarde");
            LOGGER.error("PecaController: Houve um erro ao cadastrar peca: " + e.getMessage(), e);
            return "redirect:/peca/cadastro";
        }
    }

}
