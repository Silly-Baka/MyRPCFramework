package study.studyBuilder;

/**
 * Date: 2022/5/26
 * Time: 23:36
 *
 * @Author SillyBaka
 * Description：
 **/
public class builderTest {
    public static void main(String[] args) {
        User user = User.builder()
                .name("niubi")
                .id(123)
                .age(999)
                .build();
        System.out.println(user);
    }
}
