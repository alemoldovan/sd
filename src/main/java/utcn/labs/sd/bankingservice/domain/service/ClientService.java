package utcn.labs.sd.bankingservice.domain.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.ClientConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ClientRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;
import utcn.labs.sd.bankingservice.domain.exception.CreateClientException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<ClientDTO> getAllClients() {
        return ClientConverter.toDto(clientRepository.findAll());
    }

    public ClientDTO getClientById(String clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) throw new NotFoundException("No client found with that clientId");
        return ClientConverter.toDto(client);
    }
    
    public List<AccountDTO> getAccountsByClient(String clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        List<Account> accounts = client.getAccountList();
        if (client == null) throw new NotFoundException("No client found for that clientId");
        return AccountConverter.toDto(accounts);
    }

    public ClientDTO createClient(ClientDTO clientDto) throws Exception {
        Client client = new Client(clientDto.getSsn(), clientDto.getFirstname(), clientDto.getLastname(), clientDto.getIdentityCardNumber(),
                clientDto.getAddress(), clientDto.getEmail(), clientDto.getAccountList());
        Client possibleAlreadyExistingClient = clientRepository.findById(clientDto.getSsn()).orElse(null);
        if (possibleAlreadyExistingClient == null) {
            Client newClient = clientRepository.save(client);
            return ClientConverter.toDto(newClient);
        } else {
            throw new CreateClientException("Client already exists!");
        }
    }

    public ClientDTO changeClient(String clientId, ClientDTO clientDto) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("No client found with that clientId");
        }
        client.setSsn(clientDto.getSsn());
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setIdentityCardNumber(clientId);
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        return ClientConverter.toDto(clientRepository.save(client));
    }

    public void deleteClient(String clientId) throws Exception {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            throw new NotFoundException("No client with that clientId");
        }
        List<Account> clientListOfAccounts = client.getAccountList();
        
        if(clientListOfAccounts != null) {
	        for (Account account : clientListOfAccounts) {
	            accountRepository.delete(account);
	        }
        }
        clientRepository.delete(client);
    }

    public ClientDTO addAccountToClient(String clientId, AccountDTO accountDto) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        Account account = new Account(accountDto.getAccountId(), accountDto.getAccountType(), currentTimestamp.toString(), accountDto.getBalance());
        client.getAccountList().add(account);
        accountRepository.save(account);
        return ClientConverter.toDto(client);
    }

    public ClientDTO deleteAccountFromClient(String clientId, Integer accountId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        accountRepository.deleteById(accountId);
        return ClientConverter.toDto(client);
    }
    
    
}
