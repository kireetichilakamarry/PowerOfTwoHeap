import java.util.ArrayList;
import java.util.List;

public class powerOfTwoHeap {
    List<Integer> lstRep;
    int height;
    public powerOfTwoHeap(int height) {
        lstRep = new ArrayList<>();
        lstRep.add(-1); //placeholder for 0th item since heap should be 1-indexed
        this.height = height;
    }
    public int insert(int elem) {
        lstRep.add(elem);
        heapify(1, 0);
        return elem;
    }
    public void popMax() {
        if (lstRep.size() > 1) {
            lstRep.remove(1);
            lstRep.add(lstRep.remove(lstRep.size() - 1)); //brings last element to the front
            heapify(1, 0);
        }
    }
    private void heapify(int startElem, int currHeight) {
        int maxC = maxChild(startElem, );
        if (lstRep.get(maxC) > lstRep.get(startElem)) {
            int temp = lstRep.get(maxC);
            lstRep.set(maxC, lstRep.get(startElem));
            lstRep.set(startElem, temp);
            heapify(maxC, currHeight + 1);
        }
    }
    private int maxChild(int startElem, int endElem) {
        int maxElem = startElem;
        while (startElem < endElem && startElem < lstRep.size()) {
            if (lstRep.get(startElem) > lstRep.get(maxElem)) {
                maxElem = startElem;
            }
            startElem++;
        }
        return maxElem;
    }
}
