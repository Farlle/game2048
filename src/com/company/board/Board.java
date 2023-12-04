package com.company.board;

import com.company.key.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Board {

    protected int width;
    protected int height;
    protected LinkedHashMap<Key, Integer> board = new LinkedHashMap<>();//HashMap<Key, Integer>();


    Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBoard(LinkedHashMap<Key, Integer> board) {
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LinkedHashMap<Key, Integer> getBoard() {
        return board;
    }

    public abstract void fillBoard(List<Integer> list);

    public abstract List<Key> availableSpace();

    public abstract void addItem(Key key, Integer value);

    public abstract Key getKey(int i, int j);

    public abstract Integer getValue(Key key);

    public abstract List<Key> getColumn(int j);

    public abstract List<Key> getRow(int i);

    public abstract List<Integer> getColumnValue(int j);
    public abstract List<Integer> getRowsValue(int i);

    public abstract boolean hasValue(Integer value);

    public abstract List<Integer> getValues(List<Key> keys);


}
