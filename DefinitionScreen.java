import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.SwingConstants;

/**
 * Lead Author(s):
 * @author Cruz Yanez
 *
 * Other contributors:
 * None
 *
 * Version/date:
 * 1.0 — September 1, 2026 — Initial screen created
 * 1.1 — September 1, 2026 — Added 15-question definition
 *                            practice interface
 *
 * Responsibilities of class:
 * Loads Japanese vocabulary definitions, randomly selects up to
 * 15 questions, accepts typed answers, and displays the results.
 */
public class DefinitionScreen extends JFrame
{
    private String sectionName;

    private int selectedChapter;

    private ArrayList<StudyItem> items;

    private ArrayList<StudyItem> testItems;

    private ArrayList<JTextField> answerFields;

    private ArrayList<JLabel> resultLabels;

    private JLabel feedbackLabel;

    private RoundedButton submitButton;

    private RoundedButton newRoundButton;

    private RoundedButton backButton;

    /**
     * Creates the Japanese-definition practice screen.
     *
     * @param sectionName the selected study section
     * @param selectedChapter the selected textbook chapter
     */
    public DefinitionScreen(
            String sectionName,
            int selectedChapter)
    {
        this.sectionName = sectionName;
        this.selectedChapter = selectedChapter;

        items = new ArrayList<>();
        testItems = new ArrayList<>();
        answerFields = new ArrayList<>();
        resultLabels = new ArrayList<>();

        setTitle("マイ練習 - 日本語の意味");
        setSize(1400, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loadData();
        createTestItems();
        createLayout();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Loads the Japanese definitions for the selected chapter.
     */
    private void loadData()
    {
        String folderPath =
                "data/chapter" + selectedChapter + "/";

        String fileName =
                folderPath + "vocabulary_definitions.txt";

        try
        {
            items = DataLoader.loadJapaneseDefinitions(
                    fileName,
                    sectionName);
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not find:\n" + fileName,
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Randomly selects up to 15 definitions for one study round.
     */
    private void createTestItems()
    {
        ArrayList<StudyItem> shuffledItems =
                new ArrayList<>(items);

        Collections.shuffle(shuffledItems);

        int numberOfQuestions =
                Math.min(15, shuffledItems.size());

        for (int index = 0;
                index < numberOfQuestions;
                index++)
        {
            testItems.add(shuffledItems.get(index));
        }
    }

    /**
     * Creates the definition practice interface.
     */
    private void createLayout()
    {
        Color backgroundColor =
                new Color(245, 250, 248);

        Color rowColor =
                new Color(255, 255, 255);

        Color buttonColor =
                new Color(215, 235, 225);

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(backgroundColor);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40));

        /*
         * Create the heading.
         */
        JPanel headingPanel = new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS));

        headingPanel.setBackground(backgroundColor);

        JLabel titleLabel = new JLabel(
                "日本語の意味から単語を考えましょう");

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30));

        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel instructionLabel = new JLabel(
                "説明を読んで、正しい単語を入力してください。");

        instructionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        18));

        instructionLabel.setAlignmentX(CENTER_ALIGNMENT);

        feedbackLabel = new JLabel(
                testItems.size() + " Questions",
                SwingConstants.CENTER);

        feedbackLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18));

        feedbackLabel.setForeground(
                new Color(40, 100, 90));

        feedbackLabel.setAlignmentX(CENTER_ALIGNMENT);

        headingPanel.add(titleLabel);
        headingPanel.add(Box.createVerticalStrut(8));
        headingPanel.add(instructionLabel);
        headingPanel.add(Box.createVerticalStrut(8));
        headingPanel.add(feedbackLabel);

        /*
         * Create one row for each definition.
         */
        JPanel questionsPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                10,
                                10));

        questionsPanel.setBackground(backgroundColor);

        for (int index = 0;
                index < testItems.size();
                index++)
        {
            StudyItem item = testItems.get(index);

            JPanel questionRow =
                    new JPanel(
                            new BorderLayout(
                                    20,
                                    5));

            questionRow.setBackground(rowColor);

            questionRow.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(
                                            210,
                                            225,
                                            218)),
                            BorderFactory.createEmptyBorder(
                                    12,
                                    15,
                                    12,
                                    15)));

            JLabel definitionLabel = new JLabel(
                    (index + 1)
                            + ". "
                            + item.getPrompt());

            definitionLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.PLAIN,
                            18));

            JTextField answerField =
                    new JTextField();

            answerField.setFont(
                    new Font(
                            "SansSerif",
                            Font.PLAIN,
                            18));

            answerField.setPreferredSize(
                    new Dimension(
                            220,
                            38));

            JLabel resultLabel = new JLabel(" ");

            resultLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            16));

            resultLabel.setPreferredSize(
                    new Dimension(
                            180,
                            38));

            JPanel answerPanel = new JPanel();

            answerPanel.setLayout(
                    new BoxLayout(
                            answerPanel,
                            BoxLayout.X_AXIS));

            answerPanel.setBackground(rowColor);

            answerPanel.add(answerField);
            answerPanel.add(Box.createHorizontalStrut(12));
            answerPanel.add(resultLabel);

            questionRow.add(
                    definitionLabel,
                    BorderLayout.CENTER);

            questionRow.add(
                    answerPanel,
                    BorderLayout.EAST);

            questionsPanel.add(questionRow);

            answerFields.add(answerField);
            resultLabels.add(resultLabel);
        }

        JScrollPane scrollPane =
                new JScrollPane(questionsPanel);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        /*
         * Create the buttons at the bottom.
         */
        submitButton =
                new RoundedButton(
                        "Check Answers（答えを確認）");

        newRoundButton =
                new RoundedButton(
                        "New Round（もう一度）");

        backButton =
                new RoundedButton(
                        "Back（戻る）");

        RoundedButton[] buttons =
        {
            submitButton,
            newRoundButton,
            backButton
        };

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0));

        buttonPanel.setBackground(backgroundColor);

        for (RoundedButton button : buttons)
        {
            button.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            17));

            button.setBackground(buttonColor);
            button.setForeground(Color.BLACK);

            buttonPanel.add(button);
        }

        /*
         * Check all 15 answers.
         */
        submitButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(
                            ActionEvent e)
                    {
                        checkAnswers();
                    }
                });

        /*
         * Start a new random round.
         */
        newRoundButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(
                            ActionEvent e)
                    {
                        new DefinitionScreen(
                                sectionName,
                                selectedChapter);

                        dispose();
                    }
                });

        /*
         * Return to the practice menu.
         */
        backButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(
                            ActionEvent e)
                    {
                        new SectionMenuScreen(
                                sectionName,
                                selectedChapter);

                        dispose();
                    }
                });

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Checks every typed answer and displays feedback.
     */
    private void checkAnswers()
    {
        int correctAnswers = 0;

        for (int index = 0;
                index < testItems.size();
                index++)
        {
            StudyItem item =
                    testItems.get(index);

            JTextField answerField =
                    answerFields.get(index);

            JLabel resultLabel =
                    resultLabels.get(index);

            String userAnswer =
                    answerField.getText().trim();

            String correctAnswer =
                    item.getAnswer().trim();

            if (userAnswer.equals(correctAnswer))
            {
                correctAnswers++;

                resultLabel.setText("✓ 正解");

                resultLabel.setForeground(
                        new Color(30, 130, 70));

                answerField.setBorder(
                        BorderFactory.createLineBorder(
                                new Color(30, 150, 80),
                                2));
            }
            else
            {
                resultLabel.setText(
                        "✗ 正解：" + correctAnswer);

                resultLabel.setForeground(
                        new Color(190, 50, 50));

                answerField.setBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 70, 70),
                                2));
            }

            answerField.setEditable(false);
        }

        feedbackLabel.setText(
                "結果：" + correctAnswers
                        + " / "
                        + testItems.size());

        submitButton.setEnabled(false);
    }
}