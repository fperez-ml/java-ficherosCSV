/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.dam2627.accesodatos.javaficheroscsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import me.dam2627.accesodatos.javaficheroscsv.modelo.Producto;

/**
 *
 * @author Fperez
 */
public class TransformarDatosCSV {

    /* Este programa va a cargar unos datosLinea en memoria para transformarlos
    *  para posteriormente escribirlo en un fichero CSV
    *  La transformación consiste en aumentar el precioUnitario el porcentaje
    *  definid en la constante PERC_INCRE
     */
    private static double PERC_INCRE = 3.4/100;

    private static List<Producto> leerDatosCSV(File fichero) {

        List<Producto> datos = new ArrayList<Producto>();

        try {
            BufferedReader productosFichero = new BufferedReader(new FileReader(fichero));
            boolean primeraLinea = true;
            String linea;

            while ((linea = productosFichero.readLine()) != null) {
                if (primeraLinea) {
                    // Leemos la primera linea para avanzar, ya que no se procesa
                    primeraLinea = false;
                } else {
                    String[] datosLinea = linea.split(",");
                    String idProducto = datosLinea[0];
                    double precioUnidad = Double.parseDouble(datosLinea[1]);
                    String descripcion = datosLinea[2];
                    Producto producto = new Producto(idProducto, precioUnidad, descripcion);
                    datos.add(producto);
                }
            }

        } catch (IOException e) {
            System.out.println(String.format("ERROR!! leyendo el fichero %s\n%s", fichero, e)
            );
            datos = null;
        }
        return datos;

    }

    static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.out.println("ERROR: fichero no válido");
            System.exit(1);
        }

        File fichero = new File(args[0]);
        if (!fichero.exists() || !fichero.isFile()) {
            System.out.println("ERROR: fichero no válido");
            System.exit(1);
        }

        List<Producto> datos = leerDatosCSV(fichero);
        
        //Ruta del fichero procesado
        String rutaFicheroProcesado = fichero.getParent() + "\\productos_procesado.csv";

        // Escribir cabecera opcional
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFicheroProcesado));
            
            //Cabecera del fichero
            
            bw.write("idProducto,precioUnidad,descripcion");
            bw.newLine();

            // Escribir cada objeto Producto como una línea en el CSV
            for (Producto p : datos) {
                //Transformamos los datos, en especial el precioUnidad
                String linea = String.format("%s,%.2f,%s",
                        p.getIdProducto(),
                        p.getPrecioUnidad() * (1 + PERC_INCRE),
                        p.getDescripcion());

                bw.write(linea);
                bw.newLine();
            }
            bw.close();
            System.out.println("Proceso terminado existosamente");
        }catch(IOException e){
            System.out.println(
                    String.format("ERROR!! leyendo el fichero %s\n%s", rutaFicheroProcesado, e)
            
            );
        }

    }

}
