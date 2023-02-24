import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class tienda {
    private JPanel panel1;
    private JTextField txtcodigo;
    private JTextField txtprecio;
    private JComboBox cbtipo;
    private JComboBox cbdetails;
    private JTextField txtunidades;
    private JButton crearButton;
    private JButton buscarButton;
    private JButton btnactualizar;
    private JButton borrarButton;
    private JLabel codigo;
    private JLabel Tipo;
    private JTextField txtnombre;
    PreparedStatement st;
    String tabla, valor;
    JComboBox combo;
    Connection con;
    Statement statement;
    ResultSet rs;
    public void rellenarTipos(String tabla, String valor,JComboBox combo)
    {
        String sql="select * from "+tabla;
        try {
            con=getConection();
            statement=con.createStatement();
            while (rs.next())
            {
                combo.addItem(rs.getString(valor));
            }
        }catch (SQLException g)
        {
            JOptionPane.showMessageDialog(null, "Error"+ g.toString());
        }
    }

    public tienda() {



    crearButton.addActionListener(new ActionListener() {
           Connection connection;
            @Override
            public void actionPerformed(ActionEvent e) {
                connection = getConection();
                String tipop = cbtipo.getSelectedItem().toString();
                String detailsp = cbdetails.getSelectedItem().toString();
                try{
                    st = connection.prepareStatement("INSERT INTO product(codigo,nombre,tipo,precio,unidad,details) VALUES (?,?,?,?,?,?)");

                    st.setString(1,txtcodigo.getText());
                    st.setString(2,txtnombre.getText());
                    st.setString(4,txtprecio.getText());
                    st.setString(5,txtunidades.getText());
                    st.setString(3, tipop);
                    st.setString(6, detailsp);

                    int res = st.executeUpdate();

                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Se creo de manera correta");
                        txtcodigo.setText("");
                        txtnombre.setText("");
                        txtprecio.setText("");
                        txtunidades.setText("");
                    }else {
                        JOptionPane.showMessageDialog(null, "No se pudo crear");
                    }

                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }


            }
        });
        cbtipo.addActionListener(new ActionListener() {
            Connection con;
            @Override
            public void actionPerformed(ActionEvent e) {

                rellenarTipos("tipos",valor,cbtipo);

            }
        });
    }

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
