package fr.pantheonsorbonne.miage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class StudentRepository implements Iterable<Student> {

	private String db;
	

	private StudentRepository(String db) {
		this.db = db;
	}

	public static StudentRepository withDB(String db) {
		if (!Files.exists(Paths.get(db))) {
			throw new RuntimeException("failed to find" + Paths.get(db).toAbsolutePath().toString());
		}
		return new StudentRepository(db);
	}

	public static List<String> toReccord(Student stu) {

<<<<<<< HEAD
		return Arrays.asList(stu.getName(), stu.getTitle(), "" + stu.getId());
	}

	public StudentRepository add(Student s) throws FailedUpdateException {
=======
		return Arrays.asList(stu.getName(), stu.getTitle(), "" + stu.getId(),stu.getPassword());
	}

	public StudentRepository add(Student s) {
>>>>>>> encryption-unit-tests
		Iterator<Student> previousContent = StudentRepository.withDB(this.db).iterator();
		try (FileWriter writer = new FileWriter(this.db)) {
			CSVPrinter csvFilePrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

			previousContent.forEachRemaining(student -> {
				try {
					csvFilePrinter.printRecord(toReccord(student));
				} catch (IOException e) {
<<<<<<< HEAD
					try {
						throw new FailedUpdateException("failed to update db file");
					} catch (FailedUpdateException failedUpdateException) {
						failedUpdateException.printStackTrace();
					}
=======
					throw new RuntimeException("failed to update db file");
>>>>>>> encryption-unit-tests
				}
			});
			csvFilePrinter.printRecord(toReccord(s));
			csvFilePrinter.flush();
			csvFilePrinter.close(true);

		} catch (IOException e) {
			throw new FailedUpdateException("failed to update db file");
		}
		return this;

	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.Iterator<Student> iterator() {
		try (FileReader reader = new FileReader(this.db)) {

			CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT);
<<<<<<< HEAD

			return parser.getRecords().stream()
=======
			this.currentIterator = parser.getRecords().stream()
>>>>>>> encryption-unit-tests
					.map((reccord) -> new Student(Integer.parseInt(reccord.get(2)), reccord.get(0), reccord.get(1), reccord.get(3)))
					.map(c -> (Student) c).iterator();

		} catch (IOException e) {
			Logger.getGlobal().info("IO PB" + e.getMessage());
			return (Iterator<Student>) Collections.emptySet();
		}
	}

}
