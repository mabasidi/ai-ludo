/*
 *  Copyright (C) 2010 Gregor Trefs, Dominique Ritze
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.uni_mannheim.informatik.ai.ludo.model.ai.geneticAlgo;

import de.uni_mannheim.informatik.ai.ludo.model.ai.geneticAlgo.Individual;
import de.uni_mannheim.informatik.ai.ludo.model.ai.geneticAlgo.Setting;
import de.uni_mannheim.informatik.ai.ludo.model.ai.geneticAlgo.TermValues;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory;
import de.uni_mannheim.informatik.ai.ludo.model.Game;
import de.uni_mannheim.informatik.ai.ludo.model.Player;
import de.uni_mannheim.informatik.ai.ludo.model.ai.GeneticPlayer;
import de.uni_mannheim.informatik.ai.ludo.model.events.NotificationEvent;
import de.uni_mannheim.informatik.ai.ludo.model.states.EndState;
import de.uni_mannheim.informatik.ai.ludo.model.states.RoundStartedState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dominique Ritze
 *
 * 
 */
public class GeneticUtility {

    //list containing all settings which should be tested
    private static List<Setting> allSettings = new ArrayList<Setting>();
    //setting which is currently tested
    private static Setting currentSetting;

    //number of individuals which have been created within a certain number of games
    private static int numberOfGeneratedIndividuals = 0;
    //number of generated populations
    private static int numberOfGenerations = 1;
    //current population
    private static Population population = new Population();
    //index of the setting which should be currently used
    private static int settingIndex = 0;
    //number of games played within a certain time
    private static int currentlyPlayed = 1;
    private static int gamesPlayed = 1;

    private static Random random = new Random();

    /**
     * After a single game has been finished, the
     * genetic algorithm is involved. First it checks
     * whether the genetic player won the game and if this is true,
     * the number of wins of the current individual is increased.
     * Afterwards it must be determined whether this was just another sample
     * of an individual and the next game is also played by this individual
     * or if this was the last sample for this individual.
     * If it was the last sample but the population size has not yet been reached,
     * a new individual must take over. Otherwise either the algorithm terminates
     * because the termination criteria is satisfied or it continues with
     * the selection, crossover and mutation steps and in the end
     * start the whole procedure again with a new population.
     * In case of termination, the whole procedure might start again
     * with a different setting, if there is at least one untested setting left.
     *
     *
     * @param game
     */
    public static void procedureAfterGame(Game game) {

        //number of all games which have been played since the program has been
        //started
        int gamessPlayedTotal = game.getGamesPlayed();

        //initialize the different test setting which are successively executed
        if(gamessPlayedTotal == 1) {
            initSettings();
            currentSetting = allSettings.get(settingIndex);
            settingIndex++;
        }

        //if the player won, increase the score of its individual
        if(game.getCurrentPlayer() instanceof GeneticPlayer) {
            ((GeneticPlayer)game.getCurrentPlayer()).getIndividual().increaseScore();
        }

        //within the first phase where the first population is generated
        if(gamesPlayed < (currentSetting.getNUMBER_OF_SAMPLES()*currentSetting.getSTART_POPULATION())) {            
            //a new individual within the first generating should be generated
            if(gamesPlayed%currentSetting.getNUMBER_OF_SAMPLES() == 0) {
                //set the new individual
                for(Player p : game.getPlayers()) {
                    if(!(p instanceof GeneticPlayer)) {
                        continue;
                    }
                    //first population only contains individuals with random weights
                    ((GeneticPlayer)p).setIndividual(randomIndividual());
                }
            }
            currentlyPlayed++;
            gamesPlayed++;
            //start a new game
            startNewGame(game);
            return;
        }

        //new individual should be created 
        if(currentlyPlayed == currentSetting.getNUMBER_OF_SAMPLES()*currentSetting.getSTART_POPULATION()
                || currentlyPlayed == currentSetting.getNUMBER_OF_SAMPLES()) {
            GeneticUtility.numberOfGeneratedIndividuals++;
            currentlyPlayed = 0;
            gamesPlayed++;
            //enough new individuals created, delete weakest individuals and start a new generation
            if(GeneticUtility.numberOfGeneratedIndividuals == currentSetting.getNUMBER_OF_NEW_INSTANCES()
                    || gamesPlayed-1 == currentSetting.getSTART_POPULATION()*currentSetting.getNUMBER_OF_SAMPLES()) {
                population.clearLastOnes();
                GeneticUtility.numberOfGeneratedIndividuals = 0;
                GeneticUtility.numberOfGenerations++;
            }
            //check termination criterium and exit if it is satisfied
            if(checkTerminationCriterium()) {
                //all settings have been tested
                System.out.println("currentSetting: " + currentSetting.toString() +
                        " best: " + population.getCurrentPopulation().get(population.getCurrentPopulation().size()-1).getScore());
                System.out.println(population.getCurrentPopulation().get(population.getCurrentPopulation().size()-1).getWeights());
                if(settingIndex == allSettings.size()) {
                    endGame(game);
                    return;
                }
                //a test with one setting has been finished but there are other ones
                else {
                    //grab next setting and reset all values
                    currentSetting = allSettings.get(settingIndex);
                    settingIndex++;                    
                    population = new Population();
                    currentlyPlayed = 0;
                    gamesPlayed = 0;
                    numberOfGeneratedIndividuals = 0;
                    numberOfGenerations = 1;                    
                    startNewGame(game);
                    return;
                }
            }

            //create individual by recombination and mutation and bind it to the player
            for(Player p : game.getPlayers()) {
                if(!(p instanceof GeneticPlayer)) {
                    continue;
                }
                ((GeneticPlayer)p).setIndividual(createIndividualByCrossoverAndMutation());
            }
        }
        currentlyPlayed++;
        gamesPlayed++;
        startNewGame(game);
        return;
    }

    /**
     * Define all settings which should be tested.
     */
    private static void initSettings() {
        Integer[] populationSize = new Integer[]{250};
        Integer[] sampleSize = new Integer[]{100};
        Integer[] numberNewIndividuals = new Integer[]{75};
        Integer[] ProbMutation = new Integer[]{100};
        for(int i=0; i<populationSize.length; i++) {
            for(int j=0; j<sampleSize.length; j++) {
                for(int k=0; k<numberNewIndividuals.length; k++) {
                    if(numberNewIndividuals[k]>=populationSize[i]) {
                            continue;
                        }
                    for(int l=0; l<ProbMutation.length; l++) {
                        allSettings.add(new Setting(populationSize[i], sampleSize[j],
                                numberNewIndividuals[k], ProbMutation[l]));
                    }
                }
            }
        }
    }

    /**
     * Start a new game.
     *
     * @param game
     */
    private static void  startNewGame(Game game) {
        game.setState(new RoundStartedState());
        game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.NEW_GAME));
        IntentFactory.getInstance().createAndDispatchNewGameIntent(game);
        return;
    }

    /**
     *
     * End the whole game such that the program terminates.
     *
     * @param game
     */
    private static void endGame(Game game) {
        game.setState(new EndState());
        game.fireNotificationEvent(new NotificationEvent(game, NotificationEvent.Type.END_GAME));
        IntentFactory.getInstance().createAndDispatchEndGameIntent(game);
        return;
    }

    /**
     * Check whether the termination criterium is satsified or not.
     *
     * @return True if satisfied, false otherwise.
     */
    private static boolean checkTerminationCriterium() {
        return numberOfGenerations == currentSetting.getNUMBER_OF_GENERATIONS();
    }

    /**
     * Create a new individual by crossover and mutation.
     * First two individuals are randomly chosen as "parents".
     * Again radomly a breakage is selected and the new individual gets
     * all values in front of the breakage from one individual and the
     * remaining parts of the other one.
     * The mutation step is applied according to the defined probability.
     * If it is applied, just one randomly selected values is set to
     * a new random value.
     * In the end the new individual is added to the population and returned
     *
     * @return The new created individual.
     */
    private static Individual createIndividualByCrossoverAndMutation() {
        //determine parents
        int firstInd = random.nextInt(currentSetting.getSTART_POPULATION()-currentSetting.getNUMBER_OF_NEW_INSTANCES());
        int secondInd = random.nextInt(currentSetting.getSTART_POPULATION()-currentSetting.getNUMBER_OF_NEW_INSTANCES());
        //crossover step
        Individual generatedIndividual = createIndividualByCrossover(population.currentPopulation.get(firstInd),
                population.currentPopulation.get(secondInd));
        //mutation step
        if((random.nextInt(101))<=(currentSetting.getPROBABILITY_MUTATION()*100)){
            generatedIndividual = mutateIndividual(generatedIndividual);
        }
        //add to population
        GeneticUtility.population.addIndividual(generatedIndividual);
        return generatedIndividual;
    }

    /**
     * Create a individual with random values and
     * add it to the current population.
     *
     * @return Individual with random values.
     */
    public static Individual randomIndividual() {
        Individual individual = new Individual();
        GeneticUtility.population.addIndividual(individual);
        return individual;
    }

    /**
     *
     * Mutate an individual.
     * First randomly select the value which should be changed.
     * Then randomly generate a new value and create a new individual
     * with the values.
     *
     * @param ind Individual which should be mutated.
     * @return Mutated individual.
     */
    private static Individual mutateIndividual(Individual ind) {
        //which value should be changed
        int mutationIndex = random.nextInt(7);
        //new value
        double newValue = random.nextInt(100);
        TermValues mutatedWeight = new TermValues(new Double[]{
            (0 == mutationIndex ? newValue : ind.getWeights().getDIRECT_BEAT()),
            (1 == mutationIndex ? newValue : ind.getWeights().getINDIRECT_BEAT()),
            (2 == mutationIndex ? newValue : ind.getWeights().getESCAPE_POSSIBLE_BEAT()),
            (3 == mutationIndex ? newValue : ind.getWeights().getENTER_POSSIBLE_BEAT()),
            (4 == mutationIndex ? newValue : ind.getWeights().getSTART_POSITION()),
            (5 == mutationIndex ? newValue : ind.getWeights().getENTER_TARGET()),
            (6 == mutationIndex ? newValue : ind.getWeights().getPROGRESS_FACTOR())});
        return new Individual(mutatedWeight);
    }

    /**
     * Execution of the crossover step.
     * Randomly select a breakage and create a new individual with
     * values according to the values of the parent and the breakage.
     *
     * @param ind1 First parent individual.
     * @param ind2 Second parent individual.
     * @return Generated Individual.
     */
    private static Individual createIndividualByCrossover(Individual ind1, Individual ind2) {
        //determine breakage
        int combinationIndex = random.nextInt(6)+1;
        TermValues combinedWeights = new TermValues(new Double[]{
            (0 < combinationIndex ? ind1.getWeights().getDIRECT_BEAT() : ind2.getWeights().getDIRECT_BEAT()),
            (1 < combinationIndex ? ind1.getWeights().getINDIRECT_BEAT() : ind2.getWeights().getINDIRECT_BEAT()),
            (2 < combinationIndex ? ind1.getWeights().getESCAPE_POSSIBLE_BEAT() : ind2.getWeights().getESCAPE_POSSIBLE_BEAT()),
            (3 < combinationIndex ? ind1.getWeights().getENTER_POSSIBLE_BEAT() : ind2.getWeights().getENTER_POSSIBLE_BEAT()),
            (4 < combinationIndex ? ind1.getWeights().getSTART_POSITION() : ind2.getWeights().getSTART_POSITION()),
            (5 < combinationIndex ? ind1.getWeights().getENTER_TARGET() : ind2.getWeights().getENTER_TARGET()),
            (6 < combinationIndex ? ind1.getWeights().getPROGRESS_FACTOR() : ind2.getWeights().getPROGRESS_FACTOR())});
        return new Individual(combinedWeights);
    }

    /**
     * Represents the population used within the genetic algorithm.
     * Consists of several individuals which can be added or deleted
     * to/of the population.
     *
     */
    public static class Population {

        //individuals of a population
        List<Individual> currentPopulation;

        /**
         * Create a new population without any individual.
         *
         */
        public Population() {
            currentPopulation = new ArrayList<Individual>();
        }

        /**
         * Add an individual to the current population.
         *
         * @param player Individual to be added.
         */
        public void addIndividual(Individual player) {
            this.currentPopulation.add(player);
        }

        /**
         *
         * @return The sorted (ascending) population accoring to the
         * score of the individuals.
         */
        public List<Individual> getCurrentPopulation() {
            Collections.sort(this.currentPopulation);
            return this.currentPopulation;
        }

        /**
         * Delete all individuals of the current population.
         */
        public void clearAll() {
            this.currentPopulation.clear();
        }

        /**
         * Delete the individuals which are too weak and have been 
         * chosen to be deleted during the selection step.
         */
        public void clearLastOnes() {
            Collections.sort(this.currentPopulation);
            for(int i=0; i<currentSetting.getNUMBER_OF_NEW_INSTANCES(); i++) {
                this.currentPopulation.remove(0);                
            }
        }
    }
}

