import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
/**
 * Implementation of a noun-only WordNet.
 * 
 * @author Joon Song
 * @since April 22, 2020
 */
public class WordNet {

	private Digraph wordNetGraph;
	private Bag<String> allNouns;
	private ArrayList<Bag<String>> nounsList;
	
	/**
	 * Constructor for {@link WordNet} object.
	 * 
	 * @param synsets The file name of the synsets file.
	 * @param hypernyms The file name of the hypernyms file.
	 * 
	 * @throws IllegalArgumentException if synsets or hypernyms is {@link null}.
	 */
	public WordNet(String synsets, String hypernyms) {
		// Throwing exception of one of the input fields is invalid.
		if (synsets == null) throw new IllegalArgumentException("Parameter sysnets is null.");
		else if (hypernyms == null) throw new IllegalArgumentException("Parameter hypernyms is null.");
		
		String[] sysnetsLines = (new In(synsets)).readAllLines();
		String[] hypernymsLines = (new In(hypernyms)).readAllLines();
		
		// Setting up nouns.
		allNouns = new Bag<String>();
		nounsList = new ArrayList<Bag<String>>();
		for (String sysnet : sysnetsLines) {
			String[] nouns = sysnet.split(",")[1].split(" ");
			
			Bag<String> nounBag = new Bag<String>();
			for (String noun : nouns) {
				nounBag.add(noun);
				allNouns.add(noun);
			}
			
			nounsList.add(nounBag);
		}
		
		// Setting up WordNet hypernym directed edges.
		wordNetGraph = new Digraph(nounsList.size());
		for (String hypernym : hypernymsLines) {
			String[] splitLine = hypernym.split(",");
			
			int tailNounIndex = Integer.parseInt(splitLine[0]);
			for (int i = 1; i < splitLine.length; i++) {
				wordNetGraph.addEdge(tailNounIndex, Integer.parseInt(splitLine[i]));
			}
		}
		
		// Check if it is an rooted DAG.
	}
	
	/**
	 * Gets an {@link Iterable} containing the nouns in this {@link WordNet}.
	 * 
	 * @return An {@link Iterable} containing the nouns in this {@link WordNet}.
	 */
	public Iterable<String> nouns(){
		return allNouns;
	}

	/**
	 * Determines whether a word is a noun in this {@link WordNet}.
	 * 
	 * @param word The word to check if it is a noun in this {@link WordNet}.
	 * @return Whether word is a noun in this {@link WordNet}.
	 * 
	 * @throws IllegalArgumentException is word is {@link null}.
	 */
	public boolean isNoun(String word) {
		if (word == null) throw new IllegalArgumentException("Parameter word is null.");
				
		for (String noun : allNouns) {
			if (word.equals(noun)) return true;
		}
		
		return false;
	}
	
	/**
	 * Computes the shortest ancestral path of nounA and nounB.
	 * 
	 * @param nounA The noun to compute the shortest ancestral path of.
	 * @param nounB The noun to compute the shortest ancestral path of.
	 * @return The shortest ancestral path of nounA and nounB.
	 * 
	 * @throws IllegalArgumentException if nounA or nounB is not a noun.
	 */
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA)) throw new IllegalArgumentException("Parameter nounA is not a noun.");
		if (!isNoun(nounB)) throw new IllegalArgumentException("Parameter nounB is not a noun.");
	}
	
	
	
	
   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
	   
   }

   // do unit testing of this class
   public static void main(String[] args) {
	   
   }
}