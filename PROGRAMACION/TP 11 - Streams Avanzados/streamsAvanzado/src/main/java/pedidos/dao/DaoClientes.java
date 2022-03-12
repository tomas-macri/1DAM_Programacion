package pedidos.dao;

import com.github.javafaker.Faker;
import pedidos.dao.modelo.Cliente;
import pedidos.dao.modelo.Cuenta;

import java.util.*;

public class DaoClientes {
    //los clientes se van a registrar por email, no pudiendose repetir los mismos

    private static Map<String, Cliente> clientes = new HashMap<>();

    static {
        Faker f = new Faker();
        Random r = new Random();

        //CARGA MANUAL DE CLIENTES
        for (int i = 0; i < 10; i++) {

            String numDominioMail = String.valueOf((r.nextInt(5) + 1));
            String numCuentaMail = String.valueOf((r.nextInt(1000)+1));
            String mail = numCuentaMail + "@" + numDominioMail + ".com";
            clientes.put(mail, new Cliente(f.gameOfThrones().character(), f.gameOfThrones().city(), f.phoneNumber().toString(), mail));

            // CARGA MANUAL DE CUENTAS AL CLIENTE
            int max = r.nextInt(3)+1;
            for (int j = 0; j < max; j++) {
                clientes.get(mail).getCuentas().add(new Cuenta(f.idNumber().valid(), r.nextInt(5)+1));
            }
        }

    }
    public List<Cliente> getTodosClientes() {
        return List.copyOf(clientes.values());
    }

    public boolean addCliente(Cliente cliente) {
        boolean socioRegistrado = false;
        if (clientes.put(cliente.getEmail(), cliente) == null) {
            socioRegistrado = true;
        }
        return socioRegistrado;
    }

    public Cliente deleteCliente(String email){
        return clientes.remove(email);
    }

    public Cliente getClientePorEmail(String email){
        return clientes.get(email);
    }

    public boolean addCuenta(Cuenta cuenta, Cliente cliente){
        return cliente.getCuentas().add(cuenta);
    }

    public List<Cuenta>cuentasCliente(Cliente cliente){
        return cliente.getCuentas();
    }
}
