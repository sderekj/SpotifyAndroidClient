package nyc.c4q.dereksantos.spotifyfellowship;

class Person {

    private String id;
    private String name;
    private String favoriteCity;

    public Person(String name, String favoriteCity) {
        this.name = name;
        this.favoriteCity = favoriteCity;
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

    public String getFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(String favoriteCity) {
        this.favoriteCity = favoriteCity;
    }
}
