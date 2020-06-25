package lzw.leetcode;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LeetCodeTest {
     /**
      * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
          你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
          给定 nums = [2, 7, 11, 15], target = 9
         因为 nums[0] + nums[1] = 2 + 7 = 9
          所以返回 [0, 1]

      * @param nums
      * @param target
      * @return
      */
		public static int[] twoSum(int[] nums, int target) {

			Map<Integer, Integer> map = new HashMap();
			int[] retur = null;
			for (int j = 0; j < nums.length; j++) {
				if (map.containsKey(target - nums[j])) {

					retur = new int[] { map.get(target - nums[j]), j };
				}
				map.put(nums[j], j);
			}

			return retur;
		}
	/**
	 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
		示例 1:
		输入: "abcabcbb"
		输出: 3 
		解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
		输入: "pwwkew"
              输出: 3
               解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
        请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

	 * @param s
	 * @return
	 */
		 public static int lengthOfLongestSubstring(String s) {
			int n = s.length(), ans = 0;
	        Map<Character, Integer> map = new HashMap<>();
	        for (int end = 0, start = 0; end < n; end++) {
	            char alpha = s.charAt(end);
	            if (map.containsKey(alpha)) {
	                start = Math.max(map.get(alpha), start);
	            }
	            ans = Math.max(ans, end - start + 1);
	            map.put(s.charAt(end), end + 1);
	        }
	        return ans;
	    }
			/**
			 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
			示例 1：
			输入: "babad"
			输出: "bab"
			注意: "aba" 也是一个有效答案。
			
			 * @param s
			 * @return
			 */
		 public static String longestPalindrome(String s) {
			 int len = s.length();
		        if (len < 2) {
		            return s;
		        }

		        int maxLen = 1;
		        int begin = 0;
		        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
		        char[] charArray = s.toCharArray();

		        // 枚举所有长度大于 1 的子串 charArray[i..j]
		        for (int i = 0; i < len - 1; i++) {
		            for (int j = i + 1; j < len; j++) {
		                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
		                    maxLen = j - i + 1;
		                    begin = i;
		                }
		            }
		        }
		        return s.substring(begin, begin + maxLen);

		    }
		

		    /**
		     * 验证子串 s[left..right] 是否为回文串
		     */
		    private static boolean validPalindromic(char[] charArray, int left, int right) {
		        while (left < right) {
		            if (charArray[left] != charArray[right]) {
		                return false;
		            }
		            left++;
		            right--;
		        }
		        return true;
		    }
            /**
              * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
			      比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
				L   C   I   R
				E T O E S I I G
				E   D   H   N
				之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
				请你实现这个将字符串进行指定行数变换的函数：
				string convert(string s, int numRows);
				示例 1:
				输入: s = "LEETCODEISHIRING", numRows = 3
				输出: "LCIRETOESIIGEDHN"
             * @param s
            * @param numRows
            * @return
            */
		    public String convert(String s, int numRows) {

		        if (numRows == 1) return s;

		        List<StringBuilder> rows = new ArrayList<>();
		        for (int i = 0; i < Math.min(numRows, s.length()); i++)
		            rows.add(new StringBuilder());

		        int curRow = 0;
		        boolean goingDown = false;

		        for (char c : s.toCharArray()) {
		            rows.get(curRow).append(c);
		            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
		            curRow += goingDown ? 1 : -1;
		        }

		        StringBuilder ret = new StringBuilder();
		        for (StringBuilder row : rows) ret.append(row);
		        return ret.toString();
		    }
		    /**
		     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
		     * @param x
		     * @return
		     */
		    public static int reverse(int x) {
		        int res = 0;
		        while(x!=0) {
		            //每次取末尾数字
		            int tmp = x%10;
		            //判断是否 大于 最大32位整数
		            if (res>214748364 || (res==214748364 && tmp>7)) {
		                return 0;
		            }
		            //判断是否 小于 最小32位整数
		            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
		                return 0;
		            }
		            res = res*10 + tmp;
		            x /= 10;
		        }
		        return res;
		    }
           /**
			* 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
			首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
			如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
			假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
			该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
			注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
			在任何情况下，若函数不能进行有效的转换时，请返回 0 。
			
			提示：
			本题中的空白字符只包括空格字符 ' ' 。
			假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
			 示例 1:
			输入: "42"
			输出: 42
			示例 2:
			输入: "   -42"
			输出: -42
			解释: 第一个非空白字符为 '-', 它是一个负号。
			     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
			示例 3:
			
			输入: "4193 with words"
			输出: 4193
			解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

            * @param str
            * @return
            */
			public int myAtoi(String str) {
				char[] chars = str.toCharArray();
				int n = chars.length;
				int idx = 0;
				while (idx < n && chars[idx] == ' ') {
					// 去掉前导空格
					idx++;
				}
				if (idx == n) {
					// 去掉前导空格以后到了末尾了
					return 0;
				}
				boolean negative = false;
				if (chars[idx] == '-') {
					// 遇到负号
					negative = true;
					idx++;
				} else if (chars[idx] == '+') {
					// 遇到正号
					idx++;
				} else if (!Character.isDigit(chars[idx])) {
					// 其他符号
					return 0;
				}
				int ans = 0;
				while (idx < n && Character.isDigit(chars[idx])) {
					int digit = chars[idx] - '0';
					if (ans > (Integer.MAX_VALUE - digit) / 10) {
						// 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
						// 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
						return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
					}
					ans = ans * 10 + digit;
					idx++;
				}
				return negative ? -ans : ans;
			}

	public static void main(String[] args) {
		int [] lt= {1,2,5,4};
          System.out.println(Arrays.toString(twoSum(lt, 6)));
          System.out.println(lengthOfLongestSubstring("qwertyuioplsdfdfsdsfsfadsqwvbbnmmlkjfddd")+""+reverse(126));
          
	}

}
