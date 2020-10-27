package gradleAssignment;

@SuppressWarnings("serial")
public class CensusAnalyserException extends Exception {
	public enum ExceptionType {
		INCORRECT_FILE,
		UNABLE_TO_PARSE,
		NO_CENSUS_DATA,
		CENSUS_FILE_PROBLEM;
	}

	public ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}