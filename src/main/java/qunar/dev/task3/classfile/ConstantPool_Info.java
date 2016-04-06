package qunar.dev.task3.classfile;

/**
 * Base class of the elements in constant pool.
 * Created by Administrator on 2016/4/5.
 */
public abstract class ConstantPool_Info {
    private ConstantPool_Tag tag;

    public ConstantPool_Tag getTag() {
        return tag;
    }

    public void setTag(ConstantPool_Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "[tag = " + tag + "] ";
    }
}
