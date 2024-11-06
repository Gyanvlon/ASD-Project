package MMS.inventory;

import MMS.inventory.model.Drug;
import MMS.inventory.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {
	@Autowired
	private DrugRepository drugRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Drug drug = new Drug();
		drug.setDrugId(1L);
		drug.setName("Cipla");
		drug.setBatchNumber("1234");
		drug.setExpiryDate(new java.util.Date());
		drug.setManufactureDate(new java.util.Date());
		drug.setManufacturer("Cipla");
		drug.setPrice(100.0);
		drug.setQuantity(10);
		drug.setCategory("Tablet");
		drug.setDescription("Cipla Tablet");
		drugRepository.save(drug);
	}
}
