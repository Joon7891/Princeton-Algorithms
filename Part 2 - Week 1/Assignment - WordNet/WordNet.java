import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

/**
 * Implementation of a noun-only WordNet.
 * 
 * @author Joon Song
 * @since April 22, 2020
 */
public class WordNet {

	private final SAP sap;
	private final Digraph digraph;
	private final HashMap<String, Integer> nounToIndex;
	private final HashMap<Integer, String> indexToSynset;
	
	/**
	 * Constructor for {@link WordNet} object.
	 * 
	 * @param synsets The file name of the synsets file.
	 * @param hypernyms The file name of the hypernyms file.
	 * 
	 * @throws IllegalArgumentException if synsets or hypernyms is {@link null}, or if the digraph is not a rooted DAG.
	 */
	public WordNet(String synsets, String hypernyms) {
		// Throwing exception of one of the input fields is invalid.
		if (synsets == null) throw new IllegalArgumentException("Parameter sysnets is null.");
		else if (hypernyms == null) throw new IllegalArgumentException("Parameter hypernyms is null.");
		
		In synsetsIn = new In(synsets);
		In hypernymsIn = new In(hypernyms);
		
		// Setting up nouns.
		int size = 0;
		nounToIndex = new HashMap<String, Integer>();
		indexToSynset = new HashMap<Integer, String>();
		while (synsetsIn.hasNextLine()) {
			String[] line = synsetsIn.readLine().split(",");
			
			if (line.length < 2) break;

			size++;
			int index = Integer.parseInt(line[0]);
			indexToSynset.put(index, line[1]);
			String[] words = line[1].split(" ");
			for (String word : words) {
				nounToIndex.put(word, index);
			}
		}
		
		// Setting up digraph.
		digraph = new Digraph(size);
		while (hypernymsIn.hasNextLine()) {
			String[] line = hypernymsIn.readLine().split(",");
			
			if (line.length < 2) continue;
			
			int index = Integer.parseInt(line[0]);
			for (int i = 1; i < line.length; i++) {
				digraph.addEdge(index, Integer.parseInt(line[i]));
			}
		}
		
		sap = new SAP(digraph);
		
		// Check if it is an rooted DAG.
		int roots = 0;
		for (int i = 0; i < size; i++) {
			if (!digraph.adj(i).iterator().hasNext()) roots++;
		}
		
		if (roots != 1) {
			throw new IllegalArgumentException("Hypernym digraph does not have exactly 1 root.");
		}
		
		DirectedCycle dicycle = new DirectedCycle(digraph);
		if (dicycle.hasCycle()) {
			throw new IllegalArgumentException("Hypernym digraph is not acyclical.");
		}
	}
	
	/**
	 * Gets an {@link Iterable} containing the nouns in this {@link WordNet}.
	 * 
	 * @return An {@link Iterable} containing the nouns in this {@link WordNet}.
	 */
	public Iterable<String> nouns(){
		return nounToIndex.keySet();
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
		return nounToIndex.containsKey(word);
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
		
		int indexA = nounToIndex.get(nounA);
		int indexB = nounToIndex.get(nounB);
		return sap.length(indexA, indexB);
	}
	
	/**
	 * Determines a synset that is the common ancestor in a shortest ancestral path of nounA and nounB.
	 * 
	 * @param nounA The noun to determine the ancestor in the shortest acestreal path from.
	 * @param nounB The noun to determine the ancestor in the shortest acestreal path from.
	 * @return A synset that is the common ancestor in a shortest ancestral path of nounA and nounB.
	 * 
	 * @throws IllegalArgumentException if nounA or nounB is not a noun.
	 */
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA)) throw new IllegalArgumentException("Parameter nounA is not a noun.");
		if (!isNoun(nounB)) throw new IllegalArgumentException("Parameter nounB is not a noun.");
		
		int indexA = nounToIndex.get(nounA);
		int indexB = nounToIndex.get(nounB);
		int ancestorIndex = sap.ancestor(indexA, indexB);
		return indexToSynset.get(ancestorIndex);
	}
}