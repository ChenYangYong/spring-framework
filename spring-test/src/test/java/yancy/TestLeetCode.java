package yancy;

import java.util.*;

public class TestLeetCode {
	public static void main(String[] args) {
//		String s = "abceabcabcd";
//		System.out.println(s.indexOf('a'));
//		int pos0 = s.indexOf('a');
//		System.out.println(s.substring(0,pos0+1));
//		int pos1 = s.indexOf('a',pos0+1);
//		System.out.println(pos1);
//		System.out.println(s.substring(0,pos1+1));
//		int pos2 = s.indexOf('a',pos1+1);
//		System.out.println(pos2);
//		System.out.println(s.substring(0,pos2+1));
//		int pos3 = s.indexOf('a',pos2+1);
//		System.out.println(pos3);
		TestLeetCode leetCode = new TestLeetCode();
		System.out.println(leetCode.longestPalindrome("babad"));
		System.out.println(leetCode.longestPalindrome("cbbd"));
		System.out.println(leetCode.longestPalindrome("a"));

	}
	public String longestPalindrome(String s) {
		//解题思路：回文为对称字符串，即开始字符必定跟最后一个字符相同，且两个字符反转后相等
		//故对字符串进行迭代，对每个字符找到与他同样字符的位置进行截取，反转后判断是否相等
		//最大回文
		String longest = "";
		//循环处理每一个字符
		for(int i=0,len=s.length();i<len;i++){
			char c = s.charAt(i);
			//第一次对自我进行判断，避免回文中就一个字符
			int pos = i;
			do{
				//截取首尾相同的一段子字符
				String sub = s.substring(i,pos+1);
				//对子字符进行反转
				String reverse = new StringBuffer(sub).reverse().toString();
				//如果反转后相等，且长度大于已有回文，替换老的
				if(sub.equals(reverse) && sub.length()>=longest.length()){
					longest = sub;
				}
				//寻找字符串中下一个相同字符的位置
				pos = s.indexOf(c,pos+1);
			}while (pos!=-1);
			//如果已有回文长度大于剩余字符串长度，代表已找到
			if(longest.length()>len-i){
				break;
			}
		}
		return longest;
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
