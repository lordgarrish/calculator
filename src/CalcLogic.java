public class CalcLogic {
    private double result;
    private Operation operation;
    private boolean isReady = false;

    enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLYING,
        DIVISION
    }

    public double calculate(double x) {
        if (!isReady) {
            result = x;
            isReady = true;
            return result;
        }
        switch (operation) {
            case ADDITION:
                result += x;
                break;
            case SUBTRACTION:
                result -= x;
                break;
            case MULTIPLYING:
                result *= x;
                break;
            case DIVISION:
                result /= x;
                break;
        }
        return result;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void cancel() {
        isReady = false;
        operation = null;
    }
}
