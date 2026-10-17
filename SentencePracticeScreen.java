import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 * Lead Author(s):
 * @author Cruz Yanez
 *
 * Version/date:
 * 1.0 — October 8, 2026 — Adapted from DefinitionScreen.
 *
 * Responsibilities of class:
 * Loads English sentence prompts, randomly selects up to 20 questions,
 * accepts complete Japanese sentences, and checks the round on Submit.
 *
 * File format (UTF-8): English prompt|Japanese answer
 * Optional alternatives: English prompt|Answer one|Answer two
 * Uses the project's DataLoader, StudyItem, RoundedButton, and SectionMenuScreen.
 * Requires DataLoader.loadSentencePractice(String, String).
 */
public class SentencePracticeScreen extends JFrame
{
    private final String sectionName;
    private final int selectedChapter;
    private final ArrayList<StudyItem> items = new ArrayList<>();
    private final ArrayList<StudyItem> testItems = new ArrayList<>();
    private final ArrayList<JTextField> answerFields = new ArrayList<>();
    private final ArrayList<JTextArea> resultLabels = new ArrayList<>();
    private JLabel feedbackLabel;
    private RoundedButton submitButton;
    private RoundedButton newRoundButton;
    private boolean submitted;

    public SentencePracticeScreen(String sectionName, int selectedChapter)
    {
        this.sectionName = sectionName;
        this.selectedChapter = selectedChapter;
        setTitle("マイ練習 - Sentence Practice");
        setSize(1400, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadData();
        createTestItems();
        createLayout();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Selects the sentence file for the current section and chapter. */
    private void loadData()
    {
        String folderPath = "data/chapter" + selectedChapter + "/";
        String fileName;
        try
        {
            if (sectionName.equals("Vocabulary"))
                fileName = "vocabulary_sentence.txt";
            else if (sectionName.equals("Kanji"))
                fileName = "kanji_sentence.txt";
            else if (sectionName.equals("Grammar"))
                fileName = "grammar_sentence.txt";
            else if (sectionName.equals("Conjugations"))
                fileName = "conjugation_sentence.txt";
            else if (sectionName.equals("Keigo"))
                fileName = "keigo_sentence.txt";
            else if (sectionName.equals("Sonkeigo"))
                fileName = "sonkeigo_sentence.txt";
            else if (sectionName.equals("gitaigo"))
                fileName = "gitaigo_sentence.txt";
            else if (sectionName.equals("Speech Styles"))
                fileName = "speech_styles_sentence.txt";
            else if (sectionName.equals("Counters"))
                fileName = "counters_sentence.txt";
            else
                throw new IllegalArgumentException(
                        "Unknown study section: " + sectionName);

            items.clear();
            items.addAll(DataLoader.loadSentencePractice(
                    folderPath + fileName, sectionName));
        }
        catch (FileNotFoundException error)
        {
            JOptionPane.showMessageDialog(this,
                    "Could not load sentence practice data for "
                            + sectionName + " in Chapter " + selectedChapter
                            + ".\n" + error.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException error)
        {
            JOptionPane.showMessageDialog(this, error.getMessage(),
                    "Sentence Data Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createTestItems()
    {
        testItems.clear();
        ArrayList<StudyItem> shuffled = new ArrayList<>(items);
        Collections.shuffle(shuffled);
        testItems.addAll(shuffled.subList(0, Math.min(20, shuffled.size())));
    }

    private void createLayout()
    {
        Color background = new Color(245, 250, 248);
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(background);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JPanel headingPanel = new JPanel(new GridLayout(0, 1, 0, 8));
        headingPanel.setBackground(background);
        JLabel title = new JLabel("Sentence Practice", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        JLabel instructions = new JLabel(
                "Read each English sentence and type the complete Japanese sentence."
                + " Finish the round, then click Submit.", SwingConstants.CENTER);
        instructions.setFont(new Font("SansSerif", Font.PLAIN, 17));
        feedbackLabel = new JLabel(testItems.isEmpty() ? "No questions loaded"
                : testItems.size() + " questions", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        headingPanel.add(title);
        headingPanel.add(instructions);
        headingPanel.add(feedbackLabel);

        JPanel questionsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        questionsPanel.setBackground(background);
        for (int index = 0; index < testItems.size(); index++)
        {
            StudyItem item = testItems.get(index);
            JPanel row = new JPanel(new BorderLayout(0, 8));
            row.setBackground(Color.WHITE);
            row.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(210, 225, 218)),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)));

            JTextArea prompt = new JTextArea((index + 1) + ". " + item.getPrompt());
            prompt.setEditable(false);
            prompt.setLineWrap(true);
            prompt.setWrapStyleWord(true);
            prompt.setFont(new Font("SansSerif", Font.PLAIN, 18));

            JTextField answer = new JTextField(50);
            answer.setFont(new Font("SansSerif", Font.PLAIN, 22));
            answer.setMargin(new java.awt.Insets(8, 8, 8, 8));
            answer.getAccessibleContext().setAccessibleName(
                    "Japanese answer for question " + (index + 1));
            // Enter does not submit: all answers are checked with the Submit button.

            JTextArea result = new JTextArea(" ", 2, 50);
            result.setEditable(false);
            result.setLineWrap(true);
            result.setWrapStyleWord(true);
            result.setFont(new Font("SansSerif", Font.PLAIN, 17));

            row.add(prompt, BorderLayout.NORTH);
            row.add(answer, BorderLayout.CENTER);
            row.add(result, BorderLayout.SOUTH);
            questionsPanel.add(row);
            answerFields.add(answer);
            resultLabels.add(result);
        }

        JScrollPane scrollPane = new JScrollPane(questionsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        submitButton = new RoundedButton("Submit（提出）");
        newRoundButton = new RoundedButton("New Round（もう一度）");
        RoundedButton backButton = new RoundedButton("Back（戻る）");
        JPanel buttons = new JPanel(new GridLayout(1, 3, 20, 0));
        buttons.setBackground(background);
        for (RoundedButton button : new RoundedButton[] {
                submitButton, newRoundButton, backButton })
        {
            button.setFont(new Font("SansSerif", Font.BOLD, 17));
            button.setBackground(new Color(215, 235, 225));
            button.setForeground(Color.BLACK);
            buttons.add(button);
        }
        submitButton.setEnabled(!testItems.isEmpty());
        newRoundButton.setEnabled(!testItems.isEmpty());
        submitButton.addActionListener(e -> checkAnswers());
        newRoundButton.addActionListener(e -> {
            new SentencePracticeScreen(sectionName, selectedChapter);
            dispose();
        });
        backButton.addActionListener(e -> {
            new SectionMenuScreen(sectionName, selectedChapter);
            dispose();
        });
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttons, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }

    /** Requires every sentence to be attempted before showing any answers. */
    private void checkAnswers()
    {
        if (submitted || testItems.isEmpty()) return;
        for (int index = 0; index < answerFields.size(); index++)
        {
            if (normalize(answerFields.get(index).getText()).isEmpty())
            {
                feedbackLabel.setText("Please complete question " + (index + 1)
                        + " before submitting.");
                JTextField missing = answerFields.get(index);
                missing.scrollRectToVisible(new java.awt.Rectangle(
                        0, 0, missing.getWidth(), missing.getHeight()));
                missing.requestFocusInWindow();
                return;
            }
        }

        int score = 0;
        for (int index = 0; index < testItems.size(); index++)
        {
            StudyItem item = testItems.get(index);
            JTextField field = answerFields.get(index);
            String typed = normalize(field.getText());
            boolean correct = false;
            for (String accepted : item.getAnswer().split("\\|", -1))
                if (typed.equals(normalize(accepted))) correct = true;

            Color color = correct ? new Color(30, 130, 70)
                    : new Color(190, 50, 50);
            if (correct) score++;
            resultLabels.get(index).setText(correct ? "✓ Correct"
                    : "Review — accepted answer: " + item.getAnswer().split("\\|", -1)[0].trim());
            resultLabels.get(index).setForeground(color);
            field.setBorder(BorderFactory.createLineBorder(color, 2));
            field.setEditable(false);
        }
        feedbackLabel.setText("Result: " + score + " / " + testItems.size()
                + " matched an accepted answer");
        submitted = true;
        submitButton.setEnabled(false);
    }

    /** Ignores whitespace and optional final 。; preserves internal punctuation. */
    private static String normalize(String text)
    {
        return Normalizer.normalize(text, Normalizer.Form.NFC)
                .replaceAll("[\\s\\p{Z}]+", "")
                .replaceAll("。$", "");
    }
}