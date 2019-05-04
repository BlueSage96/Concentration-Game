package concentration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

//re code what I can!

/**
 * @author brittanyprice
 *Card class establishes each cards' width, height, face color, 
 *position relative to the other cards, if a card's face down or
 *face up at a given time, knows it's pair, and know its background color.
 */


public class Card extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color faceColor;
	private static Color backgroundColor;
	
	private boolean isFaceDown = true; //all cards are face down at the beginning
	private int width = 75;
	private int height = 100;
	
	/**
	 *The Card constructor creates the preferred size/dimensions
	 *of the cards.
	 *The face color is instantiated here.
	 *A black border size 2 is created to go around the cards.
	 *An ActionListener is added when the cards are face down.
	 */
	public Card(Color fc, ActionListener al) {
		setPreferredSize(new Dimension(width,height));
		faceColor = fc;
		setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		this.addActionListener(al);
		faceDown();
	}
	/**
	 *if a card is not face down, its color is changed to its face color.
	 */
	public void faceUp() { 
		setFaceDown(false);
		changeColor(faceColor);
		
	}
	/**
	 *if a card's face down, the color of the card is changed to
	 *the background color.
	 */
	public void faceDown() {
		setFaceDown(true);
		changeColor(backgroundColor);
		
	}
	/**
	 * @param otherCard -> second card selected in the card pair.
	 *if the card color matches the other card's face color,
	 *@return true
	 *else @return false
	 */
	public boolean equals(Card otherCard) {
		if(this.faceColor==otherCard.faceColor) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 *this method changes the background of the card to
	 *its face color and paints the card once the background is painted.
	 *@param faceColor -> the card's face color.
	 */
	private void changeColor(Color faceColor) {
		this.setBackground(faceColor);
		paintImmediately(0, 0, width, height);//each card has an origin, width, & height
		
	}
	/**
	 *gets the cards face color.
	 *@return the face color
	 */
	public Color getColor() {
		return faceColor;
	}
	public boolean getIsFaceDown() {
		return isFaceDown;
	}
	public void setFaceDown(boolean isFaceDown) {
		this.isFaceDown = isFaceDown;
	}
}