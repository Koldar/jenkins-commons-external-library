#!/usr/bin/env groovy

package com.fibonacci.jenkins.commons

import java.util.Map.Entry;

class MatrixUtils {

    @NonCPS
    static List getMatrixCombinations(Map matrix_axes) {
        List axes = []
        matrix_axes.each { axis, values ->
            List axisList = []
            values.each { value ->
                axisList << [(axis): value]
            }
            axes << axisList
        }
        // calculate cartesian product
        axes.combinations()*.sum()
    }

    @NonCPS
    static boolean shouldReject(Map combination, Map rejectMap) {
        for (Entry rejectedEntry : rejectMap.entrySet()) {
            def actual = combination.get(rejectedEntry.getKey())
            def expected = rejectedEntry.getValue()
            if (actual == null) {
                return false
            }
            if (!actual.equals(expected)) {
                return false
            }
        }
        return true
    }

    @NonCPS
    static boolean shouldSelect(Map combination, Map selectMap) {
        for (Entry selectedEntry : selectMap.entrySet()) {
            def actual = combination.get(selectedEntry.getKey())
            def expected = selectedEntry.getValue()
            if (actual == null) {
                return false
            }
            if (!actual.equals(expected)) {
                return false
            }
        }
        return true
    }

    @NonCPS
    static List rejectCombinations(List combinations, Map rejectMap) {
        List result = []
        for(Map combination : combinations) {
            if (!shouldReject(combination, rejectMap)) {
                result.add(combination)
            }
        }
        return result
    }

    @NonCPS
    static List selectCombinations(List combinations, Map selectMap) {
        List result = []
        for(Map combination : combinations) {
            if (shouldSelect(combination, selectMap)) {
                result.add(combination)
            }
        }
        return result
    }

    @NonCPS
    static runCombinations(List combinations, boolean shouldRunParallel) {
        List result = []
        Map tasks = new HashMap()

        for(int i = 0; i < combinations.size(); i++) {
            // convert the Axis into valid values for withEnv step
            Map combination = new HashMap(combinations[i])
            combination.put("COMBINATION_ID", i)
            List combinationEnv = combination.collect { k, v ->
                "${k}=${v}"
            }
            // let's say you have diverse agents among Windows, Mac and Linux all of
            // which have proper labels for their platform and what browsers are
            // available on those agents.
            String nodeLabel = "node " + combinationEnv.join(", ")
            tasks.put(nodeLabel, { ->
                node {
                    withEnv(combinationEnv) {
                        // closure(combination)
                        stage("inner") {
                            blueEcho "blue echo inner"
                        }
                    }
                }
            })
        }

        return tasks

        // if (shouldRunParallel) {
        //     parallel(tasks)
        // }
        // else {
        //     for (entry in tasks.entrySet()) {
        //         result.add(entry.getKey())
        //         entry.getValue()()
        //     }
        // }

        // return result
    }
}