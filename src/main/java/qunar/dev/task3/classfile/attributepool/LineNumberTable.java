package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class LineNumberTable extends Attribute_Info {

    private int line_number_table_length;

    private LineNumberTable_Element[] elements;

    public int getLine_number_table_length() {
        return line_number_table_length;
    }

    public void setLine_number_table_length(int line_number_table_length) {
        this.line_number_table_length = line_number_table_length;
    }

    public LineNumberTable_Element[] getElements() {
        return elements;
    }

    public void setElements(LineNumberTable_Element[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String state = super.toString() + " line_number_table_length=" + line_number_table_length + "{";
        for (int i = 0; i < line_number_table_length; i++) {
            state += "(element $" + i + ":" + elements[i] + ")";
        }
        state += "}";
        return state;
    }
}