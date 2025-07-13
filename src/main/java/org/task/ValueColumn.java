package org.task;

import java.util.Objects;

public class ValueColumn {
    private final String value;
    private final int columnIndex;

    public ValueColumn(String value, int columnIndex) {
        this.value = value;
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueColumn that = (ValueColumn) o;
        return Objects.equals(value, that.value) && Objects.equals(columnIndex, that.columnIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, columnIndex);
    }
}