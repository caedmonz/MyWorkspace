package qunar.dev.task3.classfile.attributepool;


import qunar.dev.task3.classfile.Attribute_Info;
import qunar.dev.task3.classfile.attributepool.annotations.ParameterAnnotation;

public class RuntimeInvisibleParameterAnnotations extends Attribute_Info {

    private int num_parameters;

    private ParameterAnnotation[] parameter_annotations;

    public int getNum_parameters() {
        return num_parameters;
    }

    public void setNum_parameters(int num_parameters) {
        this.num_parameters = num_parameters;
    }

    public ParameterAnnotation[] getParameter_annotations() {
        return parameter_annotations;
    }

    public void setParameter_annotations(ParameterAnnotation[] parameter_annotations) {
        this.parameter_annotations = parameter_annotations;
    }

    @Override
    public String toString() {
        String state = super.toString() + " num_parameters=" + num_parameters + "\n";
        for (int i = 0; i < num_parameters; i++) {
            state += "\t\t\t" + parameter_annotations[i] + "\n";
        }
        return state;
    }
}