package com.mjvdevschool.oficina_mjv.rowmapper;

import br.com.mjvdevschool.oficina.modelsDTO.DefeitoPecaDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefeitoPecaRowMapper implements RowMapper<DefeitoPecaDTO> {
    @Override
    public DefeitoPecaDTO mapRow(ResultSet rs, int i) throws SQLException {
        DefeitoPecaDTO defeitoPecaDTO = new DefeitoPecaDTO();

        defeitoPecaDTO.setId(rs.getLong(1));
        defeitoPecaDTO.setIdDefeito(rs.getLong(2));
        defeitoPecaDTO.setNomeDefeito(rs.getString(3));
        defeitoPecaDTO.setIdPeca(rs.getLong(4));
        defeitoPecaDTO.setNomePeca(rs.getString(5));

        return defeitoPecaDTO;
    }
}
