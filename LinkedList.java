import java.util.*;

public class LinkedList<T> implements ListADT<T>
{
   protected int count;
   protected LinearNode<T> head, tail;
   
   LinearNode<T> current;
   
   /**
    * Creates an empty list.
    */
   public LinkedList()
   {
      count = 0;
      head = tail = null;
   }

   public void insertFirst(LinearNode<T> node){

	   if (isEmpty()){
		   head = node;
		   node.setNext(null);
	   } else {
		   node = head.getNext();
		   head = node;
	   }
	   count++;
	   
	   //	   head = node;
//	   if (isEmpty()){
//		   ;
//	   } else {
//		   node = head.getNext();
//	   }
//	   node = head.getNext();
//	   head = node;
   }
   public void insertLast(LinearNode<T> node){
	   current = head;
	   while (current != null){
		   
		   current = current.getNext();
	   }
	   current = node;
	   node = null;
	   head = current;
	   count++;
   }
   public T removeFirst() throws EmptyCollectionException
   {
       LinearNode<T> temp = head;
       head = head.getNext();
       count--;
       return temp.getElement();
       
   }
   
   public T removeLast() throws EmptyCollectionException
   {
       while (current != null){
    	   current = current.getNext();
       }
       count--;
	   return current.getElement();
   }
//   public T search(String targetElement) throws EmptyCollectionException{
//	   
//	   if (isEmpty())
//		   throw new EmptyCollectionException("List");
//	   
//	   LinearNode<T> current = head;
//	   
//	   while (current != null){
//		   System.out.println("Gets in here");
//		   current = current.getNext();
//		   System.out.println("Gets in here2");
//		   System.out.println(current);
//		   if (current.getElement().equals(targetElement)){
//			   System.out.println("Gets in here3");
//			   return current.getElement();
//		   }
//	   }
//	   return null;
//   }
   
   public T remove (T targetElement) throws EmptyCollectionException, ElementNotFoundException 
   {
      if (isEmpty()){
    	  System.out.println("Linkedlist is empty");
    	  throw new EmptyCollectionException ("List");
      }
      
      boolean found = false;
      LinearNode<T> previous = null;
      LinearNode<T> current = head;
      
      while (current != null && !found)
         if (targetElement.equals (current.getElement()))
            found = true;
         else
         {
            previous = current;
            current = current.getNext();
         }
            
      if (!found){
    	  System.out.println("Element not found exception");
    	  throw new ElementNotFoundException ("List");
      }
      if (size() == 1)
         head = tail = null;
      else if (current.equals (head))
         head = current.getNext();
      else if (current.equals (tail))
      {
         tail = previous;
         tail.setNext(null);
      }
      else
         previous.setNext(current.getNext());
      
      count--;
      
      return current.getElement();
   }
   
   
   public boolean contains (T targetElement) throws EmptyCollectionException 
   {
       while (current != null){
    	   if (targetElement.equals(current.getElement())){
    		   return true;
    	   }
    	   current = current.getNext();
       }
       return false;
   }
   
   
   public boolean isEmpty()
   {
	   return (head == null);
//	   return (size() == 0);
   }

  
   public int size()
   {
//	   int count = 0;
//       while (current != null){
//    	   count++;
//    	   current = current.getNext();
//       }
//       return count;
	   return count;
   }
   public String toString()
   {	
	   String result = "";
       LinearNode<T> current = head;
       
	   if (isEmpty()){
		   result = "";
	   } else {
	   while(current.getNext() != null){
           current = current.getNext();
           System.out.println("The value of current is " + current.toString());
           result += current.getElement() + ", ";
       }
	   }
       return "List: " + result;
   }

   public T first()
   {
	   return head.getElement();
   }
   public T last()
   {
	   return tail.getElement();
   }

@Override
public boolean searchAvailability(int index) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Iterator<T> iterator() {
	// TODO Auto-generated method stub
	return null;
}
}


