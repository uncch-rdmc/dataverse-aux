/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unc.odum.dataverse.util.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPatchBuilder;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonValue;
import org.json.JSONException;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 *
 * @author Akio Sone, Univ, of North Carolina at Chapel Hill, H.W. Odum Inst.
 */
public class JsonPointerForDatasetTest {
    
    private static final Logger logger = Logger.getLogger(JsonPointerForDatasetTest.class.getName());
    
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();    
    
    public JsonPointerForDatasetTest() {
    }

    @Test
    public void testJsonPointer() throws IOException, JSONException {
        
        String rawJsonFileName = "/json/export_dataverse_json.cached";

        String filteredResultFile = "/json/filtered-result_2.json";

        // read the raw json file and expected result file
        try (InputStream rawIs = getClass().getResourceAsStream(rawJsonFileName);
            InputStream resultIs = getClass().getResourceAsStream(filteredResultFile);
                JsonReader  jsonReader = Json.createReader(rawIs);
                JsonReader jsonReaderActual = Json.createReader(resultIs)
                ) {

            JsonObject expected = jsonReaderActual.readObject();
            logger.log(Level.INFO, "expected={0}", expected);
            JsonObject rawJsonObject = jsonReader.readObject();

            logger.log(Level.INFO, "rawJsonObject={0}", rawJsonObject);

            // create a new Json object to store JsonPointers
            JsonObject object = Json.createObjectBuilder().build();
            
            // create the two JsonPointer instances 
            JsonPointer metadataBlock = Json.createPointer(JsonPointerForDataset.POINTER_METADATABLOCKS);
            JsonPointer files = Json.createPointer(JsonPointerForDataset.POINTER_FILES);
            
            // get the value for each of the above JsonPointer instances
            JsonValue metadataBlockValue = metadataBlock.getValue(rawJsonObject);
            JsonValue filesValue         = files.getValue(rawJsonObject);
            
            // create a JsonPatchBuilder object and add JsonValu instances
            JsonPatchBuilder builder = Json.createPatchBuilder();
            
            JsonObject actual = builder
                    .add(JsonPointerForDataset.POINTER_METADATABLOCKS_FILTERED, metadataBlockValue)
                    .add(JsonPointerForDataset.POINTER_FILES_FILTERED, filesValue)
                    .build()
                    .apply(object);
            
            logger.log(Level.INFO, "actual={0}", actual);

            JSONAssert.assertEquals(expected.toString(), actual.toString(), false);
        }
        
        
        
        
    }
    
}
