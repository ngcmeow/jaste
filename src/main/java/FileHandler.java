import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
    private String fileLocation = "";
    private String baseUrl = "";

    public FileHandler(String baseUrl) {
        if (baseUrl.startsWith("/"))
            System.err.println("No absolute paths allowed.");

        this.baseUrl = baseUrl;

        try {
            Files.createDirectories(Paths.get("pastes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String write(String text) {
        if (fileLocation.equals(""))
            fileLocation = RandomStringUtils.randomAlphanumeric(15);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("pastes/" + fileLocation));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baseUrl + "pastes/" + fileLocation;
    }
}
