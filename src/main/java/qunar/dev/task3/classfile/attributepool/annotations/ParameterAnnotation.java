package qunar.dev.task3.classfile.attributepool.annotations;

public class ParameterAnnotation {

    private int num_annotations;

    private AnnotationValue[] annotations;

    public int getNum_annotations() {
        return num_annotations;
    }

    public void setNum_annotations(int num_annotations) {
        this.num_annotations = num_annotations;
    }

    public AnnotationValue[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(AnnotationValue[] annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        String state = "num_annotations=" + num_annotations + "\n";
        for (int i = 0; i < num_annotations; i++) {
            state += "\t\t\t\t" + annotations[i] + "\n";
        }
        return state;
    }
}
