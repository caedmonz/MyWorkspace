package qunar.dev.task3.classfile.constantpool;

import qunar.dev.task3.classfile.ConstantPool_Info;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Constant_NameAndType_Info extends ConstantPool_Info {
    int name_index;

    int descriptor_index;

    public int getName_index() {
        return name_index;
    }

    public void setName_index(int name_index) {
        this.name_index = name_index;
    }

    public int getDescriptor_index() {
        return descriptor_index;
    }

    public void setDescriptor_index(int descriptor_index) {
        this.descriptor_index = descriptor_index;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "name_index: " + name_index + ", descriptor_index: " + descriptor_index;
        return state;
    }
}
