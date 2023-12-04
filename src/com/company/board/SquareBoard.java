package com.company.board;

//import com.company.*;

import com.company.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SquareBoard extends Board {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<Integer> list) {
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (index < list.size()) {
                    board.put(new Key(i, j), list.get(index++));
                } else {
                    board.put(new Key(i, j), null);
                }
            }
        }

    }

    @Override
    public List<Key> availableSpace() {
        List<Key> availableKeys = new ArrayList<>();
        for (var entry : board.entrySet()) {
            if (entry.getValue() == null) {
                availableKeys.add(entry.getKey());
            }
        }
        return availableKeys;
//        ArrayList<Key> emptyList = new ArrayList<>();
//        ArrayList<Key> listKey = new ArrayList<>();
//
//        listKey.addAll(board.keySet());
//        for (Key key : listKey) {
//            System.out.println(key.getI());
//            if (board.get(key)==null) {
//                System.out.println(key);
//                emptyList.add(boa);
//                System.out.println(emptyList);
//            }
//        }

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//            if (list.get(i) == null) {
//                emptyList.add(list.get(i));
//            }
//        }
        // return emptyList;
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        var key = new Key(i, j);
        for (var map : board.entrySet()) {
            if (map.getKey().equals(key)) {
                return map.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> column = new ArrayList<>();
        for (var map : board.entrySet()) {
            if (map.getKey().getJ() == j) {
                column.add(map.getKey());
            }
        }
        return column;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> row = new ArrayList<>();
        for (var map : board.entrySet()) {
            if (map.getKey().getI() == i) {
                row.add(map.getKey());
            }
        }
        return row;
    }

    @Override
    public boolean hasValue(Integer value) {
        return board.containsValue(value);
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        List<Integer> values = new ArrayList<>();
        for (var key : keys) {
            if (board.containsKey(key)) {
                values.add(board.get(key));
            }
        }
        return values;
    }

    @Override
    public List<Integer> getColumnValue(int j) {
        return getValues(getColumn(j));
    }

    @Override
    public List<Integer> getRowsValue(int i) {
        return getValues(getRow(i));
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getValue(getKey(i, j)) == null) {
                    board.append("0 ");
                } else {
                    board.append(getValue(getKey(i, j)) + " ");
                }
            }

            board.append("\n");
        }

        return board.toString();
    }
}
