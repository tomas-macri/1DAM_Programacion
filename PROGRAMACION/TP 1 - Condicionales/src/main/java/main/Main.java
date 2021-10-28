package main;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;


        do {
            System.out.println("Ejercicios para elegir: 9, 13, 14, 15, 16, 17, 18, 19 o 20");
            System.out.println("Seleccione -1 para salir.");
            opcion = sc.nextInt();

            switch (opcion) {
                case 9:
                    ejercicio9(sc);
                    break;
                case 13:
                    ejercicio13(sc);
                    break;
                case 14:
                    ejercicio14(sc);
                    break;
                case 15:
                    ejercicio15(sc);
                    break;
                case 16:
                    ejercicio16(sc);
                    break;
                case 17:
                    ejercicio17(sc);
                    break;
                case 18:
                    ejercicio18(sc);
                    break;
                case 19:
                    ejercicio19(sc);
                    break;
                case 20:
                    ejercicio20(sc);
                    break;
                default:
                    System.out.println("Ese ejercicio no existe");
            }
//            System.out.println("Ejercicios para elegir: 9, 13, 14, 15, 16, 17, 18, 19 o 20");
//            System.out.println("Seleccione -1 para salir.");
//            opcion = sc.nextInt();
        } while (opcion != -1);
    }

    private static void ejercicio9 (Scanner sc){
//      Ejercicio 9
//      Algoritmo que pida tres números y los muestre ordenados (de mayor a menor);
//      Al haber numeros iguales se imprimirán como mayores los numeros ingresados primero

        System.out.println("Ingrese el primer número (-1 para finalizar): ");
        int numero1 = sc.nextInt();
        sc.nextLine();
        while (numero1 != -1) {
            System.out.println("Ingrese el segundo número: ");
            int numero2 = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese el tercer número: ");
            int numero3 = sc.nextInt();
            sc.nextLine();

            if (numero1 >= numero2 && numero1 >= numero3) {

                System.out.print(numero1 + ", ");
                if (numero2 >= numero3) {
                    System.out.print(numero2 + ", ");
                    System.out.println(numero3);
                } else {
                    System.out.print(numero3 + ",  ");
                    System.out.println(numero2);
                }
            } else if (numero2 >= numero1 && numero2 >= numero3) {

                System.out.print(numero2 + ", ");
                if (numero1 >= numero3) {
                    System.out.print(numero1 + ", ");
                    System.out.println(numero3);
                } else {
                    System.out.print(numero3 + ",  ");
                    System.out.println(numero1);
                }
            } else {

                System.out.print(numero3 + ", ");
                if (numero1 >= numero2) {
                    System.out.print(numero1 + ", ");
                    System.out.println(numero2);
                } else {
                    System.out.print(numero2 + ",  ");
                    System.out.println(numero1);
                }

            }
            System.out.println("Ingrese el primer número (-1 para finalizar): ");
            numero1 = sc.nextInt();
            sc.nextLine();
        }

    }


    private static void ejercicio13 (Scanner sc){
//      Ejercicio 13
//      Escribe un programa que pida una fecha (día, mes y año) y diga si es correcta.

        System.out.print("Ingrese un día (-10 para terminar): ");
        int day = sc.nextInt();
        sc.nextLine();
        while (day != -10) {

            System.out.print("Ingrese un mes: ");
            int month = sc.nextInt();
            sc.nextLine();

            System.out.print("Ingrese un año: ");
            int year = sc.nextInt();
            sc.nextLine();

            if (year > 0 && year < 2022) {
                if (month <= 12 && month >= 1) {
                    if (day > 0 && day < 32) {
                        switch (month) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                                System.out.println("El " + day + "/" + month + "/" + year + " es un día válido");
                                break;
                            case 2:
                                if (day < 28 || (day == 29 && (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))) {
                                    System.out.println("El " + day + "/" + month + "/" + year + " es un día válido");
                                } else {
                                    System.out.println("El " + day + " de Febrero no existe en el " + year);
                                }
                                break;
                            case 4:
                                if (day < 31) {
                                    System.out.println("El " + day + "/" + month + "/" + year + " es un día válido");
                                } else {
                                    System.out.println("El " + day + " de Abril no existe");
                                }
                                break;
                            case 6:
                                if (day < 31) {
                                    System.out.println("El " + day + "/" + month + "/" + year + " es un día válido");
                                } else {
                                    System.out.println("El " + day + " de Junio no existe");
                                }
                                break;
                            case 9:
                                if (day < 31) {
                                    System.out.println("El " + day + "/" + month + "/" + year + " es un día válido");
                                } else {
                                    System.out.println("El " + day + " de Septiembre no existe");
                                }
                                break;
                            case 11:
                                if (day < 31) {
                                    System.out.println("El " + day + "/" + month + "/" + year + " es un día válido");
                                } else {
                                    System.out.println("El " + day + " de Noviembre no existe");
                                }
                                break;
                        }
                    } else {
                        System.out.println("El día no puede ser menor que 1 ni mayor que 31");
                    }
                } else {
                    System.out.println(("El mes ingresado no es válido"));
                }
            } else {
                System.out.println("El año no es válido, debe ser entre 0 y 2021");
            }

            System.out.print("Ingrese un día (-10 para terminar): ");
            day = sc.nextInt();
            sc.nextLine();
        }
    }

    private static void ejercicio14 (Scanner sc){
//      Ejercicio 14
//      La asociación de vinicultores tiene como política fijar un precio inicial al kilo de uva, la cual se
//      clasifica en tipos A y B, y además en tamaños 1 y 2. Cuando se realiza la venta del producto,
//      ésta es de un solo tipo y tamaño, se requiere determinar cuánto recibirá un productor por la
//      uva que entrega en un embarque, considerando lo siguiente: si es de tipo A,
//      se le cargan 20 céntimos al precio inicial cuando es de tamaño 1; y 30 céntimos si es de
//      tamaño 2. Si es de tipo B, se rebajan 30 céntimos cuando es de tamaño 1, y 50 céntimos
//      cuando es de tamaño 2. Realice un algoritmo para determinar la ganancia obtenida.

        System.out.println("Ingrese la cantidad de kilos del embarque: ");
        int embarque = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el tipo de uva (A o B): ");
        String tipo = sc.nextLine();
        System.out.println("Ingrese el tamaño de la uva (1 o 2): ");
        int tamano = sc.nextInt();
        double gananciaTotal;

        if (tipo.equalsIgnoreCase("A")) {
            if (tamano == 1) {
                gananciaTotal = 0.2 * embarque;
            } else {
                gananciaTotal = 0.3 * embarque;
            }
        } else {
            if (tamano == 1) {
                gananciaTotal = -0.3 * embarque;

            } else {
                gananciaTotal = -0.5 * embarque;
            }
        }
        System.out.println("La ganancia de un envío de " + embarque + "kg. será de " + gananciaTotal + "€");
    }

    private static void ejercicio15 (Scanner sc){
//      Ejercicio 15
//      El director de una escuela está organizando un viaje de estudios, y requiere determinar
//      cuánto debe cobrar a cada alumno y cuánto debe pagar a la compañía de viajes por el
//      servicio. La forma de cobrar es la siguiente: si son 100 alumnos o más, el costo por cada
//      alumno es de 65 euros; de 50 a 99 alumnos, el costo es de 70 euros, de 30 a 49, de 95
//      euros, y si son menos de 30, el costo de la renta del autobús es de 4000 euros, sin importar
//      el número de alumnos.
//      Realice un algoritmo que permita determinar el pago a la compañía de autobuses y lo que
//      debe pagar cada alumno por el viaje

        System.out.println("Ingrese la cantidad de alumnos que irán al viaje de estudios: ");
        int cantAlumnos = sc.nextInt();
        int valorRenta;
        int precioPorAlumno;
        sc.nextLine();
        if (cantAlumnos > 0) {
            if (cantAlumnos >= 100) {
                precioPorAlumno = 65;
                valorRenta = precioPorAlumno * cantAlumnos;
            } else if (cantAlumnos >= 50) {
                precioPorAlumno = 70;
                valorRenta = precioPorAlumno * cantAlumnos;
            } else if (cantAlumnos >= 30) {
                precioPorAlumno = 95;
                valorRenta = precioPorAlumno * cantAlumnos;
            } else {
                valorRenta = 4000;
                precioPorAlumno = valorRenta / cantAlumnos;
            }
            System.out.println("El costo de la renta del autobus es de " + valorRenta + "€ y cada alumno deberá pagar un total de " + precioPorAlumno + "€.");
        } else {
            System.out.println("La cantidad de alumnos no es válida.");
        }
    }

    private static void ejercicio16 (Scanner sc){
//      Ejercicio 16
//      La política de cobro de una compañía telefónica es: cuando se realiza una llamada, el cobro
//      es por el tiempo que esta dura, de tal forma que los primeros cinco minutos cuestan 1 euro,
//      los siguientes tres, 80 céntimos, los siguientes dos minutos, 70 céntimos, y a partir del
//      décimo minuto, 50 céntimos.
//      Además, se carga un impuesto de 3 % cuando es domingo, y si es otro día, en turno de
//      mañana, 15 %, y en turno de tarde, 10 %. Realice un algoritmo para determinar cuánto debe
//      pagar por cada concepto una persona que realiza una llamada.

        System.out.println("Cuántos minutos durará su llamada? ");
        int minutosLlamada = sc.nextInt();
        sc.nextLine();
        if (minutosLlamada > 0) {
            double precioFinal;
            double cargoLlamada;
            System.out.println("Qué día realizará la llamada?");
            String dia = sc.nextLine();
            if (!dia.equalsIgnoreCase("domingo")) {
                System.out.println("Hará la llamada por la tarde o por la mañana? Ingrese M o T: ");
                if (sc.nextLine().equalsIgnoreCase("M")) {
                    cargoLlamada = 1.15;
                } else {
                    cargoLlamada = 1.10;
                }
            } else {
                cargoLlamada = 1.03;
            }
            if (minutosLlamada <= 5) {
                precioFinal = minutosLlamada * cargoLlamada;
            } else if (minutosLlamada <= 8) {
                precioFinal = minutosLlamada * 0.8 * cargoLlamada;
            } else if (minutosLlamada <= 10) {
                precioFinal = minutosLlamada * 0.7 * cargoLlamada;
            } else {
                precioFinal = minutosLlamada * 0.5 * cargoLlamada;
            }

            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("El precio final de la llamada será de: " + df.format(precioFinal) + "€.");
        } else {
            System.out.println("Cantidad de minutos no válida");
        }

    }

    private static void ejercicio17 (Scanner sc){
//     Realiza un programa que pida por teclado el resultado (dato entero) obtenido al lanzar un
//     dado de seis caras y muestre por pantalla el número en letras (dato cadena) de la cara
//     opuesta al resultado obtenido.
//     •Nota 1: En las caras opuestas de un dado de seis caras están los números: 1-6, 2-5 y 3-4.
//     •Nota 2: Si el número del dado introducido es menor que 1 o mayor que 6, se mostrará
//     el mensaje: “ERROR: número incorrecto.”.
//     Ejemplo:
//      Introduzca número del dado: 5
//      En la cara opuesta está el "dos".

        System.out.println("Ingrese el número entero del dado: ");
        int numeroDado = sc.nextInt();
        sc.nextLine();
        if (numeroDado >= 1 && numeroDado <= 6) {
            switch (numeroDado) {
                case 1:
                    System.out.println("En la cara opuesta del dado está el número seis");
                    break;
                case 2:
                    System.out.println("En la cara opuesta del dado está el número cinco");
                    break;
                case 3:
                    System.out.println("En la cara opuesta del dado está el número cuatro");
                    break;
                case 4:
                    System.out.println("En la cara opuesta del dado está el número tres");
                    break;
                case 5:
                    System.out.println("En la cara opuesta del dado está el número dos");
                    break;
                case 6:
                    System.out.println("En la cara opuesta del dado está el número uno");
                    break;
            }

        } else {
            System.out.println("ERROR: número incorrecto.");
        }

    }

    private static void ejercicio18 (Scanner sc){
//      Ejercicio 18
//      Realiza un programa que pida el día de la semana (del 1 al 7) y escriba el día
//      correspondiente. Si introducimos otro número nos da un error.

        System.out.println("Dime un número de día (1 al 7): ");
        int numeroDia = sc.nextInt();
        switch (numeroDia) {
            case 1:
                System.out.println("El día " + numeroDia + " es el lunes.");
                break;
            case 2:
                System.out.println("El día " + numeroDia + " es el martes.");
                break;
            case 3:
                System.out.println("El día " + numeroDia + " es el miércoles.");
                break;
            case 4:
                System.out.println("El día " + numeroDia + " es el jueves.");
                break;
            case 5:
                System.out.println("El día " + numeroDia + " es el viernes.");
                break;
            case 6:
                System.out.println("El día " + numeroDia + " es el sábado.");
                break;
            case 7:
                System.out.println("El día " + numeroDia + " es el domingo.");
                break;
            default:
                System.out.println("Ese número de día no es válido.");
        }
    }


    private static void ejercicio19 (Scanner sc){
//      Ejercicio 19
//      Escribe un programa que pida un número entero entre uno y doce e imprima el número de
//      días que tiene el mes correspondiente.

        System.out.println("Ingrese el número de mes (entre 1 y 12): ");
        int numeroMes = sc.nextInt();
        switch (numeroMes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("El mes tiene 31 días");
                break;
            case 2:
                System.out.println("El mes tiene 28 días");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("El mes tiene 30 días");
                break;
            default:
                System.out.println("Número de nes incorrecto");
        }
    }

    private static void ejercicio20 (Scanner sc){
//      Ejercicio 20
//      Una compañía de transporte internacional tiene servicio en algunos países de América del
//      Norte, América Central, América del Sur, Europa y Asia. El costo por el servicio de transporte
//      se basa en el peso del paquete y la zona a la que va dirigido. Lo anterior se muestra en la
//      tabla:
//      Zona      Ubicación           Costo/gramo
//      1     América del Norte       24.00 euros
//      2     América Central         20.00 euros
//      3     América del Sur         21.00 euros
//      4     Europa                  10.00 euros
//      5     Asia                    18.00 euros
//      Parte de su política implica que los paquetes con un peso superior a 5 kg no son
//      transportados, esto por cuestiones de logística y de seguridad.
//      Realice un algoritmo para determinar el cobro por la entrega de un paquete o, en su caso, el
//      rechazo de la entrega.

        System.out.println("De cuántos gramos será el paquete que envíe?");
        int gramos = sc.nextInt();
        sc.nextLine();
        if (gramos > 0 && gramos <= 5000) {
            System.out.println("Cuál es el destino final de su paquete? Ingrese el numero correspondiente (1: América del Norte; 2: América Central;" +
                    " 3: América del Sur; 4: Europa; 5: Asia");
            int zona = sc.nextInt();
            sc.nextLine();
            int valorEnvioFinal = 0;
            switch (zona) {
                case 1:
                    valorEnvioFinal = gramos * 24;
                    break;
                case 2:
                    valorEnvioFinal = gramos * 20;
                    break;
                case 3:
                    valorEnvioFinal = gramos * 21;
                    break;
                case 4:
                    valorEnvioFinal = gramos * 10;
                    break;
                case 5:
                    valorEnvioFinal = gramos * 18;
                    break;
                default:
                    System.out.println("Zona inexistente");
                    break;

            }
            if (zona >= 1 && zona <= 5) {
                System.out.println("El costo del envío será de " + valorEnvioFinal + "€.");
            }
        } else if (gramos > 5000) {
            System.out.println("Su paquete es muy pesado para ser enviado");
        } else {
            System.out.println("La cantidad de gramos no puede ser 0 o menos.");
        }
    }
}
