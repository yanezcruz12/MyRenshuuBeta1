import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
 *         Swing Component Set Covers: JLabel, JTextField, JPanel used to build
 *         the practice card Chapter 13.5: The Java Event Model Covers:
 *         addActionListener(this) and actionPerformed(ActionEvent e) Chapter
 *         13.6: Case Study: Designing a Basic GUI Covers: overall pattern of
 *         building a GUI screen with panels and buttons Chapter 13.7:
 *         Containers and Layout Managers Covers: BoxLayout stacking components
 *         vertically inside the card panel Chapter 10.7: From the Java Library:
 *         JOptionPane Covers: showMessageDialog used when vocabulary file
 *         cannot be loaded Chapter 11.2: Streams and Files Covers:
 *         FileNotFoundException thrown by DataLoader.loadVocabulary() Chapter
 *         4.6: A Graphical User Interface Covers: JTextField as an input
 *         component — getText(), setHorizontalAlignment(), and
 *         requestFocusInWindow() used in updateQuestion() and checkAnswer()
 *         Chapter 9 Case Study: An N-Player Computer Game Covers: (currentIndex
 *         + 1) % items.size() wrap-around pattern used in actionPerformed() to
 *         cycle back to index 0 after the last question
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
 *         Oracle. (n.d.). Class String. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html
 *         Covers: .trim(), .toLowerCase(), .equals() used in checkAnswer()
 *
 *         Version/date: 1.0 — April 10, 2026 — Class created with basic window
 *         and layout designed 1.1 — April 16, 2026 — loadData() and
 *         updateQuestion() added, prompt display and text field wired up. 1.2 —
 *         April 23, 2026 — checkAnswer() completed with toLowerCase()
 *         comparison, Submit, Next, and Back buttons work. 1.3 — April 24, 2026
 *         — Javadoc improvements and concept comments added 1.4 — April 27,
 *         2026 — Added missing Chapter 4.6 citation for JTextField, added
 *         missing Chapter 9 citation for modulo wrap-around pattern 1.5 — May
 *         5, 2026 — Added questionNumberLabel and updated updateQuestion() so
 *         the user can see progress such as “Question 1 / 15.” 1.6 — May 7,
 *         2026 — Added shuffledItems to randomize question order and
 *         Math.min(15, shuffledItems.size()) to limit each test session to 15
 *         questions. 1.6 — May 19, 2026 — Updated GUI screens to use
 *         button-specific ActionListener objects instead of e.getSource() event
 *         handling.
 * 
 *         Responsibilities of class: Displays a writing practice screen for the
 *         マイ練習 application. This class loads vocabulary items, shows one prompt
 *         at a time, accepts typed user input, checks the answer, and lets the
 *         user move to the next question or return to the section menu.
 */

//IS-A: WritingPracticeScreen is a JFrame because it extends JFrame.
public class WritingPracticeScreen extends JFrame {
	private String sectionName;
	// HAS-A: WritingPracticeScreen has a section name to identify the selected
	// study section.

	private int selectedChapter;
	// HAS-A: WritingPracticeScreen has a selected chapter number to load the
	// correct chapter data.

	private ArrayList<StudyItem> items;
	// HAS-MANY: WritingPracticeScreen has many StudyItem objects loaded from a
	// data file.

	private ArrayList<StudyItem> testItems;
	// HAS-MANY: WritingPracticeScreen has many StudyItem objects selected for
	// one writing practice session.

	private ArrayList<JTextField[]> practiceFields;
	// HAS-MANY: WritingPracticeScreen has many JTextField arrays.
	// Each array stores five text boxes for one StudyItem row.

	private int attempts;
	// HAS-A: WritingPracticeScreen has an attempt count for the current
	// practice session.

	private int correctAnswers;
	// HAS-A: WritingPracticeScreen has a correct answer count for the current
	// practice session.

	private boolean scoreSaved;
	// HAS-A: WritingPracticeScreen has a score saved value to prevent saving
	// the same score twice.

	private JLabel titleLabel;
	// HAS-A: WritingPracticeScreen has a title label to display the screen
	// heading.

	private JLabel feedbackLabel;
	// HAS-A: WritingPracticeScreen has a feedback label to show completion
	// and score messages.

	private RoundedButton submitButton;
	// HAS-A: WritingPracticeScreen has a submit button to check all typed
	// answers.

	private RoundedButton backButton;
	// HAS-A: WritingPracticeScreen has a back button to return to the section
	// menu.

	/**
	 * Creates the writing practice screen for the selected section. The constructor
	 * loads the study data, creates a 15-word practice session, builds the GUI, and
	 * connects each button to its own ActionListener.
	 *
	 * @param sectionName     the name of the study section to practice
	 * @param selectedChapter the selected textbook chapter
	 */
	public WritingPracticeScreen(String sectionName, int selectedChapter) {
		this.sectionName = sectionName;
		this.selectedChapter = selectedChapter;
		this.items = new ArrayList<>();
		this.testItems = new ArrayList<>();
		this.practiceFields = new ArrayList<>();
		this.attempts = 0;
		this.correctAnswers = 0;
		this.scoreSaved = false;

		setTitle("マイ練習 - " + sectionName + " Writing Practice");
		setSize(1400, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		loadData();
		createTestItems();
		CreateLayout();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Builds the main writing practice GUI. This version displays all 15 words at
	 * once with five text boxes for each word.
	 */
	private void CreateLayout() {
		Color backgroundColor = new Color(252, 251, 247);
		Color buttonColor = new Color(230, 238, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);

		titleLabel = new JLabel(sectionName + " Writing Practice");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		titleLabel.setForeground(new Color(44, 74, 120));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		feedbackLabel = new JLabel("Type each answer five times, then click Submit.");
		feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		feedbackLabel.setForeground(new Color(90, 90, 90));
		feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel practicePanel = createPracticePanel();

		JScrollPane scrollPane = new JScrollPane(practicePanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setMaximumSize(new java.awt.Dimension(1100, 1120));
		scrollPane.setPreferredSize(new java.awt.Dimension(1100, 1120));
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

		submitButton = new RoundedButton("Submit（答える）");
		backButton = new RoundedButton("Back（戻る）");

		RoundedButton[] buttons = { submitButton, backButton };
		// HAS-MANY: WritingPracticeScreen has many RoundedButton objects
		// grouped in an array so they can share formatting.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);

		for (RoundedButton button : buttons) {
			button.setFont(new Font("SansSerif", Font.BOLD, 12));
			button.setBackground(buttonColor);
			buttonPanel.add(button);
		}

		submitButton.addActionListener(new ActionListener() {
			/**
			 * Checks all five typed answers for each word.
			 *
			 * @param e the ActionEvent created when Submit is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				checkAnswer();
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

		mainPanel.add(Box.createVerticalStrut(25));
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(15));
		mainPanel.add(feedbackLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(scrollPane);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(buttonPanel);

		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the writing practice table. The table has one word column and five
	 * practice columns.
	 *
	 * @return the JPanel containing the writing practice table
	 */
	private JPanel createPracticePanel() {
		practiceFields.clear();

		JPanel practicePanel = new JPanel();
		practicePanel.setLayout(new GridLayout(testItems.size() + 1, 6, 5, 8));
		practicePanel.setBackground(Color.WHITE);
		practicePanel.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 2),
						BorderFactory.createEmptyBorder(20, 20, 20, 20)));

		practicePanel.add(createHeaderLabel("言葉"));
		practicePanel.add(createHeaderLabel("練習 1"));
		practicePanel.add(createHeaderLabel("練習 2"));
		practicePanel.add(createHeaderLabel("練習 3"));
		practicePanel.add(createHeaderLabel("練習 4"));
		practicePanel.add(createHeaderLabel("練習 5"));

		if (testItems.isEmpty()) {
			practicePanel.add(new JLabel("No items found."));
			return practicePanel;
		}

		for (StudyItem item : testItems) {
			JLabel wordLabel = new JLabel(item.getPrompt());
			wordLabel.setFont(new Font("Serif", Font.BOLD, 10));

			practicePanel.add(wordLabel);

			JTextField[] rowFields = new JTextField[5];
			// HAS-MANY: Each StudyItem row has five JTextField objects so the
			// user can type the same answer five times.

			for (int i = 0; i < 5; i++) {
				JTextField field = new JTextField();
				field.setFont(new Font("SansSerif", Font.PLAIN, 10));
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setPreferredSize(new java.awt.Dimension(10, 10));

				rowFields[i] = field;
				practicePanel.add(field);
			}

			practiceFields.add(rowFields);
		}

		return practicePanel;
	}

	/**
	 * Creates a formatted header label for the practice table.
	 *
	 * @param text the text displayed in the header label
	 * @return the formatted JLabel
	 */
	private JLabel createHeaderLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("SansSerif", Font.BOLD, 16));
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	/**
	 * Loads study data from the text file for the selected section. If the file
	 * cannot be found, a message dialog is shown.
	 */
	private void loadData() {
		try {
			// Builds the folder path for the selected textbook chapter so the
			// program can load the correct writing practice files for that chapter.
			String folderPath = "data/chapter" + selectedChapter + "/";

			if (sectionName.equals("Vocabulary")) {
				items = DataLoader.loadStudyItems(folderPath + "vocabulary_writing.txt", "Vocabulary");
			} else if (sectionName.equals("Kanji")) {
				items = DataLoader.loadStudyItems(folderPath + "kanji_writing.txt", "Kanji");
			} else if (sectionName.equals("Grammar")) {
				items = DataLoader.loadStudyItems(folderPath + "grammar_writing.txt", "Grammar");
			} else if (sectionName.equals("Conjugations")) {
				items = DataLoader.loadStudyItems(folderPath + "conjugation_writing.txt", "Conjugations");
			} else if (sectionName.equals("Keigo")) {
				items = DataLoader.loadStudyItems(folderPath + "keigo_writing.txt", "Keigo");
			} else if (sectionName.equals("Sonkeigo")) {
				items = DataLoader.loadStudyItems(folderPath + "sonkeigo_writing.txt", "Sonkeigo");
			} else if (sectionName.equals("Speech Styles")) {
				items = DataLoader.loadStudyItems(folderPath + "speech_styles_writing.txt", "Speech Styles");
			} else if (sectionName.equals("gitaigo")) {
				items = DataLoader.loadStudyItems(folderPath + "gitaigo_writing.txt", "gitaigo");
			} else if (sectionName.equals("Counters")) {
				items = DataLoader.loadStudyItems(folderPath + "counters_writing.txt", "Counters");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Could not load writing practice data file.");
		}
	}

	/**
	 * Creates a randomized 15-word practice list from the full list of loaded
	 * items. The original items list remains unchanged.
	 */
	private void createTestItems() {
		testItems.clear();

		// Copying before shuffling keeps the original file-loaded list
		// unchanged.
		ArrayList<StudyItem> shuffledItems = new ArrayList<>(items);
		Collections.shuffle(shuffledItems);

		// Math.min prevents errors if the file has fewer than 15 items.
		int numberOfQuestions = Math.min(45, shuffledItems.size());

		for (int i = 0; i < numberOfQuestions; i++) {
			testItems.add(shuffledItems.get(i));
		}
	}

	/**
	 * Checks all typed answers in the writing practice table. Each StudyItem has
	 * five text boxes, so a 15-word session creates 75 total answers.
	 */
	private void checkAnswer() {
		if (testItems.isEmpty() || scoreSaved) {
			return;
		}

		attempts = 0;
		correctAnswers = 0;

		for (int row = 0; row < testItems.size(); row++) {
			StudyItem item = testItems.get(row);
			String correctAnswer = item.getAnswer().trim().toLowerCase();

			JTextField[] fields = practiceFields.get(row);

			for (JTextField field : fields) {
				String userAnswer = field.getText().trim().toLowerCase();

				attempts++;

				if (userAnswer.equals(correctAnswer)) {
					correctAnswers++;
					field.setBackground(new Color(220, 245, 220));
				} else {
					field.setBackground(new Color(255, 220, 220));
				}

				field.setEditable(false);
			}
		}

		feedbackLabel.setText("You got " + correctAnswers + " out of " + attempts + " correct.");
		feedbackLabel.setForeground(new Color(40, 130, 70));

		saveScore();

		submitButton.setEnabled(false);
	}

	/**
	 * Saves the completed writing practice score to ScoreManager.
	 */
	private void saveScore() {
		if (scoreSaved || attempts == 0) {
			return;
		}

		double percent = (double) correctAnswers / attempts;
		// Calculates the score percentage using the total number of text box
		// answers.

		java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();

		String scoreText = "Writing Practice - " + sectionName + " - Test " + ScoreManager.getTestNumber() + " | "
				+ correctAnswers + "/" + attempts + " | " + percentFormat.format(percent);
		// Builds the completed score text shown on the ScoreCardScreen.

		ScoreManager.addScore(scoreText);
		// Sends the completed score to ScoreManager.

		scoreSaved = true;
		// Prevents the same test from being saved multiple times.
	}
}