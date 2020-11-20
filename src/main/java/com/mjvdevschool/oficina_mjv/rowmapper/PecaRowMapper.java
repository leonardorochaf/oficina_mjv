package com.mjvdevschool.oficina_mjv.rowmapper;

import com.mjvdevschool.oficina_mjv.models.Peca;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PecaRowMapper implements RowMapper<Peca> {

    @Override
    public Peca mapRow(ResultSet rs, int i) throws SQLException {
        Peca peca = new Peca();

        peca.setId(rs.getLong("id"));
        peca.setNome(rs.getString("nome"));

        return peca;
    }
}
