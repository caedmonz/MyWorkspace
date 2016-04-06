package qunar.dev.task3.classfile.attributepool.stackmapclasses;

public class Chop_Frame extends Stack_Map_Frame {

    private int offset_delta;

    public int getOffset_delta() {
        return offset_delta;
    }

    public void setOffset_delta(int offset_delta) {
        this.offset_delta = offset_delta;
    }

    @Override
    public String toString() {
        return super.toString() + " offset_delta=" + offset_delta;
    }
}
