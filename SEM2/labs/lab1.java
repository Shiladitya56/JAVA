package codes;
import java.util.Scanner;

public class Performance {
    private int[] marks;

    public Performance() {
        marks = new int[10];
    }

    public void readMarks() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the marks of 10 students (between 0 and 100):");

        for (int i = 0; i < 10; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            int mark = -1;

            // Keep prompting the user until a valid mark is entered
            while (mark < 0 || mark > 100) {
                System.out.print("Enter a mark between 0 and 100: ");
                mark = s.nextInt();

                if (mark < 0 || mark > 100) {
                    System.out.println("Invalid input. Please enter a mark between 0 and 100.");
                }
            }
            s.close();
            marks[i] = mark;
        }
    }

    public int highestMark() {
        int highest = marks[0];
        for (int i = 1; i < 10; i++) {
            if (marks[i] > highest) {
                highest = marks[i];
            }
        }
        return highest;
    }

    public int leastMark() {
        int least = marks[0];
        for (int i = 1; i < 10; i++) {
            if (marks[i] < least) {
                least = marks[i];
            }
        }
        return least;
    }

    public int getMode() {
        int mode = marks[0];
        int maxFreq = 1;

        for (int i = 0; i < 10; i++) {
            int curMark = marks[i];
            int curFreq = 1;

            for (int j = i + 1; j < 10; j++) {
                if (marks[j] == curMark) {
                    curFreq++;
                }
            }

            if (curFreq > maxFreq || (curFreq == maxFreq && curMark > mode)) {
                mode = curMark;
                maxFreq = curFreq;
            }
        }

        return mode;
    }

    public int getFreqAtMode() {
        int mode = getMode();
        int freq = 0;

        for (int i = 0; i < 10; i++) {
            if (marks[i] == mode) {
                freq++;
            }
        }

        return freq;
    }

    public void display() {
        System.out.println("Highest Mark: " + highestMark());
        System.out.println("Least Mark: " + leastMark());
        System.out.println("Mode: " + getMode());
        System.out.println("Mode Frequency: " + getFreqAtMode());
    }

    public static void main(String[] args) {
        Performance p = new Performance();
        p.readMarks();
        p.display();
    }
}

package codes;
import java.util.Scanner;

public class AlphabetWarGame {
    private static final String DEFAULT_LEFT_STRENGTH = "wpbs";
    private static final String DEFAULT_RIGHT_STRENGTH = "mqdz";

    private String leftStrength;
    private String rightStrength;

    public AlphabetWarGame() {
        this.leftStrength = DEFAULT_LEFT_STRENGTH;
        this.rightStrength = DEFAULT_RIGHT_STRENGTH;
    }

    public AlphabetWarGame(String leftStrength, String rightStrength) {
        this.leftStrength = leftStrength;
        this.rightStrength = rightStrength;
    }

    public String battle(String word) {
        return determineWinner(word, leftStrength, rightStrength);
    }

    public String battle(String leftWord, String rightWord) {
        int leftScore = calculateScoreL(leftWord, leftStrength);
        int rightScore = calculateScoreR(rightWord, rightStrength);

        return determineWinner(leftScore, rightScore);
    }

    private int calculateScoreL(String word, String strength) {
        int score = 0;

        for (char letter : word.toCharArray()) {
            score += strength.indexOf(letter) != -1 ? strength.length() - strength.indexOf(letter) : 0;
        }
        System.out.println("Left Score : "  + score); 
        return score;
    }

    private int calculateScoreR(String word, String strength) {
        int score = 0;

        for (char letter : word.toCharArray()) {
            score += strength.indexOf(letter) != -1 ? strength.length() - strength.indexOf(letter) : 0;
        }
        System.out.println("Right Score : "  + score); 
        return score;
    }

    private String determineWinner(int leftScore, int rightScore) {
        if (leftScore > rightScore) {
            return "Left side wins!\n";
        } else if (rightScore > leftScore) {
            return "Right side wins!\n";
        } else {
            return "Let's fight again!\n";
        }
    }

    private String determineWinner(String word, String leftStrength, String rightStrength) {
        int leftScore = calculateScoreL(word, leftStrength);
        int rightScore = calculateScoreR(word, rightStrength);

        return determineWinner(leftScore, rightScore);
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println("Test Cases: ");
        System.out.println();
        AlphabetWarGame game1 = new AlphabetWarGame();
        System.out.println(game1.battle("z")); // Right side wins!

        // Example 2
        AlphabetWarGame game2 = new AlphabetWarGame("wpbs", "mqdz");
        System.out.println(game2.battle("zdqmwpbs")); // Let's fight again!

        // Example 3
        AlphabetWarGame game3 = new AlphabetWarGame();
        System.out.println(game3.battle("wwwwwwz")); // Left side wins!

        // Example 4
        AlphabetWarGame game4 = new AlphabetWarGame("wpbs", "mqdz");
        System.out.println(game4.battle("wwwwww", "zzz")); // Let's fight again!

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the left word: ");
        String leftWord = scanner.nextLine();
        System.out.println("Enter the right word: ");
        String rightWord = scanner.nextLine();
        scanner.close();
        AlphabetWarGame userGame = new AlphabetWarGame();
        System.out.println(userGame.battle(leftWord, rightWord));
    }
}
