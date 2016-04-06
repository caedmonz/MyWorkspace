package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class Code extends Attribute_Info {

    private int max_stack;

    private int max_locals;

    private int code_length;

    private byte[] code;

    private int exception_table_length;

    private Code_Element[] exception_table;

    private int attributes_count;

    private Attribute_Info[] attributes;

    public int getMax_stack() {
        return max_stack;
    }

    public void setMax_stack(int max_stack) {
        this.max_stack = max_stack;
    }

    public int getMax_locals() {
        return max_locals;
    }

    public void setMax_locals(int max_locals) {
        this.max_locals = max_locals;
    }

    public int getCode_length() {
        return code_length;
    }

    public void setCode_length(int code_length) {
        this.code_length = code_length;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public int getException_table_length() {
        return exception_table_length;
    }

    public void setException_table_length(int exception_table_length) {
        this.exception_table_length = exception_table_length;
    }

    public Code_Element[] getException_table() {
        return exception_table;
    }

    public void setException_table(Code_Element[] exception_table) {
        this.exception_table = exception_table;
    }

    public int getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(int attributes_count) {
        this.attributes_count = attributes_count;
    }

    public Attribute_Info[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute_Info[] attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String state = super.toString() + " max_stack=" + max_stack;
        state += ",max_locals=" + max_locals;
        state += ",code_length=" + code_length;
        state += ",code=";
        String codes = "";
        for (int i = 0; i < code.length; i++) {
            codes += Integer.toHexString(code[i] & 0xff);
        }
        state += codes;
        state += ",exception_table_length=" + exception_table_length + "{";
        for (int i = 0; i < exception_table_length; i++) {
            state += "(exception_table $" + i + ":" + exception_table[i] + ")";
        }
        state += "}";
        state += " attributes_count=" + attributes_count;
        for (int i = 0; i < attributes_count; i++) {
            state += "\n\t\t\tattributes #" + i + ":" + attributes[i];
        }
        return state;
    }
}
