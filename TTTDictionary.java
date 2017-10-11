import com.sun.prism.impl.Disposer.Record;

public class TTTDictionary{
	private LinearNode<TTTRecord> head = null;
	private LinearNode<TTTRecord> current;
	private LinearNode<TTTRecord> Hashnode;
	private LinkedList list;
	private LinkedList[] hashtable;
	private int numElements;
	private int index;
	private String[] configArray;
	
	public TTTDictionary(int size){
		
		Hashnode = new LinearNode<TTTRecord>();
		hashtable = new LinkedList[size];
		configArray = new String[size];
		for (int i = 0; i < hashtable.length; i++){
			list = new LinkedList();
			hashtable[i] = list;
		}
		numElements = 0;
		
	}
	
	private boolean searchArray(String element, String[] array){
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element){
				return true;
			}
		}
		return false;
	}
	
	private int hashfunction(String config){
		int hash = config.charAt(0);
		for (int i = 0; i < config.length(); i++){
			hash = (hash*47 + config.charAt(i)) % hashtable.length;
			
		}
		return (hash % hashtable.length);
	}
	
	//This is not an optimised function, but it works. Don't wake up sleeping giants. 
	public int put(TTTRecord record) throws DuplicatedKeyException{
		index = hashfunction(record.getConfiguration());
		Hashnode.setElement(record);
		if (hashtable[index].isEmpty()){
			hashtable[index].insertLast(Hashnode);
			configArray[index] = record.getConfiguration();
			return 0;
		} else {
			if (searchArray(record.getConfiguration(), configArray)){
				throw new DuplicatedKeyException(record.getConfiguration());
			}
			hashtable[index].insertLast(Hashnode);
			configArray[index] = record.getConfiguration();
			return 1;
		}
		
//		for (int i = 0; i < hashtable.length; i++){
//			if (index == i){
//				if (hashtable[i].isEmpty()){
//					hashtable[i].insertLast(Hashnode);
//					configArray[i] = record.getConfiguration();
//					return 0;
//				} else {
//					if (searchArray(record.getConfiguration(), configArray)){
//						throw new DuplicatedKeyException(record.getConfiguration());
//					} 
//					hashtable[i].insertLast(Hashnode);
//					configArray[i] = record.getConfiguration();
//					return 1;
//				}
//			}
//		}
//		return 1;
	}
	
	public void remove(String config) throws InexistentKeyException{
		index = hashfunction(config);
		current = hashtable[index].head;
		System.out.println("The configuration is " + config);
//		System.out.println("Size of the linkedlist: " + hashtable[index].size());
		if (!searchArray(config, configArray) || hashtable[index].isEmpty()){
//			System.out.println("Key does not exist");
			throw new InexistentKeyException(config);
		}
		while (!(current.getElement().getConfiguration().equals(config))){
			if (current.getNext() == null)
				break;
			current = current.getNext();
			
		hashtable[index].remove(current.getElement());
		
//		if (hashtable[index].isEmpty()){
//			System.out.println("Index No. " + index);
//			System.out.println("The list is empty");
//			throw new InexistentKeyException(config);
//		} else {
//			while (!(current.getElement().getConfiguration().equals(config))) {
////				System.out.println(hashtable[index].current.getElement());
////				if (current != null)
////					System.out.println("Current != null");
////				if (!(hashtable[index].current.getElement().equals(config)))
////					System.out.println("The current element does not equal config");
////				System.out.println("Come in here");
//////				System.out.println(current.getElement().getConfiguration() + " " + config);
////
////				System.out.println(current.getElement().getConfiguration());
//				if (current.getNext() == null)
//					break;
//				current = current.getNext();
//			}
////			System.out.println("-----------------------");
////			System.out.println(current.getElement().getConfiguration());
//			hashtable[index].remove(current.getElement());
////			System.out.println("Removed");
////			System.out.println(hashtable[index].size());
		
		}
			// Complete this part
		
	}
	
	//This is not an optimised function, but it works. Don't wake up sleeping giants. 
	public Object get(String config) {
	
		index = hashfunction(config);
		int count = 0;
		LinkedList templist = hashtable[index];
		if (templist.isEmpty()) {
			return null;
		}
		while (!(templist.isEmpty()) && !(templist.head.getElement().equals(config))){
			templist = hashtable[(index + count) % hashtable.length];
			count++;
		}
		return templist;
		
	}
	public int numElements(){
		return numElements;
	}
}

