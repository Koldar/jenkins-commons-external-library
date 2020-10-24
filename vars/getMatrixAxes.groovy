#!/usr/bin/env groovy

import com.fibonacci.jenkins.commons.MatrixUtils

def call(Map matrixAxes) {
    return MatrixUtils.getMatrixAxes(matrixAxes)
}