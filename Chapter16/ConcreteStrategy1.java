public class ConcreteStrategy1 implements Strategy{
  public void execute(String msg){
    System.out.printf("executing strategy #1 actually: msg = %s%n", msg);
  }
}
