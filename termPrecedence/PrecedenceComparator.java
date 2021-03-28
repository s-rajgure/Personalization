package termPrecedence;

import java.util.Comparator;

public class PrecedenceComparator implements Comparator<WordPrecedence>{

	@Override
	public int compare(WordPrecedence o1, WordPrecedence o2) {
		return (o1.precedence < o2.precedence ? -1 :(o1.precedence == o2.precedence ? 0 : 1));
	}
	

}
