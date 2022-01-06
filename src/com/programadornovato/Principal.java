package com.programadornovato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//importar librerias de mysql
import java.sql.*;
public class Principal {
    private JButton btnConexion;
    private JPanel panel1;
    private JTable table1;

    public Principal() {
        btnConexion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                leerRegistros();
            }
        });
        //inicializa la tabla tabla1 con un modelo
        table1.setModel(new DefaultTableModel());
        //agregar columnas
        ((DefaultTableModel)table1.getModel()).addColumn("Nombre");
        ((DefaultTableModel)table1.getModel()).addColumn("Apellido");
        ((DefaultTableModel)table1.getModel()).addColumn("Edad");
        //llenar los datos de la tabla con una consulta
        try{
            //crear conexion

            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/escuela", "root", "");
            //crear sentencia
            Statement st = cn.createStatement();
            //crear query
            ResultSet rs = st.executeQuery("select * from alumnos");
            //recorrer resultado
            while(rs.next()){
                //agregar filas
                ((DefaultTableModel)table1.getModel()).addRow(new Object[]{rs.getString("nombre"), rs.getString("apellido"), rs.getString("edad")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //crear metodo main
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programador Novato");
        frame.setContentPane(new Principal().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void leerRegistros(){
        try{
            //crear conexion
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/escuela", "root", "");
            //crear sentencia
            Statement st = cn.createStatement();
            //crear query
            ResultSet rs = st.executeQuery("select * from alumnos");
            //recorrer resultado
            //declarar una variable que almacenara el nombre de la columna
            String nombreColumna = "";
            //recorrer resultado
            while(rs.next()){
                nombreColumna=nombreColumna+rs.getString("nombre")+"\n";
                //System.out.println(rs.getString("nombre"));
            }
            //mostrar resultado
            JOptionPane.showMessageDialog(null, nombreColumna);
            //cerrar conexion
            cn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
