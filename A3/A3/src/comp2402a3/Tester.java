package comp2402a3;
import java.util.Iterator;
import java.util.Random;

public class Tester {
    static <T> void showContents(Iterable<T> ds) {
        System.out.print("[");
        Iterator<T> it = ds.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static void skippityTest(int n){
        Random rand = new Random();
        SkippityFast<Integer> iss = new SkippityFast<>();
        SkippitySlow<Integer> idd = new SkippitySlow<>();

        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(3*n);
            iss.add(x);//System.out.println("add(" + x + ") = " + iss.add(x));
            idd.add(x);
        }

        System.out.print("\nContents(Fast): ");
        showContents(iss);
        System.out.print("Contents(Slow): ");
        showContents(idd);

        System.out.println("size()=" + iss.size());

        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(3*n);
            //System.out.println("index(" + x + ") = " + iss.getIndex(x));
        }

        for (int i = 0; i < iss.size(); i++) {
            System.out.println("get(" + i + ")=" + iss.get(i));
        }

        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(3*n);
            int y = rand.nextInt(3*n);
            System.out.println("index(" + x + ") = " + iss.getIndex(x));
            System.out.println("index(" + y + ") = " + iss.getIndex(y));
            System.out.println("rangecountFast(" + x + ", " + y + ") = "+iss.rangecount(x, y));
            System.out.println("rangecountSlow(" + x + ", " + y + ") = "+idd.rangecount(x, y));
        }


        for (int i = 0; i < 5; i++) {
            System.out.println("index(" + iss.get(i) + ") = " + iss.getIndex(iss.get(i)));
            System.out.print("Contents: ");
            showContents(iss);
            System.out.println("remove(" + iss.get(i) + ") = " + iss.remove(iss.get(i)));
        }
        for (int i = 0; i < iss.size(); i++) {
            System.out.println("get(" + i + ")=" + iss.get(i));
        }

        System.out.println("size()=" + iss.size());

    }

    static void treeTest(int n){
        BinaryTree t = BinaryTree.randomBST(n);
        System.out.println("Tree:" + t);
        System.out.println("size() = " + t.size());
        System.out.println("leafAndOnlyLeaf() = " + t.leafAndOnlyLeaf());
        System.out.println("dawnOfSpring() = " + t.dawnOfSpring());
        System.out.println("monkeyLand() = " + t.monkeyLand());
        System.out.println("bracketSequence() = " + t.bracketSequence());
    }

    public static void main(String[] args) {
        skippityTest(20);
//        treeTest(20);
    }
}
