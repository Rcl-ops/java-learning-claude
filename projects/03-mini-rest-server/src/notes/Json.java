package notes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 极简 JSON 工具：只够本项目使用。真实项目请使用 Jackson。 */
final class Json {

    private Json() { }          // 工具类：禁止实例化

    /** 转义字符串里的特殊字符，防止破坏 JSON 结构（也防止注入）。 */
    static String escape(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 0x20) sb.append(String.format("\\u%04x", (int) c));
                    else sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    static String array(List<String> jsonItems) {
        return "[" + String.join(",", jsonItems) + "]";
    }

    private static final Pattern TEXT_FIELD =
            Pattern.compile("\"text\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"");

    /** 从请求体里取出 text：支持 {"text":"..."}，也接受纯文本。 */
    static Optional<String> extractText(String body) {
        String trimmed = body.strip();
        if (trimmed.isEmpty()) return Optional.empty();
        if (trimmed.startsWith("{")) {
            Matcher m = TEXT_FIELD.matcher(trimmed);
            return m.find() ? Optional.of(unescape(m.group(1))) : Optional.empty();
        }
        return Optional.of(trimmed);
    }

    private static String unescape(String s) {
        return s.replace("\\n", "\n").replace("\\t", "\t").replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
