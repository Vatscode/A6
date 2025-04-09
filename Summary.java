import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Vats Upadhyay (A00454163)
 */
public class Summary {

    public static final String URL = "https://people.math.sc.edu/Burkardt/datasets/csv/biostats.csv";

    /**
     * Downloads a file from the specified URL and saves it to the given output
     * file.
     *
     * @param link The URL of the file to be downloaded.
     * @param outFile The path where the downloaded file should be saved.
     * @throws IOException If an I/O error occurs while reading from the URL or
     * writing to the file.
     * @throws InterruptedException If the operation is interrupted while
     * waiting for the HTTP response.
     */
    public static void downloadFile(String link, String outFile) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            try (PrintWriter outputStream = new PrintWriter(outFile)) {
                outputStream.print(response.body());
            }
        } else {
            throw new IOException("Failed to download file, HTTP response code: "
                    + response.statusCode());
        }
    }

    /**
     * Reads the CSV file and returns a list of Person objects.
     */
    public static List<Person> readCSV(String fileName) throws FileNotFoundException {
        List<Person> people = new ArrayList<>();

        // Use Scanner to read the file
        try (Scanner kbd = new Scanner(new File(fileName))) {
            
            if (kbd.hasNextLine()) {
                kbd.nextLine();
            }

            // Read data line by line and create Person objects
            while (kbd.hasNextLine()) {
                String line = kbd.nextLine();
                String[] fields = line.split(",");
                // Check if the line has the expected number of fields
                if (fields.length < 5) {
                    System.out.println("Skipping line due to insufficient fields: " + line);
                    continue; // Skip this line
                }
                String name = fields[0];
                String sex = fields[1];
                int age = Integer.parseInt(fields[2]);
                double height = Double.parseDouble(fields[3]);
                double weight = Double.parseDouble(fields[4]);
                people.add(new Person(name, sex, age, height, weight));
            }
        }

        return people;
    }

    /**
     * Prints the summary statistics for the data.
     */
    public static void printSummary(List<Person> people) {
        if (people.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Total number of records (excluding the header)
        System.out.println("Total number of records: " + people.size());

        // Youngest person
        Person youngest = people.stream().min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).orElse(null);
        System.out.println("Youngest person: " + (youngest != null ? youngest.getName() : "N/A"));

        // Oldest person
        Person oldest = people.stream().max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).orElse(null);
        System.out.println("Oldest person: " + (oldest != null ? oldest.getName() : "N/A"));

        // Average age
        double averageAge = people.stream().mapToInt(Person::getAge).average().orElse(0);
        System.out.println("Average age: " + averageAge);

        // Tallest person
        Person tallest = people.stream().max((p1, p2) -> Double.compare(p1.getHeight(), p2.getHeight())).orElse(null);
        System.out.println("Tallest person: " + (tallest != null ? tallest.getName() : "N/A"));

        // Shortest female
        Person shortestFemale = people.stream()
                .filter(p -> p.getSex().equalsIgnoreCase("F"))
                .min((p1, p2) -> Double.compare(p1.getHeight(), p2.getHeight()))
                .orElse(null);
        
        if (shortestFemale != null) { //simple if else 
            System.out.println("Shortest female: " + shortestFemale.getName());
        } else {
            System.out.println("Shortest female: N/A");
        }
    }

    /**
     * Prints the details of each person.
     */
    public static void printDetails(List<Person> people) {
        for (Person person : people) { //enhanced for loop 
            System.out.println(person);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("No command provided.");
            return; 
        }

        switch (args[0]) {  //simple switch statement
            case "download": {
                try {
                    downloadFile(URL, args[1]);
                    System.out.println("Downloaded successfully: " + args[1]);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                break;
            }

            case "summary":
                List<Person> people = readCSV(args[1]);
                printSummary(people);
                break;

            case "print":
                List<Person> peopleForPrint = readCSV(args[1]);
                printDetails(peopleForPrint);
                break;
            default:
                System.out.println("Unknown command");
        }
    }

}
//creates a new people.csv file 
//end of program