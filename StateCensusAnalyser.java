package gradleAssignment;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	
	List<CSVStateCensus> censusCSVList = null;
	List<CSVStateCode> censusCodeCSVList = null;
	
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			CSVBuilderInterface<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader,CSVStateCensus.class);
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
			censusCodeCSVList = csvBuilder.getCSVFileList(reader,CSVStateCode.class);
			return censusCodeCSVList.size();
		} 
		catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} 
		catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	/**
	 * UC 3
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		if(censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(CSVStateCensus -> CSVStateCensus.state);
		this.sort(censusCSVList, censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}
	
	/**
	 * UC 4
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
		if(censusCodeCSVList == null || censusCodeCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCode> censusCodeComparator = Comparator.comparing(CSVStateCode -> CSVStateCode.stateCode);
		this.sort(censusCodeCSVList, censusCodeComparator);
		String sortedStateCodeCensusJson = new Gson().toJson(censusCodeCSVList);
		return sortedStateCodeCensusJson;
	}

	public <E> void sort(List<E> censusList, Comparator<E> censusComparator) {
		for (int i = 0; i < censusList.size(); i++) {
			for (int j = 0; j < censusList.size() - i - 1; j++) {
				E census1 =  censusList.get(j);
				E census2 =  censusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
			}
		}
	}
	
	/**
	 * UC 5
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
		if(censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator1 = Comparator.comparing(CSVStateCensus -> CSVStateCensus.population);
		this.sortPopulation(censusCSVList, censusComparator1);
		String sortedStateCensusJson1 = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson1;
	}
	
	/**
	 * UC 6
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
		if(censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(CSVStateCensus -> CSVStateCensus.densityPerSqKm);
		this.sortPopulation(censusCSVList, censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}
	
	
	public <E> void sortPopulation(List<E> censusList, Comparator<E> censusComparator) {
		for (int i = 0; i < censusList.size(); i++) {
			for (int j = 0; j < censusList.size() - 1 ; j++) {
				E census1 =  censusList.get(j);
				E census2 =  censusList.get(j + 1);
				if (censusComparator.compare(census1, census2) < 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
			}
		}
	}
	
	
}
