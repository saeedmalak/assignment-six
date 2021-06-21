package com.coderscampus.streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

	public List<Vehicle> readFile(String filepath) throws IOException {

		BufferedReader fileReader = null;

		try {
			fileReader = new BufferedReader(new FileReader(filepath));
			List<Vehicle> tesla = new ArrayList<>();
			String line = "";

			int index = 0;
			while ((line = fileReader.readLine()) != null) {
				// split each row by "," to get the correct data
				String[] props = line.split(",");
				if (index != 0) {
					tesla.add(new Vehicle(props[0], Integer.parseInt(props[1])));
				}
				else {
					index++;
				}
			}
			// System.out.println(tesla);
			return tesla;

		} finally {
			try {
				if (fileReader != null)
					fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
