package gradleAssignment;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			CSVBuilderInterface<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> censusCSVList = csvBuilder.getCSVFileList(reader,CSVStateCensus.class);
			return censusCSVList.size();
		} 
		catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} 
		catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndianStateCode(String csvFile) throws IOException, CensusAnalyserException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			CSVBuilderInterface<CSVStateCode> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCode> censusCSVList = csvBuilder.getCSVFileList(reader,CSVStateCode.class);
			return censusCSVList.size();
		} 
		catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} 
		catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
}
