import java.util.List;

public class GraphProblem implements Problem{
    @Override
    public Integer initialState() {
        return null;
    }

    @Override
    public List actions(Object state) {
        return null;
    }

    @Override
    public Object result(Object state, Object action) {
        return null;
    }

    @Override
    public boolean isGoal(Object state) {
        return false;
    }

    @Override
    public double cost(Object state, Object action) {
        return 0;
    }


}