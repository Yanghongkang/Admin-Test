package com.tuberculosis.service.data;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Hospital;
import com.tuberculosis.entity.MxData;
import com.tuberculosis.entity.data.Assessment;
import com.tuberculosis.entity.data.BacterialType;
import com.tuberculosis.entity.data.BiochemicalDetection;
import com.tuberculosis.entity.data.BloodRoutine;
import com.tuberculosis.entity.data.ChestRadioGraph;
import com.tuberculosis.entity.data.DrugScpt;
import com.tuberculosis.entity.data.GeneChip;
import com.tuberculosis.entity.data.GeneXpert;
import com.tuberculosis.entity.data.Hain;
import com.tuberculosis.entity.data.Immunology;
import com.tuberculosis.entity.data.InitialCheck;
import com.tuberculosis.entity.data.LiverFunc;
import com.tuberculosis.entity.data.PhysicalExam;
import com.tuberculosis.entity.data.RenalFunc;
import com.tuberculosis.entity.data.SputumCulture;
import com.tuberculosis.entity.data.SputumSmear;
import com.tuberculosis.entity.data.TbDiagnosis;
import com.tuberculosis.entity.data.Therapy;
import com.tuberculosis.entity.data.TransInst;
import com.tuberculosis.entity.data.UrineRoutine;
import com.tuberculosis.entity.json.DataEditRequest;
import com.tuberculosis.entity.json.DataRequest;
import com.tuberculosis.exception.AuthorizationException;
import com.tuberculosis.repository.DiagnosisSettingDao;
import com.tuberculosis.repository.HospitalDao;
import com.tuberculosis.repository.MxDataDao;
import com.tuberculosis.repository.data.AssessMentDao;
import com.tuberculosis.repository.data.BacterialTypeDao;
import com.tuberculosis.repository.data.BiochemicalDetectionDao;
import com.tuberculosis.repository.data.BloodRoutineDao;
import com.tuberculosis.repository.data.ChestRadioGraphDao;
import com.tuberculosis.repository.data.DrugScptDao;
import com.tuberculosis.repository.data.GeneChipDao;
import com.tuberculosis.repository.data.GeneXpertDao;
import com.tuberculosis.repository.data.HainDao;
import com.tuberculosis.repository.data.ImmunologyDao;
import com.tuberculosis.repository.data.InitialCheckDao;
import com.tuberculosis.repository.data.LiverFuncDao;
import com.tuberculosis.repository.data.PhysicalExamDao;
import com.tuberculosis.repository.data.RenalFuncDao;
import com.tuberculosis.repository.data.SputumCultureDao;
import com.tuberculosis.repository.data.SputumSmearDao;
import com.tuberculosis.repository.data.TbDiagnosisDao;
import com.tuberculosis.repository.data.TherapyDao;
import com.tuberculosis.repository.data.TransInstDao;
import com.tuberculosis.repository.data.UrineRoutineDao;

/**
 *
 * @author Lishaoqing
 */
@Component
@Transactional(readOnly = true)
public class DataService {

    private static Logger logger = LoggerFactory.getLogger(DataService.class);

    @Autowired
    private TransInstDao transInstDao;
    @Autowired
    private ChestRadioGraphDao chestRadioGraphDao;
    @Autowired
    private GeneXpertDao geneXpertDao;
    @Autowired
    private SputumSmearDao sputumSmearDao;
    @Autowired
    private SputumCultureDao sputumCultureDao;
    @Autowired
    private DrugScptDao drugScptDao;
    @Autowired
    private HainDao hainDao;
    @Autowired
    private GeneChipDao geneChipDao;
//    @Autowired
//    private HivTestDao hivTestDao;
//    @Autowired
//    private DiabetesTestDao diabetesTestDao;
    @Autowired
    private TbDiagnosisDao tbDiagnosisDao;
    @Autowired
    private TherapyDao therapyDao;
    @Autowired
    private LiverFuncDao liverFuncDao;
    @Autowired
    private RenalFuncDao renalFuncDao;
    @Autowired
    private UrineRoutineDao urineRoutineDao;
    @Autowired
    private BloodRoutineDao bloodRoutineDao;
    @Autowired
    private PhysicalExamDao physicalExamDao;
    @Autowired
    private ImmunologyDao immunologyDao;
    @Autowired
    private InitialCheckDao initialCheckDao;
    @Autowired
    private BacterialTypeDao bacterialTypeDao;

    @Autowired
    private DiagnosisSettingDao diagnosisSettingDao;

    @Autowired
    private HospitalDao hospitalDao;

    @Autowired
    private MxDataDao mxDataDao;
    
    @Autowired
    BiochemicalDetectionDao biochemicaldetectionDao;
    
    @Autowired
    AssessMentDao assessMentDao;

    //    @Autowired
//    private EffectDao effectDao;


    @Transactional(readOnly = false)
    public void save(DataRequest dataRequest) throws AuthorizationException {
        Date date = dataRequest.getDate();
        Long ptInfoId = dataRequest.getPtInfoId();
        TbDiagnosis tbDiagnosis = dataRequest.getTbDiagnosis();
        Therapy therapy = dataRequest.getTherapy();

        MxData mxData = new MxData();
        mxData.setDate(date);
        mxData.setPtInfoId(ptInfoId);
        mxData.setStatus(CommonContants.MX_DATA_STATUS_COMMON);
        mxData.setTherapy(CommonContants.MX_DATA_NO_THERAPY);
        mxData.setTbDiagnosis(CommonContants.MX_DATA_NO_TbDiagnosis);
        //Yhk 提醒药物剂量
        mxData.setIsdosage(dataRequest.getIsdosage());
        if(tbDiagnosis != null) {
            mxData.setTbDiagnosis(tbDiagnosis.getTbDiagnosis());
        }
        if(therapy != null) {
            mxData.setTherapy(therapy.getTherapyStatus());
            mxData.setCurrTherapy(therapy.getCurrTherapy());
        }
        mxData = mxDataDao.save(mxData);

        Long mxId = mxData.getId();
        if(tbDiagnosis != null) {
            tbDiagnosis.setDate(date);
            tbDiagnosis.setPtInfoId(ptInfoId);
            tbDiagnosis.setMxId(mxId);
            tbDiagnosisDao.save(tbDiagnosis);
        }
        TransInst transInst = dataRequest.getTransInst();
        if(transInst != null){
            transInst.setDate(date);
            transInst.setPtInfoId(ptInfoId);
            transInst.setMxId(mxId);
            // TODO 主治医师为当前登录人
            transInstDao.save(transInst);
        }

        ChestRadioGraph chestRadioGraph = dataRequest.getChestRadioGraph();
        if(chestRadioGraph != null) {
            chestRadioGraph.setDate(date);
            chestRadioGraph.setPtInfoId(ptInfoId);
            chestRadioGraph.setMxId(mxId);
            chestRadioGraphDao.save(chestRadioGraph);
        }

//        DiabetesTest diabetesTest = dataRequest.getDiabetesTest();
//        if(diabetesTest != null) {
//            diabetesTest.setDate(date);
//            diabetesTest.setPtInfoId(ptInfoId);
//            diabetesTest.setMxId(mxId);
//            diabetesTestDao.save(diabetesTest);
//        }

        DrugScpt drugScpt = dataRequest.getDrugScpt();
        if(drugScpt != null) {
            drugScpt.setDate(date);
            drugScpt.setPtInfoId(ptInfoId);
            drugScpt.setMxId(mxId);
            drugScptDao.save(drugScpt);
        }

        GeneChip geneChip = dataRequest.getGeneChip();
        if(geneChip != null) {
            geneChip.setDate(date);
            geneChip.setPtInfoId(ptInfoId);
            geneChip.setMxId(mxId);
            geneChipDao.save(geneChip);
        }

        GeneXpert geneXpert = dataRequest.getGeneXpert();
        if(geneXpert != null) {
            geneXpert.setDate(date);
            geneXpert.setPtInfoId(ptInfoId);
            geneXpert.setMxId(mxId);
            geneXpertDao.save(geneXpert);
        }

        Hain hain = dataRequest.getHain();
        if(hain != null){
            hain.setDate(date);
            hain.setPtInfoId(ptInfoId);
            hain.setMxId(mxId);
            hainDao.save(hain);
        }

//        HivTest hivTest = dataRequest.getHivTest();
//        if(hivTest != null){
//            hivTest.setDate(date);
//            hivTest.setPtInfoId(ptInfoId);
//            hivTest.setMxId(mxId);
//            hivTestDao.save(hivTest);
//        }

        SputumCulture sputumCulture = dataRequest.getSputumCulture();
        if(sputumCulture != null) {
            sputumCulture.setDate(date);
            sputumCulture.setPtInfoId(ptInfoId);
            sputumCulture.setMxId(mxId);
            sputumCultureDao.save(sputumCulture);
        }

        SputumSmear sputumSmear = dataRequest.getSputumSmear();
        if(sputumSmear != null) {
            sputumSmear.setDate(date);
            sputumSmear.setPtInfoId(ptInfoId);
            sputumSmear.setMxId(mxId);
            sputumSmearDao.save(sputumSmear);
        }

        if(therapy != null) {
            therapy.setDate(date);
            therapy.setPtInfoId(ptInfoId);
            therapy.setMxId(mxId);
            therapyDao.save(therapy);
        }

        LiverFunc liverFunc = dataRequest.getLiverFunc();
        if(liverFunc != null){
            liverFunc.setDate(date);
            liverFunc.setPtInfoId(ptInfoId);
            liverFunc.setMxId(mxId);
            liverFuncDao.save(liverFunc);
        }
        RenalFunc renalFunc = dataRequest.getRenalFunc();
        if(renalFunc != null){
            renalFunc.setDate(date);
            renalFunc.setPtInfoId(ptInfoId);
            renalFunc.setMxId(mxId);
            renalFuncDao.save(renalFunc);
        }
        UrineRoutine urineRoutine = dataRequest.getUrineRoutine();
        if(urineRoutine != null){
            urineRoutine.setDate(date);
            urineRoutine.setPtInfoId(ptInfoId);
            urineRoutine.setMxId(mxId);
            urineRoutineDao.save(urineRoutine);
        }
        BloodRoutine bloodRoutine = dataRequest.getBloodRoutine();
        if(bloodRoutine != null) {
            bloodRoutine.setDate(date);
            bloodRoutine.setPtInfoId(ptInfoId);
            bloodRoutine.setMxId(mxId);
            bloodRoutineDao.save(bloodRoutine);
        }
        PhysicalExam physicalExam = dataRequest.getPhysicalExam();
        if(physicalExam != null) {
            physicalExam.setDate(date);
            physicalExam.setPtInfoId(ptInfoId);
            physicalExam.setMxId(mxId);
            physicalExamDao.save(physicalExam);
        }
        Immunology immunology = dataRequest.getImmunology();
        if(immunology != null) {
            immunology.setDate(date);
            immunology.setPtInfoId(ptInfoId);
            immunology.setMxId(mxId);
            immunologyDao.save(immunology);
        }
        InitialCheck initialCheck = dataRequest.getInitialCheck();
        if(initialCheck != null) {
            initialCheck.setDate(date);
            initialCheck.setPtInfoId(ptInfoId);
            initialCheck.setMxId(mxId);
            initialCheckDao.save(initialCheck);
        }
        BacterialType bacterialType = dataRequest.getBacterialType();
        if(bacterialType != null) {
            bacterialType.setDate(date);
            bacterialType.setPtInfoId(ptInfoId);
            bacterialType.setMxId(mxId);
            bacterialTypeDao.save(bacterialType);
        }
        
        BiochemicalDetection bd = dataRequest.getBiochemicalDetection();
        if(bd != null) {
        	bd.setDate(date);
        	bd.setPtInfoId(ptInfoId);
        	bd.setMxId(mxId);
        	biochemicaldetectionDao.save(bd);
        }
        
        Assessment assessment = dataRequest.getAssessment();
        if(assessment != null) {
        	assessment.setDate(date);
        	assessment.setPtInfoId(ptInfoId);
        	assessment.setMxId(mxId);
        	assessMentDao.save(assessment);
        }
        
        //----------------新增不良反应 保存
//        Effect effect = dataRequest.getEffect();
//        if(effect != null) {
//            effectDao.save(effect);
//        }
    }

    @Transactional(readOnly = false)
    public void edit(DataEditRequest dataEditRequest) throws AuthorizationException {
        Long ptInfoId = dataEditRequest.getPtInfoId();
        long mxId = dataEditRequest.getMxId();
        Date date = dataEditRequest.getDate();
        Therapy therapy = dataEditRequest.getTherapy();
        TbDiagnosis tbDiagnosis = dataEditRequest.getTbDiagnosis();

        if(therapy != null || tbDiagnosis != null) {
            MxData mxData = mxDataDao.findOne(mxId);
            if(therapy != null) {
                mxData.setTherapy(therapy.getTherapyStatus());
                mxData.setCurrTherapy(therapy.getCurrTherapy());
            }
            if(tbDiagnosis != null) {
                mxData.setTbDiagnosis(tbDiagnosis.getTbDiagnosis());
            }
            if(date != null) {
                mxData.setDate(date);
            }
            mxData.setIsdosage(dataEditRequest.getIsdosage());
            mxDataDao.save(mxData);
        }
        if(tbDiagnosis != null) {
            if(tbDiagnosis.getId() == null || tbDiagnosis.getId() <= 0) {
                tbDiagnosis.setDate(date);
                tbDiagnosis.setPtInfoId(ptInfoId);
                tbDiagnosis.setMxId(mxId);
            }
            if(date != null) tbDiagnosis.setDate(date);
            tbDiagnosisDao.save(tbDiagnosis);
        }
        TransInst transInst = dataEditRequest.getTransInst();
        if(transInst != null){
            if(transInst.getId() == null || transInst.getId() <= 0) {
                transInst.setDate(date);
                transInst.setPtInfoId(ptInfoId);
                transInst.setMxId(mxId);
            }
            if(date != null) transInst.setDate(date);
            transInstDao.save(transInst);
        }

        ChestRadioGraph chestRadioGraph = dataEditRequest.getChestRadioGraph();
        if(chestRadioGraph != null) {
            if(chestRadioGraph.getId() == null || chestRadioGraph.getId() <= 0){
                chestRadioGraph.setDate(date);
                chestRadioGraph.setPtInfoId(ptInfoId);
                chestRadioGraph.setMxId(mxId);
            }
            if(date != null) chestRadioGraph.setDate(date);
            chestRadioGraphDao.save(chestRadioGraph);
        }

        DrugScpt drugScpt = dataEditRequest.getDrugScpt();
        if(drugScpt != null) {
            if(drugScpt.getId() == null || drugScpt.getId() <= 0){
                drugScpt.setDate(date);
                drugScpt.setPtInfoId(ptInfoId);
                drugScpt.setMxId(mxId);
            }
            if(date != null) drugScpt.setDate(date);
            drugScptDao.save(drugScpt);
        }

        GeneChip geneChip = dataEditRequest.getGeneChip();
        if(geneChip != null) {
            if(geneChip.getId() == null || geneChip.getId() <= 0){
                geneChip.setDate(date);
                geneChip.setPtInfoId(ptInfoId);
                geneChip.setMxId(mxId);
            }
            if(date != null) geneChip.setDate(date);
            geneChipDao.save(geneChip);
        }

        GeneXpert geneXpert = dataEditRequest.getGeneXpert();
        if(geneXpert != null) {
            if(geneXpert.getId() == null || geneXpert.getId() <= 0){
                geneXpert.setDate(date);
                geneXpert.setPtInfoId(ptInfoId);
                geneXpert.setMxId(mxId);
            }
            if(date != null) geneXpert.setDate(date);
            geneXpertDao.save(geneXpert);
        }

        Hain hain = dataEditRequest.getHain();
        if(hain != null){
            if(hain.getId() == null || hain.getId() <= 0){
                hain.setDate(date);
                hain.setPtInfoId(ptInfoId);
                hain.setMxId(mxId);
            }
            if(date != null) hain.setDate(date);
            hainDao.save(hain);
        }

        SputumCulture sputumCulture = dataEditRequest.getSputumCulture();
        if(sputumCulture != null) {
            if(sputumCulture.getId() == null || sputumCulture.getId() <= 0) {
                sputumCulture.setDate(date);
                sputumCulture.setPtInfoId(ptInfoId);
                sputumCulture.setMxId(mxId);
            }
            if(date != null) sputumCulture.setDate(date);
            sputumCultureDao.save(sputumCulture);
        }

        SputumSmear sputumSmear = dataEditRequest.getSputumSmear();
        if(sputumSmear != null) {
            if(sputumSmear.getId() == null || sputumSmear.getId() <= 0) {
                sputumSmear.setDate(date);
                sputumSmear.setPtInfoId(ptInfoId);
                sputumSmear.setMxId(mxId);
            }
            if(date != null) sputumSmear.setDate(date);
            sputumSmearDao.save(sputumSmear);
        }

        if(therapy != null) {
            if(therapy.getId() == null || therapy.getId() <= 0) {
                therapy.setDate(date);
                therapy.setPtInfoId(ptInfoId);
                therapy.setMxId(mxId);
            }
            if(date != null) therapy.setDate(date);
            therapyDao.save(therapy);
        }

        LiverFunc liverFunc = dataEditRequest.getLiverFunc();
        if(liverFunc != null){
            if(liverFunc.getId() == null || liverFunc.getId() <= 0){
                liverFunc.setDate(date);
                liverFunc.setPtInfoId(ptInfoId);
                liverFunc.setMxId(mxId);
            }
            if(date != null) liverFunc.setDate(date);
            liverFuncDao.save(liverFunc);
        }

        RenalFunc renalFunc = dataEditRequest.getRenalFunc();
        if(renalFunc != null){
            if(renalFunc.getId() != null && renalFunc.getId() <= 0) {
                renalFunc.setDate(date);
                renalFunc.setPtInfoId(ptInfoId);
                renalFunc.setMxId(mxId);
            }
            if(date != null) renalFunc.setDate(date);
            renalFuncDao.save(renalFunc);
        }

        UrineRoutine urineRoutine = dataEditRequest.getUrineRoutine();
        if(urineRoutine != null){
            if(urineRoutine.getId() == null || urineRoutine.getId() <= 0){
                urineRoutine.setDate(date);
                urineRoutine.setPtInfoId(ptInfoId);
                urineRoutine.setMxId(mxId);
            }
            if(date != null) urineRoutine.setDate(date);
            urineRoutineDao.save(urineRoutine);
        }

        BloodRoutine bloodRoutine = dataEditRequest.getBloodRoutine();
        if(bloodRoutine != null) {
            if(bloodRoutine.getId() == null || bloodRoutine.getId() <= 0){
                bloodRoutine.setDate(date);
                bloodRoutine.setPtInfoId(ptInfoId);
                bloodRoutine.setMxId(mxId);
            }
            if(date != null) bloodRoutine.setDate(date);
            bloodRoutineDao.save(bloodRoutine);
        }

        PhysicalExam physicalExam = dataEditRequest.getPhysicalExam();
        if(physicalExam != null) {
            if(physicalExam.getId() == null || physicalExam.getId() <= 0){
                physicalExam.setDate(date);
                physicalExam.setPtInfoId(ptInfoId);
                physicalExam.setMxId(mxId);
            }
            if(date != null) physicalExam.setDate(date);
            physicalExamDao.save(physicalExam);
        }

        Immunology immunology = dataEditRequest.getImmunology();
        if(immunology != null) {
            if(immunology.getId() == null || immunology.getId() <= 0){
                immunology.setDate(date);
                immunology.setPtInfoId(ptInfoId);
                immunology.setMxId(mxId);
            }
            if(date != null) immunology.setDate(date);
            immunologyDao.save(immunology);
        }

        InitialCheck initialCheck = dataEditRequest.getInitialCheck();
        if(initialCheck != null) {
            if(initialCheck.getId() == null || initialCheck.getId() <= 0) {
                initialCheck.setDate(date);
                initialCheck.setPtInfoId(ptInfoId);
                initialCheck.setMxId(mxId);
            }
            if(date != null) initialCheck.setDate(date);
            initialCheckDao.save(initialCheck);
        }

        BacterialType bacterialType = dataEditRequest.getBacterialType();
        if(bacterialType != null) {
            if(bacterialType.getId() == null || bacterialType.getId() <= 0) {
                bacterialType.setDate(date);
                bacterialType.setPtInfoId(ptInfoId);
                bacterialType.setMxId(mxId);
            }
            if(date != null) bacterialType.setDate(date);
            bacterialTypeDao.save(bacterialType);
        }
        
        BiochemicalDetection biochemicalDetection = dataEditRequest.getBiochemicalDetection();
        if(biochemicalDetection != null) {
            if(biochemicalDetection.getId() == null || biochemicalDetection.getId() <= 0) {
            	biochemicalDetection.setDate(date);
            	biochemicalDetection.setPtInfoId(ptInfoId);
            	biochemicalDetection.setMxId(mxId);
            }
            if(date != null) biochemicalDetection.setDate(date);
            biochemicaldetectionDao.save(biochemicalDetection);
        }
        
        Assessment assessment = dataEditRequest.getAssessment();
        if(assessment != null) {
            if(assessment.getId() == null || assessment.getId() <= 0) {
            	assessment.setDate(date);
            	assessment.setPtInfoId(ptInfoId);
            	assessment.setMxId(mxId);
            }
            if(date != null) assessment.setDate(date);
            assessMentDao.save(assessment);
        }
    }

    @Transactional(readOnly = false)
    public void delete(Long mxDataId, Long ptInfoId) throws AuthorizationException {
        transInstDao.deleteMxData(mxDataId, ptInfoId);
        chestRadioGraphDao.deleteMxData(mxDataId, ptInfoId);
        geneXpertDao.deleteMxData(mxDataId, ptInfoId);
        sputumSmearDao.deleteMxData(mxDataId, ptInfoId);
        sputumCultureDao.deleteMxData(mxDataId, ptInfoId);
        drugScptDao.deleteMxData(mxDataId, ptInfoId);
        hainDao.deleteMxData(mxDataId, ptInfoId);
        geneChipDao.deleteMxData(mxDataId, ptInfoId);
        tbDiagnosisDao.deleteMxData(mxDataId, ptInfoId);
        therapyDao.deleteMxData(mxDataId, ptInfoId);
        liverFuncDao.deleteMxData(mxDataId, ptInfoId);
        renalFuncDao.deleteMxData(mxDataId, ptInfoId);
        urineRoutineDao.deleteMxData(mxDataId, ptInfoId);
        bloodRoutineDao.deleteMxData(mxDataId, ptInfoId);
        physicalExamDao.deleteMxData(mxDataId, ptInfoId);
        immunologyDao.deleteMxData(mxDataId, ptInfoId);
        initialCheckDao.deleteMxData(mxDataId, ptInfoId);
        bacterialTypeDao.deleteMxData(mxDataId, ptInfoId);

        mxDataDao.deleteMxData(mxDataId, ptInfoId);
    }

    @Transactional(readOnly = false)
    public void deletePtInfo(Long ptInfoId) throws AuthorizationException {
        transInstDao.deleteMxDataPtInfo(ptInfoId);
        chestRadioGraphDao.deleteMxDataPtInfo(ptInfoId);
        geneXpertDao.deleteMxDataPtInfo(ptInfoId);
        sputumSmearDao.deleteMxDataPtInfo(ptInfoId);
        sputumCultureDao.deleteMxDataPtInfo(ptInfoId);
        drugScptDao.deleteMxDataPtInfo(ptInfoId);
        hainDao.deleteMxDataPtInfo(ptInfoId);
        geneChipDao.deleteMxDataPtInfo(ptInfoId);
        tbDiagnosisDao.deleteMxDataPtInfo(ptInfoId);
        therapyDao.deleteMxDataPtInfo(ptInfoId);
        liverFuncDao.deleteMxDataPtInfo(ptInfoId);
        renalFuncDao.deleteMxDataPtInfo(ptInfoId);
        urineRoutineDao.deleteMxDataPtInfo(ptInfoId);
        bloodRoutineDao.deleteMxDataPtInfo(ptInfoId);
        physicalExamDao.deleteMxDataPtInfo(ptInfoId);
        immunologyDao.deleteMxDataPtInfo(ptInfoId);
        initialCheckDao.deleteMxDataPtInfo(ptInfoId);
        bacterialTypeDao.deleteMxDataPtInfo(ptInfoId);

        mxDataDao.deleteMxDataPtInfo(ptInfoId);
    }

    @Transactional(readOnly = false)
    public void deleteTransInst(Long id) throws AuthorizationException {
        transInstDao.delete(id);
    }

    @Transactional(readOnly = false)
    public void deleteChestRadioGraph(Long id) throws AuthorizationException {
        chestRadioGraphDao.delete(id);
    }

    @Transactional(readOnly = false)
    public void deleteGeneXpert(Long id) throws AuthorizationException {
        geneXpertDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteSputumSmear(Long id) throws AuthorizationException {
        sputumSmearDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteSputumCulture(Long id) throws AuthorizationException {
        sputumCultureDao.delete(id);
    }
    @Transactional(readOnly = false)
     public void deleteDrugScpt(Long id) throws AuthorizationException {
        drugScptDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteHain(Long id) throws AuthorizationException {
        hainDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteGeneChip(Long id) throws AuthorizationException {
        geneChipDao.delete(id);
    }
    @Transactional(readOnly = false)
     public void deleteTbDiagnosis(Long id) throws AuthorizationException {
        tbDiagnosisDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteTherapy(Long id) throws AuthorizationException {
        Therapy therapy = therapyDao.findOne(id);
        mxDataDao.updateTherapy(CommonContants.MX_DATA_NO_THERAPY, therapy.getMxId());
        therapyDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteLiverFunc(Long id) throws AuthorizationException {
        liverFuncDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteRenalFunc(Long id) throws AuthorizationException {
        renalFuncDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteUrineRoutine(Long id) throws AuthorizationException {
        urineRoutineDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteBloodRoutine(Long id) throws AuthorizationException {
        bloodRoutineDao.delete(id);
    }
    @Transactional(readOnly = false)
     public void deletePhysicalExam(Long id) throws AuthorizationException {
        physicalExamDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteImmunology(Long id) throws AuthorizationException {
        immunologyDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteInitialCheck(Long id) throws AuthorizationException {
        initialCheckDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void deleteBacterialType(Long id) throws AuthorizationException {
        bacterialTypeDao.delete(id);
    }
    
    @Transactional(readOnly = false)
    public void deleteBiochemicalDetection(Long id) throws AuthorizationException {
        biochemicaldetectionDao.delete(id);
    }
    
    @Transactional(readOnly = false)
    public void deleteAssessment(Long id) throws AuthorizationException {
    	assessMentDao.delete(id);
    }

//    public MxData searchRangeMxDataByDate(Date date, long ptInfoId) {
//        Date findBeginDate = DateUtil.monthOffset(date, -1);
//        Date findEndDate = DateUtil.monthOffset(date, 1);
//        MxData settingMxData = null;
//        List<MxData> mxDataList = mxDataDao.searchMxDataByDate(ptInfoId, CommonContants.MX_DATA_TYPE_PRE, findBeginDate, findEndDate);
//        if (mxDataList != null && !mxDataList.isEmpty()) {
//            for (MxData m : mxDataList) {
//                Date beginDate = DateUtil.dateOffset(m.getDate(), -14);
//                Date endDate = DateUtil.dateOffset(DateUtil.monthOffset(m.getDate(), 1), -14);
//                if ((date.after(beginDate) || date.equals(beginDate)) && date.before(endDate)) {
//                    settingMxData = m;
//                    break;
//                }
//            }
//        }
//
//        return settingMxData;
//    }

    public List<MxData> searchVisitMxDataInDate(Date endDate) {
        return mxDataDao.searchVisitMxDataInDate(endDate);
    }

//    private Long generalMx(Date date, long ptInfoId,int tbDiagnosis) {
//        Long mxId = CommonContants.BEFORE_TREAT;
//        TbDiagnosis tb = tbDiagnosisDao.findTop1ByPtInfoIdAndTherapyStatusOrOrderByDateDesc(ptInfoId);
//        if(tb == null || tbDiagnosis != tb.getTbDiagnosis()) {
//            if(tb != null && tbDiagnosis != tb.getTbDiagnosis()) {
//                // update statue to del  有可能会有删除时没有删除间隙的Mx的问题
//                mxDataDao.delMxData(ptInfoId, date, CommonContants.MX_DATA_TYPE_PRE, CommonContants.MX_DATA_STATUS_COMMON);
//            }
//            DiagnosisSetting setting = diagnosisSettingDao.findDiagnosisSettingByDiagnosisType(tbDiagnosis);
//            // Mx
//            if(setting != null) {
////                if(StringUtils.isNotBlank(setting.getM0())) {
//                MxData m = saveMxData("M0", date, 0, tbDiagnosis, ptInfoId);
//                mxId = m.getId();
////                }
//
////                if(StringUtils.isNotBlank(setting.getM1())) {
//                saveMxData("M1", date, 1, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM2())) {
//                saveMxData("M2", date, 2, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM3())) {
//                saveMxData("M3", date, 3, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM4())) {
//                saveMxData("M4", date, 4, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM5())) {
//                saveMxData("M5", date, 5, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM6())) {
//                saveMxData("M6", date, 6, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM7())) {
//                saveMxData("M7", date, 7, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM8())) {
//                saveMxData("M8", date, 8, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM9())) {
//                saveMxData("M9", date, 9, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM10())) {
//                saveMxData("M10", date, 10, tbDiagnosis, ptInfoId);
////                }
//
////                if(StringUtils.isNotBlank(setting.getM11())) {
//                saveMxData("M11", date, 11, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM12())) {
//                saveMxData("M12", date, 12, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM13())) {
//                saveMxData("M13", date, 13, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM14())) {
//                saveMxData("M14", date, 14, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM15())) {
//                saveMxData("M15", date, 15, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM16())) {
//                saveMxData("M16", date, 16, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM17())) {
//                saveMxData("M17", date, 17, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM18())) {
//                saveMxData("M18", date, 18, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM19())) {
//                saveMxData("M19", date, 19, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM20())) {
//                saveMxData("M20", date, 20, tbDiagnosis, ptInfoId);
////                }
//
////                if(StringUtils.isNotBlank(setting.getM21())) {
//                saveMxData("M21", date, 21, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM22())) {
//                saveMxData("M22", date, 22, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM23())) {
//                saveMxData("M23", date, 23, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM24())) {
//                saveMxData("M24", date, 24, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM25())) {
//                saveMxData("M25", date, 25, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM26())) {
//                saveMxData("M26", date, 26, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM27())) {
//                saveMxData("M27", date, 27, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM28())) {
//                saveMxData("M28", date, 28, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM29())) {
//                saveMxData("M29", date, 29, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM30())) {
//                saveMxData("M30", date, 30, tbDiagnosis, ptInfoId);
////                }
//
////                if(StringUtils.isNotBlank(setting.getM31())) {
//                saveMxData("M31", date, 31, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM32())) {
//                saveMxData("M32", date, 32, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM33())) {
//                saveMxData("M33", date, 33, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM34())) {
//                saveMxData("M34", date, 34, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM35())) {
//                saveMxData("M35", date, 35, tbDiagnosis, ptInfoId);
////                }
////                if(StringUtils.isNotBlank(setting.getM36())) {
//                saveMxData("M36", date, 36, tbDiagnosis, ptInfoId);
////                }
//            }
//        } else {
//            mxId = rangeMxDataByDate(date, ptInfoId, tb.getTbDiagnosis()).getId();
//        }
//        return mxId;
//    }

//    private MxData rangeMxDataByDate(Date date, long ptInfoId, int tbDiagnosis) {
//        // find mxData by date , del then add new mxData
//        Date findBeginDate = DateUtil.monthOffset(date, -1);
//        Date findEndDate = DateUtil.monthOffset(date, 1);
//        MxData mxDataTmp;
//
//        List<MxData> mxDataList = mxDataDao.searchMxDataByDate(ptInfoId, CommonContants.MX_DATA_TYPE_PRE, findBeginDate,findEndDate);
//        if(mxDataList != null && !mxDataList.isEmpty()) {
//            MxData settingMxData = null;
//            for(MxData m:mxDataList) {
//                Date beginDate = DateUtil.dateOffset(m.getDate(), -14);
//                Date endDate = DateUtil.dateOffset(DateUtil.monthOffset(m.getDate(), 1), -14);
//                if((date.after(beginDate) || date.equals(beginDate)) && date.before(endDate)) {
//                    settingMxData = m;
//                    break;
//                }
//            }
//            if(settingMxData != null) {
//                MxData mxData = new MxData();
//                mxData.setMx(settingMxData.getMx());
//                mxData.setDate(date);
//                mxData.setTbDiagnosis(tbDiagnosis);
//                mxData.setPtInfoId(ptInfoId);
//                mxData.setType(CommonContants.MX_DATA_TYPE_SAVE);
//                mxData.setStatus(CommonContants.MX_DATA_STATUS_COMMON);
//                mxDataTmp = mxDataDao.save(mxData);
//
//                settingMxData.setStatus(CommonContants.MX_DATA_STATUS_DEL);
//                mxDataDao.save(settingMxData);
//            } else{
//                // 如果日期不在现有的Mx范围内
//                mxDataTmp = saveBeforeTreatMxData(date, ptInfoId);
//            }
//
//        } else{
//            //TODO  只是为了纠错，一般操作不会到这   如果日期不在现有的Mx范围内
//            mxDataTmp = saveBeforeTreatMxData(date, ptInfoId);
//        }
//
//        return mxDataTmp;
//    }

//    private MxData saveBeforeTreatMxData(Date date, long ptInfoId) {
//        MxData mxData = new MxData();
//        mxData.setMx(CommonContants.BEFORE_TREAT_MX);
//        mxData.setDate(date);
//        mxData.setTbDiagnosis(CommonContants.BEFORE_TREAT_tbDiagnosis);
//        mxData.setPtInfoId(ptInfoId);
//        mxData.setType(CommonContants.MX_DATA_TYPE_SAVE);
//        mxData.setStatus(CommonContants.MX_DATA_STATUS_COMMON);
//        return mxDataDao.save(mxData);
//    }
//
//    private MxData saveMxData(String mx, Date date, int offSet, int tbDiagnosis, long ptInfoId) {
//        MxData mxData = new MxData();
//        mxData.setMx(mx);
//        mxData.setDate(DateUtil.monthOffset(date,offSet));
//        mxData.setTbDiagnosis(tbDiagnosis);
//        mxData.setPtInfoId(ptInfoId);
//        mxData.setType(CommonContants.MX_DATA_TYPE_PRE);
//        mxData.setStatus(CommonContants.MX_DATA_STATUS_COMMON);
//        return mxDataDao.save(mxData);
//    }

    public List<MxData> mxList(Long ptInfoId) {
        return mxDataDao.mxDataList(ptInfoId, CommonContants.MX_DATA_STATUS_COMMON);
    }

    public List<MxData> checkMxData(Long ptInfoId, Date date) {
        return mxDataDao.checkMxData(ptInfoId, date,  CommonContants.MX_DATA_STATUS_COMMON);
    }

    public MxData findTop1ByPtInfoIdOrderByDateDesc(Long ptInfoId) {
        return mxDataDao.findTop1ByPtInfoIdOrderByDateDesc(ptInfoId);
    }

    public TbDiagnosis findTbDiagnosisByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return tbDiagnosisDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public TransInst findTransInstByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return transInstDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public ChestRadioGraph findChestRadioGraphByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return chestRadioGraphDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

//    public DiabetesTest findDiabetesTestByPtInfoIdAndMxId(long ptInfoId, long mxId){
//        return diabetesTestDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
//    }

    public DrugScpt findDrugScptByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return drugScptDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public GeneChip findGeneChipByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return geneChipDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public GeneXpert findGeneXpertByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return geneXpertDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public Hain findHainByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return hainDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

//    public HivTest findHivTestByPtInfoIdAndMxId(long ptInfoId, long mxId){
//        return hivTestDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
//    }

    public List<SputumCulture> findAllSputumCultureOrderByDateAsc(long ptInfoId){
        return sputumCultureDao.findAllOrderByDateAsc(ptInfoId);
    }

    public List<SputumSmear> findAllSputumSmearOrderByDateAsc(long ptInfoId){
        return sputumSmearDao.findAllOrderByDateAsc(ptInfoId);
    }

    public SputumCulture findSputumCultureByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return sputumCultureDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public SputumSmear findSputumSmearByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return sputumSmearDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public Therapy findTherapyByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return therapyDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public LiverFunc findLiverFuncByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return liverFuncDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    public RenalFunc findRenalFuncByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return renalFuncDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    public UrineRoutine findUrineRoutineByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return urineRoutineDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    public BloodRoutine findBloodRoutineByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return bloodRoutineDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    public PhysicalExam findPhysicalExamByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return physicalExamDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    public Immunology findImmunologyByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return immunologyDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    public BacterialType findBacterialTypeByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return bacterialTypeDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }

    public InitialCheck findInitialCheckByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return initialCheckDao.findByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    
    public BiochemicalDetection findBiochemicalDetectionByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return biochemicaldetectionDao.findBiochemicalDetectionByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    
    public Assessment findAssessmentByPtInfoIdAndMxId(long ptInfoId, long mxId){
        return assessMentDao.findAssessMentDaoByPtInfoIdAndMxId(ptInfoId, mxId);
    }
    

    public ChestRadioGraph findChestRadioGraphTop1ByPtInfoId(long ptInfoId){
        return chestRadioGraphDao.findTop1ByPtInfoIdOrderByDateDesc(ptInfoId);
    }

    public ChestRadioGraph findChestRadioGraphFirstByPtInfoId(long ptInfoId){
        return chestRadioGraphDao.findTop1ByPtInfoIdOrderByDateAsc(ptInfoId);
    }

    public List<ChestRadioGraph> findChestRadioGraphTop2ByPtInfoId(long ptInfoId){
        return chestRadioGraphDao.findTop2ByPtInfoIdOrderByDateDesc(ptInfoId);
    }

    public TbDiagnosis findDbDiagnosisTop1ByPtInfoId(long ptInfoId) {
        return tbDiagnosisDao.findTop1ByPtInfoIdOrderByDateDesc(ptInfoId);
    }

    public List<TbDiagnosis> findByPtInfoIdOrderByDateDesc(long ptInfoId) {
        return tbDiagnosisDao.findByPtInfoIdOrderByDateDesc(ptInfoId);
    }

    public Therapy findTherapyByPtInfoId(long ptInfoId) {
        return therapyDao.findTop1ByPtInfoIdAndTherapyStatusNotAndTherapyStatusNotOrderByDateDesc
                (ptInfoId, CommonContants.THEARAPY_STATUS_NOT, CommonContants.THEARAPY_STATUS_FOLLOW);
    }

    public Therapy findTherapyByPtInfoIdBeforeDate(long ptInfoId, Date date) {
        return therapyDao.findTop1ByPtInfoIdAndTherapyStatusNotAndTherapyStatusNotAndDateBeforeOrderByDateDesc
                (ptInfoId, CommonContants.THEARAPY_STATUS_NOT, CommonContants.THEARAPY_STATUS_FOLLOW, date);
    }

    public Therapy findTop1ByPtInfoIdAndTherapyStatusAndDateBeforeAndCurrTherapyNotNullOrderByDateDesc(long ptInfoId, Date date) {
        return therapyDao.findTop1ByPtInfoIdAndTherapyStatusAndDateBeforeAndCurrTherapyNotNullOrderByDateDesc(ptInfoId, 1, date);
    }

    public ChestRadioGraph findChestRadioGraphByPtInfoIdBeforeDate(long ptInfoId, Date date) {
        return chestRadioGraphDao.findTop1ByPtInfoIdAndDateBeforeOrderByDateDesc(ptInfoId, date);
    }

    public List<Hospital> findAllHospital(){
        return (List<Hospital>) hospitalDao.findAll();
    }

    public List<DrugScpt> findDrugScptOrderByDateAsc(Long ptInfoId){
        return drugScptDao.findAllOrderByDateAsc(ptInfoId);
    }

    public List<GeneXpert> findGeneXpertOrderByDateAsc(Long ptInfoId){
        return geneXpertDao.findAllOrderByDateAsc(ptInfoId);
    }

    public List<Hain> findHainOrderByDateAsc(Long ptInfoId){
        return hainDao.findAllOrderByDateAsc(ptInfoId);
    }

    public List<GeneChip> findGeneChipOrderByDateAsc(Long ptInfoId){
        return geneChipDao.findAllOrderByDateAsc(ptInfoId);
    }

    public MxData findTop1OrderByDateAsc(){
        return mxDataDao.findTopByOrderByDateAsc();
    }

    public MxData findTop1OrderByDateDesc(){
        return mxDataDao.findTopByOrderByDateDesc();
    }
}
