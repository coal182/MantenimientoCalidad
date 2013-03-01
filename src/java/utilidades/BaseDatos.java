/*
 * BaseDatos.java
 *
 * Created on 1 de abril de 2006, 15:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package utilidades;

import java.sql.*;

/**
 *
 * @author Juan
 */
public class BaseDatos {
    
    private Connection conexion = null;
    private Statement stm = null;
    private PreparedStatement pstm= null;
    private ResultSet res = null;
    private final String driver_mysql="com.mysql.jdbc.Driver";
    private final String driver_postgresql = "org.postgresql.Driver";
    
    public static final int MySQL = 1;
    public static final int PostgreSQL = 2;
    /** 
     * Crea una nueva instancia de sql
     * server: 1 MySQL, 2 Postgresql
     * host nombre del servidor a conectarnos
     * bd nombre de la base de datos
     * user usuario
     * pass contraseña del usuario
     */
    public BaseDatos(int server, String host, String bd, String user, String pass) {
         String driver = null;
         String url = null;
         // Selecciono el tipo de servidor a utilizar
         switch(server){
            case BaseDatos.MySQL: driver = this.driver_mysql;
                    url = "jdbc:mysql://"+host+"/"+bd;
                  break;
            
            case BaseDatos.PostgreSQL: driver = this.driver_postgresql;
                    url = "jdbc:postgresql://"+host+"/"+bd;
                  break;
        }
                               
        // Cargar el driver
        try{
           Class.forName(driver);// Cargo el driver
        }catch (ClassNotFoundException cnfe){
            System.err.println("Error al cargar el Driver: "+driver);
            System.err.println(cnfe.getMessage());
        }
        
        // Crear una conexion a traves del DriverManager
        try{
            // Creo la conexion a la base de datos con mi usuario y contraseña
            this.conexion = DriverManager.getConnection(url, user, pass);
            
        }catch(SQLException ex){
            System.err.println("Error en la Conexion");
            System.err.println("url: "+url);
            System.err.println("user: "+user);
            System.err.println("pass: "+pass);
            System.err.println(ex.getMessage());
        }
         
         // Creo un espacio de trabajo para ejecutar SQL
         try {
            stm = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
        
         }catch (SQLException ex){
             System.err.println("Error al crear el Statement");
         }
    }
    
    
    /**
     * Ejecutar una sentencia select.
     * @param la sentencia sql.
     * @return ResultSet con la respuesta a la sentencia sql.
     */
    public ResultSet Select(String sql){
           // ejecuto la sentencia SQL
        ResultSet rs = null;
        try {
            rs = stm.executeQuery(sql);
        
        }catch (SQLException ex){
             System.err.println("Error al ejecutar la sentencia SQL.");
        }
      
       return rs;
    }
    
    /**
     *
     */
    public int Ejecuta(String sql){
        int result = 0;
        
        try {
           result = stm.executeUpdate(sql);
        
        }catch (SQLException ex){
             System.err.println("Error al ejecutar la sentencia SQL.");
             System.err.println(ex.getMessage());
             ex.printStackTrace();
        }
        
      return result;
    }
    
    /**
     * Prepara una sentencia sql para posteriormente pasarle los datos
     * @param sql la sentencia a precompilar.
     */
    public Boolean prepararSQL(){
        Boolean ret = false;
        try{
            String sql="INSERT INTO prueba (nombre, edad)" +
               " VALUES(?, ?)";
            this.pstm = this.conexion.prepareStatement(sql);
            this.pstm.setString(1, "Eva");
            this.pstm.setInt(2, 26);
            if (this.pstm.executeUpdate() > 0){
                ret = true;
            }
            
        }catch(SQLException e){
            System.err.println("Error al crear la sentencia SQL precompilada.");
        }
        
        return ret;
    }
    
    /**
     * Cierra la conexion con la base de datos.
     */
    public void cerrarBaseDatos(){
        try{
            if (this.res != null){
                this.res.close();
            }
            if (this.stm != null){
                this.stm.close();
            }
            if (this.conexion != null){
                this.conexion.close();
            }            
            if (this.pstm != null){
                this.pstm.close();
            }
        }catch(Exception e){
            System.err.println("Error al cerrar las conexiones.");
        }
        
    }

    public Connection getConexion() {
        return conexion;
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }
    
    
    /**
     *
     */
    public void info(){
        try{
           DatabaseMetaData dbmt = conexion.getMetaData();
           System.out.println("Nombre Base Datos: "+dbmt.getDatabaseProductName());
           System.out.println("Version Base de Datos: "+dbmt.getDatabaseProductVersion());
           System.out.println("Nombre Driver: "+dbmt.getDriverName());
           System.out.println("Version Driver: "+dbmt.getDriverVersion());
           System.out.println("Soporta ResultSet forward only: "+dbmt.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
           System.out.println("Soporta ResultSet scroll insensitive: "+dbmt.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
           System.out.println("Soporta ResultSet scroll sensitive: "+dbmt.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
           System.out.println("Soporta actualizar tabla con ResultSet: "+dbmt.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
        
        }catch(SQLException e){
            System.err.println("'info()' Error al ver informacion. " + e.getMessage());
	    e.printStackTrace();
        }    
    }
    
}
