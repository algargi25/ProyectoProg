package es.uji.garcia.data;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;



@Suite
@SuiteDisplayName("Lanzar todos los tests")
@SelectPackages({"es.uji.garcia", "es.uji.garcia", "es.garcia"})
@IncludeClassNamePatterns(".*Test")
public class TestSuite {
}
