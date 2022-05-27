package study.studyBuilder;

import lombok.Data;

/**
 * Date: 2022/5/26
 * Time: 23:32
 *
 * @Author SillyBaka
 * Description：
 **/
@Data
public class User {
    private String name;
    private Integer id;
    private Integer age;

    public User(Builder builder){
        this.name = builder.name;
        this.id = builder.id;
        this.age = builder.age;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private String name;
        private Integer id;
        private Integer age;

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder id(Integer id){
            this.id = id;
            return this;
        }
        public Builder age(Integer age){
            this.age = age;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

}
