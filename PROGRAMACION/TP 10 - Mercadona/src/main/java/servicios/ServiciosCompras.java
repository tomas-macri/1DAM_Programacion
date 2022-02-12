package servicios;

import dao.DaoCompras;
import dao.DaoProductos;
import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Usuario;

public class ServiciosCompras {

    public boolean hayStock(int stockAComprar, Producto prod){
        DaoCompras daoCompras = new DaoCompras();
        if (stockAComprar < 0 && stockAComprar - prod.getStock() > 0){
            daoCompras.quitarStock(stockAComprar, prod);
            return true;
        }
        return false;
    }

    public boolean agregarALaCompra(ProductoComprado prodComp, Usuario userLogueado){
        DaoCompras daoCompras = new DaoCompras();

        if (prodComp != null && userLogueado != null){
            daoCompras.agregarALaCompra(prodComp, userLogueado);
            return true;
        }
        return false;
    }

}
