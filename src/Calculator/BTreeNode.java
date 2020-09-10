package Calculator;

import java.util.ArrayList;

public class BTreeNode {
    private String data;
    private int height;
    private BTreeNode parent;
    private ArrayList<BTreeNode> children;

    private CalcTreeNodeType nodeType;
    public BTreeNode(String data, BTreeNode parent, ArrayList<BTreeNode> children, CalcTreeNodeType nodeType){
        this.data = data;
        this.children = children;
        this.parent = parent;
        this.nodeType = nodeType;
    }

    public String getData(){
        if (this.data.equals("")){
            return null;
        }
        return this.data;
    }
    public BTreeNode getParent(){ return this.parent; }
    public BTreeNode getLeftChild() { return children.get(0); }
    public BTreeNode getRightChild() { return children.get(-1); }
    public ArrayList<BTreeNode> getChildren(){ return children; }
    public CalcTreeNodeType getNodeType() { return nodeType; }
    public int getHeight() { return height; }

    public void setData(String data){
        this.data = data;
    }
    public void setParent(BTreeNode parent){ this.parent = parent; }
    public void setLeftChild(BTreeNode leftChild){ this.children.add(0, leftChild); }
    public void setRightChild(BTreeNode rightChild) { this.children.add(0, rightChild); }
    public void setAnyChild(BTreeNode anyChild, int index) { this.children.set(index, anyChild); }
    public void setHeight(int height) { this.height = height; }
    public void setNodeType(CalcTreeNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public boolean equals(Object o){
        boolean a = false;
        boolean b = false;
        int childArraySize = 0;
        ArrayList<BTreeNode> childArray = null;
        //check if object is BtreeNode and has same parent
        if (o instanceof BTreeNode && ((BTreeNode) o).parent == this.parent) {
            a = true;
            childArray = ((BTreeNode) o).children;
            childArraySize = childArray.size();
        }
        //check if object has same children
        for (int i = 0; i < childArraySize; i++) {
            b = childArray.get(i) == this.children.get(i);
        }
        return (a && b);
    }

    public String toString(){
        String s = "";
        for (BTreeNode child:this.children) {
            s = s.concat(child.getData() + " ");
        }
        return "CalcTreeNode(Data = " + this.data
                + ", Parent = " + this.parent.getData()
                + ", Children = [ "  + s + "] )";
    }
}
