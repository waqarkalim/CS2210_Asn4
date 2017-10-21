
public class UnsupportedOperationException extends RuntimeException
{

  public UnsupportedOperationException(String operation)
  {
    super ("The " + operation + " is not supported.");
  }
}
