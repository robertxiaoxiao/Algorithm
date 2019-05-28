package LeetCode.BinarySearch;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 */
public class q33 {

    public int search(int[] nums, int target) {
        int i=0;
        int l=nums.length-1;
        // 数组为空 ，为一个元素，
        if(l==-1)
            return -1;

        //只有一个元素时，防止数组越界，最好是把 最初的情况直接输出
        while(i<=l&&i+1<=l&&nums[i]<nums[i+1])
            i++;

        int cut=i;

        int left=bsearch(nums,0,cut,target);
        int right=bsearch(nums,cut+1,l,target);

        if(left!=-1)
            return left;


        if(right!=-1)
            return right;

        return  -1;



    }


    public int bsearch(int[] nums,int start ,int end ,int target){

        int low=start;
        int high=end;

        while(low<=high)
        {
            int mid=low+(high-low)/2;

            if(nums[mid]>target)
                high=mid-1;
            else if(nums[mid]<target)
                low=mid+1;
            else
                return mid;
        }

        return -1;
    }
}
