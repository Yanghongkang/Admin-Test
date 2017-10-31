package com.tuberculosis.web.rest;

import com.tuberculosis.auth.Authentication;
import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.DiagnosisSetting;
import com.tuberculosis.entity.MxData;
import com.tuberculosis.entity.PtInfo;
import com.tuberculosis.entity.data.BloodRoutine;
import com.tuberculosis.entity.data.ChestRadioGraph;
import com.tuberculosis.entity.data.DrugScpt;
import com.tuberculosis.entity.data.GeneChip;
import com.tuberculosis.entity.data.GeneXpert;
import com.tuberculosis.entity.data.Hain;
import com.tuberculosis.entity.data.Immunology;
import com.tuberculosis.entity.data.LiverFunc;
import com.tuberculosis.entity.data.PhysicalExam;
import com.tuberculosis.entity.data.RenalFunc;
import com.tuberculosis.entity.data.SputumCulture;
import com.tuberculosis.entity.data.SputumSmear;
import com.tuberculosis.entity.data.TbDiagnosis;
import com.tuberculosis.entity.data.Therapy;
import com.tuberculosis.entity.data.UrineRoutine;
import com.tuberculosis.entity.json.AscribeReportResponse;
import com.tuberculosis.entity.json.AscribeResponse;
import com.tuberculosis.entity.json.ChestImgResponse;
import com.tuberculosis.entity.json.CommonResponse;
import com.tuberculosis.entity.json.CreateSearchResponse;
import com.tuberculosis.entity.json.DataEditRequest;
import com.tuberculosis.entity.json.DataRequest;
import com.tuberculosis.entity.json.DataResponse;
import com.tuberculosis.entity.json.HospitalResponse;
import com.tuberculosis.entity.json.MxDataForList;
import com.tuberculosis.entity.json.MxDataForListResponse;
import com.tuberculosis.entity.json.MxDataReport;
import com.tuberculosis.entity.json.MxDataReportResponse;
import com.tuberculosis.entity.json.RecentlyChestResponse;
import com.tuberculosis.entity.json.RecentlyTherapyResponse;
import com.tuberculosis.service.data.DataService;
import com.tuberculosis.service.setting.DiagnosisSettingService;
import com.tuberculosis.service.setting.PtInfoService;
import com.tuberculosis.util.DateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/rest/data" })
public class DataAPIController {
	@Autowired
	private DataService dataService;
	@Autowired
	private PtInfoService ptInfoService;
	@Autowired
	private DiagnosisSettingService diagnosisSettingService;

	@RequestMapping(value = { "create" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@Authentication
	@ResponseBody
	public CommonResponse create(@RequestBody DataRequest dataRequest) {
		this.dataService.save(dataRequest);
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "createSearch" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CreateSearchResponse createSearch(@RequestParam("ptInfoId") long ptInfoId,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		CreateSearchResponse response = new CreateSearchResponse();

		Therapy therapy = this.dataService.findTherapyByPtInfoId(ptInfoId);
		if (therapy != null) {
			response.setCurrTherapy(therapy.getCurrTherapy());
			response.setAdjReason(therapy.getAdjReason());
			response.setStopReason(therapy.getStopReason());
			response.setTherapy(therapy.getTherapy());
			response.setTherapyStatus(therapy.getTherapyStatus());
		}
		Therapy recentlyTherapy = this.dataService.findTherapyByPtInfoIdBeforeDate(ptInfoId, date);
		if (recentlyTherapy != null) {
			response.setRecentlyCurrTherapy(recentlyTherapy.getCurrTherapy());
			response.setRecentlyAdjReason(recentlyTherapy.getAdjReason());
			response.setRecentlyStopReason(recentlyTherapy.getStopReason());
			response.setRecentlyTherapy(recentlyTherapy.getTherapy());
			response.setRecentlyTherapyStatus(recentlyTherapy.getTherapyStatus());
		}
		Therapy tt = this.dataService
				.findTop1ByPtInfoIdAndTherapyStatusAndDateBeforeAndCurrTherapyNotNullOrderByDateDesc(ptInfoId, date);
		if (tt != null) {
			response.setHasTherapy(1);
		}
		TbDiagnosis tbDiagnosis = this.dataService.findDbDiagnosisTop1ByPtInfoId(ptInfoId);
		if (tbDiagnosis != null) {
			response.setTbDiagnosis(tbDiagnosis.getTbDiagnosis());
		}
		List<MxData> mxData = this.dataService.checkMxData(Long.valueOf(ptInfoId), date);
		if ((mxData != null) && (!mxData.isEmpty())) {
			response.setHasData(1);
		}
		List<MxData> mxDataList = this.dataService.mxList(Long.valueOf(ptInfoId));
		if ((mxDataList != null) && (!mxDataList.isEmpty())) {
			response.setHasInit(1);
		}
		ChestRadioGraph chestRadioGraph = this.dataService.findChestRadioGraphTop1ByPtInfoId(ptInfoId);
		if (chestRadioGraph != null) {
			response.setChestResult(chestRadioGraph.getCardResult());
		}
		ChestRadioGraph chestRadioGraphFirst = this.dataService.findChestRadioGraphFirstByPtInfoId(ptInfoId);
		if (chestRadioGraphFirst != null) {
			response.setFirstChestResult(chestRadioGraphFirst.getCardResult());
		}
		return response;
	}

	@RequestMapping(value = { "recentlyTherapy" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public RecentlyTherapyResponse recentlyTherapy(@RequestParam("ptInfoId") long ptInfoId,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		RecentlyTherapyResponse response = new RecentlyTherapyResponse();

		Therapy recentlyTherapy = this.dataService.findTherapyByPtInfoIdBeforeDate(ptInfoId, date);
		if (recentlyTherapy != null) {
			response.setRecentlyCurrTherapy(recentlyTherapy.getCurrTherapy());
			response.setRecentlyAdjReason(recentlyTherapy.getAdjReason());
			response.setRecentlyStopReason(recentlyTherapy.getStopReason());
			response.setRecentlyTherapy(recentlyTherapy.getTherapy());
			response.setRecentlyTherapyStatus(recentlyTherapy.getTherapyStatus());
		}
		return response;
	}

	@RequestMapping(value = { "recentlyChest" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public RecentlyChestResponse recentlyChest(@RequestParam("ptInfoId") long ptInfoId,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		RecentlyChestResponse response = new RecentlyChestResponse();

		ChestRadioGraph recentlyChest = this.dataService.findChestRadioGraphByPtInfoIdBeforeDate(ptInfoId, date);
		if (recentlyChest != null) {
			response.setByo(recentlyChest.getByo());
			response.setByoDate(recentlyChest.getByoDate());
			response.setCardResult(recentlyChest.getCardResult());
			response.setCured(recentlyChest.getCured());
			response.setImage(recentlyChest.getImage());
			response.setLastImage(recentlyChest.getLastImage());
			response.setResultStr(recentlyChest.getResultStr());
		}
		return response;
	}

	@RequestMapping(value = { "edit" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@Authentication
	@ResponseBody
	public CommonResponse edit(@RequestBody DataEditRequest dataEditRequest) {
		this.dataService.edit(dataEditRequest);
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@Authentication
	@ResponseBody
	public CommonResponse delete(@RequestParam("ptInfoId") long ptInfoId, @RequestParam("mxDataId") long mxDataId) {
		this.dataService.delete(Long.valueOf(mxDataId), Long.valueOf(ptInfoId));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/transInst" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteTransInst(@RequestParam("id") long id) {
		this.dataService.deleteTransInst(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/chestRadioGraph" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteChestRadioGraph(@RequestParam("id") long id) {
		this.dataService.deleteChestRadioGraph(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/geneXpert" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteGeneXpert(@RequestParam("id") long id) {
		this.dataService.deleteGeneXpert(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/sputumSmear" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteSputumSmear(@RequestParam("id") long id) {
		this.dataService.deleteSputumSmear(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/sputumCulture" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteSputumCulture(@RequestParam("id") long id) {
		this.dataService.deleteSputumCulture(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/drugScpt" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteDrugScpt(@RequestParam("id") long id) {
		this.dataService.deleteDrugScpt(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/hain" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteHain(@RequestParam("id") long id) {
		this.dataService.deleteHain(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/geneChip" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteGeneChip(@RequestParam("id") long id) {
		this.dataService.deleteGeneChip(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/tbDiagnosis" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteTbDiagnosis(@RequestParam("id") long id) {
		this.dataService.deleteTbDiagnosis(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/therapy" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteTherapy(@RequestParam("id") long id) {
		this.dataService.deleteTherapy(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/liverFunc" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteLiverFunc(@RequestParam("id") long id) {
		this.dataService.deleteLiverFunc(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/renalFunc" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteRenalFunc(@RequestParam("id") long id) {
		this.dataService.deleteRenalFunc(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/urineRoutine" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteUrineRoutine(@RequestParam("id") long id) {
		this.dataService.deleteUrineRoutine(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/bloodRoutine" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteBloodRoutine(@RequestParam("id") long id) {
		this.dataService.deleteBloodRoutine(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/physicalExam" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deletePhysicalExam(@RequestParam("id") long id) {
		this.dataService.deletePhysicalExam(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/immunology" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteImmunology(@RequestParam("id") long id) {
		this.dataService.deleteImmunology(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/initialCheck" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteInitialCheck(@RequestParam("id") long id) {
		this.dataService.deleteInitialCheck(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/bacterialType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteBacterialType(@RequestParam("id") long id) {
		this.dataService.deleteBacterialType(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/biochemicaldetectionzType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteBiochemicalDetectionzType(@RequestParam("id") long id) {
		this.dataService.deleteBiochemicalDetection(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/AssessMentType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse deleteAssessMentDaoType(@RequestParam("id") long id) {
		this.dataService.deleteAssessment(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "mx" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public MxDataForListResponse mx(long ptInfoId) {
		List<MxData> mxDataList = this.dataService.mxList(Long.valueOf(ptInfoId));
		List<DiagnosisSetting> settings = this.diagnosisSettingService.getAllSetting();
		List<MxDataForList> mxDataResponse = new ArrayList();
		int ascribeStatus = 0;
		if ((mxDataList != null) && (!mxDataList.isEmpty())) {
			PtInfo ptInfo = this.ptInfoService.getPtInfo(Long.valueOf(ptInfoId));
			MxData mxDataLast = (MxData) mxDataList.get(mxDataList.size() - 1);
			boolean isMxDataLast = false;
			if ((ptInfo.getDeathDate() != null) || (StringUtils.isNotBlank(ptInfo.getDeathReason()))) {
				ascribeStatus = 1;
			} else if (mxDataLast != null) {
				int therapyLast = mxDataLast.getTherapy();
				if ((therapyLast == 3) || (therapyLast == 4)) {
					ascribeStatus = 2;
				} else if (therapyLast == 5) {
					ascribeStatus = 8;
				} else if (therapyLast == 6) {
					ascribeStatus = 9;
				} else if (therapyLast == 7) {
					ascribeStatus = 10;
				}
			}
			Map<Integer, DiagnosisSetting> settingMap = new HashMap();
			for (DiagnosisSetting setting : settings) {
				settingMap.put(Integer.valueOf(setting.getDiagnosisType()), setting);
			}
			int tbDiagnosis_last = 0;
			int therapy_last = 0;
			Date m0_Date = null;
			int m_index_last = 0;
			String mxRes = "未治疗";
			boolean isStop = false;
			for (MxData mx : mxDataList) {
				MxDataForList res = new MxDataForList();
				res.setId(mx.getId());
				res.setDate(mx.getDate());
				res.setPtInfoId(mx.getPtInfoId());
				res.setIsdosage(mx.getIsdosage());
				int tbDiagnosis = mx.getTbDiagnosis();
				int therapy = mx.getTherapy();
				if (-1 == therapy) {
					therapy = therapy_last;
				}
				if (-1 == tbDiagnosis) {
					tbDiagnosis = tbDiagnosis_last;
				}
				if ((m0_Date == null) && (therapy == 0)) {
					mxRes = "未治疗";
				} else if (((tbDiagnosis != tbDiagnosis_last) && (3 != therapy) && (4 != therapy) && (5 != therapy)
						&& (6 != therapy)) || ((therapy_last == 0) && (1 == therapy))) {
					m0_Date = mx.getDate();
					mxRes = "M0";
					m_index_last = 0;
				} else if (m0_Date != null) {
					Date mDate = mx.getDate();
					int mxSize = 33;
					if (5 == tbDiagnosis) {
						mxSize = 25;
					} else if (6 == tbDiagnosis) {
						mxSize = 31;
					}
					for (int i = 0; i < mxSize; i++) {
						Date maxDate = DateUtil.monthOffset(m0_Date, mxSize - 1);
						maxDate = DateUtil.dateOffset(DateUtil.monthOffset(maxDate, 1), -14);
						if ((maxDate.before(mDate)) || (maxDate.equals(mDate))) {
							mxRes = "";
							break;
						}
						Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
						Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
						Date beginDate = DateUtil.dateOffset(d, -14);
						Date endDate = DateUtil.dateOffset(d1, -14);
						if (((mDate.after(beginDate)) || (beginDate.equals(mDate))) && (mDate.before(endDate))) {
							if (therapy == 0) {
								mxRes = "M" + i + "X";
								m_index_last = i;
								break;
							}
							if ((3 == therapy) || (4 == therapy) || (5 == therapy) || (6 == therapy)) {
								mxRes = "M" + i + "终止";
								break;
							}
							mxRes = "M" + i;
							m_index_last = i;

							break;
						}
						String tmpMxRes = "M" + i;
						if (m_index_last < i) {
							MxDataForList baseResTmp = new MxDataForList();
							baseResTmp.setId(Long.valueOf(0L));
							baseResTmp.setMx(tmpMxRes);
							baseResTmp.setDate(d);
							baseResTmp.setPtInfoId(Long.valueOf(ptInfoId));
							baseResTmp.setTbDiagnosis(tbDiagnosis);
							baseResTmp.setIsdosage(mx.getIsdosage());
							DiagnosisSetting setting = (DiagnosisSetting) settingMap
									.get(Integer.valueOf(baseResTmp.getTbDiagnosis()));
							if (setting != null) {
								importanceMonthOrData(baseResTmp.getMx(), ptInfoId, baseResTmp.getId().longValue(),
										baseResTmp.getDate(), setting, baseResTmp);
								MxData tmp = new MxData();
								tmp.setId(Long.valueOf(0L));
								tmp.setMx(baseResTmp.getMx());
								tmp.setDate(baseResTmp.getDate());
								tmp.setPtInfoId(baseResTmp.getPtInfoId());

								baseResTmp.setDataStatus(dataStatus(tmp, setting));
							}
							mxDataResponse.add(baseResTmp);
						}
					}
				}
				res.setMx(mxRes);
				res.setTbDiagnosis(tbDiagnosis);
				mx.setMx(mxRes);
				if ((5 == tbDiagnosis) && (m_index_last == 24) && (StringUtils.isNotBlank(mx.getMx()))) {
					mxDataLast = mx;
					isMxDataLast = true;
				} else if ((6 == tbDiagnosis) && (m_index_last == 30) && (StringUtils.isNotBlank(mx.getMx()))) {
					mxDataLast = mx;
					isMxDataLast = true;
				}
				DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(tbDiagnosis));
				if ((setting != null) && (!"未治疗".equals(mx.getMx()))) {
					importanceMonthOrData(mx.getMx(), ptInfoId, mx.getId().longValue(), mx.getDate(), setting, res);
					res.setDataStatus(dataStatus(mx, setting));
				} else {
					res.setDataStatus(dataStatus(mx, setting));
				}
				mxDataResponse.add(res);
				tbDiagnosis_last = tbDiagnosis;
				if (therapy != 0) {
					therapy_last = therapy;
				}
				if ((therapy == 3) || (therapy == 4)) {
					isStop = true;
					ascribeStatus = 2;
					break;
				}
				if (therapy == 5) {
					isStop = true;
					ascribeStatus = 8;
					break;
				}
				if (therapy == 6) {
					isStop = true;
					ascribeStatus = 9;
					break;
				}
				if (therapy == 7) {
					isStop = true;
					ascribeStatus = 10;
					break;
				}
			}
			if (!isStop) {
				MxData lastMxData = (MxData) mxDataList.get(mxDataList.size() - 1);
				if ((m0_Date != null) && (lastMxData != null) && (lastMxData.getMx().startsWith("M"))) {
					DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(tbDiagnosis_last));

					int mxSize = 33;
					if (5 == tbDiagnosis_last) {
						mxSize = 25;
					} else if (6 == tbDiagnosis_last) {
						mxSize = 31;
					}
					for (int i = m_index_last + 1; i < mxSize; i++) {
						Date mxDate = DateUtil.monthOffset(m0_Date, i);
						MxDataForList res = new MxDataForList();
						res.setId(Long.valueOf(0L));
						res.setDate(mxDate);
						res.setPtInfoId(Long.valueOf(ptInfoId));
						res.setTbDiagnosis(tbDiagnosis_last);
						res.setMx("M" + i);
						res.setIsdosage(lastMxData.getIsdosage());
						if (setting != null) {
							importanceMonthOrData(res.getMx(), ptInfoId, 0L, res.getDate(), setting, res);
						}
						MxData tmp = new MxData();
						tmp.setId(Long.valueOf(0L));
						tmp.setMx(res.getMx());
						tmp.setDate(res.getDate());
						tmp.setPtInfoId(res.getPtInfoId());
						if (setting != null) {
							res.setDataStatus(dataStatus(tmp, setting));
						}
						mxDataResponse.add(res);
					}
				}
			}
			if (mxRes.equals("未治疗")) {
				ascribeStatus = 7;
			}
			if (ascribeStatus == 0) {
				MxDataForList maxBaseMxData = (MxDataForList) mxDataResponse.get(mxDataResponse.size() - 1);
				Date maxBaseDate = maxBaseMxData.getDate();

				Date baseDate = DateUtil.dateOffset(DateUtil.monthOffset(maxBaseDate, -2), -15);
				if ((mxDataLast.getDate().after(baseDate)) || (baseDate.equals(mxDataLast.getDate()))) {
					Date last12Month = DateUtil.monthOffset(maxBaseDate, -12);
					int size = mxDataList.size();
					List<SputumCulture> last5SputumCulture = new ArrayList(5);
					MxData lastMxDataTmp = null;
					for (int s = size - 1; s >= 0; s--) {
						MxData tmp = (MxData) mxDataList.get(s);
						if (last5SputumCulture.size() == 5) {
							break;
						}
						Date tmpDate = tmp.getDate();
						if (last12Month.before(tmpDate)) {
							if (lastMxDataTmp == null) {
								SputumCulture sputumCulture = this.dataService.findSputumCultureByPtInfoIdAndMxId(
										tmp.getPtInfoId().longValue(), tmp.getId().longValue());
								if ((sputumCulture != null) && (sputumCulture.getSpcuResult() != 0)
										&& (sputumCulture.getSpcuResult() != 6)) {
									last5SputumCulture.add(sputumCulture);
									lastMxDataTmp = tmp;
								}
							} else {
								Date d = DateUtil.dateOffset(lastMxDataTmp.getDate(), -30);
								if ((tmpDate.before(d)) || (d.equals(tmpDate))) {
									SputumCulture sputumCulture = this.dataService.findSputumCultureByPtInfoIdAndMxId(
											tmp.getPtInfoId().longValue(), tmp.getId().longValue());
									if ((sputumCulture != null) && (sputumCulture.getSpcuResult() != 0)
											&& (sputumCulture.getSpcuResult() != 6)) {
										last5SputumCulture.add(sputumCulture);
										lastMxDataTmp = tmp;
									}
								}
							}
						}
					}
					int last5SputumCultureSize = last5SputumCulture.size();
					if (last5SputumCultureSize == 5) {
						int one = ((SputumCulture) last5SputumCulture.get(0)).getSpcuResult();
						int two = ((SputumCulture) last5SputumCulture.get(1)).getSpcuResult();
						int three = ((SputumCulture) last5SputumCulture.get(2)).getSpcuResult();
						int four = ((SputumCulture) last5SputumCulture.get(3)).getSpcuResult();
						int five = ((SputumCulture) last5SputumCulture.get(4)).getSpcuResult();
						if (((one == -11) && (two == -11) && (three == -11) && (four == -11) && (five == -11))
								|| ((one == -11) && (two == -11) && (three == -11) && (four == -11) && (five > 0))
								|| ((one == -11) && (two == -11) && (three == -11) && (four > 0) && (five == -11))) {
							if (isMxDataLast) {
								SputumCulture sc = this.dataService.findSputumCultureByPtInfoIdAndMxId(
										mxDataLast.getPtInfoId().longValue(), mxDataLast.getId().longValue());
								if ((sc != null) && (sc.getSpcuResult() != 0) && (sc.getSpcuResult() != 6)) {
									ascribeStatus = 3;
								} else {
									ascribeStatus = 4;
								}
							} else {
								ascribeStatus = 4;
							}
						} else {
							ascribeStatus = 2;
						}
					} else {
						List<SputumCulture> last2SputumCulture = new ArrayList(2);
						for (int s = size - 1; s >= 0; s--) {
							MxData tmp = (MxData) mxDataList.get(s);
							if (last2SputumCulture.size() >= 2) {
								break;
							}
							if (tmp.getDate().after(last12Month)) {
								SputumCulture sputumCulture = this.dataService.findSputumCultureByPtInfoIdAndMxId(
										tmp.getPtInfoId().longValue(), tmp.getId().longValue());
								if ((sputumCulture != null) && (sputumCulture.getSpcuResult() > 0)) {
									last2SputumCulture.add(sputumCulture);
								}
							}
						}
						int last2SputumCultureSize = last2SputumCulture.size();
						if (last2SputumCultureSize >= 2) {
							ascribeStatus = 2;
						} else if (last2SputumCultureSize == 1) {
							Date endDate = DateUtil.dateOffset(DateUtil.monthOffset(maxBaseDate, 1), -14);
							long interval = DateUtil.Interval(((SputumCulture) last2SputumCulture.get(0)).getDate(),
									endDate);
							if (interval < 90L) {
								ascribeStatus = 2;
							} else {
								ascribeStatus = 4;
							}
						} else {
							ascribeStatus = 4;
						}
					}
				} else {
					Date lastDate = DateUtil.dateOffset(DateUtil.monthOffset(mxDataLast.getDate(), 3), -16);
					Date currentDate = new Date();
					if ((currentDate.after(lastDate)) || (currentDate.equals(lastDate))) {
						ascribeStatus = 5;
					} else {
						ascribeStatus = 6;
					}
				}
			}
		}
		if (ascribeStatus == 0) {
			ascribeStatus = 7;
		}
		MxDataForListResponse response = new MxDataForListResponse();

		response.setMxDataList(mxDataResponse);
		response.setAscribeStatus(ascribeStatus);
		return response;
	}

	@RequestMapping(value = { "mxData/{ptInfoId}/{mxId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public DataResponse mxData(@PathVariable("ptInfoId") long ptInfoId, @PathVariable("mxId") long mxId) {
		DataResponse response = new DataResponse();

		response.setPhysicalExam(this.dataService.findPhysicalExamByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setBloodRoutine(this.dataService.findBloodRoutineByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setUrineRoutine(this.dataService.findUrineRoutineByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setRenalFunc(this.dataService.findRenalFuncByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setChestRadioGraph(this.dataService.findChestRadioGraphByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setDrugScpt(this.dataService.findDrugScptByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setGeneChip(this.dataService.findGeneChipByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setGeneXpert(this.dataService.findGeneXpertByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setHain(this.dataService.findHainByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setLiverFunc(this.dataService.findLiverFuncByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setSputumCulture(this.dataService.findSputumCultureByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setSputumSmear(this.dataService.findSputumSmearByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setTbDiagnosis(this.dataService.findTbDiagnosisByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setTherapy(this.dataService.findTherapyByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setTransInst(this.dataService.findTransInstByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setImmunology(this.dataService.findImmunologyByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setInitialCheck(this.dataService.findInitialCheckByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setBacterialType(this.dataService.findBacterialTypeByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setBiochemicalDetection(this.dataService.findBiochemicalDetectionByPtInfoIdAndMxId(ptInfoId, mxId));
		response.setAssessment(this.dataService.findAssessmentByPtInfoIdAndMxId(ptInfoId, mxId));
		for (MxData mxdata : this.dataService.mxList(Long.valueOf(ptInfoId))) {
			if (mxdata.getIsdosage() == 1) {
				response.setIsdosage(mxdata.getIsdosage());
				break;
			}
		}
		return response;
	}

	@RequestMapping(value = { "chestImg" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public ChestImgResponse chestImg(@RequestParam("ptInfoId") long ptInfoId) {
		List<ChestRadioGraph> chestRadioGraph = this.dataService.findChestRadioGraphTop2ByPtInfoId(ptInfoId);
		ChestImgResponse response = new ChestImgResponse();
		if ((chestRadioGraph != null) && (!chestRadioGraph.isEmpty())) {
			for (int i = 0; i < chestRadioGraph.size(); i++) {
				if (i == 0) {
					response.setOneImgUrl(((ChestRadioGraph) chestRadioGraph.get(0)).getImage());
				}
				if (i == 1) {
					response.setTwoImgUrl(((ChestRadioGraph) chestRadioGraph.get(1)).getImage());
					break;
				}
			}
		}
		return response;
	}

	@RequestMapping(value = { "hospital" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public HospitalResponse hospital() {
		HospitalResponse response = new HospitalResponse();
		response.setHospitals(this.dataService.findAllHospital());
		return response;
	}

	private void importanceMonthOrData(String mx, long ptInfoId, long mxId, Date date, DiagnosisSetting setting,
			MxDataForList res) {
		String m;
		Date d = new Date();
		boolean isNeedCheckDataIncomplete = d.after(date) || d.equals(date);
		if (mx.endsWith("X"))
			mx = mx.substring(0, mx.lastIndexOf("X"));
		switch (mx) {
		case "M0":
			m = setting.getM0();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M1":
			m = setting.getM1();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M2":
			m = setting.getM2();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M3":
			m = setting.getM3();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M4":
			m = setting.getM4();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M5":
			m = setting.getM5();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M6":
			m = setting.getM6();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M7":
			m = setting.getM7();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M8":
			m = setting.getM8();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M9":
			m = setting.getM9();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M10":
			m = setting.getM10();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M11":
			m = setting.getM11();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M12":
			m = setting.getM12();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M13":
			m = setting.getM13();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M14":
			m = setting.getM14();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M15":
			m = setting.getM15();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M16":
			m = setting.getM16();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M17":
			m = setting.getM17();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M18":
			m = setting.getM18();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M19":
			m = setting.getM19();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M20":
			m = setting.getM20();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M21":
			m = setting.getM21();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M22":
			m = setting.getM22();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M23":
			m = setting.getM23();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M24":
			m = setting.getM24();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M25":
			m = setting.getM25();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M26":
			m = setting.getM26();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M27":
			m = setting.getM27();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M28":
			m = setting.getM28();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M29":
			m = setting.getM29();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M30":
			m = setting.getM30();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M31":
			m = setting.getM31();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M32":
			m = setting.getM32();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M33":
			m = setting.getM33();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M34":
			m = setting.getM34();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M35":
			m = setting.getM35();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		case "M36":
			m = setting.getM36();
			if (StringUtils.isNotBlank(m)) {
				res.setImportanceMonth(CommonContants.IMPORTANCE_MONTH);
				res.setCheckItem(m);
				if (isNeedCheckDataIncomplete && isDataIncomplete(m, ptInfoId, mxId))
					res.setDataIncomplete(CommonContants.DATA_INCOMPLETE);
			}
			break;
		}
	}

	@RequestMapping(value = { "mx/report" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public MxDataReportResponse mxReport(long ptInfoId) {
		List<MxData> mxDataList = this.dataService.mxList(Long.valueOf(ptInfoId));
		List<DiagnosisSetting> settings = this.diagnosisSettingService.getAllSetting();
		List<MxDataReport> realMxDataList = new ArrayList();
		List<MxDataReport> baseMxDataList = new ArrayList();
		if ((mxDataList != null) && (!mxDataList.isEmpty())) {
			Map<Integer, DiagnosisSetting> settingMap = new HashMap();
			for (DiagnosisSetting setting : settings) {
				settingMap.put(Integer.valueOf(setting.getDiagnosisType()), setting);
			}
			int tbDiagnosis_last = 0;
			int therapy_last = 0;
			String currTherapy_last = "";

			Date m0_Date = null;
			int m_index_last = 0;
			String mxRes = "未治疗";
			String mxRes_last = "未治疗";
			for (MxData mx : mxDataList) {
				MxDataReport res = new MxDataReport();
				res.setId(mx.getId());
				res.setDate(mx.getDate());
				res.setPtInfoId(mx.getPtInfoId());
				res.setSputumCulture(
						this.dataService.findSputumCultureByPtInfoIdAndMxId(ptInfoId, mx.getId().longValue()));
				res.setSputumSmear(this.dataService.findSputumSmearByPtInfoIdAndMxId(ptInfoId, mx.getId().longValue()));

				MxDataReport baseRes = new MxDataReport();
				baseRes.setDate(mx.getDate());
				baseRes.setId(Long.valueOf(0L));

				int tbDiagnosis = mx.getTbDiagnosis();
				int therapy = mx.getTherapy();
				if (-1 == therapy) {
					therapy = therapy_last;
				}
				if (-1 == tbDiagnosis) {
					tbDiagnosis = tbDiagnosis_last;
				}
				if ((m0_Date == null) && (therapy == 0)) {
					mxRes = "未治疗";
				} else if (((tbDiagnosis != tbDiagnosis_last) && (3 != therapy) && (4 != therapy) && (5 != therapy)
						&& (6 != therapy)) || ((therapy_last == 0) && (1 == therapy))) {
					m0_Date = mx.getDate();
					mxRes = "M0";
					m_index_last = 0;
				} else if (m0_Date != null) {
					Date mDate = mx.getDate();
					int mxSize = 33;
					if (5 == tbDiagnosis) {
						mxSize = 25;
					} else if (6 == tbDiagnosis) {
						mxSize = 31;
					}
					for (int i = 0; i < mxSize; i++) {
						Date maxDate = DateUtil.monthOffset(m0_Date, mxSize - 1);
						maxDate = DateUtil.dateOffset(DateUtil.monthOffset(maxDate, 1), -14);
						if ((maxDate.before(mDate)) || (maxDate.equals(mDate))) {
							mxRes = "";
							break;
						}
						Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
						Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
						Date beginDate = DateUtil.dateOffset(d, -14);
						Date endDate = DateUtil.dateOffset(d1, -14);
						baseRes.setDate(d);

						baseRes.setAssessment(
								this.dataService.findAssessmentByPtInfoIdAndMxId(ptInfoId, mx.getId().longValue()));
						if (((mDate.after(beginDate)) || (beginDate.equals(mDate))) && (mDate.before(endDate))) {
							if (therapy == 0) {
								mxRes = "M" + i + "X";
								m_index_last = i;
								break;
							}
							if ((3 == therapy) || (4 == therapy) || (5 == therapy) || (6 == therapy)) {
								mxRes = "M" + i + "终止";
								break;
							}
							mxRes = "M" + i;
							m_index_last = i;

							break;
						}
						String tmpMxRes = "M" + i;
						if (m_index_last < i) {
							MxDataReport baseResTmp = new MxDataReport();
							baseResTmp.setId(Long.valueOf(0L));
							baseResTmp.setMx(tmpMxRes);
							baseResTmp.setDate(d);
							baseResTmp.setPtInfoId(Long.valueOf(ptInfoId));
							baseResTmp.setTbDiagnosis(tbDiagnosis);
							baseResTmp.setTherapy(therapy);
							baseResTmp.setCurrTherapy(StringUtils.isNotBlank(mx.getCurrTherapy()) ? mx.getCurrTherapy()
									: currTherapy_last);

							baseResTmp.setAssessment(
									this.dataService.findAssessmentByPtInfoIdAndMxId(ptInfoId, mx.getId().longValue()));

							DiagnosisSetting setting = (DiagnosisSetting) settingMap
									.get(Integer.valueOf(baseResTmp.getTbDiagnosis()));
							if (setting != null) {
								importanceMonthOrData(baseResTmp.getMx(), ptInfoId, baseResTmp.getId().longValue(),
										baseResTmp.getDate(), setting, baseResTmp);
								MxData tmp = new MxData();
								tmp.setId(Long.valueOf(0L));
								tmp.setMx(baseResTmp.getMx());
								tmp.setDate(baseResTmp.getDate());
								tmp.setPtInfoId(baseResTmp.getPtInfoId());

								baseResTmp.setDataStatus(dataStatus(tmp, setting));
							}
							baseMxDataList.add(baseResTmp);
							realMxDataList.add(baseResTmp);
						}
					}
				}
				res.setMx(mxRes);
				res.setTbDiagnosis(tbDiagnosis);
				res.setTherapy(therapy);
				res.setCurrTherapy(mx.getCurrTherapy());

				res.setAssessment(this.dataService.findAssessmentByPtInfoIdAndMxId(ptInfoId, mx.getId().longValue()));
				mx.setMx(mxRes);
				DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(tbDiagnosis));
				if ((setting != null) && (!"未治疗".equals(mx.getMx()))) {
					importanceMonthOrData(mx.getMx(), ptInfoId, mx.getId().longValue(), mx.getDate(), setting, res);
					int dataStatus = dataStatus(mx, setting);
					res.setDataStatus(dataStatus);
					baseRes.setDataStatus(dataStatus);
					baseRes.setMx(mxRes);
					importanceMonthOrData(baseRes.getMx(), ptInfoId, mx.getId().longValue(), baseRes.getDate(), setting,
							baseRes);
				}
				realMxDataList.add(res);
				if ((!mxRes.equals("未治疗")) && (!mxRes.equals(mxRes_last))) {
					baseRes.setPtInfoId(mx.getPtInfoId());
					baseRes.setTbDiagnosis(tbDiagnosis);
					baseRes.setTherapy(therapy);
					baseRes.setCurrTherapy(
							StringUtils.isNotBlank(mx.getCurrTherapy()) ? mx.getCurrTherapy() : currTherapy_last);

					res.setAssessment(
							this.dataService.findAssessmentByPtInfoIdAndMxId(ptInfoId, mx.getId().longValue()));
					baseMxDataList.add(baseRes);
				}
				tbDiagnosis_last = tbDiagnosis;
				if (therapy != 0) {
					therapy_last = therapy;
				}
				if (StringUtils.isNotBlank(mx.getCurrTherapy())) {
					currTherapy_last = mx.getCurrTherapy();
				}
				mxRes_last = mxRes;
			}
			MxData lastMxData = (MxData) mxDataList.get(mxDataList.size() - 1);
			if ((m0_Date != null) && (lastMxData != null) && (lastMxData.getMx().startsWith("M"))) {
				DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(tbDiagnosis_last));
				int mxSize = 33;
				if (5 == tbDiagnosis_last) {
					mxSize = 25;
				} else if (6 == tbDiagnosis_last) {
					mxSize = 31;
				}
				for (int i = m_index_last + 1; i < mxSize; i++) {
					MxDataReport res = new MxDataReport();
					res.setId(Long.valueOf(0L));
					res.setDate(DateUtil.monthOffset(m0_Date, i));
					res.setPtInfoId(Long.valueOf(ptInfoId));
					res.setTbDiagnosis(tbDiagnosis_last);
					res.setTherapy(therapy_last);
					res.setCurrTherapy(currTherapy_last);
					res.setMx("M" + i);
					if (setting != null) {
						importanceMonthOrData(res.getMx(), ptInfoId, 0L, res.getDate(), setting, res);
					}
					MxData tmp = new MxData();
					tmp.setId(Long.valueOf(0L));
					tmp.setMx(res.getMx());
					tmp.setDate(res.getDate());
					tmp.setPtInfoId(res.getPtInfoId());

					res.setAssessment(
							this.dataService.findAssessmentByPtInfoIdAndMxId(ptInfoId, lastMxData.getId().longValue()));
					res.setDataStatus(dataStatus(tmp, setting));
					baseMxDataList.add(res);
				}
			}
		}
		MxDataReportResponse reportResponse = new MxDataReportResponse();
		reportResponse.setRealMxDataList(realMxDataList);
		reportResponse.setBaseMxDataList(baseMxDataList);
		return reportResponse;
	}

	@RequestMapping(value = { "ascribe/report" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public AscribeReportResponse ascribeReport() {
		AscribeReportResponse reportResponse = new AscribeReportResponse();
		int complete = 0;
		int death = 0;
		int fail = 0;
		int voluntary = 0;
		int exclude = 0;
		int inCure = 0;
		int lose = 0;
		int cure = 0;
		int notTreat = 0;
		int out = 0;

		List<PtInfo> completePtInfo = new ArrayList();
		List<PtInfo> deathPtInfo = new ArrayList();
		List<PtInfo> failPtInfo = new ArrayList();
		List<PtInfo> voluntaryPtInfo = new ArrayList();
		List<PtInfo> excludePtInfo = new ArrayList();
		List<PtInfo> inCurePtInfo = new ArrayList();
		List<PtInfo> losePtInfo = new ArrayList();
		List<PtInfo> curePtInfo = new ArrayList();
		List<PtInfo> notTreatPtInfo = new ArrayList();
		List<PtInfo> outPtInfo = new ArrayList();

		List<PtInfo> ptInfoList = this.ptInfoService.findAll();
		long ptInfoId;
		List<MxData> mxDataList;
		List<MxDataForList> mxDataResponse;
		MxData mxDataLast;
		boolean isMxDataLast;
		boolean isStop;
		int tbDiagnosis_last;
		int therapy_last;
		Date m0_Date;
		if ((ptInfoList != null) && (!ptInfoList.isEmpty())) {
			for (int p = 0; p < ptInfoList.size(); p++) {
				PtInfo ptInfo = (PtInfo) ptInfoList.get(p);
				if ((ptInfo.getDeathDate() != null) || (StringUtils.isNotBlank(ptInfo.getDeathReason()))) {
					death++;
					deathPtInfo.add(ptInfo);
				} else {
					ptInfoId = ptInfo.getId().longValue();
					mxDataList = this.dataService.mxList(Long.valueOf(ptInfoId));
					mxDataResponse = new ArrayList();
					if ((mxDataList != null) && (!mxDataList.isEmpty())) {
						mxDataLast = (MxData) mxDataList.get(mxDataList.size() - 1);
						isMxDataLast = false;
						if (mxDataLast != null) {
							if ((mxDataLast.getTherapy() == 3) || (mxDataLast.getTherapy() == 4)) {
								fail++;
								failPtInfo.add(ptInfo);
								continue;
							}
							if (mxDataLast.getTherapy() == 5) {
								voluntary++;
								voluntaryPtInfo.add(ptInfo);
								continue;
							}
							if (mxDataLast.getTherapy() == 6) {
								exclude++;
								excludePtInfo.add(ptInfo);
								continue;
							}
							if (mxDataLast.getTherapy() == 7) {
								out++;
								outPtInfo.add(ptInfo);
								continue;
							}
						}
						isStop = false;
						tbDiagnosis_last = 0;
						therapy_last = 0;
						m0_Date = null;
						int m_index_last = 0;
						String mxRes = "未治疗";
						for (MxData mx : mxDataList) {
							MxDataForList res = new MxDataForList();
							res.setId(mx.getId());
							res.setDate(mx.getDate());
							res.setPtInfoId(mx.getPtInfoId());

							int tbDiagnosis = mx.getTbDiagnosis();
							int therapy = mx.getTherapy();
							if (-1 == therapy) {
								therapy = therapy_last;
							}
							if (-1 == tbDiagnosis) {
								tbDiagnosis = tbDiagnosis_last;
							}
							if ((3 == therapy) || (4 == therapy) || (5 == therapy) || (6 == therapy)) {
								mxRes = "终止";
							} else if ((m0_Date == null) && (therapy == 0)) {
								mxRes = "未治疗";
							} else if (((tbDiagnosis != tbDiagnosis_last) && (3 != therapy) && (4 != therapy)
									&& (5 != therapy) && (6 != therapy)) || ((therapy_last == 0) && (1 == therapy))) {
								m0_Date = mx.getDate();
								mxRes = "M0";
								m_index_last = 0;
							} else if (m0_Date != null) {
								Date mDate = mx.getDate();
								int mxSize = 33;
								if (5 == tbDiagnosis) {
									mxSize = 25;
								} else if (6 == tbDiagnosis) {
									mxSize = 31;
								}
								for (int i = 0; i < mxSize; i++) {
									Date maxDate = DateUtil.monthOffset(m0_Date, mxSize - 1);
									maxDate = DateUtil.dateOffset(DateUtil.monthOffset(maxDate, 1), -14);
									if ((maxDate.before(mDate)) || (maxDate.equals(mDate))) {
										mxRes = "";
										break;
									}
									Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
									Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
									Date beginDate = DateUtil.dateOffset(d, -14);
									Date endDate = DateUtil.dateOffset(d1, -14);
									if (((mDate.after(beginDate)) || (beginDate.equals(mDate)))
											&& (mDate.before(endDate))) {
										mxRes = "M" + i;
										m_index_last = i;
										break;
									}
									String tmpMxRes = "M" + i;
									if (m_index_last < i) {
										MxDataForList baseResTmp = new MxDataForList();
										baseResTmp.setId(Long.valueOf(0L));
										baseResTmp.setMx(tmpMxRes);
										baseResTmp.setDate(d);
										baseResTmp.setPtInfoId(Long.valueOf(ptInfoId));
										baseResTmp.setTbDiagnosis(tbDiagnosis);

										mxDataResponse.add(baseResTmp);
									}
								}
							}
							res.setMx(mxRes);
							res.setTbDiagnosis(tbDiagnosis);
							mx.setMx(mxRes);
							if ((5 == tbDiagnosis) && (m_index_last == 24) && (StringUtils.isNotBlank(mx.getMx()))) {
								mxDataLast = mx;
								isMxDataLast = true;
							} else if ((6 == tbDiagnosis) && (m_index_last == 30)
									&& (StringUtils.isNotBlank(mx.getMx()))) {
								mxDataLast = mx;
								isMxDataLast = true;
							}
							mxDataResponse.add(res);
							tbDiagnosis_last = tbDiagnosis;
							if (therapy != 0) {
								therapy_last = therapy;
							}
							if ((therapy == 3) || (therapy == 4)) {
								fail++;
								failPtInfo.add(ptInfo);
								isStop = true;
								break;
							}
							if (therapy == 5) {
								voluntary++;
								voluntaryPtInfo.add(ptInfo);
								isStop = true;
								break;
							}
							if (therapy == 6) {
								exclude++;
								excludePtInfo.add(ptInfo);
								isStop = true;
								break;
							}
							if (therapy == 7) {
								out++;
								outPtInfo.add(ptInfo);
								isStop = true;
								break;
							}
						}
						if (!isStop) {
							if (mxRes.equals("未治疗")) {
								notTreat++;
								notTreatPtInfo.add(ptInfo);
							} else {
								MxData lastMxData = (MxData) mxDataList.get(mxDataList.size() - 1);
								if ((m0_Date != null) && (lastMxData != null) && (lastMxData.getMx().startsWith("M"))) {
									int mxSize = 33;
									if (5 == tbDiagnosis_last) {
										mxSize = 25;
									} else if (6 == tbDiagnosis_last) {
										mxSize = 31;
									}
									for (int i = m_index_last + 1; i < mxSize; i++) {
										Date mxDate = DateUtil.monthOffset(m0_Date, i);
										MxDataForList res = new MxDataForList();
										res.setId(Long.valueOf(0L));
										res.setDate(mxDate);
										res.setPtInfoId(Long.valueOf(ptInfoId));
										res.setTbDiagnosis(tbDiagnosis_last);
										res.setMx("M" + i);

										mxDataResponse.add(res);
									}
								}
								MxDataForList maxBaseMxData = (MxDataForList) mxDataResponse
										.get(mxDataResponse.size() - 1);
								Date maxBaseDate = maxBaseMxData.getDate();
								Date baseDate = DateUtil.dateOffset(DateUtil.monthOffset(maxBaseDate, -2), -15);
								if ((baseDate.before(mxDataLast.getDate()))
										|| (baseDate.equals(mxDataLast.getDate()))) {
									Date last12Month = DateUtil.monthOffset(maxBaseDate, -12);
									int size = mxDataList.size();
									List<SputumCulture> last5SputumCulture = new ArrayList(5);
									MxData lastMxDataTmp = null;
									for (int s = size - 1; s >= 0; s--) {
										MxData tmp = (MxData) mxDataList.get(s);
										if (last5SputumCulture.size() == 5) {
											break;
										}
										Date tmpDate = tmp.getDate();
										if (last12Month.before(tmpDate)) {
											if (lastMxDataTmp == null) {
												SputumCulture sputumCulture = this.dataService
														.findSputumCultureByPtInfoIdAndMxId(
																tmp.getPtInfoId().longValue(), tmp.getId().longValue());
												if ((sputumCulture != null) && (sputumCulture.getSpcuResult() != 0)
														&& (sputumCulture.getSpcuResult() != 6)) {
													last5SputumCulture.add(sputumCulture);
													lastMxDataTmp = tmp;
												}
											} else {
												Date d = DateUtil.dateOffset(lastMxDataTmp.getDate(), -30);
												if ((tmpDate.before(d)) || (d.equals(tmpDate))) {
													SputumCulture sputumCulture = this.dataService
															.findSputumCultureByPtInfoIdAndMxId(
																	tmp.getPtInfoId().longValue(),
																	tmp.getId().longValue());
													if ((sputumCulture != null) && (sputumCulture.getSpcuResult() != 0)
															&& (sputumCulture.getSpcuResult() != 6)) {
														last5SputumCulture.add(sputumCulture);
														lastMxDataTmp = tmp;
													}
												}
											}
										}
									}
									int last5SputumCultureSize = last5SputumCulture.size();
									if (last5SputumCultureSize == 5) {
										int one = ((SputumCulture) last5SputumCulture.get(0)).getSpcuResult();
										int two = ((SputumCulture) last5SputumCulture.get(1)).getSpcuResult();
										int three = ((SputumCulture) last5SputumCulture.get(2)).getSpcuResult();
										int four = ((SputumCulture) last5SputumCulture.get(3)).getSpcuResult();
										int five = ((SputumCulture) last5SputumCulture.get(4)).getSpcuResult();
										if (((one == -11) && (two == -11) && (three == -11) && (four == -11)
												&& (five == -11))
												|| ((one == -11) && (two == -11) && (three == -11) && (four == -11)
														&& (five > 0))
												|| ((one == -11) && (two == -11) && (three == -11) && (four > 0)
														&& (five == -11))) {
											if (isMxDataLast) {
												SputumCulture sc = this.dataService.findSputumCultureByPtInfoIdAndMxId(
														mxDataLast.getPtInfoId().longValue(),
														mxDataLast.getId().longValue());
												if ((sc != null) && (sc.getSpcuResult() != 0)
														&& (sc.getSpcuResult() != 6)) {
													cure++;
													curePtInfo.add(ptInfo);
												} else {
													complete++;
													completePtInfo.add(ptInfo);
												}
											} else {
												complete++;
												completePtInfo.add(ptInfo);
											}
										} else {
											failPtInfo.add(ptInfo);
											fail++;
										}
									} else {
										List<SputumCulture> last2SputumCulture = new ArrayList(2);
										for (int s = size - 1; s >= 0; s--) {
											MxData tmp = (MxData) mxDataList.get(s);
											if (last2SputumCulture.size() >= 2) {
												break;
											}
											if (tmp.getDate().after(last12Month)) {
												SputumCulture sputumCulture = this.dataService
														.findSputumCultureByPtInfoIdAndMxId(
																tmp.getPtInfoId().longValue(), tmp.getId().longValue());
												if ((sputumCulture != null) && (sputumCulture.getSpcuResult() > 0)) {
													last2SputumCulture.add(sputumCulture);
												}
											}
										}
										int last2SputumCultureSize = last2SputumCulture.size();
										if (last2SputumCultureSize >= 2) {
											fail++;
											failPtInfo.add(ptInfo);
										} else if (last2SputumCultureSize == 1) {
											Date endDate = DateUtil.dateOffset(DateUtil.monthOffset(maxBaseDate, 1),
													-14);
											long interval = DateUtil.Interval(
													((SputumCulture) last2SputumCulture.get(0)).getDate(), endDate);
											if (interval < 90L) {
												fail++;
												failPtInfo.add(ptInfo);
											} else {
												complete++;
												completePtInfo.add(ptInfo);
											}
										} else {
											complete++;
											completePtInfo.add(ptInfo);
										}
									}
								} else {
									Date lastDate = DateUtil.dateOffset(DateUtil.monthOffset(mxDataLast.getDate(), 3),
											-16);
									Date currentDate = new Date();
									if ((currentDate.after(lastDate)) || (currentDate.equals(lastDate))) {
										lose++;
										losePtInfo.add(ptInfo);
									} else {
										inCure++;
										inCurePtInfo.add(ptInfo);
									}
								}
							}
						}
					}
				}
			}
			reportResponse.setComplete(complete);
			reportResponse.setDeath(death);
			reportResponse.setFail(fail);
			reportResponse.setVoluntary(voluntary);
			reportResponse.setExclude(exclude);
			reportResponse.setInCure(inCure);
			reportResponse.setCure(cure);
			reportResponse.setLose(lose);
			reportResponse.setNotTreat(notTreat);
			reportResponse.setOuter(out);
			reportResponse.setTotal(ptInfoList.size());
		}
		List<Long> completePtInfoIds = new ArrayList();
		for (PtInfo p : completePtInfo) {
			completePtInfoIds.add(p.getId());
		}
		List<Long> deathPtInfoIds = new ArrayList();
		for (PtInfo p : deathPtInfo) {
			deathPtInfoIds.add(p.getId());
		}
		List<Long> failPtInfoIds = new ArrayList();
		for (PtInfo p : failPtInfo) {
			failPtInfoIds.add(p.getId());
		}
		Object voluntaryPtInfoIds = new ArrayList();
		for (PtInfo p : voluntaryPtInfo) {
			((List) voluntaryPtInfoIds).add(p.getId());
		}
		List<Long> excludePtInfoIds = new ArrayList();
		for (PtInfo p : excludePtInfo) {
			excludePtInfoIds.add(p.getId());
		}
		List<Long> inCurePtInfoIds = new ArrayList();
		for (PtInfo p : inCurePtInfo) {
			inCurePtInfoIds.add(p.getId());
		}
		List<Long> losePtInfoIds = new ArrayList();
		for (PtInfo p : losePtInfo) {
			losePtInfoIds.add(p.getId());
		}
		List<Long> curePtInfoIds = new ArrayList();
		for (PtInfo p : curePtInfo) {
			curePtInfoIds.add(p.getId());
		}
		List<Long> outPtInfoIds = new ArrayList();
		for (PtInfo p : outPtInfo) {
			outPtInfoIds.add(p.getId());
		}
		ptInfoList.removeAll(completePtInfo);
		ptInfoList.removeAll(deathPtInfo);
		ptInfoList.removeAll(failPtInfo);
		ptInfoList.removeAll(voluntaryPtInfo);
		ptInfoList.removeAll(excludePtInfo);
		ptInfoList.removeAll(inCurePtInfo);
		ptInfoList.removeAll(losePtInfo);
		ptInfoList.removeAll(curePtInfo);
		ptInfoList.removeAll(outPtInfo);
		if (!ptInfoList.isEmpty()) {
			notTreatPtInfo.addAll(ptInfoList);
		}
		List<Long> notTreatPtInfoIds = new ArrayList();
		for (PtInfo p : notTreatPtInfo) {
			if (!notTreatPtInfoIds.contains(p.getId())) {
				notTreatPtInfoIds.add(p.getId());
			}
			if ((completePtInfoIds.contains(p.getId())) || (deathPtInfoIds.contains(p.getId()))
					|| (failPtInfoIds.contains(p.getId())) || (inCurePtInfoIds.contains(p.getId()))
					|| (losePtInfoIds.contains(p.getId())) || (curePtInfoIds.contains(p.getId()))
					|| (((List) voluntaryPtInfoIds).contains(p.getId())) || (excludePtInfoIds.contains(p.getId()))
					|| (outPtInfoIds.contains(p.getId()))) {
				notTreatPtInfoIds.remove(p.getId());
			}
		}
		reportResponse.setNotTreat(notTreatPtInfoIds.size());

		reportResponse.setCompletePtInfo(completePtInfoIds);
		reportResponse.setDeathPtInfo(deathPtInfoIds);
		reportResponse.setFailPtInfo(failPtInfoIds);
		reportResponse.setVoluntaryPtInfo((List) voluntaryPtInfoIds);
		reportResponse.setExcludePtInfo(excludePtInfoIds);
		reportResponse.setInCurePtInfo(inCurePtInfoIds);
		reportResponse.setLosePtInfo(losePtInfoIds);
		reportResponse.setCurePtInfo(curePtInfoIds);
		reportResponse.setNotTreatPtInfo(notTreatPtInfoIds);
		reportResponse.setOuterPtInfo(outPtInfoIds);
		return reportResponse;
	}

	@RequestMapping(value = { "ascribe/date" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public AscribeResponse ascribeDate() {
		AscribeResponse response = new AscribeResponse();
		MxData beginMxData = this.dataService.findTop1OrderByDateAsc();
		MxData endMxData = this.dataService.findTop1OrderByDateDesc();

		response.setBeginDate(beginMxData != null ? beginMxData.getDate() : new Date());
		response.setEndDate(endMxData != null ? endMxData.getDate() : new Date());
		return response;
	}

	@RequestMapping(value = { "ascribe/search" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public AscribeReportResponse ascribeSearch(
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchBeginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchEndDate,
			@RequestParam(value = "type", defaultValue = "5,6") String type,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		AscribeReportResponse reportResponse = new AscribeReportResponse();
		int complete = 0;
		int death = 0;
		int fail = 0;
		int voluntary = 0;
		int exclude = 0;
		int inCure = 0;
		int lose = 0;
		int cure = 0;
		int notTreat = 0;
		int out = 0;

		List<PtInfo> completePtInfo = new ArrayList();
		List<PtInfo> deathPtInfo = new ArrayList();
		List<PtInfo> failPtInfo = new ArrayList();
		List<PtInfo> voluntaryPtInfo = new ArrayList();
		List<PtInfo> excludePtInfo = new ArrayList();
		List<PtInfo> inCurePtInfo = new ArrayList();
		List<PtInfo> losePtInfo = new ArrayList();
		List<PtInfo> curePtInfo = new ArrayList();
		List<PtInfo> notTreatPtInfo = new ArrayList();
		List<PtInfo> outPtInfo = new ArrayList();
		List<PtInfo> tmpPtInfo = new ArrayList();
		List<PtInfo> notTreatTmpPtInfo = new ArrayList();

		long tmpPtInfoId = 0L;
		List<PtInfo> ptInfoList = this.ptInfoService.findAll();
		long ptInfoId;
		List<MxData> mxDataList;
		List<MxDataForList> mxDataResponse;
		MxData mxDataLast;
		boolean isMxDataLast;
		int tbDiagnosis_last;
		int therapy_last;
		Date m0_Date;
		int m_index_last;
		if ((ptInfoList != null) && (!ptInfoList.isEmpty())) {
			for (int p = 0; p < ptInfoList.size(); p++) {
				PtInfo ptInfo = (PtInfo) ptInfoList.get(p);
				if (tmpPtInfoId == ptInfo.getId().longValue()) {
					tmpPtInfo.add(ptInfo);
				} else if (((!"all".equals(dept)) && (!dept.equals(ptInfo.getDepartment())))
						|| ((!"all".equals(city)) && (!city.equals(ptInfo.getCity())))) {
					tmpPtInfoId = ptInfo.getId().longValue();
					tmpPtInfo.add(ptInfo);
				} else {
					ptInfoId = ptInfo.getId().longValue();
					mxDataList = this.dataService.mxList(Long.valueOf(ptInfoId));
					mxDataResponse = new ArrayList();
					if ((mxDataList != null) && (!mxDataList.isEmpty())) {
						mxDataLast = (MxData) mxDataList.get(mxDataList.size() - 1);
						isMxDataLast = false;

						tbDiagnosis_last = 0;
						therapy_last = 0;
						m0_Date = null;
						m_index_last = 0;
						String mxRes = "未治疗";
						int firstM0 = 0;
						boolean isNeedAscribe = false;
						boolean hasM0 = false;
						boolean isStop = false;
						boolean isStop_Voluntary = false;
						boolean isStop_EXCLUDE = false;
						boolean isStop_OUT = false;
						for (MxData mx : mxDataList) {
							MxDataForList res = new MxDataForList();
							res.setId(mx.getId());
							res.setDate(mx.getDate());
							res.setPtInfoId(mx.getPtInfoId());

							int tbDiagnosis = mx.getTbDiagnosis();
							int therapy = mx.getTherapy();
							if (-1 == therapy) {
								therapy = therapy_last;
							}
							if (-1 == tbDiagnosis) {
								tbDiagnosis = tbDiagnosis_last;
							}
							if ((3 == therapy) || (4 == therapy)) {
								mxRes = "终止";
								isStop = true;
							} else if (5 == therapy) {
								mxRes = "终止";
								isStop_Voluntary = true;
							} else if (6 == therapy) {
								mxRes = "终止";
								isStop_EXCLUDE = true;
							} else if (7 == therapy) {
								mxRes = "终止";
								isStop_OUT = true;
							} else if ((m0_Date == null) && (therapy == 0)) {
								mxRes = "未治疗";
								Date mDate = mx.getDate();
								if (((searchBeginDate.before(mDate)) || (searchBeginDate.equals(mDate)))
										&& ((searchEndDate.after(mDate)) || (searchEndDate.equals(mDate)))) {
									notTreat++;
									notTreatPtInfo.add(ptInfo);
								} else {
									notTreatTmpPtInfo.add(ptInfo);
								}
							} else if (((tbDiagnosis != tbDiagnosis_last) && (3 != therapy) && (4 != therapy)
									&& (5 != therapy) && (6 != therapy)) || ((therapy_last == 0) && (1 == therapy))) {
								m0_Date = mx.getDate();
								mxRes = "M0";
								m_index_last = 0;
								firstM0++;
								hasM0 = true;
								if (((searchBeginDate.before(m0_Date)) || (searchBeginDate.equals(m0_Date)))
										&& ((searchEndDate.after(m0_Date)) || (searchEndDate.equals(m0_Date)))
										&& (firstM0 == 1)) {
									isNeedAscribe = true;
								} else if (firstM0 == 1) {
									break;
								}
							} else if (m0_Date != null) {
								Date mDate = mx.getDate();
								int mxSize = 33;
								if (5 == tbDiagnosis) {
									mxSize = 25;
								} else if (6 == tbDiagnosis) {
									mxSize = 31;
								}
								for (int i = 0; i < mxSize; i++) {
									Date maxDate = DateUtil.monthOffset(m0_Date, mxSize - 1);
									maxDate = DateUtil.dateOffset(DateUtil.monthOffset(maxDate, 1), -14);
									if ((maxDate.before(mDate)) || (maxDate.equals(mDate))) {
										mxRes = "";
										break;
									}
									Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
									Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
									Date beginDate = DateUtil.dateOffset(d, -14);
									Date endDate = DateUtil.dateOffset(d1, -14);
									if (((mDate.after(beginDate)) || (beginDate.equals(mDate)))
											&& (mDate.before(endDate))) {
										mxRes = "M" + i;
										m_index_last = i;
										break;
									}
									String tmpMxRes = "M" + i;
									if (m_index_last < i) {
										MxDataForList baseResTmp = new MxDataForList();
										baseResTmp.setId(Long.valueOf(0L));
										baseResTmp.setMx(tmpMxRes);
										baseResTmp.setDate(d);
										baseResTmp.setPtInfoId(Long.valueOf(ptInfoId));
										baseResTmp.setTbDiagnosis(tbDiagnosis);

										mxDataResponse.add(baseResTmp);
									}
								}
							}
							res.setMx(mxRes);
							res.setTbDiagnosis(tbDiagnosis);
							mx.setMx(mxRes);
							mx.setTbDiagnosis(tbDiagnosis);
							if ((5 == tbDiagnosis) && (m_index_last == 24) && (StringUtils.isNotBlank(mx.getMx()))) {
								mxDataLast = mx;
								isMxDataLast = true;
							} else if ((6 == tbDiagnosis) && (m_index_last == 30)
									&& (StringUtils.isNotBlank(mx.getMx()))) {
								mxDataLast = mx;
								isMxDataLast = true;
							}
							mxDataResponse.add(res);
							tbDiagnosis_last = tbDiagnosis;
							if (therapy != 0) {
								therapy_last = therapy;
							}
						}
						if (!isNeedAscribe) {
							if (hasM0) {
								tmpPtInfo.add(ptInfo);
							}
						} else {
							int tbDiagnosis = mxDataLast.getTbDiagnosis();

							Object tbDiagnosis_search = null;
							if (StringUtils.isNotBlank(type)) {
								String[] type_search = type.split(",");
								tbDiagnosis_search = Arrays.asList(type_search);
							}
							if (((tbDiagnosis_search == null)
									|| (!((List) tbDiagnosis_search).contains(String.valueOf(tbDiagnosis))))
									&& (tbDiagnosis != -1) && (tbDiagnosis != 0)) {
								tmpPtInfo.add(ptInfo);
							} else if ((ptInfo.getDeathDate() != null)
									|| (StringUtils.isNotBlank(ptInfo.getDeathReason()))) {
								death++;
								deathPtInfo.add(ptInfo);
							} else if ((isStop) || (therapy_last == 3) || (therapy_last == 4)) {
								fail++;
								failPtInfo.add(ptInfo);
							} else if ((isStop_Voluntary) || (therapy_last == 5)) {
								voluntary++;
								voluntaryPtInfo.add(ptInfo);
							} else if ((isStop_EXCLUDE) || (therapy_last == 6)) {
								exclude++;
								excludePtInfo.add(ptInfo);
							} else if ((isStop_OUT) || (therapy_last == 7)) {
								out++;
								outPtInfo.add(ptInfo);
							} else if (mxRes.equals("未治疗")) {
								notTreat++;
								notTreatPtInfo.add(ptInfo);
							} else {
								MxData lastMxData = (MxData) mxDataList.get(mxDataList.size() - 1);
								if ((m0_Date != null) && (lastMxData != null) && (lastMxData.getMx().startsWith("M"))) {
									int mxSize = 33;
									if (5 == tbDiagnosis_last) {
										mxSize = 25;
									} else if (6 == tbDiagnosis_last) {
										mxSize = 31;
									}
									for (int i = m_index_last + 1; i < mxSize; i++) {
										Date mxDate = DateUtil.monthOffset(m0_Date, i);
										MxDataForList res = new MxDataForList();
										res.setId(Long.valueOf(0L));
										res.setDate(mxDate);
										res.setPtInfoId(Long.valueOf(ptInfoId));
										res.setTbDiagnosis(tbDiagnosis_last);
										res.setMx("M" + i);

										mxDataResponse.add(res);
									}
								}
								MxDataForList maxBaseMxData = (MxDataForList) mxDataResponse
										.get(mxDataResponse.size() - 1);
								Date maxBaseDate = maxBaseMxData.getDate();

								Date baseDate = DateUtil.dateOffset(DateUtil.monthOffset(maxBaseDate, -2), -15);
								if ((mxDataLast.getDate().after(baseDate)) || (baseDate.equals(mxDataLast.getDate()))) {
									Date last12Month = DateUtil.monthOffset(maxBaseDate, -12);
									int size = mxDataList.size();

									List<SputumCulture> last5SputumCulture = new ArrayList(5);
									MxData lastMxDataTmp = null;
									for (int s = size - 1; s >= 0; s--) {
										MxData tmp = (MxData) mxDataList.get(s);
										if (last5SputumCulture.size() == 5) {
											break;
										}
										Date tmpDate = tmp.getDate();
										if (last12Month.before(tmpDate)) {
											if (lastMxDataTmp == null) {
												SputumCulture sputumCulture = this.dataService
														.findSputumCultureByPtInfoIdAndMxId(
																tmp.getPtInfoId().longValue(), tmp.getId().longValue());
												if ((sputumCulture != null) && (sputumCulture.getSpcuResult() != 0)
														&& (sputumCulture.getSpcuResult() != 6)) {
													last5SputumCulture.add(sputumCulture);
													lastMxDataTmp = tmp;
												}
											} else {
												Date d = DateUtil.dateOffset(lastMxDataTmp.getDate(), -30);
												if ((tmpDate.before(d)) || (d.equals(tmpDate))) {
													SputumCulture sputumCulture = this.dataService
															.findSputumCultureByPtInfoIdAndMxId(
																	tmp.getPtInfoId().longValue(),
																	tmp.getId().longValue());
													if ((sputumCulture != null) && (sputumCulture.getSpcuResult() != 0)
															&& (sputumCulture.getSpcuResult() != 6)) {
														last5SputumCulture.add(sputumCulture);
														lastMxDataTmp = tmp;
													}
												}
											}
										}
									}
									int last5SputumCultureSize = last5SputumCulture.size();
									if (last5SputumCultureSize == 5) {
										int one = ((SputumCulture) last5SputumCulture.get(0)).getSpcuResult();
										int two = ((SputumCulture) last5SputumCulture.get(1)).getSpcuResult();
										int three = ((SputumCulture) last5SputumCulture.get(2)).getSpcuResult();
										int four = ((SputumCulture) last5SputumCulture.get(3)).getSpcuResult();
										int five = ((SputumCulture) last5SputumCulture.get(4)).getSpcuResult();
										if (((one == -11) && (two == -11) && (three == -11) && (four == -11)
												&& (five == -11))
												|| ((one == -11) && (two == -11) && (three == -11) && (four == -11)
														&& (five > 0))
												|| ((one == -11) && (two == -11) && (three == -11) && (four > 0)
														&& (five == -11))) {
											if (isMxDataLast) {
												SputumCulture sc = this.dataService.findSputumCultureByPtInfoIdAndMxId(
														mxDataLast.getPtInfoId().longValue(),
														mxDataLast.getId().longValue());
												if ((sc != null) && (sc.getSpcuResult() != 0)
														&& (sc.getSpcuResult() != 6)) {
													cure++;
													curePtInfo.add(ptInfo);
												} else {
													complete++;
													completePtInfo.add(ptInfo);
												}
											} else {
												complete++;
												completePtInfo.add(ptInfo);
											}
										} else {
											failPtInfo.add(ptInfo);
											fail++;
										}
									} else {
										List<SputumCulture> last2SputumCulture = new ArrayList(2);
										for (int s = size - 1; s >= 0; s--) {
											MxData tmp = (MxData) mxDataList.get(s);
											if (last2SputumCulture.size() >= 2) {
												break;
											}
											if (tmp.getDate().after(last12Month)) {
												SputumCulture sputumCulture = this.dataService
														.findSputumCultureByPtInfoIdAndMxId(
																tmp.getPtInfoId().longValue(), tmp.getId().longValue());
												if ((sputumCulture != null) && (sputumCulture.getSpcuResult() > 0)) {
													last2SputumCulture.add(sputumCulture);
												}
											}
										}
										int last2SputumCultureSize = last2SputumCulture.size();
										if (last2SputumCultureSize >= 2) {
											fail++;
											failPtInfo.add(ptInfo);
										} else if (last2SputumCultureSize == 1) {
											Date endDate = DateUtil.dateOffset(DateUtil.monthOffset(maxBaseDate, 1),
													-14);
											long interval = DateUtil.Interval(
													((SputumCulture) last2SputumCulture.get(0)).getDate(), endDate);
											if (interval < 90L) {
												fail++;
												failPtInfo.add(ptInfo);
											} else {
												complete++;
												completePtInfo.add(ptInfo);
											}
										} else {
											complete++;
											completePtInfo.add(ptInfo);
										}
									}
								} else {
									Date lastDate = DateUtil.dateOffset(DateUtil.monthOffset(mxDataLast.getDate(), 3),
											-16);
									Date currentDate = new Date();
									if ((currentDate.after(lastDate)) || (currentDate.equals(lastDate))) {
										lose++;
										losePtInfo.add(ptInfo);
									} else {
										inCure++;
										inCurePtInfo.add(ptInfo);
									}
								}
							}
						}
					}
				}
			}
			reportResponse.setComplete(complete);
			reportResponse.setDeath(death);
			reportResponse.setFail(fail);
			reportResponse.setVoluntary(voluntary);
			reportResponse.setExclude(exclude);
			reportResponse.setInCure(inCure);
			reportResponse.setCure(cure);
			reportResponse.setLose(lose);
			reportResponse.setNotTreat(notTreat);
			reportResponse.setOuter(out);
			reportResponse.setTotal(ptInfoList.size());
		}
		List<Long> completePtInfoIds = new ArrayList();
		for (PtInfo p : completePtInfo) {
			completePtInfoIds.add(p.getId());
		}
		List<Long> deathPtInfoIds = new ArrayList();
		for (PtInfo p : deathPtInfo) {
			deathPtInfoIds.add(p.getId());
		}
		List<Long> failPtInfoIds = new ArrayList();
		for (PtInfo p : failPtInfo) {
			failPtInfoIds.add(p.getId());
		}
		Object voluntaryPtInfoIds = new ArrayList();
		for (PtInfo p : voluntaryPtInfo) {
			((List) voluntaryPtInfoIds).add(p.getId());
		}
		List<Long> excludePtInfoIds = new ArrayList();
		for (PtInfo p : excludePtInfo) {
			excludePtInfoIds.add(p.getId());
		}
		List<Long> inCurePtInfoIds = new ArrayList();
		for (PtInfo p : inCurePtInfo) {
			inCurePtInfoIds.add(p.getId());
		}
		List<Long> losePtInfoIds = new ArrayList();
		for (PtInfo p : losePtInfo) {
			losePtInfoIds.add(p.getId());
		}
		List<Long> curePtInfoIds = new ArrayList();
		for (PtInfo p : curePtInfo) {
			curePtInfoIds.add(p.getId());
		}
		List<Long> outPtInfoIds = new ArrayList();
		for (PtInfo p : outPtInfo) {
			outPtInfoIds.add(p.getId());
		}
		ptInfoList.removeAll(completePtInfo);
		ptInfoList.removeAll(deathPtInfo);
		ptInfoList.removeAll(failPtInfo);
		ptInfoList.removeAll(voluntaryPtInfo);
		ptInfoList.removeAll(excludePtInfo);
		ptInfoList.removeAll(inCurePtInfo);
		ptInfoList.removeAll(losePtInfo);
		ptInfoList.removeAll(curePtInfo);
		ptInfoList.removeAll(outPtInfo);
		ptInfoList.removeAll(tmpPtInfo);
		ptInfoList.removeAll(notTreatTmpPtInfo);

		notTreatPtInfo.removeAll(tmpPtInfo);
		if (!ptInfoList.isEmpty()) {
			notTreatPtInfo.addAll(ptInfoList);
		}
		List<Long> notTreatPtInfoIds = new ArrayList();
		for (PtInfo p : notTreatPtInfo) {
			if (!notTreatPtInfoIds.contains(p.getId())) {
				notTreatPtInfoIds.add(p.getId());
			}
			if ((completePtInfoIds.contains(p.getId())) || (deathPtInfoIds.contains(p.getId()))
					|| (failPtInfoIds.contains(p.getId())) || (inCurePtInfoIds.contains(p.getId()))
					|| (losePtInfoIds.contains(p.getId())) || (curePtInfoIds.contains(p.getId()))
					|| (((List) voluntaryPtInfoIds).contains(p.getId())) || (excludePtInfoIds.contains(p.getId()))
					|| (outPtInfoIds.contains(p.getId()))) {
				notTreatPtInfoIds.remove(p.getId());
			}
		}
		reportResponse.setNotTreat(notTreatPtInfoIds.size());

		reportResponse.setCompletePtInfo(completePtInfoIds);
		reportResponse.setDeathPtInfo(deathPtInfoIds);
		reportResponse.setFailPtInfo(failPtInfoIds);
		reportResponse.setInCurePtInfo(inCurePtInfoIds);
		reportResponse.setLosePtInfo(losePtInfoIds);
		reportResponse.setCurePtInfo(curePtInfoIds);
		reportResponse.setNotTreatPtInfo(notTreatPtInfoIds);
		reportResponse.setVoluntaryPtInfo((List) voluntaryPtInfoIds);
		reportResponse.setExcludePtInfo(excludePtInfoIds);
		reportResponse.setOuterPtInfo(outPtInfoIds);
		return reportResponse;
	}

	private boolean isDataIncomplete(String m, long ptInfoid, long mxId) {
		if (mxId <= 0)
			return false;
		String[] mArray = m.split(",");
		for (int i = 0; i < mArray.length; i++) {
			String s = mArray[i];
			switch (s) {
			case "ttp":
				SputumSmear sputumSmear = dataService.findSputumSmearByPtInfoIdAndMxId(ptInfoid, mxId);
				if (sputumSmear == null)
					return true;
				break;
			case "tpy":
				SputumCulture sputumCulture = dataService.findSputumCultureByPtInfoIdAndMxId(ptInfoid, mxId);
				if (sputumCulture == null)
					return true;
				break;
			case "xp":
				ChestRadioGraph chestRadioGraph = dataService.findChestRadioGraphByPtInfoIdAndMxId(ptInfoid, mxId);
				if (chestRadioGraph == null)
					return true;
				break;
			case "ctym":
				DrugScpt drugScpt = dataService.findDrugScptByPtInfoIdAndMxId(ptInfoid, mxId);
				if (drugScpt == null)
					return true;
				break;
			case "jyxp":
				GeneChip geneChip = dataService.findGeneChipByPtInfoIdAndMxId(ptInfoid, mxId);
				if (geneChip == null)
					return true;
				break;
			case "genexpert":
				GeneXpert geneXpert = dataService.findGeneXpertByPtInfoIdAndMxId(ptInfoid, mxId);
				if (geneXpert == null)
					return true;
				break;
			case "hain":
				Hain hain = dataService.findHainByPtInfoIdAndMxId(ptInfoid, mxId);
				if (hain == null)
					return true;
				break;
			case "tj":
				PhysicalExam physicalExam = dataService.findPhysicalExamByPtInfoIdAndMxId(ptInfoid, mxId);
				if (physicalExam == null)
					return true;
				break;
			case "xcg":
				BloodRoutine bloodRoutine = dataService.findBloodRoutineByPtInfoIdAndMxId(ptInfoid, mxId);
				if (bloodRoutine == null)
					return true;
				break;
			case "ncg":
				UrineRoutine urineRoutine = dataService.findUrineRoutineByPtInfoIdAndMxId(ptInfoid, mxId);
				if (urineRoutine == null)
					return true;
				break;
			case "ggn":
				LiverFunc liverFunc = dataService.findLiverFuncByPtInfoIdAndMxId(ptInfoid, mxId);
				if (liverFunc == null)
					return true;
				break;
			case "sgn":
				RenalFunc renalFunc = dataService.findRenalFuncByPtInfoIdAndMxId(ptInfoid, mxId);
				if (renalFunc == null)
					return true;
				break;
			case "myx":
				Immunology immunology = dataService.findImmunologyByPtInfoIdAndMxId(ptInfoid, mxId);
				if (immunology == null)
					return true;
				break;
			}
		}
		return false;
	}

	private int dataStatus(MxData mx, DiagnosisSetting setting) {

		Date mxDate = mx.getDate();
		Date currentDate = new Date();
		if ((mxDate.after(currentDate) || currentDate.equals(mxDate)) && mx.getId() == 0)
			return CommonContants.DATA_STATUS_FUTURE;

		int dataStatus = CommonContants.DATA_STATUS_NOT_NEED;
		if (mx.getId() > 0)
			dataStatus = CommonContants.DATA_STATUS_DO;
		if (mxDate.after(currentDate) || currentDate.equals(mxDate) || setting == null)
			return dataStatus;

		switch (mx.getMx()) {
		case "M0":
			if (StringUtils.isNotBlank(setting.getM0()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M1":
			if (StringUtils.isNotBlank(setting.getM1()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M2":
			if (StringUtils.isNotBlank(setting.getM2()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M3":
			if (StringUtils.isNotBlank(setting.getM3()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M4":
			if (StringUtils.isNotBlank(setting.getM4()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M5":
			if (StringUtils.isNotBlank(setting.getM5()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M6":
			if (StringUtils.isNotBlank(setting.getM6()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M7":
			if (StringUtils.isNotBlank(setting.getM7()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M8":
			if (StringUtils.isNotBlank(setting.getM8()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M9":
			if (StringUtils.isNotBlank(setting.getM9()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M10":
			if (StringUtils.isNotBlank(setting.getM10()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M11":
			if (StringUtils.isNotBlank(setting.getM11()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M12":
			if (StringUtils.isNotBlank(setting.getM12()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M13":
			if (StringUtils.isNotBlank(setting.getM13()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M14":
			if (StringUtils.isNotBlank(setting.getM14()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M15":
			if (StringUtils.isNotBlank(setting.getM15()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M16":
			if (StringUtils.isNotBlank(setting.getM16()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M17":
			if (StringUtils.isNotBlank(setting.getM17()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M18":
			if (StringUtils.isNotBlank(setting.getM18()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M19":
			if (StringUtils.isNotBlank(setting.getM19()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M20":
			if (StringUtils.isNotBlank(setting.getM20()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M21":
			if (StringUtils.isNotBlank(setting.getM21()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M22":
			if (StringUtils.isNotBlank(setting.getM22()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M23":
			if (StringUtils.isNotBlank(setting.getM23()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M24":
			if (StringUtils.isNotBlank(setting.getM24()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M25":
			if (StringUtils.isNotBlank(setting.getM25()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M26":
			if (StringUtils.isNotBlank(setting.getM26()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M27":
			if (StringUtils.isNotBlank(setting.getM27()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M28":
			if (StringUtils.isNotBlank(setting.getM28()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M29":
			if (StringUtils.isNotBlank(setting.getM29()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M30":
			if (StringUtils.isNotBlank(setting.getM30()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M31":
			if (StringUtils.isNotBlank(setting.getM31()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M32":
			if (StringUtils.isNotBlank(setting.getM32()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M33":
			if (StringUtils.isNotBlank(setting.getM33()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M34":
			if (StringUtils.isNotBlank(setting.getM34()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M35":
			if (StringUtils.isNotBlank(setting.getM35()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		case "M36":
			if (StringUtils.isNotBlank(setting.getM36()))
				dataStatus = this.dataStatusSearchData(mx);
			break;
		}

		return dataStatus;
	}

	private int dataStatusSearchData(MxData mx) {
		long ptInfoId = mx.getPtInfoId().longValue();
		long mxId = mx.getId().longValue();
		if (mxId == 0L) {
			return 2;
		}
		if ((this.dataService.findPhysicalExamByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findBloodRoutineByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findUrineRoutineByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findRenalFuncByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findChestRadioGraphByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findDrugScptByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findGeneChipByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findGeneXpertByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findHainByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findLiverFuncByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findSputumCultureByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findSputumSmearByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findTbDiagnosisByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findTherapyByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findTransInstByPtInfoIdAndMxId(ptInfoId, mxId) != null)
				|| (this.dataService.findBacterialTypeByPtInfoIdAndMxId(ptInfoId, mxId) != null)) {
			return 1;
		}
		return 2;
	}

	 private String getSettingItems(String mx, DiagnosisSetting setting) {

	        String m = null;
	        switch (mx) {
	            case "M0":
	                m = setting.getM0();
	                break;
	            case "M1":
	                m = setting.getM1();
	                break;
	            case "M2":
	                m = setting.getM2();
	                break;
	            case "M3":
	                m = setting.getM3();
	                break;
	            case "M4":
	                m = setting.getM4();
	                break;
	            case "M5":
	                m = setting.getM5();
	                break;
	            case "M6":
	                m = setting.getM6();
	                break;
	            case "M7":
	                m = setting.getM7();
	                break;
	            case "M8":
	                m = setting.getM8();
	                break;
	            case "M9":
	                m = setting.getM9();
	                break;
	            case "M10":
	                m = setting.getM10();
	                break;
	            case "M11":
	                m = setting.getM11();
	                break;
	            case "M12":
	                m = setting.getM12();
	                break;
	            case "M13":
	                m = setting.getM13();
	                break;
	            case "M14":
	                m = setting.getM14();
	                break;
	            case "M15":
	                m = setting.getM15();
	                break;
	            case "M16":
	                m = setting.getM16();
	                break;
	            case "M17":
	                m = setting.getM17();
	                break;
	            case "M18":
	                m = setting.getM18();
	                break;
	            case "M19":
	                m = setting.getM19();
	                break;
	            case "M20":
	                m = setting.getM20();
	                break;
	            case "M21":
	                m = setting.getM21();
	                break;
	            case "M22":
	                m = setting.getM22();
	                break;
	            case "M23":
	                m = setting.getM23();
	                break;
	            case "M24":
	                m = setting.getM24();
	                break;
	            case "M25":
	                m = setting.getM25();
	                break;
	            case "M26":
	                m = setting.getM26();
	                break;
	            case "M27":
	                m = setting.getM27();
	                break;
	            case "M28":
	                m = setting.getM28();
	                break;
	            case "M29":
	                m = setting.getM29();
	                break;
	            case "M30":
	                m = setting.getM30();
	                break;
	            case "M31":
	                m = setting.getM31();
	                break;
	            case "M32":
	                m = setting.getM32();
	                break;
	            case "M33":
	                m = setting.getM33();
	                break;
	            case "M34":
	                m = setting.getM34();
	                break;
	            case "M35":
	                m = setting.getM35();
	                break;
	            case "M36":
	                m = setting.getM36();
	                break;
	        }
	        return m;
	    }
}
