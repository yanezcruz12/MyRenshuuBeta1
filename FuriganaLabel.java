import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Displays Japanese text with furigana above kanji by drawing the text manually.
 *
 * This class works similarly to RoundedButton because it overrides
 * paintComponent() and controls how the component is drawn.
 */
public class FuriganaLabel extends JPanel
{
    private String text;

    private Font kanjiFont;

    private Font furiganaFont;

    /**
     * Creates a FuriganaLabel from Japanese text.
     *
     * Example format:
     * 先生(せんせい)が新(あたら)しい学生(がくせい)を紹介(しょうかい)しました。
     *
     * @param text the Japanese text with furigana in parentheses
     */
    public FuriganaLabel(String text)
    {
        this.text = text;

        // Starting font sizes.
        // The font will automatically become smaller if the sentence
        // is too long to fit inside the flashcard.
        kanjiFont = new Font("Serif", Font.PLAIN, 22);

        furiganaFont = new Font("Serif", Font.PLAIN, 22);

        setOpaque(false);

        setPreferredSize(new Dimension(800, 90));
    }

    /**
     * Draws the Japanese sentence and places furigana above kanji.
     *
     * The font size automatically becomes smaller when necessary
     * so longer sentences can fit inside the flashcard.
     *
     * @param g the Graphics object used by Swing to draw the component
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        /*
         * AUTOMATIC FONT SIZING
         *
         * Start at size 32. If the sentence is too wide,
         * decrease the font size until it fits.
         */
        int kanjiSize = 22;

        // Do not allow the Japanese text to become smaller than this.
        int minimumSize = 16;

        // Leave a little space on the left and right sides.
        int maxWidth = getWidth() - 40;

        /*
         * Remove the furigana from the text temporarily.
         *
         * Example:
         *
         * 先生(せんせい)に頼(たの)みました。
         *
         * becomes:
         *
         * 先生に頼みました。
         *
         * This lets us measure the width of the Japanese sentence.
         */
        String plainText =
                text.replaceAll("\\([^)]*\\)", "");

        /*
         * Keep reducing the font size until the sentence
         * fits inside the available width.
         */
        while (kanjiSize > minimumSize)
        {
            Font testFont =
                    new Font("Serif", Font.PLAIN, kanjiSize);

            FontMetrics testMetrics =
                    g2.getFontMetrics(testFont);

            if (testMetrics.stringWidth(plainText) <= maxWidth)
            {
                break;
            }

            kanjiSize--;
        }

        /*
         * Use the final calculated size for the Japanese text.
         */
        kanjiFont =
                new Font("Serif", Font.PLAIN, kanjiSize);

        /*
         * Make the furigana smaller than the main Japanese text.
         *
         * Math.max() prevents the furigana from becoming
         * smaller than size 12.
         */
        furiganaFont =
                new Font(
                        "Serif",
                        Font.PLAIN,
                        Math.max(12, kanjiSize - 10)
                );

        FontMetrics kanjiMetrics =
                g2.getFontMetrics(kanjiFont);

        FontMetrics furiganaMetrics =
                g2.getFontMetrics(furiganaFont);

        /*
         * Calculate the width after the font size has
         * been automatically adjusted.
         */
        int textWidth = getTextWidth(g2);

        /*
         * Center the entire Japanese sentence.
         */
        int x = (getWidth() - textWidth) / 2;

        int kanjiY = 60;

        int furiganaY = 30;

        int i = 0;

        /*
         * Go through the text and look for furigana
         * written inside parentheses.
         */
        while (i < text.length())
        {
            int openParen = text.indexOf("(", i);

            /*
             * If there is no more furigana,
             * draw the rest of the sentence normally.
             */
            if (openParen == -1)
            {
                String remainingText = text.substring(i);

                g2.setFont(kanjiFont);

                g2.drawString(remainingText, x, kanjiY);

                break;
            }

            int closeParen = text.indexOf(")", openParen);

            /*
             * If an opening parenthesis does not have
             * a closing parenthesis, draw the remaining
             * text normally.
             */
            if (closeParen == -1)
            {
                String remainingText = text.substring(i);

                g2.setFont(kanjiFont);

                g2.drawString(remainingText, x, kanjiY);

                break;
            }

            /*
             * Move backward from the parenthesis to find
             * the kanji that the furigana belongs to.
             */
            int kanjiStart = openParen - 1;

            while (kanjiStart >= 0
                    && isKanji(text.charAt(kanjiStart)))
            {
                kanjiStart--;
            }

            kanjiStart++;

            /*
             * Draw any normal text that appears before
             * the kanji/furigana pair.
             */
            String normalText =
                    text.substring(i, kanjiStart);

            g2.setFont(kanjiFont);

            g2.drawString(normalText, x, kanjiY);

            x += kanjiMetrics.stringWidth(normalText);

            /*
             * Get the kanji and its furigana.
             */
            String kanji =
                    text.substring(kanjiStart, openParen);

            String furigana =
                    text.substring(openParen + 1, closeParen);

            int kanjiWidth =
                    kanjiMetrics.stringWidth(kanji);

            int furiganaWidth =
                    furiganaMetrics.stringWidth(furigana);

            /*
             * Center the furigana above the kanji.
             */
            int furiganaX =
                    x + (kanjiWidth - furiganaWidth) / 2;

            /*
             * Draw the furigana.
             */
            g2.setFont(furiganaFont);

            g2.drawString(
                    furigana,
                    furiganaX,
                    furiganaY
            );

            /*
             * Draw the kanji underneath the furigana.
             */
            g2.setFont(kanjiFont);

            g2.drawString(
                    kanji,
                    x,
                    kanjiY
            );

            /*
             * Move the drawing position to the right
             * so the next part of the sentence can be drawn.
             */
            x += kanjiWidth;

            /*
             * Continue after the closing parenthesis.
             */
            i = closeParen + 1;
        }

        g2.dispose();
    }

    /**
     * Calculates the width of the displayed Japanese text without the
     * furigana parentheses.
     *
     * This helps center the full sentence inside the FuriganaLabel.
     *
     * @param g2 the Graphics2D object used to measure text width
     * @return the width of the Japanese text without furigana readings
     */
    private int getTextWidth(Graphics2D g2)
    {
        FontMetrics kanjiMetrics =
                g2.getFontMetrics(kanjiFont);
        
     // Removes the furigana inside parentheses so only the
     // Japanese sentence remains when calculating its width.
     // Example: 先生(せんせい) → 先生
        String plainText =
                text.replaceAll("\\([^)]*\\)", "");

        return kanjiMetrics.stringWidth(plainText);
    }

    /**
     * Checks whether a character is kanji.
     *
     * @param c the character being checked
     * @return true if the character is kanji
     */
    private boolean isKanji(char c)
    {
        return c >= '\u4E00' && c <= '\u9FAF';
    }
}