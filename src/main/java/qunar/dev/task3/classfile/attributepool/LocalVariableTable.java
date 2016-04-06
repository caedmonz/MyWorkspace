package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class LocalVariableTable extends Attribute_Info {

    private int local_variable_table_length;

    private LocalVariableTable_Element[] elements;

    public int getLocal_variable_table_length() {
        return local_variable_table_length;
    }

    public void setLocal_variable_table_length(int local_variable_table_length) {
        this.local_variable_table_length = local_variable_table_length;
    }

    public LocalVariableTable_Element[] getElements() {
        return elements;
    }

    public void setElements(LocalVariableTable_Element[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String state = super.toString() + " local_variable_table_length=" + local_variable_table_length + "{";
        for (int i = 0; i < local_variable_table_length; i++) {
            state += "(element $" + i + ":" + elements[i] + ")";
        }
        state += "}";
        return state;
    }
}