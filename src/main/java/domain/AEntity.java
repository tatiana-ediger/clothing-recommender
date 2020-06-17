package domain;

public abstract class AEntity {

    //    @GeneratedValue
    private Long id;

    protected AEntity() {
    }

    AEntity(long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AEntity{" +
                "id=" + this.id +
                '}';
    }
}

