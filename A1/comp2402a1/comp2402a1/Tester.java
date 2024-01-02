package comp2402a1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tester {
    private static int binarySearch(List<String> l, String target) {
        Collections.sort(l);
        int high = l.size() - 1;
        int low = 0;

        while (low <= high) {
            int med = (high + low) / 2;
            String middleElement = l.get(med);
            if (middleElement.contains(target)) {
                return med;
            }
            if (target.compareTo(middleElement) <= 0) {
                high = med - 1;
            } else {
                low = med + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        List<String> l = new ArrayList<>();
        String target = "tour";
        String test = "to";
        int tlen = test.length();

        for(int i = 0; i<=10; i++){
            l.add(String.format("%d",i));
        }
        System.out.println(target.substring(0,tlen));

        System.out.println(binarySearch(l,target));
        if(binarySearch(l,target)==-1){
            System.out.println("yipee");
        }else{
            System.out.println("hoes mad");
        }
        System.out.println(l);
    }
}
