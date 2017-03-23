package org.jsche.common.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jsche.common.validation.constraints.CustomPattern;
import org.jsche.common.validation.constraints.NotEmpty;
import org.jsche.common.validation.constraints.Range;

public class Test {
    @NotEmpty()
    private String name;
    @Range(min=20,max=30)
    private int age;
    @Range(min=2,max=10)
    private String addr;
    @CustomPattern(regex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public static void main(String[] args) {
        Test test = new Test();
        test.setAge(80);
//        test.setAddr("s");
        test.setEmail("my1@aa.cc");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Test>> set = validator.validate(test);
        for (ConstraintViolation<Test> violation : set) {
            System.out.println(violation.getInvalidValue() + violation.getMessage());
            
        }
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    
}
