package qunar.dev.task3.classfile.attributepool.stackmapclasses;

public class Object_Variable_Info extends Verification_Type_Info {
    private int cpool_index;

    public int getCpool_index() {
        return cpool_index;
    }

    public void setCpool_index(int cpool_index) {
        this.cpool_index = cpool_index;
    }

    @Override
    public String toString() {
        return super.toString() + " cpool_index=" + cpool_index;
    }
}
