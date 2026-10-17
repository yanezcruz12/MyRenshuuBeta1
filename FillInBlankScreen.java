import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

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
 *         Chapter 8.2: Java's Inheritance Mechanism Chapter 8.3: Abstract
 *         Classes, Interfaces, and Polymorphism Chapter 13.2: Java GUIs: From
 *         AWT to Swing Chapter 13.3: The Swing Component Set Chapter 13.5: The
 *         Java Event Model Chapter 13.6: Case Study: Designing a Basic GUI
 *         Chapter 13.7: Containers and Layout Managers Chapter 10.7: From the
 *         Java Library: JOptionPane Chapter 5.2: Boolean Data and Operators
 *         Covers: ! operator used in if (!items.isEmpty()) Chapter 11.2:
 *         Streams and Files Chapter 11.3: Case Study: Reading and Writing Text
 *         Files Covers: FileNotFoundException handling in loadData() Chapter
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
 *         Other References Oracle. (n.d.). Class JFrame. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 *         Oracle. (n.d.). Class ArrayList. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
 *
 *         Oracle. (n.d.). Class FileNotFoundException. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileNotFoundException.html
 *
 *         Oracle. (n.d.). Class JOptionPane. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JOptionPane.html
 *
 *         Oracle. (n.d.). Class String. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html
 *         Covers: .trim(), .toLowerCase(), .equals() used in checkAnswer()
 *
 *         Version/date: 1.0 — April 9, 2026 — Class created, basic window and
 *         layout designed 1.1 — April 16, 2026 — loadData() added,
 *         vocabulary_fill_blank.txt format decided updateQuestion() written to
 *         display clue and blank sentence 1.2 — April 23, 2026 — checkAnswer()
 *         completed, Submit and Next buttons wired up Back button navigation
 *         added, submitted for grading 1.3 — April 25, 2026 — Fixed getAnswer()
 *         bug in checkAnswer() due to getPrompt() returning the full sentence
 *         instead of the word Added toLowerCase() so capitalization does not
 *         count against use 1.4 — April 27, 2026 — Added missing Chapter 4.6
 *         citation for JTextField, added missing Chapter 9 citation for modulo
 *         wrap-around pattern 1.5 — May 5, 2026 — Added questionNumberLabel and
 *         updated updateQuestion() so the user can see progress such as
 *         “Question 1 / 15.” 1.6 — May 7, 2026 — Added shuffledItems to
 *         randomize question order and Math.min(15, shuffledItems.size()) to
 *         limit each test session to 15 questions. 1.7 — May 19, 2026 — Updated
 *         GUI screens to use button-specific ActionListener objects instead of
 *         e.getSource() event handling. 1.9 — May 26, 2026 — Added try/catch
 *         exception handling to setup, Submit, Next, Back, updateQuestion(),
 *         and chapter-based file loading so the user sees a JOptionPane message
 *         instead of the program crashing. 1.10 - June 5, 2026 - Added
 *         speech_styles.txt file for chapter two addition.
 * 
 *         Responsibilities of class: Displays a fill-in-the-blank practice
 *         screen for the マイ練習 application. This class loads vocabulary items,
 *         shows a clue and blank sentence, accepts user input, checks the
 *         answer, and allows the user to move between questions.
 */

//IS-A: FillInBlankScreen is a JFrame because it extends JFrame.
public class FillInBlankScreen extends JFrame {
	private String sectionName;
	// HAS-A: FillInBlankScreen has a section name to identify the selected
	// study section.

	private int selectedChapter;
	// HAS-A: FillInBlankScreen has a selected chapter to load chapter data.

	private ArrayList<StudyItem> items;
	// HAS-MANY: FillInBlankScreen has many StudyItem objects loaded from a data
	// file.

	private ArrayList<StudyItem> testItems;
	// HAS-MANY: FillInBlankScreen has many StudyItem objects selected for one
	// test session.

	private int currentIndex;
	// HAS-A: FillInBlankScreen has a current index to track the current
	// question.

	private int attempts;
	// HAS-A: FillInBlankScreen has an attempt count for the current test.

	private int correctAnswers;
	// HAS-A: FillInBlankScreen has a correct answer count for the current test.

	private boolean scoreSaved;
	// HAS-A: FillInBlankScreen has a score saved value to prevent saving the
	// same test twice.

	private boolean answered;
	// HAS-A: FillInBlankScreen has an answered value to prevent counting the
	// same question more than once.

	private JLabel titleLabel;
	// HAS-A: FillInBlankScreen has a title label to display the screen heading.

	private JLabel questionNumberLabel;
	// HAS-A: FillInBlankScreen has a question number label to show test
	// progress.

	private JLabel clueLabel;
	// HAS-A: FillInBlankScreen has a clue label to display the hint or
	// translation.

	private JLabel blankLabel;
	// HAS-A: FillInBlankScreen has a blank label to display the sentence with
	// the missing word.

	private JLabel feedbackLabel;
	// HAS-A: FillInBlankScreen has a feedback label to show correct or
	// incorrect messages.

	private JTextField answerField;
	// HAS-A: FillInBlankScreen has an answer field where the user types a
	// response.

	private RoundedButton submitButton;
	// HAS-A: FillInBlankScreen has a submit button to check the user's answer.

	private RoundedButton nextButton;
	// HAS-A: FillInBlankScreen has a next button to move forward in the test.

	private RoundedButton backButton;
	// HAS-A: FillInBlankScreen has a back button to return to the section menu.

	private RoundedButton previousButton;
	// HAS-A: FlashcardScreen has a previous button to move to the previous
	// flashcard

	public FillInBlankScreen(String sectionName, int selectedChapter) {
		this.sectionName = sectionName;
		this.selectedChapter = selectedChapter;
		this.items = new ArrayList<>();
		this.testItems = new ArrayList<>();
		this.currentIndex = 0;
		this.attempts = 0;
		this.correctAnswers = 0;
		this.scoreSaved = false;
		this.answered = false;

		setTitle("マイ練習 - " + sectionName + " Fill in the Blank");
		setSize(1400, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			loadData();
			createTestItems();
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "An error occurred while setting up fill-in-the-blank practice.",
					"Application Error", JOptionPane.ERROR_MESSAGE);
		}

		Color backgroundColor = new Color(252, 251, 247);
		Color buttonColor = new Color(230, 238, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);

		titleLabel = new JLabel(sectionName + " Fill in the Blank");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLabel.setForeground(new Color(44, 74, 120));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		questionNumberLabel = new JLabel("");
		questionNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		questionNumberLabel.setForeground(new Color(90, 90, 90));
		questionNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		clueLabel = new JLabel("");
		clueLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		clueLabel.setForeground(new Color(70, 70, 70));
		clueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		blankLabel = new JLabel("______");
		blankLabel.setFont(new Font("Serif", Font.BOLD, 20));
		blankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		answerField = new JTextField();
		answerField.setFont(new Font("Serif", Font.PLAIN, 20));
		answerField.setMaximumSize(new java.awt.Dimension(400, 45));
		answerField.setHorizontalAlignment(JTextField.CENTER);

		feedbackLabel = new JLabel("Type the missing Japanese word.");
		feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		feedbackLabel.setForeground(new Color(90, 90, 90));
		feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(Color.WHITE);
		cardPanel.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 2),
						BorderFactory.createEmptyBorder(45, 60, 45, 60)));
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cardPanel.setMaximumSize(new java.awt.Dimension(850, 500));

		cardPanel.add(Box.createVerticalGlue());
		cardPanel.add(questionNumberLabel);
		cardPanel.add(Box.createVerticalStrut(15));
		cardPanel.add(clueLabel);
		cardPanel.add(Box.createVerticalStrut(25));
		cardPanel.add(blankLabel);
		cardPanel.add(Box.createVerticalStrut(25));
		cardPanel.add(answerField);
		cardPanel.add(Box.createVerticalStrut(20));
		cardPanel.add(feedbackLabel);
		cardPanel.add(Box.createVerticalGlue());

		submitButton = new RoundedButton("Submit（答える）");
		nextButton = new RoundedButton("Next（次へ）");
		previousButton = new RoundedButton("Previous (前へ) ");
		backButton = new RoundedButton("Back（戻る）");

		RoundedButton[] buttons = { submitButton, nextButton, previousButton, backButton };
		// HAS-MANY: FillInBlankScreen has many RoundedButton objects grouped
		// in an array so they can share formatting.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);

		for (RoundedButton button : buttons) {
			button.setFont(new Font("SansSerif", Font.BOLD, 18));
			button.setBackground(buttonColor);
			buttonPanel.add(button);
		}

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					checkAnswer();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(FillInBlankScreen.this,
							"An error occurred while checking your answer.", "Application Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!testItems.isEmpty()) {
						if (currentIndex < testItems.size() - 1) {
							currentIndex++;
							updateQuestion();
						} else {
							saveScore();

							feedbackLabel.setText("Test complete! Score saved.");
							feedbackLabel.setForeground(new Color(40, 130, 70));
							nextButton.setEnabled(false);
							submitButton.setEnabled(false);
						}
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(FillInBlankScreen.this,
							"An error occurred while moving to the next question.", "Application Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		previousButton.addActionListener(new ActionListener() {
			/**
			 * Moves to the previous question in the study set.
			 *
			 * @param e the ActionEvent created when Previous is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentIndex > 0) {
					currentIndex--;
					updateQuestion();
				}
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SectionMenuScreen(sectionName, selectedChapter);
					dispose();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(FillInBlankScreen.this,
							"An error occurred while returning to the section menu.", "Application Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		mainPanel.add(Box.createVerticalStrut(35));
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(40));
		mainPanel.add(cardPanel);
		mainPanel.add(Box.createVerticalStrut(30));
		mainPanel.add(buttonPanel);

		add(mainPanel);

		try {
			updateQuestion();
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "An error occurred while showing the first question.",
					"Application Error", JOptionPane.ERROR_MESSAGE);
		}

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void loadData() {
		try {
			String folderPath = "data/chapter" + selectedChapter + "/";

			if (sectionName.equals("Vocabulary")) {
				items = DataLoader.loadFillBlank(folderPath + "vocabulary_fill_blank.txt", "Vocabulary");
			} else if (sectionName.equals("Kanji")) {
				items = DataLoader.loadFillBlank(folderPath + "kanji_fill_blank.txt", "Kanji");
			} else if (sectionName.equals("Grammar")) {
				items = DataLoader.loadFillBlank(folderPath + "grammar_fill_blank.txt", "Grammar");
			} else if (sectionName.equals("Conjugations")) {
				items = DataLoader.loadFillBlank(folderPath + "conjugation_fill_blank.txt", "Conjugations");
			} else if (sectionName.equals("Keigo")) {
				items = DataLoader.loadFillBlank(folderPath + "keigo_fill_blank.txt", "Keigo");
			} else if (sectionName.equals("Sonkeigo")) {
				items = DataLoader.loadFillBlank(folderPath + "sonkeigo_fill_blank.txt", "Sonkeigo");
			} else if (sectionName.equals("gitaigo")) {
				items = DataLoader.loadFillBlank(folderPath + "gitaigo_fill_blank.txt", "Sonkeigo");
			} else if (sectionName.equals("Speech Styles")) {
				items = DataLoader.loadFillBlank(folderPath + "speech_styles_fill_blank.txt", "Speech Styles");
			} else if (sectionName.equals("Counters")) // New counters 10.17
				items = DataLoader.loadFillBlank(folderPath + "counters_fill_blank.txt", "Counters");
		} catch (FileNotFoundException error) {
			JOptionPane.showMessageDialog(this,
					"Could not load fill-in-the-blank data file for Chapter " + selectedChapter + ".", "File Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(this, "An unexpected error occurred while loading fill-in-the-blank data.",
					"Application Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void createTestItems() {
		testItems.clear();

		ArrayList<StudyItem> shuffledItems = new ArrayList<>(items);
		Collections.shuffle(shuffledItems);

		int numberOfQuestions = Math.min(45, shuffledItems.size());

		for (int i = 0; i < numberOfQuestions; i++) {
			testItems.add(shuffledItems.get(i));
		}
	}

	private void updateQuestion() {
		if (testItems.isEmpty()) {
			questionNumberLabel.setText("Question 0 / 0");
			clueLabel.setText("No items found.");
			blankLabel.setText("______");
			feedbackLabel.setText("Check your fill-in-the-blank data file.");
			return;
		}

		answered = false;

		StudyItem currentItem = testItems.get(currentIndex);

		questionNumberLabel.setText("Question " + (currentIndex + 1) + " / " + testItems.size());

		clueLabel.setText(currentItem.getExtra());
		blankLabel.setText(currentItem.getPrompt());

		answerField.setText("");
		feedbackLabel.setText("Type the missing Japanese word.");
		feedbackLabel.setForeground(new Color(90, 90, 90));

		submitButton.setEnabled(true);
		answerField.requestFocusInWindow();
	}

	private void checkAnswer() {
		if (testItems.isEmpty() || answered) {
			return;
		}

		StudyItem currentItem = testItems.get(currentIndex);

		String userAnswer = answerField.getText().trim().toLowerCase();
		String correctAnswer = currentItem.getAnswer().trim().toLowerCase();

		attempts++;

		if (userAnswer.equals(correctAnswer)) {
			correctAnswers++;

			feedbackLabel.setText("Correct! 正解です！");
			feedbackLabel.setForeground(new Color(40, 130, 70));
		} else {
			feedbackLabel.setText("Incorrect. Answer: " + correctAnswer);
			feedbackLabel.setForeground(new Color(170, 60, 60));
		}

		answered = true;
		submitButton.setEnabled(false);
	}

	private void saveScore() {
		if (scoreSaved || testItems.isEmpty()) {
			return;
		}

		double percent = (double) correctAnswers / testItems.size();

		java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();

		String scoreText = "Fill in the Blank - " + sectionName + " - Test " + ScoreManager.getTestNumber() + " | "
				+ correctAnswers + "/" + testItems.size() + " | " + percentFormat.format(percent);

		ScoreManager.addScore(scoreText);

		scoreSaved = true;
	}
}