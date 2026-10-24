import java.awt.Desktop;
import java.net.URI;

public class BrowserOpener {

    public void open(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Статья открыта в браузере.");
            } else {
                System.out.println("Не получилось открыть браузер. Ссылка: " + url);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при открытии браузера. Ссылка: " + url);
        }
    }
}
