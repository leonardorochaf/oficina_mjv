package com.mjvdevschool.oficina_mjv.rowmapper;

import com.mjvdevschool.oficina_mjv.models.Veiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoRowMapper implements RowMapper<Veiculo> {

    @Override
    public Veiculo mapRow(ResultSet rs, int i) throws SQLException {
        Veiculo veiculo = new Veiculo();

        veiculo.setId(rs.getLong("id"));
        veiculo.setNome(rs.getString("nome"));

        return veiculo;
    }
}
