package com.mjvdevschool.oficina_mjv.services.impl;

import com.mjvdevschool.oficina_mjv.dao.RegistroDao;
import com.mjvdevschool.oficina_mjv.exceptions.BusinessException;
import com.mjvdevschool.oficina_mjv.exceptions.ResourceNotFoundException;
import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Registro;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;
import com.mjvdevschool.oficina_mjv.services.RegistroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String horarioAtual = formatter.format((calendar.getTime()));

        registro.setHora((horarioAtual));
        registro.setData(new Date());

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

    @Override
    public Registro buscarPorId(Long id) {
        Optional<Registro> registroPorId = registroDao.buscarPorId(id);

        if(registroPorId.isEmpty()) {
            LOGGER.error("DefeitoService: Não foi encontrado o registro com o id: " + id);
            throw new ResourceNotFoundException("Registro com id: " + id + " não encontrado");
        }

        return registroPorId.get();
    }

    @Override
    public List<DefeitoPecaDTO> buscarDefeitoEPecaPorRegistro(Long idRegistro) {
        List<DefeitoPecaDTO> teste = registroDao.buscarDefeitoEPecaPorRegistro(idRegistro);
        return teste;
    }

}
