/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.calidad;

import java.sql.Date;
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
 * @author cristianmartin
 */
@ManagedBean
@RequestScoped
public class Tarea {
    private int ID;
    private String FECHA;
    private Boolean REALIZADA;
    private String EQUIPO;
    private String EXP_ACTIVIDAD;
    private String TIPO_ORDENADOR;
    private String ACTIVIDAD;
    private String DIRECCION_IP;

    public Tarea() {
    }

    public Tarea(String FECHA, Boolean REALIZADA, String EQUIPO, String EXP_ACTIVIDAD, String TIPO_ORDENADOR, String ACTIVIDAD, String DIRECCION_IP) {
        this.FECHA = FECHA;
        this.REALIZADA = REALIZADA;
        this.EQUIPO = EQUIPO;
        this.EXP_ACTIVIDAD = EXP_ACTIVIDAD;
        this.TIPO_ORDENADOR = TIPO_ORDENADOR;
        this.ACTIVIDAD = ACTIVIDAD;
        this.DIRECCION_IP = DIRECCION_IP;
    }

    public Tarea(int ID, String FECHA, Boolean REALIZADA, String EQUIPO, String EXP_ACTIVIDAD, String TIPO_ORDENADOR, String ACTIVIDAD, String DIRECCION_IP) {
        this.ID = ID;
        this.FECHA = FECHA;
        this.REALIZADA = REALIZADA;
        this.EQUIPO = EQUIPO;
        this.EXP_ACTIVIDAD = EXP_ACTIVIDAD;
        this.TIPO_ORDENADOR = TIPO_ORDENADOR;
        this.ACTIVIDAD = ACTIVIDAD;
        this.DIRECCION_IP = DIRECCION_IP;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public Boolean getREALIZADA() {
        return REALIZADA;
    }

    public void setREALIZADA(Boolean REALIZADA) {
        this.REALIZADA = REALIZADA;
    }

    public String getEQUIPO() {
        return EQUIPO;
    }

    public void setEQUIPO(String EQUIPO) {
        this.EQUIPO = EQUIPO;
    }

    public String getEXP_ACTIVIDAD() {
        return EXP_ACTIVIDAD;
    }

    public void setEXP_ACTIVIDAD(String EXP_ACTIVIDAD) {
        this.EXP_ACTIVIDAD = EXP_ACTIVIDAD;
    }

    public String getTIPO_ORDENADOR() {
        return TIPO_ORDENADOR;
    }

    public void setTIPO_ORDENADOR(String TIPO_ORDENADOR) {
        this.TIPO_ORDENADOR = TIPO_ORDENADOR;
    }

    public String getACTIVIDAD() {
        return ACTIVIDAD;
    }

    public void setACTIVIDAD(String ACTIVIDAD) {
        this.ACTIVIDAD = ACTIVIDAD;
    }

    public String getDIRECCION_IP() {
        return DIRECCION_IP;
    }

    public void setDIRECCION_IP(String DIRECCION_IP) {
        this.DIRECCION_IP = DIRECCION_IP;
    }

    
    
    private List<Tarea> tareas = new ArrayList<Tarea>();

/* Inserta Tarea */
 /**
     * Cambiar Contraseña
     * @return
     * @throws SQLException 
     */
    public String crearTarea() throws SQLException {
        
        BaseDatos BD = new BaseDatos(BaseDatos.MySQL, "localhost:3306", "mantenimiento_preventivo", "root", "coal182");

                try {
                  
                    String sql="";
                    
                        sql="insert into mantenimiento_preventivo.plan_sistemas_pendiente VALUES ('"+this.FECHA+"', "+this.REALIZADA+", '"+this.EQUIPO+"', '"+this.EXP_ACTIVIDAD+"', '"+this.TIPO_ORDENADOR+"', '"+this.ACTIVIDAD+"', '"+this.DIRECCION_IP+"')";
                    
                    System.out.println(sql);
                        BD.Ejecuta(sql);
                           
                }finally{
                    BD.cerrarBaseDatos();
                }
        
        return "index";
    }
    
    public String marcarRealizada() throws SQLException {
        
        BaseDatos BD = new BaseDatos(BaseDatos.MySQL, "localhost:3306", "mantenimiento_preventivo", "root", "coal182");

                try {
                  
                    String sql="";
                    
                        sql="UPDATE mantenimiento_preventivo.plan_sistemas_pendiente SET REALIZADA=1 WHERE ID="+this.ID;
                    
                    System.out.println(sql);
                        BD.Ejecuta(sql);
                           
                }finally{
                    BD.cerrarBaseDatos();
                }
        
        return "index";
    } 
    
    
}
