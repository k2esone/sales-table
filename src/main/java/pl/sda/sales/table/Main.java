package pl.sda.sales.table;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.sales.table.module.Kategoria;
import pl.sda.sales.table.module.Produkt;
import pl.sda.sales.table.module.Sprzedaz;

import java.util.List;
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

/// DODAJ PRODUKT!
        if (wykonaj.equalsIgnoreCase("dodaj produkt")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                System.out.println("Podaj kategorie produktu");
                String kat = scanner.nextLine();
                Kategoria kategoria = Kategoria.valueOf(kat);

                System.out.println("Podaj nazwe produktu");
                String nazwa = scanner.nextLine();

                Produkt produkt = Produkt.builder()
                        .kategoria(kategoria)
                        .nazwa(nazwa)
                        .build();

                session.persist(produkt);

                transaction.commit();

            } catch (Exception ioe) {

            }
        }

///DODAJ SPRZEDAZ
        if (wykonaj.equalsIgnoreCase("dodaj sprzedaz")) {

            System.out.println("Podaj id produktu dla ktorego chcesz utworzyc sprzedaz");
            Long id = Long.parseLong(scanner.nextLine());

            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Produkt produkt = session.get(Produkt.class, id);
                if (produkt == null) System.out.println("Nie znaleziono produktu");
                else {
                    System.out.println("Podaj cene sprzedazy produktu");
                    Double cena = Double.parseDouble(scanner.nextLine());

                    System.out.println("Podaj ilosc");
                    Double ilosc = Double.parseDouble(scanner.nextLine());

                    Sprzedaz sprzedaz = Sprzedaz.builder()
                            .cena(cena)
                            .ilosc(ilosc)
                            .produkt(produkt)
                            .build();

                    session.persist(sprzedaz);
                }
                transaction.commit();

            } catch (Exception ioe) {

            }

        }

///WYSWIETL LISTE PRODUKTOW
        if (wykonaj.equalsIgnoreCase("wyswietl liste produktow")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                TypedQuery<Produkt> zapytanie = session.createQuery("FROM Produkt", Produkt.class);
                List<Produkt> lista = zapytanie.getResultList();
                lista.forEach(System.out::println);
            } catch (Exception ioe) {

            }
        }

///WYSWIETL LISTE SPRZEDAZY
        if (wykonaj.equalsIgnoreCase("wyswietl liste sprzedazy")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                TypedQuery<Sprzedaz> zapytanie = session.createQuery("FROM Sprzedaz", Sprzedaz.class);
                List<Sprzedaz> lista = zapytanie.getResultList();
                lista.forEach(System.out::println);
            } catch (Exception ioe) {

            }
        }

/// !! DO POPRAWY !!
///WYSWIETL LISTE SPRZEDAZY PRODUKTOW
        if (wykonaj.equalsIgnoreCase("wyswietl liste sprzedazy produktu")) {
            System.out.println("Podaj id produktu, do wyswietlenia listy sprzedazy");
            int id = Integer.parseInt(scanner.nextLine());

            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                TypedQuery<Sprzedaz> zapytanie = session.createQuery("FROM Sprzedaz", Sprzedaz.class);
                List<Sprzedaz> lista = zapytanie.getResultList();
                System.out.println(lista.get(id));

            } catch (Exception ioe) {

            }
        }

///USUN SPRZEDAZ
        if (wykonaj.equalsIgnoreCase("usun sprzedaz")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                System.out.println("Podaj id sprzedazy, ktora chcesz usunac");
                Long id = Long.parseLong(scanner.nextLine());

                Sprzedaz sprzedaz = session.get(Sprzedaz.class, id);
                if (sprzedaz == null) {
                    System.err.println("Nie znaleziono sprzedazy");
                } else {
                    session.remove(sprzedaz);
                }
                transaction.commit();
            } catch (Exception ioe) {

            }
        }

///USUN PRODUKT
        if (wykonaj.equalsIgnoreCase("usun produkt")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                System.out.println("Podaj id produktu, ktory chcesz usunac");
                Long id = Long.parseLong(scanner.nextLine());

                Produkt produkt = session.get(Produkt.class, id);
                if (produkt == null) {
                    System.err.println("Nie znaleziono produktu");
                } else {
                    if (!produkt.getSprzedaze().isEmpty()) {
                        for (Sprzedaz sprzedaz : produkt.getSprzedaze()) {
                            session.remove(sprzedaz);
                        }
                    }
                    session.remove(produkt);
                }
                transaction.commit();

            } catch (Exception ioe) {
                System.err.println("Błąd bazy: " + ioe);
            }
        }

///USUN PRODUKT(NULL)
        if (wykonaj.equalsIgnoreCase("usun produkt(null)")) {
            try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                System.out.println("Podaj id produktu, ktory chcesz usunac");
                Long id = Long.parseLong(scanner.nextLine());

                Produkt produkt = session.get(Produkt.class, id);
                if (produkt == null) {
                    System.err.println("Nie znaleziono produktu");
                } else {
                    if (!produkt.getSprzedaze().isEmpty()) {
                        for (Sprzedaz sprzedaz : produkt.getSprzedaze()) {
                            sprzedaz.setProdukt(null);
                        }
                    }
                    session.remove(produkt);
                }
                transaction.commit();

            } catch (Exception ioe) {
                System.err.println("Błąd bazy: " + ioe);
            }
        }


/// ZROBIC WSZYSTKO W METODACH!!!
/// POPRAWIC LISTE SPRZEDAZY PRODUKTOW!!!
/// POPRAWIC SREDNIA WAZONA!!!


    }
}
