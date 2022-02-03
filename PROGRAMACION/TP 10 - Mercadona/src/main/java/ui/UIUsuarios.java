package ui;

import ui.common.Constantes;
import dao.DaoUsuarios;
import modelo.Usuario;

import java.util.Scanner;

public class UIUsuarios {

    public void inicioUIUsuarios() {
        Scanner sc = new Scanner(System.in);
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        UIUsuarios uiUsuarios = new UIUsuarios();
        int opcion;
        System.out.println(Constantes.BIENVENIDO_ADMINISTRADOR);
        System.out.println();

        do {
            opcion = uiUsuarios.mostrarMenu(sc);
            System.out.println();
            switch (opcion) {
                case 1:
                    //agregar usuarios
                    uiUsuarios.agregarUsuario(sc);
                    break;
                case 2:
                    // modificar prod
                    uiUsuarios.modificarUsuario(sc);
                    break;
                case 3:
                    // eliminar prod
                    uiUsuarios.eliminarUsuario(sc);
                    break;
                case 4:
                    // mostrar usuarios
                    System.out.println(daoUsuarios);
                    System.out.println();
                    break;
                case 5:
                    // salir
                    System.out.println(Constantes.CHAU);
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }


    private int mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println(Constantes.MENU_ADMINISTRADOR_USUARIOS);

            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    private void agregarUsuario(Scanner sc) {
        DaoUsuarios dao = new DaoUsuarios();

        System.out.println(Constantes.AGREGAR_USUARIO);
        System.out.println();
        String nomCliente;
        String dniCliente;
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_EL_DNI_DEL_USUARIO);
            dniCliente = sc.nextLine();
            System.out.println();

            System.out.println(Constantes.INGRESE_EL_NOMBRE_DEL_USUARIO);
            nomCliente = sc.nextLine();
            System.out.println();
            Usuario unUser = new Usuario(dniCliente, nomCliente);

            if (dao.agregarusuario(unUser)) {
                System.out.println(Constantes.SE_AGREGO + unUser + Constantes.A_LA_LISTA_DE_USUARIOS_DISPONIBLES);
            } else {
                System.out.println(Constantes.EL_USUARIO_YA_SE_ENCONTRABA_EN_LA_LISTA_DE_USUARIOS_O_ALGUNO_DE_SUS_CAMPOS_NO_ERAN_VALIDOS_INTENTE_NUEVAMENTE);
            }
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_AGREGAR_OTRO_USUARIO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();

        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_AGREGAR_USUARIO);
        System.out.println();
    }


    private void modificarUsuario(Scanner sc) {
        int opcionModi;
        do {
            do {
                System.out.println();
                System.out.println(Constantes.MODIFICAR_USUARIOS);
                System.out.println();
                System.out.println(Constantes.MENU_ADMINISTRADOR_MODIFICAR_USUARIOS);

                opcionModi = sc.nextInt();
                sc.nextLine();
                System.out.println();
            }while (opcionModi < 1 || opcionModi > 4);
            switchModificar(sc, opcionModi);


        } while (opcionModi != 4);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_MODIFICAR_USUARIO);
        System.out.println();
    }

    private void switchModificar(Scanner sc, int opcionModi) {
        String nomProdMod;
        if (opcionModi != 4) {
            System.out.println(Constantes.INGRESE_EL_DNI_DEL_USUARIO_A_MODIFICAR_RECUERDE_QUE_TIENE_QUE_ESTAR_PREVIAMENTE_CARGADO_EN_LA_LISTA_DE_USUARIOS_PARA_VOLVER_INGRESE_0);
            nomProdMod = sc.nextLine();
            if (!nomProdMod.equals("0")) {
                switch (opcionModi) {
                    case 1:
                        modificarUsuarioEntero(sc, nomProdMod);
                        break;
                    case 2:
                        modificarDNIUsuario(sc, nomProdMod);
                        break;
                    case 3:
                        modificarNombreUsuario(sc, nomProdMod);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void modificarDNIUsuario(Scanner sc, String dniMod) {
        DaoUsuarios dao = new DaoUsuarios();
        String nuevoDNI;
        System.out.println(Constantes.INGRESE_EL_NUEVO_DNI_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoDNI = sc.nextLine();
        // cambiar solo el dni
        if (dao.modificarUsuarioDNI(dniMod, nuevoDNI)) {
            System.out.println(Constantes.SE_MODIFICO_EL_USUARIO_AHORA_ES + dao.getUsuario(nuevoDNI));
        } else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_USUARIO_EN_NUESTRA_LISTA_DE_USUARIOS_O_SE_INTENTO_CAMBIAR_POR_UN_DNI_DE_UN_USUARIO_QUE_YA_ESTA_EN_LA_LISTA_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarNombreUsuario(Scanner sc, String dniMod) {
        DaoUsuarios dao = new DaoUsuarios();
        String nuevoNombreProd;
        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoNombreProd = sc.nextLine();
        if (dao.modificarUsuarioNombre(dniMod, nuevoNombreProd)){
            System.out.println(Constantes.SE_MODIFICO_EL_USUARIO_AHORA_ES + dao.getUsuario(dniMod));
        }
        else {
            System.out.println(Constantes.NO_SE_ENCONTRO_EL_USUARIO_EN_NUESTRA_LISTA_DE_USUARIOS_O_EL_NOMBRE_NUEVO_NO_TIENE_UN_VALOR_INTENTE_NUEVAMENTE);
        }
    }

    private void modificarUsuarioEntero(Scanner sc, String dniMod) {
        DaoUsuarios dao = new DaoUsuarios();
        String nuevoDNIUsuario;
        String nuevoNombreUsuario;

        System.out.println(Constantes.INGRESE_EL_NUEVO_DNI_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoDNIUsuario = sc.nextLine();

        System.out.println(Constantes.INGRESE_EL_NUEVO_NOMBRE_QUE_TENDRA_EL_USUARIO + dniMod + Constantes.DOS_PUNTOS);
        nuevoNombreUsuario = sc.nextLine();


        // cambiar
        Usuario userNuevo = new Usuario(nuevoDNIUsuario, nuevoNombreUsuario);
        if (dao.modificarUsuario(userNuevo, dniMod)) {
            System.out.println(Constantes.SE_MODIFICO_EL_USUARIO_AHORA_ES + dao.getUsuario(nuevoDNIUsuario));
        } else {
            System.out.println(Constantes.ERROR_BUSQUEDA_Y_MODIFICACION_USUARIOS);
        }
    }

    private void eliminarUsuario(Scanner sc) {
        DaoUsuarios dao = new DaoUsuarios();
        String dniUsuario;
        System.out.println(Constantes.ELIMINAR_USUARIOS);
        System.out.println();
        int opcion;
        do {
            System.out.println(Constantes.INGRESE_EL_DNI_DEL_USUARIO_QUE_DESEA_ELIMINAR);
            dniUsuario = sc.nextLine();

            Usuario userEliminado = dao.eliminarUsuario(dniUsuario);

            if (userEliminado != null) {
                System.out.println(Constantes.SE_ELIMINO + userEliminado + Constantes.DE_LA_LISTA_DE_USUARIOS);
            } else {
                System.out.println(Constantes.EL_USUARIO_CON_DNI + dniUsuario + Constantes.NO_SE_ENCONTRABA_EN_LA_LISTA_DE_USUARIOS_POR_LO_QUE_NO_SE_ELIMINO);
            }
            System.out.println();
            System.out.println(Constantes.INGRESE_1_SI_QUIERE_ELIMINAR_OTRO_USUARIO_O_CUALQUIER_OTRO_NUMERO_SI_QUIERE_SALIR);
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion == 1);
        System.out.println();
        System.out.println(Constantes.SALIENDO_DE_ELIMINAR_USUARIOS);
        System.out.println();
    }
}
