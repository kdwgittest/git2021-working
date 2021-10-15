package com.git.myworkspace.opendata.air;

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
public class AirService {

	private final String SERVICE_KEY = "%2FDGE8x1Q5qXeIEAL69VbHQz5Nzy5dMzgI%2Bsi%2B7ZAGnu6CFOVZnszOjJgAxE7FkHmuJHh5iuW8atylPOcDcOGPQ%3D%3D";

	private AirSigunguHourRepository repo;

	@Autowired
	public AirService(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	@Scheduled(cron = "0 30 * * * *")

	@CacheEvict(value = "air-current", allEntries = true)
	public void requestAir() throws IOException {
//		String[] sidoNames = { "����", "���" };
		String[] sidoNames = { "����" };
		for (String sidoName : sidoNames) {
			requestAirSiGunGuHour(sidoName);
		}
	}

	@SuppressWarnings("deprecation")
	public void requestAirSiGunGuHour(String sido) throws IOException {
		System.out.println(new Date().toLocaleString());

		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584"); // ȣ��Ʈ/����Ʈ����
		builder.append("/ArpltnStatsSvc"); // ����
		builder.append("/getCtprvnMesureSidoLIst"); // ���(�õ�-�ñ�������ȸ ��) ����-������...�߶���)
		builder.append("?sidoName=" + URLEncoder.encode(sido, "UTF-8")); // �õ�(����, ���...)
		builder.append("&searchCondition=HOUR"); // 1�ð�����
		builder.append("&pageNo=1&numOfRows=100"); // �ñ��� ����
		builder.append("&serviceKey=" + SERVICE_KEY); // ����Ű

		System.out.println(builder.toString());

		URL url = new URL(builder.toString());

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		byte[] result = con.getInputStream().readAllBytes();

		String data = new String(result, "UTF-8");
		System.out.println(data);

		String json = XML.toJSONObject(data).toString(2);
		System.out.println(json);

		AirSigunguHourResponse response = new Gson().fromJson(json, AirSigunguHourResponse.class);
		System.out.println(response);

		List<AirSigunguHour> list = new ArrayList<AirSigunguHour>();
		for (AirSigunguHourResponse.Item item : response.getResponse().getBody().getItems().getItem()) {
			AirSigunguHour record = AirSigunguHour.builder().dataTime(item.getDataTime()).sidoName(item.getSidoName())
					.cityName(item.getCityName())
					.pm10Value(item.getPm10Value().isEmpty() ? null : Integer.valueOf(item.getPm10Value()))
					.pm25Value(item.getPm25Value().isEmpty() ? null : Integer.valueOf(item.getPm25Value())).build();

			list.add(record);
		}

		repo.saveAll(list);
	}
}
