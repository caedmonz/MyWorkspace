package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class Exceptions extends Attribute_Info {

    private int number_of_exceptions;

    private int[] exception_index_table;

    public int getNumber_of_exceptions() {
        return number_of_exceptions;
    }

    public void setNumber_of_exceptions(int number_of_exceptions) {
        this.number_of_exceptions = number_of_exceptions;
    }

    public int[] getException_index_table() {
        return exception_index_table;
    }

    public void setException_index_table(int[] exception_index_table) {
        this.exception_index_table = exception_index_table;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "number_of_exceptions = " + number_of_exceptions + " [";
        for (int i = 0; i < number_of_exceptions; i++) {
            state += "(exception_index" + i + "," + exception_index_table[i] + ")";
        }
        state += "]";
        return state;
    }
}
