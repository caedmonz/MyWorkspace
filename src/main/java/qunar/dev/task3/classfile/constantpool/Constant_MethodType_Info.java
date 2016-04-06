package qunar.dev.task3.classfile.constantpool;

import qunar.dev.task3.classfile.ConstantPool_Info;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Constant_MethodType_Info extends ConstantPool_Info {
    private int descriptor_index;

    public int getDescriptor_index() {
        return descriptor_index;
    }

    public void setDescriptor_index(int descriptor_index) {
        this.descriptor_index = descriptor_index;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "descriptor_index: " + descriptor_index;
        return state;
    }
}
