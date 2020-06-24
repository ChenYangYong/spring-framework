package yancy;
import java.math.BigDecimal;
import java.util.*;

public class TestLeetCode {
	public static void main(String[] args) {

		TestLeetCode leetCode = new TestLeetCode();
		System.out.println(leetCode.myAtoi("words and 987"));
		System.out.println(leetCode.myAtoi("4193 with words"));
		System.out.println(leetCode.myAtoi("   -42"));
		System.out.println(leetCode.myAtoi("3.14159"));
		System.out.println(leetCode.myAtoi("+-2"));
		System.out.println(leetCode.myAtoi("+0a2"));
		System.out.println(leetCode.myAtoi("9223372036854775808"));
		System.out.println(leetCode.myAtoi(" b11228552307"));
	}
	public int myAtoi(String str) {
		Long result = 0l;
		if(str!=null && str.length()>0){
			int multiplier = 1;
			boolean hasFirstNum = false;
			for(int i=0,len=str.length();i<len;i++){
				Character c = str.charAt(i);
				boolean isNum = Character.isDigit(c);
				if(!(isNum || c=='-'  || c==' '  || c=='+')){
					break;
				}
				//找到第一个数字符号
				if(!hasFirstNum && (c=='-' || c=='+'  || isNum)){
					hasFirstNum = true;
					if(c=='-'){
						multiplier = -1;
					}
					if(!isNum){
						continue;
					}

				}
				if(!isNum){
					if(hasFirstNum){
						break;
					}else{
						continue;
					}
				}
				result = result*10+Integer.valueOf(c.toString())*multiplier;
				if(result<Integer.MIN_VALUE || result>Integer.MAX_VALUE){
					if(result<Integer.MIN_VALUE){
						result = Integer.MIN_VALUE*1l;
					}
					if(result>Integer.MAX_VALUE){
						result = Integer.MAX_VALUE*1l;
					}
					break;
				}
			}
		}
		return  result.intValue();
	}
	public int reverse(int x) {
		Long rev = 0l;
		//方案一  info: 解答成功: //	执行耗时:2 ms,击败了39.45% 的Java用户 //	内存消耗:37.2 MB,击败了5.33% 的Java用户 (3 minutes ago)
//		while (x!=0){
//			//余数
//			int remainder = x%10;
//			x = x/10;
//			rev = rev*10 + remainder;
//		}
//		if(rev>Integer.MAX_VALUE || rev<Integer.MIN_VALUE){
//			rev = 0l;
//		}
		//方案二
		StringBuilder sb = new StringBuilder();
		sb.append(x);
		sb.reverse();
		rev = Long.parseLong(sb.toString().replaceAll("-",""))*(x>=0?1:-1);
		if(rev>Integer.MAX_VALUE || rev<Integer.MIN_VALUE){
			rev = 0l;
		}
		return rev.intValue();
	}
	public String convert(String s, int numRows) {
		//解题思路:旋转z每L=(numRows+(numRows-2))个字符形成一个轮回
		//各字符位置整除这个轮回后余数Y小于numRows的直接放入对应行中
		//大于numRows的放入L-Y的行中
		if(s==null || s.length()==0 || numRows<2){
			return s;
		}
		List<StringBuilder> numList = new ArrayList<StringBuilder>(numRows);
		for(int i=0;i<numRows;i++){
			numList.add(new StringBuilder());
		}
		//一圈轮回数字行数+行数-首尾两行
		int wheelNum = numRows +(numRows-2);
		for(int j=0,len=s.length();j<len;j++){
			//获取当前字符
			char c = s.charAt(j);
			//字符位置对轮询求余
			int num = j%wheelNum;
			//得到该字符应该放的行
			int putNum = num<numRows?num:wheelNum-num;
			numList.get(putNum).append(c);
		}
		StringBuilder result = new StringBuilder();
		//按行将结果合并
		for(StringBuilder sb:numList){
			result.append(sb.toString());
		}
		return result.toString();
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
		//方案一
//		int[] arr = new int[nums1.length + nums2.length];
//		System.arraycopy(nums1,0,arr,0,nums1.length);
//		System.arraycopy(nums2,0,arr,nums1.length,nums2.length);
//		Arrays.sort(arr);
////		Arrays.sort(arr,Collections.reverseOrder());//倒序排列
//		boolean isOdd = arr.length%2>0;
//		int half = arr.length/2;
//		return (arr[half]+arr[isOdd?half:half-1])/2.0;

		//方案二
		//思路：找中位数的可以确定为找位置k的数，在将两个数组中k/2处值较小的数组中的前k/2个数都去掉
		//此时为寻找第剩下两个数组中位置为k-k/2的数，递归寻找，直到k=1
		return 0;
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
