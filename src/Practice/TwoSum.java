package Practice;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers
 */
public class TwoSum {
    private static int[] twoSum(int[] numbers, int target){
        //define the return value
        int[] indexArray = new int[2];

        //handle corner cases
        if(numbers==null || numbers.length==0){
            return null;
        }

        //value->index map
        Map<Integer,Integer> hasMap = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            hasMap.put(numbers[i],i);
        }

        for(int i=0;i<numbers.length;i++){
            if(hasMap.containsKey(target-numbers[i])){
                indexArray[0]=i;
                indexArray[1]=hasMap.get(target-numbers[i]);
            }
            if(indexArray[0]==indexArray[1]) {
                continue;
            }
            return indexArray;
        }

        return null;
    }
}
