package servicios;

import config.Configuration;
import dao.DaoAlquileres;
import dao.DaoProductos;
import dao.DaoSocios;
import modelo.*;

import java.time.LocalDateTime;
import java.util.List;

public class ServiciosVideoclub {

    private Object TimeUnit;

    // add member
    public boolean addSocio(Member member) {
        DaoSocios daoSocios = new DaoSocios();
        return daoSocios.addSocio(member);
    }

    // borrarSocio
    public boolean borrarSocio(String dni) {
        DaoSocios daoSocio = new DaoSocios();
        if (daoSocio.getSocioPorNif(dni) != null) {
            return daoSocio.deleteSocio(daoSocio.getSocioPorNif(dni));
        }
        return false;
    }

    public Member getSocio(String nif) {
        DaoSocios daoSocio = new DaoSocios();
        return daoSocio.getSocioPorNif(nif);
    }

    public boolean addProducto(Producto producto) {
        DaoProductos daoProductos = new DaoProductos();
        return daoProductos.addProducto(producto);
    }

    public void actualizarStockProducto(Producto p, int cantidad) {
        p.setCantidad(p.getCantidad() + cantidad);
    }

    public boolean devolverProducto(String nifSocio, Poll e) {
        boolean devolucion = false;
        DaoAlquileres daoAlquileres = new DaoAlquileres();
        Renting alquiler = daoAlquileres.alquilerSocio(nifSocio);
        if (alquiler != null) {
            Producto producto = alquiler.getProductoAlquilado();
            producto.setCantidadAlquilada(producto.getCantidadAlquilada() - 1);
            //sancion --> comprobar si se pasa del tiempo
            int tiempoAlquiler;
            if (producto instanceof Documentary) {
                tiempoAlquiler = Configuration.getDiasAlquilerPeliculas();
            } else {
                tiempoAlquiler = Configuration.getDiasAlquilerVideojuego();
            }
            if (!daoAlquileres.alquilerSocio(nifSocio).getFechaAlquiler().plusSeconds(tiempoAlquiler).isAfter(LocalDateTime.now())) {
                daoAlquileres.alquilerSocio(nifSocio).getSocio().setSancionado(true);
            }
            devolucion = daoAlquileres.borrarAlquiler(daoAlquileres.alquilerSocio(nifSocio));
            //añadir encuesta a producto
            producto.getEncuestas().add(e);
        }
        return devolucion;
    }

    public String alquilarProducto(Producto p, String nifSocio) {
        String alquilado = null;
        double precio = Configuration.getPrecioAlquiler();
        DaoAlquileres daoAlquileres = new DaoAlquileres();
        DaoSocios daoSocios = new DaoSocios();
        if(daoSocios.getSocioPorNif(nifSocio) == null){
            alquilado = "Lo siento, pero aun no esta registrado.\n" +
                    "Debera primero registrarse para poder realizar un alquiler";
        } else if (daoSocios.getSocioPorNif(nifSocio).isSancionado()) {
            alquilado = "Esta sancionado proceda primero a pagar su multa.\n" +
                    "MUCHAS GRACIAS";
        }else if (daoAlquileres.alquilerSocio(nifSocio) != null) {
            alquilado = "Actualmente tiene un producto alquilado.\n" +
                    "Devuelvalo primero, por favor.";
        } else if ((p.getCantidad() - p.getCantidadAlquilada()) < 1) {
            alquilado = "Actualmente no tenemos este producto disponible.\n" +
                    "Disculpe las molestias";
        } else {
            p.setCantidadAlquilada(p.getCantidadAlquilada() + 1);
            Renting alquiler = new Renting(LocalDateTime.now(), daoSocios.getSocioPorNif(nifSocio), p);
            if(daoAlquileres.addAlquiler(alquiler)){
                alquilado = "Producto alquilado correctamente\n" +
                        "MUCHAS GRACIAS";
            }
        }
        return alquilado;
    }

    public List<Movie> getTodasPeliculas() {
        DaoProductos daoProductos = new DaoProductos();
        return daoProductos.getTodasPeliculas();
    }

    public List<Documentary> getTodosDocumentales() {
        DaoProductos daoProductos = new DaoProductos();
        return daoProductos.getTodosDocumentales();
    }

    public List<Videogame> getTodosVideoJuegos() {
        DaoProductos daoProductos = new DaoProductos();
        return daoProductos.getTodosVideojuegos();
    }

}
