package Calculator;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class CalculationTree {
    private HashMap<String, FuncType> funcMap;
    private HashMap<String, BinaryOperatorType> opMap;
    private int depth;
    private int size;
    private BTreeNode root;
    private ArrayList<String> postFix;
    public CalculationTree(ArrayList<String> postFix,
                           HashMap<String, FuncType> funcMap,
                           HashMap<String, BinaryOperatorType> opMap){
        this.size = 0;
        this.depth = 0;
        this.root = null;
        this.postFix = postFix;
        this.funcMap = funcMap;
        this.opMap = opMap;
    }
    public BTreeNode generateTree(){
        Stack<BTreeNode> nodeStack = new Stack<>();
        for (String postfixTerm:postFix) {
            //String is binary operator, pop off two terms if possible
            if (opMap.containsKey(postfixTerm)){
                //initialize a new OperatorNode with edges equal to null. Assume this node will be middle (this will always be the case with the exception
                // of the root Node.)
                BTreeNode OperatorNode = new BTreeNode(postfixTerm,null,new ArrayList<BTreeNode>(),CalcTreeNodeType.MIDDLE);
                //initialize the child Nodes for a binary operator. Check to see if the node Stack is formed correctly.
                //If the nodeStack has less than two elements and we found a binary operator, something went wrong
                if (nodeStack.size() >= 2) {
                    BTreeNode leftChild = nodeStack.pop();
                    BTreeNode rightChild = nodeStack.pop();
                    //set the left and right children of this operatorNode. Also, set the OperatorNode as the parent of its children.
                    //thus all the setparent operations happen here. The parent of the OperatorNode is set when the OperatorNode is popped from the stack.
                    //the operatorNode then becomes a childnode and has it's parent set accordingly. Unless of course this OperatorNode
                    //is the root, then it won't have it's parent set to anything except null.
                    OperatorNode.setLeftChild(leftChild);
                    leftChild.setParent(OperatorNode);
                    OperatorNode.setRightChild(rightChild);
                    rightChild.setParent(OperatorNode);
                    //push the OperatorNode onto the NodeStack so it can be added to the tree properly.
                    nodeStack.push(OperatorNode);
                }
                else{
                    System.out.println("error: found binary operator " + postfixTerm + "but nodeStack size was " + nodeStack.size() + " when it should've been at least 2");
                    throw new EmptyStackException();
                }
            }
            //String is a function. Pop off the required number of terms
            else if (funcMap.containsKey(postfixTerm)){
                //TODO create the B-Tree Impl for functions including variadic ones (Functions where the number of inputs vary).
            }
            //String must be a variable, recognized constant or numerical term
            else{
                //TODO implement the constants with the OperandType ENUM. do the same for variables
                //String is a number. Number and variable nodes will always be leaf nodes.
                nodeStack.push(new BTreeNode(postfixTerm, null,null,CalcTreeNodeType.LEAF));
            }
        }
        //the last Node on the stack will be the root of this tree.
        this.root = nodeStack.pop();
        return this.root;
    }
    public void printTree(){
        int topLength = root.getData().length();
        int botLength = 0;
        String topString = "";
        String botString = "";
        for (BTreeNode child:root.getChildren()) {
            //the extra one gives exactly one space between each child element
            botLength = botLength + 1 + child.getData().length();
            botString = botString.concat(child.getData() + " ");
        }
        if (topLength <= botLength){
            //print normally where center is determined by botLength
            int center = botLength/2 + 1;
            //determine where the top string should start based on center and topLength
            int inLine = center - (topLength/2 + 1);
            //print topString aligned
            topString = topString.concat(spaces(inLine));
            topString = topString.concat(root.getData());
            System.out.println(topString);
            //print botString made earlier
            System.out.println(botString);
        }
    }
    public String spaces(int n) {
        String s = "";
        while (n > 0) {
            s = s.concat(" ");
            n--;
        }
        return s;
    }
}


