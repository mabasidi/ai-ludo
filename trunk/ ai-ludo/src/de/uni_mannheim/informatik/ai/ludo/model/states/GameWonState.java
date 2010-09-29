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
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;
import de.uni_mannheim.informatik.ai.ludo.model.preferences.Preferences;
import de.uni_mannheim.informatik.ai.ludo.model.statistics.Statistics;

/**
 *
 * @author gtrefs
 */
public class GameWonState extends GameState {

    public GameWonState(Game game) {
        super(game);
    }

    @Override
    public void processIntent(TransitionIntent intent) {
        intent.success();
        // Tell the statistics, that the current player has won
        Statistics.getInstance().gameWonByPlayer(intent, game.getCurrentPlayer());
        // Tell the game, that this game has been played
        game.increaseGamesPlayed();
        // When the game is in simulation mode, we proceed to the next game or end (MAX_GAMES = currentGame)
        int gamesPlayed = game.getGamesPlayed();
        int maxGames = Preferences.getInstance().getMaxRound();
        // 1. simulation
        if (Preferences.getInstance().isInSimulationMode()) {
            // 1.1 End this game
            if (gamesPlayed >= maxGames) {
                game.setState(new EndState(game));
                game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.END_GAME));
                IntentFactory.getInstance().createAndDispatchEndGameIntent(game);
                return;
            }
            // 1.2 new game
            // Reset the game
            game.reset();
            game.setState(new RoundStartedState(game));
            game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.NEW_GAME));
            IntentFactory.getInstance().createAndDispatchTransitionIntent(game);
            return;
        }
        // 2. no simulation
        game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.GAME_WON));
    }

    @Override
    public void processIntent(RollDiceIntent roleDiceIntent) {
    }

    @Override
    public void processIntent(MoveIntent moveIntent) {
    }

    @Override
    public void processIntent(EndGameIntent intent) {
        intent.success();
        game.setState(new EndState(game));
        IntentFactory.getInstance().createAndDispatchEndGameIntent(game);
    }

    @Override
    public void processIntent(NewGameIntent intent) {
        intent.success();
        game.reset();
        game.setState(new InitState(game));
        IntentFactory.getInstance().createAndDispatchNewGameIntent(game);
    }
}
