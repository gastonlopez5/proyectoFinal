/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista2.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaston
 */
public class DietaData {
    private Connection connection = null;
    private Conexion conexion;
    private double calorias;
    private DietaComidaData dietaComidaData;
    

    public DietaData(Conexion conexion) {
        try {
            this.conexion=conexion;
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
    public void guardarPerfilDeDieta(Dieta dieta){
        
        try {
            String sql = "INSERT INTO dieta (idPaciente, fechaInicial, fechaFinal, pesoInicial, pesoBuscado) VALUES ( ? , ? , ? , ?, ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, dieta.getPaciente().getId());
            statement.setDate(2, Date.valueOf(dieta.getFechaInicial()));
            statement.setDate(3, Date.valueOf(dieta.getFechaFinal()));
            statement.setDouble(4, dieta.getPesoInicial());
            statement.setDouble(5, dieta.getPesoBuscado());


            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                dieta.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una dieta");
            }
            statement.close();

        } catch (SQLException ex) {
            System.out.println("Error al insertar una dieta: " + ex.getMessage());
        }
        
        dietaComidaData = new DietaComidaData(conexion);
        dietaComidaData.guardarComidasXDieta(dieta.getId(), dieta.getComidas());
        
    }
    
    public List<Comida> obtenerComidasXPaciente(int idPaciente){
        List<Comida> comidas = new ArrayList<>();
            

        try {
            String sql = "SELECT comida.idComida, comida.nombre, comida.detalle, comida.calorias FROM paciente, dieta, dieta_comida, comida WHERE paciente.idPaciente = dieta.idPaciente AND dieta.idDieta = dieta_comida.idDieta AND dieta_comida.idComida = comida.idComida AND paciente.idPaciente =?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();
            Comida comida;
            while(resultSet.next()){
                comida = new Comida();
                comida.setId(resultSet.getInt("idComida"));
                comida.setNombre(resultSet.getString("nombre"));
                comida.setDetalle(resultSet.getString("detalle"));
                comida.setCalorias(resultSet.getDouble("calorias"));
                
                comidas.add(comida);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las comidas: " + ex.getMessage());
        }
        
        
        return comidas;
    }
//    
    
    public Paciente buscarPaciente(int id){
    
        PacienteData pd=new PacienteData(conexion);
        
        return pd.buscarPaciente(id);
        
    }
    
    public Comida buscarComida(int id){
    
        ComidaData cd = new ComidaData(conexion);
        return cd.buscarComida(id);
    
    }
    
    
    public void borrarDietaDeUnPaciente(int idDieta){
        
        dietaComidaData = new DietaComidaData(conexion);
        dietaComidaData.borrarUnaDietaComida(idDieta);
        
        try {
            
            String sql = "DELETE FROM dieta WHERE idDieta =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idDieta);
            
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar la dieta de un paciente: " + ex.getMessage());
        }
     
    }
    
    public void actualizarDietaDeUnPaciente(Dieta dieta){
        
        if ((dieta.getComidas()).size() != 0){
            dietaComidaData = new DietaComidaData(conexion);
            dietaComidaData.borrarUnaDietaComida(dieta.getId());
        }
        
        try {
            
            String sql = "UPDATE dieta SET fechaInicial =?, fechaFinal =?, pesoInicial =?, pesoBuscado =? WHERE idDieta =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(dieta.getFechaInicial()));
            statement.setDate(2, Date.valueOf(dieta.getFechaFinal()));
            statement.setDouble(3, dieta.getPesoInicial());
            statement.setDouble(4, dieta.getPesoBuscado());
            statement.setInt(5, dieta.getId());
            
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una dieta: " + ex.getMessage());
        }
        
        if ((dieta.getComidas()).size() != 0){
            dietaComidaData.guardarComidasXDieta(dieta.getId(), dieta.getComidas());
        }
    }
    
    
    public List<Paciente> obtenerPacientesConPerdidaDePesoMayorA(double kilos){
        List<Paciente> pacientes = new ArrayList<Paciente>();
            

        try {
            String sql = "SELECT DISTINCT paciente.idPaciente FROM paciente, dieta WHERE paciente.idPaciente = dieta.idPaciente AND (dieta.pesoInicial - dieta.pesoBuscado) >= ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, kilos);
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                Paciente paciente = buscarPaciente(resultSet.getInt("idPaciente"));
                
                pacientes.add(paciente);
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los pacientes: " + ex.getMessage());
        }
        
        
        return pacientes;
    }
//    
    public double obtenerCaloriasXPaciente(int idPaciente){
        
        try {
            String sql = "SELECT SUM(calorias) AS cantidad_de_calorias FROM paciente, dieta, dieta_comida, comida WHERE paciente.idPaciente = dieta.idPaciente AND dieta.idDieta = dieta_comida.idDieta AND dieta_comida.idComida = comida.idComida AND paciente.idPaciente =?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPaciente);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                calorias = resultSet.getDouble("cantidad_de_calorias");
            }
            
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las comidas: " + ex.getMessage());
        }
        
        
        return calorias;
    }
//  
    public List<Paciente> obtenerPacientesQueNoCumplieronPesoBuscado(){
        List<Paciente> pacientes = new ArrayList<Paciente>();
            

        try {
            String sql = "SELECT * FROM paciente, dieta WHERE paciente.idPaciente = dieta.idPaciente AND dieta.fechaFinal <= LOCALTIME() AND paciente.pesoActual > dieta.pesoBuscado;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                Paciente paciente = buscarPaciente(resultSet.getInt("idPaciente"));
                
                pacientes.add(paciente);
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los pacientes: " + ex.getMessage());
        }
        
        
        return pacientes;
    }
//  
    public Dieta buscarDieta(int id){
    
    Dieta dieta = null;
    try {
            
            String sql = "SELECT * FROM dieta WHERE idDieta =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                dieta = new Dieta();
                dieta.setId(resultSet.getInt("idDieta"));
                dieta.setPaciente(buscarPaciente(resultSet.getInt("idPaciente")));
                dieta.setFechaInicial(resultSet.getDate("fechaInicial").toLocalDate());
                dieta.setFechaFinal(resultSet.getDate("fechaFinal").toLocalDate());
                dieta.setPesoInicial(resultSet.getDouble("pesoInicial"));
                dieta.setPesoBuscado(resultSet.getDouble("pesoBuscado"));
            }      
            statement.close();
         
        } catch (SQLException ex) {
            System.out.println("Error al encontrar una comida: " + ex.getMessage());
        }
        
        return dieta;
    }
    
    public Dieta buscarDietaXPaciente(int idPaciente){
    
    Dieta dieta = null;
    try {
            
            String sql = "SELECT * FROM dieta WHERE idPaciente =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idPaciente);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                dieta = new Dieta();
                dieta.setId(resultSet.getInt("idDieta"));
                dieta.setPaciente(buscarPaciente(resultSet.getInt("idPaciente")));
                dieta.setFechaInicial(resultSet.getDate("fechaInicial").toLocalDate());
                dieta.setFechaFinal(resultSet.getDate("fechaFinal").toLocalDate());
                dieta.setPesoInicial(resultSet.getDouble("pesoInicial"));
                dieta.setPesoBuscado(resultSet.getDouble("pesoBuscado"));
            }      
            statement.close();
         
        } catch (SQLException ex) {
            System.out.println("Error al encontrar una comida: " + ex.getMessage());
        }
        
        return dieta;
    }
    
}
