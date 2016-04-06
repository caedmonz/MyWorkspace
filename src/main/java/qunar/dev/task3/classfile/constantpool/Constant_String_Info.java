package qunar.dev.task3.classfile.constantpool;

import qunar.dev.task3.classfile.ConstantPool_Info;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Constant_String_Info extends ConstantPool_Info {
    int string_index;

    public int getString_index() {
        return string_index;
    }

    public void setString_index(int string_index) {
        this.string_index = string_index;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "string_index: " + string_index;
        return state;
    }
}
