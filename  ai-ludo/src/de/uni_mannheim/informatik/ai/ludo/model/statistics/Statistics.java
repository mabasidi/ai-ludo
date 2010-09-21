/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.statistics;

import de.uni_mannheim.informatik.ai.ludo.intent.Intent;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * The statistics class is used to store statistics specific to the game.
 * This enables easy evaluation of results, so whether a specific AI is better
 * than another.
 * @author gtrefs
 */
public class Statistics {

    private static Statistics INSTANCE = new Statistics();

    public static Statistics getInstance(){
        return INSTANCE;
    }

    private Statistics(){
        rounds = new LinkedList<Round>();
    }

    private Queue<Round> rounds;

    public void writeStatistics(){
        
    }

    public void successFullIntent(Intent intent){

    }

    public void newGame(){
    }

    public void gameWonByPlayer(Player winner){
        
    }

    private class Round{
        private Player winnerClass;
        private Queue<SourceClassAndIntent<?>> playersWithIntents;

        public Round(){
            playersWithIntents = new LinkedList<SourceClassAndIntent<?>>();
        }

        public Player getWinner() {
            return winnerClass;
        }

        public void setWinner(Player winner) {
            this.winnerClass = winner;
        }

        public <T> void addSourceWithIntent(T source, Intent intent){
            SourceClassAndIntent<T> sourceClassAndIntent = new SourceClassAndIntent<T>(source,intent);

        }

        private class SourceClassAndIntent<T>{
            private T source;
            private Intent intent;

            public SourceClassAndIntent(T source, Intent intent){
                this.source = source;
                this.intent = intent;
            }

            public Intent getIntent() {
                return intent;
            }

            public void setIntent(Intent intent) {
                this.intent = intent;
            }

            public T getSource() {
                return source;
            }

            public <V> V getSourceAsType(Class<V> clazz){
                return clazz.cast(source);
            }

            public void setSource(T source) {
                this.source = source;
            }

        }
    }

}
