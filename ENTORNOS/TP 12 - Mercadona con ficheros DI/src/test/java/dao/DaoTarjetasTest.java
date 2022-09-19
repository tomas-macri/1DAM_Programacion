package dao;

import dao.impl.DaoTarjetasImpl;
import dao.impl.DataBase;
import modelo.Ingrediente;
import modelo.Tarjeta;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import org.apache.logging.log4j.core.util.Assert;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

@ExtendWith(SystemStubsExtension.class)
@ExtendWith(MockitoExtension.class)
class DaoTarjetasTest {

    @InjectMocks
    private DaoTarjetasImpl daoTarjetas;

    @Mock
    private DataBase bd;

    @Captor
    private ArgumentCaptor<LinkedHashMap<String, Usuario>> captor;


    @Nested
    @DisplayName("AGREGAR TARJETAS")
    class AgregarTarjetas {
        @Test
        @DisplayName("SE AGREGA TARJETA")
        void agregarTarjeta() {
            //given
            Usuario baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When

            when(bd.loadUsuarios()).thenReturn(base);
            Tarjeta tarjeta = new Tarjeta("t1", 150);
            daoTarjetas.agregarTarjeta(tarjeta, base.get("123"));

            //Then
            assertAll(
                    () -> {
                        verify(bd).saveUsuarios(captor.capture());
                        LinkedHashMap<String, Usuario> usuarios = captor.getValue();
                        Set<Tarjeta> tarjetaList = usuarios.get(baseCliente.getDni()).getListaTarjetas();
                        assertEquals(1, tarjetaList.size());
                        assertThat(tarjetaList).contains(tarjeta);
                    }
            );

        }

        @Test
        @DisplayName("NO SE AGREGA TARJETA YA EXISTENTE")
        void noSeAgregaTarjetaPorqueExiste() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            Set<Tarjeta> baseTarjetas = new HashSet<>();
            baseTarjetas.add(new Tarjeta("t1", 150));
            baseCliente.setListaTarjetas(baseTarjetas);
            base.put(baseCliente.getDni(), baseCliente);

            //When

            Tarjeta tarjeta = new Tarjeta("t1", 200);
            daoTarjetas.agregarTarjeta(tarjeta, base.get("123"));

            //Then
            assertAll(
                    () -> {
                        verify(bd, times(0)).saveUsuarios(base);

                        Set<Tarjeta> tarjetaList = base.get(baseCliente.getDni()).getListaTarjetas();
                        assertEquals(1, tarjetaList.size());
                        assertEquals(150, tarjetaList.stream().findFirst().get().getSaldo());
                    }
            );
        }


        @Test
        @DisplayName("NO SE AGREGA TARJETA POR SALDO NEGATIVO")
        void noSeAgregaTarjetaPorSaldoNegativo() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When
            Tarjeta tarjeta = new Tarjeta("t1", -200);
            daoTarjetas.agregarTarjeta(tarjeta, base.get("123"));

            assertAll(
                    () -> {
                        verify(bd, times(0)).saveUsuarios(base);

                        Set<Tarjeta> tarjetaList = base.get(baseCliente.getDni()).getListaTarjetas();
                        assertEquals(0, tarjetaList.size());
                    }
            );
        }

        @Test
        @DisplayName("NO SE AGREGA TARJETA POR NOMBRE VACIO")
        void noSeAgregaTarjetaPorNombreNoValido() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When
            Tarjeta tarjeta = new Tarjeta("", 200);
            daoTarjetas.agregarTarjeta(tarjeta, base.get("123"));

            assertAll(
                    () -> {
                        verify(bd, times(0)).saveUsuarios(base);

                        Set<Tarjeta> tarjetaList = base.get(baseCliente.getDni()).getListaTarjetas();
                        assertEquals(0, tarjetaList.size());
                    }
            );
        }
    }

    @Nested
    @DisplayName("Get Tarjetas")
    class GetTarjetas {
        @Test
        @DisplayName("SE OBTIENE TARJETA")
        void seObtieneTarjeta() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            Set<Tarjeta> baseTarjetas = new HashSet<>();
            baseTarjetas.add(new Tarjeta("t1", 150));
            baseCliente.setListaTarjetas(baseTarjetas);
            base.put(baseCliente.getDni(), baseCliente);

            //When
            Tarjeta recuperada = daoTarjetas.getTarjeta("t1", base.get("123"));

            //Then
            assertAll(
                    () -> {
                        assertEquals("t1", recuperada.getNombre());
                        assertEquals(150, recuperada.getSaldo());
                    }
            );
        }

        @Test
        @DisplayName("NO TIENE ESA TARJETA")
        void noSeObtieneTarjeta() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            Set<Tarjeta> baseTarjetas = new HashSet<>();
            baseTarjetas.add(new Tarjeta("t1", 150));
            baseCliente.setListaTarjetas(baseTarjetas);
            base.put(baseCliente.getDni(), baseCliente);

            //When
            Tarjeta recuperada = daoTarjetas.getTarjeta("t2", base.get("123"));

            //Then
            assertAll(
                    () -> {
                        assertEquals("error", recuperada.getNombre());
                        assertEquals(0, recuperada.getSaldo());
                    }
            );
        }
    }

    @Nested
    @DisplayName("Existe Tarjeta")
    class ExisteTarjeta {
        @Test
        @DisplayName("EXISTE TARJETA")
        void existeTarjeta() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            Set<Tarjeta> baseTarjetas = new HashSet<>();
            baseTarjetas.add(new Tarjeta("t1", 150));
            baseCliente.setListaTarjetas(baseTarjetas);
            base.put(baseCliente.getDni(), baseCliente);

            //When
            boolean existe = daoTarjetas.laTarjetaExiste("t1", base.get("123"));

            //Then
            assertTrue(existe);

        }

        @Test
        @DisplayName("NO EXISTE TARJETA")
        void noExisteTarjeta() {
            //given
            UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
            LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
            base.put(baseCliente.getDni(), baseCliente);

            //When
            boolean existe = daoTarjetas.laTarjetaExiste("t1", base.get("123"));

            //Then
            assertFalse(existe);
        }
    }

    @Test
    @DisplayName("Lista de Tarjetas valida")
    void listarTarjetas() {
        //given
        UsuarioNormal baseCliente = new UsuarioNormal("123", "juan", new ArrayList<>());
        LinkedHashMap<String, Usuario> base = new LinkedHashMap<>();
        Set<Tarjeta> baseTarjetas = new HashSet<>();
        Tarjeta t1 = new Tarjeta("t1", 150);
        Tarjeta t2 = new Tarjeta("t2", 200);
        baseTarjetas.add(t1);
        baseTarjetas.add(t2);
        baseCliente.setListaTarjetas(baseTarjetas);
        base.put(baseCliente.getDni(), baseCliente);


        //When
        List<Tarjeta> tarjetas = daoTarjetas.devolverLista(base.get("123"));

        //Then
        assertEquals(2, tarjetas.size());
        assertTrue(tarjetas.contains(t1));
        assertTrue(tarjetas.contains(t2));
    }
}

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
