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
        String sql = "SELECT * FROM bi_corp_business.jm_cotr_bis;";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while (rs.next()) {
                Contratos contrato = new Contratos();
                contrato.setNroDeContrato(rs.getString(1));
                contrato.setIdCliente(rs.getString(2));
                contrato.set
                usuario.setCodigo(rs.getString(1));
                usuario.setNombre(rs.getString(2));

                usuario.setCorreo(rs.getString(4));

                Rol rol = new Rol();
                rol.setIdRol(rs.getInt(10));
                rol.setNombreRol(rs.getString(11));
                usuario.setRol(rol);

                CategoriaPUCP categoriaPUCP = new CategoriaPUCP();
                categoriaPUCP.setIdCategoria(rs.getInt(12));
                categoriaPUCP.setNombreCategoria(rs.getString(13));
                usuario.setCategoriaPUCP(categoriaPUCP);

                FotoPerfil fotoPerfil = new FotoPerfil();
                fotoPerfil.setIdFoto(rs.getInt(14));
                fotoPerfil.setNombreFoto(rs.getString(15));
                fotoPerfil.setFotobyte(rs.getBytes(16));
                usuario.setFotoPerfil(fotoPerfil);

                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContratos;

    }




}
