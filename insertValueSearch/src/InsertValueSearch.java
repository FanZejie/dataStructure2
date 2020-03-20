import java.util.Arrays;

/**
 * 插值查找类似于二分查找，不同的是mid自适应
 * int mid = left + (right-left)*(findVal-arr[left])/(arr[right]-arr[left])
 * 1.对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
 * 2.关键字分布不均匀的情况下，该方法不一定比折半查找要好
 * @author Mike
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int [] arr = new int[100];
        for (int i = 0;i < 100;i++){//注意这里是<
            arr[i] = i+1;
        }
        //System.out.println(Arrays.toString(arr));
        int index = insertValueSearch(arr,0,arr.length-1,100);
        System.out.println("index="+index);
    }

    /**
     * 插值查找方法  ,也要求数组是有序的
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 如果找到就返回下标，没找到就返回-1
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        //|| findVal < arr[0] || findVal>arr[arr.length-1]这个判断必须有，不但可以优化，还可以防止mid越界
        if (left>right || findVal < arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        //求出mid的公式
        int mid = left + (right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){//向右边递归
            return insertValueSearch(arr, mid+1, right, findVal);
        }else if(findVal < midVal){
            return insertValueSearch(arr, left, mid-1, findVal);
        }else{
            return mid;
        }
    }
}
