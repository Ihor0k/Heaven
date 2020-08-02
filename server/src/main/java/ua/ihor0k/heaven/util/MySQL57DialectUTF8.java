package ua.ihor0k.heaven.util;

import org.hibernate.dialect.MySQL57Dialect;

public class MySQL57DialectUTF8 extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
