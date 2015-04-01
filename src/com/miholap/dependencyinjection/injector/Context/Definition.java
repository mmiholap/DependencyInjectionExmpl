package com.miholap.dependencyinjection.injector.Context;

import com.miholap.dependencyinjection.injector.scopes.Scope;
import com.miholap.dependencyinjection.injector.scopes.SingletonScope;

import java.util.Map;

public class Definition<T> {
    private Class<? extends T> objectClass;
    private Map<String,Property> properties;
    private Scope scope = new SingletonScope();

    public Class<? extends T> getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Class<? extends T> objectClass) {
        this.objectClass = objectClass;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public class Property {
        private String value;
        private ValueKinds kind;

        public Property() {
        }

        public Property(String value, ValueKinds kind) {
            this.value = value;
            this.kind = kind;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Property property = (Property) o;

            if (kind != property.kind) return false;
            if (!value.equals(property.value)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = value.hashCode();
            result = 31 * result + kind.hashCode();
            return result;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public ValueKinds getKind() {
            return kind;
        }

        public void setKind(ValueKinds kind) {
            this.kind = kind;
        }

    }
}
