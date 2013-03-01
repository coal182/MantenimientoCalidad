/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.calidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import utilidades.BaseDatos;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class ListaTareas {
    private List<Tarea> tareas = new ArrayList<Tarea>();

    public ListaTareas() {
    }

/* Devuelve una lista */
    public List getListaTareas() {
            BaseDatos BD = new BaseDatos(BaseDatos.MySQL, "localhost:3306", "mantenimiento_preventivo", "root", "coal182");

                    try {

                        String sql3="";
                        sql3="SELECT * FROM plan_sistemas_pendiente ORDER BY FECHA DESC";

                        System.out.println(sql3);
                            ResultSet rs3 = BD.Select(sql3);
                            while(rs3.next()){


                                java.sql.Date FECHA= rs3.getDate("FECHA");
             
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                String fecha_tarea=formato.format(FECHA);
                                Boolean REALIZADA= rs3.getBoolean("REALIZADA");
                                String EQUIPO= rs3.getString("EQUIPO");
                                String EXP_ACTIVIDAD= rs3.getString("EXP_ACTIVIDAD");
                                String TIPO_ORDENADOR= rs3.getString("TIPO_ORDENADOR");
                                String ACTIVIDAD= rs3.getString("ACTIVIDAD");

                                String DIRECCION_IP= rs3.getString("DIRECCION_IP");

                                Tarea tarea = new Tarea(fecha_tarea, REALIZADA, EQUIPO, EXP_ACTIVIDAD, TIPO_ORDENADOR, ACTIVIDAD, DIRECCION_IP);

                                tareas.add(tarea);

                            }
                    } catch (SQLException ex) {
                        System.out.println("ERROR en listar tareas");
                    }finally{
                        BD.cerrarBaseDatos();
                    }
           return tareas;
        }
    
    public List getListaTareasPendientes() {
            BaseDatos BD = new BaseDatos(BaseDatos.MySQL, "localhost:3306", "mantenimiento_preventivo", "root", "coal182");

                    try {

                        String sql3="";
                        sql3="SELECT * FROM plan_sistemas_pendiente WHERE REALIZADA=0 ORDER BY FECHA ASC";

                        System.out.println(sql3);
                            ResultSet rs3 = BD.Select(sql3);
                            while(rs3.next()){

                                int ID= rs3.getInt("ID");
                                java.sql.Date FECHA= rs3.getDate("FECHA");
                           
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                String fecha_tarea=formato.format(FECHA);
                                Boolean REALIZADA= rs3.getBoolean("REALIZADA");
                                String EQUIPO= rs3.getString("EQUIPO");
                                String EXP_ACTIVIDAD= rs3.getString("EXP_ACTIVIDAD");
                                String TIPO_ORDENADOR= rs3.getString("TIPO_ORDENADOR");
                                String ACTIVIDAD= rs3.getString("ACTIVIDAD");

                                String DIRECCION_IP= rs3.getString("DIRECCION_IP");

                                Tarea tarea = new Tarea(ID, fecha_tarea, REALIZADA, EQUIPO, EXP_ACTIVIDAD, TIPO_ORDENADOR, ACTIVIDAD, DIRECCION_IP);

                                tareas.add(tarea);

                            }
                    } catch (SQLException ex) {
                        System.out.println("ERROR en listar tareas");
                    }finally{
                        BD.cerrarBaseDatos();
                    }
           return tareas;
        }
    
        public List getListaTareasPendientesPorEquipo(String EQUIPO) {
            BaseDatos BD = new BaseDatos(BaseDatos.MySQL, "localhost:3306", "mantenimiento_preventivo", "root", "coal182");

                    try {

                        String sql3="";
                        sql3="SELECT * FROM plan_sistemas_pendiente WHERE REALIZADA=0 AND EQUIPO='"+EQUIPO+"' ORDER BY FECHA ASC";

                        System.out.println(sql3);
                            ResultSet rs3 = BD.Select(sql3);
                            while(rs3.next()){

                                int ID= rs3.getInt("ID");
                                java.sql.Date FECHA= rs3.getDate("FECHA");
                           
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                String fecha_tarea=formato.format(FECHA);
                                Boolean REALIZADA= rs3.getBoolean("REALIZADA");
                      
                                String EXP_ACTIVIDAD= rs3.getString("EXP_ACTIVIDAD");
                                String TIPO_ORDENADOR= rs3.getString("TIPO_ORDENADOR");
                                String ACTIVIDAD= rs3.getString("ACTIVIDAD");

                                String DIRECCION_IP= rs3.getString("DIRECCION_IP");

                                Tarea tarea = new Tarea(ID, fecha_tarea, REALIZADA, EQUIPO, EXP_ACTIVIDAD, TIPO_ORDENADOR, ACTIVIDAD, DIRECCION_IP);

                                tareas.add(tarea);

                            }
                    } catch (SQLException ex) {
                        System.out.println("ERROR en listar tareas pendientes por equipo");
                    }finally{
                        BD.cerrarBaseDatos();
                    }
           return tareas;
        }
    
}
