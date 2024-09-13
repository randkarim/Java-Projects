import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static Map<String, Double> exchangeRates;

    static {
        exchangeRates = new HashMap<>();
        // (ideally you'd fetch these from an API)
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.73);
        exchangeRates.put("JPY", 110.25);
        exchangeRates.put("AUD", 1.34);
        exchangeRates.put("CAD", 1.25);
        exchangeRates.put("CHF", 0.92);
        exchangeRates.put("CNY", 6.47);
        exchangeRates.put("INR", 74.38);
        exchangeRates.put("MXN", 20.05);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Currency Converter!");
        System.out.println("\nAvailable currencies: " + String.join(", ", exchangeRates.keySet()));

        double amount = getUserInputDouble(scanner, "Enter the amount to convert: ");
        String fromCurrency = getUserInputCurrency(scanner, "Enter the currency to convert from: ");
        String toCurrency = getUserInputCurrency(scanner, "Enter the currency to convert to: ");

        double result = convertCurrency(amount, fromCurrency, toCurrency);

        if (result != -1) {
            System.out.printf("%.2f %s is equal to %.2f %s%n", amount, fromCurrency, result, toCurrency);
        } else {
            System.out.println("Conversion failed. Please check your inputs.");
        }

        scanner.close();
    }

    private static double getUserInputDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    private static String getUserInputCurrency(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toUpperCase();
            if (exchangeRates.containsKey(input)) {
                return input;
            } else {
                System.out.println("Invalid currency. Please choose from: " + String.join(", ", exchangeRates.keySet()));
            }
        }
    }

    private static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            return -1; // Indicate failure
        }

        // Convert to USD first (our base currency)
        double usdAmount = amount / exchangeRates.get(fromCurrency);

        // Then convert from USD to the target currency
        return usdAmount * exchangeRates.get(toCurrency);
    }
}
