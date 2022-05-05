package dao;

import dao.impl.DaoTarjetasImpl;
import dao.impl.DataBase;
import modelo.Ingrediente;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.apache.logging.log4j.core.util.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
public class DaoTarjetasTest {

    @InjectMocks
    private DaoTarjetasImpl daoTarjetas;

    @Mock
    private DataBase bd;

    @Captor
    private ArgumentCaptor<LinkedHashMap<String , Usuario>> captor;


    @Nested
    @DisplayName("AGREGAR TARJETAS")
    class AgregarTarjetas {
        @Test
        @DisplayName("SE AGREGA TARJETA")
        void agregarTarjeta() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);
            when(daoTarjetas.laTarjetaExiste(any(), any())).thenReturn(false);
            Tarjeta tarjeta = new Tarjeta("t1", 150);
            daoTarjetas.agregarTarjeta(tarjeta, base.get(0));

            assertAll(
            () -> {
                verify(bd).saveUsuarios(captor.capture());
                LinkedHashMap<String, Usuario> usuarios = captor.getValue();
                Set<Tarjeta> tarjetaList = usuarios.get(baseCliente.getDni()).getListaTarjetas();
                assertEquals(1, tarjetaList.size());
                Assertions.assertThat(tarjetaList).contains(tarjeta);
            }
            );

        }
    }



}


//    @Test
//    @DisplayName("SE AGREGO TARJETA CORRECTAMENTE")
//    void addTarjetaValidaTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario userLogueado = listaBD.get("dni1");
//        Tarjeta nuevaTarjeta = new Tarjeta("tar1", 200);
//
//        assertTrue(daoTarjetasImpl.agregarTarjeta(nuevaTarjeta, userLogueado));
//    }
//
//    @Test
//    @DisplayName("NO SE AGREGO TARJETA POR SALDO NEGATIVO CORRECTAMENTE")
//    void addTarjetaNoValidaSaldoNegativoTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario userLogueado = listaBD.get("dni1");
//        Tarjeta nuevaTarjeta = new Tarjeta("tar1", -50);
//
//        assertFalse(daoTarjetasImpl.agregarTarjeta(nuevaTarjeta, userLogueado));
//    }
//
//
//    @Test
//    @DisplayName("NO SE AGREGO TARJETA POR REPETICION CORRECTAMENTE")
//    void addTarjetaNoValidaRepetidaTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario userLogueado = listaBD.get("dni1");
//        Tarjeta nuevaTarjeta = new Tarjeta("tar1", 50);
//        daoTarjetasImpl.agregarTarjeta(nuevaTarjeta, userLogueado);
//
//        Tarjeta otraTarjeta = new Tarjeta("tar1", 200);
//
//
//        assertFalse(daoTarjetasImpl.agregarTarjeta(otraTarjeta, userLogueado));
//    }
//
//    @Test
//    @DisplayName("SE DEVUELVE LA TARJETA VALIDA")
//    void getTarjetaValidaTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario usuario = listaBD.get("dni1");
//        Tarjeta tarjetaValida = new Tarjeta("t1", 200);
//        usuario.getListaTarjetas().add(tarjetaValida);
//
//        Tarjeta tarjetaObtenida = daoTarjetasImpl.getTarjeta("t1", usuario);
//
//        assertEquals(tarjetaValida, tarjetaObtenida);
//    }
//
//    @Test
//    @DisplayName("NO SE DEVUELVE LA TARJETA")
//    void getTarjetaNoValidaTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario usuario = listaBD.get("dni1");
//        Tarjeta tarjetaValida = new Tarjeta("t1", 200);
//        usuario.getListaTarjetas().add(tarjetaValida);
//
//
//        Tarjeta tarjetaObtenida = daoTarjetasImpl.getTarjeta("t4", usuario);
//
//        assertNull(tarjetaObtenida);
//    }
//
//    @Test
//    @DisplayName("EXISTE LA TARJETA")
//    void existeTarjetaTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario userLogueado = listaBD.get("dni1");
//
//        userLogueado.getListaTarjetas().add(new Tarjeta("t1", 500));
//
//        boolean existe = daoTarjetasImpl.laTarjetaExiste("t1", userLogueado);
//
//
//        assertTrue(existe);
//    }
//
//    @Test
//    @DisplayName("NO EXISTE LA TARJETA")
//    void noExisteTarjetaTest() {
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        Usuario userLogueado = listaBD.get("dni1");
//        userLogueado.getListaTarjetas().add(new Tarjeta("t1", 500));
//
//        boolean existe = daoTarjetasImpl.laTarjetaExiste("t4", userLogueado);
//
//        assertFalse(existe);
//    }
//
//    @Test
//    @DisplayName("LISTA TARJETAS VALIDA")
//    void listaTarjetaValida(){
//        LinkedHashMap<String, Usuario> listaBD = new LinkedHashMap<>();
//        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
//        ingredienteArrayList.add(new Ingrediente("ing1"));
//        ingredienteArrayList.add(new Ingrediente("ing2"));
//        ingredienteArrayList.add(new Ingrediente("ing3"));
//
//
//        listaBD.put("dni1", new Usuario("dni1", "user1", ingredienteArrayList));
//        listaBD.put("dni2", new Usuario("dni2", "user2", ingredienteArrayList));
//        listaBD.put("dni3", new Usuario("dni3", "user3", ingredienteArrayList));
//
//        Usuario usuario = listaBD.get("dni1");
//
//        Tarjeta tar1 = new Tarjeta("tar1", 200);
//        Tarjeta tar2 = new Tarjeta("tar2", 150);
//
//        List<Tarjeta> listTarjetas = new ArrayList<>();
//
//        listTarjetas.add(tar1);
//        listTarjetas.add(tar2);
//
//        usuario.getListaTarjetas().add(tar1);
//        usuario.getListaTarjetas().add(tar2);
//
//        BD bd = new BD();
//        bd.listaUsuarios = listaBD;
//        DaoTarjetasImpl daoTarjetasImpl = new DaoTarjetasImpl(bd);
//
//        List<Tarjeta> listaObtenida = daoTarjetasImpl.devolverLista(usuario);
//
//        for (int i = 0; i < listaObtenida.size(); i++) {
//            assertEquals(listTarjetas.get(i).getNombre(), listaObtenida.get(i).getNombre());
//        }
//    }
//}
