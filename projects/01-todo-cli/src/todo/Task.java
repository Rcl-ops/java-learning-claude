package todo;

/** 一条待办事项。record 自动生成构造器、访问器、equals/hashCode/toString。 */
public record Task(int id, String text, boolean done) {

    public Task {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("待办内容不能为空");
        }
        text = text.strip();
    }

    /** record 不可变，所以"修改"就是创建一个新对象。 */
    public Task markDone() {
        return new Task(id, text, true);
    }

    @Override
    public String toString() {
        return String.format("[%s] %d. %s", done ? "x" : " ", id, text);
    }
}
