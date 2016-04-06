package qunar.dev.task3.classfile.attributepool.stackmapclasses;

import java.io.InvalidClassException;

enum Stack_Map_Frame_Type {
    SAME, SAME_LOCALS_1_STACK_ITEM, SAME_LOCALS_1_STACK_ITEM_EXTENDED, CHOP, SAME_FRAME_EXTENSION, APPEND
}

public class Stack_Map_Frame {

    private Stack_Map_Frame_Type frame_type;

    public Stack_Map_Frame_Type getFrame_type() {
        return frame_type;
    }

    public void setFrame_type(Stack_Map_Frame_Type frame_type) {
        this.frame_type = frame_type;
    }

    public void setFrame_type(int frame_type) throws InvalidClassException {
        if (frame_type <= 63) {
            setFrame_type(Stack_Map_Frame_Type.SAME);
            return;
        }
        if (frame_type <= 127 && frame_type >= 64) {
            setFrame_type(Stack_Map_Frame_Type.SAME_LOCALS_1_STACK_ITEM);
            return;
        }
        if (frame_type == 247) {
            setFrame_type(Stack_Map_Frame_Type.SAME_LOCALS_1_STACK_ITEM_EXTENDED);
            return;
        }
        if (frame_type <= 250 && frame_type >= 248) {
            setFrame_type(Stack_Map_Frame_Type.CHOP);
            return;
        }
        if (frame_type == 251) {
            setFrame_type(Stack_Map_Frame_Type.SAME_FRAME_EXTENSION);
            return;
        }
        if (frame_type <= 254 || frame_type >= 252) {
            setFrame_type(Stack_Map_Frame_Type.APPEND);
            return;
        }
        throw new InvalidClassException("[frame_type=" + frame_type + "]No such Frame Type");
    }

    @Override
    public String toString() {
        return "frame_type=" + frame_type;
    }
}
