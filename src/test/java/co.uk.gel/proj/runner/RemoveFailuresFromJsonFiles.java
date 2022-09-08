package co.uk.gel.proj.runner;

import co.uk.gel.config.BrowserConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveFailuresFromJsonFiles {

    private static final Logger log = LoggerFactory.getLogger(RemoveFailuresFromJsonFiles.class);

    @Test
    public void jsonFileFailureRemoval() throws IOException, JSONException {
        if (BrowserConfig.ifRerunNeeded().toUpperCase().equals("YES")) {
            String directory = "target/cucumber-parallel1/";
            File dir = new File(directory);

            FilenameFilter jsonFilter = (d, name) -> (name.toLowerCase().endsWith(".json") && !name.toLowerCase().contains("rerun"));

            String[] jsonFiles = dir.list(jsonFilter);
            log.info("Total number of JSON file(s) to process is/are " + jsonFiles.length);

            for (int i = 1; i <= jsonFiles.length; i++) {

                String textFilePath = directory + i + ".txt";
                String jsonFilePath = directory + i + ".json";

                Path textPath = Paths.get(textFilePath);
                Path jsonPath = Paths.get(jsonFilePath);

                List<String> failureContents = Files.readAllLines((textPath));

                if (!failureContents.isEmpty()) {

                    String patternStart = "$.*.elements[?(@.line!=";
                    String patternEnd = ")]";

                    Pattern p = Pattern.compile("(:)\\d+");
                    Matcher m = p.matcher(failureContents.get(0));

                    ArrayList<String> lineNumber = new ArrayList<>();

                    while (m.find()) {
                        lineNumber.add(m.group().replaceAll(":", ""));
                    }

                    log.info("Number of Error(s) in file " + textFilePath + " is/are " + lineNumber.size());

                    log.info("Now Processing the file --> " + jsonFilePath);

                    for (int x = 0; x < lineNumber.size(); x++) {

                        String contents = new String(Files.readAllBytes(jsonPath));
                        Object document = Configuration.defaultConfiguration().jsonProvider().parse(contents);

                        log.info("Now removing the failed test from " + jsonFilePath + " for line number " + lineNumber.get(x));

                        String errorLine = patternStart + lineNumber.get(x) + patternEnd;

                        JSONArray line = JsonPath.read(document, "$.*.line");
                        JSONArray element = JsonPath.read(document, errorLine);
                        JSONArray name = JsonPath.read(document, "$.*.name");
                        JSONArray description = JsonPath.read(document, "$.*.description");
                        JSONArray id = JsonPath.read(document, "$.*.id");
                        JSONArray keyword = JsonPath.read(document, "$.*.keyword");
                        JSONArray uri = JsonPath.read(document, "$.*.uri");
                        JSONArray tags = JsonPath.read(document, "$.*.tags");

                        for (int y = 0; y < element.size(); y++) {
                            if (element.get(element.size() - 1).toString().contains("background")) {
                                element.remove(element.size() - 1);
                                log.info("Removing the related Background");
                            }
                            if ((element.get(y).toString().contains("background")) && (element.get(y + 1).toString().contains("background"))) {
                                element.remove(y);
                                log.info("Removing the related Background");
                            }
                        }

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        if (!element.isEmpty()) {

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("line", line.get(0));
                            jsonObject.put("elements", element.toArray());
                            jsonObject.put("name", name.get(0));
                            jsonObject.put("description", description.get(0));
                            jsonObject.put("id", id.get(0));
                            jsonObject.put("keyword", keyword.get(0));
                            jsonObject.put("uri", uri.get(0));
                            jsonObject.put("tags", tags.get(0));

                            Files.write(jsonPath, gson.toJson(jsonObject).getBytes());
                            List<String> a = Files.readAllLines(jsonPath);
                            a.remove(a.size() - 1);
                            a.remove(0);
                            a.remove(0);
                            a.add(0, "   {");
                            a.add(0, "[");
                            a.add("]");

                            FileUtils.writeLines(FileUtils.getFile(jsonFilePath), a);

                            log.info("Failed test from " + jsonFilePath + " for line number " + lineNumber.get(x) + " has been removed ");
                        } else {
                            Files.write(jsonPath, "".getBytes());
                            log.info("All the test(s) in the " + textFilePath + "have failed, Hence made the file empty. All the tests will be re-run again.");

                        }

                    }
                }
            }
        }
    }
}