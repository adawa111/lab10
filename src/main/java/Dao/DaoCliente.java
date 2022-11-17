package Dao;
import Bean.Clientes;
import Bean.Credentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
public class DaoCliente extends DaoBase{

    public Clientes buscarCliente(String id){
        Clientes client = new Clientes();
        String sql1 = "Select * from jm_client_bii where g4093_nro_id ="+id;
        try(Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery(sql1)) {
            rs1.next();
            int idd = Integer.parseInt(id);
            client.setNombreCliente(rs1.getString("g4093_name"));
            client.setEdad(rs1.getString("g4093_age"));
            client.setTipoCliente(rs1.getString("g4093_type"));
            client.setTipoDocumento(rs1.getString("g4093_documentType"));
            client.setNombreDocumento(rs1.getString("g4093_nro_id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return client;
    }

    public ArrayList<Clientes> listarClientes (){
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        String sql = "select g4093_nro_id, g4093_name, g4093_age, g4093_type, g4093_documentType " +
                "from jm_client_bii" +
                "where g4093_nro_id Not in (Select nro_documento from credentials)";
        try (Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Clientes cli = new Clientes();
                cli.setNombreCliente(rs.getString("g4093_name"));
                cli.setEdad(rs.getString("g4093_age"));
                cli.setTipoCliente(rs.getString("g4093_type"));
                cli.setTipoDocumento(rs.getString("g4093_documentType"));
                cli.setNombreDocumento(rs.getString("g4093_nro_id"));
                listaClientes.add(cli);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaClientes;
    }

    public float mostrarMaxExpectedLoss (String id){
        float expectedLoss = 0;
        String sql = "select * from jm_values " +
                "where substring(jm_cotr_bis_g6789,len(jm_cotr_bis_g6789)-1,"+
                id.length()+") = "+id;
        try(Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery(sql)) {
            rs1.next();
            float valorpd = (rs1.getFloat("pd_value"));
            float valorlgd = (rs1.getFloat("lgd_value"));
            float tasa = (rs1.getFloat("recovery_rate"));
            expectedLoss = valorpd*valorlgd*(1-tasa);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return expectedLoss;
    }

    public void crearCredentialCliente(Credentials credencial, String password){

        //Credentials credencial = new Credentials();
        String sql = "INSERT INTO credentials (nro_documento, password, hashedPassword, tipoUsuario) VALUES (?,?,sha2(?,256),?)";

        try (Connection connection =this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,credencial.getNumeroDocumento());
            pstmt.setString(2,password);
            pstmt.setString(3,password);
            pstmt.setInt(4,credencial.getTipoUsuario());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public Credentials buscarUsuario(String numeroDocumento, String password){

        Credentials credencial = new Credentials();
        String sql = "Select * from credentials where nro_documento=? and password=?";
        try(Connection con = this.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);) {

            pstmt.setString(1, numeroDocumento) ;
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){

                    credencial.setNumeroDocumento(rs.getString(1));
                    credencial.set

                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return credencial;
    }


    public Usuario ingresarLogin(String username, String password){

        Usuario usuario = null;

        //antes del sql se debe hashear el password para comparar los hashes
        String sql = "select * from Usuarios where correo=? and password=sha2(?,256)";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String codigo = rs.getString(1);
                    System.out.println(codigo);
                    usuario=buscarPorId(codigo);
                    System.out.println(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;

    }




}
