package com.uno.domain;


/**
 * Represents a card which can change the current direction of Game Play.
 * 
 * @author SUMIT
 * 
 */
public class ReverseCard extends Card
{
 
    public ReverseCard(int colour)
    {
        super(colour,"Reverse");
    }

    /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    public boolean canPlayOn(Card card) {        
        return (card.myColour == myColour);        
    }
    
    
     /**
     * Take any effect when the card is played.
     * 
     * Drawcard effect: the next player draws nDraw cards and their turn will
     * also be skipped
     */
    @Override
    public void play (UnoGame game) {
        game.setPlayDirection(-1);
    }
}
