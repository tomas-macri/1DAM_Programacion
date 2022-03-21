package dao.daoUsuarios;

import dao.DaoUsuarios;
import modelo.Ingrediente;
import modelo.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DaoADDUsuariosTest {


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

        DaoUsuarios daoUsuarios = new DaoUsuarios(listaBD);

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

        DaoUsuarios daoUsuarios = new DaoUsuarios(listaBD);

        Usuario nuevoUser = new Usuario("dni1", "user4", new ArrayList<>());
        boolean seAgrego = daoUsuarios.agregarusuario(nuevoUser);

        assertEquals(nuevoUser, listaBD.get(nuevoUser.getDni()));
        assertFalse(seAgrego);
    }

}
