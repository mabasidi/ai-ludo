/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.intent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The IntentDispatcher executes the given intents. It corresponds to the
 * executor of the command pattern. It runs asynchrounusly.
 * @author gtrefs
 */
public class IntentDispatcher {

    // Singleton
    private static IntentDispatcher INSTANCE = new IntentDispatcher();

    private Thread thread;

    private ConcurrentLinkedQueue<Intent> queue;

    private IntentDispatcher(){
        queue = new ConcurrentLinkedQueue<Intent>();
    }

    public void start(){
        if(thread != null){
            return;
        }
        thread = new Thread(new QueueRunner());
        thread.start();
    }

    public void stop(){
        if(thread != null)
            thread = null;
    }

    public static IntentDispatcher getInstance(){
        return INSTANCE;
    }

    public void dispatchIntent(Intent intent){
        queue.offer(intent);
    }

    private class QueueRunner implements Runnable{

        public void run() {
            while(thread != null){
                Intent head = queue.poll();
                if(head==null){
                    continue;
                }
                head.execute();
            }
        }
    }
}
