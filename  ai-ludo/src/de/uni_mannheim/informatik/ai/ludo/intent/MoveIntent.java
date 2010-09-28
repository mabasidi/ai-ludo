/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.intent;

import de.uni_mannheim.informatik.ai.ludo.model.Pawn;

/**
 *
 * @author gtrefs
 */
public interface MoveIntent extends PlayerIntent{
        public Pawn getPawnToMove();
        public void setPawnToMove(Pawn pawnToMove);
}
