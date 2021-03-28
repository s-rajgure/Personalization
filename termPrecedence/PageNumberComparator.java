package termPrecedence;

import java.util.Comparator;

public class PageNumberComparator implements Comparator<WordPrecedence>{

	@Override
	public int compare(WordPrecedence o1, WordPrecedence o2) {

		return (o1.pageNumbers.get(0) < o2.pageNumbers.get(0) ? -1 :(o1.pageNumbers.get(0) == o2.pageNumbers.get(0) ? 0 : 1));
	}
	

}
