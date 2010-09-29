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
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;
import de.uni_mannheim.informatik.ai.ludo.model.statistics.Statistics;

/**
 *
 * @author gtrefs
 */
public class EndState extends GameState{

    public EndState(Game game){
        super(game);
    }

    @Override
    public void processIntent(TransitionIntent intent) {

    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {

    }

    @Override
    public void processIntent(MoveIntent moveIntent) {

    }

    @Override
    public void processIntent(EndGameIntent intent) {
        // Do some clean up.
        game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.START_WRITING_STATISTICS));
        Statistics.getInstance().writeOut();
        game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.END_WRITING_STATISTICS));
        // Tell the game, that all is over
        game.endGame();
    }

    @Override
    public void processIntent(NewGameIntent intent) {
    }

}
