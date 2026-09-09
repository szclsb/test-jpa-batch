package ch.szclsb.test.jpabatch.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SourceIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        var targetEntity = (TargetEntity) object;
        return targetEntity.getSourceId();
    }
}
