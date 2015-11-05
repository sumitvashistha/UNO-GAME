package com.uno.domain;


/**
 * Handles the WildCard on which current colour can be changes
 * by the current player
 * 
 * @author SUMIT
 */
public class WildCard extends Card
{

    public WildCard(){
        super(COLOUR_NONE,"Wild");
    }
    
    /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    @Override
    public boolean canPlayOn(Card card)  { 
        //change to current card's colour
        this.setColour(card.getColour());
        // wild card: it should always return true
        return true;       
    }
    
}
