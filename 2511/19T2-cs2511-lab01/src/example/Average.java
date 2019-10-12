package example;

/**
 * Average example
 * @author Aarthi
 */
public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public float average(int[] nums) {
            float result = 0;
	        int i;
	        for(i=0; i< nums.length;i++) {
	        		result += nums[i];
	        }
	        result = result/Float.valueOf(nums.length);
	        return result;
        }

        public static void main(String[] args) {
            int[] a = {2,3,4,5,6};
	    		Average arr = new Average();
	    		
	    		float r = arr.average(a);
	    		 System.out.println(r);
        }
}
