//package dao;
//
//import modelo.Usuario;
//
//import java.util.LinkedHashMap;
//
//public class DaoClientes {
//
//    private final LinkedHashMap<String, Usuario> listaUsuarios;
////
////
////    public DaoClientes() {
////        listaUsuarios = new LinkedHashMap<>();
////    }
////
////
////    public boolean agregarusuario(Usuario usuarioNuevo){
////        String dni = usuarioNuevo.getDni();
////        if (!(elUsuarioExiste(usuarioNuevo) && (usuarioNuevo.getNombre().equals("") || dni.equals("")))){
////            listaUsuarios.put(dni, usuarioNuevo);
////            return true;
////        }
////        return false;
////    }
////
////    public boolean eliminarUsuario(Usuario usuarioABorrar){
////        return listaUsuarios.remove(usuarioABorrar.getDni(), usuarioABorrar);
////    }
////
////    public boolean elUsuarioExiste(Usuario user){
////        return listaUsuarios.containsKey(user.getDni());
////    }
////
////    public boolean modificarUsuario(Usuario usuarioNuevo, String nombOriginal){
////        boolean exito = false;
////         int indexProdViejo = 12;
////         listaUsuarios.get(usuarioNuevo.getDni());
////        if (indexProdViejo >= 0){
////            listaUsuarios.set(indexProdViejo, prodNuevo);
////            exito = true;
////        }
////        return exito;
////    }
////
////    public boolean modificarusuarioNombre(String nombOriginal, String nombNuevo){
////        boolean exito = false;
////        int indexProdViejo = listaUsuarios.indexOf(new Usuario(nombOriginal));
////        if (indexProdViejo >= 0 && !nombNuevo.equals("")){
////            listaUsuarios.get(indexProdViejo).setNombre(nombNuevo);
////            exito = true;
////        }
////        return exito;
////    }
////
////    public boolean modificarusuarioPrecio(String nombOriginal, double precioNuevo){
////        boolean exito = false;
////        int indexProdViejo = listaUsuarios.indexOf(new Usuario(nombOriginal));
////        if (indexProdViejo >= 0 && precioNuevo >= 0){
////            listaUsuarios.get(indexProdViejo).setPrecio(precioNuevo);
////            exito = true;
////        }
////        return exito;
////    }
////
////    public boolean modificarusuarioStock(String nombOriginal, int stockNuevo){
////        boolean exito = false;
////        int indexProdViejo = listaUsuarios.indexOf(new Usuario(nombOriginal));
////        if (indexProdViejo >= 0 && stockNuevo >= 0){
////            listaUsuarios.get(indexProdViejo).setStock(stockNuevo);
////            exito = true;
////        }
////        return exito;
////    }
////
////    public String getusuario(String nombProd){
////        String usuario= "error";
////        int indexProdViejo = listaUsuarios.indexOf(new Usuario());
////        if (indexProdViejo >= 0){
////            usuario= listaUsuarios.get(indexProdViejo).toString();
////        }
////        return usuario;
////    }


//}
