import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Привіт! Я Котовий — став мені будь-яке питання.");
        System.out.println("Щоб завершити розмову, введи «вихід».");

        while (true) {
            System.out.print("Твоє питання: ");
            String question = scanner.nextLine().trim();

            if (question.equalsIgnoreCase("вихід")) {
                System.out.println("Котовий прощається. Мяу!");
                break;
            }

            if (question.isEmpty()) {
                System.out.println("Котовий: Спочатку введи питання, будь ласка.");
            } else {
                System.out.println("Котовий: База не підключена.");
            }
        }

        scanner.close();
    }
}
