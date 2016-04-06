package qunar.dev.task3.classfile;

/*
 * Super class of attributes.
 * 
 * <p>Parse corresponding bytes of class file to a class extends from
 * Attribute_Info as attributes has different formats according to tag
 * 
 * @author ZhangQinghua
 * 
 * @version 1.0
 * 
 * @since 2016.01.24
 * 
 * @see Method_Info
 * 
 * @see Field_Info
 */
public class Attribute_Info {
    Attribute_Tag attribute_name_index;

    int attribute_length;

    public Attribute_Tag getAttribute_name_index() {
        return attribute_name_index;
    }

    public void setAttribute_name_index(Attribute_Tag attribute_name_index) {
        this.attribute_name_index = attribute_name_index;
    }

    public int getAttribute_length() {
        return attribute_length;
    }

    public void setAttribute_length(int attribute_length) {
        this.attribute_length = attribute_length;
    }

    @Override
    public String toString() {
        String state = "attribute_name_index = " + attribute_name_index + ",";
        state += "attribute_length = " + attribute_length;
        return state;
    }
}
