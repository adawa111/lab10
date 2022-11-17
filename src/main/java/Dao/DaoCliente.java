package Dao;

import Bean.Clientes;

import java.sql.*;

public class DaoCliente extends DaoBase{

    public void crearCredentialCliente(Clientes cliente){

        String sql = "INSERT INTO credentials (codigo, nombre, apellido, DNI, password) VALUES (?,sha2(?,256))";


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getCodigo());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getCorreo());
            pstmt.setString(5, usuario.getDni());
            pstmt.setBoolean(6, true);

            pstmt.executeUpdate();
            //pstmt.setBytes(12, usuario.getFotoPerfil().getFotobyte());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
