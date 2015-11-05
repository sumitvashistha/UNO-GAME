package com.uno.domain;


/**
 * A standard Uno card.
 * 
 * @author SUMIT
 */
public class Card
{
    // Constants for representing colours
    public static final int COLOUR_NONE = 0;
    public static final int COLOUR_GREEN = 1;
    public static final int COLOUR_BLUE = 2;
    public static final int COLOUR_RED = 3;
    public static final int COLOUR_YELLOW = 4;

    // An array of names indexed by the colours above
    public static final String[] COLOUR_NAMES= {"", "Green","Blue","Red","Yellow"};
    protected int myColour;    
    private final String mySymbol;
    
    /**
     * Create a card with a given colour and symbol
     * 
     * @param colour The card colour. Should be one of COLOUR_NONE, COLOUR_GREEN, COLOUR_BLUE, COLOUR_RED or COLOUR_YELLOW.
     * @param symbol The symbol on the card
     */
    public Card(int colour, String symbol) {
        myColour = colour;
        mySymbol = symbol;
    }

    /**
     * Create a card with a given colour and number
     * 
     * @param colour The card colour. Should be one of COLOUR_NONE, COLOUR_GREEN, COLOUR_BLUE, COLOUR_RED or COLOUR_YELLOW.
     * @param number The number on the card
     */
    public Card(int colour, int number) {
        myColour = colour;
        mySymbol = "" + number;
    }
    
    public int getColour() {
        return myColour;
    }

    public void setColour(int colour) {
        myColour = colour;
    }

    public String getSymbol() {
        return mySymbol;
    }
    
    /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    public boolean canPlayOn(Card card) {        
        return (card.mySymbol.equals(mySymbol) || card.myColour == myColour);        
    }
    
    /**
     * Take any effect when the card is played.
     * 
     * The default is no effect.
     */
    public void play(UnoGame game) {
        // Default: has no effect
    }
    
    @Override
    public String toString() {
        String result;
        
        result = COLOUR_NAMES[myColour];
        if (!result.isEmpty()) {
            result += " ";
        }
        result += mySymbol;
        return result;
    }
}
