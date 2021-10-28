package dao;

import modelo.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoSocios {

    private static Map<String, Member> socios = new HashMap<>();

    // devuelve si se metio o no
    public boolean addSocio(Member member) {
        boolean insertado = false;
        if (socios.get(member.getNif()) == null) {
            DaoSocios.socios.put(member.getNif(), member);
            insertado = true;
        }

        return insertado;
    }

    public List<Member> getTodosSocios() {
        List<Member> o = null;
        return o;
    }

    public Member getSocioPorNif(String nif) {
        return socios.get(nif);
    }

    public boolean deleteSocio(Member member) {
        boolean borrado = false;
        if (socios.containsKey(member.getNif())) {
            DaoSocios.socios.remove(member.getNif());
            borrado = true;
        }
        return borrado;
    }

}
