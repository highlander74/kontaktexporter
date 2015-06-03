package tup.tools.kontakte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;

public class KontaktExporter {

	public static void export() throws IOException {
		File file = new File("E:/proj/eclipse/tools/data/Telefon.csv");
		FileInputStream fileStream = new FileInputStream(file);
		CsvReader csvReader = new CsvReader (fileStream,Charset.availableCharsets().get("Windows-1252"));

		while (csvReader.readRecord()) {
			String[] values = csvReader.getValues();
			System.out.println();
			boolean first = true;
			StringBuffer kontakte = new StringBuffer();
			for (String value : values) {
				if (!first) {
					kontakte.append("_");
				}
				kontakte.append(value);
				first = false;
			}
			File out = new File("E:/proj/eclipse/tools/data/out/" + kontakte.toString() + ".kontakte");
			FileWriter writer;
			try {
				writer = new FileWriter(out);
				writer.write(kontakte.toString());
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			export();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}