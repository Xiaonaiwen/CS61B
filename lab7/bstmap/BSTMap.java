package bstmap;

import ucb.util.mailbox.Mailbox;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNode<K,V> root;

    private class BSTNode<K, V>{
        private K currentKey;
        private V currentValue;
        private BSTNode leftNode;
        private BSTNode rightNode;
        private BSTNode(K key, V value){
            currentKey = key;
            currentValue = value;
            leftNode = null;
            rightNode = null;
        }
    }

    public BSTMap(){
        size = 0;
        root = null;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode<K, V> checkNode = root;
        while (checkNode != null){
            int compare = key.compareTo(checkNode.currentKey);
            if (compare < 0){
                checkNode = checkNode.leftNode;
            }
            else if (compare == 0){
                return true;
            }
            else{
                checkNode = checkNode.rightNode;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode<K, V> checkNode = root;
        while (checkNode != null){
            int compare = key.compareTo(checkNode.currentKey);
            if (compare < 0){
                checkNode = checkNode.leftNode;
            }
            else if (compare == 0){
                return checkNode.currentValue;
            }
            else{
                checkNode = checkNode.rightNode;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (size == 0){
            root = new BSTNode<>(key, value);
        }
        else{
            BSTNode<K,V> parentCheckNode = null;
            BSTNode<K,V> checkNode = root;
            boolean smallFlag = false;
            while (checkNode != null){
                K checkKey = checkNode.currentKey;
                if (key.compareTo(checkKey) < 0){
                    parentCheckNode = checkNode;
                    checkNode = checkNode.leftNode;
                    smallFlag = true;
                }
                else if (key.compareTo(checkKey) > 0){
                    parentCheckNode = checkNode;
                    checkNode = checkNode.rightNode;
                    smallFlag = false;
                }
                else{
                    return;
                }
            }
            if (smallFlag){
                parentCheckNode.leftNode = new BSTNode(key, value);
            }
            else{
                parentCheckNode.rightNode = new BSTNode(key, value);
            }
        }
        size += 1;
    }

    public void printInOrder(){
        BSTNode<K, V> currentNode = root;
        BSTNode<K, V> leftNode;
        BSTNode<K, V> rightNode;
        BSTMap<K, V> leftMap = new BSTMap<>();
        BSTMap<K, V> rightMap = new BSTMap<>();
        if (root == null){
            return;
        }
        else{
            leftNode = currentNode.leftNode;
            rightNode = currentNode.rightNode;
            leftMap.root = leftNode;
            rightMap.root = rightNode;
            leftMap.printInOrder();
            System.out.println(currentNode.currentKey);
            rightMap.printInOrder();
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
