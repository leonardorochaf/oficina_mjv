package com.mjvdevschool.oficina_mjv.services.impl;

import com.mjvdevschool.oficina_mjv.dao.DefeitoDao;
import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.services.DefeitoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DefeitoServiceImpl implements DefeitoService {

    private final DefeitoDao defeitoDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefeitoServiceImpl.class);

    public DefeitoServiceImpl(DefeitoDao defeitoDao) {
        this.defeitoDao = defeitoDao;
    }

    @Transactional
    @Override
    public void Salvar(Defeito defeito) {
        LOGGER.info("DefeitoService: Inicio metodo salvar");

        Optional<Defeito> defeitoPorNome = defeitoDao.buscarPorNome(defeito.getNome());

        if(defeitoPorNome.isPresent()) {
            LOGGER.error("DefeitoService: Já existe um defeito cadastrado com o nome: " + defeitoPorNome.get().getNome());
            throw new BusinessException("Já existe um defeito cadastrado com esse nome");
        }

        LOGGER.info("DefeitoService: Fim metodo salvar");

        defeitoDao.salvar(defeito);
    }

    @Override
    public List<Defeito> buscarTodos() {
        return defeitoDao.buscarTodos();
    }
}
