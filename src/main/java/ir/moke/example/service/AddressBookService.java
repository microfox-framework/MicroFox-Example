package ir.moke.example.service;

import ir.moke.example.persistence.mapper.AddressMapper;
import ir.moke.example.persistence.mapper.ClientMapper;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.persistence.model.Client;

import java.util.List;

import static ir.moke.microfox.MicroFox.sqlExecute;
import static ir.moke.microfox.MicroFox.sqlFetch;

public class AddressBookService {
    public static void saveClient(Client client) {
        sqlExecute("h2", ClientMapper.class, clientMapper -> clientMapper.save(client));
    }

    public static List<Client> findAllClients() {
        return sqlFetch("h2", ClientMapper.class, ClientMapper::findAll);
    }

    public static void saveAddress(Address address, long id) {
        Client client = sqlFetch("h2", ClientMapper.class, clientMapper -> clientMapper.findById(id));
        if (client == null) throw new RuntimeException("Client does not exists");
        sqlExecute("h2", AddressMapper.class, addressMapper -> addressMapper.save(address, client));
    }

    public static Address findAddress(long id) {
        return sqlFetch("h2", AddressMapper.class, addressMapper -> addressMapper.findById(id));
    }
}
