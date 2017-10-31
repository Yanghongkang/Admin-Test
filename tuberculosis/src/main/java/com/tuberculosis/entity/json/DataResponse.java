package com.tuberculosis.entity.json;

import com.tuberculosis.entity.data.*;

/**
 * @author Li Shaoqing
 */
public class DataResponse {

	private TransInst transInst;
	private ChestRadioGraph chestRadioGraph;
	private GeneXpert geneXpert;
	private SputumSmear sputumSmear;
	private SputumCulture sputumCulture;
	private DrugScpt drugScpt;
	private Hain hain;
	private GeneChip geneChip;
	// private HivTest hivTest;
	// private DiabetesTest diabetesTest;
	private TbDiagnosis tbDiagnosis;
	private Therapy therapy;
	private LiverFunc liverFunc;
	private RenalFunc renalFunc;
	private UrineRoutine urineRoutine;
	private BloodRoutine bloodRoutine;
	private PhysicalExam physicalExam;
	private Immunology Immunology;
	private InitialCheck initialCheck;
	private BacterialType bacterialType;

	private BiochemicalDetection biochemicalDetection;
	private Assessment assessment;

	public DataResponse() {
	}

	public BacterialType getBacterialType() {
		return bacterialType;
	}

	public void setBacterialType(BacterialType bacterialType) {
		this.bacterialType = bacterialType;
	}

	public TransInst getTransInst() {
		return transInst;
	}

	public Immunology getImmunology() {
		return Immunology;
	}

	public void setImmunology(Immunology immunology) {
		Immunology = immunology;
	}

	public void setTransInst(TransInst transInst) {
		this.transInst = transInst;
	}

	public ChestRadioGraph getChestRadioGraph() {
		return chestRadioGraph;
	}

	public void setChestRadioGraph(ChestRadioGraph chestRadioGraph) {
		this.chestRadioGraph = chestRadioGraph;
	}

	public GeneXpert getGeneXpert() {
		return geneXpert;
	}

	public void setGeneXpert(GeneXpert geneXpert) {
		this.geneXpert = geneXpert;
	}

	public SputumSmear getSputumSmear() {
		return sputumSmear;
	}

	public void setSputumSmear(SputumSmear sputumSmear) {
		this.sputumSmear = sputumSmear;
	}

	public SputumCulture getSputumCulture() {
		return sputumCulture;
	}

	public void setSputumCulture(SputumCulture sputumCulture) {
		this.sputumCulture = sputumCulture;
	}

	public DrugScpt getDrugScpt() {
		return drugScpt;
	}

	public void setDrugScpt(DrugScpt drugScpt) {
		this.drugScpt = drugScpt;
	}

	public Hain getHain() {
		return hain;
	}

	public void setHain(Hain hain) {
		this.hain = hain;
	}

	public GeneChip getGeneChip() {
		return geneChip;
	}

	public void setGeneChip(GeneChip geneChip) {
		this.geneChip = geneChip;
	}

	// public HivTest getHivTest() {
	// return hivTest;
	// }
	//
	// public void setHivTest(HivTest hivTest) {
	// this.hivTest = hivTest;
	// }

	// public DiabetesTest getDiabetesTest() {
	// return diabetesTest;
	// }
	//
	// public void setDiabetesTest(DiabetesTest diabetesTest) {
	// this.diabetesTest = diabetesTest;
	// }

	public LiverFunc getLiverFunc() {
		return liverFunc;
	}

	public void setLiverFunc(LiverFunc liverFunc) {
		this.liverFunc = liverFunc;
	}

	public RenalFunc getRenalFunc() {
		return renalFunc;
	}

	public void setRenalFunc(RenalFunc renalFunc) {
		this.renalFunc = renalFunc;
	}

	public UrineRoutine getUrineRoutine() {
		return urineRoutine;
	}

	public void setUrineRoutine(UrineRoutine urineRoutine) {
		this.urineRoutine = urineRoutine;
	}

	public BloodRoutine getBloodRoutine() {
		return bloodRoutine;
	}

	public void setBloodRoutine(BloodRoutine bloodRoutine) {
		this.bloodRoutine = bloodRoutine;
	}

	public PhysicalExam getPhysicalExam() {
		return physicalExam;
	}

	public void setPhysicalExam(PhysicalExam physicalExam) {
		this.physicalExam = physicalExam;
	}

	public TbDiagnosis getTbDiagnosis() {
		return tbDiagnosis;
	}

	public void setTbDiagnosis(TbDiagnosis tbDiagnosis) {
		this.tbDiagnosis = tbDiagnosis;
	}

	public Therapy getTherapy() {
		return therapy;
	}

	public void setTherapy(Therapy therapy) {
		this.therapy = therapy;
	}

	public InitialCheck getInitialCheck() {
		return initialCheck;
	}

	public void setInitialCheck(InitialCheck initialCheck) {
		this.initialCheck = initialCheck;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public BiochemicalDetection getBiochemicalDetection() {
		return biochemicalDetection;
	}

	public void setBiochemicalDetection(BiochemicalDetection biochemicalDetection) {
		this.biochemicalDetection = biochemicalDetection;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	 private int isdosage;//0 提示     1 不提示
	    
	    

		public int getIsdosage() {
			return isdosage;
		}

		public void setIsdosage(int isdosage) {
			this.isdosage = isdosage;
		}

}