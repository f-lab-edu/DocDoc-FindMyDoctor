package flab.docdoc.common.util;

import flab.docdoc.hospitalSubInfo.domain.Subject;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static flab.docdoc.hospitalSubInfo.domain.Subject.toEnum;


public class CodeEnumTypeHandler<E extends Enum<E>> implements TypeHandler<String> {

    private Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
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
