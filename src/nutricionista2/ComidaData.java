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
public class ComidaData {
    private Connection connection = null;

    public ComidaData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
    public void guardarComida(Comida comida){
        try {
            
            String sql = "INSERT INTO comida (nombre, detalle, calorias) VALUES ( ? , ? , ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, comida.getNombre());
            statement.setString(2, comida.getDetalle());
            statement.setDouble(3, comida.getCalorias());
            
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                comida.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una comida");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una comida: " + ex.getMessage());
        }
    }
    
    public List<Comida> obtenerComidas(){
        List<Comida> comidas = new ArrayList<Comida>();
            

        try {
            String sql = "SELECT * FROM comida;";
            PreparedStatement statement = connection.prepareStatement(sql);
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
    
    public Comida buscarComida(int id){
    
        Comida comida=null;
    try {
            
            String sql = "SELECT * FROM comida WHERE idComida =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                comida = new Comida();
                comida.setId(resultSet.getInt("idComida"));
                comida.setNombre(resultSet.getString("nombre"));
                comida.setDetalle(resultSet.getString("detalle"));
                comida.setCalorias(resultSet.getDouble("calorias"));

                
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al encontrar una comida: " + ex.getMessage());
        }
        
        return comida;
    }
    
    public void borrarComida (int id){
    
        
    try {
            
            String sql = "DELETE FROM comida WHERE idComida =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una comida: " + ex.getMessage());
        }
        
    
    
    }
    public void actualizarComida(Comida comida){
    try {
            
            String sql = "UPDATE comida SET nombre = ?, detalle = ?, calorias = ? WHERE idComida = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, comida.getNombre());
            statement.setString(2, comida.getDetalle());
            statement.setDouble(3, comida.getCalorias());
            statement.setInt(4, comida.getId());
            statement.executeUpdate();
            
          
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una comida: " + ex.getMessage());
        }
     
    }
    
    public List<Comida> buscarComidaConCaloriasMenoresA(int calorias){
        List<Comida> comidas = new ArrayList<Comida>();
            

        try {
            String sql = "SELECT * FROM comida WHERE calorias <?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, calorias);
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
    
}
