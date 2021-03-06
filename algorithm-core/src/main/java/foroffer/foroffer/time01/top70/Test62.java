package foroffer.foroffer.time01.top70;

import org.junit.Test;

import java.util.LinkedList;


/**
 * description:
 *
 * @author liyazhou
 * @create 2017-06-19 11:08
 *
 * 面试题62：序列化二叉树
 *
 * 题目：
 *      请实现两个函数，分别用来序列化和反序列化二叉树。
 *                    1
 *                  /  \
 *                2     3
 *               /     / \
 *              4     5  6
 *
 * 考查点：
 *      1. 二叉树
 *
 * 思路：
 *      1. 序列化二叉树，采用前序遍历的方式
 *            而且要保证任何结点均有两个孩子，若没有孩子或者只有一个孩子，则将缺失的孩子设置为 null
 *      2. 反序列化二叉树，
 *
 */

class BinTreeNode62{
    int value;
    BinTreeNode62 left;
    BinTreeNode62 right;
    public BinTreeNode62(){}
    public BinTreeNode62 (int _value){ value = _value; }
    public void setChildren(BinTreeNode62 _left, BinTreeNode62 _right){
        left = _left;
        right = _right;
    }

}

public class Test62 {

    /**
     * 序列化二叉树
     * @param root 二叉树的根结点
     * @return 序列化后的字符串
     */
    public String serializeToString(BinTreeNode62 root){
        if (root == null) return null;
        String[] sequence = new String[]{""};
        serializeToString(root, sequence);
        return sequence[0].substring(1);
    }

    private void serializeToString(BinTreeNode62 root, String[] sequence){
        if (root == null) {
            sequence[0] += ",#";
            return;
        }
        else sequence[0] += ("," + root.value);

        serializeToString(root.left, sequence);
        serializeToString(root.right, sequence);
    }


    /**
     * 反序列化二叉树
     * @param sequence 序列化后的二叉树
     * @return 二叉树的根结点
     */
    public BinTreeNode62 deserializeToBinTree(String sequence){
        if (sequence == null || sequence.length() == 0) return null;
        String[] values = sequence.split(",");
        return deserializeToBinTree(values, new int[]{0});
    }

    private BinTreeNode62 deserializeToBinTree(String[] values, int[] index) {
        String value = values[index[0]++];
        BinTreeNode62 root = null;
        if (value.matches("[0-9]+")){
            root = new BinTreeNode62();
            root.value = Integer.valueOf(value);

            // System.out.println(root.value);

            root.left = deserializeToBinTree(values, index);
            root.right = deserializeToBinTree(values, index);
        }
        return root;
    }


    @Test
    public void test(){
        BinTreeNode62 node1 = new BinTreeNode62(1);
        BinTreeNode62 node2 = new BinTreeNode62(2);
        BinTreeNode62 node3 = new BinTreeNode62(3);
        BinTreeNode62 node4 = new BinTreeNode62(4);
        BinTreeNode62 node5 = new BinTreeNode62(5);
        BinTreeNode62 node6 = new BinTreeNode62(6);

        node1.setChildren(node2, node3);
        node2.setChildren(node4, null);
        node3.setChildren(node5, node6);

        String sequence = serializeToString(node1);
        System.out.println(sequence);  // 1,2,4,#,#,#,3,5,#,#,6,#,#

        BinTreeNode62 root = deserializeToBinTree(sequence);
        System.out.println("-------------------");
        System.out.println(root.value);
        System.out.println(root.left.value);
        System.out.println(root.left.left.value);
        System.out.println(root.right.value);
        System.out.println(root.right.left.value);
        System.out.println(root.right.right.value);

        LinkedList<BinTreeNode62> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            BinTreeNode62 node = queue.poll();
            System.out.print(node.value + "\t");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

    }

}
