package ex30_bdor5;

import java.sql.Struct;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex30_bdor5 {

    Connection conn;

    public void Conexion() throws SQLException {

        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

        conn = DriverManager.getConnection(url);

    }

    public void Cerrar() throws SQLException {

        conn.close();
    }

    /*
     a)metodo insireLinea que insira unha linea de pedido pasandolle como minimo os seguintes datos :
     ordnum, linum,item,cantidad,descuento

     probar a inserir unha linea de pedido para o pedido de numero de orden 4001 cos seguintes datos:
     linum: 48
     item : 2004
     cantidad: 20
     descuento: 10
     */
    public void insertarLinea() {

        try {
            PreparedStatement ps
                    = conn.prepareStatement("insert into pedido_tab values(987,pedido(lineas_pedido_t(48,deref(item).itemnum(2004),20,10)))");

            ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Ex30_bdor5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        Ex30_bdor5 obj = new Ex30_bdor5();

        try {
            obj.Conexion();

            obj.insertarLinea();

            obj.Cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Ex30_bdor5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
