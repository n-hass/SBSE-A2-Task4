package uoa.partII;

import org.moeaframework.core.Constraint;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

public class TestProblem extends AbstractProblem {
	public TestProblem() {
        super(2, 2, 2);
    }

    @Override
    public void evaluate(Solution solution) {
        double x = EncodingUtils.getReal(solution.getVariable(0));
        double y = EncodingUtils.getReal(solution.getVariable(1));

        double f1 = Math.pow(x - 2.0, 2.0) + Math.pow(y - 1.0, 2.0) + 2.0;
        double f2 = 9.0*x - Math.pow(y - 1.0, 2.0);
        double c1 = Math.pow(x, 2.0) + Math.pow(y, 2.0);
        double c2 = x - 3.0*y;

        solution.setObjective(0, f1);
        solution.setObjective(1, f2);

        solution.setConstraint(0, Constraint.lessThanOrEqual(c1, 225.0));
        solution.setConstraint(1, Constraint.lessThanOrEqual(c2, -10.0));
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(2, 2, 2);

        solution.setVariable(0, new RealVariable(-20.0, 20.0));
        solution.setVariable(1, new RealVariable(-20.0, 20.0));

        return solution;
    }
}
