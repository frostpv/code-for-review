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
                try {
                    double result = CodeCalculator.CALCULATE(question);
                    System.out.println(GREEN + "  Котовий: " + result + " 🐾\n" + RESET);
                } catch (IllegalArgumentException e) {
                    if (looksLikeMath(question)) {
                        System.out.println(YELLOW + "  Котовий: Не вдалося порахувати цей вираз. Перевір його.\n" + RESET);
                    } else {
                        System.out.println(GREEN + "  Котовий: База не підключена. 🐾\n" + RESET);
                    }
                }
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

    private static boolean looksLikeMath(String text) {
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c) || "+-*/().".indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }

    private static class CodeCalculator {
        static double CALCULATE(String s) {
            Parser parser = new Parser(s);
            double x = parser.expression();
            parser.skipSpaces();
            if (parser.p != s.length()) {
                throw new IllegalArgumentException("Зайві символи у виразі");
            }
            return x;
        }

        private static class Parser {
            private final String s;
            private int p;

            Parser(String s) {
                this.s = s;
            }

            double expression() {
                double x = term();
                while (true) {
                    skipSpaces();
                    if (take('+')) x += term();
                    else if (take('-')) x -= term();
                    else return x;
                }
            }

            double term() {
                double x = factor();
                while (true) {
                    skipSpaces();
                    if (take('*')) x *= factor();
                    else if (take('/')) {
                        double y = factor();
                        if (y == 0) throw new IllegalArgumentException("Ділення на нуль");
                        x /= y;
                    } else return x;
                }
            }

            double factor() {
                skipSpaces();
                if (take('+')) return factor();
                if (take('-')) return -factor();
                if (take('(')) {
                    double x = expression();
                    skipSpaces();
                    if (!take(')')) throw new IllegalArgumentException("Не закрита дужка");
                    return x;
                }

                int start = p;
                while (p < s.length() && (Character.isDigit(s.charAt(p)) || s.charAt(p) == '.')) p++;
                if (start == p) throw new IllegalArgumentException("Очікувалося число");
                try {
                    return Double.parseDouble(s.substring(start, p));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Некоректне число", e);
                }
            }

            void skipSpaces() {
                while (p < s.length() && Character.isWhitespace(s.charAt(p))) p++;
            }

            boolean take(char c) {
                if (p < s.length() && s.charAt(p) == c) {
                    p++;
                    return true;
                }
                return false;
            }
        }
    }
}
