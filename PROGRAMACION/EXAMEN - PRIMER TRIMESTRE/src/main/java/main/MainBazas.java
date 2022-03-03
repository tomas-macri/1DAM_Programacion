package main;

import servicios.ServiciosBaraja;

import java.util.Scanner;

public class MainBazas {
    public static void main(String[] args) {

//        2) (6.5Pto) Juegos de cartas con baraja española, el proceso del juego es el siguiente:
//        - Decidir número de jugadores
//        - Repartir cartas, se reparten 3 cartas a cada jugador.
//        - Jugar las bazas hasta que se acaben las cartas de la baraja, tiene que haber para repartir 3 a
//        cada jugador.
//        - En cada baza, cada jugador descarta una carta en la mesa, el que eche la mayor(valores del 1
//        al 10) se lleva las cartas, en caso de empate sacan una aleatoria de la baraja los jugadores
//        que hayan empatado y la echan en la mesa, el que la tenga más alta se lleva todas las cartas
//        de la mesa.
//        - Al final el que se haya llevado más cartas gana.

        int cantCartasJugador = 3;
        Scanner sc = new Scanner(System.in);
        ServiciosBaraja sb = new ServiciosBaraja();
        int[] baraja = sb.inicializarBaraja(40,10);
        int contPosicionBaraja = 0;
        // tengo baraja

        System.out.println("Cuantos jugadores seran?");
        int cantJugadores = sc.nextInt();
        sc.nextLine();

        int[][] cartasJugadorxRonda = new int[cantJugadores][cantCartasJugador];
        int[] arrayCantCartasLevantadas = new int[cantJugadores];

        // repartir 3 por jugador mientras se pueda
        while (sb.quedanCartas(contPosicionBaraja, cantCartasJugador, cantJugadores, 40)){
            System.out.println("NUEVA RONDA");
            System.out.println();

            // reparto las cartas a los jugadores
            for (int i = 0; i < cartasJugadorxRonda.length; i++) {
                for (int j = 0; j < cartasJugadorxRonda[i].length; j++) {
                    cartasJugadorxRonda[i][j] = baraja[contPosicionBaraja];
                    contPosicionBaraja++;
                }
            }
            //todos los jugadores ya tienen sus 3 cartas

            // inicio con los turnos
            int contTurnosJugados = 0;

            while (contTurnosJugados < cantCartasJugador){// mientras se hayan jugado menos turnos que la cantidad de cartas que recibe cada jugador (3)
                System.out.println("---------TURNO " + (contTurnosJugados+1) + "---------");
                System.out.println();
                int valorMayorCartaTurno = 0;
                int posiMayorCartaTurno = 0;
                int cartasUtilizadasPorTurno = 0;
                for (int i = 0; i < cartasJugadorxRonda.length; i++) {
                    System.out.println("CARTA DEL JUGADOR " + (i+1) + ": " + cartasJugadorxRonda[i][contTurnosJugados]);
                    cartasUtilizadasPorTurno++;
                    if (cartasJugadorxRonda[i][contTurnosJugados] > valorMayorCartaTurno){
                        valorMayorCartaTurno = cartasJugadorxRonda[i][contTurnosJugados];
                        posiMayorCartaTurno = i;
                    }
                    System.out.println();
                }
                int cantJugadoresEmpatados = 1; // uno que es el que tiene la de mayor valor
                // veo que no haya otra carta con el mismo valor de la mayor
                for (int j = posiMayorCartaTurno; j < cartasJugadorxRonda.length; j++) { // j esta iniciallizada en la posi porque en el for me aseguro que no hay una igual de alta antes que esta carta.
                    if (j != posiMayorCartaTurno && cartasJugadorxRonda[j][contTurnosJugados] == valorMayorCartaTurno){
                        cantJugadoresEmpatados++;   //me guardo la cantidad de jugadores que empataron, para poder crear el array con la longitud exacta
                    }
                }
                // FIN DEL TURNO, VEO SI HAY GANADOR O HAY QUE DESEMPATAR


                if (cantJugadoresEmpatados != 1 && contPosicionBaraja + cantJugadoresEmpatados <= 40){ //no hay empate, no hay solo un jugador con la carta mas alta y alcanzan las cartas para hacer un desempate
                    System.out.println("HAY DESEMPATEEEEEEE");
                    System.out.println();
                    int[] jugadoresEmpatados = new int[cantJugadoresEmpatados];

                    for (int i = posiMayorCartaTurno, j = 0; i < cartasJugadorxRonda.length; i++) { // i esta iniciallizada en la posi porque en el for me aseguro que no hay una igual de alta antes que esta carta.
                        if (cartasJugadorxRonda[i][contTurnosJugados] == valorMayorCartaTurno){
                            jugadoresEmpatados[j] = i; //me guardo la posicion del jugador igualado
                            j++;
                        }
                    }

                    int[] nuevasCartasJugsEmpatados = new int[cantJugadoresEmpatados];
                    valorMayorCartaTurno = 0;
                    posiMayorCartaTurno = 0; // posicion y valor vuelven a 0 para guardar el maximo del desempate
                    for (int i = 0; i < nuevasCartasJugsEmpatados.length; i++) {
                        nuevasCartasJugsEmpatados[i] = baraja[contPosicionBaraja];
                        contPosicionBaraja++;
                        cartasUtilizadasPorTurno++;
                        System.out.println("CARTA DEL JUGADOR " + (jugadoresEmpatados[i]+1) + ": " + nuevasCartasJugsEmpatados[i]);
                        if (nuevasCartasJugsEmpatados[i] > valorMayorCartaTurno){
                            valorMayorCartaTurno = nuevasCartasJugsEmpatados[i];
                            posiMayorCartaTurno = jugadoresEmpatados[i]; //  la posicion no es la del array de jugadores empatados sino la del array de jugadores original
                        }
                        // si hay otro empate gana el que le sale primero
                    }
                }
                // Al que gano la ronda le sumo todas las cartas
                arrayCantCartasLevantadas[posiMayorCartaTurno] += cartasUtilizadasPorTurno;
                System.out.println("Este turno lo gano el jugador " + (posiMayorCartaTurno+1) + " y se llevo un total de " + cartasUtilizadasPorTurno + " cartas");
                System.out.println();
                // sumar el contador de cartas jugadas
                contTurnosJugados++;
            }
            System.out.println("-------------------------------------------------------------------------");
            System.out.println();
        }
        // mostrar resultados
        for (int i = 0; i < arrayCantCartasLevantadas.length; i++) {
            System.out.println("El jugador " + (i+1) + " levanto " + arrayCantCartasLevantadas[i] + " cartas");
        }
    }
}
