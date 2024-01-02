package comp2402a2;

import java.util.Iterator;
import java.util.Stack;


public class SuperFast implements SuperStack {
  protected Stack<Integer> maxStack;
  protected Stack<Long> prefixSum;
  protected Stack<Integer> ds;
  public SuperFast() {
    ds = new Stack<>();
    prefixSum = new Stack<>();
    maxStack = new Stack<>();
    prefixSum.push(0L);
    maxStack.push(0);
  }

  public void push(Integer x) {
//    if (ds.isEmpty()) {
//      maxStack.push(x);
//    } else {
      if (x > maxStack.peek()) {
        maxStack.push(x);
      }else{
        maxStack.push(maxStack.peek());
//      }
    }
    prefixSum.push(prefixSum.peek()+x);
    ds.push(x);
  }

  public Integer pop() {
    if (ds.isEmpty()) {
      return null;
    }
    prefixSum.pop();
    maxStack.pop();
    return ds.pop();
  }

  public Integer max() {
    if(ds.isEmpty()){
      return null;
    }
    return maxStack.peek();
  }

  public long ksum(int k) {
    if(k<size()){
      return (prefixSum.peek() - prefixSum.get(ds.size() - k));
    }else{
      return fullSum();
    }
  }
  public int size() {
    return ds.size();
  }

  public Iterator<Integer> iterator() {
    return ds.iterator();
  }
  public boolean isEmpty(){
    return ds.isEmpty();
  }
  public SuperFast reverse() {
    SuperFast reversed = new SuperFast();
    while (!isEmpty()) {
      reversed.push(this.pop());
    }
    return reversed;
  }
  public void pushElements(int amount, SuperFast source){
    //if(!source.isEmpty()){
      while (amount!=0){
        push(source.pop());
        amount--;
      }
    //}
  }
  public long fullSum(){
    return prefixSum.peek();
  }
  public Stack<Integer> getStack() {
    return ds;
  }
}