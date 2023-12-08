package com.company.board;

import com.company.key.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Board <T, K > {

    protected int width;
    protected int height;
    protected LinkedHashMap<K, T> board = new LinkedHashMap<>();//HashMap<Key, Integer>();


    protected Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

//    public void setWidth(int width) {
//        this.width = width;
//    }

//    public void setHeight(int height) {
//        this.height = height;
//    }
//
//    public void setBoard(LinkedHashMap<K, T> board) {
//        this.board = board;
//    }
//
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

//    public LinkedHashMap<Key, Integer> getBoard() {
//        return board;
//    }

    public abstract void fillBoard(List<T> list);

    public abstract List<K> availableSpace();

    public abstract void addItem(K key, T value);

    public abstract K getKey(int i, int j);

    public abstract T getValue(K key);

    public abstract List<K> getColumn(int j);

    public abstract List<K> getRow(int i);

    public abstract List<T> getColumnValue(int j);
    public abstract List<T> getRowsValue(int i);

    public abstract boolean hasValue(T value);

    public abstract List<T> getValues(List<K> keys);


    public abstract void clear();
}
