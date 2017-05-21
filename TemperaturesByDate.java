import java.util.Scanner;

class TemperatureSample {
	int month, day, year;
	double temperature;
}

public class TemperaturesByDate {
	public static void main(String[] args) throws Exception {
		String site = "https://learnjavathehardway.org";
		String path = "/txt/avg-daily-temps-with-dates-atx.txt";
		Scanner inFile = new Scanner((new java.net.URL(site+path)).openStream());

		TemperatureSample[] tempDB = new TemperatureSample[10000];
		int numRecords, i = 0;

		while (inFile.hasNextInt() && i < tempDB.length) {
			TemperatureSample e = new TemperatureSample();
			e.month = inFile.nextInt();
			e.day = inFile.nextInt();
			e.year = inFile.nextInt();
			e.temperature = inFile.nextDouble();
			if (e.temperature == -99)
				continue;
			tempDB[i] = e;
			i++;
		}
		inFile.close();
		numRecords = i;

		System.out.println(numRecords + " daily temperatures loaded.");

		double total = 0, avg, newYearsTemp, febMax = 0, febMin = 99;
		int count = 0;
		for (i=0 ; i<numRecords ; i++) {
			if (tempDB[i].month == 11) {
				total += tempDB[i].temperature;
				count++;
			}
		}
		//SD: 1
		for (i=0 ; i<numRecords ; i++) {	
			if (tempDB[i].month == 2 && tempDB[i].temperature > febMax) {
				febMax = tempDB[i].temperature;
			}
			if (tempDB[i].month == 2 && tempDB[i].temperature < febMin) {
				febMin = tempDB[i].temperature;
			}
		}

		TemperatureSample ts = tempDB[56];

		

		avg = total / count;
		avg = roundToOneDecimal(avg);
		System.out.print("Average daily temperature over " + count);
		System.out.println(" days in November: " + avg);
		System.out.println("Feb max temp was " + febMax + 
			" F, and the min was " + febMin + " F.");
		System.out.println(ts); // weird, prints @7946e1f4 Place in memory in hex?
		System.out.println(ts.temperature); // Prints hex and value.
		System.out.println("On the date " + ts.month + "/" +
			ts.day + "/" + ts.year + " the temperature was " +
			ts.temperature + " F.");	//This is better.
	}

	public static double roundToOneDecimal(double d) {
		return Math.round(d*10)/10.0;
	}
}
