package com.example.jibibackend.service;

import com.example.jibibackend.model.Client;
import com.example.jibibackend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(String phone,String lastName,String firstName, MultipartFile cinFront, MultipartFile cinBack,double balance) throws IOException {
        String cinFrontPath = saveFile(cinFront);
        String cinBackPath = saveFile(cinBack);

        String temporaryPassword = generateTemporaryPassword();

        Client client = new Client();
        client.setPhone(phone);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setTemporaryPassword(temporaryPassword);
        client.setCinFront(cinFrontPath);
        client.setCinBack(cinBackPath);
        client.setBalance(balance);

        return clientRepository.save(client);
    }

    public Client getClientById(String clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }


    private String saveFile(MultipartFile file) throws IOException {
        String folder = "uploadsOfClients/";
        Path uploadPath = Paths.get(folder);
        // Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + UUID.randomUUID() + "-" + file.getOriginalFilename());
        Files.write(path, bytes);
        return path.toString();
    }

    private String generateTemporaryPassword() {
        return UUID.randomUUID().toString();
    }
}
