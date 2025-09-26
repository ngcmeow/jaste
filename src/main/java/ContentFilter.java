import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContentFilter extends FeatureSet {
    private String pathToFilter = "filters.txt";
    private List<String> words = new ArrayList<>();

    public ContentFilter(String filterPath) throws Exception {
        featureSetName = "ContentFilter";

        if (!getState())
            throw new Exception("[!] Filter is not enabled! Set a flag first.");

        this.pathToFilter = filterPath;

        try {
            Scanner scanner = new Scanner(new File(filterPath));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }

            System.out.println("[+] Filter: " + this.pathToFilter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPathToFilter() {
        return pathToFilter;
    }

    public boolean hasFilteredWord(String body) {
        for (String s : words)
            if (body.toLowerCase().contains(s.toLowerCase()))
                return true;

        return false;
    }
}
