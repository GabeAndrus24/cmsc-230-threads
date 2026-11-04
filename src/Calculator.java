public class Calculator {

    static String answer(String line) {
        String[] words = line.split(" ");

        try {
            if (words.length != 3) {
                return "ERROR: Invalid input";
            }

            int num1 = Integer.parseInt(words[1]);
            int num2 = Integer.parseInt(words[2]);

            if (words[0].equals("ADD")) {
                return String.valueOf(num1 + num2);
            } else if (words[0].equals("MUL")) {
                return String.valueOf(num1 * num2);
            } else {
                return "ERROR: Unknown operation";
            }

        } catch (NumberFormatException e) {
            return "ERROR: Invalid numbers";
        }
    }
}
