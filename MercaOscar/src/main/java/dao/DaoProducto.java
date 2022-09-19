package dao;

import modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoProducto {




    public DaoProducto() {

    }


public boolean existeProducto(String nombre)
{
    return BD.productos.stream().anyMatch(producto -> producto.getNombre().equals(nombre));
}


    public List<Producto> verProductos()
    {
        return BD.productos.stream()
                .map(producto -> new Producto(producto.getPrecio(),producto.getNombre(), producto.getStock()))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean addProducto(Producto p)
    {
        return BD.productos.add(p);
    }


    public boolean updateProducto(Producto p)
    {
        boolean updated= false;
        int posicion = BD.productos.indexOf(p);
        if (posicion >= 0) {
            BD.productos.set(posicion, p);
            updated = true;
        }
        return updated;
    }


    public boolean updateProducto(String nombre,int cambioStock)
    {
        boolean updated= false;
        int posicion = BD.productos.indexOf(new Producto(nombre));
        if (posicion >= 0) {
            Producto p = BD.productos.get(posicion);
            p.aumentarStock(cambioStock);
            updated = true;
        }
        return updated;
    }



    public boolean borrarProducto(String nombre)
    {
        boolean updated= false;
        for (int i = 0; i < BD.productos.size(); i++) {
            if (BD.productos.get(i).getNombre().equals(nombre))
            {
                BD.productos.remove(i);
            }

        }

        return BD.productos.remove(new Producto(nombre));
    }


    public boolean updateProducto(String nombre,double precio)
    {
        boolean updated= false;
        int posicion =BD.productos.indexOf(new Producto(nombre));
        if (posicion >= 0) {
            Producto p = BD.productos.get(posicion);
            p.setPrecio(precio);
            updated = true;
        }
        return updated;
    }




}
