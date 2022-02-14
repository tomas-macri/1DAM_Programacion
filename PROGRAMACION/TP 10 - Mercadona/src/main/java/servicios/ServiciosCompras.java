package servicios;

import dao.DaoCompras;
import modelo.Producto;
import modelo.ProductoComprado;
import modelo.Usuario;

import java.util.List;

public class ServiciosCompras {

    public boolean hayStock(int stockAComprar, Producto prod){
        DaoCompras daoCompras = new DaoCompras();
        if (stockAComprar > 0 && prod.getStock() - stockAComprar > 0){
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

    public boolean eliminarDeLaCompra(Producto prod, Usuario user){
        DaoCompras daoCompras = new DaoCompras();
<<<<<<< HEAD
        if (prod != null){
            return daoCompras.eliminarDeLaCompra(prod, user);
        }
        else{
            return false;
        }
=======
        return daoCompras.eliminarDeLaCompra(prod, user);
>>>>>>> 8344a7053992cd746b92e02f7d34e5c2c816c551
    }

    public List<ProductoComprado> getListaCompra(Usuario userLogueado) {
        DaoCompras daoCompras = new DaoCompras();
        return daoCompras.devolverLista(userLogueado);
    }
}
