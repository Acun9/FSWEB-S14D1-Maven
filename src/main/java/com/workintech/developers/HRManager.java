// src/com/workintech/developers/HRManager.java
package com.workintech.developers;

public class HRManager extends Employee {
    // 3 adet dizi
    private final JuniorDeveloper[] juniorDevelopers;
    private final MidDeveloper[] midDevelopers;
    private final SeniorDeveloper[] seniorDevelopers;

    // Kapasiteleri belirleyen ctor
    public HRManager(long id, String name, double salary,
                     int juniorCapacity, int midCapacity, int seniorCapacity) {
        super(id, name, salary);
        this.juniorDevelopers  = new JuniorDeveloper[Math.max(0, juniorCapacity)];
        this.midDevelopers     = new MidDeveloper[Math.max(0, midCapacity)];
        this.seniorDevelopers  = new SeniorDeveloper[Math.max(0, seniorCapacity)];
    }

    // Basit varsayılan kapasite
    public HRManager(long id, String name, double salary) {
        this(id, name, salary, 5, 5, 5);
    }

    @Override
    public void work() {
        System.out.println("HRManager starts to working");
        setSalary(getSalary() + 1500);
    }

    /* ----------------- Overloaded addEmployee ----------------- */
    // Junior
    public void addEmployee(JuniorDeveloper dev, int index) {
        addToArray(dev, index, juniorDevelopers, "Junior");
    }
    public void addEmployee(JuniorDeveloper dev) { // ilk boş yere
        addToArray(dev, -1, juniorDevelopers, "Junior");
    }

    // Mid
    public void addEmployee(MidDeveloper dev, int index) {
        addToArray(dev, index, midDevelopers, "Mid");
    }
    public void addEmployee(MidDeveloper dev) {
        addToArray(dev, -1, midDevelopers, "Mid");
    }

    // Senior
    public void addEmployee(SeniorDeveloper dev, int index) {
        addToArray(dev, index, seniorDevelopers, "Senior");
    }
    public void addEmployee(SeniorDeveloper dev) {
        addToArray(dev, -1, seniorDevelopers, "Senior");
    }

    /* ----------------- Ortak ekleme mantığı ----------------- */
    private <T extends Employee> void addToArray(T dev, int index, T[] arr, String levelName) {
        if (dev == null) {
            System.out.println(levelName + " eklenemedi: geliştirici null.");
            return;
        }
        // Belirli indeks istendiyse
        if (index >= 0) {
            if (index >= arr.length) {
                System.out.println(levelName + " eklenemedi: index " + index +
                        " kapasiteyi aşıyor (cap=" + arr.length + ").");
                return;
            }
            if (arr[index] != null) {
                System.out.println(levelName + " eklenemedi: index " + index + " dolu, veri EZİLMEDİ.");
                return;
            }
            arr[index] = dev;
            System.out.println(levelName + " eklendi (index " + index + "): " + dev.getName());
            return;
        }
        // İlk boş yere ekle
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = dev;
                System.out.println(levelName + " eklendi (ilk boş index " + i + "): " + dev.getName());
                return;
            }
        }
        System.out.println(levelName + " eklenemedi: hiçbir boş slot yok (cap=" + arr.length + ").");
    }
}
