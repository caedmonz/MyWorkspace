package qunar.dev.task3.classfile.attributepool;

public class LocalVariableTypeTable_Element {

    private int start_pc;

    private int length;

    private int name_index;

    private int signatuere_index;

    private int index;

    public int getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(int start_pc) {
        this.start_pc = start_pc;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getName_index() {
        return name_index;
    }

    public void setName_index(int name_index) {
        this.name_index = name_index;
    }

    public int getSignatuere_index() {
        return signatuere_index;
    }

    public void setSignatuere_index(int signatuere_index) {
        this.signatuere_index = signatuere_index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return " start_pc=" + start_pc + ",length=" + length + ",name_index=" + name_index
                + ",signatuere_index=" + signatuere_index + ",index=" + index;
    }
}
