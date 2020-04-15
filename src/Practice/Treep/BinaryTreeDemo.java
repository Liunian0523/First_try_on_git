package Practice.Treep;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree=new BinaryTree();
        HeroNode root=new HeroNode(1,"宋");
        HeroNode node2=new HeroNode(2,"吴");
        HeroNode node3=new HeroNode(3,"陆");
        HeroNode node4=new HeroNode(4,"林");
        HeroNode node5=new HeroNode(5,"关");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序查询");
        HeroNode resNode=binaryTree.preOrderSearch(5);
        if(resNode!=null){
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(),resNode.getName());
        }else{
            System.out.printf("没有找到 no=%d 的姓", 5);
        }

        System.out.println("中序查询");
        HeroNode resNode1=binaryTree.infixOrderSearch(5);
        if(resNode!=null){
            System.out.printf("找到了，信息为 no=%d name=%s", resNode1.getNo(),resNode1.getName());
        }else{
            System.out.printf("没有找到 no=%d 的姓", 5);
        }

        System.out.println("后序查询");
        HeroNode resNode2=binaryTree.postOrderSearch(5);
        if(resNode!=null){
            System.out.printf("找到了，信息为 no=%d name=%s", resNode2.getNo(),resNode2.getName());
        }else{
            System.out.printf("没有找到 no=%d 的姓", 5);
        }



    }
}
//定义一个binary tree
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root=root;
    }
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return this.root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}
//创建heroNode节点

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    public HeroNode(int no, String name){
        this.no=no;
        this.name=name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历
    public void preOrder(){
        System.out.println(this);
        //递归向左
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右
        if(this.right!=null){
            this.right.preOrder();
        }

    }
    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }

    }
    //后序遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);

    }

    /**
     *
     * @param no search no
     * @return if find return node, if not return null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序");
        if(this.no==no){
            return this;
        }
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if(resNode!=null){//proof answer is on left branch
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode infixOrderSearch(int no){
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("进入中序");
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode postOrderSearch(int no){
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("进入后序");
        if(this.no==no){
            return this;
        }
        return resNode;
    }



}