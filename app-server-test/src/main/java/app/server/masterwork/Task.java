package app.server.masterwork;

/**
 * Author:  Administrator
 * Date:    2017/5/8 22:34
 * Function:Please input the function of this class!
 */
public class Task {


    private int id;
    private int price;
    private String name;

    public Task() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
