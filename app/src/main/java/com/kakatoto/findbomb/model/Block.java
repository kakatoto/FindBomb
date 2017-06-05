package com.kakatoto.findbomb.model;

/**
 * Created by ohyowan on 2017. 6. 3..
 */

public class Block {
    int center;
    int left;
    int right;
    int up;
    int down;
    int leftUpConner;
    int leftDownConner;
    int rightDownConner;
    int rightUpConner;
    Boolean isBomb ;
    int mineCount;

    public Block() {
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getLeftUpConner() {
        return leftUpConner;
    }

    public void setLeftUpConner(int leftUpConner) {
        this.leftUpConner = leftUpConner;
    }

    public int getLeftDownConner() {
        return leftDownConner;
    }

    public void setLeftDownConner(int leftDownConner) {
        this.leftDownConner = leftDownConner;
    }

    public int getRightDownConner() {
        return rightDownConner;
    }

    public void setRightDownConner(int rightDownConner) {
        this.rightDownConner = rightDownConner;
    }

    public int getRightUpConner() {
        return rightUpConner;
    }

    public void setRightUpConner(int rightUpConner) {
        this.rightUpConner = rightUpConner;
    }

    public Boolean getBomb() {
        return isBomb;
    }

    public void setBomb(Boolean bomb) {
        isBomb = bomb;
    }


}
