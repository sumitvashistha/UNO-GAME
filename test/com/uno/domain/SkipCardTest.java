package com.uno.domain;


import java.util.ArrayList;

/**
 * The test class SkipCardTest.
 *
 * @author  SUMIT
 */
public class SkipCardTest extends junit.framework.TestCase
{
    public void testConstruct() {
        SkipCard redSkip1 = new SkipCard(Card.COLOUR_RED,1);
        
        assertEquals(Card.COLOUR_RED, redSkip1.getColour());
        assertEquals(1, redSkip1.getNSkip());
        
    }
    public void testCanPlayOn() {
        SkipCard redSkip1 = new SkipCard(Card.COLOUR_RED,1);
        
        Card card1 = new Card(Card.COLOUR_GREEN,1);
        
        Card card2 = new Card(Card.COLOUR_RED,"Hello World");
        
        Card card3 = new Card(Card.COLOUR_RED,2);
        
        assertFalse(redSkip1.canPlayOn(card1));
        assertTrue(redSkip1.canPlayOn(card2));
        assertTrue(redSkip1.canPlayOn(card3));
    }

    public void testPlay()   {
        
        ArrayList <Card> deck = new ArrayList();
        
        SkipCard redSkip1 = new SkipCard(Card.COLOUR_RED,1);
        // player 0's cards - one playable and 4 unplayable
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_GREEN, 5));
        deck.add(new Card(Card.COLOUR_GREEN, 5));
        deck.add(redSkip1);

        // player 1's cards - it doesn't matter what these are
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_GREEN, 5));
        deck.add(new Card(Card.COLOUR_GREEN, 5));
        
         // player 2's cards - it doesn't matter what these are
        deck.add(new Card(Card.COLOUR_BLUE, 1));
        deck.add(new Card(Card.COLOUR_BLUE, 2));
        deck.add(new Card(Card.COLOUR_BLUE, 7));
        deck.add(new Card(Card.COLOUR_BLUE, 8));
        deck.add(new Card(Card.COLOUR_BLUE, 3));
        deck.add(new Card(Card.COLOUR_YELLOW, 5));
        deck.add(new Card(Card.COLOUR_YELLOW, 5));
        

        // allow player 0 to use the red skip card
        deck.add(new Card(Card.COLOUR_RED, 2));
        deck.add(new Card(Card.COLOUR_GREEN, 1));


        // create a game using this deck (unshuffled)
        UnoGame game = new UnoGame(deck, 3);
        game.dealCards();
        
        // get the two players
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        Player player2 = game.getPlayers().get(2);
       
        game.playTurn();
        
        assertEquals(redSkip1,game.getPile().get(0));
        
        // checked if skips to player2's turn
        assertEquals(player2,game.getCurrentPlayer());
        
        // check how many cards each player has
        assertEquals(6, player0.getCards().size());
        assertEquals(7, player1.getCards().size());
        assertEquals(7, player2.getCards().size());
    }
}
