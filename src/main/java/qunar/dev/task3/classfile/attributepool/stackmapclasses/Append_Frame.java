package qunar.dev.task3.classfile.attributepool.stackmapclasses;

public class Append_Frame extends Stack_Map_Frame {

    private int offset_delta;

    private int length;

    private Verification_Type_Info[] locals;

    public Verification_Type_Info[] getLocals() {
        return locals;
    }

    public void setLocals(Verification_Type_Info[] locals) {
        this.locals = locals;
    }

    public int getOffset_delta() {
        return offset_delta;
    }

    public void setOffset_delta(int offset_delta) {
        this.offset_delta = offset_delta;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        String state = super.toString() + " offset_delta=" + offset_delta + "\n";
        for (int i = 0; i < length; i++) {
            state += "\t\t\t\t" + locals[i] + "\n";
        }
        return state;
    }
}
