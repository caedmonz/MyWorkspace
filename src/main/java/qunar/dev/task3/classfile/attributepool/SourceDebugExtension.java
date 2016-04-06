package qunar.dev.task3.classfile.attributepool;


import qunar.dev.task3.classfile.Attribute_Info;

public class SourceDebugExtension extends Attribute_Info {

    private byte[] debug_extension;

    public byte[] getDebug_extension() {
        return debug_extension;
    }

    public void setDebug_extension(byte[] debug_extension) {
        this.debug_extension = debug_extension;
    }

    @Override
    public String toString() {
        String state = super.toString();
        state += "debug_extension: " + new String(debug_extension);
        return state;
    }
}