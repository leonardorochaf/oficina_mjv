package com.mjvdevschool.oficina_mjv.rowmapper;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import com.mjvdevschool.oficina_mjv.models.Peca;
import com.mjvdevschool.oficina_mjv.modelsDTO.DefeitoPecaDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefeitoPecaRowMapper implements RowMapper<DefeitoPecaDTO> {
    @Override
    public DefeitoPecaDTO mapRow(ResultSet rs, int i) throws SQLException {
        DefeitoPecaDTO defeitoPecaDTO = new DefeitoPecaDTO();

        defeitoPecaDTO.setId(rs.getLong(1));

        Defeito defeito = new Defeito();
        defeito.setId(rs.getLong(2));
        defeito.setNome(rs.getString(3));

        Peca peca = new Peca();
        peca.setId(rs.getLong(4));
        peca.setNome(rs.getString(5));

        defeitoPecaDTO.setDefeito(defeito);
        defeitoPecaDTO.setPeca(peca);

        return defeitoPecaDTO;
    }
}
