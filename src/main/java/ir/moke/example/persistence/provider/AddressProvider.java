package ir.moke.example.persistence.provider;

import ir.moke.example.persistence.model.Address;
import ir.moke.example.persistence.model.Client;
import ir.moke.microfox.persistence.SqlUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class AddressProvider {
    public String insert(Map<String, Object> params) {
        Address address = (Address) params.get("address");
        Client client = (Client) params.get("client");
        return new SQL()
                .INSERT_INTO("address")
                .VALUES(SqlUtils.getColumns(Address.class) + ",client_id", SqlUtils.getValues(address) + "," + client.getId())
                .toString();
    }

    public String selectAll() {
        return new SQL()
                .SELECT("*")
                .FROM("address")
                .toString();
    }

    public String selectById(long id) {
        return new SQL()
                .SELECT(SqlUtils.getColumns(Address.class))
                .FROM("address")
                .WHERE("id=#{id}")
                .toString();
    }
}
