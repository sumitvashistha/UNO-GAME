package com.uno.domain;


import java.util.ArrayList;

/**
 * The test class DrawCardTest.
 *
 * @author  SUMIT
 * 
 */
public class DrawCardTest extends junit.framework.TestCase
{
    public void testConstruct() {
        DrawCard drawCard = new DrawCard(2);
        
        assertEquals(Card.COLOUR_NONE,drawCard.getColour());
        assertEquals("Draw",drawCard.getSymbol());
        
        assertEquals(2, drawCard.getNDraw());
    }
    public void testCanPlayOn() {
       // create a wild draw card
       WildCard wildDraw = new WildCard();
       
       // create another 3 normal cards with dffirent colour and number(or symbol)
       Card card1 = new Card(Card.COLOUR_BLUE, 3);
       Card card2 = new Card(Card.COLOUR_RED,2);
       Card card3 = new Card(Card.COLOUR_RED,"abc");
       
       assertTrue(wildDraw.canPlayOn(card1));
       assertTrue(wildDraw.canPlayOn(card2));
       
       //test if the wild card has been changed colour, i.e. red
       assertTrue(card3.canPlayOn(wildDraw));
       
    }

    public void testPlay()   {
        // stack the deck
        ArrayList <Card> deck = new ArrayList();

        DrawCard wildCardDraw = new DrawCard(2);

        deck.add(wildCardDraw);
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_GREEN, 5));
        deck.add(new Card(Card.COLOUR_GREEN, 4));

        // player 1's cards - it doesn't matter what these are
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_RED, 5));
        deck.add(new Card(Card.COLOUR_RED, 5));

        // the card that becomes the initial pile
        // chosen to allow player 0 to play the wild draw card

        deck.add(new Card(Card.COLOUR_RED, 1));

        // the draw pile -- add enough cards for player1 to draw 2

        deck.add(new Card(Card.COLOUR_GREEN, 2));
        deck.add(new Card(Card.COLOUR_GREEN, 3));
        deck.add(new Card(Card.COLOUR_GREEN, 4));

        // create a game using this deck (unshuffled)
        UnoGame game = new UnoGame(deck, 2);
        game.dealCards();

        // get the two players
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);

        // player 0 plays a turn
        game.playTurn();

        // check the card played is the red draw 2
        assertEquals(wildCardDraw, game.getPile().get(0));

        // check how many cards each player has
        assertEquals(6, player0.getCards().size());
        assertEquals(9, player1.getCards().size());

        // check that we skipped player1's turn
        assertEquals(player0, game.getCurrentPlayer()); 
       
    }
}
