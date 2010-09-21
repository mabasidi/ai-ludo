/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model;

/**
 * This class represents a dice in the game.
 * @author gtrefs
 */
public class Dice {
    private int count;

    public int rollDice() {
        count = (int) Math.ceil(Math.random() * 6);
        return count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
   
}
