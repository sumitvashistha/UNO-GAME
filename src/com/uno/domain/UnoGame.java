package com.uno.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An implementation of the game of Uno.
 * 
 * @author SUMIT
 */
public class UnoGame
{
    private final ArrayList<Card> myDeck;
    private final ArrayList<Card> myPile;
    private final ArrayList<Player> myPlayers;

    private int myCurrentPlayer;
    private int myPlayDirection;
    
    
    /**
     * Create a new game with a standard deck of cards
     * 
     * @param nPlayers The number of players
     */
    public UnoGame(int nPlayers) {
        myDeck = new ArrayList<>();
        
        for (int c = 1; c <= 4; c++) {
            for (int i = 1; i <= 9; i++) {
                myDeck.add(new Card(c, i));
            }
        }
        Collections.shuffle(myDeck);
        
        // Pile is initially empty;
        myPile = new ArrayList<>();
        myPlayers = new ArrayList<>();

        for (int i = 1; i <= nPlayers; i++) {
            Player p = new Player("Player " + i);
            myPlayers.add(p);
        }

    }
    
    /**
     * Access the deck
     */
    public ArrayList<Card> getDeck() {
        return myDeck;
    }
    
    /**
     * Access the pile
     */
    public ArrayList<Card> getPile() {
        return myPile;
    }

    /**
     * Access the players
     */
    public ArrayList<Player> getPlayers() {
        return myPlayers;
    }
    
    /**
     * Create a new game with a given deck of cards. 
     * 
     * @param deck  The deck to use
     * @param nPlayers The number of players
     *
     */
    public UnoGame(ArrayList<Card> deck, int nPlayers) {
        if(nPlayers <2)
        {
            throw new IllegalArgumentException("Game requires min 2 players to start");
        }
        myDeck = deck;
        
        // Pile is initially empty;
        myPile = new ArrayList<>();
        myPlayers = new ArrayList<>();

        for (int i = 1; i <= nPlayers; i++) {
            Player p = new Player("Player " + i);
            myPlayers.add(p);
        }

    }

    /**
     * Reset the game. Gather all cards into the deck and shuffle.
     */
    
    public void reset() {
        myDeck.addAll(myPile);
        myPile.clear();
        for (Player p : myPlayers) {
            myDeck.addAll(p.getCards());
            p.clearCards();
        }
        Collections.shuffle(myDeck);
    }
    
    /**
     * Get the player whose turn it currently is.
     */
    
    public Player getCurrentPlayer() {
        return myPlayers.get(myCurrentPlayer);
    }

    /**
     * Get the player who is a given number of positions ahead in play (in the current direction of play).
     * 
     * @param skip  A number of positions away from the current player.
     * @returns The player at that position
     */
    public Player getNextPlayer(int skip) {
        int i = myCurrentPlayer + (skip * myPlayDirection);
        i = i % myPlayers.size();
        if (i < 0) {
            i += myPlayers.size();
        }
        
        return myPlayers.get(i);
    }

    /**
     * Get the current direction of play (1 or -1)
     */
    public int getPlayDirection() {
        return myPlayDirection;
    }
    
    public void setPlayDirection(int dir) {
        myPlayDirection = dir;
    }
    
    /**
     * Change the current player to the next one in the direction of play
     */
    public void goToNextPlayer() {
        myCurrentPlayer = (myCurrentPlayer + myPlayDirection + myPlayers.size()) % myPlayers.size();
    }
    
    /**
     * Draw cards from the deck into the hand of the given player.
     * 
     * 
     * @param player The player who is drawing
     * @param nCards The number of cards to draw
     */
    public void draw(Player player, int nCards) {
        System.out.println(player + " draws " + nCards + ".");

        for (int i = 0; i < nCards; i++) {
            if (myDeck.isEmpty() && myPile.size() > 0) {
                Card keep = myPile.get(0);
                myDeck.addAll(myPile.subList(1, myPile.size()));
                myPile.clear();
                myPile.add(keep);
                Collections.shuffle(myDeck);
            }
            
            if (myDeck.isEmpty()) {
                return;
            }
            
            player.gainCard(myDeck.remove(0));
        }
    }
    
    /**
     * Deal out cards and start a new game
     */
    public void dealCards() {
        // Everyone draws seven cards to start
        for (Player p : myPlayers) {
            draw(p, 7);
        }
        
        // turn over the top card
        Card card = myDeck.remove(0);
        myPile.add(0, card);
        System.out.println("The starting card is: " + card);

        // We start from player zero, going in the positive direction
        myCurrentPlayer = 0;
        myPlayDirection = 1;

    }

    /**
     * Play a single turn of the game.
     * 
     * @returns If someone wins, return their identity, otherwise null.
     */
    public Player playTurn() {               
        Player player = getCurrentPlayer();
        
        // Display everyone's hands
        for (Player p : myPlayers) {
            if (p == player) {
                System.out.print("* ");
            }
            System.out.println(p + ": " + p.getCards());
        }
        
        // the current player chooses a card to play
        Card card = player.playCard(myPile.get(0));
        
        if (card == null) {
            // they cannot play, so draw a card
            System.out.println(player + " cannot play.");
            draw(player, 1);
        }
        else {
            // play the card
            System.out.println(player + " plays " + card + ".");
            myPile.add(0, card);
            card.play(this);
        }
        
        // If that was their last card, then they win
        if (player.getCards().isEmpty()) {
            return player;
        }
        
        // Go on to the next player
        goToNextPlayer();
        System.out.println();
        return null;
    }
    
    
    /**
     * Play the game until someone wins.
     * 
     * @returns The identity of the winner.
     */
    public Player play() {
    
        dealCards();

        // Keep playing turns until there is a winner
        Player winner = null;        
        while (winner == null) {
            winner = playTurn();
        }
        
        System.out.println(winner + " has won the game!");
        return winner;
    }
    
}
