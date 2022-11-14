package Passpor;

import java.time.LocalDate;

public class Passport {
    private final int number;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;

    public Passport(int number, String name, String surname, LocalDate birthday) {
        this.number = check(number);
        this.name = check(name);
        this.surname = check(surname);
        this.birthday = check(birthday);
    }

    public Passport(int number, String name, String surname, String patronymic, LocalDate birthday) {
        this.number = check(number);
        this.name = check(name);
        this.surname = check(surname);
        this.patronymic = check(patronymic);
        this.birthday = check(birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;

        Passport passport = (Passport) o;

        return number == passport.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        String str = patronymic.equals("unknown") ? "" : patronymic;
        return String.format("Паспорт № %d на имя %s %s %s %tY года рождения.",
                number, surname, name, str, birthday);
    }

    private String check(String str) {
        if (str == null) str = "";
        if (str.isBlank()) return "unknown";
        return str;
    }

    private int check(int i) {
        if (i < 0) i = Math.abs(i);
        if (i < 10_00_000000) i += 10_00_000000;
        return i;
    }

    private LocalDate check(LocalDate localDate) {
        if (localDate == null) return LocalDate.now();
        return localDate;
    }
}
