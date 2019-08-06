/**
 *
 * Created by CYH, 2019-07-26 08:18:33
 *
 * 问题难度级别：士 尉 校 将
 *
 */


/******************************** 第1章：栈和队列 *****************************
 * {@link chapter_1_stackandqueue.Problem_01_GetMinStack}
 *    带有 getMin() 功能的栈
 * {@link chapter_1_stackandqueue.Problem_02_TwoStacksImplementQueue}
 *    由两个栈组成的队列
 * {@link chapter_1_stackandqueue.Problem_03_ReverseStackUsingRecursive}
 *    仅用递归函数和桡操作逆序一个栈
 *    有意思，可以多写写 # TODO
 * {@link chapter_1_stackandqueue.Problem_04_DogCatQueue}
 *    猫狗队列
 *    数据结构的设计比较巧妙 # TODO
 * {@link chapter_1_stackandqueue.Problem_05_StackSortStack}
 *    用一个栈实现另一个栈的排序
 * {@link chapter_1_stackandqueue.Problem_06_HanoiStack}
 *    用栈来解决汉诺塔问题
 * {@link chapter_1_stackandqueue.Problem_07_SlidingWindowMaxArray}
 *    生成窗口最大值数组 # TODO coding
 * {@link chapter_1_stackandqueue.Problem_08_MonotonousStack}
 *    单调栈结构 # TODO 难题，还未想通
 * {@link chapter_1_stackandqueue.Problem_09_MaximalRectangle}
 *    求最大子矩阵的大小 # TODO 难题，还未想通
 * {@link chapter_1_stackandqueue.Problem_10_AllLessNumSubArray}
 *    最大值减去最小值小于等于num的子数组数量 # TODO 难题，还未想通
 * {@link chapter_1_stackandqueue.Problem_11_VisibleMountains}
 *    可见的山峰对数量 # TODO 难题，还未想通
 *******************************************************************************/

/******************************** 第2章：链表问题 *****************************
 * {@link chapter_2_listproblem.Problem_01_PrintCommonPart}
 *    打印两个有序链表的公共部分
 * {@link chapter_2_listproblem.Problem_02_RemoveLastKthNode}
 *    在单链表和双链表中删除倒数第K个节点
 * {@link chapter_2_listproblem.Problem_03_RemoveNodeByRatio}
 *    删除链表的中间节点和a/b处的节点
 * {@link chapter_2_listproblem.Problem_04_ReverseList}
 *    反转单向和双向链表
 *    两个变量即可搞定 # TODO coding
 * {@link chapter_2_listproblem.Problem_05_ReversePartList}
 *    反转部分单向链表
 * {@link chapter_2_listproblem.Problem_06_JosephusProblem}
 *    环形单链表的约瑟夫问题
 * {@link chapter_2_listproblem.Problem_07_IsPalindromeList}
 *    判断一个链表是否为回文结构
 *    方法三中没有使用辅助空间，只用几个变量就能实现且能够还原原来的链表，可以看看 # TODO
 * {@link chapter_2_listproblem.Problem_08_SmallerEqualBigger}
 *    将单向链表按某值划分成左边小、中间相等、右边大的形式
 *    进阶：时间复杂度O(N)、空间复杂度O(1)，也是利用几个变量来实现 # TODO
 * {@link chapter_2_listproblem.Problem_09_CopyListWithRandom}
 *    复制含有随机指针节点的链表
 * {@link chapter_2_listproblem.Problem_10_AddTwoLinkedLists}
 *    两个单链表生成相加链表
 * {@link chapter_2_listproblem.Problem_11_FindFirstIntersectNode}
 *    两个单链表相交的一系列问题
 *    问题难度：将 # TODO
 * {@link chapter_2_listproblem.Problem_12_ConvertEveryKNodesInList}
 *    将单链表的每K个节点之间逆序
 * {@link chapter_2_listproblem.Problem_13_RemoveRepetition}
 *    删除无序单链表中值重复出现的节点
 *    1 2 3 3 4 4 2 1 1 --> 1 2 3 4
 * {@link chapter_2_listproblem.Problem_14_RemoveGivenValue}
 *    在单链表中删除指定值的节点
 * {@link chapter_2_listproblem.Problem_15_BSTtoDoubleLinkedList}
 *    将二叉搜索树转换为双向链表
 * {@link chapter_2_listproblem.Problem_16_ListSelectionSort}
 *    单链表的选择排序
 * {@link chapter_2_listproblem.Problem_17_RemoveNodeWired}
 *    一种怪异的节点删除方式
 *    就是删除一个指定值的节点，需要考虑各种场景
 * {@link chapter_2_listproblem.Problem_18_InsertNumToCircularList}
 *    向有序的环形单链表中插入新节点
 * {@link chapter_2_listproblem.Problem_19_MergeTwoLinkedLists}
 *    合并两个有序的单链表
 * {@link chapter_2_listproblem.Problem_20_RelocateLinkedList}
 *    按照左右半区的方式重新组合单链表
 *******************************************************************************/

/******************************** 第3章：二叉树问题 *****************************
 * {@link chapter_3_binarytreeproblem.Problem_01_PreInPosTraversal}
 *    分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 *    标注：非递归方式会麻烦一点，后期考虑写一次 #TODO
 * {@link chapter_3_binarytreeproblem.Problem_02_PrintEdgeNodes}
 *    打印二叉树的边界节点
 * {@link chapter_3_binarytreeproblem.Problem_03_PrintBinaryTree}
 *    如何较为直观地打印二叉树
 * {@link chapter_3_binarytreeproblem.Problem_04_SerializeAndReconstructTree}
 *    二叉树的序列化和反序列化
 * {@link chapter_3_binarytreeproblem.Problem_05_MorrisTraversal}
 *    遍历二叉树的神级方法
 *    问题难度：将 # TODO
 * {@link chapter_3_binarytreeproblem.Problem_06_LongestPathSum}
 *    在二叉树中找到累加和为指定值的最长路径长度
 *    与此题关系较大：{@link chapter_8_arrayandmatrix.Problem_11_LongestSumSubArrayLength}
 * {@link chapter_3_binarytreeproblem.Problem_07_BiggestSubBSTInTree}
 *    找到二叉树中的最大搜索二叉子树
 *    树形DP问题 # TODO
 * {@link chapter_3_binarytreeproblem.Problem_08_BiggestBSTTopologyInTree}
 *    找到二叉树中符合搜索二叉树条件的最大拓扑结构
 *    问题难度：校
 * {@link chapter_3_binarytreeproblem.Problem_09_PrintBinaryTreeByLevelAndZigZag}
 *    二叉树的按层打印与ZigZag打印
 * {@link chapter_3_binarytreeproblem.Problem_10_RecoverBST}
 *    调整搜索二叉树中两个错误的节点
 * {@link chapter_3_binarytreeproblem.Problem_11_T1ContainsT2Topology}
 *    判断T1树是否包含T2树全部的拓扑结构
 * {@link chapter_3_binarytreeproblem.Problem_12_T1SubtreeEqualsT2}
 *    判断T1树中是否有与T2树拓扑结构完全相同的子树
 * {@link chapter_3_binarytreeproblem.Problem_13_IsBalancedTree}
 *    判断二叉树是否为平衡二叉树
 * {@link chapter_3_binarytreeproblem.Problem_14_PosArrayToBST}
 *    根据后序数组重建搜索二叉树
 * {@link chapter_3_binarytreeproblem.Problem_15_IsBSTAndCBT}
 *    判断一棵二叉树是否为搜索二叉树和完全二叉树
 * {@link chapter_3_binarytreeproblem.Problem_16_SortedArrayToBalancedBST}
 *    通过有序数组生成平衡搜索二叉树
 * {@link chapter_3_binarytreeproblem.Problem_17_DescendantNode}
 *    在二叉树中找到一个节点的后继节点
 * {@link chapter_3_binarytreeproblem.Problem_18_LowestCommonAncestor}
 *    在二叉树中找到两个节点的最近公共祖先
 * {@link chapter_3_binarytreeproblem.Problem_19_TarjanAndDisjointSetsForLCA}
 *    Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询问题
 * {@link chapter_3_binarytreeproblem.Problem_20_MaxDistanceInTree}
 *    二叉树节点之间的最大距离问题
 *    也可以套用树形DP来解决
 * {@link chapter_3_binarytreeproblem.Problem_21_MaxHappy}
 *    派对的最大快乐值
 *    也算是树形DP吧
 * {@link chapter_3_binarytreeproblem.Problem_22_PreAndInArrayToPosArray}
 *    通过先序和中序数组生成后序数组
 * {@link chapter_3_binarytreeproblem.Problem_23_UniqueBST}
 *    统计和生成所有不同的二叉树
 * {@link chapter_3_binarytreeproblem.Problem_24_CompleteTreeNodeNumber}
 *    统计完全二叉树的节点数
 ********************************************************************************/

/******************************** 第4章：递归和动态规划 *******************************
 * {@link chapter_4_recursionanddp.Problem_01_FibonacciProblem}
 *    斐波那契数列问题的递归和动态规划
 * {@link chapter_4_recursionanddp.Problem_02_MinPathSum}
 *    矩阵的最小路径和
 * {@link chapter_4_recursionanddp.Problem_03_CoinsMin}
 *    换钱的最少货币数
 * {@link chapter_4_recursionanddp.Problem_04_RobotWalk}
 *    机器人到达指定位置的方案数
 * {@link chapter_4_recursionanddp.Problem_05_CoinsWay}
 *    换钱的方法数
 * {@link chapter_4_recursionanddp.Problem_06_BurstBalloons}
 *    打气球的最大分数
 * {@link chapter_4_recursionanddp.Problem_07_LIS}
 *    最长递增子序列
 * {@link chapter_4_recursionanddp.Problem_08_EnvelopesProblem}
 *    信封嵌套问题
 * {@link chapter_4_recursionanddp.Problem_09_HanoiProblem}
 *    汉诺塔问题
 * {@link chapter_4_recursionanddp.Problem_10_LCSubsequence}
 *    最长公共子序列问题
 * {@link chapter_4_recursionanddp.Problem_11_LCSubstring}
 *    最长公共子串问题
 * {@link chapter_4_recursionanddp.Problem_12_MostEOR}
 *    子数组异或和为0的最多划分
 * {@link chapter_4_recursionanddp.Problem_13_EditCost}
 *    最小编辑代价
 * {@link chapter_4_recursionanddp.Problem_14_StringCross}
 *    字符串的交错组成
 * {@link chapter_4_recursionanddp.Problem_15_DungeonGame}
 *    龙与地下城游戏问题（骑士与公主问题）
 * {@link chapter_4_recursionanddp.Problem_16_NumberToLetter}
 *    数字字符串转换为字母组合的种数
 * {@link chapter_4_recursionanddp.Problem_17_ExpressionNumber}
 *    表达式得到期望结果的组成种数
 * {@link chapter_4_recursionanddp.Problem_18_CardsInLine}
 *    排成一条线的纸牌博弈问题
 * {@link chapter_4_recursionanddp.Problem_19_JumpGame}
 *    跳跃游戏
 * {@link chapter_4_recursionanddp.Problem_20_LongestConsecutive}
 *    数组中的最长连续序列
 * {@link chapter_4_recursionanddp.Problem_21_NQueens}
 *    N皇后问题
 *******************************************************************************/

/******************************** 第5章：字符串问题 *******************************
 * {@link chapter_5_stringproblem.Problem_01_IsDeformation}
 *    判断两个字符串是否互为变形词
 * {@link chapter_5_stringproblem.Problem_02_IsRotation}
 *    判断两个字符串是否互为旋转词
 * {@link chapter_5_stringproblem.Problem_03_ConvertStringToInteger}
 *    将整数字符串转成整形值
 * {@link chapter_5_stringproblem.Problem_04_ConvertStringToCount}
 *    字符串的统计字符串
 * {@link chapter_5_stringproblem.Problem_05_IsAllUnique}
 *    判断字符数组中是否所有的字符都只出现过一次
 *    要求1：时间复杂度为O(N)
 *    要求2：额外空间为O(1) #TODO
 * {@link chapter_5_stringproblem.Problem_06_FindStringInContainsNullArray}
 *    在有序但含有null的数组中查找字符串
 * {@link chapter_5_stringproblem.Problem_07_ModifyAndReplace}
 *    字符串的调整与替换
 * {@link chapter_5_stringproblem.Problem_08_RotateString}
 *    翻转字符串
 * {@link chapter_5_stringproblem.Problem_09_ShuffleProblem}
 *    完美洗牌问题
 *    问题难度：将 #TODO
 * {@link chapter_5_stringproblem.Problem_10_RemoveDuplicateLettersLessLexi}
 *    删除多余字符得到字典序最小的字符串
 * {@link chapter_5_stringproblem.Problem_11_MinDistance}
 *    数组中两个字符串的最小距离
 * {@link chapter_5_stringproblem.Problem_12_WordMinPaths}
 *    字符串的转换路径问题
 *    经典题目，有意思，回头来写一写 #TODO
 * {@link chapter_5_stringproblem.Problem_13_PalindromeString}
 *    添加最少字符使得字符串整体都是回文字符串
 *    DP问题，难度：校
 * {@link chapter_5_stringproblem.Problem_14_ParenthesesProblem}
 *    括号字符串的有效性和最长有效长度
 * {@link chapter_5_stringproblem.Problem_15_ExpressionCompute}
 *    公式字符串求值
 *    经典问题，要写 #TODO
 * {@link chapter_5_stringproblem.Problem_16_ZeroLeftOneStringNumber}
 *    0左边必有1的二进制字符串数量
 * {@link chapter_5_stringproblem.Problem_17_LowestLexicography}
 *    拼接所有字符串产生字典顺序最小的大写字符串
 * {@link chapter_5_stringproblem.Problem_18_LongestNoRepeatSubstring}
 *    找到字符串的最长无重复子字符串
 * {@link chapter_5_stringproblem.Problem_19_FindNewTypeChar}
 *    找到指定的新类型字符
 * {@link chapter_5_stringproblem.Problem_20_ScrambleString}
 *    旋变字符串问题
 *    问题难度：将
 * {@link chapter_5_stringproblem.Problem_21_MinWindowLength}
 *    最小包含子串的长度
 *    可以写一下 #TODO
 * {@link chapter_5_stringproblem.Problem_22_PalindromeMinCut}
 *    回文最少分隔数
 * {@link chapter_5_stringproblem.Problem_23_RegularExpressionMatch}
 *    字符串匹配问题
 *    正则表达式匹配问题，一定要写 #TODO
 * {@link chapter_5_stringproblem.Problem_24_TrieTree}
 *    字典树的实现
 * {@link chapter_5_stringproblem.Problem_25_MaxEOR}
 *    子数组的最大异或和
 ********************************************************************************/

/******************************** 第6章：大数据和空间限制 *******************************
 *    01. 认识布隆过滤器
 *    02. 只用2G内存在20亿个整数中找到出现次数最多的数
 *    03. 40亿个非负整数中找到未出现的数
 *    04. 找到100亿个URL中重复的URL及搜索词汇的TopK问题
 *    05. 40亿个非负整数中找到出现两次的数和所有数的中位数
 *    06. 一致性Hash算法的基本原理
 *  {@link chapter_6_bigdata.Problem_07_Islands}
 *    07. 岛问题
 *    病毒感染问题（简单来说就是搜索问题），也算是经典问题了 #TODO
 ********************************************************************************/

/******************************** 第7章：位运算 *******************************
 * {@link chapter_7_bitoperation.Problem_01_SwapWithoutTmp}
 *    不用额外变量交换两个整数的值
 * {@link chapter_7_bitoperation.Problem_02_GetMax}
 *    不用做比较判断两个数中较大的数
 * {@link chapter_7_bitoperation.Problem_03_AddMinusMultiDivideByBit}
 *    只用位运算不用算术运算实现整数的加减乘除运算
 * {@link chapter_7_bitoperation.Problem_04_NumberOf1}
 *    整数的二进制表示中有多少个1
 * {@link chapter_7_bitoperation.Problem_05_EvenTimesOddTimes}
 *    在其他数都出现偶数次的数组中找到出现奇数次的数
 * {@link chapter_7_bitoperation.Problem_06_KTimesOneTime}
 *    在其他数都出现K次的数组中找到只出现一次的数
 */

/******************************** 第3章：二叉树问题 *******************************
 * {@link chapter_8_arrayandmatrix.Problem_10_LongestSumSubArrayLengthInPositiveArray}
 *    未排序正整数数组中累加和为给定值的最长子数组长度
 * {@link chapter_8_arrayandmatrix.Problem_11_LongestSumSubArrayLength}
 *    未排序数组中累加和为给定值的最长子数组系列问题
 * {@link chapter_8_arrayandmatrix.Problem_12_LongestLessSumSubArrayLength}
 *    未排序数组中累加和小于等于给定值的最长子数组长度
 ********************************************************************************/