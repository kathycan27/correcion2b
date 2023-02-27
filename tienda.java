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
            if(txtcodigo.getText().equals("")||txtnombre.getText().equals("")||txtprecio.getText().equals("")||txtunidades.getText().equals(""))
                {
                     JOptionPane.showMessageDialog(null, "Uno varios campos vacios");
                }else {
                try {
                 con = getConection();
                 statement = con.createStatement();
                 rs = statement.executeQuery("select * from product1 where codigo=" + txtcodigo.getText() + ";");

             if(rs.next()){

        JOptionPane.showMessageDialog(null,"Codigo ya ocupado||Producto ya registrado");
    }else {
                 try {

                     st = connection.prepareStatement("INSERT INTO product1(codigo,nombre,tipo,precio,unidad,details) VALUES (?,?,?,?,?,?)");


                     st.setString(1, txtcodigo.getText());
                     st.setString(2, txtnombre.getText());
                     st.setString(4, txtprecio.getText());
                     st.setString(5, txtunidades.getText());
                     st.setString(3, tipop);
                     st.setString(6, detailsp);

                     int res = st.executeUpdate();

                     if (res > 0) {
                         JOptionPane.showMessageDialog(null, "Se creo de manera correta");
                         txtcodigo.setText("");
                         txtnombre.setText("");
                         txtprecio.setText("");
                         txtunidades.setText("");
                     }

                 } catch (HeadlessException | SQLException f) {
                     System.out.println(f);

             }}}
             catch (Exception s) {

             }    }}






        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try {
                    String combotipo=cbtipo.getSelectedItem().toString();
                    con = getConection();
                    statement = con.createStatement();
                    ResultSet rs;
                    rs = statement.executeQuery("select * from product1 where codigo=" + txtcodigo.getText() + ";");
                    if(rs.next()){
                     do{
                        txtnombre.setText(rs.getString("nombre"));
                        cbtipo.setSelectedItem(rs.getString("tipo"));
                        txtprecio.setText(rs.getString("precio"));
                        txtunidades.setText(rs.getString("unidad"));
                        cbdetails.setSelectedItem(rs.getString("details"));
                    }while (rs.next());
                        JOptionPane.showMessageDialog(null,"Producto Encontrado||Listo");
                    }else {
                        JOptionPane.showMessageDialog(null,"El producto aun no ha sido registrado || No se encuentra en la base de datos");
                    }
                } catch (Exception s) {

                }
            }
        });
        btnactualizar.addActionListener(new ActionListener() {
            Connection con2;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String tipoa = cbtipo.getSelectedItem().toString();
                    String detailsa = cbdetails.getSelectedItem().toString();
                    con2 = getConection();
                    st = con2.prepareStatement("UPDATE product1 SET tipo = ?, nombre = ? ,precio  = ? ,unidad = ? ,details = ? WHERE codigo ="+txtcodigo.getText());

                    st.setString(1,tipoa);
                    st.setString(2,txtnombre.getText());
                    st.setString(3,txtprecio.getText());
                    st.setString(4, txtunidades.getText());
                    st.setString(5, detailsa);


                    System.out.println(st);
                    int res = st.executeUpdate();

                    if(res > 0 ){
                        JOptionPane.showMessageDialog(null,"La actualización se realizado con EXITO!");
                        txtcodigo.setText("");
                        txtnombre.setText("");
                        txtprecio.setText("");
                        txtunidades.setText("");

                    }else{
                        JOptionPane.showMessageDialog(null,"Error, producto no registrado");
                    }
                    con2.close();
                }catch (SQLException f){
                    System.out.println(f);
                }
            }
        }
    );
        borrarButton.addActionListener(new ActionListener() {
            Connection con4;
            @Override
            public void actionPerformed(ActionEvent e) {

                        con4 = getConection();

                        try {
                            st = con4.prepareStatement("DELETE FROM product1 WHERE codigo ="+txtcodigo.getText());
                            int res = st.executeUpdate();

                            if(res > 0 ){
                                JOptionPane.showMessageDialog(null,"Se elemino con exito");
                            }else{
                                JOptionPane.showMessageDialog(null,"No existe el producto");
                            }
                        }catch (HeadlessException | SQLException f){
                            System.out.println(f);
                        }
                    }

        });
    }



    public static Connection getConection() {
        Connection con = null;
        String base = "tienda";
        String url = "jdbc:mysql://localhost:3307/" + base;
        String user = "root";
        String password = "Luchito2724";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
           // System.out.println("si se conecto");
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
