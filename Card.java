public class Card {
    
    private Suit suit;
    private Values values;

    public Card(Suit suit, Values values) {

        this.values = values;
        this.suit = suit;

    }
    public String toString(){
        return this.suit.toString() + "-" + this.values.toString();
    }

    public Values getValues() {

        return this.values;
    }

}