/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.intent;

import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Pawn;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.statistics.Statistics;

/**
 * This class creates concrete Intents which are dispatchable by the Intent-
 * Dispatcher.
 * @author gtrefs
 */
public class IntentFactory {

    private static IntentFactory INSTANCE = new IntentFactory();

    public static IntentFactory getInstance() {
        return INSTANCE;
    }

    private IntentFactory() {
    }

    public Intent createMoveIntent(Game game, Pawn pawnToMove) {
        return new MoveIntentImpl(game, pawnToMove);
    }

    public Intent createRollDiceIntent(Game game, Player player) {
        return new RollDiceIntentImpl(game, player);
    }

    public Intent createTransitionIntent(Game game) {
        return new TransitionIntentImpl(game);
    }

    public void createAndDispatchTransitionIntent(Game game) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent transitionIntent = createTransitionIntent(game);
        dispatcher.dispatchIntent(transitionIntent);
    }

    public void createAndDispatchMoveIntent(Game game, Pawn pawn) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent moveIntent = createMoveIntent(game, pawn);
        dispatcher.dispatchIntent(moveIntent);
    }

    public void createAndDispatchRollDiceIntent(Game game, Player player) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent rollIntent = createRollDiceIntent(game, player);
        dispatcher.dispatchIntent(rollIntent);
    }

    public Intent createNewGameIntent(Game game) {
        return new NewGameIntentImpl(game);
    }

    public void createAndDispatchNewGameIntent(Game game) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent newGameIntent = createNewGameIntent(game);
        dispatcher.dispatchIntent(newGameIntent);
    }

    public Intent createEndGameIntent(Game game) {
        return new EndGameIntentImpl(game);
    }

    public void createAndDispatchEndGameIntent(Game game) {
        IntentDispatcher dispatcher = IntentDispatcher.getInstance();
        Intent endGameIntent = createEndGameIntent(game);
        dispatcher.dispatchIntent(endGameIntent);
    }

    private class MoveIntentImpl implements MoveIntent {

        private Game game;
        private Pawn pawnToMove;
        private Player player;

        private MoveIntentImpl(Game game, Pawn pawnToMove) {
            this.game = game;
            this.pawnToMove = pawnToMove;
            this.player = pawnToMove.getOwner();
        }

        public void takeVisitor(IntentVisitor visitor) {
            visitor.processIntent(this);
        }

        public Pawn getPawnToMove() {
            return pawnToMove;
        }

        public void setPawnToMove(Pawn pawnToMove) {
            this.pawnToMove = pawnToMove;
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            player.rejectIntent(this);
            Statistics.getInstance().rejectedIntent(this);
        }

        public void success() {
            player.successIntent(this);
            Statistics.getInstance().successfullIntent(this);
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }
    }

    private class RollDiceIntentImpl implements RollDiceIntent {

        private Game game;
        private Player player;
        // dice count
        private int diceCount;

        private RollDiceIntentImpl(Game game, Player player) {
            this.game = game;
            this.player = player;
        }

        public void takeVisitor(IntentVisitor visitor) {
            visitor.processIntent(this);
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            player.rejectIntent(this);
            Statistics.getInstance().rejectedIntent(this);
        }

        public void success() {
            player.successIntent(this);
            Statistics.getInstance().successfullIntent(this);
        }

        public int getDiceCount() {
            return diceCount;
        }

        public void setDiceCount(int diceCount){
            this.diceCount = diceCount;
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }
    }

    private class TransitionIntentImpl implements TransitionIntent {

        private Game game;

        private TransitionIntentImpl(Game game) {
            this.game = game;
        }

        public void takeVisitor(IntentVisitor visitor) {
            visitor.processIntent(this);
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            Statistics.getInstance().rejectedIntent(this);
        }

        public void success() {
            Statistics.getInstance().successfullIntent(this);
        }
    }

    private class NewGameIntentImpl implements NewGameIntent {

        private Game game;

        private NewGameIntentImpl(Game game) {
            this.game = game;
        }

        public void takeVisitor(IntentVisitor visitor) {
            visitor.processIntent(this);
        }

        public void execute() {
           game.handleIntent(this);
        }

        public void reject() {
            Statistics.getInstance().rejectedIntent(this);
        }

        public void success() {
            Statistics.getInstance().successfullIntent(this);
        }
    }

    private class EndGameIntentImpl implements EndGameIntent {

        private Game game;

        public EndGameIntentImpl(Game game) {
            this.game = game;
        }

        public void takeVisitor(IntentVisitor visitor) {
            visitor.processIntent(this);
        }

        public void execute() {
            game.handleIntent(this);
        }

        public void reject() {
            Statistics.getInstance().rejectedIntent(this);
        }

        public void success() {
            Statistics.getInstance().successfullIntent(this);
        }
    }
}
