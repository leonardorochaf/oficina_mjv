package com.mjvdevschool.oficina_mjv.controllers;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.models.Registro;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;
import com.mjvdevschool.oficina_mjv.services.RegistroService;
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
@RequestMapping("/registro")
public class RegistroController {

    private final RegistroService registroService;

    private final VeiculoService veiculoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroController.class);

    public RegistroController(RegistroService registroService, VeiculoService veiculoService) {
        this.registroService = registroService;
        this.veiculoService = veiculoService;
    }

    /** Metodo que inicia a pagina de cadastro de {@link Registro}.
     * @return
     */
    @GetMapping("/cadastro")
    public String cadastro(ModelMap model) {
        model.addAttribute("veiculos", veiculoService.buscarTodos());
        return "registro/cadastro";
    }

    /** Metodo que cadastra um {@link Registro}, os {@link Defeito} e {@link Peca} relacionados com esse registro, validando o formulario de cadastro e retorna mensagem de erro ou sucesso para o cliente da aplicacao
     * @return
     */
    @PostMapping("/cadastrar")
    public String cadastrar(Registro registro, @RequestParam(required = false)  List<String> ids, RedirectAttributes atrAttributes) {
        LOGGER.info("RegistroController: Inicio metodo cadastrar");

        List<String> mensagensErroFormulario = new ArrayList<>();

        if(StringUtils.isEmpty(registro.getNomeCliente())) {
            mensagensErroFormulario.add("Nome do cliente não informado");
        }

        if(registro.getVeiculo() == null) {
            mensagensErroFormulario.add("É necessário selecionar o tipo de veículo do registro");
        }

        if(ids == null) {
            mensagensErroFormulario.add("É necessário selecionar ao menos um checkbox");
        }

        if(!mensagensErroFormulario.isEmpty()) {
            atrAttributes.addFlashAttribute("mensagensErroFormulario", mensagensErroFormulario);
            LOGGER.error("RegistroController: Erro no formulario nos campos: " + mensagensErroFormulario.toString());
            return "redirect:/registro/cadastro";
        }

        try {
            registroService.salvar(registro, ids);
            atrAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso");
            LOGGER.info("RegistroController: Fim metodo cadastrar");
            return "redirect:/registro/cadastro";
        } catch (Exception e) {
            atrAttributes.addFlashAttribute("mensagemErro", "Houve um erro ao realizar o cadastro. Tente novamente mais tarde");
            LOGGER.error("RegistroController: Houve um erro ao cadastrar registro: " + e.getMessage(), e);
            return "redirect:/registro/cadastro";
        }
    }

    /** Metodo que inicia a pagina de listagem de {@link Registro}.
     * @return
     */
    @GetMapping
    public String listar(ModelMap model) {
        model.put("veiculos", veiculoService.buscarTodos());
        return "registro/listagem";
    }

    @GetMapping("/{id}")
    public String listarPorId(@PathVariable Long id, ModelMap model) {
        List<DefeitoPecaDTO> teste = registroService.buscarDefeitoEPecaPorRegistro(id);

        model.put("pecasdefeitos", teste);
        model.put("registro", registroService.buscarPorId(id));

        return "registro/detalhes";
    }

    /** Metodo que retorna um JSON com todos os {@link Registro}, podendo ou nao conter paramentros para serem usados na filtragem da query
     * @return
     */
    @GetMapping("/todos")
    @ResponseBody
    public ResponseEntity<List<Registro>> listarTodosRegistros(@RequestParam(required = false) Long veiculoId, @RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim) {
        if(veiculoId == null && StringUtils.isEmpty(dataInicio) && StringUtils.isEmpty(dataFim)) {
            return new ResponseEntity<>(registroService.buscarTodos(), HttpStatus.OK);
        }

        return new ResponseEntity<>(registroService.buscarTodos(veiculoId, dataInicio, dataFim), HttpStatus.OK);
    }
}
