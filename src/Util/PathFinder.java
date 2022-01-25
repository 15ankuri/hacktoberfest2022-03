package Util;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathFinder {
    public static Path rootPath(Path path) {
        Pattern pattern = Pattern.compile("(.*)/(.*)");
        Matcher matcher = pattern.matcher(path.toString());
        matcher.find();
        return Path.of(matcher.group(1));
    }
}
