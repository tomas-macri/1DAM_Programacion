package dao;

import modelo.Producto;
import java.util.ArrayList;

public class DaoProducto {

    ArrayList<Producto> listaProductos;

    public DaoProducto() {
        listaProductos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto productoNuevo){
        if (!listaProductos.contains(productoNuevo)){
            return listaProductos.add(productoNuevo);
        }
        return false;
    }

    public boolean eliminarProducto(String nombProd){
        //las variables precio y stock no son necesarias a la hora de eliminar un producto, por lo que no se le piden al usuario
        return listaProductos.remove(new Producto(nombProd, 0, 0));
    }


    @Override
    public String toString() {
        return "Lista de productos = " + listaProductos;
    }
}
