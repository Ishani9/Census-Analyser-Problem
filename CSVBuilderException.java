package gradleAssignment;

@SuppressWarnings("serial")
public class CSVBuilderException extends Exception {
	public enum ExceptionType {
		INCORRECT_FILE,
		UNABLE_TO_PARSE,
		CENSUS_FILE_PROBLEM;
	}

	public ExceptionType type;

	public CSVBuilderException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}

