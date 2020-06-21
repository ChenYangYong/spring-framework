package yancy;

import java.util.*;

public class TestLeetCode {
	public static void main(String[] args) {
		Integer[] a = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		Arrays.sort(a,Collections.reverseOrder());
		System.out.println(Arrays.toString(a));

//		TestLeetCode leetCode = new TestLeetCode();
//		System.out.println(leetCode.findMedianSortedArrays(new int[]{},new int[]{1}));
//		System.out.println(leetCode.findMedianSortedArrays(new int[]{-1,-2},new int[]{3}));

	}
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] arr = new int[nums1.length + nums2.length];
		System.arraycopy(nums1,0,arr,0,nums1.length);
		System.arraycopy(nums2,0,arr,nums1.length,nums2.length);
		Arrays.sort(arr);
//		Arrays.sort(arr,Collections.reverseOrder());//倒序排列
		boolean isOdd = arr.length%2>0;
		int half = arr.length/2;
		return (arr[half]+arr[isOdd?half:half-1])/2.0;
	}
	public int lengthOfLongestSubstring(String s) {
		if(s==null || s.length()==0){
			return 0;
		}
		//最大子串长度值
		int num = 0;
		//记录当前子串
		StringBuilder sub = new StringBuilder();
		//循环处理每一个字符
		for(int i=0,len=s.length();i<len;i++){
			Character c = s.charAt(i);
			//获取该字符在子串中的位置
			int pos = sub.indexOf(c.toString());
			//拼接当前字符
			sub.append(c);
			//如果大于-1，代表包含该字符
			if(pos>-1) {
				//删除子串中截止到该字符前的所有字符
				sub.delete(0, pos+1);
			}else{
				//-1代表不包含该字符
				//修改最大长度子串值
				num = sub.length()>num?sub.length():num;
			}
			//如果子串加上剩余字符长度之后都已经比最大子串的值要小，
			//代表当前最大子串数字已是最大的了
			if(num>=(sub.length()+len-i-1)){
				break;
			}
		}
		return num;
	}
}
