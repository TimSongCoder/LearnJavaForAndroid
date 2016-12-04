public class Context{
  private Strategy strategy;

  public Context(Strategy strategy){
   setStrategy(strategy);
  }

  public void setStrategy(Strategy strategy){
    this.strategy = strategy;
  }

  public void executeStrategy(String msg){
    if(strategy == null){
      System.err.println("No strategy to be used at all...");
      return;
    }
    this.strategy.execute(msg);
  }
}
