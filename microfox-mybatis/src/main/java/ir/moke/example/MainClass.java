package ir.moke.example;

import ir.moke.example.persistence.DB;
import ir.moke.example.persistence.mapper.AddressMapper;
import ir.moke.example.persistence.mapper.ClientMapper;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.persistence.model.Client;

import java.util.List;

import static ir.moke.microfox.MicroFox.mybatis;
import static ir.moke.microfox.MicroFox.mybatisTx;

public class MainClass {
    static {
        DB.initializeMyBatis();
        DB.initializeDatabase();
    }

    public static void main(String[] args) {
        Address a1 = new Address("Iran", "Tehran", "Tehran", "Enghelab", "0123456789");
        Address a2 = new Address("Iran", "Golestan", "Gorgan", "Azadi", "951753464");
        Client client = new Client("Mahdi", "Sheikh Hosseini", List.of(a1, a2));

        mybatisTx("h2", ClientMapper.class, clientMapper -> clientMapper.save(client));
        mybatisTx("h2", AddressMapper.class, addressMapper -> addressMapper.save(a1, client));
        mybatisTx("h2", AddressMapper.class, addressMapper -> addressMapper.save(a2, client));

        List<Address> list = mybatis("h2", AddressMapper.class, AddressMapper::findAll);
        list.forEach(System.out::println);
    }
}
