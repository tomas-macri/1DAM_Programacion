package ui;

import dao.DaoProductos;
import modelo.Productos.ProductoCaducable;
import modelo.Productos.ProductoNormal;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {

        DaoProductos dao = new DaoProductos();

        dao.agregarProducto(new ProductoNormal("mm",1.0,1,null));
        dao.agregarProducto(new ProductoCaducable("mm",1.0,1,null, LocalDateTime.now()));

        UIAdminProductos ui = new UIAdminProductos();

        ui.agregarProducto(new Scanner(System.in));




    }

}

