import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
/**
* Lead Author(s):
* @author Cruz Yanez

*
* Other Contributors:
* None
*
* References:
* Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving
* Retrieved from: https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
* Chapter 8.2: Java's Inheritance Mechanism
*   Covers: extends JFrame and implements ActionListener
* Chapter 8.3: Abstract Classes, Interfaces, and Polymorphism
*   Covers: implementing the ActionListener interface contract
* Chapter 13.2: Java GUIs: From AWT to Swing
*   Covers: using Swing classes such as JFrame, JLabel, JPanel, JScrollPane, and JTextArea
* Chapter 13.5: The Java Event Model
*   Covers: addActionListener(this) and actionPerformed(ActionEvent e)
* Chapter 13.7: Containers and Layout Managers
*   Covers: BorderLayout and placing components in NORTH, CENTER, and SOUTH regions
* Chapter 10.7: From the Java Library: JOptionPane
*   Covers: using JOptionPane to display message dialogs
*
* Version:
* 1.0 — May 4, 2026 — Created ScoreCardScreen to display saved scores from ScoreManager.
* 1.1 — May 5, 2026 — Added Reset Scores and Home buttons with ActionListener logic.
* 1.2 — May 19, 2026 — Updated GUI screens to use button-specific
*                         ActionListener objects instead of e.getSource()
*                         event handling.
*/
/**
 * Responsibilities of class:
 * ScoreCardScreen displays saved test scores from ScoreManager.
 * It also allows the user to reset all saved scores or return to the home screen.
 */

// IS-A: ScoreCardScreen is a JFrame because it extends JFrame.
public class ScoreCardScreen extends JFrame
{
    private RoundedButton homeButton;
    // HAS-A: ScoreCardScreen has a home button to return to the home screen.

    private RoundedButton resetScoresButton;
    // HAS-A: ScoreCardScreen has a reset button to clear saved scores.

    /**
     * Creates the scorecard screen.
     * This screen displays saved scores from ScoreManager.
     */
    public ScoreCardScreen()
    {
        setTitle("マイ練習 - Scores");
        setSize(950, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(252, 251, 247);

        JLabel titleLabel = new JLabel("View Scores / 成績", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        titleLabel.setForeground(new Color(44, 74, 120));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 10, 15, 10));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        scoreArea.setBackground(Color.WHITE);
        scoreArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        scoreArea.setText(ScoreManager.getScores());

        JScrollPane scrollPane = new JScrollPane(scoreArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));

        resetScoresButton = new RoundedButton("Reset Scores");
        resetScoresButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        resetScoresButton.setPreferredSize(new Dimension(170, 45));

        resetScoresButton.addActionListener(new ActionListener()
        {
            /**
             * Clears all saved scores and reloads the scorecard screen.
             * @param e the ActionEvent created when Reset Scores is clicked
             */
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ScoreManager.clearScores();

                JOptionPane.showMessageDialog(
                        ScoreCardScreen.this,
                        "All scores have been reset.",
                        "Scores Cleared",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new ScoreCardScreen();
            }
        });

        homeButton = new RoundedButton("Home");
        homeButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        homeButton.setPreferredSize(new Dimension(140, 45));

        homeButton.addActionListener(new ActionListener()
        {
            /**
             * Returns the user to the home screen.
             *
             * @param e the ActionEvent created when Home is clicked
             */
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new ChapterSelectScreen();
                dispose();
            }
        });

        bottomPanel.add(resetScoresButton);
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(backgroundColor);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}