public class CodeCalculator {
    public static double CALCULATE(String s) {
        Parser parser = new Parser(s);
        double x = parser.expression();
        parser.skipSpaces();
        if (parser.p != s.length()) {
            throw new IllegalArgumentException("Зайві символи у виразі");
        }
        return x;
    }

    public static class Parser {
        public final String s;
        public int p;

        public Parser(String s) {
            this.s = s;
        }

        public double expression() {
            double x = term();
            while (true) {
                skipSpaces();
                if (take('+')) x += term();
                else if (take('-')) x -= term();
                else return x;
            }
        }

        public double term() {
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

        public double factor() {
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

        public void skipSpaces() {
            while (p < s.length() && Character.isWhitespace(s.charAt(p))) p++;
        }

        public boolean take(char c) {
            if (p < s.length() && s.charAt(p) == c) {
                p++;
                return true;
            }
            return false;
        }
    }
}
