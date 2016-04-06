package qunar.dev.task3.classfile.attributepool;


import qunar.dev.task3.classfile.Attribute_Info;
import qunar.dev.task3.classfile.attributepool.annotations.ElementValue;

public class AnnotationDefault extends Attribute_Info {

    private ElementValue default_value;

    public ElementValue getDefault_value() {
        return default_value;
    }

    public void setDefault_value(ElementValue default_value) {
        this.default_value = default_value;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += default_value;
        return state;
    }
}