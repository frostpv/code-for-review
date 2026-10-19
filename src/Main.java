import java.util.Scanner;

public class Main {
    public static String RESET = "\u001B[0m";
    public static String CYAN = "\u001B[96m";
    public static String YELLOW = "\u001B[93m";
    public static String GREEN = "\u001B[92m";
    public static String PURPLE = "\u001B[95m";
    public static String GRAY = "\u001B[90m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ConsoleUtils.ShowWelcome();

        while (true) {
            System.out.print(CYAN + "  Твоє питання » " + RESET);
            String question = scanner.nextLine().trim();

            if (question.equalsIgnoreCase("вихід")) {
                System.out.println(PURPLE + "\n  Котовий прощається. Мяу! 🐾" + RESET);
                break;
            }

            QuestionProcessor.Handle(question);
        }

        scanner.close();
    }

    public static boolean looksLikeMath(String text) {
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c) || "+-*/().".indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }

}
