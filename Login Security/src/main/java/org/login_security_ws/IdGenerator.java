package org.login_security_ws;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;

public class IdGenerator implements IdentifierGenerator, Configurable {

    private String tableName;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String gen_id = null;
        try {

            ResultSet rs = session.connection().createStatement()
                    .executeQuery("select Get_Next_ID('" + tableName + "') id");
            if (rs.next()) {
                gen_id = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gen_id;
    }

    @Override
    public void configure(org.hibernate.type.Type type, Properties params, ServiceRegistry serviceRegistry)
            throws org.hibernate.MappingException {
        this.tableName = params.getProperty("tableName");

    }

}