package qunar.dev.task3.classfile;

import java.util.ArrayList;
import java.util.List;

public class Method_Info {

    private int access_flags;

    private int name_index;

    private int descriptor_index;

    private int attributes_count;

    private List<Attribute_Info> attributes;

    public int getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(int access_flags) {
        this.access_flags = access_flags;
    }

    public int getName_index() {
        return name_index;
    }

    public void setName_index(int name_index) {
        this.name_index = name_index;
    }

    public int getDescriptor_index() {
        return descriptor_index;
    }

    public void setDescriptor_index(int descriptor_index) {
        this.descriptor_index = descriptor_index;
    }

    public int getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(int attributes_count) {
        this.attributes_count = attributes_count;
    }

    public List<Attribute_Info> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute_Info> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String state = "access_flags=" + transMethodAccessFlags(access_flags) + ",";
        state += "name_index=" + name_index + ",";
        state += "descriptor_index=" + descriptor_index + ",";
        state += "attributes_count=" + attributes_count + "\n";
        for (int i = 0; i < attributes_count; i++) {
            state += "\t\tattributes #" + i + ": " + attributes.get(i) + "\n";
        }
        return state;
    }

    public static List<Method_Access_Flag> transMethodAccessFlags(int access_flags) {
        List<Method_Access_Flag> acc_flags = new ArrayList<Method_Access_Flag>();
        if (access_flags >= 0x1000) {
            acc_flags.add(Method_Access_Flag.ACC_SYNTHETIC);
            access_flags -= 0x1000;
        }
        if (access_flags >= 0x0800) {
            acc_flags.add(Method_Access_Flag.ACC_STRICT);
            access_flags -= 0x0800;
        }
        if (access_flags >= 0x0400) {
            acc_flags.add(Method_Access_Flag.ACC_ABSTRACT);
            access_flags -= 0x0400;
        }
        if (access_flags >= 0x0100) {
            acc_flags.add(Method_Access_Flag.ACC_NATIVE);
            access_flags -= 0x0100;
        }
        if (access_flags >= 0x0080) {
            acc_flags.add(Method_Access_Flag.ACC_VARARGS);
            access_flags -= 0x0080;
        }
        if (access_flags >= 0x0040) {
            acc_flags.add(Method_Access_Flag.ACC_BRIDGE);
            access_flags -= 0x0040;
        }
        if (access_flags >= 0x0020) {
            acc_flags.add(Method_Access_Flag.ACC_SYNCHRONIZED);
            access_flags -= 0x0020;
        }
        if (access_flags >= 0x0010) {
            acc_flags.add(Method_Access_Flag.ACC_FINAL);
            access_flags -= 0x0010;
        }
        if (access_flags >= 0x0008) {
            acc_flags.add(Method_Access_Flag.ACC_STATIC);
            access_flags -= 0x0008;
        }
        if (access_flags >= 0x0004) {
            acc_flags.add(Method_Access_Flag.ACC_PROTECTED);
            access_flags -= 0x0004;
        }
        if (access_flags >= 0x0002) {
            acc_flags.add(Method_Access_Flag.ACC_PRIVATE);
            access_flags -= 0x0002;
        }
        if (access_flags >= 0x0001) {
            acc_flags.add(Method_Access_Flag.ACC_PUBLIC);
            access_flags -= 0x0001;
        }
        return acc_flags;
    }
}

