package gradleAssignment;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

import com.google.gson.Gson;

public class CensusAnalyserTest {
	private static final String STATECENSUSFILE = "C:\\Users\\Ishani\\eclipse-workspace\\gradleAssignment\\IndiaStateCensusData.csv";
	private static final String USCENSUSFILE = "C:\\Users\\Ishani\\eclipse-workspace\\gradleAssignment\\USCensusData.csv";
	private static final String STATESCODE = "C:\\Users\\Ishani\\eclipse-workspace\\gradleAssignment\\IndiaStateCode.csv";
	private static final String WRONG_FILE = "C:\\Users\\Ishani\\eclipse-workspace\\gradleAssignment\\USCensus.csv";
	private static final String WRONG_EXTENSION = "C:\\Users\\Ishani\\eclipse-workspace\\gradleAssignment\\USCensusData.txt";
	
	/**
	 * UC 1
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenStateCSVFile_IfMatchNumberOfRecords_ShouldReturnTrue() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUSFILE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
		assertEquals(29, count);
	}
	
	@Test
	public void givenCSVFile_IfWrongFile_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_FILE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_EXTENSION);
		}
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUSFILE);
		}
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException , CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(USCENSUSFILE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}
	
	/**
	 * UC 2
	 * 
	 */
	@Test
	public void givenStateCodeCSVFile_IfMatchNumberOfRecords_ShouldReturnTrue() throws IOException , CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadIndianStateCode(STATESCODE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
		assertEquals(37, count);
	}
	
	@Test
	public void givenCodeCSVFile_IfWrongFile_ShouldThrowError() throws IOException , CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadIndianStateCode(WRONG_FILE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	
	
	@Test
	public void givenCodeCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException , CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadIndianStateCode(WRONG_EXTENSION);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenCodeCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException , CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadIndianStateCode(STATESCODE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenCodeCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException , CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadIndianStateCode(USCENSUSFILE);
		} 
		catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}
	
	/**
	 * UC 3
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUSFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].state);
	}

	
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUSFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}
	
	/**
	 * UC 4
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCodeCensusData_WhenSortedOnState_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadIndianStateCode(STATESCODE);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		CSVStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
		assertEquals("Andhra Pradesh New", censusCSV[0].state);
	}

	
	@Test
	public void givenIndianCodeCensusData_WhenSortedOnState_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadIndianStateCode(STATESCODE);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		CSVStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}


}
