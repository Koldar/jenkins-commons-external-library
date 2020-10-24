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
                if (combination.get(rejectedEntry.key).EqualTo(rejectedEntry.value)) {
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
}