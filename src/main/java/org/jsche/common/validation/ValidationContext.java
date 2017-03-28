package org.jsche.common.validation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ValidationContext {
    private Method method;
    private Parameter[] parameters;
    private String[] argNames;
    private Object[] argValues;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public String[] getArgNames() {
        return argNames;
    }

    public void setArgNames(String[] argNames) {
        this.argNames = argNames;
    }

    public Object[] getArgValues() {
        return argValues;
    }

    public void setArgValues(Object[] argValues) {
        this.argValues = argValues;
    }
}
