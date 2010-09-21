/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model;

/**
 * The interface Field represents any field on the board.
 * It has four implementing classes:
 * <b>StartField:</b> Field of the "out" area.
 * <b>BeginFiled:</b> Field which is the first field a player puts his pawns on.
 * <b>EndField:</b> Field of the "end" area.
 * <b>IntermediateField:</b> All field which are none of the above are intermediate fields.
 * @author gtrefs
 */
public interface Field {
    public Pawn getPawn();
    public boolean isEmpty();
    public Pawn takePawnFromField();
    public void placePawnOnField(Pawn p);
}
