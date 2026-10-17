import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lead Author(s):
 * 
 * @author Cruz Yanez
 *
 *         Other contributors: None
 *
 *         References: Morelli, R., & Walde, R. (2016). Java, Java, Java:
 *         Object-Oriented Problem Solving. Retrieved from:
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 *         Chapter 2.6: From the Java Library: java.util.Scanner Chapter 11.2:
 *         Streams and Files Chapter 11.3: Case Study: Reading and Writing Text
 *         Files Chapter 11.4: The File Class
 *
 *         Other References: Oracle. (n.d.). Class File. Java Platform SE API
 *         Specification. Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/File.html
 *
 *         Oracle. (n.d.). Class Scanner. Java Platform SE API Specification.
 *         Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
 *
 *         Oracle. (n.d.). Class FileNotFoundException. Java Platform SE API
 *         Specification. Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileNotFoundException.html
 *
 *         Oracle. (n.d.). Class ArrayList. Java Platform SE API Specification.
 *         Retrieved from:
 *         https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
 *
 *         Oracle. (n.d.). Equality, Relational, and Conditional Operators. The
 *         Java Tutorials. Retrieved from:
 *         https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op2.html
 *
 *         Covers: The ?: ternary operator used in the loading methods to safely
 *         access array parts without causing an ArrayIndexOutOfBoundsException.
 *
 *         Akira Miura. An Integrated Approach to Intermediate Japanese (Revised
 *         Edition). Retrieved from: https://online.fliphtml5.com/cialx/jzho/
 *
 *         Version/date: 1.0 — April 9, 2026 — Initial version created 1.1 —
 *         April 23, 2026 — loadFillBlank() method added 1.2 — April 25, 2026 —
 *         Switched Scanner to try-with-resources to ensure files are always
 *         closed 1.3 — April 27, 2026 — Added missing Oracle citation for the
 *         ternary operator 1.4 — September 1, 2026 — Added
 *         loadJapaneseDefinitions() for Japanese-definition practice
 *
 *         Responsibilities of class: DataLoader reads study data from external
 *         text files and converts each line into a StudyItem object for the
 *         マイ練習 application.
 */
public class DataLoader {
	/**
	 * Loads regular study items from a comma-separated text file. Each line should
	 * follow this format:
	 *
	 * prompt,answer,extra,exampleTranslation
	 *
	 * @param fileName    the name of the study data file to read
	 * @param sectionName the study section connected to the file
	 * @return an ArrayList of StudyItem objects created from the file
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static ArrayList<StudyItem> loadStudyItems(String fileName, String sectionName)
			throws FileNotFoundException {
		ArrayList<StudyItem> items = new ArrayList<>();

		// HAS-MANY: DataLoader creates many StudyItem objects
		// and stores them in an ArrayList.
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (!line.isEmpty()) {
					String[] parts = line.split(",");

					// These checks prevent errors if a line is
					// missing one of its fields.
					String prompt = parts.length > 0 ? parts[0].trim() : "";

					String answer = parts.length > 1 ? parts[1].trim() : "";

					String extra = parts.length > 2 ? parts[2].trim() : "";

					String exampleTranslation = parts.length > 3 ? parts[3].trim() : "";

					items.add(new StudyItem(sectionName, prompt, answer, extra, exampleTranslation));
				}
			}
		}

		return items;
	}

	/**
	 * Loads fill-in-the-blank items from a pipe-separated text file. Each line
	 * should follow this format:
	 *
	 * sentence|translation|answer
	 *
	 * The pipe symbol is used because Japanese or English sentences may contain
	 * commas.
	 *
	 * @param fileName    the name of the fill-in-the-blank file
	 * @param sectionName the study section connected to the file
	 * @return an ArrayList of StudyItem objects created from the file
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static ArrayList<StudyItem> loadFillBlank(String fileName, String sectionName) throws FileNotFoundException {
		ArrayList<StudyItem> items = new ArrayList<>();

		// HAS-MANY: DataLoader creates many StudyItem objects
		// and stores them in an ArrayList.
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (!line.isEmpty()) {
					// The double backslash is needed because
					// split() uses regular expressions.
					String[] parts = line.split("\\|");

					String prompt = parts.length > 0 ? parts[0].trim() : "";

					String extra = parts.length > 1 ? parts[1].trim() : "";

					String answer = parts.length > 2 ? parts[2].trim() : "";

					items.add(new StudyItem(sectionName, prompt, answer, extra, ""));
				}
			}
		}

		return items;
	}

	/**
	 * Loads Japanese-definition questions from a pipe-separated text file.
	 *
	 * Each line should follow this format:
	 *
	 * Japanese definition|Japanese answer
	 *
	 * Example:
	 *
	 * 学校で学生に勉強を教える人|先生
	 *
	 * @param fileName    the name of the definition file to read
	 * @param sectionName the study section connected to the file
	 * @return an ArrayList of StudyItem objects created from the file
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static ArrayList<StudyItem> loadJapaneseDefinitions(String fileName, String sectionName)
			throws FileNotFoundException {
		ArrayList<StudyItem> items = new ArrayList<>();

		// HAS-MANY: DataLoader creates many definition questions
		// and stores them in an ArrayList.
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (!line.isEmpty()) {
					// Split the line at the pipe symbol.
					String[] parts = line.split("\\|");

					// The Japanese definition becomes the prompt.
					String prompt = parts.length > 0 ? parts[0].trim() : "";

					// The Japanese vocabulary word becomes the answer.
					String answer = parts.length > 1 ? parts[1].trim() : "";

					items.add(new StudyItem(sectionName, prompt, answer, "", ""));
				}
			}
		}

		return items;
	}

	/**
	 * Loads English sentences and accepted Japanese answers.
	 *
	 * Format: English sentence|Japanese answer Optional: English sentence|Answer
	 * one|Answer two
	 *
	 * @param fileName    the sentence practice file
	 * @param sectionName the selected study section
	 * @return sentence practice StudyItems
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static ArrayList<StudyItem> loadSentencePractice(String fileName, String sectionName)
			throws FileNotFoundException {
		ArrayList<StudyItem> items = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
			int lineNumber = 0;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNumber++;

				if (lineNumber == 1 && line.startsWith("\uFEFF")) {
					line = line.substring(1);
				}

				line = line.trim();

				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				}

				String[] parts = line.split("\\|", -1);

				if (parts.length < 2 || parts[0].trim().isEmpty()) {
					throw new IllegalArgumentException(
							"Invalid sentence on line " + lineNumber + ". Use: English sentence|Japanese answer");
				}

				String prompt = parts[0].trim();
				StringBuilder answers = new StringBuilder();

				for (int index = 1; index < parts.length; index++) {
					String answer = parts[index].trim();

					if (answer.isEmpty()) {
						throw new IllegalArgumentException("Missing answer on line " + lineNumber);
					}

					if (index > 1) {
						answers.append("|");
					}

					answers.append(answer);
				}

				items.add(new StudyItem(sectionName, prompt, answers.toString(), "", ""));
			}
		}

		return items;
	}
}