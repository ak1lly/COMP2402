package comp2402a2;

import java.util.ArrayList;
import java.util.Iterator;

public class DuperSlow implements DuperDeque {
  protected ArrayList<Integer> ds;

  public DuperSlow() {
    ds = new ArrayList<>();
  }

  public void addFirst(Integer x) {
    ds.add(0, x);
  }

  public void addLast(Integer x) {
    ds.add(x);
  }

  public Integer removeFirst() {
    if(size() <= 0)
      return null;
    else
      return ds.remove(0);
  }

  public Integer removeLast() {
    if(size() <= 0)
      return null;
    else
      return ds.remove(ds.size()-1);
  }

  public Integer max() {
    Integer m = null;
    for (int x : ds) {
      if (m == null || x > m) {
        m = x;
      }
    }
    return m;
  }

  public long ksumFirst(int k) {
    long sum = 0;
    for(int i=0; i<k && i<ds.size(); i++)
      sum += ds.get(i);
    return sum;
  }

  public long ksumLast(int k){
    long sum = 0;
    for(int i=0; i<k && i<ds.size(); i++)
      sum += ds.get(ds.size() - 1 - i);
    return sum;
  }

  public int size() {
    return ds.size();
  }

  public Iterator<Integer> iterator() {
    return ds.iterator();
  }
}