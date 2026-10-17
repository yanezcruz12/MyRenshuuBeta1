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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
 *         Chapter 8.2: Java's Inheritance Mechanism Chapter 8.3: Abstract
 *         Classes, Interfaces, and Polymorphism Chapter 13.2: Java GUIs: From
 *         AWT to Swing Chapter 13.3: The Swing Component Set Chapter 13.5: The
 *         Java Event Model Chapter 13.6: Case Study: Designing a Basic GUI
 *         Chapter 13.7: Containers and Layout Managers
 *
 *         Other References: Oracle. (n.d.). Class JFrame. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JFrame.html
 *
 *         Oracle. (n.d.). Class JOptionPane. Java Platform SE API
 *         Specification. Retrieved from
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/JOptionPane.html
 *
 *         Version/date: 1.0 — April 16, 2026 — Initial version created 1.1 —
 *         April 19, 2026 — All six section buttons added 1.2 — April 25, 2026 —
 *         Restored all buttons after accidental removal, citations and concept
 *         comments updated 1.3 — May 21, 2026 — Updated GUI screens to use
 *         button-specific event handling.
 *
 *         Responsibilities of class: Displays the main home screen for the マイ練習
 *         application. This class allows the user to choose a study section.
 */

// IS-A: HomeScreen is a JFrame because it extends JFrame.
public class HomeScreen extends JFrame {

	private int selectedChapter;

	// HAS-A: HomeScreen has a selected chapter number to track
	// which textbook chapter the user chose.
	private RoundedButton vocabularyButton;
	private RoundedButton grammarButton;
	private RoundedButton kanjiButton;
	private RoundedButton keigoButton;
	private RoundedButton sonkeigoButton;
	private RoundedButton gitaigoButton;
	private RoundedButton speechStylesButton;
	private RoundedButton countersButton;
	private RoundedButton scoresButton;
	private RoundedButton backButton;

	// HAS-MANY: HomeScreen has many RoundedButton objects for menu choices.

	/**
	 * Creates the home screen for the selected chapter and connects each menu
	 * button to its own ActionListener.
	 *
	 * @param selectedChapter the chapter number selected by the user
	 */
	public HomeScreen(int selectedChapter) {
		this.selectedChapter = selectedChapter;

		setTitle("マイ練習 - Chapter " + selectedChapter);
		setSize(1400, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Color backgroundColor = new Color(250, 250, 245);
		Color buttonColor = new Color(222, 236, 250);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(backgroundColor);

		JLabel titleLabel = new JLabel("Chapter " + selectedChapter + "（第" + selectedChapter + "課）");

		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		titleLabel.setForeground(new Color(45, 75, 120));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel subtitleLabel = new JLabel("Choose a Study Section（学習セクションを選びましょう）");

		subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		subtitleLabel.setForeground(new Color(95, 95, 95));
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainPanel.add(Box.createVerticalStrut(40));
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(subtitleLabel);
		mainPanel.add(Box.createVerticalStrut(30));

		JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 15, 15));
		buttonPanel.setBackground(backgroundColor);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 180, 20, 180));

		vocabularyButton = new RoundedButton("Vocabulary（語彙）");
		grammarButton = new RoundedButton("Grammar（文法）");
		kanjiButton = new RoundedButton("Kanji（漢字）");

		if (selectedChapter == 1) {
			// Added keigo and sonkeigo to chapter 1.
			keigoButton = new RoundedButton("Keigo（敬語）");
			sonkeigoButton = new RoundedButton("Sonkeigo（尊敬語）");
		} else if (selectedChapter == 2) {
			speechStylesButton = new RoundedButton("Speech Styles（話し方）");
		} else if (selectedChapter == 9) {
			gitaigoButton = new RoundedButton("Gitaigo（擬態語）");
		} else if (selectedChapter == 11) {
			countersButton = new RoundedButton("Counters（助数詞）");
		}

		scoresButton = new RoundedButton("View Scores（成績を見る）");
		backButton = new RoundedButton("Back（戻る）");

		if (selectedChapter == 1) {
			RoundedButton[] buttons = { vocabularyButton, grammarButton, kanjiButton, keigoButton, sonkeigoButton,
					scoresButton, backButton };

			for (RoundedButton button : buttons) {
				button.setFont(new Font("SansSerif", Font.BOLD, 18));
				button.setBackground(buttonColor);
				button.setForeground(Color.BLACK);
				buttonPanel.add(button);
			}
		} else if (selectedChapter == 2) {
			RoundedButton[] buttons = { vocabularyButton, grammarButton, kanjiButton, speechStylesButton, scoresButton,
					backButton };

			for (RoundedButton button : buttons) {
				button.setFont(new Font("SansSerif", Font.BOLD, 18));
				button.setBackground(buttonColor);
				button.setForeground(Color.BLACK);
				buttonPanel.add(button);
			}
		} else if (selectedChapter == 9) {
			RoundedButton[] buttons = { vocabularyButton, grammarButton, kanjiButton, gitaigoButton, scoresButton,
					backButton };

			for (RoundedButton button : buttons) {
				button.setFont(new Font("SansSerif", Font.BOLD, 18));
				button.setBackground(buttonColor);
				button.setForeground(Color.BLACK);
				buttonPanel.add(button);
			}
		} else if (selectedChapter == 11) {
			RoundedButton[] buttons = { vocabularyButton, grammarButton, kanjiButton, countersButton, scoresButton,
					backButton };

			for (RoundedButton button : buttons) {
				button.setFont(new Font("SansSerif", Font.BOLD, 18));
				button.setBackground(buttonColor);
				button.setForeground(Color.BLACK);
				buttonPanel.add(button);
			}
		} else {
			RoundedButton[] buttons = { vocabularyButton, grammarButton, kanjiButton, scoresButton, backButton };

			for (RoundedButton button : buttons) {
				button.setFont(new Font("SansSerif", Font.BOLD, 18));
				button.setBackground(buttonColor);
				button.setForeground(Color.BLACK);
				buttonPanel.add(button);
			}
		}

		vocabularyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SectionMenuScreen("Vocabulary", selectedChapter);
				dispose();
			}
		});

		grammarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SectionMenuScreen("Grammar", selectedChapter);
				dispose();
			}
		});

		kanjiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SectionMenuScreen("Kanji", selectedChapter);
				dispose();
			}
		});

		if (selectedChapter == 1) {
			keigoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SectionMenuScreen("Keigo", selectedChapter);
					dispose();
				}
			});

			sonkeigoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SectionMenuScreen("Sonkeigo", selectedChapter);
					dispose();
				}
			});
		} else if (selectedChapter == 2) {
			speechStylesButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SectionMenuScreen("Speech Styles", selectedChapter);
					dispose();
				}
			});
		} else if (selectedChapter == 9) {
			// Created new gitaigobutton - users can practice new category 9.10.
			gitaigoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SectionMenuScreen("gitaigo", selectedChapter);
					dispose();
				}
			});
		} else if (selectedChapter == 11) {
			countersButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new SectionMenuScreen("Counters", selectedChapter);
					dispose();
				}
			});
		}

		scoresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ScoreCardScreen();
				dispose();
			}
		});

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