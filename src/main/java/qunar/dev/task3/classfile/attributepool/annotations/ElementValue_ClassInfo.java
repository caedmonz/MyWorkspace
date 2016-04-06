package qunar.dev.task3.classfile.attributepool.annotations;

public class ElementValue_ClassInfo extends ElementValue {

    private int class_info_index;

    public int getClass_info_index() {
        return class_info_index;
    }

    public void setClass_info_index(int class_info_index) {
        this.class_info_index = class_info_index;
    }

    @Override
    public String toString() {
        return super.toString() + " class_info_index:" + class_info_index;
    }
}
