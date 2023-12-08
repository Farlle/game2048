package com.company.game;

import com.company.board.Board;
import com.company.board.SquareBoard;
import com.company.direction.Direction;
import com.company.key.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private final Board<Integer, Key> board = new SquareBoard<>(GAME_SIZE);

    GameHelper helper = new GameHelper();
    Random random = new Random();


    public Game2048(Board board) {
        init();
        helper = new GameHelper();
        random = new Random();
    }

    public Game2048() {

    }

    @Override
    public void init() {
        board.clear();
        for (int i = 0; i < 2; i++) {
            addItem();
        }
    }

    @Override
    public boolean canMove() {
        return board.availableSpace().isEmpty() ? false : haveEqualNeighbours();
    }

    @Override
    public boolean move(Direction direction) {
        int countMove = 0;
        switch (direction) {
            case BACK -> moveBack();
            case FORWARD -> moveForward();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            default -> {
                return false;
            }
        }
        addItem();
        return true;
    }


    @Override
    public void addItem() {
        var freeBoard = board.availableSpace();
        var randomFreePointKey = freeBoard.get(random.nextInt(freeBoard.size() - 1));
        var randomInitValue = random.nextInt(10) + 1;
        if (randomInitValue == 10) {
            board.addItem(randomFreePointKey, 4);
        } else {
            board.addItem(randomFreePointKey, 2);
        }
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
            List<Integer> mergedColumns = helper.moveAndMergeEqual(board.getColumnValue(i));
            newBoard.addAll(mergedColumns);
        }
        newBoard = transposeBoard(newBoard);
        board.fillBoard(newBoard);
    }

    private void moveBack() {
        List<Integer> newBoard = new ArrayList<>();

        for (int i = 0; i < board.getWidth(); i++) {
            List<Integer> mergedColumns = helper.moveAndMergeEqual(board.getColumnValue(i));
            mergedColumns = reverseNull(mergedColumns);
            newBoard.addAll(mergedColumns);
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

    private List<Integer> transposeBoard(List<Integer> list) {
        List<Integer> transposedList = new ArrayList<>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                transposedList.add(list.get(j * board.getHeight() + i));
            }
        }
        return transposedList;
    }

    private boolean haveEqualNeighbours() {
        for (int i = 0; i < board.getHeight() - 1; i++) {
            for (int j = 0; j < board.getWidth() - 1; j++) {
                if (board.getValue(new Key(i, j)) == board.getValue(new Key(i, j + 1)) ||
                        board.getValue(new Key(i, j)) == board.getValue(new Key(i + 1, j))) {
                    return true;
                }
            }
        }
        return false;
    }

}
