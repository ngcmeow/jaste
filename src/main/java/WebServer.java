import io.javalin.Javalin;
import io.javalin.http.Context;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WebServer {
    private static List<FeatureSet> enabledFeatures = new ArrayList<>();
    private static String baseUrl = "";

    public WebServer(String baseUrl, int port, List<FeatureSet> featureSetList) {
        WebServer.baseUrl = baseUrl + ":" + port + "/";

        var app = Javalin.create().get("/", ctx -> ctx.result("Jaste")).start(port);
        app.post("/upload", WebServer::contentUpload);
        app.get("/pastes/*", ctx -> {
            String query = ctx.path().substring(7);
            System.out.println(query);
            if (!Files.exists(Path.of("pastes/" + query))) {
                ctx.status(404).result("File not found.");
                return;
            }
            ctx.contentType("text/plain").result(Files.readString(Path.of("pastes/" + query), StandardCharsets.UTF_8));
        });

        if (featureSetList != null) {
            for (FeatureSet feature : featureSetList) {
                if (feature.getState()) {
                    enabledFeatures.add(feature);
                    System.out.println("[+] FeatureSet: " + feature + " enabled.");
                }
            }
        }
    }

    private static FeatureSet hasFeature(String properFeatureSetName) {
        for (FeatureSet feature : enabledFeatures)
            if (feature.getFeatureSetName().equals(properFeatureSetName))
                return feature;

        return null;
    }

    private static void contentUpload(Context ctx) throws Exception {
        String postedString = ctx.body();
        ContentFilter filter = (ContentFilter) hasFeature("ContentFilter");

        if (filter != null)
            if (filter.hasFilteredWord(postedString)) {
                ctx.contentType("text/plain").result("DENIED. Reason: filtered word(s)");
                return;
            }

        fileHandler userUpload = new fileHandler(baseUrl);
        ctx.contentType("text/plain").result(userUpload.write(postedString));
    }
}
