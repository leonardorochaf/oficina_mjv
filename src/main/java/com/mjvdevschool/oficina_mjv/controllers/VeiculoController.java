package com.mjvdevschool.oficina_mjv.controllers;

import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;
import com.mjvdevschool.oficina_mjv.services.PecaService;
import com.mjvdevschool.oficina_mjv.services.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    private final PecaService pecaService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoController.class);

    public VeiculoController(VeiculoService veiculoService, PecaService pecaService) {
        this.veiculoService = veiculoService;
        this.pecaService = pecaService;
    }

    /** Metodo que inicia a pagina de cadastro de {@link Veiculo}.
     * @return
     */
    @GetMapping("/cadastro")
    public String cadastroVeiculo(ModelMap model) {
        model.addAttribute("pecas", pecaService.buscarTodos());
        return "veiculo/cadastro";
    }

    /** Metodo que cadastra um {@link Veiculo} e as {@link Peca} relacionados com esse tipo de veiculo, validando o formulario de cadastro e retorna mensagem de erro ou sucesso para o cliente da aplicacao
     * @return
     */
    @PostMapping("/cadastrar")
    public String cadastar(Veiculo veiculo, @RequestParam(required = false) List<String> pecasIds, RedirectAttributes atrAttributes) {
        LOGGER.info("VeiculoController: Inicio metodo cadastrar");

        List<String> mensagensErroFormulario = new ArrayList<>();

        if(StringUtils.isEmpty(veiculo.getNome())) {
            mensagensErroFormulario.add("Nome não informado");
        }

        if(pecasIds == null) {
            mensagensErroFormulario.add("É necessário selecionar ao menos um checkbox de peça");
        }

        if(!mensagensErroFormulario.isEmpty()) {
            atrAttributes.addFlashAttribute("mensagensErroFormulario", mensagensErroFormulario);
            LOGGER.error("VeiculoController: Erro no formulario nos campos: " + mensagensErroFormulario.toString());
            return "redirect:/veiculo/cadastro";
        }

        try {
            veiculoService.salvar(veiculo, pecasIds);
            atrAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso");
            LOGGER.info("VeiculoController: Fim metodo cadastrar");
            return "redirect:/veiculo/cadastro";
        } catch (BusinessException e) {
            atrAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            LOGGER.error("VeiculoController: Houve um erro ao cadastrar peca: " + e.getMessage(), e);
            return "redirect:/veiculo/cadastro";
        } catch (Exception e) {
            atrAttributes.addFlashAttribute("mensagemErro", "Houve um erro ao realizar o cadastro. Tente novamente mais tarde");
            LOGGER.error("VeiculoController: Houve um erro ao cadastrar tipo de veiculo: " + e.getMessage(), e);
            return "redirect:/veiculo/cadastro";
        }
    }

    /** Metodo que retorna um JSON com todos os {@link Defeito} e {@link Peca} que estejam relacionandos com um {@link Veiculo}
     * @return
     */
    @GetMapping("/{id}/buscar_defeitos_e_pecas")
    @ResponseBody
    public ResponseEntity<List<DefeitoPecaDTO>> buscarDefeitosEPecas(@PathVariable Long id) {
        List<DefeitoPecaDTO> listDefeitosEPecas = veiculoService.buscarDefeitosEPecasPorVeiculo(id);

        return new ResponseEntity<>(listDefeitosEPecas, HttpStatus.OK);
    }
}
