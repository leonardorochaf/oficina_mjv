package com.mjvdevschool.oficina_mjv.services.impl;

import com.mjvdevschool.oficina_mjv.dao.VeiculoDao;
import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;
import com.mjvdevschool.oficina_mjv.services.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoDao veiculoDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoServiceImpl.class);

    public VeiculoServiceImpl(VeiculoDao veiculoDao) {
        this.veiculoDao = veiculoDao;
    }

    @Transactional
    @Override
    public void salvar(Veiculo veiculo, List<String> pecaIdsRequest) {
        LOGGER.info("VeiculoService: Inicio metodo salvar");

        Optional<Veiculo> veiculoPorNome = veiculoDao.buscarPorNome(veiculo.getNome());

        if(veiculoPorNome.isPresent()) {
            LOGGER.error("VeiculoService: Já existe um tipo de veiculo cadastrado com o nome: " + veiculoPorNome.get().getNome());
            throw new BusinessException("Já existe um tipo de veículo cadastrado com esse nome");
        }

        List<Long> pecaIds = new ArrayList<>();

        for(String s: pecaIdsRequest) {
            pecaIds.add(Long.parseLong(s));
        }

        Long veiculoId = veiculoDao.salvar(veiculo);

        for (Long pecaId: pecaIds) {
            veiculoDao.salvarRelacionamentoPeca(veiculoId, pecaId);
        }

        LOGGER.info("VeiculoService: Fim metodo salvar");
    }

    @Override
    public List<Veiculo> buscarTodos() {
        return veiculoDao.buscarTodos();
    }

    @Override
    public List<DefeitoPecaDTO> buscarDefeitosEPecasPorVeiculo(Long veiculoId) {
        return veiculoDao.buscarDefeitosEPecasPorVeiculo(veiculoId);
    }
}
