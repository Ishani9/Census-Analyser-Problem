package gradleAssignment;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface CSVBuilderInterface<E> {

	@SuppressWarnings("hiding")
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
	public List<E> getCSVFileList(Reader reader, @SuppressWarnings("rawtypes") Class csvClass) throws CSVBuilderException;
}
