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
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author gaston
 */
public class DietaComidaData {
    private Connection connection = null;
    private Conexion conexion;
    
    
    public DietaComidaData(Conexion conexion) {
        try {
            this.conexion=conexion;
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    public void guardarComidasXDieta(int idDieta, List<Comida> comidas){
        
        for (Comida comida : comidas) {
            DietaComida dietaComida = new DietaComida();
            try {
                String sql = "INSERT INTO dieta_comida (idDieta, idComida) VALUES ( ? , ? );";

                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, idDieta);
                statement.setInt(2, comida.getId());
                

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    dietaComida.setId(rs.getInt(1));
                    dietaComida.setIdDieta(idDieta);
                    dietaComida.setIdComida(comida.getId());
                } else {
                    System.out.println("No se pudo obtener el id luego de insertar una dietaComida");
                }
                statement.close();

            } catch (SQLException ex) {
                System.out.println("Error al insertar una dietaComida: " + ex.getMessage());
            }
        }
    }
    
    public void actualizarUnaComidaXDieta(int idPlan, int idDieta, int idComida){
    
        try {
            
            String sql = "UPDATE dieta_comida SET idComida =? WHERE idPlan =? AND idDieta =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idComida);
            statement.setInt(2, idPlan);
            statement.setInt(3, idDieta);
            
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una dietaComida: " + ex.getMessage());
        }
        
    }
    
    public void borrarUnaDietaComida(int idDieta){
    
        try {
            
            String sql = "DELETE FROM dieta_comida WHERE idDieta =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idDieta);
            
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar la dietaComida de una dieta: " + ex.getMessage());
        }
     
    }
    
    public List<Comida> obtenerComidasXDieta(int idDieta){
        List<Comida> comidas = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM dieta_comida WHERE idDieta =?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDieta);
            ResultSet resultSet = statement.executeQuery();
            Comida comida;
            ComidaData comidaData = new ComidaData(conexion);
            while(resultSet.next()){
                comida = new Comida();
                comida = comidaData.buscarComida(resultSet.getInt("idComida"));
                
                comidas.add(comida);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las comidas: " + ex.getMessage());
        }
        
        
        return comidas;
    }


}
