package exceptions.solutions;

/**
 * 【参考答案】Q02_CustomException：自定义异常与异常链
 *
 * 解题思路：
 * 底层抛出具体异常，上层包装并保留 cause，调用者既能得到业务含义，又不丢失根因。
 *
 * 运行：java -cp out exceptions.solutions.A02_CustomException
 */
public class A02_CustomException {
    static class ValidationException extends Exception {
        private final String field;
        ValidationException(String field, String message) {
            super(field + ": " + message);
            this.field = field;
        }
        String getField() { return field; }
    }

    static void validateUser(String name, int age) throws ValidationException {
        if (name == null || name.isBlank()) throw new ValidationException("name", "不能为空");
        if (age < 0 || age > 150) throw new ValidationException("age", "必须在 0~150 之间，实际 " + age);
    }

    static void registerUser(String name, int age) {
        try {
            validateUser(name, age);
            System.out.println("注册成功：" + name);
        } catch (ValidationException e) {
            throw new RuntimeException("注册失败", e);       // 保留 cause
        }
    }

    public static void main(String[] args) {
        registerUser("Tom", 20);
        try {
            registerUser("Tom", 200);
        } catch (RuntimeException e) {
            Throwable t = e;
            while (t != null) {                                 // 打印整条异常链
                System.out.println(t.getClass().getSimpleName() + " -> " + t.getMessage());
                t = t.getCause();
            }
        }
    }
}
