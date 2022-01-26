package com.spring.CovidTracker.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.spring.CovidTracker.Model.DailyData;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
public class CovidServices {
	
	private static String url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<DailyData> data=new ArrayList<>();
		
	public List<DailyData> getData() {
		return data;
	}

	public void setData(List<DailyData> data) {
		this.data = data;
	}

	@PostConstruct
	@Scheduled(cron = "* 1 * * * *")
	public void fetchData() throws IOException, InterruptedException {
		List<DailyData> newData=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvReader=new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (CSVRecord record : records) {
			DailyData dailydata=new DailyData();
			dailydata.setCountry(record.get("Country/Region"));
			dailydata.setState(record.get("Province/State"));
			int newCases=Integer.parseInt(record.get(record.size()-1));
			int prevDayCases=Integer.parseInt(record.get(record.size()-2));
		    dailydata.setPrevDayCase(prevDayCases);
		    dailydata.setNewCases(newCases-prevDayCases);
		    newData.add(dailydata);   
		}
		this.data=newData;
	}
	

}
