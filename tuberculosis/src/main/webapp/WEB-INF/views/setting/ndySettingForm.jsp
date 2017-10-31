<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>${diagnosisName}治疗检测项目及频率</title>
	<script>
		$(document).ready(function() {
			$("#${diagnosisTab}-tab").addClass("active");
			
		});
	</script>
</head>

<body>
	<h1>${diagnosisName}治疗检测项目及频率</h1>
	<form:form id="inputForm" modelAttribute="diagnosisSetting" action="${ctx}/diagnosisSetting/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${diagnosisSetting.id}"/>
		<input type="hidden" name="diagnosisType" value="${diagnosisType}"/>
		<input type="hidden" name="m0" id="m0" value="${diagnosisSetting.m0}"/>
		<input type="hidden" name="m1" id="m1" value="${diagnosisSetting.m1}"/>
		<input type="hidden" name="m2" id="m2" value="${diagnosisSetting.m2}"/>
		<input type="hidden" name="m3" id="m3" value="${diagnosisSetting.m3}"/>
		<input type="hidden" name="m4" id="m4" value="${diagnosisSetting.m4}"/>
		<input type="hidden" name="m5" id="m5" value="${diagnosisSetting.m5}"/>
		<input type="hidden" name="m6" id="m6" value="${diagnosisSetting.m6}"/>
		<input type="hidden" name="m7" id="m7" value="${diagnosisSetting.m7}"/>
		<input type="hidden" name="m8" id="m8" value="${diagnosisSetting.m8}"/>
		<input type="hidden" name="m9" id="m9" value="${diagnosisSetting.m9}"/>
		<input type="hidden" name="m10" id="m10" value="${diagnosisSetting.m10}"/>
        <input type="hidden" name="m11" id="m11" value="${diagnosisSetting.m11}"/>
        <input type="hidden" name="m12" id="m12" value="${diagnosisSetting.m12}"/>
        <input type="hidden" name="m13" id="m13" value="${diagnosisSetting.m13}"/>
        <input type="hidden" name="m14" id="m14" value="${diagnosisSetting.m14}"/>
        <input type="hidden" name="m15" id="m15" value="${diagnosisSetting.m15}"/>
        <input type="hidden" name="m16" id="m16" value="${diagnosisSetting.m16}"/>
        <input type="hidden" name="m17" id="m17" value="${diagnosisSetting.m17}"/>
        <input type="hidden" name="m18" id="m18" value="${diagnosisSetting.m18}"/>
        <input type="hidden" name="m19" id="m19" value="${diagnosisSetting.m19}"/>
        <input type="hidden" name="m20" id="m20" value="${diagnosisSetting.m20}"/>
        <input type="hidden" name="m21" id="m21" value="${diagnosisSetting.m21}"/>
        <input type="hidden" name="m22" id="m22" value="${diagnosisSetting.m22}"/>
        <input type="hidden" name="m23" id="m23" value="${diagnosisSetting.m23}"/>
        <input type="hidden" name="m24" id="m24" value="${diagnosisSetting.m24}"/>
        <input type="hidden" name="m25" id="m25" value="${diagnosisSetting.m25}"/>
        <input type="hidden" name="m26" id="m26" value="${diagnosisSetting.m26}"/>
        <input type="hidden" name="m27" id="m27" value="${diagnosisSetting.m27}"/>
        <input type="hidden" name="m28" id="m28" value="${diagnosisSetting.m28}"/>
        <input type="hidden" name="m29" id="m29" value="${diagnosisSetting.m29}"/>
        <input type="hidden" name="m30" id="m30" value="${diagnosisSetting.m30}"/>
        <input type="hidden" name="m31" id="m31" value="${diagnosisSetting.m31}"/>
        <input type="hidden" name="m32" id="m32" value="${diagnosisSetting.m32}"/>
        <input type="hidden" name="m33" id="m33" value="${diagnosisSetting.m33}"/>
        <input type="hidden" name="m34" id="m34" value="${diagnosisSetting.m34}"/>
        <input type="hidden" name="m35" id="m35" value="${diagnosisSetting.m35}"/>
        <input type="hidden" name="m36" id="m36" value="${diagnosisSetting.m36}"/>
      <table id="contentTable" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <th >治疗月份</th>
          <th>0</th>
          <th>1</th>
          <th>2</th>
          <th>3</th>
          <th>4</th>
          <th>5</th>
          <th>6</th>
          <th>7</th>
          <th>8</th>
          <th>9</th>
          <th>10</th>
          <th>11</th>
          <th>12</th>
          <th>13</th>
          <th>14</th>
          <th>15</th>
          <th>16</th>
          <th>17</th>
          <th>18</th>
          <th>19</th>
          <th>20</th>
          <th>21</th>
          <th>22</th>
          <th>23</th>
          <th>24</th>
          <th>25</th>
          <th>26</th>
          <th>27</th>
          <th>28</th>
          <th>29</th>
          <th>30</th>
          <th>31</th>
          <th>32</th>
          <th>33</th>
          <th>34</th>
          <th>35</th>
          <th>36</th>
        </tr>
        </thead>
        <tbody>
          <tr>
            <td>痰培养</td>
            <td><input type="checkbox" name="m0_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="tpy"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="tpy"/></td>
          </tr>
          <tr>
            <td>痰涂片</td>
            <td><input type="checkbox" name="m0_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="ttp"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="ttp"/></td>
          </tr>
          <tr>
            <td>放射学胸片</td>
            <td><input type="checkbox" name="m0_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="xp"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="xp"/></td>
          </tr>
          <tr>
            <td>传统药敏</td>
            <td><input type="checkbox" name="m0_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="ctym"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="ctym"/></td>
          </tr>
          <tr>
            <td>基因芯片</td>
            <td><input type="checkbox" name="m0_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="jyxp"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="jyxp"/></td>
          </tr>
          <tr>
            <td>GeneXpert</td>
            <td><input type="checkbox" name="m0_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="genexpert"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="genexpert"/></td>
          </tr>
          <tr>
            <td>Hain</td>
            <td><input type="checkbox" name="m0_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="hain"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="hain"/></td>
          </tr>
          <tr>
            <td>体检</td>
            <td><input type="checkbox" name="m0_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="tj"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="tj"/></td>
          </tr>
          <tr>
            <td>血常规</td>
            <td><input type="checkbox" name="m0_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="xcg"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="xcg"/></td>
          </tr>
          <tr>
            <td>尿常规</td>
            <td><input type="checkbox" name="m0_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="ncg"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="ncg"/></td>
          </tr>
          <tr>
            <td>肝功能</td>
            <td><input type="checkbox" name="m0_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="ggn"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="ggn"/></td>
          </tr>
          <tr>
            <td>肾功能</td>
            <td><input type="checkbox" name="m0_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="sgn"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="sgn"/></td>
          </tr>
          <tr>
            <td>免疫学</td>
            <td><input type="checkbox" name="m0_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m1_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m2_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m3_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m4_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m5_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m6_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m7_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m8_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m9_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m10_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m11_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m12_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m13_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m14_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m15_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m16_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m17_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m18_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m19_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m20_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m21_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m22_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m23_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m24_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m25_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m26_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m27_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m28_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m29_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m30_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m31_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m32_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m33_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m34_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m35_checkbox" value="myx"/></td>
            <td><input type="checkbox" name="m36_checkbox" value="myx"/></td>
          </tr>

        </tbody>
      </table>
        <div class="form-actions">
          <input id="submit_btn" class="btn btn-primary" type="button" value="提交" onclick="submitCheckbox()"/>&nbsp;

        </div>
	</form:form>

<script lang="text/javascript" >

  function checkBoxRender(){
    for (var i=0;i<=36;i++){
      mx = $("#m"+i).val();
      mxArray = mx.split(",");
      for(var j=0; j<mxArray.length; j++){
        mxTmp = mxArray[j];
        $("input[name='m"+i+"_checkbox']").each(function () {
          if($(this).val() == mxTmp) {
            $(this).prop("checked", true);
            return false
          }

        });
      }
    }
  }

  checkBoxRender();

  function submitCheckbox(){
    for (var i=0;i<=36;i++) {
      var mValue = '';
      var checkboxs = $("input[name='m" + i + "_checkbox']:checked");
      var len = checkboxs.length;
      checkboxs.each(function (i, val) {
        mValue = mValue + val.value;
        if(i!=(len-1)) mValue = mValue + ",";
      });
      $("#m"+i).val(mValue);
//      alert(mValue);
    }
    $("#inputForm").submit();
  }
</script>
</body>
</html>
