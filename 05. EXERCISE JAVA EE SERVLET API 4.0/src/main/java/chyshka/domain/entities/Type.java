package chyshka.domain.entities;

public enum Type {
    FOOD, DOMESTIC, HEALTH, COSMETIC, OTHER;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
