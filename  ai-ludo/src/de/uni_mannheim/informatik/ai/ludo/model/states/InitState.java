/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory;
import de.uni_mannheim.informatik.ai.ludo.intent.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.TransitionIntent;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.events.RequestForUserInputEvent;

/**
 * The InitState is the first game state a game is in.
 * It waits for a {@link de.uni_mannheim.informatik.ai.ludo.intent.NewGameIntent NewGameIntent} in order to request initial input from the user.
 * After the initial input is provided this game state advances to the {@link de.uni_mannheim.informatik.ai.ludo.model.states.RoundStartedState RoundStartedState} if a {@link de.uni_mannheim.informatik.ai.ludo.intent.TransitionIntent TransitionIntent} occurs.
 * @author gtrefs
 */
public class InitState extends GameState{

    public InitState(Game game){
        super(game);
    }

    @Override
    public void processIntent(TransitionIntent intent) {
        intent.success();
        game.setState(new RoundStartedState(game));
        IntentFactory.getInstance().createAndDispatchTransitionIntent(game);
    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {

    }

    @Override
    public void processIntent(MoveIntent moveIntent) {

    }

    @Override
    public void processIntent(EndGameIntent intent) {
        
    }

    @Override
    public void processIntent(NewGameIntent intent) {
        intent.success();
        game.fireRequestForUserInputEvent(new RequestForUserInputEvent(game, RequestForUserInputEvent.Type.INIT_DATA));
    }

}
