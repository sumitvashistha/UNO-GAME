package com.uno.domain;


/**
 * Handles how many cards needs to be drawn by next player when a wild card is thrown 
 * by the previous player like WildCardDraw2,WildCardDraw4
 * 
 * @author SUMIT
 */
public class DrawCard extends Card
{
    private int nDraw;

    public DrawCard(int nDraw)
    {
       super(COLOUR_NONE,"Draw");
       this.nDraw = nDraw;
    }
    public int getNDraw(){
        return nDraw;
    }
     /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    @Override
    public boolean canPlayOn(Card card) { 
        //change the wild draw card's colour
        this.setColour(card.getColour());
        // it should always return true;
        return true;       
    }
    
    
     /**
     * Take any effect when the card is played.
     * 
     * Wild Draw Card effect: the next player draws nDraw cards and their turn will
     * also be skipped
     */
    public void play (UnoGame game) {
        game.draw(game.getNextPlayer(1),nDraw);
        // the current player's turn will also be skipped
        game.goToNextPlayer();
    }
}
