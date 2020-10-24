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
    static List rejectCombinations(List combinations, Map reject) {
        List result = []
        for(Map combination : combinations) {
            def toAdd = true
            for (Entry rejectedEntry : reject.entrySet()) {
                if (combination.get(rejectedEntry.getKey()).equals(rejectedEntry.getValue())) {
                    toAdd = false
                    break
                }
            }
            if (toAdd) {
                result.add(combination)
            }
        }
        return result
    }

    @NonCPS
    static List selectCombinations(List combinations, Map select) {
        List result = []
        for(Map combination : combinations) {
            def toAdd = true
            for (Entry selectedEntry : select.entrySet()) {
                if (!combination.get(selectedEntry.getKey()).equals(selectedEntry.getValue())) {
                    toAdd = false
                    break
                }
            }
            if (toAdd) {
                result.add(combination)
            }
        }
        return result
    }

    @NonCPS
    static runCombinations(List combinations, boolean shouldRunParallel, Closure closure) {
        Map tasks = [failFast: false]

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
            String nodeLabel = "node ${String.Join(', ', combination)}"
            tasks[combinationEnv.join(', ')] = { ->
                node(nodeLabel) {
                    withEnv(combinationEnv) {
                        closure(combination)
                    }
                }
            }
        }

        if (shouldRunParallel) {
            parallel(tasks)
        }
        else {
            for (Entry entry : tasks) {
                entry.value()
            }
        }
    }
}