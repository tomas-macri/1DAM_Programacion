package dao.daoUsuarios;

import dao.DaoUsuarios;
import modelo.Ingrediente;
import modelo.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DaoGETUsuariosTest {

    @Test
    @DisplayName("EXISTE EL USUARIO")
    void getUsuarioValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        DaoUsuarios daoUsuarios = new DaoUsuarios(listaBD);

        boolean existe = daoUsuarios.elUsuarioExiste("dni1");

        assertTrue(existe);
    }

    @Test
    @DisplayName("NO EXISTE EL USUARIO")
    void getUsuarioNoValidoTest() {
        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        ingredienteArrayList.add(new Ingrediente("ing1"));
        ingredienteArrayList.add(new Ingrediente("ing2"));
        ingredienteArrayList.add(new Ingrediente("ing3"));


        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));

        DaoUsuarios daoUsuarios = new DaoUsuarios(listaBD);

        boolean existe = daoUsuarios.elUsuarioExiste("dni4");

        assertFalse(existe);
    }


}
