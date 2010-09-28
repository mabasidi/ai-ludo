/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.TransitionIntent;

/**
 *
 * @author gtrefs
 */
public class EndState extends GameState{

    @Override
    public void processIntent(TransitionIntent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIntent(MoveIntent moveIntent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIntent(EndGameIntent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIntent(NewGameIntent intent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
