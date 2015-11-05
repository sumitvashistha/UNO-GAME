package com.uno.domain;


/**
 * Represents a card which allows current player to force next player to skip a chance
 * when their turn comes
 * 
 * @author SUMIT
 * 
 */
public class SkipCard extends Card
{
    private int nSkip;
  
    public SkipCard(int colour, int nSkip)
    {
        super(colour, "Skip");
        this.nSkip = nSkip;
    }
    
    public int getNSkip() {
        return nSkip;
    }
    
     /**
     * Take any effect when the card is played.
     * 
     * SkipCard effect: it skips the nSkip player
     * also be skipped
     */
    @Override
    public void play (UnoGame game) {
        //skip the nSkip player
        for (int i = 0; i<this.nSkip; i++) {
            game.goToNextPlayer();
        }
    }
}
