/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.Control;

/**
 *
 * @author diogo.melo
 */
public class usuarioDao {

    public void consultarUusuarioLDAP() {

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // COLOCAR IP E PORTA DO SERVIDOR LDAP
        env.put(Context.PROVIDER_URL, "ldap://10.1.1.253:389");
        // env.put(Context.SECURITY_AUTHENTICATION, "WEBnutri2014");

        // NECESSARIO CONHECER COMO ESTA CONFIGURADO A ARVORE NO LDAP
        // EXEMPLO: CN=LOGIN,CN=FUNCIONARIOS,OU=FILIALSP,DC=COM,DC=BR
        env.put(Context.SECURITY_PRINCIPAL, "CN=WEB,CN=Users,DC=nutriplan,DC=com,DC=br");
        // COLOCAR SENHA CASO NECESSARIO
        env.put(Context.SECURITY_CREDENTIALS, "WEBnutri2014");

        LdapContext ctx = null;
        try {
            Control[] connectionControls = null;
            // Nesse momento é feito a conexao, qualquer erro sera jogado na
            // excecao
            ctx = new InitialLdapContext(env, connectionControls);

            // Criando mecanismo de pesquisa
            SearchControls sc = new SearchControls();

            // Colocar o escopo da procura : SearchControls.ONELEVEL_SCOPE ,
            // SearchControls.SUBTREE_SCOPE, SearchControls.ONELEVEL_SCOPE,
            // SearchControls.OBJECT_SCOPE
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // Qual o caminho que sera pesquisado
            String base = "OU=nutriplan,DC=nutriplan,DC=com,DC=br";

            // Filtro pode ser usado *
            // Parenteses divide os parametros
            String filter = "(&(objectclass=user)(!(objectclass=computer))(!(userAccountControl:1.2.840.113556.1.4.803:=2)))";

            // O retorno da pesquisa ficara no anwser
            NamingEnumeration<?> answer = ctx.search(base, filter, sc);

            while (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                // Para pegar um atributo da busca
                Attribute attr = attrs.get("cn");
                attr = attrs.get("name");
                System.out.println(attr.get());
            }

        } catch (NamingException e) {
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                }
            }
        }

    }
}
