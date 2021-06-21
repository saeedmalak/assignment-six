package com.coderscampus.streams;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Application {

	public static void main(String[] args) throws IOException {
		FileService fileService = new FileService();
		
		List<Vehicle> teslaModel3 = fileService.readFile("model3.csv");
		List<Vehicle> teslaModelS = fileService.readFile("modelS.csv");
		List<Vehicle> teslaModelX = fileService.readFile("modelX.csv");
		

		System.out.println(teslaModel3);
		//System.out.println(teslaModelS);
		//System.out.println(teslaModelX);
		System.out.println();
		
		Integer totalSales2017 = extracted(teslaModel3, "17");
		Integer totalSales2018 = extracted(teslaModel3, "18");
		Integer totalSales2019 = extracted(teslaModel3, "19");
		
		System.out.println("Model 3 Yearly Sales Report");
		System.out.println("---------------------------");
		System.out.println("2017 -> $" + totalSales2017);
		System.out.println("2018 -> $" + totalSales2018);
		System.out.println("2019 -> $" + totalSales2019);
		
		Comparator<Vehicle> comparator = Comparator.comparing( Vehicle::getDate );
		
		Vehicle minObject = teslaModel3.stream().max(comparator).get();
		System.out.println(minObject);
		
		Optional<Vehicle> maxValue = teslaModel3.stream()
				//.mapToInt(car -> Integer.parseInt(car.getSale()))
		       // .max()
		        .max((p1, p2) -> p1.getSale() - p2.getSale());
		
        System.out.println(maxValue);

	}

	private static Integer extracted(List<Vehicle> teslaModel3, String year) {
		Integer totalSales = teslaModel3.stream()
				   //.map (car -> car.getDate().contains("17") ? car.getSale() : "")
				   .filter(car -> car.getDate().contains(year))
	               .mapToInt(car -> car.getSale())
				   .sum();
		return totalSales;
	}

}
