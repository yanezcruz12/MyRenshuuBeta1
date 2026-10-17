import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
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
 *         Chapter 8.2: Java's Inheritance Mechanism Covers: extends JFrame and
 *         implements ActionListener Chapter 8.3: Abstract Classes, Interfaces,
 *         and Polymorphism Covers: implementing the ActionListener interface
 *         contract Chapter 13.2: Java GUIs: From AWT to Swing Covers: why
 *         javax.swing classes like JFrame and JPanel are used Chapter 13.3: The
 *         Swing Component Set Covers: JLabel, JList, JScrollPane, JPanel used
 *         to build the matching layout Chapter 13.5: The Java Event Model
 *         Covers: addActionListener(this) and actionPerformed(ActionEvent e)
 *         Chapter 13.6: Case Study: Designing a Basic GUI Covers: overall
 *         pattern of building a GUI screen with panels and buttons Chapter
 *         13.7: Containers and Layout Managers Covers: GridLayout(1, 2, 30, 0)
 *         placing two list panels side by side Chapter 10.7: From the Java
 *         Library: JOptionPane Covers: showMessageDialog used when vocabulary
 *         file cannot be loaded Chapter 16.6: From the Java Library: The Java
 *         Collections Framework Covers: ArrayList and generic types used
 *         throughout — note: Collections.shuffle() specifically is not in the
 *         textbook; see Oracle citation below
 *
 *         Akira Miura. An Integrated Approach to Intermediate Japanese (Revised
 *         Edition) Retrieved from https://online.fliphtml5.com/cialx/jzho/
 *         Chapter 4: 許可をもらう
 * 
 *         Other References: Oracle. (n.d.). Class JFrame. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 *         Oracle. (n.d.). Class JList. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JList.html
 *
 *         Oracle. (n.d.). Class DefaultListModel. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/DefaultListModel.html
 *
 *         Oracle. (n.d.). Class ArrayList. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
 *
 *         Oracle. (n.d.). Class Collections. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html
 *         Covers: Collections.shuffle() used to randomize prompts and answers
 *         independently
 *
 *         Oracle. (n.d.). Class Math. Java Platform SE API Specification.
 *         Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Math.html
 *         Covers: Math.min(5, shuffledItems.size()) to safely cap the round
 *         size
 *
 *         Oracle. (n.d.). The for Statement. The Java Tutorials. Retrieved from
 *         https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html
 *         Covers: enhanced for-each loop (for (StudyItem item : roundItems))
 *         used in updateLists() and checkMatch()
 * 
 *         Shuffle or Randomize a List in Java. GeeksforGeeks, Retrieved from
 *         https://www.geeksforgeeks.org/java/shuffle-or-randomize-a-list-in-java/.
 * 
 *         Eck, David J. Introduction to Programming Using Java. 6th ed., Hobart
 *         and William Smith College, 2011 Retrieved from
 *         http://math.hws.edu/eck/cs124/javanotes6/c13/s2.html.
 *
 *         Kenny Yip Coding. (2024, July 29). Code Memory Card Game in Java
 *         [Video]. YouTube. Retrieved from
 *         https://www.youtube.com/watch?v=FxDirbh3tXc
 *
 *         Version/date: 1.0 — April 11, 2026 — Class created, two-list layout
 *         designed, JList and DefaultListModel as well 1.1 — April 12, 2026 —
 *         createRoundItems() added using Collections.shuffle() and Math.min()
 *         to safely select 5 random items, updateLists() added to independently
 *         shuffle prompts and answers so they do not line up 1.2 — April 23,
 *         2026 — checkMatch() completed using && to verify both prompt and
 *         answer match simultaneously, Match, New Round, and Back buttons
 *         connected to ActionListener 1.3 — April 25, 2026 — Added
 *         clearSelection() after incorrect match attempt Javadoc comments and
 *         concepts updated 1.4 — April 27, 2026 — Added Oracle citation for
 *         enhanced for-each loop, clarified Chapter 16.6 scope re:
 *         Collections.shuffle() 1.5 — May 5, 2026 — Added questionNumberLabel
 *         and updated updateQuestion() so the user can see progress such as
 *         “Question 1 / 15.” 1.6 — May 7, 2026 — Updated Math.min(15,
 *         shuffledItems.size()) so user can now see 15 questions per round. 1.7
 *         — May 20, 2026 — Updated GUI screens to use button-specific
 *         ActionListener objects instead of e.getSource() event handling.
 *
 *         Responsibilities of class: Displays a matching practice screen for
 *         the マイ練習 application. This class loads vocabulary items, presents a
 *         shuffled set of Japanese prompts and English answers, and checks
 *         whether the selected pair is correct.
 */
// IS-A: MatchingScreen is a JFrame because it extends JFrame.
public class MatchingScreen extends JFrame {
	private String sectionName;
	private int selectedChapter;
	// HAS-A: MatchingScreen has a section name to identify the selected study
	// section.

	private ArrayList<StudyItem> items;
	// HAS-MANY: MatchingScreen has many StudyItem objects loaded from the data
	// file.

	private ArrayList<StudyItem> roundItems;
	// HAS-MANY: MatchingScreen has many StudyItem objects selected for the
	// current round.

	private int attempts;
	// HAS-A: MatchingScreen has an attempt count for the current round.

	private int correctAnswers;
	// HAS-A: MatchingScreen has a correct answer count for the current round.

	private boolean scoreSaved;
	// HAS-A: MatchingScreen has a score saved value to prevent saving the same
	// round twice.

	private JList<String> promptList;
	// HAS-A: MatchingScreen has a prompt list to display Japanese prompts.

	private JList<String> answerList;
	// HAS-A: MatchingScreen has an answer list to display English answers.

	private DefaultListModel<String> promptModel;
	// HAS-A: MatchingScreen has a prompt model to manage prompt list data.

	private DefaultListModel<String> answerModel;
	// HAS-A: MatchingScreen has an answer model to manage answer list data.

	private JLabel feedbackLabel;
	// HAS-A: MatchingScreen has a feedback label to show match results.

	private RoundedButton matchButton;
	// HAS-A: MatchingScreen has a match button to check the selected pair.

	private RoundedButton newRoundButton;
	// HAS-A: MatchingScreen has a new round button to create another matching
	// round.

	private RoundedButton backButton;
	// HAS-A: MatchingScreen has a back button to return to the section menu.

	public MatchingScreen(String sectionName, int selectedChapter) {
		this.sectionName = sectionName;
		this.selectedChapter = selectedChapter;
		this.items = new ArrayList<>();
		this.roundItems = new ArrayList<>();
		this.attempts = 0;
		this.correctAnswers = 0;
		this.scoreSaved = false;

		setTitle("マイ練習 - " + sectionName + " Matching");
		setSize(1400, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		loadData();
		createRoundItems();

		Color backgroundColor = new Color(252, 251, 247);
		Color buttonColor = new Color(230, 238, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);

		JLabel titleLabel = new JLabel(sectionName + " Matching");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		titleLabel.setForeground(new Color(44, 74, 120));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructionLabel = new JLabel("Select one Japanese word and one English meaning.");
		instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		instructionLabel.setForeground(new Color(90, 90, 90));
		instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		promptModel = new DefaultListModel<>();
		answerModel = new DefaultListModel<>();

		promptList = new JList<>(promptModel);
		answerList = new JList<>(answerModel);

		promptList.setFont(new Font("Serif", Font.BOLD, 24));
		answerList.setFont(new Font("SansSerif", Font.PLAIN, 22));

		JScrollPane promptScrollPane = new JScrollPane(promptList);
		JScrollPane answerScrollPane = new JScrollPane(answerList);

		JPanel listsPanel = new JPanel(new GridLayout(1, 2, 30, 0));
		listsPanel.setBackground(backgroundColor);
		listsPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		JPanel leftPanel = createListPanel("Japanese", promptScrollPane);
		JPanel rightPanel = createListPanel("English", answerScrollPane);

		listsPanel.add(leftPanel);
		listsPanel.add(rightPanel);

		feedbackLabel = new JLabel("Choose a pair to check.");
		feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		feedbackLabel.setForeground(new Color(90, 90, 90));
		feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		matchButton = new RoundedButton("Match（合わせる）");
		newRoundButton = new RoundedButton("New Round（新しい問題）");
		backButton = new RoundedButton("Back（戻る）");

		RoundedButton[] buttons = { matchButton, newRoundButton, backButton };
		// HAS-MANY: MatchingScreen has many RoundedButton objects grouped
		// in an array so they can share formatting.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);

		for (RoundedButton button : buttons) {
			button.setFont(new Font("SansSerif", Font.BOLD, 18));
			button.setBackground(buttonColor);
			buttonPanel.add(button);
		}

		matchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkMatch();
			}
		});

		newRoundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRoundItems();
				updateLists();
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SectionMenuScreen(sectionName, selectedChapter);
				dispose();
			}
		});

		mainPanel.add(Box.createVerticalStrut(30));
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(instructionLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(listsPanel);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(feedbackLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(buttonPanel);

		add(mainPanel);
		updateLists();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel createListPanel(String title, JScrollPane scrollPane) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(252, 251, 247));

		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(Box.createVerticalStrut(10));
		panel.add(scrollPane);

		return panel;
	}

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
			} else if (sectionName.equals("gitaigo")) {
				items = DataLoader.loadStudyItems(folderPath + "gitaigo.txt", "gitaigo");
			} else if (sectionName.equals("Speech Styles")) {
				items = DataLoader.loadStudyItems(folderPath + "speech_styles.txt", "Speech Styles");
			} else if (sectionName.equals("Counters")) {
				items = DataLoader.loadStudyItems(folderPath + "counters.txt", "counters");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Could not load study data file.");
		}
	}

	private void createRoundItems() {
		roundItems.clear();

		ArrayList<StudyItem> shuffledItems = new ArrayList<>(items);
		Collections.shuffle(shuffledItems);

		int numberOfItems = Math.min(50, shuffledItems.size());

		for (int i = 0; i < numberOfItems; i++) {
			roundItems.add(shuffledItems.get(i));
		}

		attempts = 0;
		correctAnswers = 0;
		scoreSaved = false;

		if (matchButton != null) {
			matchButton.setEnabled(true);
		}
	}

	private void updateLists() {
		promptModel.clear();
		answerModel.clear();

		ArrayList<String> prompts = new ArrayList<>();
		ArrayList<String> answers = new ArrayList<>();

		for (StudyItem item : roundItems) {
			prompts.add(item.getPrompt());
			answers.add(item.getAnswer());
		}

		Collections.shuffle(prompts);
		Collections.shuffle(answers);

		for (String prompt : prompts) {
			promptModel.addElement(prompt);
		}

		for (String answer : answers) {
			answerModel.addElement(answer);
		}

		feedbackLabel.setText("Choose a pair to check.");
		feedbackLabel.setForeground(new Color(90, 90, 90));
	}

	private void checkMatch() {
		String selectedPrompt = promptList.getSelectedValue();
		String selectedAnswer = answerList.getSelectedValue();

		if (selectedPrompt == null || selectedAnswer == null) {
			feedbackLabel.setText("Please select one item from each list.");
			feedbackLabel.setForeground(new Color(170, 100, 40));
			return;
		}

		attempts++;

		for (StudyItem item : roundItems) {
			if (item.getPrompt().equals(selectedPrompt) && item.getAnswer().equals(selectedAnswer)) {
				correctAnswers++;

				feedbackLabel.setText("Correct! 正解です！");
				feedbackLabel.setForeground(new Color(40, 130, 70));

				promptModel.removeElement(selectedPrompt);
				answerModel.removeElement(selectedAnswer);

				promptList.clearSelection();
				answerList.clearSelection();

				if (promptModel.isEmpty()) {
					saveScore();

					feedbackLabel.setText("Round complete! Score saved.");
					feedbackLabel.setForeground(new Color(40, 130, 70));
					matchButton.setEnabled(false);
				}

				return;
			}
		}

		feedbackLabel.setText("Incorrect. Try another pair.");
		feedbackLabel.setForeground(new Color(170, 60, 60));

		promptList.clearSelection();
		answerList.clearSelection();
	}

	private void saveScore() {
		if (scoreSaved || roundItems.isEmpty()) {
			return;
		}

		double percent = (double) correctAnswers / roundItems.size();

		java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();

		String scoreText = "Matching Game - " + sectionName + " - Test " + ScoreManager.getTestNumber() + " | "
				+ correctAnswers + "/" + roundItems.size() + " | " + percentFormat.format(percent);

		ScoreManager.addScore(scoreText);
		scoreSaved = true;
	}
}