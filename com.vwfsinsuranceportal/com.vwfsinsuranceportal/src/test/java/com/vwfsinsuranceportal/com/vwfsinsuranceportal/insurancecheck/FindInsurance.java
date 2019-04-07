package com.vwfsinsuranceportal.com.vwfsinsuranceportal.insurancecheck;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import testbase.ConstantProjectValues;
import testbase.Testbase;
import webpages.InsurancePage;

public class FindInsurance extends Testbase {
	
	InsurancePage insurancePage;
	@Test
	private void findInsurance() throws InterruptedException {
		
		insurancePage = new InsurancePage(super.driver, super.extentTest);
		assertTrue(insurancePage.pageLoadedSuccessfully());
		assertTrue(insurancePage.enterRegistration(ConstantProjectValues.REG_NO));
		assertTrue(insurancePage.clickFindVehicalButton());
		assertTrue(insurancePage.readInsuranceDetails());
	}
}
