package flab.docdoc.review.domain;

import flab.docdoc.common.typeHandler.CodeEnum;
import flab.docdoc.common.typeHandler.StarTypeHandler;
import lombok.Getter;
import org.apache.ibatis.type.MappedTypes;

@Getter
public enum Star implements CodeEnum {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    Star(int value) {
        this.value = value;
    }


    @Override
    public Integer getCode() {
        return null;
    }

    @MappedTypes(Star.class)
    public static class TypeHandler extends StarTypeHandler<Star> {
        public TypeHandler() {
            super(Star.class);
        }
    }
}
