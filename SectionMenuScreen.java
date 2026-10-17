import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lead Author(s):
 * 
 * @author Cruz Yanez
 *
 *         Other Contributors: None
 *
 *         References: Morelli, R., & Walde, R. (2016). Java, Java, Java:
 *         Object-Oriented Problem Solving. Retrieved from:
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 *         Chapter 8.2: Java's Inheritance Mechanism Covers: extends JFrame
 *
 *         Chapter 13.2: Java GUIs: From AWT to Swing Covers: why javax.swing
 *         classes like JFrame and JPanel are used
 *
 *         Chapter 13.3: The Swing Component Set Covers: JLabel, JPanel, and
 *         RoundedButton, which extends JButton
 *
 *         Chapter 13.5: The Java Event Model Covers: addActionListener() and
 *         actionPerformed()
 *
 *         Chapter 13.6: Case Study: Designing a Basic GUI Covers: overall
 *         pattern of building a GUI screen with panels and listeners
 *
 *         Chapter 13.7: Containers and Layout Managers Covers: GridLayout,
 *         BoxLayout, and organizing GUI components
 *
 *         Other References:
 *
 *         Oracle. (n.d.). Class JFrame. Java Platform SE API Specification.
 *         Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 *         Oracle. (n.d.). Class JPanel. Java Platform SE API Specification.
 *         Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JPanel.html
 *
 *         Oracle. (n.d.). Class JLabel. Java Platform SE API Specification.
 *         Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JLabel.html
 *
 *         Oracle. (n.d.). Class GridLayout. Java Platform SE API Specification.
 *         Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/GridLayout.html
 *
 *         Oracle. (n.d.). Interface ActionListener. Java Platform SE API
 *         Specification. Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/event/ActionListener.html
 *
 *         Version/date: 1.0 — April 9, 2026 — Class created with five practice
 *         mode buttons and sectionName field added.
 *
 *         1.1 — April 16, 2026 — Connected all practice screens to their
 *         corresponding buttons and applied a green color theme.
 *
 *         1.2 — April 23, 2026 — Added missing Back button event handling.
 *
 *         1.3 — April 24, 2026 — Added Back button navigation logic, Javadocs,
 *         and relationship comments.
 *
 *         1.4 — May 19, 2026 — Replaced e.getSource() conditional structures
 *         with button-specific ActionListener objects.
 *
 *         1.5 — September 1, 2026 — Added the Japanese-definition vocabulary
 *         practice button.
 *
 *         Responsibilities of class: Displays a section menu that allows the
 *         user to choose a practice mode for the selected study section.
 */

// IS-A: SectionMenuScreen is a JFrame because it extends JFrame.
public class SectionMenuScreen extends JFrame {
	private String sectionName;
	// HAS-A: SectionMenuScreen has a section name to track
	// the selected study category.

	private int selectedChapter;
	// HAS-A: SectionMenuScreen has a chapter number to track
	// the selected textbook chapter.

	private RoundedButton flashcardsButton;
	// HAS-A: SectionMenuScreen has a Flashcards button.

	private RoundedButton writingButton;
	// HAS-A: SectionMenuScreen has a Writing Practice button.

	private RoundedButton matchingButton;
	// HAS-A: SectionMenuScreen has a Matching button.

	private RoundedButton fillBlankButton;
	// HAS-A: SectionMenuScreen has a Fill in the Blank button.

	private RoundedButton definitionButton;
	// HAS-A: SectionMenuScreen has a Definition button for
	// Japanese-definition vocabulary practice.
	private RoundedButton sentenceButton;

	private RoundedButton backButton;
	// HAS-A: SectionMenuScreen has a Back button.

	/**
	 * Creates the section menu for the selected section and chapter.
	 *
	 * @param sectionName     the selected study section
	 * @param selectedChapter the selected textbook chapter
	 */
	public SectionMenuScreen(String sectionName, int selectedChapter) {
		this.sectionName = sectionName;
		this.selectedChapter = selectedChapter;

		setTitle("マイ練習 - " + sectionName);
		setSize(1400, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Color backgroundColor = new Color(245, 250, 248);

		Color buttonColor = new Color(215, 235, 225);

		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.setBackground(backgroundColor);

		JLabel titleLabel = new JLabel(sectionName + " Practice");

		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));

		titleLabel.setForeground(new Color(40, 100, 90));

		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel subtitleLabel = new JLabel("Choose a practice mode");

		subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

		subtitleLabel.setForeground(new Color(90, 90, 90));

		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainPanel.add(Box.createVerticalStrut(40));
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(subtitleLabel);
		mainPanel.add(Box.createVerticalStrut(30));

		/*
		 * Create all the practice buttons.
		 */
		flashcardsButton = new RoundedButton("Flashcards（フラッシュカード）");

		writingButton = new RoundedButton("Writing Practice（書く練習）");

		matchingButton = new RoundedButton("Matching（マッチング）");

		fillBlankButton = new RoundedButton("Fill in the Blank（穴埋め）");

		definitionButton = new RoundedButton("Definitions（日本語の意味）");
		sentenceButton = new RoundedButton("Sentences (日本の文例");

		backButton = new RoundedButton("Back（戻る）");

		/*
		 * The Definition button is currently available only for the Vocabulary section.
		 */
		RoundedButton[] buttons;

		if (sectionName.equalsIgnoreCase("Vocabulary")) {
			buttons = new RoundedButton[] { flashcardsButton, writingButton, matchingButton, fillBlankButton,
					definitionButton, sentenceButton, backButton };
		} else {
			buttons = new RoundedButton[] { flashcardsButton, writingButton, matchingButton, fillBlankButton,
					definitionButton, sentenceButton, backButton };
		}

		/*
		 * Use the number of buttons to determine how many rows the menu requires.
		 */
		JPanel buttonPanel = new JPanel(new GridLayout(buttons.length, 1, 15, 15));

		buttonPanel.setBackground(backgroundColor);

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));

		// HAS-MANY: SectionMenuScreen has many buttons grouped
		// in an array so they can share the same formatting.
		for (RoundedButton button : buttons) {
			button.setFont(new Font("SansSerif", Font.BOLD, 18));

			button.setBackground(buttonColor);
			button.setForeground(Color.BLACK);

			buttonPanel.add(button);
		}

		/*
		 * Open the Flashcard screen.
		 */
		flashcardsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FlashcardScreen(sectionName, selectedChapter);

				dispose();
			}
		});

		/*
		 * Open the Writing Practice screen.
		 */
		writingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WritingPracticeScreen(sectionName, selectedChapter);

				dispose();
			}
		});

		/*
		 * Open the Matching screen.
		 */
		matchingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MatchingScreen(sectionName, selectedChapter);

				dispose();
			}
		});

		/*
		 * Open the Fill in the Blank screen.
		 */
		fillBlankButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FillInBlankScreen(sectionName, selectedChapter);

				dispose();
			}
		});

		/*
		 * Open the Japanese Definition screen.
		 */
		definitionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DefinitionScreen(sectionName, selectedChapter);

				dispose();
			}
		});

		sentenceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SentencePracticeScreen(sectionName, selectedChapter);

				dispose();
			}
		});

		/*
		 * Return to the chapter selection screen.
		 */
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChapterSelectScreen();

				dispose();
			}
		});

		mainPanel.add(buttonPanel);

		add(mainPanel);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}