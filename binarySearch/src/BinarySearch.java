import java.util.ArrayList;
import java.util.List;

/**
 * //使用二分查找，数组必须是有序的
 * @author Mike
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1000,1000,1000,1234};
        /*int resultIndex = binarySearch(arr,0,arr.length-1,88);
        System.out.println(resultIndex);*/
        List<Integer> resultIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resultIndexList="+resultIndexList);
    }

    /**
     *
     * @param arr  数组
     * @param left  左边的索引
     * @param right  右边的索引
     * @param findVal  要查找的值
     * @return 如果找到就返回下标，没找到就返回-1
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        //当left>right时，说明递归了整个数组，但没有找到
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        }else if(findVal<midVal){
            //向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }

    }

    /**
     * 有多个相同数值，要把他们都查找出来的二分查找
     * @思路 找到mid值时，不要马上返回，先向mid索引左边扫描，将所有满足findVal的元素的下标加入到一个集合中
     * 再向右边扫描，。。。。，再将ArrayList返回即可
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 一个list装着查询值的索引值
     */
    public static List<Integer> binarySearch2(int[] arr,int left,int right,int findVal){
        if (left>right){
            return new ArrayList<Integer>();
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal>midVal){
            //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        }else if(findVal<midVal){
            //向左递归
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            List<Integer> resultIndexList = new ArrayList<>();
            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                } else {//把temp放入集合中
                    resultIndexList.add(temp);
                    temp--;//temp左移
                }
            }
            resultIndexList.add(mid);

            //向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                } else {//把temp放入集合中
                    resultIndexList.add(temp);
                    temp++;//temp左移
                }
            }
            return resultIndexList;
        }
    }
}
