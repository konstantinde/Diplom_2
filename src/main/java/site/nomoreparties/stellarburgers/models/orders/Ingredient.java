package site.nomoreparties.stellarburgers.models.orders;

public class Ingredient {
    /**
     * POJO игредиента
     */

    private String _id;
    private String name;
    private String type;
    private int proteins;
    private int carbohydrates;
    private int calories;
    private float price;
    private String image;
    private String image_mobile;
    private String image_large;
    private int __v;

    public Ingredient() {
    }

    public Ingredient(String _id, String name, String type, int proteins, int carbohydrates, int calories, float price,
                      String image, String image_mobile, String image_large, int __v) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.image_mobile = image_mobile;
        this.image_large = image_large;
        this.__v = __v;
    }

    public Ingredient set_id(String _id) {
        this._id = _id;
        return this;
    }

    public Ingredient setName(String name) {
        this.name = name;
        return this;
    }

    public Ingredient setType(String type) {
        this.type = type;
        return this;
    }

    public Ingredient setProteins(int proteins) {
        this.proteins = proteins;
        return this;
    }

    public Ingredient setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }

    public Ingredient setCalories(int calories) {
        this.calories = calories;
        return this;
    }

    public Ingredient setPrice(float price) {
        this.price = price;
        return this;
    }

    public Ingredient setImage(String image) {
        this.image = image;
        return this;
    }

    public Ingredient setImage_mobile(String image_mobile) {
        this.image_mobile = image_mobile;
        return this;
    }

    public Ingredient setImage_large(String image_large) {
        this.image_large = image_large;
        return this;
    }

    public Ingredient set__v(int __v) {
        this.__v = __v;
        return this;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getImage_mobile() {
        return image_mobile;
    }

    public String getImage_large() {
        return image_large;
    }

    public int get__v() {
        return __v;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", calories=" + calories +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", image_mobile='" + image_mobile + '\'' +
                ", image_large='" + image_large + '\'' +
                ", __v=" + __v +
                '}';
    }
}
