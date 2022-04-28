package ui.pantallas.mainAdmin;

import lombok.Data;
import modelo.ProductoComprado;
import modelo.Productos.Producto;
import modelo.Usuarios.Usuario;

import java.util.LinkedHashMap;
import java.util.List;



@Data
public class MainAdminState {
    private List<Usuario>  listaUsuarios;
    private List<Producto> listaProductos;
    private String error;


    public MainAdminState(List<Usuario> usuarios, List<Producto> productos, String error) {
        this.listaUsuarios = usuarios;
        this.listaProductos = productos;
        this.error = error;
    }
}
