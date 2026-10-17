import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

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
 *         Chapter 8.2: Java's Inheritance Mechanism Covers: extends JFrame and
 *         implements ActionListener Chapter 8.3: Abstract Classes, Interfaces,
 *         and Polymorphism Covers: implementing the ActionListener interface
 *         contract Chapter 13.2: Java GUIs: From AWT to Swing Covers: why
 *         javax.swing classes like JFrame and JPanel are used Chapter 13.3: The
 *         Swing Component Set Covers: JLabel and JPanel used to build the
 *         flashcard display Chapter 13.5: The Java Event Model Covers:
 *         addActionListener(this) and actionPerformed(ActionEvent e) Chapter
 *         13.6: Case Study: Designing a Basic GUI Covers: overall pattern of
 *         building a GUI screen with panels and buttons Chapter 13.7:
 *         Containers and Layout Managers Covers: BoxLayout stacking the card
 *         label and extra label vertically Chapter 10.7: From the Java Library:
 *         JOptionPane Covers: showMessageDialog used when vocabulary file
 *         cannot be loaded Chapter 11.2: Streams and Files Covers:
 *         FileNotFoundException thrown by DataLoader.loadVocabulary() Chapter
 *         5.2: Boolean Data and Operators Covers: showingAnswer boolean and the
 *         ! operator in showingAnswer = !showingAnswer Chapter 9 Case Study: An
 *         N-Player Computer Game Covers: (currentIndex + 1) % items.size()
 *         wrap-around pattern used in actionPerformed() to cycle back to index
 *         0 after the last card
 *
 *         Akira Miura. An Integrated Approach to Intermediate Japanese (Revised
 *         Edition) Retrieved from https://online.fliphtml5.com/cialx/jzho/
 *         Chapter 4: 許可をもらう
 *
 *         Other References: Oracle. (n.d.). Class JFrame. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 *         Oracle. (n.d.). Class JOptionPane. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JOptionPane.html
 *
 *         Oracle. (n.d.). Class ArrayList. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
 *
 *         Oracle. (n.d.). Operators Summary. The Java Tutorials. Retrieved from
 *         https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html
 *         Covers: ! logical complement operator used in showingAnswer =
 *         !showingAnswer
 *
 *         Version/date: 1.0 — April 11, 2026 — Class created, basic window and
 *         card layout designed 1.1 — April 16, 2026 — loadData() and
 *         updateCard() added, showingAnswer boolean introduced for flip
 *         mechanic 1.2 — April 23, 2026 — Flip, Next, and Back buttons
 *         connected to ActionListener, each button now triggers its correct
 *         action when clicked, extraLabel added to show reading hints on answer
 *         side. 1.3 — April 25, 2026 — Javadoc improvements, concept comments
 *         added. 1.4 — April 27, 2026 — Added missing Chapter 9 citation for
 *         modulo wrap-around pattern 1.5 — April 28, 2026 — Added missing
 *         Chapter 9 citation for modulo wrap-around pattern 1.6 — May 19, 2026
 *         — Updated GUI screens to use button-specific ActionListener objects
 *         instead of e.getSource() event handling. 1.7 — May 21, 2026 — Added
 *         "previous" button 1.8 - June 9, 2026 - Added Furigana CardPanel -
 *         Furigana will appear above the Kanji.
 *
 *         Responsibilities of class: Displays a flashcard practice screen for
 *         the マイ練習 application. This class loads vocabulary items, shows one
 *         flashcard at a time, flips between the prompt and answer, and lets
 *         the user move to the next card or return to the section menu.
 */

// IS-A: FlashcardScreen is a JFrame because it extends JFrame.
public class FlashcardScreen extends JFrame {
	private String sectionName;
	private int selectedChapter;
	// HAS-A: FlashcardScreen has a section name to identify the selected study
	// section.

	private ArrayList<StudyItem> items;
	// HAS-MANY: FlashcardScreen has many StudyItem objects stored in an
	// ArrayList.

	private int currentIndex;
	// HAS-A: FlashcardScreen has a current index to track the current
	// flashcard.

	private boolean showingAnswer;
	// HAS-A: FlashcardScreen has a boolean value to track whether the answer
	// side is showing.

	private JLabel sectionLabel;
	// HAS-A: FlashcardScreen has a section label to display the selected study
	// section.

	private JLabel cardLabel;
	// HAS-A: FlashcardScreen has a card label to display the flashcard text.

	private JLabel extraLabel;
	// HAS-A: FlashcardScreen has an extra label to display additional
	// information.

	private RoundedButton flipButton;
	// HAS-A: FlashcardScreen has a flip button to switch between prompt and
	// answer.

	private RoundedButton nextButton;
	// HAS-A: FlashcardScreen has a next button to move to the next flashcard.

	private RoundedButton backButton;
	// HAS-A: FlashcardScreen has a back button to return to the section menu.

	private RoundedButton previousButton;
	// HAS-A: FlashcardScreen has a previous button to move to the previous
	// flashcard

	private JPanel cardPanel;

	/**
	 * Creates a flashcard screen for the selected section. The constructor loads
	 * the study data, builds the flashcard GUI, and connects each button to its own
	 * ActionListener.
	 *
	 * @param sectionName the name of the section to study
	 */
	public FlashcardScreen(String sectionName, int selectedChapter) {
		this.sectionName = sectionName;
		this.selectedChapter = selectedChapter;
		this.items = new ArrayList<>();
		this.currentIndex = 0;
		this.showingAnswer = false;

		setTitle("マイ練習 - " + sectionName + " Flashcards");
		setSize(1400, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		loadData();

		Color backgroundColor = new Color(252, 251, 247);
		Color buttonColor = new Color(230, 238, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);

		sectionLabel = new JLabel(sectionName + " Flashcards");
		sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		sectionLabel.setForeground(new Color(44, 74, 120));
		sectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		cardLabel = new JLabel("", JLabel.CENTER);
		cardLabel.setFont(new Font("Serif", Font.BOLD, 22));
		cardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		extraLabel = new JLabel(" ");
		extraLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		extraLabel.setForeground(new Color(100, 100, 100));
		extraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		cardPanel = new JPanel();
		cardPanel.setBackground(Color.WHITE);
		cardPanel.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 2),
						BorderFactory.createEmptyBorder(40, 60, 40, 60)));

		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		Dimension cardSize = new Dimension(850, 320);
		cardPanel.setPreferredSize(cardSize);
		cardPanel.setMaximumSize(cardSize);
		cardPanel.setMinimumSize(cardSize);

		cardPanel.add(Box.createVerticalGlue());
		cardPanel.add(cardLabel);
		cardPanel.add(Box.createVerticalStrut(20));
		cardPanel.add(extraLabel);
		cardPanel.add(Box.createVerticalGlue());

		flipButton = new RoundedButton("Flip（めくる）");
		previousButton = new RoundedButton("Previous（前へ）");
		nextButton = new RoundedButton("Next（次へ）");
		backButton = new RoundedButton("Back（戻る）");

		RoundedButton[] buttons = { flipButton, previousButton, nextButton, backButton };
		// HAS-MANY: FlashcardScreen has many RoundedButton objects grouped
		// in an array so they can share formatting.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);

		for (RoundedButton button : buttons) {
			button.setFont(new Font("SansSerif", Font.BOLD, 18));
			button.setBackground(buttonColor);
			buttonPanel.add(button);
		}

		flipButton.addActionListener(new ActionListener() {
			/**
			 * Flips the flashcard between the prompt and answer.
			 *
			 * @param e the ActionEvent created when Flip is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				showingAnswer = !showingAnswer;
				updateCard();
			}
		});

		previousButton.addActionListener(new ActionListener() {
			/**
			 * Moves to the previous flashcard in the study set.
			 *
			 * @param e the ActionEvent created when Previous is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentIndex > 0) {
					currentIndex--;
					showingAnswer = false;
					updateCard();
				}
			}
		});

		nextButton.addActionListener(new ActionListener() {
			/**
			 * Moves to the next flashcard in the study set.
			 *
			 * @param e the ActionEvent created when Next is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!items.isEmpty()) {
					currentIndex = (currentIndex + 1) % items.size();
					showingAnswer = false;
					updateCard();
				}
			}
		});

		backButton.addActionListener(new ActionListener() {
			/**
			 * Returns the user to the section menu.
			 *
			 * @param e the ActionEvent created when Back is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				new SectionMenuScreen(sectionName, selectedChapter);
				dispose();
			}
		});

		mainPanel.add(Box.createVerticalStrut(35));
		mainPanel.add(sectionLabel);
		mainPanel.add(Box.createVerticalStrut(40));
		mainPanel.add(cardPanel);
		mainPanel.add(Box.createVerticalStrut(30));
		mainPanel.add(buttonPanel);

		add(mainPanel);
		updateCard();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Loads study data from the text file for the selected section. If the file
	 * cannot be found, a message dialog is shown.
	 */
	private void loadData() {
		try {
			// Builds the folder path for the selected textbook chapter so the
			// program can load the correct data files for that chapter.
			String folderPath = "data/chapter" + selectedChapter + "/";

			if (sectionName.equals("Vocabulary")) {
				items = DataLoader.loadStudyItems(folderPath + "vocabulary.txt", "Vocabulary");
			} else if (sectionName.equals("Kanji")) {
				items = DataLoader.loadStudyItems(folderPath + "kanji.txt", "Kanji");
			} else if (sectionName.equals("Grammar")) {
				items = DataLoader.loadStudyItems(folderPath + "grammar.txt", "Grammar");
			} else if (sectionName.equals("Conjugations")) {
				items = DataLoader.loadStudyItems(folderPath + "conjugation.txt", "Conjugations");
			} else if (sectionName.equals("Keigo")) {
				items = DataLoader.loadStudyItems(folderPath + "keigo.txt", "Keigo");
			} else if (sectionName.equals("Sonkeigo")) {
				items = DataLoader.loadStudyItems(folderPath + "sonkeigo.txt", "Sonkeigo");
			} else if (sectionName.equals("Speech Styles")) {
				items = DataLoader.loadStudyItems(folderPath + "speech_styles.txt", "Speech Styles");
			} else if (sectionName.equals("gitaigo")) {
				items = DataLoader.loadStudyItems(folderPath + "gitaigo.txt", "gitaigo");
			} else if (sectionName.equals("Counters")) {
				items = DataLoader.loadStudyItems(folderPath + "counters.txt", "Counters");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Could not load study data file.");
		}
	}

	/**
	 * Updates the flashcard display using the current StudyItem.
	 *
	 * This method now clears cardPanel and rebuilds it each time the card changes.
	 * On the front side, it uses FuriganaLabel so words and example sentences with
	 * the format Kanji(furigana) can display furigana above the kanji. On the
	 * answer side, it uses regular JLabels for the English answer and translation.
	 */
	private void updateCard() {
		cardPanel.removeAll();

		if (items.isEmpty()) {
			cardLabel.setText("No study items found.");
			cardPanel.add(Box.createVerticalGlue());
			cardPanel.add(cardLabel);
			cardPanel.add(Box.createVerticalGlue());
			cardPanel.revalidate();
			cardPanel.repaint();
			return;
		}

		StudyItem currentItem = items.get(currentIndex);

		cardPanel.add(Box.createVerticalGlue());

		if (!showingAnswer) {
			cardPanel.add(new FuriganaLabel(currentItem.getPrompt()));
			cardPanel.add(Box.createVerticalStrut(20));
			cardPanel.add(new FuriganaLabel(currentItem.getExtra()));
		} else {
			cardLabel.setText(currentItem.getAnswer());
			extraLabel.setText(currentItem.getExampleTranslation());

			cardPanel.add(cardLabel);
			cardPanel.add(Box.createVerticalStrut(20));
			cardPanel.add(extraLabel);
		}

		cardPanel.add(Box.createVerticalGlue());

		cardPanel.revalidate();
		cardPanel.repaint();
	}
}