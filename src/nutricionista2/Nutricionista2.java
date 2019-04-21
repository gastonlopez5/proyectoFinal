/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista2;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author gaston
 */
public class Nutricionista2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion;
 
        try {
            conexion = new Conexion("jdbc:mysql://localhost/nutricionista2", "root", "");
            
            // ABM Paciente
//              PacienteData pacienteData = new PacienteData(conexion);
              
            // Alta de un Paciente
//              
//              Paciente paciente = new Paciente("Thiago Lopez", "55555555", "Chile 7777", "777777", 50);
//              
//              pacienteData.guardarPaciente(paciente);
//            
            // Modificación de un Paciente
//              Paciente paciente = pacienteData.buscarPaciente(5);
//              paciente.setPesoActual(40);
//              
//              pacienteData.actualizarPaciente(paciente);
//            
            // Baja de un Paciente
//              Paciente paciente = pacienteData.buscarPaciente(5);
//              
//              pacienteData.borrarPaciente(paciente.getId());

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++              
            // ABM Comida
//            ComidaData comidaData = new ComidaData(conexion);
            
            // Alta de una Comida
//            Comida comida = new Comida("Pollo con Arroz", "AAAAAA", 70);
//            
//            comidaData.guardarComida(comida);
            
            // Modificacion de una Comida
//            Comida comida = comidaData.buscarComida(5);
//            comida.setCalorias(80);
            
//            comidaData.actualizarComida(comida);

            // Baja de una Comida
//            comidaData.borrarComida(5);
            
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Consulta a la BD sobre las comidas con calorias menores a un determinado valor
//              ComidaData comidaData = new ComidaData(conexion);
//              
//              comidaData.buscarComidaConCaloriasMenoresA(50).forEach(comida -> {
//                  System.out.println("Nombre del paciente: " + comida.getNombre());
//              });
//
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            
            // ABM Dieta
//            DietaData dietaData = new DietaData(conexion);
//            PacienteData pacienteData = new PacienteData(conexion);
//            ComidaData comidaData = new ComidaData(conexion);
            

            // Alta de una Dieta
//            Paciente paciente = pacienteData.buscarPaciente(1);
//            
//            List<Comida> comidas = comidaData.obtenerComidas();
//            
//            Dieta dieta = new Dieta(paciente, LocalDate.of(2019,4,11), LocalDate.of(2019,4,18), 60, 55, comidas);
//            dietaData.guardarPerfilDeDieta(dieta);
            
            
            // Modificacion Dieta
//            Dieta dieta = dietaData.buscarDieta(1);
            
//            List<Comida> comidas = new ArrayList<>();
//            Comida comida = new Comida("Frutas", "BBBBBB", 30);
//            comidaData.guardarComida(comida);
//            comidas.add(comida);
            
//            dieta.setPesoBuscado(60);
//            dieta.setComidas(comidas);
            
//            dietaData.actualizarDietaDeUnPaciente(dieta);
            
            // Baja de una Dieta
//            dietaData.borrarDietaDeUnPaciente(7);

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Consulta a la BD de los pacientes que al día de la fecha no alcanzaron el pesoBuscado
//            DietaData dietaData = new DietaData(conexion);
//            
//            dietaData.obtenerPacientesQueNoCumplieronPesoBuscado().forEach(paciente -> {
//              System.out.println("Nombre del paciente: " + paciente.getNombre());
//            });
            
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // Consulta a la BD de los pacientes que quieren bajar una cantidad mayor a número determinado
            //de kilos. (ej. Los pacientes que deben bajar más de 10 kg).
//            DietaData dietaData = new DietaData(conexion);
//
//            dietaData.obtenerPacientesConPerdidaDePesoMayorA(10).forEach(paciente -> {
//              System.out.println("Nombre del paciente: " + paciente.getNombre());
//            });

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

            // Consulta a la BD de sobre la cantidad de calorias que un paciente consume en la dieta
            DietaData dietaData = new DietaData(conexion);
//            
            System.out.println("Cantidad de calorias de la dieta: " + dietaData.obtenerCaloriasXPaciente(2)); 

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
            
        } catch (Exception e) {
                System.out.println("Error al instanciar la clase conexion: " + e.getMessage());
        }
    }
    
}
