package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class EnclosingMethod extends Attribute_Info {

    private int class_index;

    private int method_index;

    public int getClass_index() {
        return class_index;
    }

    public void setClass_index(int class_index) {
        this.class_index = class_index;
    }

    public int getMethod_index() {
        return method_index;
    }

    public void setMethod_index(int method_index) {
        this.method_index = method_index;
    }

    @Override
    public String toString() {
        return super.toString() + " class_index=" + class_index + ",method_index=" + method_index;
    }

}