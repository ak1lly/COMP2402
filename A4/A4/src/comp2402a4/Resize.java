package comp2402a4;

import static java.lang.Math.*;

public class Resize {

    public static void main(String[] args){
        int[] pMax = {1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,13,14,15};
        int size = 15;

        System.out.println("\n");
        for(int i=0; i<size; i++){
            System.out.print(pMax[i] + ",");
        }

        int[] npm = new int[size * 2 + 1];
        int h = (int)(log(size+1)/log(2));
        while(h!=0){
            int levelSize = (int)pow(2,h);
            for(int i=-1; i<levelSize/2-1; i++){
                npm[levelSize+i] = pMax[levelSize/2+i];
            }
            h--;
        }
        size=size*2+1;

        npm[0] = pMax[0];
        pMax=npm;

        System.out.println("\n");
        for(int i=0; i<size; i++){
            System.out.print(pMax[i] + ",");
        }
    }
    public void resize(int[] pMax, int size) {
        int[] npm = new int[size * 2 + 1];
        long[] nps = new long[size * 2 + 1];
        int h = (int) Math.sqrt(size + 1);
        pMax = npm;
    }
    public void printMax(int[] pMax, int size){
        System.out.println("\n");
        for(int i=0; i<size*2+1; i++){
            System.out.print(pMax[i] + ",");
        }
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
}
