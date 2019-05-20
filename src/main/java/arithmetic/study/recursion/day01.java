package arithmetic.study.recursion;

/***
 * 递归
 */
public class day01 {

    /***
     * 如何用三行代码找到最终推荐人？
     * 给定一个用户id，如何查找到这个用户的"最终推荐人"？
     *
     * public String findId(String userId){
     *     String parentUserId = select(userId);  select()方法代表数据库查询上层推荐人
     *     if(parentUserId == null)
     *         return userId;
     *     return findId(parentId);
     * }
     */

    /***
     * 递归需要满足三个条件
     * 1.一个问题的解可以分解为几个子问题的解。
     * 2.这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样。
     * 3.存在递归终止条件。
     */
}
