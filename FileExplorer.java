/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package sdt;

/**
 *
 * @author suresh
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package javaide;

import java.io.File;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author AL
 */
public class FileExplorer {
    File rootPath;
    public FileExplorer(File path){
        rootPath = path;
    }
    public DefaultMutableTreeNode processRootPath(){  
        //System.out.println("processRootPath");
        //converting the path to a file to traverse and get its contents.
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootPath);
        File directory = rootPath;
        File[] children = directory.listFiles();
        
        int count = children.length;
        //System.out.println("processRootPath: #Children: " + children.length);
        for(int i=0;i<count;i++){
            //File file = new File(children[i]);
            File file = children[i];
            //System.out.println(children[i]);
            //System.out.println(file.isDirectory());
            if(file.isDirectory()&& !directory.isHidden()){
                //System.out.println("processRootPath: isDirectory" );
                //create a new node
                DefaultMutableTreeNode childDir = new DefaultMutableTreeNode(file.getName());
                //add to the tree
                root.add(childDir);
                //get list of children in the dir and process them
                //String[] dirChildren = file.list();
                File[] dirChildren = file.listFiles();
                if(dirChildren != null){
                    //System.out.println("processRootPath: #Children: " + dirChildren.length);
                    for(int j=0;j<dirChildren.length;j++){
                        childDir = processDirectory(dirChildren[j],childDir);
                    }
                }
            }
            else{ 
                if(file.isFile()){
                    System.out.println("processRootPath: isFile");
                    //do nothing
                }
            }
        }
        
        return root;
    }

    private DefaultMutableTreeNode processDirectory(File dir, DefaultMutableTreeNode parentNode) {
        //System.out.println("processDirectory()");
        //File directory = new File(dir);
        File directory = dir;
        //System.out.println(dir);
        if(directory.isDirectory()&& !directory.isHidden()){
            //System.out.println("processDirectory: isDirectory");
            //add to the tree
            DefaultMutableTreeNode childDir = new DefaultMutableTreeNode(directory.getName());
            //add to the parentNode
            
            parentNode.add(childDir);
            //process children
            //String[] children = directory.list();
            File[] children = directory.listFiles();
            //System.out.println("processDirectory: #Children: " + children.length);
            if(children != null){
                for(int i=0;i<children.length;i++){
                    //File file = new File(children[i]);
                    File file = children[i];
                    childDir = processDirectory(file, childDir);
                }   
            }
        }
        else if(directory.isFile() && (directory.getName().contains(".java")||directory.getName().contains(".config")||directory.getName().contains(".class"))){
            //System.out.println("processDirectory: isFile");
            //add file to the tree
            parentNode.add(new DefaultMutableTreeNode (directory.getName()));
        }
        
        return parentNode;
    }
}
