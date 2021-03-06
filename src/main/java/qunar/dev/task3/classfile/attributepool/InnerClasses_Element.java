package qunar.dev.task3.classfile.attributepool;

public class InnerClasses_Element {

    private int inner_class_info_index;

    private int outer_class_info_index;

    private int inner_name_index;

    private int inner_class_access_flags;

    public int getInner_class_info_index() {
        return inner_class_info_index;
    }

    public void setInner_class_info_index(int inner_class_info_index) {
        this.inner_class_info_index = inner_class_info_index;
    }

    public int getOuter_class_info_index() {
        return outer_class_info_index;
    }

    public void setOuter_class_info_index(int outer_class_info_index) {
        this.outer_class_info_index = outer_class_info_index;
    }

    public int getInner_name_index() {
        return inner_name_index;
    }

    public void setInner_name_index(int inner_name_index) {
        this.inner_name_index = inner_name_index;
    }

    public int getInner_class_access_flags() {
        return inner_class_access_flags;
    }

    public void setInner_class_access_flags(int inner_class_access_flags) {
        this.inner_class_access_flags = inner_class_access_flags;
    }

    @Override
    public String toString() {
        return " inner_class_info_index=" + inner_class_info_index + ",outer_class_info_index="
                + outer_class_info_index + ",inner_name_index=" + inner_name_index + ",inner_class_access_flags="
                + inner_class_access_flags;
    }
}
