import java.util.Scanner;

public class Main {
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[96m";
    private static final String YELLOW = "\u001B[93m";
    private static final String GREEN = "\u001B[92m";
    private static final String PURPLE = "\u001B[95m";
    private static final String GRAY = "\u001B[90m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        showWelcomeScreen();

        while (true) {
            System.out.print(CYAN + "  Твоє питання » " + RESET);
            String question = scanner.nextLine().trim();

            if (question.equalsIgnoreCase("вихід")) {
                System.out.println(PURPLE + "\n  Котовий прощається. Мяу! 🐾" + RESET);
                break;
            }

            if (question.isEmpty()) {
                System.out.println(YELLOW + "  Котовий: Напиши питання, будь ласка. 😺" + RESET);
            } else {
                think();
                System.out.println(GREEN + "  Котовий: База не підключена. 🐾\n" + RESET);
            }
        }

        scanner.close();
    }

    private static void showWelcomeScreen() {
        System.out.println(PURPLE + "╭──────────────────────────────────────╮" + RESET);
        System.out.println(PURPLE + "│" + RESET + "       " + YELLOW + "М Я У !" + RESET + "  " + CYAN + "КОТОВИЙ" + RESET + "         " + PURPLE + "│" + RESET);
        System.out.println(PURPLE + "│" + RESET + "                                      " + PURPLE + "│" + RESET);
        System.out.println(PURPLE + "│" + RESET + "             " + YELLOW + " /\\_/\\\\" + RESET + "               " + PURPLE + "│" + RESET);
        System.out.println(PURPLE + "│" + RESET + "            " + YELLOW + "( o.o )" + RESET + "              " + PURPLE + "│" + RESET);
        System.out.println(PURPLE + "│" + RESET + "             " + YELLOW + " > ^ <" + RESET + "               " + PURPLE + "│" + RESET);
        System.out.println(PURPLE + "│" + RESET + "                                      " + PURPLE + "│" + RESET);
        System.out.println(PURPLE + "╰──────────────────────────────────────╯" + RESET);
        System.out.println("  " + CYAN + "Питай про що завгодно." + RESET);
        System.out.println("  " + GRAY + "Для виходу введи «вихід»." + RESET + "\n");
    }

    private static void think() {
        String[] frames = {"   ", ".  ", ".. ", "..."};
        try {
            for (String frame : frames) {
                System.out.print("\r" + PURPLE + "  Котовий обдумує" + frame + RESET);
                Thread.sleep(350);
            }
            System.out.print("\r\u001B[2K");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println();
        }
    }
}
