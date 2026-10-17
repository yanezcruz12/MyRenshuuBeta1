import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Lead Author(s):
 * @author Cruz Yanez
 * @version 1.0
 *
 * Other contributors:
 * None
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * Chapter 8.2: Java's Inheritance Mechanism
 * Chapter 13.3: The Swing Component Set
 *
 * Oracle. (n.d.). Class JButton. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JButton.html
 *
 * Oracle. (n.d.). Class Graphics. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/Graphics.html
 *
 * Oracle. (n.d.). Class Graphics2D. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/Graphics2D.html
 *
 * Oracle. (n.d.). Class RenderingHints. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/RenderingHints.html
 * 
 * How to Remove Shaded Background/Border of My Rounded JButton? Stack Overflow, Jan. 2022, 
 * Retrieved from https://stackoverflow.com/questions/70700759/how-to-remove-shaded-background-border-of-my-rounded-jbutton. 
 *
 * Java – Create Rounded Buttons. 1bestcsharp, Aug. 2025,
 * Retrieved from https://1bestcsharp.blogspot.com/2025/08/java-create-rounded-buttons.html. 
 * 
 * Java GUI – Button Custom. YouTube, uploaded by Ra Ven, May 2021, 
 * Retrieved from https://www.youtube.com/watch?v=FEb2Pt9ymes. 
 * 
 * Aterai. “Rounded Corner JButton.” Java Swing Tips, Nov. 2008
 * Retrieved from https://java-swing tips.blogspot.com/2008/11/rounded-corner-jbutton.html. 
 *
 * Version/date:
 * 1.0 — April 16, 2026 — Initial version created,
 *                         custom rounded painting using Graphics2D designed
 * 1.1 — April 17, 2026 — Anti-aliasing refined with RenderingHints,
 *                        getWidth() - 1 and getHeight() - 1 adjusted
 *                        to keep border stroke inside button bounds                     
 * 1.1 — April 24, 2026 — Javadoc improvements, concept comments added
 *                    
 * Responsibilities of class:
 * Creates a reusable custom button for マイ練習 application.
 * This class changes the appearance of a normal JButton by drawing
 * a rounded background and rounded border.
 */

//IS-A: RoundedButton is a JButton because it extends JButton.
//This means it works like a normal button, but I can customize how it looks.
public class RoundedButton extends JButton
{
 /**
  * Creates a button with rounded corners.
  * This keeps the normal JButton behavior, such as showing text and
  * responding to clicks, but removes the default button painting so this
  * class can draw its own rounded style.
  *
  * @param text the words shown on the button
  */
	public RoundedButton(String text)
	{
		super(text);

		// These settings remove the default JButton look so the custom
		// rounded design can show clearly.
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
	}

	/**
	 * Draws the rounded background of the button.
	 *
	 * @param g the drawing tool Swing gives this button when it repaints
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		// This creates a Graphics2D copy so the custom drawing changes
		// only affect this button.
		Graphics2D g2 = (Graphics2D) g.create();

		// This smooths the edges so the rounded corners do not look uneven and pixelated.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// This fills the button with its current background color.
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

		// This lets JButton draw the button text on top of the rounded shape.
		super.paintComponent(g2);

		// This releases the temporary drawing object after it is finished.
		g2.dispose();
	}

	/**
	 * Draws the rounded border around the button.
	 *
	 * @param g the drawing tool Swing gives this button when it repaints
	 */
	@Override
	protected void paintBorder(Graphics g)
	{
		// This creates a separate Graphics2D copy for drawing the border.
		Graphics2D g2 = (Graphics2D) g.create();

		// This keeps the border smooth around the rounded corners.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// This draws a gray rounded border around the button.
		g2.setColor(new Color(160, 160, 160));
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

		// This cleans up the temporary drawing object.
		g2.dispose();
	}
}