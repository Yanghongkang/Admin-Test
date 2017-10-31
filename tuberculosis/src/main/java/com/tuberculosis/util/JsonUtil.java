package com.tuberculosis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuberculosis.entity.data.*;
import com.tuberculosis.entity.json.DataRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Li Shaoqing
 * on 15/8/9.
 */
public class JsonUtil {

    private  static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toJson(String json, Class<T> t) {
        if(StringUtils.isBlank(json)) return null;

        try {
            return mapper.readValue(json, t);
        } catch (Exception e) {
            throw new RuntimeException("convert json fail: json is " + json);
        }
    }

    public static void main(String[] args) {
        DataRequest d = new DataRequest();
        d.setDate(new Date());
        d.setPtInfoId(22323);
        d.setChestRadioGraph(new ChestRadioGraph());
//        d.setDiabetesTest(new DiabetesTest());
        d.setDrugScpt(new DrugScpt());
        d.setGeneChip(new GeneChip());
        d.setHain(new Hain());
        d.setGeneXpert(new GeneXpert());
//        d.setHivTest(new HivTest());
        d.setSputumCulture(new SputumCulture());
        d.setSputumSmear(new SputumSmear());

        d.setTbDiagnosis(new TbDiagnosis());
        d.setTherapy(new Therapy());
        d.setTransInst(new TransInst());

        d.setLiverFunc(new LiverFunc());
        d.setRenalFunc(new RenalFunc());
        d.setUrineRoutine(new UrineRoutine());
        d.setBloodRoutine(new BloodRoutine());
        d.setPhysicalExam(new PhysicalExam());
        d.setImmunology(new Immunology());
        System.out.println(JsonUtil.objectToJson(d));
    }
}
