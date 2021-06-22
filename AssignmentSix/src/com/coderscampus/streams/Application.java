package com.coderscampus.streams;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class Application {

	public static void main(String[] args) throws IOException, ParseException {
		// FileService instantiation
		FileService fileService = new FileService();

		// Read the 3 csv sheets and assign them to separate lists
		List<Vehicle> teslaModel3 = fileService.readFile("model3.csv");
		List<Vehicle> teslaModelS = fileService.readFile("modelS.csv");
		List<Vehicle> teslaModelX = fileService.readFile("modelX.csv");

		// Model 3 Data Analysis
		Integer totalSales2017 = annualSalesTotal(teslaModel3, "17");
		Integer totalSales2018 = annualSalesTotal(teslaModel3, "18");
		Integer totalSales2019 = annualSalesTotal(teslaModel3, "19");
		
		String bestSalesDate = bestSalesMonth(teslaModel3);
		String worstSalesDate = worstSalesMonth(teslaModel3);

		System.out.println("Model 3 Yearly Sales Report");
		System.out.println("---------------------------");
		System.out.println("2017 -> $" + totalSales2017);
		System.out.println("2018 -> $" + totalSales2018);
		System.out.println("2019 -> $" + totalSales2019);
		System.out.println();
		
		System.out.println("The best month for Model 3 was: " + bestSalesDate);
		System.out.println("The worst month for Model 3 was: " + worstSalesDate);
		
		// Model S Data Analysis
		Integer totalSales2016 = annualSalesTotal(teslaModelS, "16");
		totalSales2017 = annualSalesTotal(teslaModelS, "17");
		totalSales2018 = annualSalesTotal(teslaModelS, "18");
		totalSales2019 = annualSalesTotal(teslaModelS, "19");
	
		bestSalesDate = bestSalesMonth(teslaModelS);
		worstSalesDate = worstSalesMonth(teslaModelS);

		System.out.println();
		System.out.println("Model S Yearly Sales Report");
		System.out.println("---------------------------");
		System.out.println("2016 -> $" + totalSales2016);
		System.out.println("2017 -> $" + totalSales2017);
		System.out.println("2018 -> $" + totalSales2018);
		System.out.println("2019 -> $" + totalSales2019);
		System.out.println();
		
		System.out.println("The best month for Model S was: " + bestSalesDate);
		System.out.println("The worst month for Model S was: " + worstSalesDate);
		
		// Model X Data Analysis
		totalSales2016 = annualSalesTotal(teslaModelX, "16");
		totalSales2017 = annualSalesTotal(teslaModelX, "17");
		totalSales2018 = annualSalesTotal(teslaModelX, "18");
		totalSales2019 = annualSalesTotal(teslaModelX, "19");
	
		bestSalesDate = bestSalesMonth(teslaModelX);
		worstSalesDate = worstSalesMonth(teslaModelX);

		System.out.println();
		System.out.println("Model X Yearly Sales Report");
		System.out.println("---------------------------");
		System.out.println("2016 -> $" + totalSales2016);
		System.out.println("2017 -> $" + totalSales2017);
		System.out.println("2018 -> $" + totalSales2018);
		System.out.println("2019 -> $" + totalSales2019);
		System.out.println();
		
		System.out.println("The best month for Model X was: " + bestSalesDate);
		System.out.println("The worst month for Model X was: " + worstSalesDate);
	
	}
	
	private static String worstSalesMonth (List<Vehicle> teslaModel) {
		Optional<Object> minSalesObj = 
				teslaModel.stream()
				           .min((p1, p2) -> p1.getSale() - p2.getSale())
				           .map(car -> car.getDate());
		
		String minValue = (String) minSalesObj.get();
		// formating the year a litle
		minValue = new StringBuilder(minValue).insert(minValue.length()-2, "20").toString();
		return minValue;
	}

	private static String bestSalesMonth(List<Vehicle> teslaModel) {
		Optional<Object> maxSalesObj = 
				teslaModel.stream()
				           .max((p1, p2) -> p1.getSale() - p2.getSale())
				           .map(car -> car.getDate());
		String maxValue = (String) maxSalesObj.get();
		// formating the year a litle
		maxValue = new StringBuilder(maxValue).insert(maxValue.length()-2, "20").toString();
		return maxValue;
	}

	private static Integer annualSalesTotal(List<Vehicle> teslaModel, String year) {
		Integer totalSales = teslaModel.stream()
				                       .filter(car -> car.getDate().contains(year))
				                       .mapToInt(car -> car.getSale())
				                       .sum();
		return totalSales;
	}

}
