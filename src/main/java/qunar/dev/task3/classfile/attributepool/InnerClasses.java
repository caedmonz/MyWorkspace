package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class InnerClasses extends Attribute_Info {

    private int number_of_classes;

    private InnerClasses_Element[] classes;

    public int getNumber_of_classes() {
        return number_of_classes;
    }

    public void setNumber_of_classes(int number_of_classes) {
        this.number_of_classes = number_of_classes;
    }

    public InnerClasses_Element[] getClasses() {
        return classes;
    }

    public void setClasses(InnerClasses_Element[] classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        String state = super.toString() + " number_of_classes=" + number_of_classes + "{";
        for (int i = 0; i < number_of_classes; i++) {
            state += "(class $" + i + ":" + classes[i] + ")";
        }
        state += "}";
        return state;
    }
}