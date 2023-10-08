package dev.odaat.Entity.Enums;

import org.apache.ibatis.type.Alias;
import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;

public enum ProgramType implements JdbcType{
    FAMILY, CHORES, JOB, PERSONAL, LEARNING;

    // To be able to cast to JdbcType in mappper xml
    @Override
    public <X> ValueBinder<X> getBinder(JavaType<X> arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBinder'");
    }
    @Override
    public <X> ValueExtractor<X> getExtractor(JavaType<X> arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExtractor'");
    }
    @Override
    public int getJdbcTypeCode() {
        return java.sql.Types.OTHER;
    }
}
