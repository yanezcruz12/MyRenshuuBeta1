import java.util.ArrayList;

/**
* Lead Author(s):
* @author Cruz Yanez

*
* Other Contributors:
* None
*
* References:
* Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving
* Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
* Chapter 3.4: Retrieving Information from an Object
*   Covers: methods that return information, such as getScores() and getTestNumber()
* Chapter 5.3: Numeric Data and Operators
*   Covers: using scores.size() + 1 to calculate the next test number
* Chapter 6: Control Structures
*   Covers: if statement logic used to check whether scores is empty
* Chapter 7.2: String Basics
*   Covers: building one display String using String concatenation
* Chapter 9.2: One-Dimensional Arrays
*   Covers: storing multiple related values in one structure
* Chapter 16.6: From the Java Library: The Java Collections Framework
*   Covers: ArrayList as a collection used to store multiple score Strings
*
* Version: 
* 1.0 — May 2, 2026 — Created ScoreManager to store, add, display, clear,
*                     and number score results during the current app session.
*/

/**
 * Responsibilities of class:
 * ScoreManager stores score results as text while the app is running.
 */
public class ScoreManager
{
	private static ArrayList<String> scores = new ArrayList<>();
	// HAS-MANY: ScoreManager has many saved score strings.

	/**
	 * Adds one score result to the saved score list.
	 *
	 * @param scoreText the score result to save
	 */
	public static void addScore(String scoreText)
	{
		scores.add(scoreText);
	}

	/**
	 * Gets all saved scores as one display string.
	 *
	 * @return all saved scores, or a message if none exist
	 */
	public static String getScores()
	{
		if (scores.isEmpty())
		{
			return "No scores saved yet.";
		}

		String result = "";

		for (String score : scores)
		{
			result += score + "\n";
		}

		return result;
	}

	/**
	 * Clears all saved scores.
	 */
	public static void clearScores()
	{
		scores.clear();
	}

	/**
	 * Gets the next test number.
	 *
	 * @return the next test number
	 */
	public static int getTestNumber()
	{
		return scores.size() + 1;
	}
}
