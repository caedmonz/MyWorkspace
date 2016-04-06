package qunar.dev.task3.classfile.attributepool.stackmapclasses;

public class Same_Locals_1_Stack_Item_Frame extends Stack_Map_Frame {

    private Verification_Type_Info stack;

    public Verification_Type_Info getStack() {
        return stack;
    }

    public void setStack(Verification_Type_Info stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return super.toString() + " stack=" + stack;
    }
}
