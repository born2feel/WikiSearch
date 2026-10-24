import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    public String readQuery() {
        System.out.print("\nВведите запрос (или exit для выхода): ");
        if (!scanner.hasNextLine()) {
            return "exit"; //ввод закончился
        }
        return scanner.nextLine().trim();
    }

    //возвращает номер статьи или -1 если ввели не число
    public int readArticleNumber(int max) {
        System.out.print("Введите номер статьи (1-" + max + ") или 0 для нового поиска: ");
        if (!scanner.hasNextLine()) {
            return 0;
        }
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
