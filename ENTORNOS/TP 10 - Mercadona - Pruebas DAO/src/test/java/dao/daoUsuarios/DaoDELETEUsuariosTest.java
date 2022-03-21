package dao.daoUsuarios;

import dao.DaoUsuarios;
import modelo.Ingrediente;
import modelo.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DaoDELETEUsuariosTest {
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

        DaoUsuarios daoUsuarios = new DaoUsuarios(listaBD);

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

        DaoUsuarios daoUsuarios = new DaoUsuarios(listaBD);

        Usuario userEliminado = daoUsuarios.eliminarUsuario("dni4");

        assertNull(userEliminado);
    }

}


