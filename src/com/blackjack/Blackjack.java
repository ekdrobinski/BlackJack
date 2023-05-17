package com.blackjack;
import java.util.ArrayList;
import java.util.Scanner;
public class Blackjack {

    public static void main(String[] args) {

        ///Welcome message
        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck(); //create the desk and populates with 52 cards
        playingDeck.shuffleDeck();
        //create a deck for the player
        Deck playerDeck = new Deck();

        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);
            ///make an arrayList to store integers of 5s
        ArrayList<Integer> validBetIncrements = new ArrayList<>();
        for (int i = 5; i <= 100; i += 5) {
            validBetIncrements.add(i);
        }

        ///Game Loop
        while(playerMoney > 0) {
            ///Play On!
            ///Take the player's bet
            System.out.println("You have $" + playerMoney + ". How much would you like to bet (increments of 5 only)?");
            double playerBet = userInput.nextDouble();

            if (!validBetIncrements.contains((int) playerBet)) {
                System.out.println("Invalid amount. You can only bet in increments of 5");
                continue;
            }

            if (playerBet > playerMoney) {
                System.out.println("You can't bet more than you have. Game over.");
                break;
            }

            boolean endRound = false;

            ///Start Dealing
            ///Player gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            ///Dealer gets two cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true) {
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your player deck is valued at:  " + playerDeck.cardsValue());
                ///display Dealer's hand:
                System.out.println("Dealer's hand:  " + dealerDeck.getCard(0).toString() + "[hidden card]");

                ///what does the player want to do?
                System.out.println("Would you like to (1)Hit or (2)Stand?");
                int response = userInput.nextInt();

                ///if they hit
                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString()); ///needs the minus one to get the proper index
                    //Bust if > 21
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at:  " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2){
                    break;
                }
            }

            ///Reveal Dealer Cards
            System.out.println("Dealer Cards:  " + dealerDeck.toString());
            //See if the dealer has more points than player
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer wins.");
                playerMoney -= playerBet;
                endRound = true;
            }
            ///Dealer Draws at 16, stand at 17
            while((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            ///Display total Value for Dealer
            System.out.println("Dealer's Hand is valued at:  " + dealerDeck.cardsValue());
            ///determine if dealer busted
            if((dealerDeck.cardsValue() > 21)&& endRound ==false){
                System.out.println("Dealer busts! You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            ///Detemine if push(fancy word for tie)
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Push");
                endRound = true;
            }
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("You win the hand!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if (endRound == false) {
                System.out.println("You lose the hand");
                playerMoney -= playerBet;
                endRound = true;
            }
            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");

        }

        System.out.println("Game over! You are out of money. Too bad.");



        // Create hands for the player and the dealer -
        // hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        // Game loops


     /* Checks there are 52 cards in the deck by printing to console
        System.out.println(playingDeck); */
        // System.out.println(playingDeck); //placed after shuffle to check if it shuffled
    }
}