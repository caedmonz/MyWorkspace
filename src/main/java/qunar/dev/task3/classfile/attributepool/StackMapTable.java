package qunar.dev.task3.classfile.attributepool;


import qunar.dev.task3.classfile.Attribute_Info;
import qunar.dev.task3.classfile.attributepool.stackmapclasses.Stack_Map_Frame;

public class StackMapTable extends Attribute_Info {

    private int number_of_entries;

    private Stack_Map_Frame[] entries;

    public int getNumber_of_entries() {
        return number_of_entries;
    }

    public void setNumber_of_entries(int number_of_entries) {
        this.number_of_entries = number_of_entries;
    }

    public Stack_Map_Frame[] getEntries() {
        return entries;
    }

    public void setEntries(Stack_Map_Frame[] entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        String state = super.toString() + " number_of_entries=" + number_of_entries + "\n";
        for (int i = 0; i < number_of_entries; i++) {
            state += "\t\t\t" + entries[i] + "\n";
        }
        return state;
    }
}