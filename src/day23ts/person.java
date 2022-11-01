package day23ts;

import java.util.Objects;

class person implements Comparable {
    private String name;
    private int age;

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        person person = (person) o;
        return age == person.age && Objects.equals(name, person.name);
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof person) {
            return Integer.compare(this.age, ((person) o).age) + this.name.compareTo(((person) o).name);
        } else throw new RuntimeException("不同calss");
    }
}
