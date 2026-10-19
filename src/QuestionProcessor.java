public class QuestionProcessor {
    public static void Handle(String q) {
        ConsoleUtils.Print("DEBUG: input=" + q);
        if (q.isEmpty()) {
            ConsoleUtils.Print(Main.YELLOW + "  Котовий: Напиши питання, будь ласка. 😺" + Main.RESET);
            return;
        }

        ConsoleUtils.ShowThinking();
        try {
            double x = CodeCalculator.CALCULATE(q);
            ConsoleUtils.Print(Main.GREEN + "  Котовий: " + x + " 🐾\n" + Main.RESET);
        } catch (Exception e) {
            if (Main.looksLikeMath(q)) {
                ConsoleUtils.Print(Main.YELLOW + "  Котовий: Не вдалося порахувати цей вираз. Перевір його.\n" + Main.RESET);
            } else {
                ConsoleUtils.Print(Main.GREEN + "  Котовий: База не підключена. 🐾\n" + Main.RESET);
            }
        }
    }
}
