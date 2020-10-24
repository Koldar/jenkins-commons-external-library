class MatrixUtils {

    @NonCPS
    public static List getMatrixAxes(Map matrix_axes) {
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
}