package uiGRAFICO;

import jakarta.inject.Inject;
import modelo.Ingrediente;
import modelo.Usuarios.Usuario;
import modelo.Usuarios.UsuarioNormal;
import servicios.ServiciosUsuarios;
import common.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainRegistrarse {

    private ServiciosUsuarios serviciosUsuariosImpl;
    private MainClientes mainClientes;

    @Inject
    public MainRegistrarse(ServiciosUsuarios serviciosUsuariosImpl, MainClientes mainClientes){
        this.serviciosUsuariosImpl = serviciosUsuariosImpl;
        this.mainClientes = mainClientes;
    }

    public void inicioRegistrarse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("CREAR UN USUARIO");
        System.out.println();
        String nomCliente;
        String dniCliente;
        do {
            System.out.println("Ingrese su DNI o -1 para volver: ");
            dniCliente = sc.nextLine();
            System.out.println();

            System.out.println("Ingrese su nombre: ");
            nomCliente = sc.nextLine();
            System.out.println();

            List<Ingrediente>  ingredienteArrayList = cargarListIngredientes(sc);
            Usuario unUser = new UsuarioNormal(dniCliente, nomCliente, ingredienteArrayList);
            if (!dniCliente.equalsIgnoreCase("-1") && serviciosUsuariosImpl.agregarUsuario(unUser)) {
                    mainClientes.inicioMenuClientes(unUser);
            }
        } while (!dniCliente.equalsIgnoreCase("-1"));
    }


    private List<Ingrediente> cargarListIngredientes(Scanner sc) {
        String ingrediente;
        List<Ingrediente> ingredienteList = new ArrayList<>();
        do {
            System.out.println("Ingrese un ingrediente al que es alergico o fin para finalizar");
            ingrediente = sc.nextLine();
            if (!ingrediente.equalsIgnoreCase(Constantes.FIN)) {
                ingredienteList.add(new Ingrediente(ingrediente));
            }
        } while (!ingrediente.equalsIgnoreCase(Constantes.FIN));
        return ingredienteList;
    }
}
