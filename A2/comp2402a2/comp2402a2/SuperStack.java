package comp2402a2;

public interface SuperStack extends Iterable<Integer> {
  public void push(Integer x);
  public Integer pop();

  public Integer max();
  public long ksum(int k);

  public int size();

  }