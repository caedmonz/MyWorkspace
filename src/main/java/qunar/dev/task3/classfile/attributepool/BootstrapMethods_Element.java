package qunar.dev.task3.classfile.attributepool;

public class BootstrapMethods_Element {

    private int bootstrap_method_ref;

    private int num_bootstrap_arguments;

    private int[] bootstrap_arguments;

    public int getBootstrap_method_ref() {
        return bootstrap_method_ref;
    }

    public void setBootstrap_method_ref(int bootstrap_method_ref) {
        this.bootstrap_method_ref = bootstrap_method_ref;
    }

    public int getNum_bootstrap_arguments() {
        return num_bootstrap_arguments;
    }

    public void setNum_bootstrap_arguments(int num_bootstrap_arguments) {
        this.num_bootstrap_arguments = num_bootstrap_arguments;
    }

    public int[] getBootstrap_arguments() {
        return bootstrap_arguments;
    }

    public void setBootstrap_arguments(int[] bootstrap_arguments) {
        this.bootstrap_arguments = bootstrap_arguments;
    }

    @Override
    public String toString() {
        String state = " bootstrap_method_ref=" + bootstrap_method_ref;
        state += ",num_bootstrap_arguments=" + num_bootstrap_arguments + "\n";
        for (int i = 0; i < num_bootstrap_arguments; i++) {
            state += "\t\t\t" + bootstrap_arguments[i] + "\n";
        }
        return state;
    }
}
