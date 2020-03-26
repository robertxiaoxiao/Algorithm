package Search.BinarySearch;


/*
  二分查找最普遍的应用场景是   针对有序数列集合的查找算法

  对于给定值的查找，用二分查找能解决的，绝大部分我们更倾向于用散列表或者二叉者二叉查找树。
  而二分查找最大的优势在于可以实现“近似查找”，用其他数据结构实现比如散列表，二叉树就比较难实现。

 */
public class BinarySearch {


    /*
       基本的二分查找算法
     */

    public static int basicBS(int[] a , int target){

        int  low=0;
        int  high=a.length-1;


        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(target==a[mid])
                return mid;
            else if(a[mid]>target)
                high=mid-1;
            else
                low=mid+1;
        }
        return -1;

    }


    public static void main(String[] args) {

        int[] a={1,2,3,4,5,5,5,5,5,5,5,7,8,9};
        int[] b={3,4,6,7,10};

       // System.out.println(findLast(a,5));
        System.out.println(findFisrtUnmin(b,5));
        System.out.println(findLastUnmax(b,5));
    }
    /*
      二分查找的四类变型
     */

    /*
    变体一：查找第一个值等于给定值的元素
       有序数列中有重复的数据
     */
    public  static int findFisrt(int[] a ,int target){

        int low=0;
        int high=a.length-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(target==a[mid]) {
//                int temp=target;
////                while(temp>=0&&a[temp]==target)
////                    temp--;
////                //tucihu
////                if(temp<0)
////                    break;
////                //zhaodoa
////                return temp+1;
                if(mid==0||a[mid-1]!=target)
                    return mid;
                else
                    high=mid-1;
            }
            else if(a[mid]>target)
                high=mid-1;
            else
                low=mid+1;

        }
        return -1;
    }


    /*
    变体二：查找最后一个值等于给定值的元素
     */
    public  static int findLast(int[] a,int target){
        int low=0;
        int high=a.length-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(target==a[mid]) {
                if(mid==a.length-1||a[mid+1]!=target)
                    return mid;
                else
                    low=mid+1;
            }
            else if(a[mid]>target)
                high=mid-1;
            else
                low=mid+1;

        }
        return -1;
    }

    /*
       变体三：查找第一个值大于等于给定值的元素
     */

    public  static int findFisrtUnmin(int[] a ,int target){
        int low=0;
        int high=a.length-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(a[mid]>=target) {
                if(mid==0||a[mid-1]<target)
                    return mid;
                else
                    high=mid-1;
            }
            else
                low=mid+1;
        }
        return -1;
    }

       /*
       变体四：查找最后一个值小于等于给定值的元素
     */

    public  static int findLastUnmax(int[] a ,int target){
        int low=0;
        int high=a.length-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(a[mid]<=target) {
                if(mid==a.length-1||a[mid+1]>target)
                    return mid;
                else
                    low=mid+1;
            }
            else
                high=mid-1;
        }
        return -1;
    }
}
