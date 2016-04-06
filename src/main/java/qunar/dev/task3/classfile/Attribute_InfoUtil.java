package qunar.dev.task3.classfile;

import qunar.dev.task3.classfile.attributepool.*;
import qunar.dev.task3.classfile.attributepool.annotations.*;
import qunar.dev.task3.classfile.attributepool.stackmapclasses.*;
import qunar.dev.task3.classfile.constantpool.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.List;


/*
 * Utility class converting bytes to different attributes.
 * 
 * <p>Parse corresponding bytes of class file to a class extends from
 * Attribute_Info as attributes has different formats according to attribute
 * name
 * 
 * @author ZhangQinghua
 * 
 * @version 1.0
 * 
 * @since 2016.01.24
 * 
 * @see ConstantPool_InfoUtil
 */
public class Attribute_InfoUtil extends Attribute_Info {

    private DataInputStream dataInput;

    private List<ConstantPool_Info> const_pool;

    public Attribute_InfoUtil(DataInputStream dataInput, List<ConstantPool_Info> const_pool) {
        this.dataInput = dataInput;
        this.const_pool = const_pool;
    }

    public Attribute_Info getAttributeInfo() throws IOException {
        int attribute_name_index = dataInput.readUnsignedShort();
        int attribute_length = dataInput.readInt();
        if (attribute_length < 0) {
            throw new InvalidClassException("Attribute length is too big or not parsed correctly");
        }
        Constant_Utf8_Info utf8Info = (Constant_Utf8_Info) const_pool.get(attribute_name_index - 1);
        String attriName = new String(utf8Info.getBytes());

        if ("ConstantValue".equals(attriName)) {
            ConstantValue attributeinfo = new ConstantValue();
            int constant_value_index = dataInput.readUnsignedShort();
            attributeinfo.setAttribute_name_index(Attribute_Tag.CONSTANT_VALUE);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setConstant_value_index(constant_value_index);
            return attributeinfo;
        }

        if ("Code".equals(attriName)) {
            int max_stack = dataInput.readUnsignedShort();
            int max_locals = dataInput.readUnsignedShort();
            int code_length = dataInput.readInt();
            if (code_length < 0) {
                throw new InvalidClassException("Length is too big or not parsed correctly");
            }
            byte[] code = new byte[code_length];
            dataInput.readFully(code);
            int exception_table_length = dataInput.readUnsignedShort();
            if (exception_table_length < 0) {
                throw new InvalidClassException("Length is too big or not parsed correctly");
            }
            Code_Element[] code_exception_table_element = new Code_Element[exception_table_length];
            for (int i = 0; i < exception_table_length; i++) {
                int start_pc = dataInput.readUnsignedShort();
                int end_pc = dataInput.readUnsignedShort();
                int handler_pc = dataInput.readUnsignedShort();
                int catch_type = dataInput.readUnsignedShort();
                code_exception_table_element[i].setStart_pc(start_pc);
                code_exception_table_element[i].setEnd_pc(end_pc);
                code_exception_table_element[i].setHandler_pc(handler_pc);
                code_exception_table_element[i].setCatch_type(catch_type);
            }
            int attribute_count = dataInput.readUnsignedShort();
            Attribute_Info[] attributes = new Attribute_Info[attribute_count];
            for (int i = 0; i < attribute_count; i++) {
                attributes[i] = getAttributeInfo();
            }
            Code attributeinfo = new Code();
            attributeinfo.setAttribute_name_index(Attribute_Tag.CODE);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setMax_stack(max_stack);
            attributeinfo.setMax_locals(max_locals);
            attributeinfo.setCode_length(code_length);
            attributeinfo.setCode(code);
            attributeinfo.setException_table_length(exception_table_length);
            attributeinfo.setException_table(code_exception_table_element);
            attributeinfo.setAttributes_count(attribute_count);
            attributeinfo.setAttributes(attributes);
            return attributeinfo;
        }

        if ("StackMapTable".equals(attriName)) {
            StackMapTable attributeinfo = new StackMapTable();
            int number_of_entries = dataInput.readUnsignedShort();
            attributeinfo.setAttribute_name_index(Attribute_Tag.STACKMAPTABLE);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setNumber_of_entries(number_of_entries);
            Stack_Map_Frame[] entries = new Stack_Map_Frame[number_of_entries];
            for (int i = 0; i < number_of_entries; i++) {
                entries[i] = getFrame();
            }
            attributeinfo.setEntries(entries);
            return attributeinfo;
        }

        if ("Exceptions".equals(attriName)) {
            Exceptions attributeinfo = new Exceptions();
            int number_of_exceptions = dataInput.readUnsignedShort();
            int[] exception_index_table = new int[number_of_exceptions];
            for (int i = 0; i < number_of_exceptions; i++) {
                exception_index_table[i] = dataInput.readUnsignedShort();
            }
            attributeinfo.setAttribute_name_index(Attribute_Tag.EXCEPTIONS);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setNumber_of_exceptions(number_of_exceptions);
            attributeinfo.setException_index_table(exception_index_table);
            return attributeinfo;
        }

        if ("InnerClasses".equals(attriName)) {
            InnerClasses attributeinfo = new InnerClasses();
            attributeinfo.setAttribute_name_index(Attribute_Tag.INNERCLASSES);
            attributeinfo.setAttribute_length(attribute_length);
            int number_of_classes = dataInput.readUnsignedShort();
            attributeinfo.setNumber_of_classes(number_of_classes);
            InnerClasses_Element[] classes = new InnerClasses_Element[number_of_classes];
            for (int i = 0; i < number_of_classes; i++) {
                int inner_info_index = dataInput.readUnsignedShort();
                int outer_info_index = dataInput.readUnsignedShort();
                int inner_name_index = dataInput.readUnsignedShort();
                int inner_access_flags = dataInput.readUnsignedShort();
                classes[i].setInner_class_info_index(inner_info_index);
                classes[i].setOuter_class_info_index(outer_info_index);
                classes[i].setInner_name_index(inner_name_index);
                classes[i].setInner_class_access_flags(inner_access_flags);
            }
            attributeinfo.setClasses(classes);
            return attributeinfo;
        }

        if ("EnclosingMethod".equals(attriName)) {
            EnclosingMethod attributeinfo = new EnclosingMethod();
            int class_index = dataInput.readUnsignedShort();
            int method_index = dataInput.readUnsignedShort();
            attributeinfo.setAttribute_name_index(Attribute_Tag.ENCLOSINGMETHOD);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setClass_index(class_index);
            attributeinfo.setMethod_index(method_index);
            return attributeinfo;
        }

        if ("Synthetic".equals(attriName)) {
            Synthetic attributeinfo = new Synthetic();
            attributeinfo.setAttribute_name_index(Attribute_Tag.SYNTHETIC);
            attributeinfo.setAttribute_length(attribute_length);
            return attributeinfo;
        }

        if ("Signature".equals(attriName)) {
            Signature attributeinfo = new Signature();
            int signature_index = dataInput.readUnsignedShort();
            attributeinfo.setAttribute_name_index(Attribute_Tag.SIGNATURE);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setSignature_index(signature_index);
            return attributeinfo;
        }

        if ("SourceFile".equals(attriName)) {
            SourceFile attributeinfo = new SourceFile();
            int sourcefile_index = dataInput.readUnsignedShort();
            attributeinfo.setAttribute_name_index(Attribute_Tag.SOURCEFILE);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setSourcefile_index(sourcefile_index);
            return attributeinfo;
        }

        if ("SourceDebugExtension".equals(attriName)) {
            SourceDebugExtension attributeinfo = new SourceDebugExtension();
            byte[] debug_extension = new byte[attribute_length];
            dataInput.readFully(debug_extension);
            attributeinfo.setAttribute_name_index(Attribute_Tag.SOURCEDEBUGEXTENSION);
            attributeinfo.setAttribute_length(attribute_length);
            attributeinfo.setDebug_extension(debug_extension);
            return attributeinfo;
        }

        if ("LineNumberTable".equals(attriName)) {
            LineNumberTable attributeinfo = new LineNumberTable();
            attributeinfo.setAttribute_name_index(Attribute_Tag.LINENUMBERTABLE);
            attributeinfo.setAttribute_length(attribute_length);
            int line_number_table_length = dataInput.readUnsignedShort();
            attributeinfo.setLine_number_table_length(line_number_table_length);
            LineNumberTable_Element[] elements = new LineNumberTable_Element[line_number_table_length];
            for (int i = 0; i < line_number_table_length; i++) {
                elements[i] = new LineNumberTable_Element();
                int start_pc = dataInput.readUnsignedShort();
                int line_number = dataInput.readUnsignedShort();
                elements[i].setStart_pc(start_pc);
                elements[i].setLine_number(line_number);
            }
            attributeinfo.setElements(elements);
            return attributeinfo;
        }

        if ("LocalVariableTable".equals(attriName)) {
            LocalVariableTable attributeinfo = new LocalVariableTable();
            attributeinfo.setAttribute_name_index(Attribute_Tag.LOCALVARIABLETABLE);
            attributeinfo.setAttribute_length(attribute_length);
            int local_variable_table_length = dataInput.readUnsignedShort();
            attributeinfo.setLocal_variable_table_length(local_variable_table_length);
            LocalVariableTable_Element[] elements = new LocalVariableTable_Element[local_variable_table_length];
            for (int i = 0; i < local_variable_table_length; i++) {
                elements[i] = new LocalVariableTable_Element();
                int start_pc = dataInput.readUnsignedShort();
                int length = dataInput.readUnsignedShort();
                int name_index = dataInput.readUnsignedShort();
                int descriptor_index = dataInput.readUnsignedShort();
                int index = dataInput.readUnsignedShort();
                elements[i].setStart_pc(start_pc);
                elements[i].setLength(length);
                elements[i].setName_index(name_index);
                elements[i].setDescriptor_index(descriptor_index);
                elements[i].setIndex(index);
            }
            attributeinfo.setElements(elements);
            return attributeinfo;
        }

        if ("LocalVariableTypeTable".equals(attriName)) {
            LocalVariableTypeTable attributeinfo = new LocalVariableTypeTable();
            attributeinfo.setAttribute_name_index(Attribute_Tag.LOCALVARIABLETYPETABLE);
            attributeinfo.setAttribute_length(attribute_length);
            int local_variable_type_table_length = dataInput.readUnsignedShort();
            attributeinfo.setLocal_variable_type_table_length(local_variable_type_table_length);
            LocalVariableTypeTable_Element[] elements = new LocalVariableTypeTable_Element[local_variable_type_table_length];
            for (int i = 0; i < local_variable_type_table_length; i++) {
                elements[i] = new LocalVariableTypeTable_Element();
                int start_pc = dataInput.readUnsignedShort();
                int length = dataInput.readUnsignedShort();
                int name_index = dataInput.readUnsignedShort();
                int signature_index = dataInput.readUnsignedShort();
                int index = dataInput.readUnsignedShort();
                elements[i].setStart_pc(start_pc);
                elements[i].setLength(length);
                elements[i].setName_index(name_index);
                elements[i].setSignatuere_index(signature_index);
                elements[i].setIndex(index);
            }
            attributeinfo.setElements(elements);
            return attributeinfo;
        }

        if ("Deprecated".equals(attriName)) {
            Deprecated_Attr attributeinfo = new Deprecated_Attr();
            attributeinfo.setAttribute_name_index(Attribute_Tag.DEPRECATED);
            attributeinfo.setAttribute_length(attribute_length);
            return attributeinfo;
        }

        if ("RuntimeVisibleAnnotations".equals(attriName)) {
            RuntimeVisibleAnnotations attributeinfo = new RuntimeVisibleAnnotations();
            attributeinfo.setAttribute_name_index(Attribute_Tag.RUNTIMEVISIBLEANNOTATIONS);
            attributeinfo.setAttribute_length(attribute_length);
            int num_annotations = dataInput.readUnsignedShort();
            attributeinfo.setNum_annotations(num_annotations);
            AnnotationValue[] annotations = new AnnotationValue[num_annotations];
            for (int i = 0; i < num_annotations; i++) {
                annotations[i] = getAnnotationValue();
            }
            attributeinfo.setAnnotations(annotations);
            return attributeinfo;
        }

        if ("RuntimeInvisibleAnnotations".equals(attriName)) {
            RuntimeInvisibleAnnotations attributeinfo = new RuntimeInvisibleAnnotations();
            attributeinfo.setAttribute_name_index(Attribute_Tag.RUNTIMEINVISIBLEANNOTATIONS);
            attributeinfo.setAttribute_length(attribute_length);
            int num_annotations = dataInput.readUnsignedShort();
            attributeinfo.setNum_annotations(num_annotations);
            AnnotationValue[] annotations = new AnnotationValue[num_annotations];
            for (int i = 0; i < num_annotations; i++) {
                annotations[i] = getAnnotationValue();
            }
            attributeinfo.setAnnotations(annotations);
            return attributeinfo;
        }

        if ("RuntimeVisibleParameterAnnotations".equals(attriName)) {
            RuntimeVisibleParameterAnnotations attributeinfo = new RuntimeVisibleParameterAnnotations();
            attributeinfo.setAttribute_name_index(Attribute_Tag.RUNTIMEVISIBLEPARAMETERANNOTATIONS);
            attributeinfo.setAttribute_length(attribute_length);
            int num_parameters = dataInput.readUnsignedByte();
            ParameterAnnotation[] parameter_annotations = new ParameterAnnotation[num_parameters];
            for (int i = 0; i < num_parameters; i++) {
                parameter_annotations[i] = getParameterAnnotation();
            }
            attributeinfo.setNum_parameters(num_parameters);
            attributeinfo.setParameter_annotations(parameter_annotations);
            return attributeinfo;
        }

        if ("RuntimeInvisibleParameterAnnotations".equals(attriName)) {
            RuntimeInvisibleParameterAnnotations attributeinfo = new RuntimeInvisibleParameterAnnotations();
            attributeinfo.setAttribute_name_index(Attribute_Tag.RUNTIMEINVISIBLEPARAMETERANNOTATIONS);
            attributeinfo.setAttribute_length(attribute_length);
            int num_parameters = dataInput.readUnsignedByte();
            ParameterAnnotation[] parameter_annotations = new ParameterAnnotation[num_parameters];
            for (int i = 0; i < num_parameters; i++) {
                parameter_annotations[i] = getParameterAnnotation();
            }
            attributeinfo.setNum_parameters(num_parameters);
            attributeinfo.setParameter_annotations(parameter_annotations);
            return attributeinfo;
        }

        if ("AnnotationDefault".equals(attriName)) {
            AnnotationDefault attributeinfo = new AnnotationDefault();
            attributeinfo.setAttribute_name_index(Attribute_Tag.ANNOTATIONDEFAULT);
            attributeinfo.setAttribute_length(attribute_length);
            ElementValue default_value = getElementValue();
            attributeinfo.setDefault_value(default_value);
            return attributeinfo;
        }

        if ("BootstrapMethods".equals(attriName)) {
            BootstrapMethods attributeinfo = new BootstrapMethods();
            attributeinfo.setAttribute_name_index(Attribute_Tag.BOOTSTRAPMETHODS);
            attributeinfo.setAttribute_length(attribute_length);
            int num_bootstrap_methods = dataInput.readUnsignedShort();
            attributeinfo.setNum_bootstrap_methods(num_bootstrap_methods);
            BootstrapMethods_Element[] elements = new BootstrapMethods_Element[num_bootstrap_methods];
            for (int i = 0; i < num_bootstrap_methods; i++) {
                elements[i] = new BootstrapMethods_Element();
                int bootstrap_method_ref = dataInput.readUnsignedShort();
                int num_bootstrap_arguments = dataInput.readUnsignedShort();
                int[] bootstrap_arguments = new int[num_bootstrap_arguments];
                for (int k = 0; k < num_bootstrap_arguments; k++) {
                    bootstrap_arguments[k] = dataInput.readUnsignedShort();
                }
                elements[i].setBootstrap_method_ref(bootstrap_method_ref);
                elements[i].setNum_bootstrap_arguments(num_bootstrap_arguments);
                elements[i].setBootstrap_arguments(bootstrap_arguments);
            }
            attributeinfo.setElements(elements);
            return attributeinfo;
        }

        return null;
    }

    private Stack_Map_Frame getFrame() throws IOException {
        int frame_type = dataInput.readUnsignedByte();
        if (frame_type <= 63) {
            Same_Frame frame = new Same_Frame();
            frame.setFrame_type(frame_type);
            return frame;
        }
        if (frame_type >= 64 && frame_type <= 127) {
            Same_Locals_1_Stack_Item_Frame frame = new Same_Locals_1_Stack_Item_Frame();
            frame.setFrame_type(frame_type);
            Verification_Type_Info verificationInfo = getVerificationInfo();
            frame.setStack(verificationInfo);
            return frame;
        }
        if (frame_type == 247) {
            Same_Locals_1_Stack_Item_Frame_Extended frame = new Same_Locals_1_Stack_Item_Frame_Extended();
            frame.setFrame_type(frame_type);
            int offset_delta = dataInput.readUnsignedShort();
            frame.setOffset_delta(offset_delta);
            Verification_Type_Info verificationInfo = getVerificationInfo();
            frame.setStack(verificationInfo);
            return frame;
        }

        if (frame_type >= 248 && frame_type <= 250) {
            Chop_Frame frame = new Chop_Frame();
            frame.setFrame_type(frame_type);
            int offset_delta = dataInput.readUnsignedShort();
            frame.setOffset_delta(offset_delta);
            return frame;
        }

        if (frame_type == 251) {
            Same_Frame_Extended frame = new Same_Frame_Extended();
            frame.setFrame_type(frame_type);
            int offset_delta = dataInput.readUnsignedShort();
            frame.setOffset_delta(offset_delta);
            return frame;
        }

        if (frame_type >= 252) {
            Append_Frame frame = new Append_Frame();
            frame.setFrame_type(frame_type);
            int offset_delta = dataInput.readUnsignedShort();
            frame.setOffset_delta(offset_delta);
            Verification_Type_Info[] verificationInfo = new Verification_Type_Info[frame_type - 251];
            for (int i = 0; i < frame_type - 251; i++) {
                verificationInfo[i] = new Verification_Type_Info();
                verificationInfo[i] = getVerificationInfo();
            }
            frame.setLength(frame_type - 251);
            frame.setLocals(verificationInfo);
            return frame;
        }

        return null;
    }

    private Verification_Type_Info getVerificationInfo() throws IOException {
        int tag = dataInput.readUnsignedByte();
        if (tag <= 6) {
            Verification_Type_Info verificationType = new Verification_Type_Info();
            verificationType.setTag(tag);
            return verificationType;
        }

        if (tag == 7) {
            Object_Variable_Info verificationType = new Object_Variable_Info();
            verificationType.setTag(tag);
            int cpool_index = dataInput.readUnsignedShort();
            verificationType.setCpool_index(cpool_index);
            return verificationType;
        }

        if (tag >= 8) {
            Uninitialized_Variable_Info verificationType = new Uninitialized_Variable_Info();
            verificationType.setTag(tag);
            int offset = dataInput.readUnsignedShort();
            verificationType.setOffset(offset);
            return verificationType;
        }

        return null;
    }

    private ParameterAnnotation getParameterAnnotation() throws IOException {
        ParameterAnnotation parameter_annotation = new ParameterAnnotation();
        int num_annotations = dataInput.readUnsignedShort();
        AnnotationValue[] annotations = new AnnotationValue[num_annotations];
        for (int i = 0; i < num_annotations; i++) {
            annotations[i] = getAnnotationValue();
        }
        parameter_annotation.setNum_annotations(num_annotations);
        parameter_annotation.setAnnotations(annotations);
        return parameter_annotation;
    }

    private AnnotationValue getAnnotationValue() throws IOException {
        AnnotationValue annotation_value = new AnnotationValue();
        int type_index = dataInput.readUnsignedShort();
        int num_element_value_pairs = dataInput.readUnsignedShort();
        AnnotationValue_ElementPairs[] element_value_pairs = new AnnotationValue_ElementPairs[num_element_value_pairs];
        for (int i = 0; i < num_element_value_pairs; i++) {
            element_value_pairs[i] = new AnnotationValue_ElementPairs();
            int element_name_index = dataInput.readUnsignedShort();
            ElementValue value = getElementValue();
            element_value_pairs[i].setElement_value_index(element_name_index);
            element_value_pairs[i].setValue(value);
        }
        annotation_value.setType_index(type_index);
        annotation_value.setNum_element_value_pairs(num_element_value_pairs);
        annotation_value.setElement_value_pairs(element_value_pairs);
        return annotation_value;
    }

    private ElementValue getElementValue() throws IOException {
        char tag = dataInput.readChar();
        if ("BCDFIJSZs".indexOf(tag) != -1) {
            ElementValue_ConstValue value = new ElementValue_ConstValue();
            value.setTag(tag);
            int const_value_index = dataInput.readUnsignedShort();
            value.setConst_value_index(const_value_index);
            return value;
        }

        if (tag == 'e') {
            ElementValue_EnumValue value = new ElementValue_EnumValue();
            value.setTag(tag);
            int type_name_index = dataInput.readUnsignedShort();
            int const_name_index = dataInput.readUnsignedShort();
            value.setType_name_index(type_name_index);
            value.setConst_name_index(const_name_index);
            return value;
        }

        if (tag == 'c') {
            ElementValue_ClassInfo value = new ElementValue_ClassInfo();
            value.setTag(tag);
            int class_info_index = dataInput.readUnsignedShort();
            value.setClass_info_index(class_info_index);
            return value;
        }

        if (tag == '@') {
            ElementValue_AnnotationValue value = new ElementValue_AnnotationValue();
            value.setTag(tag);
            AnnotationValue annotation_value = getAnnotationValue();
            value.setAnnotation_value(annotation_value);
            return value;
        }

        if (tag == '[') {
            ElementValue_ArrayValue value = new ElementValue_ArrayValue();
            value.setTag(tag);
            int num_values = dataInput.readUnsignedShort();
            value.setNum_values(num_values);
            ElementValue[] values = new ElementValue[num_values];
            for (int i = 0; i < num_values; i++) {
                values[i] = getElementValue();
            }
            value.setValues(values);
            return value;
        }

        return null;
    }
}
