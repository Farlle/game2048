package com.company.game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {

    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        result = mergeSame(moveToBeginning(list));
        int count = result.size() + 1;

        while (count <= list.size()) {
            result.add(null);
            count++;
        }
        return result;
    }

    public List<Integer> mergeSame(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                list.set(i, list.get(i) * 2);
                list.remove(i + 1);
                           }
        }
        return list;
    }

    public List<Integer> moveToBeginning(List<Integer> list) {
        int i = 0;
        List<Integer> tmpList = new ArrayList<>();
        for (var elem : list) {
            if (elem != null) {
                tmpList.add(elem);
            }
        }
        return tmpList;
    }
}
