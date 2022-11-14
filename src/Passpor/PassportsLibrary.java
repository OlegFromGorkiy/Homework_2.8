package Passpor;

import java.util.HashSet;
import java.util.Set;

public class PassportsLibrary {
    Set<Passport> library = new HashSet<>();

    public void add(Passport passport) {
        if (library.contains(passport)) {
            library.remove(passport);
        }
        library.add(passport);
    }

    public Passport find(int number) {
        Passport p = new Passport(number, null, null, null, null);
        if (!library.contains(p)) return null;
        for (Passport passport : library) {
            if (p.equals(passport)) p = passport;
        }
        return p;
    }

    public void viewInfo() {
        for (Passport p : library) {
            System.out.println(p);
        }
    }
}

