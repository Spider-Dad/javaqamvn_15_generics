package ru.netology.javaqa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void searchByRouteTest() { // В этом тесте мы проверяем работу метода search
        Ticket[] expected = {
                new Ticket("Самара", "Астана", 200, 16, 18)
        };
        Ticket[] result = aviaSouls.search("Самара", "Астана");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchAndSortByPriceTest() { // проверям метод searchAndSortBy с параметром компаратора по умолчанию ( по возрастанию цены билета
        Ticket[] expected = {
                new Ticket("Самара", "Астана", 200, 16, 18),
        };
        Ticket[] result = aviaSouls.searchAndSortBy("Самара", "Астана", null);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchAndSortByTimeTest() { // проверям метод searchAndSortBy с сортировкой по времени полета от меньшего к большему
        Ticket[] expected = {
                new Ticket("Москва", "Санкт-Петербург", 400, 10, 11),
        };
        Ticket[] result = aviaSouls.searchAndSortBy("Москва", "Санкт-Петербург", new TicketTimeComparator());
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void compareToTest() { // проверяем метод compareTo
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
}