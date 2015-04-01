package com.miholap.dependencyinjection.injector.Context;

public enum ValueKinds {
    VALUE("value"),REFERENCE("ref");

    private String kind;

    ValueKinds(String kind) {
        this.kind = kind;
    }
}
