
/**
 * @author Waqar Kalim
 *
 */
public class TTTDictionary {
	private LinearNode<TTTRecord> head = null;
	private LinearNode<TTTRecord> current;
	private LinearNode<TTTRecord> Hashnode;
	private LinkedList list;
	private LinkedList[] hashtable;
	private int numElements;
	private int index;
	private String[] configArray;

	// The TTTDictionary() method is the constructor that takes size as an input
	// and creates the hashtable of that size
	/**
	 * @param size
	 */
	public TTTDictionary(int size) {

		Hashnode = new LinearNode<TTTRecord>();
		hashtable = new LinkedList[size];
		configArray = new String[size];
		for (int i = 0; i < hashtable.length; i++) {
			list = new LinkedList();
			hashtable[i] = list;
		}
		numElements = 0;

	}

	// The addConfig() takes a configuration as an input and add that
	// configuration into configArray for storage
	/**
	 * @param config
	 */
	private void addConfig(String config) {
		configArray[numElements] = config;
		numElements++;

		if (numElements == configArray.length) {
			expandCapacity();
		}
	}

	// The expandCapacity() method increases the size of configArray if it gets
	// filled
	/**
	 * 
	 */
	private void expandCapacity() {
		String[] largerList = new String[configArray.length * 2];
		for (int i = 0; i < configArray.length; i++)
			largerList[i] = configArray[i];

		configArray = largerList;
	}

	// The searchArray() takes a configuration and a configArray as inputs and
	// checks to see if that configuration is in
	// configArray
	/**
	 * @param element
	 * @param array
	 * @return
	 */
	private boolean searchArray(String element, String[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	// The hashfunction() method takes the configuration as an input and
	// converts it into a hashvalue using a polynomial
	// hash function and returns that value
	/**
	 * @param config
	 * @return
	 */
	private int hashfunction(String config) {
		int hash = config.charAt(0);
		for (int i = 0; i < config.length(); i++) {
			hash = (hash * 479 + config.charAt(i)) % hashtable.length;
		}
		return (hash);
	}

	// The put() method takes the record as an input and inserts that record
	// into the hashtable. If that record is already there, it will throw a
	// DuplicateKeyException.
	/**
	 * @param record
	 * @return
	 * @throws DuplicatedKeyException
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException {

		index = hashfunction(record.getConfiguration());
		Hashnode.setElement(record);
		if (hashtable[index].isEmpty()) {
			hashtable[index].insertLast(Hashnode);
			addConfig(record.getConfiguration());
			return 0;
		} else {
			if (searchArray(record.getConfiguration(), configArray)) {
				throw new DuplicatedKeyException(record.getConfiguration());
			}
			hashtable[index].insertLast(Hashnode);
			addConfig(record.getConfiguration());
			return 1;
		}

	}

	// The remove() method takes a configuration as an input and removes that
	// configuration from the hashtable. If that configuration does that not
	// exist, it will throw an InexistentKeyException
	/**
	 * @param config
	 * @throws InexistentKeyException
	 */
	public void remove(String config) throws InexistentKeyException {
		index = hashfunction(config);

		current = hashtable[index].head;

		if (hashtable[index].isEmpty())
			throw new InexistentKeyException(config);

		if (current.getElement().getConfiguration().equals(config)) {
			hashtable[index].remove(current.getElement());
			return;
		}
		while ((current.getNext() != null) && (current.getNext().getElement() != null)) {
			if (current.getElement().getConfiguration().equals(config)) {
				hashtable[index].remove(current.getElement());
				return;
			}
			current = current.getNext();
		}
		if (current.getElement() == null && current.getNext() == null)
			throw new InexistentKeyException(config);

	}

	// The get() method will take a configuration as an input and return the
	// record of that configuration if it is in the hashtable
	/**
	 * @param config
	 * @return
	 */
	public Object get(String config) {

		index = hashfunction(config);

		current = hashtable[index].head;

		if (current == null)
			return null;

		if (current.getElement().getConfiguration().equals(config))
			return current.getElement();
		while (!(current.getElement().getConfiguration().equals(config)) && (current.getNext() != null)) {
			current = current.getNext();
		}
		if (current.getElement() == null && current.getNext() == null)
			return null;
		return current.getElement();
	}

	// The numElements() method returns the number of elements in the hashtable
	/**
	 * @return
	 */
	public int numElements() {
		return numElements;
	}
}
