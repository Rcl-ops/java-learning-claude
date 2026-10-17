package notes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/** 内存存储。服务器会被多个线程同时访问，所以使用线程安全的容器。 */
public class NoteStore {

    private final Map<Integer, Note> notes = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger();

    public Note add(String text) {
        Note n = new Note(seq.incrementAndGet(), text, Instant.now());
        notes.put(n.id(), n);
        return n;
    }

    public List<Note> list() {
        List<Note> all = new ArrayList<>(notes.values());
        all.sort((a, b) -> Integer.compare(a.id(), b.id()));
        return all;
    }

    public Optional<Note> get(int id) {
        return Optional.ofNullable(notes.get(id));
    }

    public boolean delete(int id) {
        return notes.remove(id) != null;
    }
}
