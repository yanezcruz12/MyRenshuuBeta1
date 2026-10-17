import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lead Author(s):
 * 
 * @author Cruz Yanez
 *
 *         Other contributors: None
 *
 *         References: Morelli, R., & Walde, R. (2016). Java, Java, Java:
 *         Object-Oriented Problem Solving. Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 *         Chapter 3.3: Constructor Methods Chapter 8.2: Java's Inheritance
 *         Mechanism Chapter 13.2: Java GUIs: From AWT to Swing Chapter 13.3:
 *         The Swing Component Set Chapter 13.5: The Java Event Model Chapter
 *         13.7: Containers and Layout Managers
 *
 *         Akira Miura. An Integrated Approach to Intermediate Japanese (Revised
 *         Edition) Retrieved from https://online.fliphtml5.com/cialx/jzho/
 *         Chapter 1
 *
 *         Other References: Oracle. (n.d.). Class JFrame. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 *         Oracle. (n.d.). Class BoxLayout. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/BoxLayout.html
 *
 *         Oracle. (n.d.). Class JLabel. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JLabel.html
 *
 *         Version/date: 1.0 — May 23, 2026 — Class created, chapter selection
 *         GUI added with Chapter 1 and Chapter 4 buttons.
 *
 *         Responsibilities of class: Displays the chapter selection screen for
 *         the マイ練習 application. This class allows the user to choose a textbook
 *         chapter before moving to the section menu.
 */

//IS-A: ChapterSelectScreen is a JFrame because it extends JFrame.
public class ChapterSelectScreen extends JFrame {
	private RoundedButton chapter1Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 1.

	private RoundedButton chapter2Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 2.

	private RoundedButton chapter3Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 3

	private RoundedButton chapter4Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 4.

	private RoundedButton chapter5Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 5.

	private RoundedButton chapter6Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 6.

	private RoundedButton chapter7Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 7.

	private RoundedButton chapter8Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 8.

	private RoundedButton そば類練習Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 9.

	private RoundedButton 薬の効き目Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 10.

	private RoundedButton カップラーメンButton;
	// HAS-A: ChapterSelectScreen has a button for Chapter 11.

	private RoundedButton chapter12Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 12.

	private RoundedButton chapter13Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 13.

	private RoundedButton chapter14Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 14.

	private RoundedButton chapter15Button;
	// HAS-A: ChapterSelectScreen has a button for Chapter 15.

	private RoundedButton backButton;
	// HAS-A: ChapterSelectScreen has a back button to return
	// to the welcome screen.

	private JLabel titleLabel;
	// HAS-A: ChapterSelectScreen has a title label to display
	// the screen heading.

	private JLabel subtitleLabel;
	// HAS-A: ChapterSelectScreen has a subtitle label to give
	// the user directions in English and Japanese.

	/**
	 * Creates the chapter selection screen and connects each button to its own
	 * ActionListener.
	 */
	public ChapterSelectScreen() {
		setTitle("マイ練習 - Chapters（チャプター）");
		setSize(1400, 1200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Color backgroundColor = new Color(247, 248, 252);
		Color buttonColor = new Color(214, 228, 249);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);

		titleLabel = new JLabel("Choose a Chapter（チャプターを選びましょう）");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		titleLabel.setForeground(new Color(36, 62, 110));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		subtitleLabel = new JLabel("Select your textbook chapter（教科書のチャプター）");
		subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		subtitleLabel.setForeground(new Color(90, 90, 90));
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		chapter1Button = new RoundedButton("Chapter 1（第1課）");
		chapter2Button = new RoundedButton("Chapter 2（第2課）");
		chapter3Button = new RoundedButton("Chapter 3（第3課）");
		chapter4Button = new RoundedButton("Chapter 4（第4課）");
		chapter5Button = new RoundedButton("Chapter 5（第5課）");
		chapter6Button = new RoundedButton("Chapter 6（第6課）");
		chapter7Button = new RoundedButton("Chapter 7（第7課）");
		chapter8Button = new RoundedButton("Chapter 8（第8課）");
		そば類練習Button = new RoundedButton("そば類練習（第９課）");
		薬の効き目Button = new RoundedButton("薬の効き目（第10課）");
		カップラーメンButton = new RoundedButton("カップラーメン（第11課 ");
		chapter12Button = new RoundedButton("Chapter 12（第12課）");
		chapter13Button = new RoundedButton("Chapter 13（第13課）");
		chapter14Button = new RoundedButton("Chapter 14（第14課）");
		chapter15Button = new RoundedButton("Chapter 15（第15課）");
		backButton = new RoundedButton("Back（戻る）");

		RoundedButton[] buttons = { chapter1Button, chapter2Button, chapter3Button, chapter4Button, chapter5Button,
				chapter6Button, chapter7Button, chapter8Button, そば類練習Button, 薬の効き目Button, カップラーメンButton,
				chapter12Button, chapter13Button, chapter14Button, chapter15Button, backButton };
		// HAS-MANY: ChapterSelectScreen has many RoundedButton objects grouped
		// in an array so they can share formatting.

		for (RoundedButton button : buttons) {
			button.setFont(new Font("SansSerif", Font.BOLD, 18));
			button.setBackground(buttonColor);
			button.setForeground(Color.BLACK);
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
		}

		chapter1Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 1.
			 *
			 * @param e the ActionEvent created when Chapter 1 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(1);
				dispose();
			}
		});

		chapter2Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 2.
			 *
			 * @param e the ActionEvent created when Chapter 2 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(2);
				dispose();
			}
		});
		chapter3Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 2.
			 *
			 * @param e the ActionEvent created when Chapter 2 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(3);
				dispose();
			}
		});
		chapter4Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 4.
			 *
			 * @param e the ActionEvent created when Chapter 4 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(4);
				dispose();
			}
		});
		chapter5Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 5.
			 *
			 * @param e the ActionEvent created when Chapter 5 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(5);
				dispose();
			}
		});
		chapter6Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 6.
			 *
			 * @param e the ActionEvent created when Chapter 6 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(6);
				dispose();
			}
		});
		chapter7Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 7.
			 *
			 * @param e the ActionEvent created when Chapter 7 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(7);
				dispose();
			}
		});
		chapter8Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 8.
			 *
			 * @param e the ActionEvent created when Chapter 8 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(8);
				dispose();
			}
		});
		そば類練習Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 9.
			 *
			 * @param e the ActionEvent created when Chapter 9 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(9);
				dispose();
			}
		});
		薬の効き目Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 10.
			 *
			 * @param e the ActionEvent created when Chapter 10 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(10);
				dispose();
			}
		});
		カップラーメンButton.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 11.
			 *
			 * @param e the ActionEvent created when Chapter 11 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(11);
				dispose();
			}
		});
		chapter12Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 12.
			 *
			 * @param e the ActionEvent created when Chapter 12 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(12);
				dispose();
			}
		});
		chapter13Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 13.
			 *
			 * @param e the ActionEvent created when Chapter 13 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(13);
				dispose();
			}
		});
		chapter14Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 14.
			 *
			 * @param e the ActionEvent created when Chapter 14 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(14);
				dispose();
			}
		});
		chapter15Button.addActionListener(new ActionListener() {
			/**
			 * Opens the home screen for Chapter 15.
			 *
			 * @param e the ActionEvent created when Chapter 15 is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeScreen(15);
				dispose();
			}
		});
		backButton.addActionListener(new ActionListener() {
			/**
			 * Returns the user to the welcome screen.
			 *
			 * @param e the ActionEvent created when Back is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new WelcomeScreen();
				dispose();
			}
		});

		mainPanel.add(Box.createVerticalStrut(55));
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(subtitleLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter1Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter2Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter3Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter4Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter5Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter6Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter7Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter8Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(そば類練習Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(薬の効き目Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(カップラーメンButton);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter12Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter13Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter14Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(chapter15Button);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(backButton);

		add(mainPanel);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}