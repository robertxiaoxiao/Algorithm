package BasicDStructure.Tree;/*
 * @author:
 * @date:  2019/10/28/028
 * @description:
 */

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.List;

public class DictTree {

    static class KMP{

    }


    static class ACautomtor{




    }


    public TreeNode  root =new TreeNode('\\');
    static class TreeNode{

        public TreeNode(char c){
            ch=c;
            isEndingChar=false;
        }
        char ch;
        TreeNode[]  childrens=new TreeNode[26];
        boolean isEndingChar ;
    }

    public void insert(char[] chs){
        TreeNode temp=root ;
        for(char ch :chs)
        {
            // iterating insert
            if(temp.childrens[ch-'a']==null) {
                temp.childrens[ch - 'a'] = new TreeNode(ch);
            }
            temp=temp.childrens[ch-'a'];
        }
        temp.isEndingChar=true;
    }

    public void printTrie(){

        List<Character>  word=new LinkedList<>();
        printHelper(root,word);
    }

    public void printHelper(TreeNode cur ,List<Character> word){

        if(cur==null)
             return ;

        word.add(cur.ch);
        if(cur.isEndingChar==true)
        {
            for(int i=1;i<word.size();i++)
                System.out.print(word.get(i));
            System.out.println();
        }
        else
        {
            for(TreeNode next :cur.childrens)
                printHelper(next,word);
        }
        word.remove(word.size()-1) ;
    }
    public boolean find(char[] chs){
        TreeNode temp=root ;
        for(char ch : chs)
        {
            if(temp.childrens[ch-'a']==null)
                return false ;
            temp=temp.childrens[ch-'a'];
        }

        return  temp.isEndingChar==true?true:false ;
    }

    public void buildTierTree(String[]  words){
        for(String word :words)
            insert(word.toCharArray());
    }

    public static void main(String[] args) {

        DictTree  dictTree=new DictTree() ;
        String[] words={"hello","her","hi","how","so","see"} ;
        dictTree.buildTierTree(words);

        System.out.println(dictTree.find(words[2].toCharArray()));

        dictTree.printTrie();
    }



}
