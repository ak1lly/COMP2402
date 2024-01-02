package comp2402a4;

import java.util.Iterator;
import java.util.Stack;

public class UltraFastCheeze implements UltraStack {
  // TODO: Your data structures go here
//  int[] pMax;
//  int[] pSum;
//  int[] stack;
//  int size;
  protected Stack<Integer> maxStack;
  protected Stack<Long> prefixSum;
  protected Stack<Integer> ds;
  public UltraFastCheeze() {
    // TODO: Your code goes here
    ds = new Stack<>();
    prefixSum = new Stack<>();
    maxStack = new Stack<>();
    prefixSum.push(0L);
    maxStack.push(0);
//    pMax = new int[10];
//    pSum = new int[10];
//    stack = new int[10];
//    size=0;
  }

  public void push(int x) {
    if (x > maxStack.peek()) {
      maxStack.push(x);
    }else{
      maxStack.push(maxStack.peek());
//      }
    }
    prefixSum.push(prefixSum.peek()+x);
    ds.push(x);
//    if(size+1>stack.length){
//      resize();
//    }
//    stack[size] = x;
//    pMax[size*2]=x;
//    pSum[size*2]=x;
  }

  public Integer pop() {
    // TODO: Your code goes here
    if (ds.isEmpty()) {
      return null;
    }
    prefixSum.pop();
    maxStack.pop();
    return ds.pop();
  }


  public Integer get(int i) {
//    // TODO: Your code goes here
//    return null;
    return ds.get(i);
  }

  public Integer set(int i, int x) {
//    // TODO: Your code goes here
//    return null;
    int diffInSum = x-ds.get(i+1);
    for(int j=i+1;j<=size()+1;j++){
      if(x>maxStack.get(j)){
        maxStack.set(j,x);
      }
      long oldSum = prefixSum.get(j);
      prefixSum.set(j, oldSum+diffInSum);
    }
    return ds.set(i,x);
  }

  public Integer max() {
//    // TODO: Your code goes here
//    return null;
    if(ds.isEmpty()){
      return null;
    }
    return maxStack.peek();
  }

  public long ksum(int k) {
    // TODO: Your code goes here
    if(k<size()){
      return (prefixSum.peek() - prefixSum.get(ds.size() - k));
    }else{
      return prefixSum.peek();
    }
//    return 0;
  }

  public int size() {
    // TODO: Your code goes here
    return ds.size();
  }

  public Iterator<Integer> iterator() {
//    // TODO: Your code goes here
//    return null;
    return ds.iterator();
  }

  public int left(int i){
    return 2*i+1;
  }
  public int right(int i){
    return 2*i+2;
  }
  public int parent(int i){
    return (i-1)/2;
  }

//  public void maxUp(){
//
//  }
//
//  public void updateSum(){}

//  public void resize(){
//    int[] ns = new int[size*2];
//    int[] npm = new int[2*(2*size-1)];
//    int[] nps = new int[2*(2*size-1)];
//
//    for(int i=0; i<2*size-1; i++){
//      if (i<size){
//        ns[i] = stack[i];
//      }
//      npm[i] = pMax[i];
//      nps[i] = pSum[i];
//    }
//    stack = ns;
//    pMax=npm;
//    pSum=nps;
//  }
}
