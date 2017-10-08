public class HashNode<K,V>{
    
    private HashNode<K,V> next;
    private K key;
    private V value;

    public HashNode(){
        next = null;
        key = null;
        value = null;
    }
    public HashNode(K keyindex, V element){
        next = null;
        key = keyindex;
        value = element;
    }
    public HashNode<K,V> getNext(){
        return next;
    }
    
    public void setNext(HashNode<K,V> node){
        next = node;
    }
    public K getKey(){
        return key;
    }
    public void setKey(K index){
        key = index;
    }
    public V getvalue(){
        return value;
    }
    public void setElement(V element){
        value = element;
    }

}
