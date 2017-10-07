public class HashNode<K,V>{
    
    private HashNode<K,V> next;
    private K key;
    private V value;

    public HashNode(){
        next = null;
        key = null;
        value = null;
    }
    public HashNode(K key, V value){
        next = null;
        this.key = key;
        this.value = value;
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
    public V getvalue(){
        return value;
    }
    public void setElement(V element){
        value = element;
    }

}
