package domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import java.util.Objects;

public abstract class AEntity {

    @GeneratedValue
    private Long id;

    protected AEntity() {
    }

    AEntity(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof AEntity)) return false;
        AEntity aEntity = (AEntity) o;
        return Objects.equals(this.getId(), aEntity.getId());
    }

    public Long getId() {
        return this.id;
    }
}

