/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unc.odum.dataverse.util.json;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPatch;
import javax.json.JsonPatchBuilder;

/**
 *
 * @author asone
 */
public class JsonPatchForDatasetTemplate {
    private static final Logger logger = 
            Logger.getLogger(JsonPatchForDatasetTemplate.class.getName());

    ;
    
    public JsonPatchForDatasetTemplate() {
    }
    
    
//    public JsonPatchForDatasetTemplate(SkeletalDataset sd){
//        skeletalDataset=sd;
//    }
    
    public JsonPatch prepareJsonPatch(SkeletalDataset skeletalDataset){
        JsonArray subject = skeletalDataset.getSubject() != null ? Json.createArrayBuilder().
                add(skeletalDataset.getSubject()).build() : null;
            JsonPatchBuilder jPBuilder = Json.createPatchBuilder();
            JsonPatch jsonPatch = jPBuilder
                .replace(JsonPointerForDataset.POINTER_TO_DATASET_TITLE, skeletalDataset.getDatasetTitle())
                .replace(JsonPointerForDataset.POINTER_TO_DATASET_AUTHOR_AFFILIATION, skeletalDataset.getAuthorAffiliation())
                .replace(JsonPointerForDataset.POINTER_TO_AUTHOR_NAME, skeletalDataset.getAuthorName())
                .replace(JsonPointerForDataset.POINTER_TO_EMAIL, skeletalDataset.getDatasetContactEmail())
                .replace(JsonPointerForDataset.POINTER_TO_DATASET_DESCRIPTION, skeletalDataset.getDsDescriptionValue())
                .replace(JsonPointerForDataset.POINTER_TO_SUBJECTS, subject)
                    .build();
            logger.log(Level.INFO, "JsonPatchForDatasetTemplate#prepareJsonPatch:jsonPatch=\n{0}", jsonPatch);
        return jsonPatch;
    }
    
    
    public JsonObject applyJsonPatch(SkeletalDataset skeletalDataset, JsonObject jsonObject){
        return prepareJsonPatch(skeletalDataset).apply(jsonObject);
    }
}
