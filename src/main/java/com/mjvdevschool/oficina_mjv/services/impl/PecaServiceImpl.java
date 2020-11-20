package com.mjvdevschool.oficina_mjv.services.impl;

import com.mjvdevschool.oficina_mjv.dao.PecaDao;
import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.services.PecaService;
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
public class PecaServiceImpl implements PecaService {

    private final PecaDao pecaDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PecaServiceImpl.class);

    public PecaServiceImpl(PecaDao pecaDao) {
        this.pecaDao = pecaDao;
    }

    @Transactional
    @Override
    public void salvar(Peca peca, List<String> defeitoIds) {
        LOGGER.info("PecaService: Inicio metodo salvar");

        Optional<Peca> pecaPorNome = pecaDao.buscarPorNome(peca.getNome());

        if(pecaPorNome.isPresent()) {
            LOGGER.error("PecaService: Já existe uma peça cadastrada com o nome: " + pecaPorNome.get().getNome());
            throw new BusinessException("Já existe uma peça cadastrada com esse nome");
        }

        List<Long> defeitosIds = new ArrayList<>();

        for(String s: defeitoIds) {
            defeitosIds.add(Long.parseLong(s));
        }

        Long pecaId = pecaDao.salvar(peca);

        for (Long defeitoId: defeitosIds) {
            pecaDao.salvarRelacionamentoDefeito(pecaId, defeitoId);
        }

        LOGGER.info("PecaService: Fim metodo salvar");
    }

    @Override
    public List<Peca> buscarTodos() {
        return pecaDao.buscarTodos();
    }

}
