package comp2402a4.Save;

import comp2402a4.UltraStack;

import java.util.Arrays;
import java.util.Iterator;

public class UltraFastCheap implements UltraStack {
  // TODO: Your data structures go here
  int[] pMax;
  int[] pSum;
  int[] stack;
  int size, treeSize;

  public UltraFastCheap() {
    // TODO: Your code goes here
    pMax = new int[15];
    pMax[0] = 0;
    pSum = new int[15];
    pSum[0] = 0;
    stack = new int[8];
    size=0;
    treeSize=0;
  }

  public void push(int x) {
    if(size+1>stack.length){
      resize();
    }
    stack[size]=x;
    size++;
    pSum[size]=pSum[size-1]+x;
    pMax[size]=Math.max(pMax[size-1],x);
  }

  public Integer pop() {
    // TODO: Your code goes here
    int removed = stack[size-1];

    pMax[size]= 0;
    pSum[size]= 0;
    stack[--size] = 0;
    return null;
  }


  public Integer get(int i) {
    return stack[i];
  }

  public Integer set(int i, int x) {
    int diff = x-stack[i];
    stack[i]=x;
    for (int j=i; j<size; j++){
      pSum[j]=pSum[j]+diff;
      if(pMax[i]<x){
        pMax[i]=x;
      }
    }
    return 1;
  }

  public Integer max() {
    if (size==0){
      return 0;
    }
    return pMax[size];
  }

  public long ksum(int k) {
    if (size==0){
      return 0;
    }
    if(k>=size){
      return pSum[size];
    }
    return pSum[size]-pSum[size-k];
  }

  public int size() {
    return size;
  }

  public Iterator<Integer> iterator() {
    return Arrays.stream(stack).iterator();
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

  public void maxUp(int i){
    double fullTreeCheck=Math.sqrt(size);
    if(fullTreeCheck==(int)fullTreeCheck){
      refactorTree(i, pMax);
    }
    int p = parent(i);
    while(i>0 && pMax[i]>pMax[p]){
      pMax[p] = pMax[i];
      i = p;
      p = parent(i);
    }
  }
  //if tree is full,
  public int refactorTree(int i, int[] arr){
    if(i==0){
      return 0;
    }else{
      int p = parent(i);
      //shift over by 1
      for(int j=p; j<=treeSize; j++){
        arr[j+1]=arr[j];
      }
      if(p==0){
        arr[p] = Math.max(arr[p + 1], arr[i]);
        treeSize++;
      }
      arr[p]=arr[i];
      treeSize++;
      return refactorTree(p, arr);
    }
  }

  public void printMax(){
    System.out.println("\n");
    for(int i=0; i<size*2-1; i++){
      System.out.print(pMax[i] + " : ");
    }
  }
  public void printStack(){
    System.out.println("\n");
    for(int i=0; i<size; i++){
      System.out.print(stack[i] + " : ");
    }
  }
  public void updateSum(){}

  public void resize(){
    int[] ns = new int[size*2];
    int[] npm = new int[size*4];
    int[] nps = new int[size*4];

    for(int i=0; i<2*size-1; i++){
      if (i<size){
        ns[i] = stack[i];
      }
      npm[i] = pMax[i];
      nps[i] = pSum[i];
    }
    stack = ns;
    pMax=npm;
    pSum=nps;
  }
}
