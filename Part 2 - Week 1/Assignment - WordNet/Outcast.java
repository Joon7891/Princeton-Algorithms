import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Wrapper class to determine an outcast in a given {@link WordNet}.
 * 
 * @author Joon Song
 * @since April 22, 2020
 */
public class Outcast {
	
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}
	
	public String outcast(String[] nouns) {
		int worstValue = Integer.MIN_VALUE;
		String worstNoun = "";
		for (int i = 0; i < nouns.length; i++) {
			int totalValue = 0;
			for (String noun : nouns) {
				totalValue += wordnet.distance(nouns[i], noun);
			}
			
			if (totalValue > worstValue) {
				worstValue = totalValue;
				worstNoun = nouns[i];
			}
		}
		
		return worstNoun;		
	}
	
	/**
	 * Main function/test client for this {@link Outcast} class.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}