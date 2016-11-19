/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.newtonpaiva.modelo.AlunoTest;
import br.newtonpaiva.modelo.ContratoTest;
import br.newtonpaiva.modelo.ConvenioTest;
import br.newtonpaiva.modelo.CursoTest;
import br.newtonpaiva.modelo.EmpresaTest;
import br.newtonpaiva.modelo.UsuarioTest;
import static br.newtonpaiva.util.ConfigurationManager.DB_SENHA;
import static br.newtonpaiva.util.ConfigurationManager.DB_URL;
import static br.newtonpaiva.util.ConfigurationManager.DB_USUARIO;
import br.newtonpaiva.util.CpfCnpjUtilTest;
import br.newtonpaiva.util.DateUtilTest;
import br.newtonpaiva.util.StringUtilTest;
import br.newtonpaiva.util.ValidacoesUtilTest;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author tarle
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CpfCnpjUtilTest.class, DateUtilTest.class, StringUtilTest.class,
    ValidacoesUtilTest.class, UsuarioTest.class, CursoTest.class,
    EmpresaTest.class, AlunoTest.class, ConvenioTest.class, ContratoTest.class})
public class TestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
        Reader scriptCreate = new InputStreamReader(
            TestSuite.class.getResourceAsStream("/db_create.sql"));
        
        Reader scriptInsert = new InputStreamReader(
            TestSuite.class.getResourceAsStream("/db_insert.sql"));
        
        try(Connection c = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_SENHA);){
            ScriptRunner script = new ScriptRunner(c, true, true);
            script.runScript(scriptCreate);
            script.runScript(scriptInsert);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
