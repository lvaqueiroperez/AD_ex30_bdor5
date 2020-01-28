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
        //OJO! ESTAMOS INSERTANDO UNA LÍNEA DE PEDIDO MÁS EN UN PEDIDO YA EXISTENTE
        //
        try {
            PreparedStatement ps
                    = conn.prepareStatement("insert into the (select a.pedido from pedido_tab a where a.ordnum = ?)select ?,ref(b),?,? from item_tab b where b.itemnum = ?");

            ps.setInt(1, 4001);
            ps.setInt(2, 48);
            ps.setInt(3, 20);
            ps.setInt(4, 10);
            ps.setInt(5, 2004);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Ex30_bdor5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificaLinea() {

        try {
            PreparedStatement ps
                    = conn.prepareStatement("update cliente_tab set clinomb = ? where clinum = ? ");

            ps.setString(1, "Alvaro Luna");
            ps.setInt(2, 5);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Ex30_bdor5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borraLinea() {

        //ordnum 4001
        //linum 48
        //NO VA EN JAVA PERO SI EN TERMINAL????
        try {
            PreparedStatement ps
                    = conn.prepareStatement("delete from the (select b.pedido from pedido_tab b where b.ordnum = ?)where linum = ?");

            ps.setInt(1, 4001);
            ps.setInt(2, 48);

        } catch (SQLException ex) {
            Logger.getLogger(Ex30_bdor5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        Ex30_bdor5 obj = new Ex30_bdor5();

        try {
            obj.Conexion();

            //obj.insertarLinea();
            //obj.modificaLinea();
            obj.borraLinea();

            obj.Cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Ex30_bdor5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
