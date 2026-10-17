package linkcheck;

/** 一个链接的检查结果。 */
public record CheckResult(String url, int status, long millis, String error) {

    public boolean ok() {
        return error == null && status >= 200 && status < 400;
    }

    @Override
    public String toString() {
        String state = ok() ? "OK  " : "FAIL";
        String detail = error != null ? error : String.valueOf(status);
        return String.format("%s %-6s %5d ms  %s", state, detail, millis, url);
    }
}
