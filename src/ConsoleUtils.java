public class ConsoleUtils {
    public static String lastMessage = "";

    public static void ShowWelcome() {
        System.out.println(Main.PURPLE + "╭──────────────────────────────────────╮" + Main.RESET);
        System.out.println(Main.PURPLE + "│" + Main.RESET + "       " + Main.YELLOW + "М Я У !" + Main.RESET + "  " + Main.CYAN + "КОТОВИЙ" + Main.RESET + "         " + Main.PURPLE + "│" + Main.RESET);
        System.out.println(Main.PURPLE + "│" + Main.RESET + "                                      " + Main.PURPLE + "│" + Main.RESET);
        System.out.println(Main.PURPLE + "│" + Main.RESET + "             " + Main.YELLOW + " /\\_/\\\\" + Main.RESET + "               " + Main.PURPLE + "│" + Main.RESET);
        System.out.println(Main.PURPLE + "│" + Main.RESET + "            " + Main.YELLOW + "( o.o )" + Main.RESET + "              " + Main.PURPLE + "│" + Main.RESET);
        System.out.println(Main.PURPLE + "│" + Main.RESET + "             " + Main.YELLOW + " > ^ <" + Main.RESET + "               " + Main.PURPLE + "│" + Main.RESET);
        System.out.println(Main.PURPLE + "╰──────────────────────────────────────╯" + Main.RESET);
        System.out.println("  " + Main.CYAN + "Питай про що завгодно." + Main.RESET);
        System.out.println("  " + Main.GRAY + "Для виходу введи «вихід»." + Main.RESET + "\n");
    }

    public static void ShowThinking() {
        String[] a = {"   ", ".  ", ".. ", "..."};
        try {
            for (String frame : a) {
                System.out.print("\r" + Main.PURPLE + "  Котовий обдумує" + frame + Main.RESET);
                Thread.sleep(350);
            }
            System.out.print("\r\u001B[2K");
        } catch (Exception e) {
            System.out.println("Thinking failed");
        }
    }

    public static void Print(String text) {
        lastMessage = text;
        System.out.println(text);
    }
}
