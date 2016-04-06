package qunar.dev.task3.classfile.attributepool.annotations;

public class AnnotationValue_ElementPairs {

    private int element_value_index;

    private ElementValue value;

    public int getElement_value_index() {
        return element_value_index;
    }

    public void setElement_value_index(int element_value_index) {
        this.element_value_index = element_value_index;
    }

    public ElementValue getValue() {
        return value;
    }

    public void setValue(ElementValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + element_value_index + "," + value + ")";
    }
}
