package flab.docdoc.common.typeHandler;

import flab.docdoc.review.domain.Star;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StarTypeHandler<E extends Enum<E>> implements TypeHandler<Star> {

    private Class<E> type;

    public StarTypeHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }


    @Override
    public void setParameter(PreparedStatement ps, int i, Star star, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, star.getValue());
    }

    @Override
    public Star getResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return getStar(value);
    }

    @Override
    public Star getResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return getStar(value);
    }

    @Override
    public Star getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return getStar(value);
    }

    private Star getStar(int value) {
        try {
            Star[] enumConstants = (Star[])type.getEnumConstants();
            for (Star star : enumConstants) {
                if (star.getValue() == value) {
                    return star;
                }
            }
            return null;
        } catch (Exception exception) {
            throw new TypeException("Can't make enum object '" + type + "'", exception);
        }
    }
}
