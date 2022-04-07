package dao;

import modelo.Ingrediente;
import modelo.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class DaoUsuariosTest {


    @Test
    @DisplayName("SE AGREGO CORRECTAMENTE")
    void addUsuarioValidoTest(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));



        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        Usuario nuevoUser = new Usuario("dni4", "user4", new ArrayList<>());
        boolean seAgrego = daoUsuarios.agregarusuario(nuevoUser);

        assertEquals(nuevoUser, listaBD.get(nuevoUser.getDni()));
        assertTrue(seAgrego);
    }

    @Test
    @DisplayName("NO SE AGREGA USUARIO")
    void addUsuarioNoValidoTest(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        Usuario nuevoUser = new Usuario("dni1", "user4", new ArrayList<>());
        boolean seAgrego = daoUsuarios.agregarusuario(nuevoUser);

        assertEquals(nuevoUser, listaBD.get(nuevoUser.getDni()));
        assertFalse(seAgrego);
    }

    @Test
    @DisplayName("SE ELIMINO CORRECTAMENTE")
    void deleteUsuarioValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        Usuario userEliminado = daoUsuarios.eliminarUsuario("dni1");

        assertNotEquals(userEliminado, listaBD.get(userEliminado.getDni()));
        assertEquals("user1", userEliminado.getNombre());
    }

    @Test
    @DisplayName("NO SE ELIMINA USUARIO")
    void deleteUsuarioNoValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        Usuario userEliminado = daoUsuarios.eliminarUsuario("dni4");

        assertNull(userEliminado);
    }

    @Test
    @DisplayName("SE DEVUELVE EL USUARIO")
    void getUsuarioValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        Usuario usuario = daoUsuarios.getUsuario("dni1");

        assertEquals(new Usuario("dni1", "user1", ingredienteArrayList), usuario);
    }

    @Test
    @DisplayName("NO SE DEVUELVE EL USUARIO")
    void getUsuarioNoValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        Usuario usuario = daoUsuarios.getUsuario("dni4");

        assertNull(usuario);
    }

    @Test
    @DisplayName("SE MODIFICA EL USUARIO")
    void modificarUsuarioValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        boolean seCambio = daoUsuarios.modificarUsuarioNombre("dni1", listaBD.get("dni1"), new Usuario("dni1", "nuevoUser1", ingredienteArrayList));

        assertTrue(seCambio);
    }

    @Test
    @DisplayName("NO SE MODIFICA EL USUARIO")
    void moodificarUsuarioNoValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        boolean seCambio = daoUsuarios.modificarUsuarioNombre("dni4", listaBD.get("dni4"), new Usuario("dni5", "pepe", new ArrayList<>()));

        assertFalse(seCambio);
    }

    @Test
    @DisplayName("EXISTE EL USUARIO")
    void existeUsuarioTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        boolean existe = daoUsuarios.elUsuarioExiste("dni1");

        assertTrue(existe);
    }

    @Test
    @DisplayName("NO EXISTE EL USUARIO")
    void noExisteUsuarioTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        boolean existe = daoUsuarios.elUsuarioExiste("dni4");

        assertFalse(existe);
    }

    @Test
    @DisplayName("LISTA USUARIOS VALIDA")
    void listaUsersValida(){
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        BD bd = new BD();
        bd.listaUsuarios = listaBD;
        DaoUsuarios daoUsuarios = new DaoUsuarios(bd);

        assertEquals(new ArrayList<>(listaBD.values()), daoUsuarios.devolverLista());
    }


}
