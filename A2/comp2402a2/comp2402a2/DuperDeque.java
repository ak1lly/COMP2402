package comp2402a2;

public interface DuperDeque extends Iterable<Integer> {
  public void addFirst(Integer x);
  public void addLast(Integer x);
  public Integer removeFirst();
  public Integer removeLast();

  public Integer max();
  public long ksumFirst(int k);
  public long ksumLast(int k);
  
  public int size();
}