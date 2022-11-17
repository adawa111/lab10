package Dao;
import Bean.Clientes;
import Bean.Contratos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoContrato extends DaoBase{

    public ArrayList<Contratos> listarContratos() {

        ArrayList<Contratos> listaContratos = new ArrayList<>();
        String sql = "SELECT g6789_contract, g6789_currency,g6789_months, \n" +
                "CASE\n" +
                "    WHEN G6789_status = '0' THEN \"normal\"\n" +
                "    WHEN G6789_status = '1' THEN \"cura\"\n" +
                "    when G6789_status = '2' THEN \"mora\"\n" +
                "    ELSE \"\"\n" +
                "END as estado\n" +
                "FROM bi_corp_business.jm_cotr_bis;";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while (rs.next()) {
                Contratos contrato = new Contratos();
                contrato.setNroDeContrato(rs.getString(1));
                contrato.setDivisa(rs.getString(2));
                contrato.setMesesEnEseEstado(rs.getInt(3));
                contrato.setEstado(rs.getInt(4));

                listaContratos.add(contrato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContratos;

    }


}
