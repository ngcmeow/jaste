import java.util.List;

public class JasteMain {
    public static void main(String[] args) {
        try {
            ContentFilter adminFilter = new ContentFilter("filter.txt");
            WebServer webServer = new WebServer("http://localhost", 8081, List.of(
                    adminFilter
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
