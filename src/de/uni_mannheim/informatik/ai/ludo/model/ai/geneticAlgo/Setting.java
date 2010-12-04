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

/**
 * While searching for the best values of the utility function terms,
 * the genetic algorithm should be used with dirrent parameters, e.g.
 * different settings. For example to change the population size or
 * the mutation rate.
 * This class represents a datastructure to store the current settings
 * and to be able to chance the paramter for a new run.
 *
 * @author Dominique Ritze
 */
public class Setting {

    /**
     * Constructor.
     * Set the parameter of the setting.
     *
     * @param populationSize The population size which is used.
     * @param sampleSize Number of samples per individual.
     * @param newIndividuals Number of new individuals per population.
     * @param mutationRate How many new individuals should be mutated.
     */
    public Setting(int populationSize, int sampleSize, int newIndividuals, int mutationRate) {
        this.populationSize = populationSize;
        this.numberOfSamplesPerIndividual = sampleSize;
        //convert to percentage
        this.numberOfNewIndividuals = (newIndividuals*populationSize)/100;
        //convert to percentage
        this.mutationProbability = mutationRate/100.00;
    }

    //size population
    private int populationSize;
    //number of games played by a individual
    private int numberOfSamplesPerIndividual;
    //how many generation should be generated, can be used for termination criteria
    private int numberOfGenerations = 25;
    //number of new instances in the next generation
    private int numberOfNewIndividuals;
    //probability of a mutation
    private double mutationProbability;

    /**
     * @return the populationSize
     */
    public int getSTART_POPULATION() {
        return populationSize;
    }

    /**
     * @param populationSize the populationSize to set
     */
    public void setSTART_POPULATION(int START_POPULATION) {
        this.populationSize = START_POPULATION;
    }

    /**
     * @return the numberOfSamplesPerIndividual
     */
    public int getNUMBER_OF_SAMPLES() {
        return numberOfSamplesPerIndividual;
    }

    /**
     * @param numberOfSamplesPerIndividual the numberOfSamplesPerIndividual to set
     */
    public void setNUMBER_OF_SAMPLES(int NUMBER_OF_SAMPLES) {
        this.numberOfSamplesPerIndividual = NUMBER_OF_SAMPLES;
    }

    /**
     * @return the numberOfGenerations
     */
    public int getNUMBER_OF_GENERATIONS() {
        return numberOfGenerations;
    }

    /**
     * @param numberOfGenerations the numberOfGenerations to set
     */
    public void setNUMBER_OF_GENERATIONS(int NUMBER_OF_GENERATIONS) {
        this.numberOfGenerations = NUMBER_OF_GENERATIONS;
    }

    /**
     * @return the numberOfNewIndividuals
     */
    public int getNUMBER_OF_NEW_INSTANCES() {
        return numberOfNewIndividuals;
    }

    /**
     * @param numberOfNewIndividuals the numberOfNewIndividuals to set
     */
    public void setNUMBER_OF_NEW_INSTANCES(int NUMBER_OF_NEW_INSTANCES) {
        this.numberOfNewIndividuals = NUMBER_OF_NEW_INSTANCES;
    }

    /**
     * @return the mutationProbability
     */
    public double getPROBABILITY_MUTATION() {
        return mutationProbability;
    }

    /**
     * @param mutationProbability the mutationProbability to set
     */
    public void setPROBABILITY_MUTATION(double PROBABILITY_MUTATION) {
        this.mutationProbability = PROBABILITY_MUTATION;
    }

    @Override
    public String toString() {
        return "pop: " + populationSize + " samples: " + numberOfSamplesPerIndividual +
                " instances: " + numberOfNewIndividuals + " mutation: " + mutationProbability;
    }


}
