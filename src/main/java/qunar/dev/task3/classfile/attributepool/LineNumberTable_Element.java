package qunar.dev.task3.classfile.attributepool;

public class LineNumberTable_Element {

    private int start_pc;

    private int line_number;

    public int getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(int start_pc) {
        this.start_pc = start_pc;
    }

    public int getLine_number() {
        return line_number;
    }

    public void setLine_number(int line_number) {
        this.line_number = line_number;
    }

    @Override
    public String toString() {
        return " start_pc=" + start_pc + ",line_number=" + line_number;
    }
}
