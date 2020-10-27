package gradleAssignment;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements CSVBuilderInterface<E> {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
		return (Iterator<E>) this.getCSVToBean(reader, csvClass).iterator();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
		return this.getCSVToBean(reader, csvClass).parse();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CsvToBean<E> getCSVToBean(Reader reader, Class csvClass) throws CSVBuilderException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} 
		catch (IllegalStateException e) {
			throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

}
