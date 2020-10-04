package ac.udsm.dca.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayOperationsService<T>{

    public List<List<Integer>> permuteArray(Integer lengthOfArrayToPermute){
        List<List<Integer>> permutations = new ArrayList();

        // length of resulting array is 2**lengthOfArrayToPermute
        String lastAction = Integer.toBinaryString(lengthOfArrayToPermute);

        for (int i = 0; i < lengthOfArrayToPermute; i++) {

            List binaryArrayForI = Arrays.asList(Integer.toBinaryString(i).split(""));
            if(binaryArrayForI.size() < lastAction.length()){
                int remainingZeros = lastAction.length() - binaryArrayForI.size();
                for (int j = 0; j < remainingZeros; j++) {
                    binaryArrayForI.add(binaryArrayForI.size()-1, 0);
                }
            }
            System.out.println(binaryArrayForI);
            permutations.add(binaryArrayForI);
        }

        return permutations;
    }

    public List<Integer> permutationForI(int maxLength, int action){
        List<Integer> permutation = Arrays.asList(Integer.toBinaryString(action).split("")).stream().map(Integer::parseInt).collect(Collectors.toList());

        int remainingItems = maxLength - permutation.size();
        if (remainingItems != 0){
            for (int i = 0; i < remainingItems; i++) {
                permutation.add(0, 0);
            }
        }

        return permutation;
    }

}
