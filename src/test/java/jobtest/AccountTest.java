package jobtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import jobtest.service.PaymentService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	PaymentService paymentService;

	@Test
	public void testTransfer() throws Exception {		
		
		Account ac = paymentService.getAccount();
		System.out.println(ac);
		
		mockMvc.perform(get("/api/payment/shop/100")).andExpect(status().isOk());
		
		mockMvc.perform(get("/api/payment/shop/120")).andExpect(status().isOk());	
		
		mockMvc.perform(get("/api/payment/online/301")).andExpect(status().isOk());
		
		mockMvc.perform(get("/api/payment/online/17")).andExpect(status().isOk());
		
		mockMvc.perform(get("/api/payment/shop/1000")).andExpect(status().isOk());
		
		mockMvc.perform(get("/api/payment/online/21")).andExpect(status().isOk());
		
		
		mockMvc.perform(get("/api/payment/shop/570")).andExpect(status().isOk());
		
		mockMvc.perform(get("/api/payment/online/700")).andExpect(status().isOk());
		ac = paymentService.getAccount();
		System.out.println(ac);
	}

}
