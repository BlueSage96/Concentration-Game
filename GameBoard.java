package concentration;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.util.*;
import java.lang.Math;

/**
 * @author brittanyprice
 *The GameBoard holds and display all of the cards.
 */
public class GameBoard extends JFrame{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	{
		try
		{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 *Array List, cardArray, holds the cards in the game.
	 *The array, colorList holds the colors of the card.
	 *@variable -> variables for the width, height, 
	 *when the cards are clicked (clickedCards), the number of matched cards
	 *(matchedCards), the number of card pairs (cardPair), and n pairs of cards (n).
	 */
		private GameController gc = new GameController(); //gc -> instance of GameController
		private static ArrayList<Card> cardArray = new ArrayList<>();
		Color [] colorList = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, 
										  Color.ORANGE, Color.MAGENTA};//, Color.CYAN
		//private double width;
		//private double height;
		private int cardClicks;
		private int matchedCards = 0;
		private int cardPair;
		//private String seconds;
		//private String MAX_SECONDS;
		
		public static void main(String [] args) {
			Random randint = new Random();
			int n = 24;
			GameBoard newGame = new GameBoard(n);
		}
	
		/**
		 *@param  n pairs of cards (n).
		 *This method iterates over the colorList and makes a pair (2) cards
		 *at a time. Then, they are added to the cardArray and the cardArray
		 *is shuffle at the beginning of each game.
		 */
		
		public GameBoard(int n) {
			cardPair = n;
			for(int i=0; i<n; i += 1) {//makes __ cards at a time
				Card c1 = new Card (colorList[i%6],gc);
				Card c2 = new Card (colorList[i%6],gc);
//				Card c3 = new Card (colorList[i%6],gc);
//				Card c4 = new Card (colorList[i%6],gc);
				cardArray.add(c1);
				cardArray.add(c2);
//				cardArray.add(c3);
//				cardArray.add(c4);
				Collections.shuffle(cardArray);
		}
		/**
		 * setTitle sets the title of the game.
		 * setDefaultCloseOperation closes the GameBoard/JFrame.
		 * Container c is equal to the getter for the content pane.
		 * A for loop iterates over the cardArray and adds the cards 
		 * to the content pane.
		*/
		setTitle("Concentration Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
			for(Card b: cardArray)
				c.add(b);
		
		/**
		* c.setLayout creates a new FlowLayout.
		* The width of the GameBoard is found by 
		* taking the floor of the square root of n*2.
		* The height of the GameBoard is found by multiply n*2
		* and dividing by the width.
		* The size of the GameBoard is set by multiplying 
		* width by 75 + 5*width + 20 and height*100 + 5*height + 20.
		* The last part allows for extra room.
		* setVisible is true so that the GameBoard is visible.
		*/
		c.setLayout(new FlowLayout());
		int width = (int)Math.floor(Math.sqrt(n*2));
		int height = (int)n*2/width;
		setSize(width*75 + 5* width + 20, height*100 + 5*height + 50);
		setVisible(true);

	}
		/**
		 *This class compares to cards to see if their face colors match.
		 */
		private class GameController implements ActionListener {
			private Color cardColor1;
			private Color cardColor2;
			private Card clickedCard1;
			private Card clickedCard2;
			public void actionPerformed(ActionEvent arg0) {
				Object o = arg0.getSource(); //reference to object clicked
				
				/**
				 *if statement turns over the first
				 *clicked card and saves its face color.
				 */
				if(cardClicks % 2 == 0) {
					clickedCard1 = (Card ) o; //cast (convert) object as a card
					clickedCard1.faceUp();
					cardColor1 = clickedCard1.getColor();
					cardClicks += 1;
			}
				/**
				 *the else statement turns over the second
				 *clicked card and saves its face color.
				 *the timer and try and except sleep method
				 * puts a delay on the cards when matching.
				 *pairs of cards.
				 *the timer prevents delay glitches sometimes 
				 *caused by the sleep method and vice versa.
				 */
				else {
					clickedCard2 = (Card ) o; //cast (convert) object as a card
					clickedCard2.faceUp();
					cardColor2 = clickedCard2.getColor();
					cardClicks += 1;
					//use a timer to prevent glitches that only happens sometimes with a sleep method!
					javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
				          public void actionPerformed2(ActionEvent e) {
				              Component p = null;
							p.repaint();
				          }

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
						}
				       });
						try {
						Thread.sleep(1000);                 //1000 milliseconds is one second.
					}
					catch(InterruptedException ex)
					{
				    	Thread.currentThread().interrupt();
				}
						t.start();
						t.stop();
					/**
					 *if clickedCard1 equals clickedCard2,
					 *their face colors match, then the 
					 *clicked cards turn face up.
					 *Else, they are turned face down.
					 *The game ends if all of the pairs of cards
					 *are matched.
					 *The number of clicks is printed at the end of the game. 
					 */
					if(clickedCard1.equals(clickedCard2)) {
						clickedCard1.faceUp();
						clickedCard2.faceUp();	
						matchedCards += 1;
						if(matchedCards == cardPair) {
							System.out.println("Finished game in " +  cardClicks + " clicks!");
						}
					}
					else {
						clickedCard1.faceDown();
						clickedCard2.faceDown();
					}
					
				}
				
		
		}
}}