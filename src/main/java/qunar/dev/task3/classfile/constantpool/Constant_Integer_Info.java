package qunar.dev.task3.classfile.constantpool;

import qunar.dev.task3.classfile.ConstantPool_Info;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Constant_Integer_Info extends ConstantPool_Info {

    private int bytes;

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "bytes: " + bytes;
        return state;
    }
}
