package tn.esprit.rh.achat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProduitTest {
	
	
	
	
	@Mock
	ProduitRepository produitRepository;
	
	  @InjectMocks
	   ProduitServiceImpl produitService;

	  Produit p1 = new Produit(55L, "2365","produit1",50);
	  Produit p2 = new Produit(66L, "5681","produit5",120);


	    List<Produit> listProduits = new ArrayList<Produit>() {
	        {
	            add(p1);
	            add(new Produit(90L, "9687","produit2",30));
	            add(new Produit(46L, "4503","produit3",70));
	        }
	    };



	    @Test
	    public void testRetrieveProduit() {

	        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p1));
	        Produit produit1 = produitService.retrieveProduit(55L);
	        assertNotNull(produit1);
	        System.out.println("1");
	        assertEquals(produit1.getIdProduit(),55L);
	   }

	    @Test
	    public void testretrieveAllProduits() {
	        Mockito.when(produitRepository.findAll()).thenReturn(listProduits);
	        List<Produit> listproduit3 = produitService.retrieveAllProduits();
	        assertEquals(3, listproduit3.size());
	        //assertEquals(produit1.getIdProduit(),55L);
	        System.out.println("2555");
	    }

	    @Test
	    public void testaddProduit(){
	        Mockito.when(produitRepository.save(p1)).thenReturn(p1);
	        Produit produit1 = produitService.addProduit(p1);
	        //assertNotNull(produit1);
	        Mockito.verify(produitRepository, times(1)).save(Mockito.any(Produit.class));
	        System.out.println("3");
	    }

	    @Test
	    public void testdeleteProduit(){
	        produitService.deleteProduit(66L);
	        Mockito.verify(produitRepository, times(1)).deleteById(66L);
	        System.out.println("4");
	    }
	///sss
	    @Test
	    public void testupdateProduit(){
	        Mockito.when(produitRepository.save(p1)).thenReturn(p1);
	        Produit produit1 = produitService.updateProduit(p1);
	        Mockito.verify(produitRepository, times(1)).save(Mockito.any(Produit.class));
	        System.out.println("5");
	    }

	   



}
