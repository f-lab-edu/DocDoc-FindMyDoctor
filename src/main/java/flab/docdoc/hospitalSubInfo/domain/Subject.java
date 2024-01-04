package flab.docdoc.hospitalSubInfo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import flab.docdoc.common.util.CodeEnum;
import flab.docdoc.common.util.CodeEnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Subject implements CodeEnum {
    ALL("모든과목"),
    FM("가정의학과"),
    IM("내과"),
    CP("병리과"),
    URO("비뇨의학과"),
    OBGY("산부인과"),
    PS("성형외과"),
    PED("소아청소년과"),
    NS("신경외과"),
    OPH("안과"),
    GS("외과"),
    EM("응급의학과"),
    ENT("이비인후과"),
    RM("재활의학과"),
    PSY("정신건강의학과"),
    OS("정형외과"),
    DT("치과"),
    DER("피부과"),
    TKM("한의원");

    private final String lable;

    Subject(String lable) {
        this.lable = lable;
    }

    private String getLable() {
        return this.lable;
    }


    private static final Map<String, Subject> codeLableMap =
            Stream.of(values()).collect(Collectors.toMap(Subject::getLable, e -> e));

    public String getOpenLable() {
        return this.lable;
    }

    @JsonCreator
    public static Subject toEnum(String lable) {
        Subject subject = codeLableMap.get(lable);
        if (subject == null) throw new IllegalArgumentException("진료과목이 없습니다.");
        return subject;
    }

    private static final List<String> lableList =
            Arrays.stream(values()).map(Subject::getLable).collect(Collectors.toList());

    public static List<String> getLableList() {
        return lableList;
    }

    @MappedTypes(Subject.class)
    public static class TypeHandler <E extends Enum<E>> implements org.apache.ibatis.type.TypeHandler<String> {
        private Class<E> type;

        public TypeHandler(Class<E> type) {
            this.type = type;
        }

        @Override
        public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {

        }

        @Override
        public String getResult(ResultSet rs, String columnName) throws SQLException {
            return getCodeEnum(rs.getString(columnName))+  " 1";
        }

        @Override
        public String getResult(ResultSet rs, int columnIndex) throws SQLException {
            return getCodeEnum(rs.getString(columnIndex))+  " 1";
        }

        @Override
        public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
            return getCodeEnum(cs.getString(columnIndex))+  " 1";
        }

        private String getCodeEnum(String code) {
            try {
                return Subject.valueOf(code).getOpenLable();
            } catch (Exception e) {
                throw new TypeException("cnt not");
            }
        }
    }

    @Override
    public String getCode() {
        return this.name() + "1";
    }
}
