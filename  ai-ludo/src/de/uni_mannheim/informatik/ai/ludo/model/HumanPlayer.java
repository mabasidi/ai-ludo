/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model;

import de.uni_mannheim.informatik.ai.ludo.intent.Intent;
import de.uni_mannheim.informatik.ai.ludo.model.Game.Color;

/**
 *
 * @author gtrefs
 */
public class HumanPlayer implements Player{

    public void setColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Color getColor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPawns(Pawn[] pawns) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Pawn[] getPawns() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Pawn getPawn(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPath(Path path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Path getPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void movePawn(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rollTheDice(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rejectIntent(Intent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void successIntent(Intent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void enemyPawnThrown(Pawn enemyPawn, Intent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
