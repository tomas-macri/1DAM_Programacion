package videoclub.gui;

import main.MainVideoclubPractica;
import main.StreamsVideoClub;
import videoclub.dao.modelo.Socio;

import java.util.List;

public class PRUEBAS {

    public static void main(String[] args) {
        StreamsVideoClub streamsVideoClub = new StreamsVideoClub();
        MainVideoclubPractica.setupSocioSocios();
        MainVideoclubPractica.setupProductos();
        MainVideoclubPractica.setAlquileres();
        streamsVideoClub.mostrarListas();
        streamsVideoClub.numeroSociosSancionados();
        streamsVideoClub.mediaEdadDeSociosSancionados();
        streamsVideoClub.listaDiezProductosMasAlquilados();
        streamsVideoClub.numeroProductosAlquiladosPorTipo();
    }
}
