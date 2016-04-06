package qunar.dev.task3.classfile.constantpool;

import qunar.dev.task3.classfile.ConstantPool_Info;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Constant_MethodHandle_Info extends ConstantPool_Info {
    private int reference_kind;

    private int reference_index;

    public int getReference_kind() {
        return reference_kind;
    }

    public void setReference_kind(int reference_kind) {
        this.reference_kind = reference_kind;
    }

    public int getReference_index() {
        return reference_index;
    }

    public void setReference_index(int reference_index) {
        this.reference_index = reference_index;
    }

    @Override
    public String toString(){
        String state = super.toString();
        state += "reference_kind=" + reference_kind + ", reference_index=" + reference_index;
        return state;
    }

}
