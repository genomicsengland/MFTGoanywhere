package co.uk.gel.proj.runner;

import co.uk.gel.config.BrowserConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class MergeFailedScenarios {

    private static final Logger log = LoggerFactory.getLogger(MergeFailedScenarios.class);

    @Test
    public void  mergeTextFiles() throws IOException {
        // create instance of directory
        File dir = new File("target/cucumber-parallel");
        // create obejct of PrintWriter for output file
        PrintWriter pw = new PrintWriter("target/generated-test-sources/rerun.txt");
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File d, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };
        // Get list of all the files in form of String Array
        String[] fileNames = dir.list(textFilter);
        if (BrowserConfig.ifRerunNeeded().toUpperCase().equals("YES")) {
            for (String fileName : fileNames) {
                File f = new File(dir, fileName);
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    pw.println(line);
                    line = br.readLine();
                }
                pw.flush();
            }
        }
    }
}
