/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unc.odum.dataverse.util.json;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asone
 */
public class JsonResponseParserTest {
    private static final Logger logger = Logger.getLogger(JsonResponseParserTest.class.getName());

    public JsonResponseParserTest() {
    }
    
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
    
    
    static String RESPONSE_NEW_DATASET_CREATION="{\"status\":\"OK\",\"data\":{\"id\":29,\"persistentId\":\"doi:10.5072/FK2/UEEXAV\"}}";
    static String RESPONSE_NEW_DATASET_CREATION_X ="{\"data\":{\"persistentId\":\"doi:10.33563/FK2/FLIAPV\",\"id\":25},\"status\":\"OK\"}";
    
    /**
     * Test of parseDatasetCreationResponse method, of class JsonResponseParser.
     */
    @Test
    public void testParseDatasetCreationResponse() {
        System.out.println("parseDatasetCreationResponse");
        JsonResponseParser instance = new JsonResponseParser();
        String expResult = "29";
        logger.log(Level.INFO, "expResult={0}", expResult);
        String actual = instance.parseDatasetCreationResponse(RESPONSE_NEW_DATASET_CREATION);
        logger.log(Level.INFO, "actual={0}", actual);
        assertEquals(expResult, actual);
    }

    /**
     * Test of parseDatasetCreationResponseByJsonPath method, of class JsonResponseParser.
     */
    @Test
    public void testParseDatasetCreationResponseByJsonFilter() {
        System.out.println("parseDatasetCreationResponseByJsonPath");
        JsonFilter instance = new JsonFilter();
        String expResult = "25";
        String result = instance.parseDatasetCreationResponse(RESPONSE_NEW_DATASET_CREATION_X);
        logger.log(Level.INFO, "result={0}", result);
        assertEquals(expResult, result);
    }
    
    
    
}
