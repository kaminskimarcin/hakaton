
package com.hackathon;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hackathon.dtos.ProcessOrderReceiverDTO;

@SpringBootTest
public class  HackathonApplicationTests {

///	@Test
///	void contextLoads() {
///	}
	@Mock
	ProcessOrderReceiverDTO mock;
	
	@Test
	public void  mockLab(){
		MockitoAnnotations.initMocks(this);
		ProcessOrderReceiverDTO exampleObject = new ProcessOrderReceiverDTO();
		
		//1 poczatek
		String temp = "Let's start with this";
		System.out.println("1 " + temp);
		
		//2 proba calla na mocku
		temp = mock.hello();
		System.out.println("2 mock version: " + temp);
		
		//3 proba na prawdziwym obiekcie
		temp = exampleObject.hello();
		System.out.println("3 " + temp);
		
		//4 sama deklaracja mock when
		Mockito.when(mock.hello()).thenReturn("Mocked String");
		System.out.println("4 " + temp);
		
		//5 zawolanie zamockowanej metody
		temp = mock.hello();
		System.out.println("5 " + temp);
		
	}
}
