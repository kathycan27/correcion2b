import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class tienda {
    private JPanel panel1;
    private JTextField txtfruta;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton crearButton;
    private JButton buscarButton;
    private JButton btnactualizar;
    private JButton borrarButton;
    private JLabel codigo;
    private JLabel txtnombre;

    public static Connection getConection() {
        Connection con = null;
        String base = "tienda";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "luchito";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("si se conecto");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;


    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("Tienda de abarrotes");
        frame.setContentPane(new tienda().panel1);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
