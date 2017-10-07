import java.util.ArrayList;

public class TTTDictionary{

    private ArrayList<HashNode<K,V>> NodeArray;
    private int capacity;
    private int size;

    public TTTDictionary(){
        NodeArray = new ArrayList<>();
        capacity = 10;
        size = 5;

        for (int i = 0; i < capacity; i++){
            NodeArray.add(null);
        }
    }

    public int put(TTTRecord record) throws DuplicatedKeyException{
        
        return 1;
    }
    public void remove(String config) throws InexistentKeyException{

    }
    public TTTRecord get(String config){

    }
    public int numElements(){

    }
}
