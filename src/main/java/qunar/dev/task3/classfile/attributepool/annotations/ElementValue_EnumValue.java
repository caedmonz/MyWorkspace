package qunar.dev.task3.classfile.attributepool.annotations;

public class ElementValue_EnumValue extends ElementValue {

    private int type_name_index;

    private int const_name_index;

    public int getType_name_index() {
        return type_name_index;
    }

    public void setType_name_index(int type_name_index) {
        this.type_name_index = type_name_index;
    }

    public int getConst_name_index() {
        return const_name_index;
    }

    public void setConst_name_index(int const_name_index) {
        this.const_name_index = const_name_index;
    }

    @Override
    public String toString() {
        return super.toString() + "(type_name_index=" + type_name_index + ",const_name_index=" + const_name_index + ")";
    }
}
