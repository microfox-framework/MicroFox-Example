package ir.moke.example;

import ir.moke.example.persistence.DB;
import ir.moke.example.persistence.mapper.AddressMapper;
import ir.moke.example.persistence.mapper.ClientMapper;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.persistence.model.Client;
import ir.moke.kafir.utils.JsonUtils;

import java.util.List;

import static ir.moke.microfox.MicroFox.mybatis;

public class MyBatisRunner {
    static {
        DB.initializeMyBatis();
        DB.initializeDatabase();
    }

    public static void main(String[] args) {
        Address a1 = new Address("Iran", "Tehran", "Tehran", "Enghelab", "0123456789");
        Address a2 = new Address("Iran", "Golestan", "Gorgan", "Azadi", "951753464");
        Client client = new Client("Mahdi", "Sheikh Hosseini", List.of(a1, a2));

        mybatis("h2", ClientMapper.class, clientMapper -> clientMapper.save(client));
        mybatis("h2", AddressMapper.class, addressMapper -> addressMapper.save(a1, client));
        mybatis("h2", AddressMapper.class, addressMapper -> addressMapper.save(a2, client));

        mybatis("h2", AddressMapper.class, addressMapper -> {
            addressMapper.findAll().forEach(item -> System.out.println(JsonUtils.toJson(item)));
        });
    }
}
