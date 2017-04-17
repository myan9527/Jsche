package org.jsche.entity;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/4/17.
 */
public enum TaskType {
    FAMILY_ISSUE("Family Issue"),
    SELF_IMPROVEMENT("Self Improvement"),
    SOCIAL_ACTIVITY("Social Activity"),
    WORK_TASK("Work Staff"),
    OTHER_ISSUE("Other Issue");

    private final String typeName;

    TaskType(String name) {
        this.typeName = name;
    }

    public String getTypeName() {
        return typeName;
    }
}
