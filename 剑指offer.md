剑指offer 按顺序的刷

## JZ3 数组中重复的数字

在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组[2,3,1,0,2,5,3]，那么对应的输出是2或者3。存在不合法的输入的话输出-1

### 思路
1. 遍历数组，将每一个数字安排回对应的序列序号的位置。若出现多个数字安排到同一个位置，就是重复了。时空复杂度是O(N)
2. 其实最直接的应该是建一个hash表，判断

### code
``` java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param numbers int整型一维数组 
     * @return int整型
     */
    public int duplicate (int[] numbers) {
        // write code here
        int i = 0;
        while (i < numbers.length) {
            int m = numbers[i];
            if (i != m) {
                // 有两种情况，a[m] ? m
                if (numbers[m] != m) {
                    // 令a[m] = m
                    numbers[i] = numbers[m];
                    numbers[m] = m;
                } else {
                    return m;
                } 
            } else {
                    i++;
                }
            
        }
        return -1;
    }
}
```

```java
class Solution {
public:
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param numbers int整型vector 
     * @return int整型
     */
    int duplicate(vector<int>& numbers) {
        // write code here
        unordered_set<int> u_set;
        for(int i=0;i<numbers.size();++i){
          if(u_set.count(numbers[i])){//hash表存在
              return numbers[i];
          }else{//不存在
              u_set.insert(numbers[i]);//将该数字加入hash表中
          }
        }
        return -1;
    }
};
```

## JZ4 二维数组中的查找

在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
[
[1,2,8,9],
[2,4,9,12],
[4,7,10,13],
[6,8,11,15]
]
给定 target = 7，返回 true。

给定 target = 3，返回 false。

数据范围：矩阵的长宽满足 0 \le n,m \le 5000≤n,m≤500 ， 矩阵中的值满足 0 \le val \le 10^90≤val≤10 
9
 
进阶：空间复杂度 O(1)O(1) ，时间复杂度 O(n+m)O(n+m)

### 思路
1. 因为数组是有序的，对于一个target，每次比较每行最后一个，如果比这个大，就在下一行。如果比这个小，就有可能在这一行。时间复杂度O(N+M)，最多就行列各走完一次。

### code
``` java
public class Solution {
    public boolean Find(int target, int [][] array) {
        int length_1 = array.length;
        int length_2 = array[0].length;
        int i = 0, j = length_2 - 1;
        while ((i < length_1) && (j >= 0)) {
            if (target == array[i][j]) return true;
            else if (target < array[i][j]) j--;
            else i++;
        }
        return false;

    }
}
```

## JZ5 替换空格

请实现一个函数，将一个字符串s中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

数据范围:。保证字符串中的字符为大写英文字母、小写英文字母和空格中的一种。
进阶：时间复杂度  ,空间复杂度

### 思路
这题主要是需要熟悉java string的处理方法。本质上就是遍历一遍，时间复杂度O(N).
1. char array和string之间的转换，还有string.charAt()方法。
2. String builder 类的调用。类似python list，有append和toString()两个方法。
### code
```java
import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串 
     * @return string字符串
     */
    public String replaceSpace (String s) {
        // write code here
        int length = s.length();
        char[] array = new char[length * 3];
        int index = 0;
        for (int i=0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                array[index++] = '%';
                array[index++] = '2';
                array[index++] = '0';
            } else {
                array[index++] = s.charAt(i);
            }
        }
        return new String(array, 0, index);

    }
}
```

```java
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                stringBuilder.append("%20");
            else
                stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }
```