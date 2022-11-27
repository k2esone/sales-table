package pl.sda.sales.table;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Co chcesz zrobic? " +
                "\ndodaj produkt " +
                "\ndodaj sprzedaz" +
                "\nwyswietl liste produktow" +
                "\nwyswietl liste sprzedazy" +
                "\nwyswietl liste sprzedazy produktu" +
                "\nusun sprzedaz" +
                "\nusun produkt" +
                "\nusun produkt(null)");
        String wykonaj = scanner.nextLine();
        if (wykonaj.equalsIgnoreCase("dodaj produkt")){

        }
        if (wykonaj.equalsIgnoreCase("dodaj sprzedaz")){

        }
        if (wykonaj.equalsIgnoreCase("wyswietl liste produktow")){

        }
        if (wykonaj.equalsIgnoreCase("wyswietl liste sprzedazy")){

        }
        if (wykonaj.equalsIgnoreCase("wyswietl liste sprzedazy produktu")){

        }
        if (wykonaj.equalsIgnoreCase("usun sprzedaz")){

        }
        if (wykonaj.equalsIgnoreCase("usun produkt")){

        }
        if (wykonaj.equalsIgnoreCase("usun produkt(null)")){

        }


//        dodawanie produktów
//        dodawanie sprzedaży (do istniejących produktów)
//        wyświetlanie listy produktów
//        wyświetlanie listy sprzedaży
//        wyświetlanie listy sprzedaży danego produktu
//        usuwanie sprzedaży
//        usuwanie produktu
//        (jeśli produkt ma sprzedaże, to usuwamy sprzedaże również)

//* (dodatkowe) usuwanie produktu 2
//    (jeśli produkt ma sprzedaże, to nie usuwamy sprzedaży,
//                tylko ustawiamy produkt w sprzedaży na null)


    }
}
