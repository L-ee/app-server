package mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author:  Administrator
 * Date:    2016/9/25 22:24
 * Function:Please input the function of this class!
 */
public class Article {

    private final String title;
    private final String author;
    private final List<String> tags;

    private Article(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }

    public static void main(String[] args) {
        List<Article> list = new ArrayList<Article>();

        list.add(new Article("Java编程思想", "Jack", new ArrayList<String>() {
            {
                add("Java");
                add("coding");
            }
        }));

        list.add(new Article("JavaScript", "martin", new ArrayList<String>() {
            {
                add("javascript");
                add("script");
            }
        }));


        list.add(new Article("nodejs", "rose", new ArrayList<String>() {
            {
                add("javascript");
                add("back");
            }
        }));


        Optional<Article> java = list.stream().filter(l -> l.getTags().contains("Java")).findFirst();
        System.out.println(java.toString());


    }




}
