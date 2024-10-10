package umg.principal.formularios.productos;

import umg.principal.DaseDatos.Service.ProductoService;
import umg.principal.DaseDatos.model.Producto;
import umg.principal.reportes.pruebas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmProductos {
    private JLabel lblTitulo;
    private JTextField textFieldProducto;
    private JLabel lblCodigo;
    private JTextField textFieldDescripcion;
    private JPanel lblDescripcion;
    private JLabel lblOrigen;
    private JButton buttonLimpiar;
    private JButton buttonGrabar;
    private JButton buttonBuscar;
    private JComboBox comboBoxOrigen;
    private JButton buttonSalir;
    private JComboBox comboBoxReporte;
    private JLabel lblReportes;
    private JButton buttonReportes;

    public frmProductos() {

        //cargar valores del combobox origen con clave y valor de origen
        //ejemplo 1 china, 2 japon, 3 corea
        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("Corea");

        comboBoxReporte.addItem("Existencia menores a 20 unidades");
        comboBoxReporte.addItem("Reporte país especifico");
        comboBoxReporte.addItem("Existencias menores a 2000");
        comboBoxReporte.addItem("Agrupado por pais y ordenado de mayor a menor");


        buttonLimpiar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldProducto.setText("");
                textFieldDescripcion.setText("");
                comboBoxOrigen.setSelectedIndex(0);
            }
        });
        buttonGrabar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Producto: " + textFieldProducto.getText() + "\n" +
                        "Descripcion: " + textFieldDescripcion.getText() + "\n" +
                        "Origen: " + comboBoxOrigen.getSelectedItem().toString());


                Producto producto = new Producto();
                producto.setDescripcion(textFieldDescripcion.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());
                //producto.setIdProducto(Integer.parseInt(textFieldProducto.getText()));
                try{
                    new ProductoService().crearProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto guardado correctamente");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al guardar el producto");
                }



            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {


                int idProducto = textFieldProducto.getText().isEmpty() ? 0 : Integer.parseInt(textFieldProducto.getText());
                try{
                    Producto productoEncontrado = new ProductoService().obtenerProducto(idProducto);
                    if(productoEncontrado != null){
                        JOptionPane.showMessageDialog(null, "Producto encontrado: " + productoEncontrado.getDescripcion());
                        //llenar los campos con los valores encontrados
                        textFieldDescripcion.setText(productoEncontrado.getDescripcion());
                        comboBoxOrigen.setSelectedItem(productoEncontrado.getOrigen());

                        //convertir idProducto a string


                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al buscar el producto");
                }
            }
        });
        buttonSalir.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pruebas pr = new pruebas();
                if(comboBoxReporte.getSelectedItem().toString().equals("Existencia menores a 20 unidades")){
                    pruebas.GenerarReporte( "cantidad > 20");
                }else if(comboBoxReporte.getSelectedItem().toString().equals("Reporte país especifico")){
                    pruebas.GenerarReporte("origen = 'China'");
                }else if(comboBoxReporte.getSelectedItem().toString().equals("Existencias menores a 2000")){
                    pruebas.GenerarReporte("precio > 2000");
                }else if(comboBoxReporte.getSelectedItem().toString().equals("Agrupado por pais y ordenado de mayor a menor")){
                    pruebas.GenerarReportePorOrden("precio DESC");
                }else {
                    JOptionPane.showMessageDialog(null, "Error al generar el reporte");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmProductos");
        frame.setContentPane(new frmProductos().lblDescripcion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centrar el formulario
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
