package qunar.dev.task3;

import qunar.dev.task3.classfile.*;
import qunar.dev.task3.classfile.attributepool.Code;
import qunar.dev.task3.classfile.attributepool.LocalVariableTable;
import qunar.dev.task3.classfile.constantpool.*;

import java.io.IOException;
import java.util.*;

/*
 * ClassFileAnalysis convert class file to a strut, and offer some more methods
 * to user for friendly using.
 *
 * @author ZhangQinghua
 *
 * @version 1.0
 *
 * @since 2016.01.24
 */
public class ClassFileAnalysis {

    private ClassFile classFile;

    public String fileName;

    public ClassFileAnalysis(String fileName) throws IOException {
        this.fileName = fileName;
        ClassFileUtil classFileUtil = new ClassFileUtil(fileName);
        classFile = classFileUtil.getClassFile();
    }

    public ClassFile getClassFile() {
        return classFile;
    }

    public void setClassFile(ClassFile classFile) {
        this.classFile = classFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<String> getImportClassNames(){
        List<String> classNames = getAllClassName();
        Set<String> importClassNames = new HashSet<String>();
        for(String className : classNames){
            if(isBaseClass(className)){
                continue;
            }
            importClassNames.add(dealClassName(className));
        }
        return importClassNames;
    }

    private boolean isBaseClass(String name){
        if("BCDFIJSZ".contains(name)){
            return true;
        }
        return false;
    }

    private String dealClassName(String name){
        if(name.startsWith("[") || name.startsWith("L")){
            return dealClassName(name.substring(1));
        }
        if(name.endsWith(";")){
            return dealClassName(name.substring(0, name.length() - 1));
        }
        return name;
    }

    public List<String> getAllClassName() {
        Set<String> classNames = getClassNamesFromAttributeList(classFile.getAttributes());
        for (int i = 0; i < classFile.getFields_count(); i++) {
            Field_Info field = classFile.getFields().get(i);
            List<Attribute_Info> infos = field.getAttributes();
            if (!infos.isEmpty()) {
                Set<String> classNameField = getClassNamesFromAttributeList(infos);
                classNames.addAll(classNameField);
            }
            classNames.addAll(getClassName(field.getDescriptor_index(), true));
        }
        for (int i = 0; i < classFile.getMethods_count(); i++) {
            List<Attribute_Info> infos = classFile.getMethods().get(i).getAttributes();
            if (!infos.isEmpty()) {
                Set<String> classNameMethod = getClassNamesFromAttributeList(infos);
                classNames.addAll(classNameMethod);
            }
        }

        // Attention!!!
        // const pool traverse
        for (int i = 1; i <= classFile.getConst_pool_count() - 1; i++) {
            classNames.addAll(getClassName(i, false));
        }
        // Here is what i imagine not reliable

        List<String> names = new ArrayList<String>();
        names.addAll(classNames);
        return names;
    }

    private Set<String> getClassNamesFromAttributeList(List<Attribute_Info> attributes) {
        Set<String> classNames = new HashSet<String>();
        for (Attribute_Info attribute : attributes) {
            Set<String> className = getClassNamesFromAttribute(attribute);
            classNames.addAll(className);
        }
        return classNames;
    }

    private Set<String> getClassNamesFromAttribute(Attribute_Info attribute) {
        Set<String> classNames = new HashSet<String>();
        Attribute_Tag tag = attribute.getAttribute_name_index();
        if (tag == Attribute_Tag.CODE) {
            Code table = (Code) attribute;
            for (int i = 0; i < table.getAttributes_count(); i++) {
                Set<String> names = getClassNamesFromAttributeList(Arrays.asList(table.getAttributes()[i]));
                classNames.addAll(names);
            }
        }
        if (tag == Attribute_Tag.LOCALVARIABLETABLE) {
            LocalVariableTable table = (LocalVariableTable) attribute;
            for (int i = 0; i < table.getLocal_variable_table_length(); i++) {
                int descriptor_index = table.getElements()[i].getDescriptor_index();
                classNames.addAll(getClassName(descriptor_index, true));
            }
        }
        // TODO
        return classNames;
    }

    private List<String> getClassName(int descriptor_index, boolean utf8_flag) {
        List<String> className = new ArrayList<String>();
        ConstantPool_Info cpInfo = classFile.getConst_pool().get(descriptor_index - 1);
        ConstantPool_Tag tag = cpInfo.getTag();
        if (tag == ConstantPool_Tag.CONSTANT_UTF8 && utf8_flag) {
            Constant_Utf8_Info info = (Constant_Utf8_Info) cpInfo;
            className.add(new String(info.getBytes()));
        }
        if (tag == ConstantPool_Tag.CONSTANT_CLASS) {
            Constant_Class_Info info = (Constant_Class_Info) cpInfo;
            className.addAll(getClassName(info.getName_index(), true));
        }
        if (tag == ConstantPool_Tag.CONSTANT_METHODREF) {
            Constant_Methodref_Info info = (Constant_Methodref_Info) cpInfo;
            className.addAll(getClassName(info.getClass_index(), true));
            className.addAll(getClassName(info.getName_and_type_index(), true));
        }
        if (tag == ConstantPool_Tag.CONSTANT_FIELDREF) {
            Constant_Fieldref_Info info = (Constant_Fieldref_Info) cpInfo;
            className.addAll(getClassName(info.getClass_index(), true));
            className.addAll(getClassName(info.getName_and_type_index(), true));
        }
        if (tag == ConstantPool_Tag.CONSTANT_NAMEANDTYPE) {
            //I don't know whether it is needed, and so is other ref class TODO
            //Constant_NameAndType_Info info = (Constant_NameAndType_Info) cpInfo;
            //className.addAll(getClassName(info.getDescriptor_index(), true));
        }
        if (tag == ConstantPool_Tag.CONSTANT_DOUBLE || tag == ConstantPool_Tag.CONSTANT_LONG
                || tag == ConstantPool_Tag.CONSTANT_INTEGER || tag == ConstantPool_Tag.CONSTANT_FLOAT
                || tag == ConstantPool_Tag.CONSTANT_STRING) {

        }
        if (tag == ConstantPool_Tag.CONSTANT_INTERFACEMETHODREF) {
            Constant_InterfaceMethodref_Info info = (Constant_InterfaceMethodref_Info) cpInfo;
            className.addAll(getClassName(info.getClass_index(), true));
            className.addAll(getClassName(info.getName_and_type_index(), true));
        }
        if (tag == ConstantPool_Tag.CONSTANT_INVOKEDYNAMIC) {

        }
        if (tag == ConstantPool_Tag.CONSTANT_METHODHANDLE) {

        }
        if (tag == ConstantPool_Tag.CONSTANT_METHODTYPE) {
            Constant_MethodType_Info info = (Constant_MethodType_Info) cpInfo;
            className.addAll(getClassName(info.getDescriptor_index(), true));
        }
        // TODO
        return className;
    }
}
