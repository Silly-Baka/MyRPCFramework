package study.studyProxy;

/**
 * Date: 2022/5/26
 * Time: 20:21
 *
 * @Author SillyBaka
 * Description：
 */
public class Student implements Person{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        System.out.println(name+"上交999块钱");
    }
}