package com.mjvdevschool.oficina_mjv.rowmapper;


import com.mjvdevschool.oficina_mjv.models.Registro;
import com.mjvdevschool.oficina_mjv.models.Veiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroRowMapper implements RowMapper<Registro> {

    @Override
    public Registro mapRow(ResultSet rs, int i) throws SQLException {
        Registro registro = new Registro();

        registro.setId(rs.getLong("id"));
        registro.setNomeCliente(rs.getString("nome_cliente"));
        registro.setData(rs.getDate("data"));
        registro.setHora(rs.getString("hora"));

        Veiculo veiculo = new Veiculo();

        veiculo.setId(rs.getLong(5));
        veiculo.setNome(rs.getString("nome"));

        registro.setVeiculo(veiculo);

        return registro;
    }
}
