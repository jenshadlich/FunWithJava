package de.jeha.fwj.libsvm;

import libsvm.*;
import org.junit.Test;

public class LIBSVMTest {

    /**
     * XOR problem
     * <p/>
     * x     y
     * 0,0  -1
     * 0,1   1
     * 1,0   1
     * 1,1  -1
     */
    @Test
    public void testXORProblem() {
        svm_problem problem = new svm_problem();
        svm_node[][] x = new svm_node[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                x[i][j] = new svm_node();
            }
        }
        x[0][0].index = 1;
        x[0][0].value = 0;
        x[0][1].index = 2;
        x[0][1].value = 0;
        x[1][0].index = 1;
        x[1][0].value = 1;
        x[1][1].index = 2;
        x[1][1].value = 1;
        x[2][0].index = 1;
        x[2][0].value = 0;
        x[2][1].index = 2;
        x[2][1].value = 1;
        x[3][0].index = 1;
        x[3][0].value = 1;
        x[3][1].index = 2;
        x[3][1].value = 0;
        double[] labels = new double[]{-1, -1, 1, 1};

        problem.y = labels;
        problem.x = x;
        problem.l = x.length;

        svm_parameter parameter = new svm_parameter();
        parameter.svm_type = svm_parameter.C_SVC;
        parameter.kernel_type = svm_parameter.RBF;
        parameter.C = 1000;
        parameter.eps = 0.001;
        parameter.gamma = 10;
        parameter.probability = 1;
        parameter.cache_size = 1024;

        svm_model model = svm.svm_train(problem, parameter);

        svm_node[] test = new svm_node[]{new svm_node(), new svm_node()};
        test[0].index = 1;
        test[0].value = 0;
        test[1].index = 2;
        test[1].value = 0;
        double[] l = new double[2];

        double resultProb = svm.svm_predict_probability(model, test, l);
        double result = svm.svm_predict(model, test);

        System.out.println("resultProb = " + resultProb);
        System.out.println("result     = " + result);
    }

}
