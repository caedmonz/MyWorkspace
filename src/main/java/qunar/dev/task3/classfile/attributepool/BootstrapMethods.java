package qunar.dev.task3.classfile.attributepool;

import qunar.dev.task3.classfile.Attribute_Info;

public class BootstrapMethods extends Attribute_Info {

    private int num_bootstrap_methods;

    private BootstrapMethods_Element[] elements;

    public int getNum_bootstrap_methods() {
        return num_bootstrap_methods;
    }

    public void setNum_bootstrap_methods(int num_bootstrap_methods) {
        this.num_bootstrap_methods = num_bootstrap_methods;
    }

    public BootstrapMethods_Element[] getElements() {
        return elements;
    }

    public void setElements(BootstrapMethods_Element[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String state = super.toString() + " num_bootstrap_methods=" + num_bootstrap_methods + "{";
        for (int i = 0; i < num_bootstrap_methods; i++) {
            state += "(element $" + i + ":" + elements[i] + ")";
        }
        state += "}";
        return state;
    }
}