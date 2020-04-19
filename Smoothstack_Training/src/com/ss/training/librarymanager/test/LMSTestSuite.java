package com.ss.training.librarymanager.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ss.training.librarymanager.test.entity.EntityTestSuite;
import com.ss.training.librarymanager.test.service.ServiceTestSuite;

/**
 * @author Justin O'Brien
 *
 */

@RunWith(Suite.class)
@SuiteClasses({LibraryManagerTest.class, EntityTestSuite.class, ServiceTestSuite.class})
public class LMSTestSuite {

}
