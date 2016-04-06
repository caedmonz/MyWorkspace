package qunar.dev.task3.classfile.attributepool.annotations;

public class ElementValue {

    private char tag;

    public char getTag() {
        return tag;
    }

    public void setTag(char tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "tag=" + tag;
    }
}
