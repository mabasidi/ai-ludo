/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.TransitionIntent;

/**
 *
 * @author gtrefs
 */
public class InitState extends GameState{

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
