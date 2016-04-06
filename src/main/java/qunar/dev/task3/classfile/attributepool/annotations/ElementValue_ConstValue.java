package qunar.dev.task3.classfile.attributepool.annotations;

public class ElementValue_ConstValue extends ElementValue {

    private int const_value_index;

    public int getConst_value_index() {
        return const_value_index;
    }

    public void setConst_value_index(int const_value_index) {
        this.const_value_index = const_value_index;
    }

    @Override
    public String toString() {
        return super.toString() + " const_value_index:" + const_value_index;
    }
}
