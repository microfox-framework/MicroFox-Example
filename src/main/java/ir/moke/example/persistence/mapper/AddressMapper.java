package ir.moke.example.persistence.mapper;

import ir.moke.example.persistence.model.Address;
import ir.moke.example.persistence.model.Client;
import ir.moke.example.persistence.provider.AddressProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AddressMapper {

    @InsertProvider(type = AddressProvider.class, method = "insert")
    @SelectKey(statement = "SELECT address_seq.NEXTVAL FROM dual", keyProperty = "address.id", before = true, resultType = Long.class)
    void save(@Param("address") Address address, @Param("client") Client client);

    @SelectProvider(type = AddressProvider.class, method = "selectAll")
    List<Address> findAll();

    @SelectProvider(type = AddressProvider.class, method = "selectById")
    Address findById(long id);

    @Select("select * from address where client_id=#{clientId}")
    List<Address> findAddressByClientId(long clientId);

}
