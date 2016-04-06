package qunar.dev.task3.classfile.attributepool.annotations;

public class AnnotationValue {

    private int type_index;

    private int num_element_value_pairs;

    private AnnotationValue_ElementPairs[] element_value_pairs;

    public int getType_index() {
        return type_index;
    }

    public void setType_index(int type_index) {
        this.type_index = type_index;
    }

    public int getNum_element_value_pairs() {
        return num_element_value_pairs;
    }

    public void setNum_element_value_pairs(int num_element_value_pairs) {
        this.num_element_value_pairs = num_element_value_pairs;
    }

    public AnnotationValue_ElementPairs[] getElement_value_pairs() {
        return element_value_pairs;
    }

    public void setElement_value_pairs(AnnotationValue_ElementPairs[] element_value_pairs) {
        this.element_value_pairs = element_value_pairs;
    }

    @Override
    public String toString() {
        String state = "type_index:" + type_index + ",";
        state += "num_element_value_pairs:" + num_element_value_pairs + "{";
        for (int i = 0; i < num_element_value_pairs; i++) {
            state += element_value_pairs[i];
        }
        state += "}";
        return state;
    }
}
