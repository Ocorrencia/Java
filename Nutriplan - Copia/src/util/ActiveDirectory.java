package util;

import javax.naming.ldap.LdapContext;

public class ActiveDirectory {

    public String login(String login, String senha) {
        try {
            LdapContext ctx = AD.getConnection(login, senha);
            ctx.close();
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
