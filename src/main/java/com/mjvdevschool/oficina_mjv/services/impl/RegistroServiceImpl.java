package com.mjvdevschool.oficina_mjv.services.impl;

import com.mjvdevschool.oficina_mjv.dao.RegistroDao;
import com.mjvdevschool.oficina_mjv.models.Registro;
import com.mjvdevschool.oficina_mjv.services.RegistroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class RegistroServiceImpl implements RegistroService {

    private final RegistroDao registroDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroServiceImpl.class);

    public RegistroServiceImpl(RegistroDao registroDao) {
        this.registroDao = registroDao;
    }

    @Transactional
    @Override
    public void salvar(Registro registro, List<String> ids) {
        LOGGER.info("RegistroService: Inicio metodo salvar");

        registro.setData(new Date());
        registro.setHora("123");

        Long registroId = registroDao.salvar(registro);

        for(int i = 0; i < ids.size(); i++) {
            Long idDefeitoPeca = Long.parseLong(ids.get(i));
            registroDao.salvarRelacionamentoPecaDefeito(registroId, idDefeitoPeca);
        }

        LOGGER.info("RegistroService: Fim metodo salvar");
    }

    @Override
    public List<Registro> buscarTodos() {
        return registroDao.buscarTodos();
    }

    @Override
    public List<Registro> buscarTodos(Long veiculoId, String dataIncio, String dataFim) {
        return registroDao.buscarTodos(veiculoId, dataIncio, dataFim);
    }

}
