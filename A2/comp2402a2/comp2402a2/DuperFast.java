package comp2402a2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
//import java.util.LinkedList;

public class DuperFast implements DuperDeque {
  protected SuperFast front, back;

  public DuperFast() {
    front = new SuperFast();
    back = new SuperFast();
  }

  public void addFirst(Integer x) {
    front.push(x);
    balance();  }

  public void addLast(Integer x) {
    back.push(x);
    balance();
  }

  public Integer removeFirst() {
    balance();
    if(front.isEmpty()){
      return back.pop();
    }
    return front.pop();
  }

  public Integer removeLast() {
    balance();
    if(back.isEmpty()){
      return front.pop();
    }
    return back.pop();
  }

  public Integer max() {
    if(isEmpty()){
      return null;
    }
    if(front.max() == null){
      return back.max();
    }else if(back.max() == null) {
      return front.max();
    }
      if (front.max() >= back.max()) {
        return front.max();
      } else {
        return back.max();
      }


  }

  public long ksumFirst(int k) {
    if(k>=size()){
      return front.fullSum() + back.fullSum() ;
    }else{
      if(k<=front.size()){
        return front.ksum(k);
      }else{
        return front.fullSum() + (back.fullSum()- back.ksum(size()-k));
      }
    }
  }

  public long ksumLast(int k){
    if(k>=size()){
      return front.fullSum() + back.fullSum() ;
    }else{
      if(k<=back.size()){
        return back.ksum(k);
      }else{
        return back.fullSum() + (front.fullSum()- front.ksum(size()-k));
      }
    }
  }

  public int size() {
    return front.size() + back.size();
  }

  public Iterator<Integer> iterator() {
    ArrayList<Integer> fullDeque = new ArrayList<>();
    fullDeque.addAll(front.getStack());
    Collections.reverse(fullDeque);
    fullDeque.addAll(back.getStack());

    return fullDeque.iterator();
  }

  public void balance() {
    int n = size();

    if (3 * front.size() < back.size()) {
      int s = n / 2 - front.size();

      SuperFast l1 = new SuperFast();
      SuperFast l2 = new SuperFast();

      front=front.reverse();
      l2.pushElements(back.size() - s, back);
      l2=l2.reverse();
      l1.pushElements(s, back);
      l1.pushElements(front.size(), front);

      front = l1;
      back = l2;
    } else if (3 * back.size() < front.size()) {
      int s = n / 2 - back.size();

      SuperFast l1 = new SuperFast();
      SuperFast l2 = new SuperFast();

      back=back.reverse();
      l1.pushElements(front.size() - s, front);
      l1=l1.reverse();
      l2.pushElements(s, front);
      l2.pushElements(back.size(), back);

      front = l1;
      back = l2;
    }
  }

  public boolean isEmpty(){
    if(front.isEmpty() && back.isEmpty()){
      return true;
    }else{
      return false;
    }
  }
}