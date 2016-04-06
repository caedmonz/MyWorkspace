package qunar.dev.task3.classfile.attributepool.stackmapclasses;

import java.io.InvalidClassException;

enum Verification_Type {
    TOP, INTEGER, FLOAT, LONG, DOUBLE, NULL, UNINITIALIZEDTHIS, OBJECT, UNINITIALIZED
}

public class Verification_Type_Info {
    Verification_Type tag;

    public Verification_Type getTag() {
        return tag;
    }

    public void setTag(Verification_Type tag) {
        this.tag = tag;
    }

    public void setTag(int tag) throws InvalidClassException {
        switch (tag) {
        case 0:
            setTag(Verification_Type.TOP);
            break;
        case 1:
            setTag(Verification_Type.INTEGER);
            break;
        case 2:
            setTag(Verification_Type.FLOAT);
            break;
        case 3:
            setTag(Verification_Type.DOUBLE);
            break;
        case 4:
            setTag(Verification_Type.LONG);
            break;
        case 5:
            setTag(Verification_Type.NULL);
            break;
        case 6:
            setTag(Verification_Type.UNINITIALIZEDTHIS);
            break;
        case 7:
            setTag(Verification_Type.OBJECT);
            break;
        case 8:
            setTag(Verification_Type.UNINITIALIZED);
            break;
        default:
            throw new InvalidClassException("[tag=" + tag + "]No such Verification Type");
        }
    }

    @Override
    public String toString() {
        return "Verification_Type = " + tag;
    }
}
