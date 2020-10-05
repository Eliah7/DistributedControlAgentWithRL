package tests.utils;

import ac.udsm.dca.utils.ArrayOperationsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;

public class ArrayOperationsServiceTests {
    ArrayOperationsService<Integer> arrayOperationsService;

    @Before
    public void setUp() {
        arrayOperationsService = new ArrayOperationsService();
//        Integer n = 1;
//        System.out.println(BigInteger.valueOf(n).toByteArray().toString());
//        List coint = Arrays.asList(Integer.toBinaryString(8).split(""));
//        System.out.println(coint);
    }

    @Test
    public void testN0Permutation() {
        List result = arrayOperationsService.permuteArray(0);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testN1Permutation() {
        int arr1c1[] = {0};
        int arr1c2[] = {1};
        List arrList[] = {Arrays.asList(arr1c1), Arrays.asList(arr1c2)};

        List result = arrayOperationsService.permuteArray(1);
        Assert.assertEquals(result.size(), Math.pow(2, 1));

        Assert.assertTrue(Arrays.asList(arrList).containsAll(result));
    }

    @Test
    public void testN2Permutation() {
        int arr2c1[] = {0, 0};
        int arr2c2[] = {0, 1};
        int arr2c3[] = {1, 0};
        int arr2c4[] = {1, 1};
        List arrList2[] = {Arrays.asList(arr2c1), Arrays.asList(arr2c2), Arrays.asList(arr2c3), Arrays.asList(arr2c4)};

        List result = arrayOperationsService.permuteArray(2);
        Assert.assertEquals(result.size(), Math.pow(2, 2));

        Assert.assertTrue(Arrays.asList(arrList2).containsAll(result));
    }

    @Test
    public void testN3Permutation() {
        int arr2c1[] = {0, 0, 0};
        int arr2c2[] = {0, 0, 1};
        int arr2c3[] = {0, 1, 0};
        int arr2c4[] = {0, 1, 1};
        int arr2c5[] = {1, 0, 0};
        int arr2c6[] = {1, 0, 1};
        int arr2c7[] = {1, 1, 0};
        int arr2c8[] = {1, 1, 1};
        List arrList[] = {Arrays.asList(arr2c1), Arrays.asList(arr2c2), Arrays.asList(arr2c3),
                Arrays.asList(arr2c4), Arrays.asList(arr2c5), Arrays.asList(arr2c6), Arrays.asList(arr2c7), Arrays.asList(arr2c8)};

        List result = arrayOperationsService.permuteArray(3);
        Assert.assertEquals(result.size(), Math.pow(2, 3));

        Assert.assertTrue(Arrays.asList(arrList).containsAll(result));
    }

    @Test
    public void testPermutationForI(){
        List result = arrayOperationsService.permutationForI(9, 3);
        List<Double> expectedResult = List.of(0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0);

        System.out.println(result);
        System.out.println(expectedResult);
        Assert.assertTrue(expectedResult.containsAll(result));

    }

    @Test
    public void testDot(){
        List<Double> arr1 = List.of(2.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0);
        List<Double> arr2 = List.of(2.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0,2.0);

        List<Double> expectedResult = List.of(4.0,0.0,0.0,0.0,0.0,0.0,0.0,2.0,2.0);

        try {
            Double result = arrayOperationsService.dot(arr1, arr2);
            Assert.assertEquals(expectedResult.stream().reduce(Double::sum).get(), result);
        } catch (Exception e){
            System.out.println(e);
        }

    }
}
