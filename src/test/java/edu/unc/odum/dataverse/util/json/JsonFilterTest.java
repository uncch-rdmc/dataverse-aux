package edu.unc.odum.dataverse.util.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 *
 * @author asone
 */
public class JsonFilterTest {

    private static final Logger logger = Logger.getLogger(JsonFilterTest.class.getName());

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public JsonFilterTest() {
    }

    String datasetIdentifier = "6NBQ5E";
    String fileLocationRoot = "/tmp/files/10.5072/FK2/";
    String fileLocation = fileLocationRoot + datasetIdentifier + "/export_dataverse_json.cached";
    String pathString = fileLocationRoot + datasetIdentifier + "/filtered-result.json";

    @Test
    public void testFilterApiPayload_String_String() throws IOException, JSONException {
        String rawJsonFileName = "/json/export_dataverse_json.cached";

        File rawJsonFile = tempFolder.newFile("export_dataverse_json.cached");

        String filteredResultFile = "/json/filtered-result.json";

        //String destFile = File.createTempFile("JsonFilterTestResult_", ".json").getAbsolutePath();
        String destFile = tempFolder.newFile("destFile.json").getAbsolutePath();
        // read the raw json file
        try (InputStream rawIs = getClass().getResourceAsStream(rawJsonFileName);
                InputStream resultIs = getClass().getResourceAsStream(filteredResultFile)) {

            // copy the input file within the jar to the temp directory
            Files.copy(rawIs, rawJsonFile.toPath(),  StandardCopyOption.REPLACE_EXISTING);

            JsonFilter filter = new JsonFilter();

            filter.filterApiPayload(rawJsonFile.getAbsolutePath(), destFile);

            // read back the result file
            File resultFile = new File(destFile);
            Gson gson = new Gson();
            JsonObject actual = gson.fromJson(new FileReader(resultFile), JsonObject.class);
            JsonObject expected = gson.fromJson(new InputStreamReader(resultIs), JsonObject.class);

            JSONAssert.assertEquals(expected.toString(), actual.toString(), false);
        }

    }

    @Test
    public void testFilterApiPayload_File_String() throws IOException, JSONException {
        String rawJsonFileName = "/json/export_dataverse_json.cached";

        File rawJsonFile = tempFolder.newFile("export_dataverse_json.cached");

        String filteredResultFile = "/json/filtered-result.json";

        //String destFile = File.createTempFile("JsonFilterTestResult_", ".json").getAbsolutePath();
        String destFile = tempFolder.newFile("destFile.json").getAbsolutePath();
        // read the raw json file
        try (InputStream rawIs = getClass().getResourceAsStream(rawJsonFileName);
                InputStream resultIs = getClass().getResourceAsStream(filteredResultFile)) {
            // copy the input file within the jar to the temp directory
            Files.copy(rawIs, rawJsonFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            JsonFilter filter = new JsonFilter();

            filter.filterApiPayload(rawJsonFile, destFile);

            // read back the result file
            File resultFile = new File(destFile);
            Gson gson = new Gson();
            JsonObject actual = gson.fromJson(new FileReader(resultFile), JsonObject.class);
            JsonObject expected = gson.fromJson(new InputStreamReader(resultIs), JsonObject.class);

            JSONAssert.assertEquals(expected.toString(), actual.toString(), false);
        }

    }

    @Test
    public void testFilterApiPayload_InputStream_String() throws IOException, JSONException {
        String rawJsonFile = "/json/export_dataverse_json.cached";
        String filteredResultFile = "/json/filtered-result.json";

        //String destFile = File.createTempFile("JsonFilterTestResult_", ".json").getAbsolutePath();
        String destFile = tempFolder.newFile("destFile.json").getAbsolutePath();
        // read the raw json file
        try (InputStream rawIs = getClass().getResourceAsStream(rawJsonFile);
                InputStream resultIs = getClass().getResourceAsStream(filteredResultFile)) {
            JsonFilter filter = new JsonFilter();

            filter.filterApiPayload(rawIs, destFile);

            // read back the result file
            File resultFile = new File(destFile);
            Gson gson = new Gson();
            JsonObject actual = gson.fromJson(new FileReader(resultFile), JsonObject.class);
            JsonObject expected = gson.fromJson(new InputStreamReader(resultIs), JsonObject.class);

            JSONAssert.assertEquals(expected.toString(), actual.toString(), false);
        }
    }

//    @Test
//    public void shouldRunApplication() throws Exception {
//        String[] args = new String[]{};
//        JsonFilter.main(args);
//    }
}
