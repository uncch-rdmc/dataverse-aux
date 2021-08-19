/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unc.odum.dataverse.util.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPatch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 *
 * @author asone
 */
public class JsonPatchForDatasetTemplateTest {
    
    private static final Logger logger = Logger.getLogger(JsonPatchForDatasetTemplateTest.class.getName());
    public JsonPatchForDatasetTemplateTest() {
    }
    String patch1 ="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/0/value\",\"value\":\"Dataset created by TRSA\"}]";
    
//    String patch2 = "[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/0/value\",\"value\":\"Dataset created by TRSA\"},{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/1/value/0/authorAffiliation/value\",\"value\":\"UNC Odum Institute\"}]";
    
    String patch2="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/1/value/0/authorAffiliation/value\",\"value\":\"UNC Odum Institute\"}]";
    
    String patch3="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/1/value/0/authorName/value\",\"value\":\"Akio Sone\"}]";
    
    String patch4="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/2/value/0/datasetContactEmail/value\",\"value\":\"xxxxx@email.unc.edu\"}]";
    
    String patch5="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/3/value/0/dsDescriptionValue/value\",\"value\":\"minimum dataset\"}]";
    
    String patch6 ="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/4/value\",\"value\":[\"Other\"]}]";
    
    String patch="[{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/0/value\",\"value\":\"Dataset created by TRSA\"},{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/1/value/0/authorAffiliation/value\",\"value\":\"UNC Odum Institute\"},{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/1/value/0/authorName/value\",\"value\":\"Akio Sone\"},{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/2/value/0/datasetContactEmail/value\",\"value\":\"xxxxx@email.unc.edu\"},{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/3/value/0/dsDescriptionValue/value\",\"value\":\"minimum dataset\"},{\"op\":\"replace\",\"path\":\"/datasetVersion/metadataBlocks/citation/fields/4/value\",\"value\":[\"Other\"]}]";
    
    String afterPatchApplied ="{\"datasetVersion\":{\"metadataBlocks\":{\"citation\":{\"displayName\":\"Citation Metadata\",\"fields\":[{\"multiple\":false,\"typeClass\":\"primitive\",\"typeName\":\"title\",\"value\":\"dataset created by native api with minimal metadata\"},{\"multiple\":true,\"typeClass\":\"compound\",\"typeName\":\"author\",\"value\":[{\"authorAffiliation\":{\"multiple\":false,\"typeClass\":\"primitive\",\"typeName\":\"authorAffiliation\",\"value\":\"UNC Odum Institute\"},\"authorName\":{\"multiple\":false,\"typeClass\":\"primitive\",\"typeName\":\"authorName\",\"value\":\"Akio Sone\"}}]},{\"multiple\":true,\"typeClass\":\"compound\",\"typeName\":\"datasetContact\",\"value\":[{\"datasetContactEmail\":{\"multiple\":false,\"typeClass\":\"primitive\",\"typeName\":\"datasetContactEmail\",\"value\":\"zzzz@email.unc.edu\"}}]},{\"multiple\":true,\"typeClass\":\"compound\",\"typeName\":\"dsDescription\",\"value\":[{\"dsDescriptionValue\":{\"multiple\":false,\"typeClass\":\"primitive\",\"typeName\":\"dsDescriptionValue\",\"value\":\"minimum dataset\"}}]},{\"multiple\":true,\"typeClass\":\"controlledVocabulary\",\"typeName\":\"subject\",\"value\":[\"Other\"]}]}}}}";
    
    
    @BeforeClass
    public static void setUpClass() {
        
        
        
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of prepareJsonPatch method, of class JsonPatchForDatasetTemplate.
     */

    @Test
    public void testPrepareJsonPatch() {
        System.out.println("========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch: start ==========");
        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
                "minimum dataset", "Other");
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch: end ==========");
    }

    @Ignore
    @Test
    public void testPrepareJsonPatch6() {
        System.out.println("========== prepareJsonPatch6 ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch6: start ==========");
//        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
//                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
//                "minimum dataset", "Other");
        SkeletalDataset ds = new SkeletalDataset();
        ds.setSubject("Other");
        logger.log(Level.INFO, "ds={0}", ds);
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch6))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch6: end ==========");
    }
    
    @Ignore
    @Test
    public void testPrepareJsonPatch5() {
        System.out.println("========== prepareJsonPatch5 ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch5: start ==========");
//        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
//                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
//                "minimum dataset", "Other");
        SkeletalDataset ds = new SkeletalDataset();
        ds.setDsDescriptionValue("minimum dataset");
        logger.log(Level.INFO, "ds={0}", ds);
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch5))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch5: end ==========");
    }
    
    @Ignore
    @Test
    public void testPrepareJsonPatch4() {
        System.out.println("========== prepareJsonPatch4 ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch4: start ==========");
//        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
//                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
//                "minimum dataset", "Other");
        SkeletalDataset ds = new SkeletalDataset();
        ds.setDatasetContactEmail("xxxxx@email.unc.edu");
        logger.log(Level.INFO, "ds={0}", ds);
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch4))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch4: end ==========");
    }
    
    @Ignore
    @Test
    public void testPrepareJsonPatch3() {
        System.out.println("========== prepareJsonPatch3 ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch3: start ==========");
//        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
//                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
//                "minimum dataset", "Other");
        SkeletalDataset ds = new SkeletalDataset();
        ds.setAuthorName("Akio Sone");
        logger.log(Level.INFO, "ds={0}", ds);
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch3))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch3: end ==========");
    }
    
    @Ignore
    @Test
    public void testPrepareJsonPatch2() {
        System.out.println("========== prepareJsonPatch2 ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch2: start ==========");
//        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
//                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
//                "minimum dataset", "Other");
        SkeletalDataset ds = new SkeletalDataset();
        ds.setAuthorAffiliation("UNC Odum Institute");
        logger.log(Level.INFO, "ds={0}", ds);
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch2))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch2: end ==========");
    }
    
    @Ignore
    @Test
    public void testPrepareJsonPatch1() {
        System.out.println("========== prepareJsonPatch1 ==========");
        
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch1: start ==========");
//        SkeletalDataset ds = new SkeletalDataset("Dataset created by TRSA", 
//                "Akio Sone", "UNC Odum Institute", "xxxxx@email.unc.edu", 
//                "minimum dataset", "Other");
        SkeletalDataset ds = new SkeletalDataset();
        ds.setDatasetTitle("Dataset created by TRSA");
        logger.log(Level.INFO, "ds={0}", ds);
        JsonPatchForDatasetTemplate instance = new JsonPatchForDatasetTemplate();
        JsonArray expResult = Json.createReader(new StringReader(patch1))
             .readArray();
        logger.log(Level.INFO, "expResult={0}", expResult);
        JsonArray result = instance.prepareJsonPatch(ds).toJsonArray();
        logger.log(Level.INFO, "actual={0}", result);
        assertEquals(expResult, result);
        logger.log(Level.INFO, "========== JsonPatchForDatasetTemplateTest#testPrepareJsonPatch1: end ==========");
    }
    
    
    /**
     * Test of applyJsonPatch method, of class JsonPatchForDatasetTemplate.
     */
    @Test
    public void testApplyJsonPatch() {
        System.out.println("applyJsonPatch");
        logger.log(Level.INFO, "#applyJsonPatch");
        SkeletalDataset skeletalDataset = new SkeletalDataset(
                "dataset created by native api with minimal metadata",
                "Akio Sone", "UNC Odum Institute", "zzzz@email.unc.edu",
                "minimum dataset", "Other");

        String skeletalDatasetTemplate = "/json/min-data-to-create-dataset.json";
        try (InputStream intemp = 
                JsonPatchForDatasetTemplateTest.class.getResourceAsStream(skeletalDatasetTemplate)) {
            JsonObject jsonObject = Json.createReader(intemp).readObject();
            logger.log(Level.INFO, "dataset template={0}", jsonObject);
            JsonPatchForDatasetTemplate datasetTemplate = new JsonPatchForDatasetTemplate();
            JsonObject result = datasetTemplate.applyJsonPatch(skeletalDataset, jsonObject);
            logger.log(Level.INFO, "after patches applied={0}", result);
            assertEquals(afterPatchApplied, result.toString());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
}
