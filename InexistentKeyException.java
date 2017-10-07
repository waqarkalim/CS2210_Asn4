public class InexistentKeyException extends RuntimeException{
    public InexistentKeyException (String key){
        super ("This " + key + " does not exist");
    }
}
