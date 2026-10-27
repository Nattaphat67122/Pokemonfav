package cs.ubru.nattaphat.uthumporn.pokemonfav.model;

public class Pokemon {
    private String id;
    private String name;
    private String height;
    private String weight;
    private String description;
    private String imgUrl;

    public Pokemon(String id, String name, String height, String weight, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public Pokemon(String name, String height, String weight, String imgUrl, String description) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public Pokemon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
