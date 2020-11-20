package com.mjvdevschool.oficina_mjv.rowmapper;

import com.mjvdevschool.oficina_mjv.models.Defeito;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefeitoRowMapper implements RowMapper<Defeito> {

    @Override
    public Defeito mapRow(ResultSet rs, int i) throws SQLException {
        Defeito defeito = new Defeito();

        defeito.setId(rs.getLong("id"));
        defeito.setNome(rs.getString("nome"));

        return defeito;
    }
}
