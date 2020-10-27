package gradleAssignment;

public class CSVBuilderFactory {
	@SuppressWarnings("rawtypes")
	public static <E> CSVBuilderInterface createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
