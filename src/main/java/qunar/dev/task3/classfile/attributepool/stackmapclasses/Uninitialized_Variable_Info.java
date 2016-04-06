package qunar.dev.task3.classfile.attributepool.stackmapclasses;

public class Uninitialized_Variable_Info extends Verification_Type_Info {

    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return super.toString() + " offset=" + offset;
    }
}
