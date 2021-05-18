package practice_best_subarray_sum;

public class BestSumArray {

    int max_sum;
    int start_index;
    int end_index;

    public void best_max_sum(int[] arr) {
        int temp_max_sum = start_index = end_index = 0, s = 0;
        max_sum = arr[0];
        for(int i = 0; i < arr.length; i++) {
            temp_max_sum += arr[i];

            if(max_sum < temp_max_sum) {
                max_sum = temp_max_sum;
                start_index = s;
                end_index = i;
            }

            if(temp_max_sum < 0) {
                temp_max_sum = 0;
                s = i + 1;
            }
        }
    }

}
