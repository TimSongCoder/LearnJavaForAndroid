public class ConcreteStrategy2 implements Strategy{
  public void execute(String msg){
    for(int i=0; i< 3; i++){
      System.out.printf("executing strategy #2 actually: msg = %s%n", msg);
    }
  }
}
