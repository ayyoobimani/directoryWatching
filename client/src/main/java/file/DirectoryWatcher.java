package file;

/**
 * Created by vandermonde on 10/25/15.
 */
import client.Client;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example to watch a directory (or tree) for changes to files.
 */

public class DirectoryWatcher {
    private int NUMBEROFTHREDS = 10;

    private final WatchService watcher;
    private final WatchKey key;
    private final Path dir;
    private Client clientCreator;
    private ExecutorService executor;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }


    /**
     * Creates a WatchService and registers the given directory
     */
    public DirectoryWatcher(Path path, Client clientCreator) throws IOException {
        executor = Executors.newFixedThreadPool(NUMBEROFTHREDS);
        this.watcher = FileSystems.getDefault().newWatchService();
        key = path.register(watcher, ENTRY_CREATE);
        this.dir = path;
        this.clientCreator = clientCreator;
        processEvents();
    }

    void processEvents() {
        for (;;) {
            for (WatchEvent<?> event: key.pollEvents())
                if (event.kind() == ENTRY_CREATE )
                        giveFileToClient(event);

            if (!key.reset()) {
                System.out.println("key invalid, quiting");
                    break;
            }
        }
    }

    private void giveFileToClient(WatchEvent<?> event) {
        WatchEvent<Path> ev = cast(event);
        Path name = ev.context();
        System.out.println("new file created: " + name);
        if (!Files.isDirectory(Paths.get(dir.toString() + "/" + name), NOFOLLOW_LINKS)) {
            Client client = (Client) clientCreator.clone();
            client.setFile(dir.toString(), name.getFileName().toString());
            executor.execute(client);
        }
    }

}