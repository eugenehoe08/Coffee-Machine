public enum Currency {
    USD("United States dollar"),
    EUR("Euro"),
    GBP("Pound sterling"),
    RUB("Russian ruble"),
    UAH("Ukrainian hryvnia"),
    KZT("Kazakhstani tenge"),
    CAD("Canadian dollar"),
    JPY("Japanese yen"),
    CNY("Chinese yuan");

    String name;

    Currency(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return name();
    }
}