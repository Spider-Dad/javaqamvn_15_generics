package ru.netology.javaqa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    private AviaSouls aviaSouls;

    @BeforeEach
    public void setUp() {
        aviaSouls = new AviaSouls();
        aviaSouls.add(new Ticket("Москва", "Санкт-Петербург", 400, 10, 11));
        aviaSouls.add(new Ticket("Сочи", "Челябинск", 300, 14, 17));
        aviaSouls.add(new Ticket("Екатеринбург", "Казань", 500, 12, 16));
        aviaSouls.add(new Ticket("Самара", "Астана", 200, 16, 18));
        aviaSouls.add(new Ticket("Москва", "Воронеж", 400, 20, 23));
    }

    @Test
    public void searchByRouteTest() { // Проверяем работу метода search, ищем билеты по маршруту
        Ticket[] expected = {
                new Ticket("Самара", "Астана", 200, 16, 18)
        };
        Ticket[] result = aviaSouls.search("Самара", "Астана");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchAndSortByPriceTest() { // Проверяем метод searchAndSortBy с параметром компаратора по умолчанию (сортировка по возрастанию цены билета)
        Ticket[] expected = {
                new Ticket("Самара", "Астана", 200, 16, 18),
        };
        Ticket[] result = aviaSouls.searchAndSortBy("Самара", "Астана", null);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchAndSortByTimeTest() { // проверям метод searchAndSortBy с параметром компаратора по времени полета от меньшего к большему
        Ticket[] expected = {
                new Ticket("Москва", "Санкт-Петербург", 400, 10, 11),
        };
        Ticket[] result = aviaSouls.searchAndSortBy("Москва", "Санкт-Петербург", new TicketTimeComparator());
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void compareToTest() { // Проверяем метод compareTo,  сравниваем цены билетов
        Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 400, 10, 11);
        Ticket ticket2 = new Ticket("Екатеринбург", "Казань", 500, 12, 16);
        Ticket ticket3 = new Ticket("Москва", "Воронеж", 400, 20, 23);

        int result = ticket2.compareTo(ticket1);
        Assertions.assertTrue(result > 0); // ticket2.price > ticket1.price

        result = ticket1.compareTo(ticket2);
        Assertions.assertTrue(result < 0); // ticket1.price < ticket2.price

        result = ticket1.compareTo(ticket3);
        Assertions.assertEquals(0, result); // ticket1.price == ticket3.price
    }

    @Test
    public void ticketTimeComparatorTest() {  // // Проверяем компаратор TicketTimeComparator
        Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 400, 10, 11);
        Ticket ticket2 = new Ticket("Сочи", "Челябинск", 300, 14, 17);
        Ticket ticket3 = new Ticket("Екатеринбург", "Казань", 500, 12, 16);
        Ticket ticket4 = new Ticket("Москва", "Воронеж", 400, 20, 23);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        int result = comparator.compare(ticket1, ticket2);
        Assertions.assertTrue(result < 0); // ticket2 < ticket1 по времени полета

        result = comparator.compare(ticket3, ticket2);
        Assertions.assertTrue(result > 0); // ticket3 > ticket2 по времени полета

        result = comparator.compare(ticket2, ticket4);
        Assertions.assertEquals(0, result); // ticket2 и ticket4 равны по времени полета
    }
}