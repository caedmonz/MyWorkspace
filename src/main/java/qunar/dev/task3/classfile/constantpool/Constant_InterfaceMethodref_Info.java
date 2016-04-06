package qunar.dev.task3.classfile.constantpool;

import qunar.dev.task3.classfile.ConstantPool_Info;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Constant_InterfaceMethodref_Info extends ConstantPool_Info {
    private int class_index;

    private int name_and_type_index;

    public int getClass_index() {
        return class_index;
    }

    public void setClass_index(int class_index) {
        this.class_index = class_index;
    }

    public int getName_and_type_index() {
        return name_and_type_index;
    }

    public void setName_and_type_index(int name_and_type_index) {
        this.name_and_type_index = name_and_type_index;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "class_index=" + class_index + ", name_and_type_index=" + name_and_type_index;
        return state;
    }
}
