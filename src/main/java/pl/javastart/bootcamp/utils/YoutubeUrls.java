package pl.javastart.bootcamp.utils;

public class YoutubeUrls {
    public static String mapToEmbed(String link) {
        if (link.startsWith("http")) {
            if (link.contains("youtu.be")) {
                return link.replace("youtu.be/", "www.youtube.com/embed/");
            } else {
                return link.replace("watch?v=", "embed/");
            }
        } else {
            return "https://www.youtube.com/embed/" + link;
        }
    }
}
