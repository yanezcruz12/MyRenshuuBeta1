import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lead Author(s):
 * @author Cruz Yanez
 *
 * Other contributors:
 * None
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * Chapter 8.2: Java's Inheritance Mechanism
 *   Covers: extends JFrame and implements ActionListener
 * Chapter 8.3: Abstract Classes, Interfaces, and Polymorphism
 *   Covers: implementing the ActionListener interface contract
 * Chapter 13.2: Java GUIs: From AWT to Swing
 *   Covers: why javax.swing classes like JFrame and JPanel are used
 * Chapter 13.3: The Swing Component Set
 *   Covers: JLabel, JPanel, ImageIcon used to build the welcome screen
 * Chapter 13.5: The Java Event Model
 *   Covers: addActionListener(this) and actionPerformed(ActionEvent e)
 * Chapter 13.6: Case Study: Designing a Basic GUI
 *   Covers: overall pattern of building a GUI screen with panels and a button
 * Chapter 13.7: Containers and Layout Managers
 *   Covers: BorderLayout for top/center/bottom layout, BoxLayout for stacking labels
 *
 *Other References:
 * Oracle. (n.d.). Class JFrame. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 * Oracle. (n.d.). Class ImageIcon. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/ImageIcon.html
 *
 * Oracle. (n.d.). Class Image. Java Platform SE API Specification.
 * Retrieved from https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/Image.html
 *
 * Version/date:
 * 1.0 — April 16, 2026 — Initial version created
 * 1.1 — April 24, 2026 — Updated and added concept and Javadoc comments
 * 1.2 — April 25, 2026 — Citations added and updated
 * 1.6 — May 19, 2026 — Updated GUI screens to use button-specific
 *                         ActionListener objects instead of e.getSource()
 *                         event handling.
 * 
 * Responsibilities of class:
 * Displays the opening welcome screen for the マイ練習 application.
 * This class shows the title, subtitle, logo, tagline, and Start button.
 * When the user clicks Start, the program opens the HomeScreen.
 */

// IS-A: WelcomeScreen is a JFrame because it extends JFrame.
public class WelcomeScreen extends JFrame
{
	private RoundedButton startButton;
	// HAS-A: WelcomeScreen has a start button that opens the home screen.

	/**
	 * Creates the welcome screen for the application.
	 * The constructor builds the title area, displays the logo, creates
	 * the Start button, and connects the button to its own ActionListener.
	 */
	public WelcomeScreen()
	{
		setTitle("マイ練習");
		setSize(1400, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		Color backgroundColor = new Color(247, 248, 252);
		getContentPane().setBackground(backgroundColor);

		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

		JLabel titleLabel = new JLabel("マイ練習", SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 42));
		titleLabel.setForeground(new Color(36, 62, 110));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel subtitleLabel = new JLabel("MyRenshuu", SwingConstants.CENTER);
		subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
		subtitleLabel.setForeground(new Color(80, 80, 80));
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel taglineLabel = new JLabel("日本語で練習しましょう", SwingConstants.CENTER);
		taglineLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		taglineLabel.setForeground(new Color(100, 100, 100));
		taglineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		topPanel.add(titleLabel);
		topPanel.add(Box.createVerticalStrut(8));
		topPanel.add(subtitleLabel);
		topPanel.add(Box.createVerticalStrut(8));
		topPanel.add(taglineLabel);

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		JLabel logoLabel = new JLabel();
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		ImageIcon logoIcon = new ImageIcon("images/myrenshuu_logo.jpg");

		if (logoIcon.getIconWidth() > 0)
		{
			Image scaledImage = logoIcon.getImage().getScaledInstance(550, 550,
					Image.SCALE_SMOOTH);
			logoLabel.setIcon(new ImageIcon(scaledImage));
		}
		else
		{
			logoLabel.setText("Logo goes here");
			logoLabel.setFont(new Font("SansSerif", Font.ITALIC, 18));
			logoLabel.setForeground(new Color(120, 120, 120));
		}

		centerPanel.add(Box.createVerticalGlue());
		centerPanel.add(logoLabel);
		centerPanel.add(Box.createVerticalGlue());

		startButton = new RoundedButton("Start");
		startButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		startButton.setPreferredSize(new Dimension(220, 50));
		startButton.setBackground(new Color(214, 228, 249));
		startButton.setForeground(Color.BLACK);

		startButton.addActionListener(new ActionListener()
		{
			/**
			 * Opens the home screen when Start is clicked.
			 *
			 * @param e the ActionEvent created when Start is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ChapterSelectScreen();
				dispose();
			}
		});

		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 40, 10));
		bottomPanel.add(startButton);

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}