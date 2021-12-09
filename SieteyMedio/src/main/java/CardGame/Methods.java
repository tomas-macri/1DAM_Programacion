package CardGame;

public class Methods {


    public static int getRandomNumber(int max, int min){
        return (int) Math.floor(Math.random()*(min-max+1)+(max));
    }

    public static double sumPoints(double []playerPoints, int getCurrentCard){
        SieteMedio s = new SieteMedio();

            if(getCurrentCard==8||getCurrentCard==9||getCurrentCard==10){
                playerPoints[s.PLAYER_TURN] +=0.5;
            }
            else {
                playerPoints[s.PLAYER_TURN] += getCurrentCard;
            }
        return playerPoints[SieteMedio.PLAYER_TURN];

    }
}

