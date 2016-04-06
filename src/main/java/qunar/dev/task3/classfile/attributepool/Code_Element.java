package qunar.dev.task3.classfile.attributepool;

public class Code_Element {

    private int start_pc;

    private int end_pc;

    private int handler_pc;

    private int catch_type;

    public int getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(int start_pc) {
        this.start_pc = start_pc;
    }

    public int getEnd_pc() {
        return end_pc;
    }

    public void setEnd_pc(int end_pc) {
        this.end_pc = end_pc;
    }

    public int getHandler_pc() {
        return handler_pc;
    }

    public void setHandler_pc(int handler_pc) {
        this.handler_pc = handler_pc;
    }

    public int getCatch_type() {
        return catch_type;
    }

    public void setCatch_type(int catch_type) {
        this.catch_type = catch_type;
    }

    @Override
    public String toString() {
        return "start_pc=" + start_pc + ",end_pc=" + end_pc + ",handler_pc=" + handler_pc + ",catch_type=" + catch_type;
    }
}
