package dev.odaat.Entity.Enums;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;

public enum TaskStatus implements JdbcType{
    TO_DO, IN_PROGRESS, COMPLETED;

    @Override
    public int getJdbcTypeCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJdbcTypeCode'");
    }

    @Override
    public <X> ValueBinder<X> getBinder(JavaType<X> javaType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBinder'");
    }

    @Override
    public <X> ValueExtractor<X> getExtractor(JavaType<X> javaType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExtractor'");
    }
}
