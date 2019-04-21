/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaston
 */
public class PacienteData {
    private Connection con;

    public PacienteData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
    public void guardarPaciente(Paciente paciente){
        try {
            
            String sql = "INSERT INTO paciente (nombre, dni, domicilio, celular, pesoActual) VALUES ( ? , ? , ? , ? , ? );";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getDni());
            ps.setString(3, paciente.getDomicilio());
            ps.setString(4, paciente.getCelular());
            ps.setDouble(5, paciente.getPesoActual());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un paciente");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un paciente: " + ex.getMessage());
        }
    }
    
    public List<Paciente> obtenerPacientes(){
        List<Paciente> pacientes = new ArrayList<Paciente>();
            

        try {
            String sql = "SELECT * FROM paciente;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            Paciente paciente;
            while(resultSet.next()){
                paciente = new Paciente();
                paciente.setId(resultSet.getInt("idPaciente"));
                paciente.setNombre(resultSet.getString("nombre"));
                paciente.setDni(resultSet.getString("dni"));
                paciente.setDomicilio(resultSet.getString("domicilio"));
                paciente.setCelular(resultSet.getString("celular"));
                paciente.setPesoActual(resultSet.getDouble("pesoActual"));

                pacientes.add(paciente);
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los pacientes: " + ex.getMessage());
        }
        
        
        return pacientes;
    }
    
    public void borrarPaciente(int id){
    try {
            
            String sql = "DELETE FROM paciente WHERE idPaciente =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
                      
            ps.executeUpdate();
                        
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un paciente: " + ex.getMessage());
        }
        
    
    }
    
    public void actualizarPaciente(Paciente paciente){
    
        try {
            
            String sql = "UPDATE paciente SET nombre = ?, dni = ? , "
                    + "domicilio =? , celular =? , pesoActual =? WHERE idPaciente = ?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getDni());
            ps.setString(3, paciente.getDomicilio());
            ps.setString(4, paciente.getCelular());
            ps.setDouble(5, paciente.getPesoActual());
            ps.setInt(6, paciente.getId());
            
            ps.executeUpdate();
            
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un paciente: " + ex.getMessage());
        }
    
    }
    
    public Paciente buscarPaciente(int id){
    Paciente paciente=null;
    try {
            
            String sql = "SELECT * FROM paciente WHERE idPaciente =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            while(resultSet.next()){
                paciente = new Paciente();
                paciente.setId(resultSet.getInt("idPaciente"));
                paciente.setNombre(resultSet.getString("nombre"));
                paciente.setDni(resultSet.getString("dni"));
                paciente.setDomicilio(resultSet.getString("domicilio"));
                paciente.setCelular(resultSet.getString("celular"));
                paciente.setPesoActual(resultSet.getDouble("pesoActual"));

            }      
            ps.close();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un paciente: " + ex.getMessage());
        }
        
        return paciente;
    }
    
    
}
