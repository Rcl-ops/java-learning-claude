package organizer;

import java.util.Locale;
import java.util.Set;

/** 文件分类：每个枚举常量带一组扩展名。枚举 + 字段 + 方法的典型用法。 */
public enum FileCategory {
    IMAGES("Images", Set.of("jpg", "jpeg", "png", "gif", "bmp", "webp", "svg")),
    DOCUMENTS("Documents", Set.of("pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "md", "csv")),
    ARCHIVES("Archives", Set.of("zip", "rar", "7z", "tar", "gz", "jar")),
    VIDEOS("Videos", Set.of("mp4", "mkv", "avi", "mov", "flv")),
    AUDIO("Audio", Set.of("mp3", "wav", "flac", "aac", "ogg")),
    CODE("Code", Set.of("java", "py", "js", "ts", "c", "cpp", "html", "css", "json", "xml", "gradle")),
    OTHERS("Others", Set.of());

    private final String folderName;
    private final Set<String> extensions;

    FileCategory(String folderName, Set<String> extensions) {
        this.folderName = folderName;
        this.extensions = extensions;
    }

    public String folderName() {
        return folderName;
    }

    /** 根据文件名（含扩展名）判断所属分类。 */
    public static FileCategory of(String fileName) {
        int dot = fileName.lastIndexOf('.');
        if (dot <= 0 || dot == fileName.length() - 1) return OTHERS;   // 没有扩展名，或 ".bashrc" 这种隐藏文件
        String ext = fileName.substring(dot + 1).toLowerCase(Locale.ROOT);
        for (FileCategory c : values()) {
            if (c.extensions.contains(ext)) return c;
        }
        return OTHERS;
    }
}
