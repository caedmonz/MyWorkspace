package qunar.dev.task3.classfile.attributepool.stackmapclasses;

public class Same_Locals_1_Stack_Item_Frame_Extended extends Stack_Map_Frame {

    private int offset_delta;

    private Verification_Type_Info stack;

    public int getOffset_delta() {
        return offset_delta;
    }

    public void setOffset_delta(int offset_delta) {
        this.offset_delta = offset_delta;
    }

    public Verification_Type_Info getStack() {
        return stack;
    }

    public void setStack(Verification_Type_Info stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return super.toString() + " offset_delta=" + offset_delta + ",stack=" + stack;
    }
}
