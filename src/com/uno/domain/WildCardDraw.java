package com.uno.domain;


/**
 * A type of card which allows current player to force next player to draw multiple cards
 * from pile
 * 
 * @author  SUMIT
 * 
 */
public class WildCardDraw extends Card
{
    
    private int nDraw;

    /**
     * Constructor for objects of class DrawCard
     */
    public WildCardDraw(int colour, int nDraw)
    {
        super(colour,"Draw");
        this.nDraw=nDraw;
    }
    
    /**
     * Getter metthod
     */
    public int getNDraw() {
        return nDraw;
    }
    /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    @Override
    public boolean canPlayOn(Card card) {        
        return (card.myColour == myColour);        
    }
    
    
     /**
     * Take any effect when the card is played.
     * 
     * Drawcard effect: the next player draws nDraw cards and their turn will
     * also be skipped
     * 
     */
    @Override
    public void play (UnoGame game) {
        game.draw(game.getNextPlayer(1),nDraw);
        // the current player's turn will also be skipped
        game.goToNextPlayer();
    }
}
