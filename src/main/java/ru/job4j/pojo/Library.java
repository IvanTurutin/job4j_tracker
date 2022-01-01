package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book war = new Book("Война и мир", 1800);
        Book clean = new Book("Clean code", 464);
        Book master = new Book("Мастер и Маргарита", 448);
        Book rat = new Book("Стальная крыса", 352);
        Book[] books = new Book[4];
        books[0] = war;
        books[1] = clean;
        books[2] = master;
        books[3] = rat;
        print(books);
        books[0] = rat;
        books[3] = war;
        print(books);
        find(books, "Clean code");
    }

    public static void print(Book[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + ", " + books[i].getPages() + " стр.");
        }
        System.out.println();
    }

    public static void find(Book[] books, String find) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(find)) {
                System.out.println(books[i].getName() + ", " + books[i].getPages() + " стр.");
            }
        }
    }
}
