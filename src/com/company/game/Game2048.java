package com.company.game;

import com.company.board.Board;
import com.company.direction.Direction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;

import static java.util.Arrays.asList;

public class Game2048 implements Game {
    GameHelper helper;// = new GameHelper();
    Board board;
    Random random;// = new Random();

    public Game2048(Board board) {
        this.board = board;
        helper = new GameHelper();
        random = new Random();
    }

    @Override
    public void init() {
        board.fillBoard(asList(
                2, null, 2, 2,
                2, null, null, 2,
                2, 4, null, 4,
                2, 2, null, 2));
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        switch (direction) {
            case BACK -> moveBack();
            case FORWARD -> moveForward();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }

        return false;
    }


    @Override
    public void addItem() {
        var freeBoard = board.availableSpace();
        var randomFreePointKey = freeBoard.get(random.nextInt(freeBoard.size() - 1));
        board.addItem(randomFreePointKey, 2);
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

    private void moveLeft() {
        List<Integer> newBoard = new ArrayList<>();

        for (int i = 0; i < board.getHeight(); i++) {
            List<Integer> mergedRows = helper.moveAndMergeEqual(board.getRowsValue(i));
            newBoard.addAll(mergedRows);
        }
        board.fillBoard(newBoard);
    }

    private void moveRight() {
        List<Integer> newBoard = new ArrayList<>();

        for (int i = 0; i < board.getWidth(); i++) {
            List<Integer> mergedRows = helper.moveAndMergeEqual(board.getRowsValue(i));//4200
            mergedRows = reverseNull(mergedRows);
            newBoard.addAll(mergedRows);
        }
        board.fillBoard(newBoard);
    }

    private void moveForward() {
        List<Integer> newBoard = new ArrayList<>();

        for (int i = 0; i < board.getWidth(); i++) {
            List<Integer> mergedRows = helper.moveAndMergeEqual(board.getColumnValue(i));
            newBoard.addAll(mergedRows);
        }
        newBoard = transposeBoard(newBoard);
        board.fillBoard(newBoard);
    }

    private void moveBack() {
        List<Integer> newBoard = new ArrayList<>();

        for (int i = 0; i < board.getWidth(); i++) {
            List<Integer> mergedRows = helper.moveAndMergeEqual(board.getColumnValue(i));//4200
            mergedRows = reverseNull(mergedRows);
            newBoard.addAll(mergedRows);
        }
        newBoard = transposeBoard(newBoard);
        board.fillBoard(newBoard);
    }


    private List<Integer> reverseNull(List<Integer> list) {
        List<Integer> tmp = new ArrayList<>();

        Collections.reverse(list); //0024
        for (var elem : list) {
            if (elem != null) {
                tmp.add(elem);//24
            }
        }
        Collections.reverse(tmp);//42

        for (var elem : list) {
            if (elem == null) {
                tmp.add(0, elem);//0042
            }
        }
        return tmp;
    }

    private List<Integer> transposeBoard(List<Integer> list){
        List<Integer> transposedList = new ArrayList<>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                transposedList.add(list.get(j * board.getHeight() + i));
            }
        }
        return transposedList;
    }
}
