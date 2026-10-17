package notes;

import java.time.Instant;

/** 一条笔记。 */
public record Note(int id, String text, Instant createdAt) {

    /** 手写的 JSON 序列化（教学用；生产环境请用 Jackson / Gson）。 */
    public String toJson() {
        return "{\"id\":" + id
                + ",\"text\":\"" + Json.escape(text) + "\""
                + ",\"createdAt\":\"" + createdAt + "\"}";
    }
}
