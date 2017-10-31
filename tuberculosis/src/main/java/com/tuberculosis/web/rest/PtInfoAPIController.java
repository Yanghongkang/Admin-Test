package com.tuberculosis.web.rest;

import com.tuberculosis.auth.Authentication;
import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.DiagnosisSetting;
import com.tuberculosis.entity.MxData;
import com.tuberculosis.entity.PtInfo;
import com.tuberculosis.entity.data.SputumCulture;
import com.tuberculosis.entity.data.SputumSmear;
import com.tuberculosis.entity.data.TbDiagnosis;
import com.tuberculosis.entity.data.Therapy;
import com.tuberculosis.entity.json.CommonResponse;
import com.tuberculosis.entity.json.PtInfoResponse;
import com.tuberculosis.entity.json.PtInfoResponseAscribeList;
import com.tuberculosis.entity.json.PtInfoResponseList;
import com.tuberculosis.service.data.DataService;
import com.tuberculosis.service.setting.DiagnosisSettingService;
import com.tuberculosis.service.setting.PtInfoService;
import com.tuberculosis.util.DateUtil;
import com.tuberculosis.util.LongDescComparator;
import com.tuberculosis.util.PtInfoDescComparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

@Controller
@RequestMapping({ "/rest/ptInfo" })
public class PtInfoAPIController {
	@Autowired
	private PtInfoService ptInfoService;
	@Autowired
	private DataService dataService;
	@Autowired
	private DiagnosisSettingService diagnosisSettingService;

	@RequestMapping(value = { "create" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@Authentication
	@ResponseBody
	public CommonResponse create(PtInfo ptInfo) {
		if ((StringUtils.isNotBlank(ptInfo.getHospitalNo()))
				&& (!this.ptInfoService.findByHospitalNo(ptInfo.getHospitalNo()).isEmpty())) {
			CommonResponse resp = new CommonResponse();
			resp.setErrorCode(100008);
			return resp;
		}
		this.ptInfoService.save(ptInfo);
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "edit" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@Authentication
	@ResponseBody
	public CommonResponse edit(PtInfo ptInfo) {
		if (StringUtils.isNotBlank(ptInfo.getHospitalNo())) {
			List<PtInfo> ptInfoListH = this.ptInfoService.findByHospitalNo(ptInfo.getHospitalNo());
			if ((!ptInfoListH.isEmpty()) && ((ptInfoListH.size() > 1) || ((ptInfoListH.size() == 1)
					&& (!((PtInfo) ptInfoListH.get(0)).getId().equals(ptInfo.getId()))))) {
				CommonResponse resp = new CommonResponse();
				resp.setErrorCode(100008);
				return resp;
			}
		}
		this.ptInfoService.save(ptInfo);
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "check/idCode" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse checkIdCode(@RequestParam("idCode") String idCode) {
		if ((StringUtils.isNotBlank(idCode)) && (!this.ptInfoService.getPtInfoByIdCode(idCode).isEmpty())) {
			CommonResponse resp = new CommonResponse();
			resp.setErrorCode(100007);
			return resp;
		}
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "check/edit/idCode" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse checkEditIdCode(@RequestParam("idCode") String idCode, @RequestParam("id") String id) {
		if (StringUtils.isNotBlank(idCode)) {
			List<PtInfo> ptInfoList = this.ptInfoService.getPtInfoByIdCode(idCode);
			if ((!ptInfoList.isEmpty()) && ((ptInfoList.size() > 1) || ((ptInfoList.size() == 1)
					&& (!((PtInfo) ptInfoList.get(0)).getId().equals(Long.valueOf(id)))))) {
				CommonResponse resp = new CommonResponse();
				resp.setErrorCode(100007);
				return resp;
			}
		}
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "delete/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public CommonResponse delete(@PathVariable("id") long id) {
		this.ptInfoService.delete(Long.valueOf(id));
		this.dataService.deletePtInfo(Long.valueOf(id));
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "death" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@Authentication
	@ResponseBody
	public CommonResponse death(PtInfo ptInfo) {
		this.ptInfoService.death(ptInfo);
		return new CommonResponse("success");
	}

	@RequestMapping(value = { "search" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList search(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize, ServletRequest request) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<PtInfo> ptInfos = this.ptInfoService.searchPtInfo(searchParams, pageNumber, pageSize);

		PtInfoResponseList response = new PtInfoResponseList();
		if (ptInfos != null) {
			List<PtInfoResponse> respList = new ArrayList();
			List<PtInfo> infos = ptInfos.getContent();
			for (PtInfo info : infos) {
				PtInfoResponse resp = new PtInfoResponse();
				resp.setId(info.getId());
				resp.setName(info.getName());
				resp.setSex(info.getSex());
				resp.setIdType(info.getIdType());
				resp.setIdCode(info.getIdCode());
				resp.setBirthDate(info.getBirthDate());
				resp.setContactNum(info.getContactNum());
				resp.setCurrAdds(info.getCurrAdds());
				resp.setNotes(info.getNotes());
				resp.setHospitalNo(info.getHospitalNo());
				resp.setDepartment(info.getDepartment());
				resp.setCity(info.getCity());
				resp.setDeathReason(info.getDeathReason());
				resp.setDeathDate(info.getDeathDate());

				List<MxData> mal = this.dataService.mxList(info.getId());
				boolean overDosage = false;
				boolean lowDosage = false;
				for (MxData mxData : mal) {
					if (mxData.getIsdosage() == 1) {
						overDosage = true;
					}
					if (mxData.getIsdosage() == 2) {
						lowDosage = true;
					}
					if (mxData.getIsdosage() == 3) {
						overDosage = true;
						lowDosage = true;
					}
					if ((overDosage & lowDosage)) {
						break;
					}
				}
				if ((overDosage) && (lowDosage)) {
					resp.setIsDosage(3);
				} else if (overDosage) {
					resp.setIsDosage(1);
				} else if (lowDosage) {
					resp.setIsDosage(2);
				} else {
					resp.setIsDosage(0);
				}
				respList.add(resp);
			}
			response.setTotalPage(ptInfos.getTotalPages());
			response.setPtInfoResponse(respList);
		}
		return response;
	}

	@RequestMapping(value = { "search/sputum" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList searchSputum(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "sputum", defaultValue = "1") int sputum,
			@RequestParam(value = "operator", defaultValue = "1") int operator,
			@RequestParam(value = "value", defaultValue = "0") int value, ServletRequest request) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		PtInfoResponseList response = new PtInfoResponseList();
		List<PtInfoResponse> respList = new ArrayList();
		List<PtInfo> ptInfos = searchSputum(sputum, operator, value);
		if (!ptInfos.isEmpty()) {
			int totalSize = ptInfos.size();
			int totalPages;
			if (totalSize % pageSize != 0) {
				totalPages = totalSize / pageSize + 1;
			} else {
				totalPages = totalSize / pageSize;
			}
			if (pageNumber > totalPages) {
				pageNumber = totalPages;
			}
			int beginIndex = (pageNumber - 1) * pageSize;
			int endIndex = beginIndex + pageSize;
			if (endIndex > totalSize) {
				endIndex = totalSize;
			}
			if (beginIndex > endIndex) {
				beginIndex = endIndex;
			}
			List<PtInfo> infos = ptInfos.subList(beginIndex, endIndex);
			for (PtInfo info : infos) {
				PtInfoResponse resp = new PtInfoResponse();
				resp.setId(info.getId());
				resp.setName(info.getName());
				resp.setSex(info.getSex());
				resp.setIdType(info.getIdType());
				resp.setIdCode(info.getIdCode());
				resp.setBirthDate(info.getBirthDate());
				resp.setContactNum(info.getContactNum());
				resp.setCurrAdds(info.getCurrAdds());
				resp.setNotes(info.getNotes());
				resp.setHospitalNo(info.getHospitalNo());
				resp.setDepartment(info.getDepartment());
				resp.setCity(info.getCity());

				List<MxData> mal = this.dataService.mxList(info.getId());
				boolean overDosage = false;
				boolean lowDosage = false;
				for (MxData mxData : mal) {
					if (mxData.getIsdosage() == 1) {
						overDosage = true;
					}
					if (mxData.getIsdosage() == 2) {
						lowDosage = true;
					}
					if (mxData.getIsdosage() == 3) {
						overDosage = true;
						lowDosage = true;
					}
					if ((overDosage & lowDosage)) {
						break;
					}
				}
				if ((overDosage) && (lowDosage)) {
					resp.setIsDosage(3);
				} else if (overDosage) {
					resp.setIsDosage(1);
				} else if (lowDosage) {
					resp.setIsDosage(2);
				} else {
					resp.setIsDosage(0);
				}
				respList.add(resp);
			}
			response.setTotalPage(totalPages);
			response.setPtInfoResponse(respList);
		}
		return response;
	}

	@RequestMapping(value = { "search/sputum/mx/rate" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public double searchSputumMxRate(@RequestParam(value = "sputum", defaultValue = "1") int sputum,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchBeginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchEndDate,
			@RequestParam("Mn") String Mn, @RequestParam(value = "type", defaultValue = "5,6") String type,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		List<Long> ptInfosYin = sputumMx(searchBeginDate, searchEndDate, Mn, sputum, 1, -11, type, dept, city);
		List<Long> ptInfosYang = sputumMx(searchBeginDate, searchEndDate, Mn, sputum, 2, 1, type, dept, city);

		long tmpId = 0L;
		Iterator<Long> ite = ptInfosYin.iterator();
		while (ite.hasNext()) {
			Long id = (Long) ite.next();
			if ((tmpId != 0L) && (id.equals(Long.valueOf(tmpId)))) {
				ite.remove();
			} else {
				tmpId = id.longValue();
			}
		}
		tmpId = 0L;
		ite = ptInfosYang.iterator();
		while (ite.hasNext()) {
			Long id = (Long) ite.next();
			if ((tmpId != 0L) && (id.equals(Long.valueOf(tmpId)))) {
				ite.remove();
			} else {
				tmpId = id.longValue();
			}
		}
		int yinSize = ptInfosYin.size();
		int yangSize = ptInfosYang.size();
		if (yinSize == 0) {
			return 0.0D;
		}
		int allSize = yinSize + yangSize;
		return Math.round(yinSize / allSize * 1000.0D) / 10.0D;
	}

	@RequestMapping(value = { "search/sputum/mx" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList searchSputumMx(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "sputum", defaultValue = "1") int sputum,
			@RequestParam(value = "operator", defaultValue = "1") int operator,
			@RequestParam(value = "value", defaultValue = "0") int value,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchBeginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchEndDate,
			@RequestParam("Mn") String Mn, @RequestParam(value = "type", defaultValue = "5,6") String type,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city, ServletRequest request) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		PtInfoResponseList response = new PtInfoResponseList();
		List<PtInfoResponse> respList = new ArrayList();
		List<Long> ptInfos = sputumMx(searchBeginDate, searchEndDate, Mn, sputum, operator, value, type, dept, city);
		if (!ptInfos.isEmpty()) {
			long tmpId = 0L;
			Iterator<Long> ite = ptInfos.iterator();
			while (ite.hasNext()) {
				Long id = (Long) ite.next();
				if ((tmpId != 0L) && (id.equals(Long.valueOf(tmpId)))) {
					ite.remove();
				} else {
					tmpId = id.longValue();
				}
			}
			Collections.sort(ptInfos, new LongDescComparator());
			int totalSize = ptInfos.size();
			int totalPages;
			if (totalSize % pageSize != 0) {
				totalPages = totalSize / pageSize + 1;
			} else {
				totalPages = totalSize / pageSize;
			}
			if (pageNumber > totalPages) {
				pageNumber = totalPages;
			}
			int beginIndex = (pageNumber - 1) * pageSize;
			int endIndex = beginIndex + pageSize;
			if (endIndex > totalSize) {
				endIndex = totalSize;
			}
			if (beginIndex > endIndex) {
				beginIndex = endIndex;
			}
			List<PtInfo> infos = this.ptInfoService.searchPtInfoInId(ptInfos.subList(beginIndex, endIndex));

			Collections.sort(infos, new PtInfoDescComparator());
			for (PtInfo info : infos) {
				PtInfoResponse resp = new PtInfoResponse();
				resp.setId(info.getId());
				resp.setName(info.getName());
				resp.setSex(info.getSex());
				resp.setIdType(info.getIdType());
				resp.setIdCode(info.getIdCode());
				resp.setBirthDate(info.getBirthDate());
				resp.setContactNum(info.getContactNum());
				resp.setCurrAdds(info.getCurrAdds());
				resp.setNotes(info.getNotes());
				resp.setHospitalNo(info.getHospitalNo());
				resp.setDepartment(info.getDepartment());
				resp.setCity(info.getCity());

				respList.add(resp);
			}
			response.setTotalPage(totalPages);
			response.setPtInfoResponse(respList);
		}
		return response;
	}

	private List<Long> sputumMx(Date beginDate, Date endDate, String Mn, int sputum, int operator, int value,
			String type, String dept, String city) {
		List<MxData> mxDataList = this.dataService.searchVisitMxDataInDate(endDate);
		List<MxData> visitMxDataList = new ArrayList();
		long ptInfoId_last;
		if ((mxDataList != null) && (!mxDataList.isEmpty())) {
			int tbDiagnosis_last = 0;
			int therapy_last = 0;
			Date m0_Date = null;
			ptInfoId_last = 0L;

			long tmpPtInfoId = 0L;

			boolean diagnosisChanged = false;
			boolean diagnosisChanged_last = false;
			boolean therapyChanged = false;
			for (MxData mx : mxDataList) {
				if (tmpPtInfoId != mx.getPtInfoId().longValue()) {
					PtInfo ptInfo = this.ptInfoService.getPtInfo(mx.getPtInfoId());
					if ((ptInfo == null) || ((!"all".equals(dept)) && (!dept.equals(ptInfo.getDepartment())))
							|| ((!"all".equals(city)) && (!city.equals(ptInfo.getCity())))) {
						tmpPtInfoId = mx.getPtInfoId().longValue();
					} else if ((ptInfo.getDeathDate() != null) && (StringUtils.isNotBlank(ptInfo.getDeathReason()))) {
						Date mDate = mx.getDate();
						if (mDate.after(ptInfo.getDeathDate())) {
						}
					} else {
						if ((ptInfoId_last != mx.getPtInfoId().longValue()) && (ptInfoId_last != 0L)) {
							tbDiagnosis_last = 0;
							therapy_last = 0;
							m0_Date = null;

							diagnosisChanged = false;
							diagnosisChanged_last = false;
							therapyChanged = false;
						}
						int tbDiagnosis = mx.getTbDiagnosis();

						int therapy = mx.getTherapy();
						if (-1 == therapy) {
							therapy = therapy_last;
						}
						if (-1 == tbDiagnosis) {
							tbDiagnosis = tbDiagnosis_last;
						}
						if ((tbDiagnosis != tbDiagnosis_last) || (diagnosisChanged_last)) {
							diagnosisChanged = true;
							diagnosisChanged_last = diagnosisChanged;
						}
						if (therapy == 1) {
							therapyChanged = true;
						}
						if ((m0_Date == null) && (therapy == 0)) {
							Date mDate = mx.getDate();
							mx.setMx("未治疗");
							if (((beginDate.equals(mDate)) || (beginDate.before(mDate)))
									&& ((endDate.equals(mDate)) || (endDate.after(mDate)))
									&& (("未治疗".equals(Mn)) || ("all".equals(Mn)))) {
								visitMxDataList.add(mx);
							}
						} else if ((diagnosisChanged) && (therapyChanged)) {
							m0_Date = mx.getDate();
							String mxRes = "M0";
							mx.setMx(mxRes);
							if (((beginDate.equals(m0_Date)) || (beginDate.before(m0_Date)))
									&& ((endDate.equals(m0_Date)) || (endDate.after(m0_Date)))
									&& ((mxRes.equals(Mn)) || ("all".equals(Mn)))) {
								visitMxDataList.add(mx);
							}
							diagnosisChanged = false;
							diagnosisChanged_last = false;
							therapyChanged = false;
						} else if (m0_Date != null) {
							Date mDate = mx.getDate();
							int mxSize = 33;
							if (5 == tbDiagnosis) {
								mxSize = 25;
							} else if (6 == tbDiagnosis) {
								mxSize = 31;
							}
							for (int i = 0; i < mxSize; i++) {
								Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
								Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
								Date tmpBeginDate = DateUtil.dateOffset(d, -14);
								Date tmpEndDate = DateUtil.dateOffset(d1, -14);
								if (((tmpBeginDate.before(mDate)) || (tmpBeginDate.equals(mDate)))
										&& (tmpEndDate.after(mDate))) {
									String mxRes = "M" + i;
									mx.setMx(mxRes);
									mx.setTbDiagnosis(tbDiagnosis);
									if (((!beginDate.equals(mDate)) && (!beginDate.before(mDate)))
											|| ((!endDate.equals(mDate)) && (!endDate.after(mDate)))
											|| ((!mxRes.equals(Mn)) && (!"all".equals(Mn)))) {
										break;
									}
									visitMxDataList.add(mx);

									break;
								}
							}
						}
						tbDiagnosis_last = tbDiagnosis;
						therapy_last = therapy;
						ptInfoId_last = mx.getPtInfoId().longValue();
					}
				}
			}
		}
		List<String> tbDiagnosis_search = null;
		if (StringUtils.isNotBlank(type)) {
			String[] type_search = type.split(",");
			tbDiagnosis_search = Arrays.asList(type_search);
		}
		List<Long> searchPtInfo = new ArrayList();
		for (MxData mx : visitMxDataList) {
			long ptInfoId = mx.getPtInfoId().longValue();
			long mxId = mx.getId().longValue();
			int tbDiagnosis = mx.getTbDiagnosis();
			if (((tbDiagnosis_search != null) && (tbDiagnosis_search.contains(String.valueOf(tbDiagnosis))))
					|| ("未治疗".equals(Mn)) || ("all".equals(Mn))) {
				if (1 == sputum) {
					SputumCulture sputumCulture = this.dataService.findSputumCultureByPtInfoIdAndMxId(ptInfoId, mxId);
					if (sputumCulture != null) {
						if (1 == operator) {
							if ((sputumCulture.getSpcuResult() == value)
									&& (!searchPtInfo.contains(Long.valueOf(ptInfoId)))) {
								searchPtInfo.add(Long.valueOf(ptInfoId));
							}
						} else if ((2 == operator) && (sputumCulture.getSpcuResult() >= value)
								&& (!searchPtInfo.contains(Long.valueOf(ptInfoId)))) {
							searchPtInfo.add(Long.valueOf(ptInfoId));
						}
					}
				} else if (2 == sputum) {
					SputumSmear sputumSmear = this.dataService.findSputumSmearByPtInfoIdAndMxId(ptInfoId, mxId);
					if (sputumSmear != null) {
						if (1 == operator) {
							if ((sputumSmear.getSpsmResult() == value)
									&& (!searchPtInfo.contains(Long.valueOf(ptInfoId)))) {
								searchPtInfo.add(Long.valueOf(ptInfoId));
							}
						} else if ((2 == operator) && (sputumSmear.getSpsmResult() >= value)
								&& (!searchPtInfo.contains(Long.valueOf(ptInfoId)))) {
							searchPtInfo.add(Long.valueOf(ptInfoId));
						}
					}
				}
			}
		}
		return searchPtInfo;
	}

	private List<PtInfo> searchSputum(int sputum, int operator, int value) {
		List<PtInfo> ptInfos = this.ptInfoService.findAll();
		List<PtInfo> searchPtInfo = new ArrayList();
		for (PtInfo p : ptInfos) {
			MxData lastMxData = this.dataService.findTop1ByPtInfoIdOrderByDateDesc(p.getId());
			if (lastMxData != null) {
				if (1 == sputum) {
					SputumCulture sputumCulture = this.dataService
							.findSputumCultureByPtInfoIdAndMxId(p.getId().longValue(), lastMxData.getId().longValue());
					if (sputumCulture != null) {
						if (1 == operator) {
							if (sputumCulture.getSpcuResult() == value) {
								searchPtInfo.add(p);
							}
						} else if ((2 == operator) && (sputumCulture.getSpcuResult() >= value)) {
							searchPtInfo.add(p);
						}
					}
				} else if (2 == sputum) {
					SputumSmear sputumSmear = this.dataService.findSputumSmearByPtInfoIdAndMxId(p.getId().longValue(),
							lastMxData.getId().longValue());
					if (sputumSmear != null) {
						if (1 == operator) {
							if (sputumSmear.getSpsmResult() == value) {
								searchPtInfo.add(p);
							}
						} else if ((2 == operator) && (sputumSmear.getSpsmResult() >= value)) {
							searchPtInfo.add(p);
						}
					}
				}
			}
		}
		return searchPtInfo;
	}

	@RequestMapping(value = { "searchVisitAll" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	@Deprecated
	public PtInfoResponseList searchVisitAll(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		return visitMxData(beginDate, endDate, pageSize, pageNumber, false);
	}

	@RequestMapping(value = { "searchVisitNot" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	@Deprecated
	public PtInfoResponseList searchVisitNot(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		return visitMxData(beginDate, endDate, pageSize, pageNumber, true);
	}

	@RequestMapping(value = { "searchVisitRatio" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	@Deprecated
	public long searchVisitRatio(@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		List<Long> visitAll = visitPtInfoIds(beginDate, endDate, false);
		List<Long> visitNot = visitPtInfoIds(beginDate, endDate, true);

		int visitAllSize = visitAll.size();
		int visitNotSize = visitNot.size();
		if ((visitAllSize == 0) || (visitNotSize == 0)) {
			return 0L;
		}
		return 100L - Math.round(visitNotSize / visitAllSize * 100.0D);
	}

	@RequestMapping(value = { "searchReturnAll" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList searchReturnAll(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		return returnMxDataByNkEdit(beginDate, endDate, pageSize, pageNumber, false, false, false, 0, dept, city);
	}

	@RequestMapping(value = { "searchReturnNot" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList searchReturnNot(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		return returnMxDataByNkEdit(beginDate, endDate, pageSize, pageNumber, true, false, false, 0, dept, city);
	}

	@RequestMapping(value = { "searchReturnRatio" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public long searchReturnRatio(@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		List<Long> returnAll = returnPtInfoIdsByNkEdit(beginDate, endDate, false, false, false, 0, dept, city);
		List<Long> returnNot = returnPtInfoIdsByNkEdit(beginDate, endDate, true, false, false, 0, dept, city);

		int returnAllSize = returnAll.size();
		int returnNotSize = returnNot.size();
		if (returnAllSize == 0) {
			return 0L;
		}
		int returnSize = returnAllSize - returnNotSize;
		return Math.round(returnSize / returnAllSize * 100.0D);
	}

	@RequestMapping(value = { "searchHadReturn" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList searchHadReturn(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		return returnMxData(beginDate, endDate, pageSize, pageNumber, true, true, false, 0, dept, city);
	}

	@RequestMapping(value = { "searchReturnOverTime" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseList searchReturnOverTime(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "days", defaultValue = "7") int days,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		return returnMxData(beginDate, endDate, pageSize, pageNumber, true, true, true, days, dept, city);
	}

	@RequestMapping(value = { "searchComplyRatio" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public long searchComplyRatio(@RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "days", defaultValue = "7") int days,
			@RequestParam(value = "dept", defaultValue = "all", required = false) String dept,
			@RequestParam(value = "city", defaultValue = "all", required = false) String city) {
		List<Long> complyAll = returnPtInfoIdsByNkEdit(beginDate, endDate, false, false, false, days, dept, city);
		List<Long> complyNot = returnPtInfoIds(beginDate, endDate, true, true, true, days, dept, city);

		int complyAllSize = complyAll.size();
		int complyNotSize = complyNot.size();
		if (complyAllSize == 0) {
			return 0L;
		}
		int complySize = complyAllSize - complyNotSize;
		return Math.round(complySize / complyAllSize * 100.0D);
	}

	@RequestMapping(value = { "{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponse getById(@PathVariable("id") Long id) {
		return searchById(id);
	}

	@RequestMapping(value = { "ascribe" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Authentication
	@ResponseBody
	public PtInfoResponseAscribeList getById(@RequestParam("ids") String ids) {
		String[] idArray = ids.split(",");
		PtInfoResponseAscribeList response = new PtInfoResponseAscribeList();
		if ((idArray != null) && (idArray.length > 0)) {
			List<PtInfoResponse> ptInfoResponsesList = new ArrayList();
			for (int i = 0; i < idArray.length; i++) {
				PtInfoResponse ptInfoResponse = searchAscribe(Long.valueOf(idArray[i]));
				List<MxData> mal = this.dataService.mxList(ptInfoResponse.getId());
				boolean overDosage = false;
				boolean lowDosage = false;
				for (MxData mxData : mal) {
					if (mxData.getIsdosage() == 1) {
						overDosage = true;
					}
					if (mxData.getIsdosage() == 2) {
						lowDosage = true;
					}
					if (mxData.getIsdosage() == 3) {
						overDosage = true;
						lowDosage = true;
					}
					if ((overDosage & lowDosage)) {
						break;
					}
				}
				if ((overDosage) && (lowDosage)) {
					ptInfoResponse.setIsDosage(3);
				} else if (overDosage) {
					ptInfoResponse.setIsDosage(1);
				} else if (lowDosage) {
					ptInfoResponse.setIsDosage(2);
				} else {
					ptInfoResponse.setIsDosage(0);
				}
				ptInfoResponsesList.add(ptInfoResponse);
			}
			response.setPtInfoResponse(ptInfoResponsesList);
		}
		return response;
	}

	private PtInfoResponse searchAscribe(Long id) {
		PtInfo info = this.ptInfoService.getPtInfo(id);
		PtInfoResponse resp = new PtInfoResponse();
		resp.setId(info.getId());
		resp.setName(info.getName());
		resp.setSex(info.getSex());
		resp.setIdType(info.getIdType());
		resp.setIdCode(info.getIdCode());
		resp.setBirthDate(info.getBirthDate());
		resp.setContactNum(info.getContactNum());
		resp.setCurrAdds(info.getCurrAdds());
		resp.setNotes(info.getNotes());
		resp.setHospitalNo(info.getHospitalNo());
		resp.setDepartment(info.getDepartment());
		resp.setCity(info.getCity());
		resp.setDeathReason(info.getDeathReason());
		resp.setDeathDate(info.getDeathDate());
		return resp;
	}

	private PtInfoResponse searchById(Long id) {
		PtInfo info = this.ptInfoService.getPtInfo(id);
		PtInfoResponse resp = new PtInfoResponse();
		if (info != null) {
			resp.setId(info.getId());
			resp.setName(info.getName());
			resp.setSex(info.getSex());
			resp.setIdType(info.getIdType());
			resp.setIdCode(info.getIdCode());
			resp.setBirthDate(info.getBirthDate());
			resp.setContactNum(info.getContactNum());
			resp.setCurrAdds(info.getCurrAdds());
			resp.setNotes(info.getNotes());
			resp.setDeathDate(info.getDeathDate());
			resp.setDeathReason(info.getDeathReason());
			resp.setHospitalNo(info.getHospitalNo());
			resp.setCity(info.getCity());
			resp.setDepartment(info.getDepartment());

			TbDiagnosis tbDiagnosis = this.dataService.findDbDiagnosisTop1ByPtInfoId(info.getId().longValue());
			if (tbDiagnosis != null) {
				resp.setTbDiagnosis(tbDiagnosis.getTbDiagnosis());
				resp.setOtherResult(tbDiagnosis.getOtherResult());
			}
			List<TbDiagnosis> tbDiagnosisList = this.dataService
					.findByPtInfoIdOrderByDateDesc(info.getId().longValue());
			resp.setTbDiagnosisList(tbDiagnosisList);

			Therapy therapy = this.dataService.findTherapyByPtInfoId(info.getId().longValue());
			if (therapy != null) {
				resp.setCurrTherapy(therapy.getCurrTherapy());
				resp.setAdjReason(therapy.getAdjReason());
				resp.setStopReason(therapy.getStopReason());
				resp.setTherapy(therapy.getTherapy());
				resp.setTherapyStatus(therapy.getTherapyStatus());
			}
			resp.setDrugScptList(this.dataService.findDrugScptOrderByDateAsc(id));
			resp.setGeneChipList(this.dataService.findGeneChipOrderByDateAsc(id));
			resp.setGeneXpertList(this.dataService.findGeneXpertOrderByDateAsc(id));
			resp.setHainList(this.dataService.findHainOrderByDateAsc(id));
		} else {
			resp.setErrorCode(100006);
		}
		return resp;
	}

	private PtInfoResponseList visitMxData(Date beginDate, Date endDate, int pageSize, int pageNumber,
			boolean isCheckNotVisit) {
		PtInfoResponseList response = new PtInfoResponseList();
		List<PtInfoResponse> respList = new ArrayList();
		List<Long> ptInfoIds = visitPtInfoIds(beginDate, endDate, isCheckNotVisit);
		if (!ptInfoIds.isEmpty()) {
			Collections.sort(ptInfoIds);
			int totalSize = ptInfoIds.size();
			int totalPages;
			if (totalSize % pageSize != 0) {
				totalPages = totalSize / pageSize + 1;
			} else {
				totalPages = totalSize / pageSize;
			}
			if (pageNumber > totalPages) {
				pageNumber = totalPages;
			}
			int beginIndex = (pageNumber - 1) * pageSize;
			int endIndex = beginIndex + pageSize;
			if (endIndex > totalSize) {
				endIndex = totalSize;
			}
			if (beginIndex > endIndex) {
				beginIndex = endIndex;
			}
			List<PtInfo> infos = this.ptInfoService.searchPtInfoInId(ptInfoIds.subList(beginIndex, endIndex));
			for (PtInfo info : infos) {
				PtInfoResponse resp = new PtInfoResponse();
				resp.setId(info.getId());
				resp.setName(info.getName());
				resp.setSex(info.getSex());
				resp.setIdType(info.getIdType());
				resp.setIdCode(info.getIdCode());
				resp.setBirthDate(info.getBirthDate());
				resp.setContactNum(info.getContactNum());
				resp.setCurrAdds(info.getCurrAdds());
				resp.setNotes(info.getNotes());
				resp.setHospitalNo(info.getHospitalNo());

				respList.add(resp);
			}
			response.setTotalPage(totalPages);
			response.setPtInfoResponse(respList);
		}
		return response;
	}

	private List<Long> visitPtInfoIds(Date beginDate, Date endDate, boolean isCheckNotVisit) {

		List<DiagnosisSetting> settings = diagnosisSettingService.getAllSetting();
		Map<Integer, DiagnosisSetting> settingMap = new HashMap<>();
		for (DiagnosisSetting setting : settings) {
			settingMap.put(setting.getDiagnosisType(), setting);
		}

		List<MxData> mxDataList = dataService.searchVisitMxDataInDate(endDate);
		List<MxData> visitMxDataList = new ArrayList<>();

		if (mxDataList != null && !mxDataList.isEmpty()) {
			int tbDiagnosis_last = 0;
			int therapy_last = 0;
			MxData mxData_last = null;
			Date m0_Date = null;
			int m_index_last = 0;
			long ptInfoId_last = 0;
			boolean isGot = false;
			String mxRes = CommonContants.MX_NOT_TREAT;

			for (MxData mx : mxDataList) {
				if (ptInfoId_last != mx.getPtInfoId() && ptInfoId_last != 0) {

					// if(!isGot && m0_Date != null && mxData_last != null &&
					// mxData_last.getMx().startsWith("M")) {
					if (!isGot && m0_Date != null && mxData_last != null && mxData_last.getMx().startsWith("M")) {
						int mxSize = 33;
						if (CommonContants.DIAGNOSIS_TYPE_NAIDUOYAO == tbDiagnosis_last) {
							mxSize = 25;
						} else if (CommonContants.DIAGNOSIS_TYPE_GUANGFAN_NY == tbDiagnosis_last) {
							mxSize = 31;
						}
						for (int i = m_index_last + 1; i < mxSize; i++) {
							Date d = DateUtil.monthOffset(m0_Date, i);
							if ((beginDate.equals(d) || beginDate.before(d))
									&& (endDate.equals(d) || endDate.after(d))) {
								MxData tmpMxData = new MxData();
								tmpMxData.setId(0L);
								tmpMxData.setMx("M" + i);
								tmpMxData.setPtInfoId(mxData_last.getPtInfoId());
								tmpMxData.setTbDiagnosis(tbDiagnosis_last);
								tmpMxData.setDate(d);
								visitMxDataList.add(tmpMxData);
								break;
							}
						}
					}
					tbDiagnosis_last = 0;
					therapy_last = 0;
					mxData_last = null;
					m0_Date = null;
					m_index_last = 0;
					ptInfoId_last = 0;
					isGot = false;
					mxRes = CommonContants.MX_NOT_TREAT;
				}
				// else if(isGot) {
				// continue;
				// }

				int tbDiagnosis = mx.getTbDiagnosis();
				int therapy = mx.getTherapy();
				if (CommonContants.MX_DATA_NO_THERAPY == therapy) {
					therapy = therapy_last;
				}
				if (CommonContants.MX_DATA_NO_TbDiagnosis == tbDiagnosis) {
					tbDiagnosis = tbDiagnosis_last;
				}
				if (CommonContants.THEARAPY_STATUS_STOP == therapy || CommonContants.THEARAPY_STATUS_STOP_BAD == therapy
						|| CommonContants.THEARAPY_STATUS_STOP_Voluntary == therapy
						|| CommonContants.THEARAPY_STATUS_STOP_EXCLUDE == therapy
						|| (m0_Date == null && CommonContants.THEARAPY_STATUS_NOT == therapy)) {
					// continue;
					// Date mDate = mx.getDate();
					mx.setMx(CommonContants.MX_NOT_TREAT);
					mx.setTbDiagnosis(tbDiagnosis);
					if (CommonContants.THEARAPY_STATUS_STOP == therapy
							|| CommonContants.THEARAPY_STATUS_STOP_BAD == therapy
							|| CommonContants.THEARAPY_STATUS_STOP_Voluntary == therapy
							|| CommonContants.THEARAPY_STATUS_STOP_EXCLUDE == therapy) {
						visitMxDataList.add(mx);
						isGot = true;
					}
				} else if ((tbDiagnosis != tbDiagnosis_last && CommonContants.THEARAPY_STATUS_STOP != therapy
						&& CommonContants.THEARAPY_STATUS_STOP_BAD != therapy
						&& CommonContants.THEARAPY_STATUS_STOP_Voluntary != therapy
						&& CommonContants.THEARAPY_STATUS_STOP_EXCLUDE != therapy)
						|| (therapy_last == 0 && CommonContants.THEARAPY_STATUS_CREATE == therapy)) {
					m0_Date = mx.getDate();
					m_index_last = 0;
					mxRes = "M0";
					mx.setMx(mxRes);
					if ((beginDate.equals(m0_Date) || beginDate.before(m0_Date))
							&& (endDate.equals(m0_Date) || endDate.after(m0_Date))) {
						visitMxDataList.add(mx);
						isGot = true;
					}
				} else if (m0_Date != null) {
					Date mDate = mx.getDate();
					int mxSize = 33;
					if (CommonContants.DIAGNOSIS_TYPE_NAIDUOYAO == tbDiagnosis) {
						mxSize = 25;
					} else if (CommonContants.DIAGNOSIS_TYPE_GUANGFAN_NY == tbDiagnosis) {
						mxSize = 31;
					}
					for (int i = 0; i < mxSize; i++) {
						Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
						Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
						Date tmpBeginDate = DateUtil.dateOffset(d, -14);
						Date tmpEndDate = DateUtil.dateOffset(d1, -14);

						if ((tmpBeginDate.before(mDate) || tmpBeginDate.equals(mDate)) && tmpEndDate.after(mDate)) {
							if (CommonContants.THEARAPY_STATUS_NOT == therapy) {
								mxRes = "M" + i;
							} else {
								m_index_last = i;
								mxRes = "M" + i;
							}

							mx.setMx(mxRes);
							mx.setTbDiagnosis(tbDiagnosis);
							if ((beginDate.equals(mDate) || beginDate.before(mDate))
									&& (endDate.equals(mDate) || endDate.after(mDate))) {
								visitMxDataList.add(mx);
								isGot = true;
							}
							break;
						} else {
							mxRes = "M" + i;
							if (m_index_last >= i)
								continue;
							if ((beginDate.equals(d) || beginDate.before(d))
									&& (endDate.equals(d) || endDate.after(d))) {
								MxData tmpMxData = new MxData();
								tmpMxData.setId(0L);
								tmpMxData.setMx(mxRes);
								tmpMxData.setPtInfoId(ptInfoId_last);
								tmpMxData.setTbDiagnosis(tbDiagnosis_last);
								tmpMxData.setDate(d);
								visitMxDataList.add(tmpMxData);
								isGot = true;
							}
						}
					}
				}
				mxData_last = mx;
				mxData_last.setMx(mxRes);
				tbDiagnosis_last = tbDiagnosis;
				therapy_last = therapy;
				ptInfoId_last = mx.getPtInfoId();
			}

			// 补单
			if (!isGot && ptInfoId_last != 0 && m0_Date != null && mxData_last != null
					&& mxData_last.getMx().startsWith("M")) {
				int mxSize = 33;
				if (CommonContants.DIAGNOSIS_TYPE_NAIDUOYAO == tbDiagnosis_last) {
					mxSize = 25;
				} else if (CommonContants.DIAGNOSIS_TYPE_GUANGFAN_NY == tbDiagnosis_last) {
					mxSize = 31;
				}
				for (int i = m_index_last + 1; i < mxSize; i++) {
					Date d = DateUtil.monthOffset(m0_Date, i);
					if ((beginDate.equals(d) || beginDate.before(d)) && (endDate.equals(d) || endDate.after(d))) {
						MxData tmpMxData = new MxData();
						tmpMxData.setId(0L);
						tmpMxData.setMx("M" + i);
						tmpMxData.setPtInfoId(mxData_last.getPtInfoId());
						tmpMxData.setTbDiagnosis(tbDiagnosis_last);
						tmpMxData.setDate(d);
						visitMxDataList.add(tmpMxData);
						break;
					}
				}
			}
		}

		List<Long> ptInfoIds = new ArrayList<>();
		Long tmpPtInfoId = 0L;
		for (MxData mx : visitMxDataList) {
			Long ptInfoId = mx.getPtInfoId();
			if (ptInfoIds.contains(ptInfoId))
				continue;
			if (CommonContants.THEARAPY_STATUS_STOP == mx.getTherapy()
					|| CommonContants.THEARAPY_STATUS_STOP_BAD == mx.getTherapy()
					|| CommonContants.THEARAPY_STATUS_STOP_Voluntary == mx.getTherapy()
					|| CommonContants.THEARAPY_STATUS_STOP_EXCLUDE == mx.getTherapy()) {
				tmpPtInfoId = ptInfoId;
				ptInfoIds.remove(tmpPtInfoId);
				continue;
			}
			if (tmpPtInfoId != 0 && tmpPtInfoId == ptInfoId) {
				ptInfoIds.remove(tmpPtInfoId);
				continue;
			}

			DiagnosisSetting setting = settingMap.get(mx.getTbDiagnosis());
			// PtInfo ptInfo = ptInfoService.getPtInfo(ptInfoId);
			if (setting != null && importanceMonthOrData(mx, setting, isCheckNotVisit, false))
				ptInfoIds.add(ptInfoId);
			// ptInfoIds.add(ptInfoId);

			// && ptInfo.getDeathDate() == null &&
			// StringUtils.isBlank(ptInfo.getDeathReason())
		}
		return ptInfoIds;
	}

	private PtInfoResponseList returnMxData(Date beginDate, Date endDate, int pageSize, int pageNumber,
			boolean isCheckNotReturn, boolean hadReturn, boolean isCountNotComply, int days, String dept, String city) {
		PtInfoResponseList response = new PtInfoResponseList();
		List<PtInfoResponse> respList = new ArrayList();
		List<Long> ptInfoIds = returnPtInfoIds(beginDate, endDate, isCheckNotReturn, hadReturn, isCountNotComply, days,
				dept, city);
		if (!ptInfoIds.isEmpty()) {
			Collections.sort(ptInfoIds, new LongDescComparator());
			int totalSize = ptInfoIds.size();
			int totalPages;
			if (totalSize % pageSize != 0) {
				totalPages = totalSize / pageSize + 1;
			} else {
				totalPages = totalSize / pageSize;
			}
			if (pageNumber > totalPages) {
				pageNumber = totalPages;
			}
			int beginIndex = (pageNumber - 1) * pageSize;
			int endIndex = beginIndex + pageSize;
			if (endIndex > totalSize) {
				endIndex = totalSize;
			}
			if (beginIndex > endIndex) {
				beginIndex = endIndex;
			}
			List<PtInfo> infos = this.ptInfoService.searchPtInfoInId(ptInfoIds.subList(beginIndex, endIndex));

			Collections.sort(infos, new PtInfoDescComparator());
			for (PtInfo info : infos) {
				PtInfoResponse resp = new PtInfoResponse();
				resp.setId(info.getId());
				resp.setName(info.getName());
				resp.setSex(info.getSex());
				resp.setIdType(info.getIdType());
				resp.setIdCode(info.getIdCode());
				resp.setBirthDate(info.getBirthDate());
				resp.setContactNum(info.getContactNum());
				resp.setCurrAdds(info.getCurrAdds());
				resp.setNotes(info.getNotes());
				resp.setHospitalNo(info.getHospitalNo());
				resp.setDepartment(info.getDepartment());
				resp.setCity(info.getCity());

				List<MxData> mal = this.dataService.mxList(info.getId());
				boolean overDosage = false;
				boolean lowDosage = false;
				for (MxData mxData : mal) {
					if (mxData.getIsdosage() == 1) {
						overDosage = true;
					}
					if (mxData.getIsdosage() == 2) {
						lowDosage = true;
					}
					if (mxData.getIsdosage() == 3) {
						overDosage = true;
						lowDosage = true;
					}
					if ((overDosage & lowDosage)) {
						break;
					}
				}
				if ((overDosage) && (lowDosage)) {
					resp.setIsDosage(3);
				} else if (overDosage) {
					resp.setIsDosage(1);
				} else if (lowDosage) {
					resp.setIsDosage(2);
				} else {
					resp.setIsDosage(0);
				}
				respList.add(resp);
			}
			response.setTotalPage(totalPages);
			response.setPtInfoResponse(respList);
		}
		return response;
	}

	private List<Long> returnPtInfoIds(Date beginDate, Date endDate, boolean isCheckNotReturn, boolean hadReturn,
			boolean isCountNotComply, int days, String dept, String city) {
		List<DiagnosisSetting> settings = this.diagnosisSettingService.getAllSetting();
		Map<Integer, DiagnosisSetting> settingMap = new HashMap();
		for (DiagnosisSetting setting : settings) {
			settingMap.put(Integer.valueOf(setting.getDiagnosisType()), setting);
		}
		List<MxData> mxDataList = this.dataService.searchVisitMxDataInDate(DateUtil.monthOffset(endDate, 2));
		List<MxData> returnMxDataList = new ArrayList<>();
		List<Long> complyMxDataList = new ArrayList();
		if ((mxDataList != null) && (!mxDataList.isEmpty())) {
			int tbDiagnosis_last = 0;
			int therapy_last = 0;
			MxData mxData_last = null;
			Date m0_Date = null;
			int m_index_last = 0;
			long ptInfoId_last = 0L;
			boolean isGot = false;
			String mxRes = "未治疗";
			long tmpPtInfoId = 0L;
			for (MxData mx : mxDataList) {
				if (tmpPtInfoId != mx.getPtInfoId().longValue()) {
					PtInfo ptInfo = this.ptInfoService.getPtInfo(mx.getPtInfoId());
					if ((ptInfo == null) || ((!"all".equals(dept)) && (!dept.equals(ptInfo.getDepartment())))
							|| ((!"all".equals(city)) && (!city.equals(ptInfo.getCity())))) {
						tmpPtInfoId = mx.getPtInfoId().longValue();
					} else {
						if ((ptInfoId_last != mx.getPtInfoId().longValue()) && (ptInfoId_last != 0L)) {
							if ((!isGot) && (m0_Date != null) && (mxData_last != null)
									&& (mxData_last.getMx().startsWith("M")) && (!isCountNotComply)) {
								int mxSize = 33;
								if (5 == tbDiagnosis_last) {
									mxSize = 25;
								} else if (6 == tbDiagnosis_last) {
									mxSize = 31;
								}
								for (int i = m_index_last + 1; i < mxSize; i++) {
									Date d = DateUtil.monthOffset(m0_Date, i);
									if (((beginDate.equals(d)) || (beginDate.before(d)))
											&& ((endDate.equals(d)) || (endDate.after(d)))) {
										MxData tmpMxData = new MxData();
										tmpMxData.setId(Long.valueOf(0L));
										tmpMxData.setMx("M" + i);
										tmpMxData.setPtInfoId(mxData_last.getPtInfoId());
										tmpMxData.setTbDiagnosis(tbDiagnosis_last);
										tmpMxData.setDate(d);
										returnMxDataList.add(tmpMxData);
										break;
									}
								}
							}
							tbDiagnosis_last = 0;
							therapy_last = 0;
							mxData_last = null;
							m0_Date = null;
							m_index_last = 0;
							ptInfoId_last = 0L;
							isGot = false;
							mxRes = "未治疗";
						}
						int tbDiagnosis = mx.getTbDiagnosis();
						int therapy = mx.getTherapy();
						if (-1 == therapy) {
							therapy = therapy_last;
						}
						if (-1 == tbDiagnosis) {
							tbDiagnosis = tbDiagnosis_last;
						}
						if ((ptInfo.getDeathDate() != null) && (StringUtils.isNotBlank(ptInfo.getDeathReason()))) {
							if (beginDate.after(ptInfo.getDeathDate())) {
								isGot = true;
								ptInfoId_last = mx.getPtInfoId().longValue();
								mxData_last = mx;
								tbDiagnosis_last = tbDiagnosis;
								therapy_last = therapy;
								continue;
							}
						}
						if ((3 == therapy) || (4 == therapy) || (5 == therapy) || (6 == therapy) || (7 == therapy)
								|| ((m0_Date == null) && (therapy == 0))) {
							mx.setMx("未治疗");
							mx.setTbDiagnosis(tbDiagnosis);
							if ((3 == therapy) || (4 == therapy) || (5 == therapy) || (6 == therapy)
									|| (7 == therapy)) {
								Date mDate = mx.getDate();
								if ((!isCountNotComply) && ((beginDate.equals(mDate)) || (beginDate.before(mDate)))
										&& ((endDate.equals(mDate)) || (endDate.after(mDate)))) {
									((List) returnMxDataList).add(mx);
								}
								isGot = true;
							}
						} else if (((tbDiagnosis != tbDiagnosis_last) && (3 != therapy) && (4 != therapy)
								&& (5 != therapy) && (6 != therapy)) || ((therapy_last == 0) && (1 == therapy))) {
							m0_Date = mx.getDate();
							m_index_last = 0;
							mxRes = "M0";
							mx.setMx(mxRes);
							mx.setTbDiagnosis(tbDiagnosis);
							if ((!isCountNotComply) && ((beginDate.equals(m0_Date)) || (beginDate.before(m0_Date)))
									&& ((endDate.equals(m0_Date)) || (endDate.after(m0_Date)))) {
								isGot = true;
							}
						} else if (m0_Date != null) {
							Date mDate = mx.getDate();
							int mxSize = 33;
							if (5 == tbDiagnosis) {
								mxSize = 25;
							} else if (6 == tbDiagnosis) {
								mxSize = 31;
							}
							for (int i = 1; i < mxSize; i++) {
								Date d = i == 0 ? m0_Date : DateUtil.monthOffset(m0_Date, i);
								Date d1 = DateUtil.monthOffset(m0_Date, i + 1);
								Date tmpBeginDate = DateUtil.dateOffset(d, -14);
								Date tmpEndDate = DateUtil.dateOffset(d1, -14);
								if (((mDate.after(tmpBeginDate)) || (tmpBeginDate.equals(mDate)))
										&& (mDate.before(tmpEndDate))) {
									if (therapy == 0) {
										mxRes = "M" + i + "X";
										m_index_last = i;
									} else {
										m_index_last = i;
										mxRes = "M" + i;
									}
									mx.setMx(mxRes);
									mx.setTbDiagnosis(tbDiagnosis);
									if (((!beginDate.equals(d)) && (!beginDate.before(d)))
											|| ((!endDate.equals(d)) && (!endDate.after(d)))) {
										break;
									}
									if (isCountNotComply) {
										Date complyBeginDate = DateUtil.dateOffset(d, -days);
										Date complyEndDate = DateUtil.dateOffset(d, days);
										if ((complyBeginDate.after(mDate)) || (complyEndDate.before(mDate))) {
											((List) returnMxDataList).add(mx);
											isGot = true;
											break;
										}
										complyMxDataList.add(mx.getPtInfoId());

										break;
									}
									((List) returnMxDataList).add(mx);
									isGot = true;

									break;
								}
								mxRes = "M" + i;
								if (m_index_last < i) {
									if ((!isCountNotComply) && ((beginDate.equals(d)) || (beginDate.before(d)))
											&& ((endDate.equals(d)) || (endDate.after(d)))) {
										MxData tmpMxData = new MxData();
										tmpMxData.setId(Long.valueOf(0L));
										tmpMxData.setMx(mxRes);
										tmpMxData.setPtInfoId(Long.valueOf(ptInfoId_last));
										tmpMxData.setTbDiagnosis(tbDiagnosis_last);
										tmpMxData.setDate(d);
										((List) returnMxDataList).add(tmpMxData);
										isGot = true;
									}
								}
							}
						}
						mxData_last = mx;
						mxData_last.setMx(mxRes);
						tbDiagnosis_last = tbDiagnosis;
						if (therapy != 0) {
							therapy_last = therapy;
						}
						ptInfoId_last = mx.getPtInfoId().longValue();
					}
				}
			}
			if ((!isGot) && (ptInfoId_last != 0L) && (m0_Date != null) && (mxData_last != null)
					&& (mxData_last.getMx().startsWith("M"))) {
				int mxSize = 33;
				if (5 == tbDiagnosis_last) {
					mxSize = 25;
				} else if (6 == tbDiagnosis_last) {
					mxSize = 31;
				}
				for (int i = m_index_last + 1; i < mxSize; i++) {
					Date d = DateUtil.monthOffset(m0_Date, i);
					if ((!isCountNotComply) && ((beginDate.equals(d)) || (beginDate.before(d)))
							&& ((endDate.equals(d)) || (endDate.after(d)))) {
						MxData tmpMxData = new MxData();
						tmpMxData.setId(Long.valueOf(0L));
						tmpMxData.setMx("M" + i);
						tmpMxData.setPtInfoId(mxData_last.getPtInfoId());
						tmpMxData.setTbDiagnosis(tbDiagnosis_last);
						tmpMxData.setDate(d);
						returnMxDataList.add(tmpMxData);
						break;
					}
				}
			}
		}
		List<Long> ptInfoIds = new ArrayList();
		for (MxData mx : returnMxDataList) {
			Long ptInfoId = mx.getPtInfoId();
			if ((!ptInfoIds.contains(ptInfoId)) && ((!isCountNotComply) || (!complyMxDataList.contains(ptInfoId)))) {
				DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(mx.getTbDiagnosis()));
				PtInfo ptInfo = this.ptInfoService.getPtInfo(ptInfoId);
				if ((setting != null) && (ptInfo != null)
						&& (importanceMonthOrData(mx, setting, isCheckNotReturn, hadReturn))) {
					ptInfoIds.add(ptInfoId);
				}
			}
		}
		return ptInfoIds;
	}

	private boolean importanceMonthOrData(MxData mxData, DiagnosisSetting setting, boolean isCheckNotVisit,
			boolean hadReturn) {
		String m;
		String mx = mxData.getMx();
		if (mx.endsWith("X"))
			mx = mx.substring(0, mx.lastIndexOf("X"));
		switch (mx) {
		case "M0":
			m = setting.getM0();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M1":
			m = setting.getM1();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M2":
			m = setting.getM2();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M3":
			m = setting.getM3();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M4":
			m = setting.getM4();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M5":
			m = setting.getM5();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M6":
			m = setting.getM6();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M7":
			m = setting.getM7();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M8":
			m = setting.getM8();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M9":
			m = setting.getM9();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M10":
			m = setting.getM10();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M11":
			m = setting.getM11();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M12":
			m = setting.getM12();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M13":
			m = setting.getM13();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M14":
			m = setting.getM14();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M15":
			m = setting.getM15();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M16":
			m = setting.getM16();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M17":
			m = setting.getM17();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M18":
			m = setting.getM18();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M19":
			m = setting.getM19();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M20":
			m = setting.getM20();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M21":
			m = setting.getM21();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M22":
			m = setting.getM22();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M23":
			m = setting.getM23();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M24":
			m = setting.getM24();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M25":
			m = setting.getM25();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M26":
			m = setting.getM26();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M27":
			m = setting.getM27();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M28":
			m = setting.getM28();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M29":
			m = setting.getM29();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M30":
			m = setting.getM30();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M31":
			m = setting.getM31();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M32":
			m = setting.getM32();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M33":
			m = setting.getM33();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M34":
			m = setting.getM34();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M35":
			m = setting.getM35();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		case "M36":
			m = setting.getM36();
			if (StringUtils.isNotBlank(m)) {
				if (hadReturn) {
					return dataStatusSearchData(mxData);
				} else if (isCheckNotVisit) {
					return !dataStatusSearchData(mxData);
				}
				return true;
			}
			break;
		}
		return false;
	}

	private boolean dataStatusSearchData(MxData mx) {
		long ptInfoId = mx.getPtInfoId().longValue();
		long mxId = mx.getId().longValue();
		if (mxId == 0L) {
			return false;
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
			return true;
		}
		return false;
	}

	private PtInfoResponseList returnMxDataByNkEdit(Date beginDate, Date endDate, int pageSize, int pageNumber,
			boolean isCheckNotReturn, boolean hadReturn, boolean isCountNotComply, int days, String dept, String city) {
		PtInfoResponseList response = new PtInfoResponseList();
		List<PtInfoResponse> respList = new ArrayList();
		List<Long> ptInfoIds = returnPtInfoIdsByNkEdit(beginDate, endDate, isCheckNotReturn, hadReturn,
				isCountNotComply, days, dept, city);
		if (!ptInfoIds.isEmpty()) {
			Collections.sort(ptInfoIds, new LongDescComparator());
			int totalSize = ptInfoIds.size();
			int totalPages;
			if (totalSize % pageSize != 0) {
				totalPages = totalSize / pageSize + 1;
			} else {
				totalPages = totalSize / pageSize;
			}
			if (pageNumber > totalPages) {
				pageNumber = totalPages;
			}
			int beginIndex = (pageNumber - 1) * pageSize;
			int endIndex = beginIndex + pageSize;
			if (endIndex > totalSize) {
				endIndex = totalSize;
			}
			if (beginIndex > endIndex) {
				beginIndex = endIndex;
			}
			List<PtInfo> infos = this.ptInfoService.searchPtInfoInId(ptInfoIds.subList(beginIndex, endIndex));

			Collections.sort(infos, new PtInfoDescComparator());
			for (PtInfo info : infos) {
				PtInfoResponse resp = new PtInfoResponse();
				resp.setId(info.getId());
				resp.setName(info.getName());
				resp.setSex(info.getSex());
				resp.setIdType(info.getIdType());
				resp.setIdCode(info.getIdCode());
				resp.setBirthDate(info.getBirthDate());
				resp.setContactNum(info.getContactNum());
				resp.setCurrAdds(info.getCurrAdds());
				resp.setNotes(info.getNotes());
				resp.setHospitalNo(info.getHospitalNo());
				resp.setDepartment(info.getDepartment());
				resp.setCity(info.getCity());

				List<MxData> mal = this.dataService.mxList(info.getId());
				boolean overDosage = false;
				boolean lowDosage = false;
				for (MxData mxData : mal) {
					if (mxData.getIsdosage() == 1) {
						overDosage = true;
					}
					if (mxData.getIsdosage() == 2) {
						lowDosage = true;
					}
					if (mxData.getIsdosage() == 3) {
						overDosage = true;
						lowDosage = true;
					}
					if ((overDosage & lowDosage)) {
						break;
					}
				}
				if ((overDosage) && (lowDosage)) {
					resp.setIsDosage(3);
				} else if (overDosage) {
					resp.setIsDosage(1);
				} else if (lowDosage) {
					resp.setIsDosage(2);
				} else {
					resp.setIsDosage(0);
				}
				respList.add(resp);
			}
			response.setTotalPage(totalPages);
			response.setPtInfoResponse(respList);
		}
		return response;
	}

	// 修复BUG 未复诊 全部患者
	private List<Long> returnPtInfoIdsByNkEdit(Date beginDate, Date endDate, boolean isCheckNotReturn,
			boolean hadReturn, boolean isCountNotComply, int days, String dept, String city) {

		List<DiagnosisSetting> settings = diagnosisSettingService.getAllSetting();
		Map<Integer, DiagnosisSetting> settingMap = new HashMap<>();
		for (DiagnosisSetting setting : settings) {
			settingMap.put(setting.getDiagnosisType(), setting);
		}

		List<MxData> mxDataList = dataService.searchVisitMxDataInDate(DateUtil.monthOffset(endDate, 2));
		List<MxData> returnMxDataList = new ArrayList<>(); // 需要返回的用户MX单条记录
		List<MxData> cachePtMxList = new ArrayList<>(); // 缓存一个用户的全部MX数据
		PtInfo lastPtInfo = null;

		if (mxDataList != null && !mxDataList.isEmpty()) {
			for (MxData mx : mxDataList) {
				PtInfo ptInfo = ptInfoService.getPtInfo(mx.getPtInfoId());
				if (ptInfo == null || ptInfo.getId() == 0) {
					continue;
				}
				if (!dept.equals("all") && !dept.equals(ptInfo.getDepartment())) {
					continue;
				}
				if (!city.equals("all") && !city.equals(ptInfo.getCity())) {
					continue;
				}

				if (lastPtInfo != null) {
					if (lastPtInfo.getId() != ptInfo.getId()) {
						// 当条为新PT的MX记录，处理之前的之前，并清空缓存数组
						if (ptReturnCheckResult(lastPtInfo, cachePtMxList, beginDate, endDate, isCheckNotReturn,
								hadReturn, isCountNotComply, days)) {
							MxData saveMx = cachePtMxList.get(0);
							returnMxDataList.add(saveMx);
						}
						cachePtMxList.clear();
					}
				}
				cachePtMxList.add(mx);
				lastPtInfo = ptInfo;
			}
		}

		// cache数组不为空时，处理最后一个PT用户数据
		if (!cachePtMxList.isEmpty()) {
			if (ptReturnCheckResult(lastPtInfo, cachePtMxList, beginDate, endDate, isCheckNotReturn, hadReturn,
					isCountNotComply, days)) {
				MxData saveMx = cachePtMxList.get(0);
				returnMxDataList.add(saveMx);
			}
			cachePtMxList.clear();
		}

		// 处理MX数据，返回ptId
		List<Long> ptInfoIds = new ArrayList<>();
		for (MxData mx : returnMxDataList) {
			Long ptInfoId = mx.getPtInfoId();
			if (ptInfoIds.contains(ptInfoId)) {
				continue;
			}
			PtInfo ptInfo = ptInfoService.getPtInfo(ptInfoId);
			if (ptInfo != null) {
				ptInfoIds.add(ptInfoId);
			}
		}
		return ptInfoIds;
	}

	private boolean ptReturnCheckResult(PtInfo ptInfo, List<MxData> mxDataList, Date beginDate, Date endDate,
			boolean isCheckNotReturn, boolean hadReturn, boolean isCountNotComply, int days) {
		if (mxDataList.isEmpty()) {
			return false;
		}
		if ((ptInfo.getDeathDate() != null) && (StringUtils.isNotBlank(ptInfo.getDeathReason()))
				&& (beginDate.after(ptInfo.getDeathDate()))) {
			return false;
		}
		Date m0_Date = null;
		boolean addCheckRun = false;
		int lastM0Index = 0;
		int lastTbDiagnosis = -1;
		boolean tbDiagnosisChanged = false;
		int lastTherapy = 0;
		int lastMxIndex = 1;
		String mxRes = "未治疗";
		int mxTbDiagnosis;
		for (int i = 0; i < mxDataList.size(); i++) {
			MxData mx = (MxData) mxDataList.get(i);
			int mxTherapy = mx.getTherapy();
			mxTbDiagnosis = mx.getTbDiagnosis();
			if ((mxTbDiagnosis > 0) && (mxTbDiagnosis != lastTbDiagnosis)) {
				lastTbDiagnosis = mxTbDiagnosis;
				tbDiagnosisChanged = true;
			}
			if ((mxTherapy == 1) && (tbDiagnosisChanged)) {
				m0_Date = mx.getDate();
				lastM0Index = i;
				lastTherapy = mx.getTherapy();
				tbDiagnosisChanged = false;
			}
		}
		if (m0_Date == null) {
			return false;
		}
		List<DiagnosisSetting> settings = this.diagnosisSettingService.getAllSetting();
		Map<Integer, DiagnosisSetting> settingMap = new HashMap();
		for (DiagnosisSetting setting : settings) {
			settingMap.put(Integer.valueOf(setting.getDiagnosisType()), setting);
		}
		for (int i = lastM0Index; i < mxDataList.size(); i++) {
			MxData mx = (MxData) mxDataList.get(i);
			Date mDate = mx.getDate();
			int therapy = mx.getTherapy();
			if (i == lastM0Index) {
				if (i == mxDataList.size() - 1) {
					lastMxIndex = 0;
					addCheckRun = true;
				}
			} else {
				if (therapy == 2) {
					therapy = lastTherapy;
				}
				int mxSize = 33;
				if (5 == lastTbDiagnosis) {
					mxSize = 25;
				} else if (6 == lastTbDiagnosis) {
					mxSize = 31;
				}
				for (int ii = lastMxIndex; ii < mxSize; ii++) {
					Date d = DateUtil.monthOffset(m0_Date, ii);
					Date d1 = DateUtil.monthOffset(m0_Date, ii + 1);
					Date tmpBeginDate = DateUtil.dateOffset(d, -14);
					Date tmpEndDate = DateUtil.dateOffset(d1, -14);
					if (((mDate.after(tmpBeginDate)) || (mDate.equals(tmpBeginDate))) && (mDate.before(tmpEndDate))) {
						lastMxIndex = ii;
						if (therapy == 0) {
							mxRes = "M" + ii + "X";
						} else {
							mxRes = "M" + ii;
						}
						if (((!beginDate.equals(d)) && (!beginDate.before(d)))
								|| ((!endDate.equals(d)) && (!endDate.after(d)))) {
							break;
						}
						mxRes = "M" + ii;
						DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(lastTbDiagnosis));
						mx.setMx(mxRes);
						mx.setTbDiagnosis(lastTbDiagnosis);
						if ((isCheckNotReturn) || (!importanceMonthOrData(mx, setting, isCheckNotReturn, hadReturn))) {
							break;
						}
						return true;
					}
					if (tmpBeginDate.after(mDate)) {
						break;
					}
					if (ii > lastMxIndex) {
						lastMxIndex = ii;
						if (((beginDate.equals(d)) || (beginDate.before(d)))
								&& ((endDate.equals(d)) || (endDate.after(d)))) {
							mxRes = "M" + ii;
							DiagnosisSetting setting = (DiagnosisSetting) settingMap
									.get(Integer.valueOf(lastTbDiagnosis));
							mx.setId(Long.valueOf(0L));
							mx.setMx(mxRes);
							mx.setTbDiagnosis(lastTbDiagnosis);
							if (importanceMonthOrData(mx, setting, isCheckNotReturn, hadReturn)) {
								return true;
							}
						}
					}
				}
				if (i == mxDataList.size() - 1) {
					addCheckRun = true;
				}
				if ((3 == therapy) || (4 == therapy) || (5 == therapy) || (6 == therapy) || (7 == therapy)) {
					return false;
				}
			}
		}
		if ((m0_Date != null) && (addCheckRun)) {
			int mxSize = 33;
			if (5 == lastTbDiagnosis) {
				mxSize = 25;
			} else if (6 == lastTbDiagnosis) {
				mxSize = 31;
			}
			for (int i = lastMxIndex + 1; i < mxSize; i++) {
				Date d = DateUtil.monthOffset(m0_Date, i);
				if (((beginDate.equals(d)) || (beginDate.before(d))) && ((endDate.equals(d)) || (endDate.after(d)))) {
					mxRes = "M" + i;
					DiagnosisSetting setting = (DiagnosisSetting) settingMap.get(Integer.valueOf(lastTbDiagnosis));
					MxData mx = new MxData();
					mx.setPtInfoId(ptInfo.getId());
					mx.setId(Long.valueOf(0L));
					mx.setMx(mxRes);
					mx.setTbDiagnosis(lastTbDiagnosis);
					if (importanceMonthOrData(mx, setting, isCheckNotReturn, hadReturn)) {
						return true;
					}
				} else {
					if (d.after(endDate)) {
						break;
					}
				}
			}
		}
		return false;
	}
}
