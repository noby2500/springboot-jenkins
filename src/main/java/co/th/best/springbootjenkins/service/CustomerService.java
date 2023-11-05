package co.th.best.springbootjenkins.service;

import co.th.best.springbootjenkins.entity.Customer;
import co.th.best.springbootjenkins.repository.CustomerRepo;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private StringEncryptor encryptor;
    public void saveCsvData(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Read CSV file line by line and save it to the database
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV data is comma-separated
                Customer csvData = new Customer();
                csvData.setName(data[0]); // Set appropriate index for columns
                String encryptedNId = encryptor.encrypt(data[1]);
                csvData.setNId(encryptedNId);
                String encryptedMsisdn = encryptor.encrypt(data[2]);
                csvData.setMsisdn(encryptedMsisdn);
                customerRepo.save(csvData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    public List<Customer> getCsvData() {
        return customerRepo.findAll();
    }

    public String decryptData(String encryptedData) {
        return encryptor.decrypt(encryptedData);
    }
}
