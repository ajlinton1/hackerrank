import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public class Node {
        public int value;
        public Node next;
        public Node below;
    }

    public static void main(String[] args) {
        int[] arr = {5,1,2,2,3,1};
        int result = degreeOfArray(arr);
        System.out.println(result);
    }

    static int searchNodes(Node root, int value) {
        if (root.value==value) {
            return 1;
        } else {
            if (root.next!=null) {
                return 1+searchNodes(root.next, value);
            } else if (root.below!=null) {
                return 1+searchNodes(root.below, value);
            }
        }
        return -1;
    }

    static int degreeOfArray(int[] arr) {
        int ret = 0;
        // Find max occuring element
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<arr.length;i++) {
            Integer count = map.get(arr[i]);
            if (count==null) {
                map.put(arr[i],1);
            } else {
                count++;
                map.put(arr[i],count);
            }
        }
        int degree = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if (entry.getValue()>degree) {
                degree = entry.getValue();
            }
        }

        int minLength = arr.length;
        // Search subarrays for ones with same degree
        for (int start=0;start<arr.length;start++) {
            for (int end=arr.length-1;end>=0;end--) {
                map = new HashMap<>();
                for (int i=start;i<end;i++) {
                    Integer count = map.get(arr[i]);
                    if (count==null) {
                        map.put(arr[i],1);
                    } else {
                        count++;
                        map.put(arr[i],count);
                    }
                }
                int currentDegree = 0;
                for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
                    if (entry.getValue()>currentDegree) {
                        currentDegree = entry.getValue();
                    }
                }
                int currentLength = end - start;
                if (currentDegree == degree) {
                    if (currentLength<minLength) {
                        minLength = currentLength;
                    }
                }
            }
        }

        return minLength;
    }

    // int[] result = oddNumbers(3,5);
    static int[] oddNumbers(int l, int r) {
        // Determine if l is odd
        boolean startOdd = (l/2.0 != Math.round(l/2.0));
        List<Integer> o = new ArrayList<>();
        int start = l;
        if (!startOdd) {
            start++;
        }
        for (int i=start;i<=r;i=i+2) {
            o.add(new Integer(i));
        }
        int ret[] = new int[o.size()];
        int x = 0;
        for (Integer i1:o) {
           ret[x] = i1.intValue();
           x++;
        }

        return ret;

    }
}


