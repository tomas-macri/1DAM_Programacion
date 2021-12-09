package CardGame;

// POJO

public class Card {

    private int number;
    private Palos suits;

    public Card(int number, Palos suits) {
        this.number = number;
        this.suits = suits;
    }

    public int getNumber() {
        return number;
    }

    public Palos getSuits() {
        return suits;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSuits(Palos suits) {
        this.suits = suits;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", suits=" + suits +
                '}';
    }
}
