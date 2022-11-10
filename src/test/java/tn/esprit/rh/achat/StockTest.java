package tn.esprit.rh.achat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StockTest {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
   StockServiceImpl stockService;

    Stock s1 = new Stock(12L, "Code Hana",51,255);
    Stock f2 = new Stock(22L, "Code Hana2",12,20);

    List<Stock> listStocks = new ArrayList<Stock>() {
        {
            add(s1);
            add(new Stock(10L, "code 1",10,20));
            add(new Stock(20L, "code 2",21,30));
        }
    };

    @Test
    public void retrieveStocks() {
        System.out.println("retrieveStocks");
        Mockito.when(stockRepository.findById(12L)).thenReturn(Optional.of(s1));
        Stock stock1 = stockService.retrieveStock(12L);
        assertNotNull(stock1);
    }
    @Test
   public void testretrieveAllStock() {
        System.out.println("retrieveAllStocks");
        Mockito.when(stockRepository.findAll()).thenReturn(listStocks);
        List<Stock> stockList3 = stockService.retrieveAllStocks();
        assertEquals(3, stockList3.size());
        //assertEquals(stock1.,55L);
    }

    @Test
    public void testaddStock(){
        System.out.println("testaddStock");
        Mockito.when(stockRepository.save(s1)).thenReturn(s1);
        Stock stock1 = stockService.addStock(s1);
        assertNotNull(stock1);
        Mockito.verify(stockRepository, times(1)).save(Mockito.any(Stock.class));
    }


    @Test
    public void testdeleteStock(){
        System.out.println("testdeleteStock");
        stockService.deleteStock(66L);
        Mockito.verify(stockRepository, times(0)).delete(f2);
    }

}