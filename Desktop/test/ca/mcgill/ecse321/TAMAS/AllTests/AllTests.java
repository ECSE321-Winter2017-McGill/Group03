package ca.mcgill.ecse321.TAMAS.AllTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.TAMAS.controller.TestTAMASController;
import ca.mcgill.ecse321.TAMAS.persistence.TestTAMASPersistence;

@RunWith(Suite.class)
@SuiteClasses({ TestTAMASController.class, TestTAMASPersistence.class })
public class AllTests {

}