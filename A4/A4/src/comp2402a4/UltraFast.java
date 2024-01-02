package comp2402a4;

import java.util.Iterator;
import java.util.Stack;

import static java.lang.Math.pow;

public class UltraFast implements UltraStack {
  Stack<Integer> stack;
  int[] pMax;
  long[] pSum;
  int size;
  int height;
  public UltraFast(){
    stack = new Stack<>();
    pMax = new int[1];
    pSum = new long[1];
    size=0;
    height=0;
  }
  public void push(int x) {
    if(size+1>pMax.length){
      resize();
    }
    pMax[size]=x;
    maxUp(size);
    pSum[size]=x;
    sumUp(size, x);
    stack.push(x);
    size++;
  }
  public Integer pop() {
    if(stack.isEmpty()){
      return null;
    }else{
      int x = pMax[size-1];
      pMax[size-1]=0;
      maxUp(size-1);
      pSum[size-1]=0;
      sumUp(size-1, -(x));
      stack.pop();
      size--;
      return x;
    }
  }
  public Integer get(int i) {
    return stack.get(i);
  }
  public Integer set(int i, int x) {
    int index = size - size() + i;

    pMax[index] = x;
    maxUp(index);
    int prev = (int)pSum[index];
    int diff = x - prev;
    pSum[index] = x;
    sumUp(index, diff);
    stack.set(i,x);

    return prev;
  }
  public Integer max() {
    if(stack.isEmpty()){
      return null;
    }
    return pMax[0];
  }
  public long ksum(int k) {
    if(stack.isEmpty() || k == 0){
      return 0;
    }
    if(k>size() || size()==1){
      return pSum[0];
    }
    int front = size - k;
    int back = size - 1;

    long counter = 0;

    while(front != back){
      if(front%2 == 0){
        counter += pSum[left(parent(front))];
      }
      if(back%2 != 0){
        counter += pSum[right(parent(back))];
      }

      front = parent(front);
      back = parent(back);

    }

    return pSum[front] - counter;
  }

  public int size() {
    return stack.size();
  }
  public Iterator<Integer> iterator() {return stack.iterator();}
  public int left(int i){return 2*i+1;}
  public int right(int i){return 2*i+2;}
  public int parent(int i){return (i-1)/2;}
  public void maxUp(int i){
    int p=i;
    while(p!=0){
      p = parent(p);
      pMax[p] = Math.max(pMax[left(p)], pMax[right(p)]);
    }
  }
  public void sumUp(int i, int x){
    int p = parent(i);
    while(i > 0){
      pSum[p] += x;
      i = p;
      p = parent(i);
    }
  }
  public void resize(){
    int[] npm = new int[size * 2 + 1];
    long[] nps = new long[size * 2 + 1];
    height++;
    int h = height;
    size = size*2+1 - size();
    while(h!=0){
      int levelSize = (int)pow(2,h);
      for(int i=-1; i<levelSize/2-1; i++){
        npm[levelSize+i] = pMax[levelSize/2+i];
        nps[levelSize+i] = pSum[levelSize/2+i];
      }
      h--;
    }
    npm[0] = pMax[0];
    nps[0] = pSum[0];
    pMax=npm;
    pSum=nps;
  }
}
