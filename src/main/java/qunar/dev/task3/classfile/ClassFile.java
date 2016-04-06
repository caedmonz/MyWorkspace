package qunar.dev.task3.classfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Struct class of class file includes MAGIC number checking, version, constant pool information.
 * Created by Administrator on 2016/4/5.
 */
public class ClassFile {

    public static final int MAGIC = 0xCAFEBABE;

    private int minor_version;

    private int major_version;

    private int const_pool_count;

    private List<ConstantPool_Info> const_pool;

    private int access_flags;

    private int this_class;

    private int super_class;

    private int interfaces_count;

    private List<Integer> interfaces;

    private int fields_count;

    private List<Field_Info> fields;

    private int methods_count;

    private List<Method_Info> methods;

    private int attributes_count;

    private List<Attribute_Info> attributes;

    public int getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(int minor_version) {
        this.minor_version = minor_version;
    }

    public int getMajor_version() {
        return major_version;
    }

    public void setMajor_version(int major_version) {
        this.major_version = major_version;
    }

    public int getConst_pool_count() {
        return const_pool_count;
    }

    public void setConst_pool_count(int const_pool_count) {
        this.const_pool_count = const_pool_count;
    }

    public List<ConstantPool_Info> getConst_pool() {
        return const_pool;
    }

    public void setConst_pool(List<ConstantPool_Info> const_pool) {
        this.const_pool = const_pool;
    }

    public static int getMAGIC() {
        return MAGIC;
    }

    public int getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(int access_flags) {
        this.access_flags = access_flags;
    }

    public List<Attribute_Info> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute_Info> attributes) {
        this.attributes = attributes;
    }

    public int getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(int attributes_count) {
        this.attributes_count = attributes_count;
    }

    public int getMethods_count() {
        return methods_count;
    }

    public void setMethods_count(int methods_count) {
        this.methods_count = methods_count;
    }

    public List<Method_Info> getMethods() {
        return methods;
    }

    public void setMethods(List<Method_Info> methods) {
        this.methods = methods;
    }

    public List<Field_Info> getFields() {
        return fields;
    }

    public void setFields(List<Field_Info> fields) {
        this.fields = fields;
    }

    public int getFields_count() {
        return fields_count;
    }

    public void setFields_count(int fields_count) {
        this.fields_count = fields_count;
    }

    public List<Integer> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Integer> interfaces) {
        this.interfaces = interfaces;
    }

    public int getInterfaces_count() {
        return interfaces_count;
    }

    public void setInterfaces_count(int interfaces_count) {
        this.interfaces_count = interfaces_count;
    }

    public int getThis_class() {
        return this_class;
    }

    public void setThis_class(int this_class) {
        this.this_class = this_class;
    }

    public int getSuper_class() {
        return super_class;
    }

    public void setSuper_class(int super_class) {
        this.super_class = super_class;
    }

    @Override
    public String toString(){
        String state = "Magic: " + Integer.toHexString(MAGIC) + "\n";
        state += "Version: " + major_version + "." + minor_version + "\n";
        state += "const_pool_count: " + const_pool_count + "\n";
        state += "const_pool:\n";
        for(int i = 1; i < const_pool_count; i++){
            state += "\t[cont_pool #" + i + "]: " + const_pool.get(i-1) + "\n";
        }
        state += "access_flags: " + transClassAccessFlags(access_flags) + "\n";
        state += "this_class: " + this_class + "\n";
        state += "super_class: " + super_class + "\n";
        state += "interfaces_count: " + interfaces_count + "\n";
        state += "interfaces:\n";
        for (int i = 0; i < interfaces_count; i++) {
            state += "\t[interfaces #" + i + "]: " + interfaces.get(i) + "\n";
        }
        state += "fields_count: " + fields_count + "\n";
        state += "fields:\n";
        for (int i = 0; i < fields_count; i++) {
            state += "\t[fields #" + i + "]: " + fields.get(i) + "\n";
        }
        state += "methods_count: " + methods_count + "\n";
        state += "methods:\n";
        for (int i = 0; i < methods_count; i++) {
            state += "\t[methods #" + i + "]: " + methods.get(i);
        }
        state += "attributes_count: " + attributes_count + "\n";
        state += "attributes:";
        for (int i = 0; i < attributes_count; i++) {
            state += "\n\t[attributes #" + i + "]: " + attributes.get(i);
        }
        return state;
    }

    private static List<Class_Access_Flag> transClassAccessFlags(int access_flags) {
        List<Class_Access_Flag> acc_flags = new ArrayList<Class_Access_Flag>();
        if (access_flags >= 0x4000) {
            acc_flags.add(Class_Access_Flag.ACC_ENUM);
            access_flags -= 0x4000;
        }
        if (access_flags >= 0x2000) {
            acc_flags.add(Class_Access_Flag.ACC_ANNOTATION);
            access_flags -= 0x2000;
        }
        if (access_flags >= 0x1000) {
            acc_flags.add(Class_Access_Flag.ACC_SYNTHETIC);
            access_flags -= 0x1000;
        }
        if (access_flags >= 0x0400) {
            acc_flags.add(Class_Access_Flag.ACC_ABSTRACT);
            access_flags -= 0x0400;
        }
        if (access_flags >= 0x0200) {
            acc_flags.add(Class_Access_Flag.ACC_INTERFACE);
            access_flags -= 0x0200;
        }
        if (access_flags >= 0x0020) {
            acc_flags.add(Class_Access_Flag.ACC_SUPER);
            access_flags -= 0x0020;
        }
        if (access_flags >= 0x0010) {
            acc_flags.add(Class_Access_Flag.ACC_FINAL);
            access_flags -= 0x0010;
        }
        if (access_flags >= 0x0001) {
            acc_flags.add(Class_Access_Flag.ACC_PUBLIC);
            access_flags -= 0x0001;
        }
        return acc_flags;
    }
}

