package qunar.dev.task3.classfile.attributepool.annotations;

public class ElementValue_AnnotationValue extends ElementValue {

    private AnnotationValue annotation_value;

    public AnnotationValue getAnnotation_value() {
        return annotation_value;
    }

    public void setAnnotation_value(AnnotationValue annotation_value) {
        this.annotation_value = annotation_value;
    }

    @Override
    public String toString() {
        return super.toString() + annotation_value;
    }
}
