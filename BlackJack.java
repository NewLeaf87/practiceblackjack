import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

            //Welcome message
            System.out.println("Welcome to BlackJack!");

            //create playing deck
            Deck playingDeck = new Deck();
            playingDeck.createFullDeck();
            playingDeck.shuffle();
            //Create a deck for the player
            Deck playerDeck = new Deck();
            // Create a deck for the Dealer
            Deck dealerDeck = new Deck();

            double playerMoney = 100.00;

            Scanner userInput = new Scanner(System.in);

            //Game Loop
            while( playerMoney > 0){
                //play on!
                //take player bet
                System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
                double playerBet = userInput.nextDouble();
                boolean endRound = false;
                if(playerBet > playerMoney){
                    System.out.println("You dont have that money...GET OUT");
                    break;
                }

                System.out.println("Dealing...");

                //Start dealing
                //player gets two cards
                playerDeck.draw(playingDeck);
                playerDeck.draw(playingDeck);

                //Dealer gets two cards
                dealerDeck.draw(playingDeck);
                dealerDeck.draw(playingDeck);

                while(true){
                    System.out.println("Your Hand: " + playerDeck.toString());
                    System.out.print(playerDeck.toString());
                    System.out.println("your hand valued at: " + playerDeck.cardValues());
                    //Display Dealer Hand
                    System.out.println("Dealer Hand: "+ dealerDeck.getCard(0).toString()+ " and [Hidden]");

                    // What does the player want to do?
                    System.out.println("Would you like to HIT(1) or STAND(2)?");
                    int response = userInput.nextInt();

                    //HIT
                    if(response ==1){
                        playerDeck.draw(playingDeck);
                        System.out.println("you draw a : " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                        //Bust
                        if(playerDeck.cardValues()> 21){
                            System.out.println("BUST. Current Value is at " + playerDeck.cardValues());
                            playerMoney -= playerBet;
                            endRound = true;
                            break;

                        }
                    }
                        if(response==2){
                            break;
                        }

                }
                //Reveal Dealer cards
                System.out.println("Dealer Cards: " + dealerDeck.toString());
                //see if dealer has more points than player
                if((dealerDeck.cardValues()>playerDeck.cardValues())&& endRound == false){
                    System.out.println("Dealer Won!");
                    playerMoney -= playerBet;
                    endRound = true;
                }
                //Dealer Draws to 16, stand at 17
                while((dealerDeck.cardValues()< 17)&& endRound == false){
                    dealerDeck.draw(playerDeck);
                    System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
                }
                //display total for Dealer
                System.out.println("Dealer's hand is valued at: " + dealerDeck.cardValues());
                //if Dealer BUST
                if((dealerDeck.cardValues() > 21) && endRound ==false){
                    System.out.println("Dealer busts! You Win!");
                    playerMoney += playerBet;
                    endRound = true;
                }
                //Push
                if((playerDeck.cardValues() == dealerDeck.cardValues())&& endRound == false){
                    System.out.println("push");
                    endRound = true;
                }
                

                if((playerDeck.cardValues() > dealerDeck.cardValues()) && endRound ==false){
                    System.out.println("You win!");
                    playerMoney += playerBet;
                    endRound = true;
                }
                else if(endRound==false){
                    System.out.println("you lose the hand");
                    playerMoney -= playerBet;
                    endRound = true;
                }
                

                playerDeck.moveAllToDeck(playingDeck);
                dealerDeck.moveAllToDeck(playingDeck);
                System.out.println("End of Hand");

            }
            System.out.println("Game over pal game over.:(");

            //close Scanner
            userInput.close();

            



            //System.out.println(playingDeck);
    }
    
}