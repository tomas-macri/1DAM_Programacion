package dao;

import modelo.Cliente;

import java.util.concurrent.atomic.AtomicReference;

public class DaoMonedero {




    public void addDinerito(String dni,String monederoName,double cantidad)
    {
        Cliente c = BD.clientes.get(dni);


        c.getMonederos().forEach(monedero -> {
            if (monedero.getNombre().equals(monederoName)) {
                monedero.setDinero(monedero.getDinero() + cantidad);
            }
        });


//        c.getMonederos().stream()
//                .filter(monedero -> monedero.getNombre().equals(monederoName))
//                .findFirst().get().setDinero();




    }



}
