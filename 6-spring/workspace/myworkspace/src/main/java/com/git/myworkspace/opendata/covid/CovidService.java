package com.git.myworkspace.opendata.covid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class CovidService {

	private final String SERVICE_KEY = "%2FDGE8x1Q5qXeIEAL69VbHQz5Nzy5dMzgI%2Bsi%2B7ZAGnu6CFOVZnszOjJgAxE7FkHmuJHh5iuW8atylPOcDcOGPQ%3D%3D";

	private CovidSidoDailyRepository repo;

	@Autowired
	public CovidService(CovidSidoDailyRepository repo) {
		this.repo = repo;
	}

	@Scheduled(cron = "0 0 24 * * *")

	@CacheEvict(value = "covid-current", allEntries = true)
	public void requestCovid() throws IOException {
		String[] gubuns = { "서울" };
		for (String gubun : gubuns) {
			requestCovidSidoDaily(gubun);
		}
	}

	@SuppressWarnings("deprecation")
	public void requestCovidSidoDaily(String sido) throws IOException {
		System.out.println(new Date().toLocaleString());

		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.data.go.kr"); // �ּ�
		builder.append("openapi/service/rest/Covid19"); // ����
		builder.append("/getCovid19SidoInfStateJson"); // ���
		builder.append("?gubun=" + URLEncoder.encode(sido, "UTF-8")); // 시도(서울, 경기...)
		builder.append("?serviceKey=" + SERVICE_KEY); // ����Ű

		System.out.println(builder.toString());

		// 2. URL ��ü ����
		URL url = new URL(builder.toString());

		// 3. Http ���� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[]�迭�� �����͸� �о��
		byte[] result = con.getInputStream().readAllBytes();

		// 5. byte[] -> ���ڿ�(XML) ��ȯ
		String data = new String(result, "UTF-8");
		System.out.println(data);
		/* ---------------------- ������ ��û�ϰ� XML �޾ƿ��� �� ----------------- */

		/* ---------------------- XML -> JSON -> Object(Java) ���� ----------------- */
		// XML(���ڿ�) -> JSON(���ڿ�)
		String json = XML.toJSONObject(data).toString(2);
		System.out.println(json);

		// JSON(���ڿ�) -> Java(object)
		CovidSidoDailyResponse response = new Gson().fromJson(json, CovidSidoDailyResponse.class);
		System.out.println(response);

		List<CovidSidoDaily> list = new ArrayList<CovidSidoDaily>();
		for (CovidSidoDailyResponse.Item item : response.getResponse().getBody().getItems().getItem()) {
			CovidSidoDaily record = CovidSidoDaily.builder().stdDay(item.getStdDay()).gubun(item.getGubun())
					.overFlowCnt(item.getOverFlowCnt().isEmpty() ? null : Integer.valueOf(item.getOverFlowCnt()))
					.localOccCnt(item.getLocalOccCnt().isEmpty() ? null : Integer.valueOf(item.getLocalOccCnt()))
					.build();

			list.add(record);
		}

		repo.saveAll(list);

	}

}
