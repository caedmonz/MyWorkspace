package qunar.dev.task3.classfile.attributepool.annotations;

public class ElementValue_ArrayValue extends ElementValue {

    private int num_values;

    private ElementValue[] values;

    public int getNum_values() {
        return num_values;
    }

    public void setNum_values(int num_values) {
        this.num_values = num_values;
    }

    public ElementValue[] getValues() {
        return values;
    }

    public void setValues(ElementValue[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        String state = super.toString() + " num_values:" + num_values + "\n";
        for (int i = 0; i < num_values; i++) {
            state += "\t\t\t\t" + values[i] + "\n";
        }
        return state;
    }
}
