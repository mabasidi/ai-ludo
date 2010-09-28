/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.intent;

/**
 *
 * @author gtrefs
 */
public interface RollDiceIntent extends PlayerIntent{
        public int getDiceCount();
        public void setDiceCount(int diceCount);
}
